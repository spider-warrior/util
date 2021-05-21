package cn.t.util.nb.entity;

import java.util.List;

public class ServiceCommand {
    private String commandName;
    private List<CommandParams> paras;
    private String responses;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public List<CommandParams> getParas() {
        return paras;
    }

    public void setParas(List<CommandParams> paras) {
        this.paras = paras;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "ServiceCommand{" +
            "commandName='" + commandName + '\'' +
            ", paras=" + paras +
            ", responses='" + responses + '\'' +
            '}';
    }
}
