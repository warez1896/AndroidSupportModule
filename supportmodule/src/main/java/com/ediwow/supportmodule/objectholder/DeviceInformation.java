package com.ediwow.supportmodule.objectholder;

public class DeviceInformation {
    private String manufacturer, modelName, uniqueID;

    public DeviceInformation(String manufacturer, String modelName, String uniqueID) {
        setManufacturer(manufacturer);
        setModelName(modelName);
        setUniqueID(uniqueID);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
}
