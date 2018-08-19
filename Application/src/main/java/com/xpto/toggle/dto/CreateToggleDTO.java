package com.xpto.toggle.dto;

public class CreateToggleDTO {
    private String serviceName;
    private String version;
    private ToggleDTO toggle;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ToggleDTO getToggle() {
        return toggle;
    }

    public void setToggle(ToggleDTO toggle) {
        this.toggle = toggle;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
