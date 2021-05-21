package cn.t.util.nb.entity;

public class ServiceInfo {

    private String muteCmds;

    public String getMuteCmds() {
        return muteCmds;
    }

    public void setMuteCmds(String muteCmds) {
        this.muteCmds = muteCmds;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
            "muteCmds='" + muteCmds + '\'' +
            '}';
    }
}
