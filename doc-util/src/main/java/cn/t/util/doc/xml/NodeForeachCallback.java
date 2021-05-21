package cn.t.util.doc.xml;

import org.w3c.dom.Node;

import java.util.List;

/**
 * node节点回调, foreach遍历子节点
 */
public abstract class NodeForeachCallback<T> implements NodeCallback<List<T>> {

    private final NodeCallback<T> nodeCallback;

    public NodeForeachCallback(NodeCallback<T> nodeCallback) {
        this.nodeCallback = nodeCallback;
    }

    @Override
    public void readNode(Node node) {
        NodeListIterable nodeListIterable = new NodeListIterable(node.getChildNodes());
        for (Node n : nodeListIterable) {
            if (nodeCallback.apply(n.getNodeName())) {
                nodeCallback.readNode(n);
            }
            getResult().add(nodeCallback.getResult());
        }
    }
}
