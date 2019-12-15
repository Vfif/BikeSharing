package com.epam.project.entity;

import java.util.Date;

public class Trip extends Entity{
    private int id;
    private int mark;
    private int userId;
    private int bikeId;
    private String bikeName;
    private double money;
    private Date time;

    public Trip(int userId, int bikeId, double money, Date time) {
        this.userId = userId;
        this.bikeId = bikeId;
        this.money = money;
        this.time = time;
    }

    public Trip() {
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != Trip.class) return false;

        Trip trip = (Trip) o;

        if (getId() != trip.getId()) return false;
        if (getMark() != trip.getMark()) return false;
        if (getUserId() != trip.getUserId()) return false;
        if (getBikeId() != trip.getBikeId()) return false;
        if (Double.compare(trip.getMoney(), getMoney()) != 0) return false;
        return getTime() != null ? getTime().equals(trip.getTime()) : trip.getTime() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getMark();
        result = 31 * result + getUserId();
        result = 31 * result + getBikeId();
        temp = Double.doubleToLongBits(getMoney());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trip{");
        sb.append("id=").append(id);
        sb.append(", mark=").append(mark);
        sb.append(", userId=").append(userId);
        sb.append(", bikeId=").append(bikeId);
        sb.append(", money=").append(money);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
