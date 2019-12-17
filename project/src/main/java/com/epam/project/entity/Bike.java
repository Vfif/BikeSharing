package com.epam.project.entity;

public class Bike extends Entity {
    private int id;
    private String name;
    private String description;
    private String image;
    private String address;
    private double cost;
    private int location;
    private long rentTime;
    private boolean status;

    public Bike() {
    }

    public Bike(int location) {
        this.location = location;
    }

    public Bike(String name, double cost, String address , String description, String image) {
        this.name = name;
        this.cost = cost;
        this.address = address;
        this.description = description;
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getRentTime() {
        return rentTime;
    }

    public void setRentTime(long rentTime) {
        this.rentTime = rentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != Bike.class) return false;

        Bike bike = (Bike) o;

        if (getId() != bike.getId()) return false;
        if (Double.compare(bike.getCost(), getCost()) != 0) return false;
        if (getLocation() != bike.getLocation()) return false;
        if (getRentTime() != bike.getRentTime()) return false;
        if (isStatus() != bike.isStatus()) return false;
        if (getName() != null ? !getName().equals(bike.getName()) : bike.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(bike.getDescription()) : bike.getDescription() != null)
            return false;
        if (getImage() != null ? !getImage().equals(bike.getImage()) : bike.getImage() != null) return false;
        return getAddress() != null ? getAddress().equals(bike.getAddress()) : bike.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        temp = Double.doubleToLongBits(getCost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getLocation();
        result = 31 * result + (int) (getRentTime() ^ (getRentTime() >>> 32));
        result = 31 * result + (isStatus() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bike{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", location=").append(location);
        sb.append(", rentTime=").append(rentTime);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
