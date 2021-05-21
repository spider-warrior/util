package cn.t.util.nb.entity;

import java.util.List;

public class ServiceCapability {
    private String serviceId;
    private String serviceType;
    private String option;
    private String description;
    private List<ServiceCommand> commands;
    private List<ServiceProperties> properties;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ServiceCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<ServiceCommand> commands) {
        this.commands = commands;
    }

    public List<ServiceProperties> getProperties() {
        return properties;
    }

    public void setProperties(List<ServiceProperties> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ServiceCapability{" +
            "serviceId='" + serviceId + '\'' +
            ", serviceType='" + serviceType + '\'' +
            ", option='" + option + '\'' +
            ", description='" + description + '\'' +
            ", commands=" + commands +
            ", properties=" + properties +
            '}';
    }
}
