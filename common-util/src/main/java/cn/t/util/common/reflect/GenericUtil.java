package cn.t.util.common.reflect;

import cn.t.util.common.ArrayUtil;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class GenericUtil {

    public static Class<?> findTypeParam(final Object object, Class<?> parameterizedSuperclass, String typeParamName) {
        final Class<?> thisClass = object.getClass();
        Class<?> currentClass = thisClass;
        for (;;) {
            Class<?> superClass = null;
            if(parameterizedSuperclass.isInterface()) {
                Class<?>[] interfaces = currentClass.getInterfaces();
                if(!ArrayUtil.isEmpty(interfaces)) {
                    for(Class<?> i: interfaces) {
                        if(i.isAssignableFrom(currentClass)) {
                            superClass = i;
                            break;
                        }
                    }
                }
            } else {
                superClass = currentClass.getSuperclass();
            }
            if (superClass == parameterizedSuperclass) {
                int typeParamIndex = -1;
                TypeVariable<?>[] typeParams = superClass.getTypeParameters();
                for (int i = 0; i < typeParams.length; i++) {
                    if (typeParamName.equals(typeParams[i].getName())) {
                        typeParamIndex = i;
                        break;
                    }
                }
                if (typeParamIndex < 0) {
                    throw new IllegalStateException(
                        "unknown type parameter '" + typeParamName + "': " + parameterizedSuperclass);
                }

                Type genericSuperType = null;
                if(parameterizedSuperclass.isInterface()) {
                    Type[] types = currentClass.getGenericInterfaces();
                    if(!ArrayUtil.isEmpty(types)) {
                        for(Type t: types) {
                            genericSuperType = t;
                            break;
                        }
                    }
                } else {
                    genericSuperType = currentClass.getGenericSuperclass();
                }
                if (!(genericSuperType instanceof ParameterizedType)) {
                    return Object.class;
                }

                Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();

                Type actualTypeParam = actualTypeParams[typeParamIndex];
                if (actualTypeParam instanceof ParameterizedType) {
                    actualTypeParam = ((ParameterizedType) actualTypeParam).getRawType();
                }
                if (actualTypeParam instanceof Class) {
                    return (Class<?>) actualTypeParam;
                }
                if (actualTypeParam instanceof GenericArrayType) {
                    Type componentType = ((GenericArrayType) actualTypeParam).getGenericComponentType();
                    if (componentType instanceof ParameterizedType) {
                        componentType = ((ParameterizedType) componentType).getRawType();
                    }
                    if (componentType instanceof Class) {
                        return Array.newInstance((Class<?>) componentType, 0).getClass();
                    }
                }
                if (actualTypeParam instanceof TypeVariable) {
                    // Resolved type parameter points to another type parameter.
                    TypeVariable<?> v = (TypeVariable<?>) actualTypeParam;
                    currentClass = thisClass;
                    if (!(v.getGenericDeclaration() instanceof Class)) {
                        return Object.class;
                    }

                    parameterizedSuperclass = (Class<?>) v.getGenericDeclaration();
                    typeParamName = v.getName();
                    if (parameterizedSuperclass.isAssignableFrom(thisClass)) {
                        continue;
                    } else {
                        return Object.class;
                    }
                }
                throw new IllegalStateException("cannot determine the type of the type parameter '" + thisClass + "': " + typeParamName);
            }
            currentClass = superClass;
            if (currentClass == null) {
                throw new IllegalStateException("cannot determine the type of the type parameter '" + thisClass + "': " + typeParamName);
            }
        }

    }

    /**
     * ???????????????????????????
     * @param clazz xxx
     * @return xxx
     */
    public static List<ClassGeneric> getGeneric(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        List<ClassGeneric> result = new ArrayList<>();
        Type type = clazz.getGenericSuperclass();
        if(!Object.class.equals(type)) {
            ClassGeneric classGeneric = doGetGeneric(type);
            if(classGeneric != null) {
                result.add(classGeneric);
            }
        }
        Type[] types = clazz.getGenericInterfaces();
        for(Type t: types) {
            ClassGeneric classGeneric = doGetGeneric(t);
            if(classGeneric != null) {
                result.add(classGeneric);
            }
        }
        return result;
    }

    public static ClassGeneric doGetGeneric(Type type) {
        if (type == null) {
            return null;
        }
        //result
        ClassGeneric classGeneric = new ClassGeneric();
        //????????????
        List<Generic> genericList = new ArrayList<>();
        //????????????
        classGeneric.setGenericList(genericList);
        // ????????????
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            //??????????????????
            Type rawType = parameterizedType.getRawType();
            if (rawType instanceof Class) {
                Class<?> superClass = (Class<?>) rawType;
                if (!Object.class.equals(superClass)) {
                    TypeVariable<?>[] superTypeVariables = superClass.getTypeParameters();
                    ClassGeneric superClassGeneric = new ClassGeneric();
                    List<Generic> superGenericList = new ArrayList<>();
                    superClassGeneric.setGenericList(superGenericList);
                    for (TypeVariable<?> superTypeVariable : superTypeVariables) {
                        Generic superGeneric = new Generic();
                        superGeneric.setType(superTypeVariable.getName());
                        superGenericList.add(superGeneric);
                    }
                    classGeneric.setParent(superClassGeneric);
                    Type superSuperClass = superClass.getSuperclass();
                    if (!Object.class.equals(superSuperClass)) {
                        Type superSuperType = superClass.getGenericSuperclass();
                        superClassGeneric.setParent(doGetGeneric(superSuperType));
                    }
                }
            }
            //??????????????????
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type t : types) {
                Generic generic = new Generic();
                if (t instanceof ParameterizedType) {
                    generic.setType(((ParameterizedType) t).getRawType().getTypeName());
                    generic.setEmbedClassGeneric(doGetGeneric(t));
                } else if (t instanceof TypeVariable) {
                    generic.setType(((TypeVariable<?>) t).getName());
                } else if (t instanceof Class) {
                    generic.setType(((Class<?>) t).getName());
                } else {
                    System.out.println("[warn] run in else");
                }
                genericList.add(generic);
            }

            // ????????????????????????
        } else if (type instanceof TypeVariable) {
            Generic generic = new Generic();
            generic.setType(((TypeVariable<?>) type).getName());
            genericList.add(generic);
            // class????????????type???????????????
        } else if (type instanceof Class) {// class????????????type???????????????
            Generic generic = new Generic();
            generic.setType(((Class<?>) type).getName());
            genericList.add(generic);
        } else {
            System.out.println("[warn] run in else");
        }
        return classGeneric;
    }

    private static Class<?> getClass(Type type, int i) {
        if (type instanceof ParameterizedType) { // ??????????????????
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return getClass(((TypeVariable<?>) type).getBounds()[0], 0); // ????????????????????????
        } else {// class????????????type???????????????
            return (Class<?>) type;
        }
    }

    private static Class<?> getGenericClass(ParameterizedType parameterizedType, int i) {
        Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) { // ??????????????????
            return (Class<?>) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // ??????????????????
            return (Class<?>) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // ????????????????????????
            return getClass(((TypeVariable<?>) genericClass).getBounds()[0], 0);
        } else {
            return (Class<?>) genericClass;
        }
    }


    /**
     * ??????????????????
     */
    private static class ClassGeneric {
        /**
         * ????????????
         */
        private ClassGeneric parent;
        /**
         * ????????????List
         */
        private List<Generic> genericList;

        public ClassGeneric getParent() {
            return parent;
        }

        public ClassGeneric setParent(ClassGeneric parent) {
            this.parent = parent;
            return this;
        }

        public List<Generic> getGenericList() {
            return genericList;
        }

        public ClassGeneric setGenericList(List<Generic> genericList) {
            this.genericList = genericList;
            return this;
        }

        @Override
        public String toString() {
            return "ClassGeneric{" +
                "parent=" + parent +
                ", genericList=" + genericList +
                '}';
        }
    }

    /**
     * ????????????
     */
    public static class Generic {

        /**
         * ??????????????????
         */
        private String type;

        /**
         * ???????????????????????????
         */
        private ClassGeneric embedClassGeneric;

        public String getType() {
            return type;
        }

        public Generic setType(String type) {
            this.type = type;
            return this;
        }

        public ClassGeneric getEmbedClassGeneric() {
            return embedClassGeneric;
        }

        public Generic setEmbedClassGeneric(ClassGeneric embedClassGeneric) {
            this.embedClassGeneric = embedClassGeneric;
            return this;
        }

        @Override
        public String toString() {
            return "Generic{" +
                "type='" + type + '\'' +
                ", embedClassGeneric=" + embedClassGeneric +
                '}';
        }
    }
}
