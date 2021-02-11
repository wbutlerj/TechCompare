package edu.byui.cit.techcompare.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.util.Date;
import java.util.Objects;

public class DevicesDB {

    @Exclude
    private String deviceID;
//
    private Object createdDate;

    private String type;
    private String name;
    private String cpu;
    private String screen_size;
    private String battery;
    private String ram;
    private String resolution;
    private double price;
    private String image;
    private String link;

//    Used by firebase (?) copied from Firebase example
    public DevicesDB(){this.createdDate = ServerValue.TIMESTAMP;}

//    Used for searching the list of devices.
    public DevicesDB(String deviceID){ this.deviceID = deviceID; }

    public DevicesDB(String name, String type, String cpu, String screen_size,
                     String battery, String ram, String resolution, double price, String link, String image) {
        this.type = type;
        this.name = name;
        this.cpu = cpu;
        this.screen_size = screen_size;
        this.battery = battery;
        this.ram = ram;
        this.resolution = resolution;
        this.price = price;
        this.image = image;
        this.link = link;
        this.createdDate = ServerValue.TIMESTAMP;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(String screen_size) {
        this.screen_size = screen_size;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicesDB devicesDB = (DevicesDB) o;
        return Double.compare(devicesDB.price, price) == 0 &&
                Objects.equals(type, devicesDB.type) &&
                Objects.equals(name, devicesDB.name) &&
                Objects.equals(cpu, devicesDB.cpu) &&
                Objects.equals(screen_size, devicesDB.screen_size) &&
                Objects.equals(battery, devicesDB.battery) &&
                Objects.equals(ram, devicesDB.ram) &&
                Objects.equals(resolution, devicesDB.resolution);
    }
}
