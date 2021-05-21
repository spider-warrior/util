package cn.t.util.common;

import cn.t.util.common.math.LogarithmUtil;

import java.util.Iterator;
import java.util.List;

public class PrintUtil {

    /**
     * 输出二叉树
     * @param array 数据源
     * @param scale 比例
     */
    public static void printBinaryTree(Object[] array, int scale) {
        if (array != null && array.length > 0) {
            String split = ".";
            int len = LogarithmUtil.logUp(array.length + 1, 2);
            for (int i = 0; i < len; i++) {
                int last = (int) Math.pow(2, i) - 2;
                //输出数据
                int total = (int) Math.pow(2, i);
                for (int j = 1; j <= total; j++) {
                    for (int k = 0; k < Math.pow(2, len - i) * scale; k++) {
                        System.out.print(split);
                    }
                    if ((last + j) >= array.length) {
                        break;
                    }
                    System.out.print(array[last + j]);
                    int strLen = String.valueOf(array[last + j]).length();
                    for (int k = 0; k < (Math.pow(2, len - i) - 1) * scale + (scale - 1) - (strLen - 1); k++) {
                        System.out.print(split);
                    }
                }
                System.out.println();
            }
        }
    }

    public static void printBinaryTree(int[] array) {
        printBinaryTree(ArrayUtil.toObject(array), 1);
    }
    public static void printBinaryTree(int[] array, int scale) {
        printBinaryTree(ArrayUtil.toObject(array), scale);
    }

    public static void printBinaryTree(Integer[] array) {
        printBinaryTree(array, 1);
    }

    public static class TreeNode {
        final String name;
        final List<TreeNode> children;

        public TreeNode(String name, List<TreeNode> children) {
            this.name = name;
            this.children = children;
        }

        public String toString() {
            StringBuilder buffer = new StringBuilder(50);
            print(buffer, "", "");
            return buffer.toString();
        }

        private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            buffer.append(name);
            buffer.append('\n');
            for (Iterator<TreeNode> it = children.iterator(); it.hasNext();) {
                TreeNode next = it.next();
                if (it.hasNext()) {
                    next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                } else {
                    next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
                }
            }
        }
    }
}
