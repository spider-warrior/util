package cn.t.util.doc.test;

import cn.t.util.doc.XmlUtil;
import cn.t.util.doc.xml.MixedTypeNodeForeach;
import cn.t.util.doc.xml.NodeCallback;
import cn.t.util.doc.xml.NodeForeachCallback;
import cn.t.util.doc.xml.NodeListIterable;
import cn.t.util.common.FileUtil;
import org.junit.Test;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试xml文件
 */
public class XmlUtilTest {
    @Test
    public void testReadSimple() throws IOException, SAXException, ParserConfigurationException {
        String path = "/xml/simple.xml";
        UserListCallback userListCallback = new UserListCallback();
        DogListCallback dogListCallback = new DogListCallback();
        MixedTypeNodeForeach outerObjectsNodeForeach = new MixedTypeNodeForeach() {
            @Override
            public boolean apply(String nodeName) {
                return "outer".equals(nodeName);
            }
        };
        outerObjectsNodeForeach.addNodeCallback(userListCallback);
        outerObjectsNodeForeach.addNodeCallback(dogListCallback);
        XmlUtil.domReadDocument(FileUtil.getResourceInputStream(XmlUtilTest.class, path), outerObjectsNodeForeach);
        System.out.println(outerObjectsNodeForeach.getResult());
    }


    @Test
    public void testRestComplex() throws ParserConfigurationException, SAXException, IOException {
        String path = "/home/amen/test.xml";
        UserListCallback userListCallback = new UserListCallback();
        DogListCallback dogListCallback = new DogListCallback();
        ObjectsNodeForeach objectsNodeForeach = new ObjectsNodeForeach();
        List<NodeCallback<?>> objectCallbacks = new ArrayList<>();
        objectCallbacks.add(userListCallback);
        objectCallbacks.add(dogListCallback);
        objectsNodeForeach.addNodeCallbacks(objectCallbacks);
        MixedTypeNodeForeach packetObjectsNodeForeach = new MixedTypeNodeForeach() {
            @Override
            public boolean apply(String nodeName) {
                return "packet".equals(nodeName);
            }
        };
        packetObjectsNodeForeach.addNodeCallback(objectsNodeForeach);
        MixedTypeNodeForeach outerObjectsNodeForeach = new MixedTypeNodeForeach() {
            @Override
            public boolean apply(String nodeName) {
                return "outer".equals(nodeName);
            }
        };
        outerObjectsNodeForeach.addNodeCallback(packetObjectsNodeForeach);
        XmlUtil.domReadDocument(path, outerObjectsNodeForeach);
        System.out.println(outerObjectsNodeForeach.getResult());
    }

}


class ObjectsNodeForeach extends MixedTypeNodeForeach {

    @Override
    public boolean apply(String nodeName) {
        return "objects".equals(nodeName);
    }
}


class UserListCallback extends NodeForeachCallback<User> {

    private List<User> userList = new ArrayList<>();

    public UserListCallback() {
        super(new UserCallback());
    }

    @Override
    public boolean apply(String nodeName) {
        return "users".equals(nodeName);
    }

    @Override
    public List<User> getResult() {
        return userList;
    }
}

class UserCallback implements NodeCallback<User> {

    private User user;

    @Override
    public void readNode(Node node) {
        user = new User();
        NodeListIterable nodeListIterable = new NodeListIterable(node.getChildNodes());
        for (Node n : nodeListIterable) {
            if (n.getNodeName().equals("name")) {
                user.setName(n.getTextContent());
            } else if (n.getNodeName().equals("age")) {
                user.setAge(n.getTextContent());
            } else if (n.getNodeName().equals("sex")) {
                user.setSex(n.getTextContent());
            }
        }
    }

    @Override
    public boolean apply(String nodeName) {
        return "user".equals(nodeName);
    }

    @Override
    public User getResult() {
        return user;
    }
}

class User {

    private String name;

    private String age;

    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age='" + age + '\'' +
            ", sex='" + sex + '\'' +
            '}';
    }

}


class DogListCallback extends NodeForeachCallback<Dog> {

    private List<Dog> dogList = new ArrayList<>();

    public DogListCallback() {
        super(new DogCallback());
    }

    @Override
    public boolean apply(String nodeName) {
        return "dogs".equals(nodeName);
    }

    @Override
    public List<Dog> getResult() {
        return dogList;
    }
}


class DogCallback implements NodeCallback<Dog> {

    private Dog dog;

    @Override
    public void readNode(Node node) {
        dog = new Dog();
        NodeListIterable nodeListIterable = new NodeListIterable(node.getChildNodes());
        for (Node n : nodeListIterable) {
            if (n.getNodeName().equals("name")) {
                dog.setName(n.getTextContent());
            } else if (n.getNodeName().equals("color")) {
                dog.setColor(n.getTextContent());
            }
        }
    }

    @Override
    public boolean apply(String nodeName) {
        return "dog".equals(nodeName);
    }

    @Override
    public Dog getResult() {
        return dog;
    }
}

class Dog {
    private String name;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
            "name='" + name + '\'' +
            ", color='" + color + '\'' +
            '}';
    }
}
