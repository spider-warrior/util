package cn.t.util.common;

import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    public static byte[] combine(byte[]... bytesArrays) {
        if (bytesArrays == null) {
            return null;
        } else if (bytesArrays.length == 0) {
            return new byte[0];
        } else {
            byte[] result = null;
            for (byte[] bs : bytesArrays) {
                if (result == null) {
                    result = bs;
                } else {
                    if (bs != null && bs.length > 0) {
                        int startIndex = result.length;
                        result = Arrays.copyOf(result, result.length + bs.length);
                        System.arraycopy(bs, 0, result, startIndex, bs.length);
                    }
                }
            }
            return result;
        }
    }

    public static int binarySearch(byte[] source, byte[] target) {
        return binarySearch(source, 0, source.length,
            target, 0, target.length, 0);
    }

    public static byte[] reverse(byte[] bytes) {
        if(bytes == null || bytes.length < 2) {
            return bytes;
        } else {
            byte [] newArray = new byte[bytes.length];
            for(int i=0; i<bytes.length; i++){
                newArray[i] = bytes[bytes.length - i - 1];
            }
            return newArray;
        }
    }

    public static int[] reverse(int[] ints) {
        if(ints == null || ints.length < 2) {
            return ints;
        } else {
            int [] newArray = new int[ints.length];
            for(int i=0; i<ints.length; i++){
                newArray[i] = ints[ints.length - i - 1];
            }
            return newArray;
        }
    }

    public static String join(byte[] bytes, String delimiter) {
        if(bytes == null) {
            return null;
        } else if(bytes.length == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for(byte b: bytes) {
                builder.append(b).append(delimiter);
            }
            int end = builder.length();
            int begin = end - delimiter.length();
            return builder.delete(begin, end).toString();
        }
    }

    public static String join(int[] ints, String delimiter) {
        if(ints == null) {
            return null;
        } else if(ints.length == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            for(int b: ints) {
                builder.append(b).append(delimiter);
            }
            int end = builder.length();
            int begin = end - delimiter.length();
            return builder.delete(begin, end).toString();
        }
    }

    public static int binarySearch(byte[] source, int sourceOffset, int sourceCount,
                                    byte[] target, int targetOffset, int targetCount,
                                    int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        byte first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j] == target[k]; j++, k++);
                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }

    public static byte[] add(byte[] arr, byte... data) {
        if(data != null && data.length > 0) {
            byte[] arrNew = Arrays.copyOf(arr, arr.length + data.length);
            System.arraycopy(data, 0, arrNew, arr.length, data.length);
            arr = arrNew;
        }
        return arr;
    }

    public static int[] convertToIntArray(List<Integer> integerList) {
        if(CollectionUtil.isEmpty(integerList)) {
            return new int[0];
        } else {
            return integerList.stream().mapToInt(i->i).toArray();
        }
    }

    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length==0;
    }
    public static boolean isEmpty(byte[] arr) {
        return arr == null || arr.length==0;
    }
    public static boolean isEmpty(short[] arr) {
        return arr == null || arr.length==0;
    }
    public static boolean isEmpty(char[] arr) {
        return arr == null || arr.length==0;
    }
    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length==0;
    }
    public static boolean isEmpty(long[] arr) {
        return arr == null || arr.length==0;
    }
    public static boolean isEmpty(float[] arr) {
        return arr == null || arr.length==0;
    }
    public static boolean isEmpty(double[] arr) {
        return arr == null || arr.length==0;
    }


    public static int[] toPrimitive(final Integer[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new int[0];
        }
        final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static Integer[] toObject(final int[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new Integer[0];
        }
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
