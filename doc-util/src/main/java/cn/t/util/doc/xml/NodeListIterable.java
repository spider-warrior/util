package cn.t.util.doc.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NodeListIterable implements Iterable<Node> {

    private final NodeList nodeList;

    public NodeListIterable(NodeList nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Node> {

        private final Set<String> excludeNodeNames = new HashSet<>() {{
            add("#text");
        }};

        private int index;
        private final int size;
        private Node currentNode;

        private NodeIterator() {
            this.index = 0;
            this.size = nodeList.getLength();
        }

        @Override
        public boolean hasNext() {
            if (index < size) {
                while (index < size) {
                    currentNode = nodeList.item(index++);
                    if (currentNode != null && validNode(currentNode)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public Node next() {
            return currentNode;
        }

        private boolean validNode(Node node) {
            try {
                return !excludeNodeNames.contains(node.getNodeName());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}



