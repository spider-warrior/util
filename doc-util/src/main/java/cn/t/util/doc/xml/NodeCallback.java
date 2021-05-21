package cn.t.util.doc.xml;

import org.w3c.dom.Node;

/**
 * node节点回调
 */
public interface NodeCallback<T> {

    void readNode(Node node);

    boolean apply(String nodeName);

    T getResult();
}
