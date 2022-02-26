package com.lmxdawn.him.api.db.dao.mysql;

public class dbMysql {
    private int id;
    private String fanwen;//范围
    private String place;
    private String name;

    public dbMysql() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFanwen() {
        return fanwen;
    }

    public void setFanwen(String fanwen) {
        this.fanwen = fanwen;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
