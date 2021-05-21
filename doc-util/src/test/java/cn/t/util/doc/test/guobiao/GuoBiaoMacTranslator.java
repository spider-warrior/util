package cn.t.util.doc.test.guobiao;

@FunctionalInterface
public interface GuoBiaoMacTranslator {
    String translate (String controllerId, String huiluNo, String deviceNo);
}
