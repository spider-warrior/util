package cn.t.util.doc.xml;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MixedTypeNodeForeach implements NodeCallback<Map<String, Object>> {

    private final Map<String, Object> result = new HashMap<>();
    private List<NodeCallback<?>> nodeCallbacks = new ArrayList<>();

    @Override
    public void readNode(Node node) {
        NodeListIterable nodeListIterable = new NodeListIterable(node.getChildNodes());
        for (Node n : nodeListIterable) {
            String nodeName = n.getNodeName();
            for (NodeCallback<?> nodeCallback : nodeCallbacks) {
                if (nodeCallback.apply(n.getNodeName())) {
                    nodeCallback.readNode(n);
                    result.put(nodeName, nodeCallback.getResult());
                    break;
                }
            }
        }
    }

    @Override
    public Map<String, Object> getResult() {
        return result;
    }

    public void addNodeCallbacks(List<NodeCallback<?>> nodeCallbacks) {
        if (nodeCallbacks != null) {
            this.nodeCallbacks.addAll(nodeCallbacks);
        }
    }

    public void addNodeCallback(NodeCallback<?> nodeCallback) {
        if (nodeCallback != null) {
            this.nodeCallbacks.add(nodeCallback);
        }
    }
}
