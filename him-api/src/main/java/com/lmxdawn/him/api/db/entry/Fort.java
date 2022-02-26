package com.lmxdawn.him.api.db.entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.io.Serializable;
@Document(indexName = "fort",type="_doc",createIndex = false)
public class Fort implements Serializable {
    @Id
    private Long Fort_id;
    @Field(store = true)
    private String Fort_Name;
    @Field(store = true)
    @GeoPointField
    private Geo geo;//经纬度
    @Field(store = true)
    private long Fort_Energy;
    @Field(store = true)
    private String place;

    public Long getFort_id() {
        return Fort_id;
    }

    public void setFort_id(Long fort_id) {
        Fort_id = fort_id;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getFort_Energy() {
        return Fort_Energy;
    }
    public void setFort_Energy(long fort_Energy) {
        Fort_Energy = fort_Energy;
    }

    public String getFort_Name() {
        return Fort_Name;
    }

    public void setFort_Name(String fort_Name) {
        Fort_Name = fort_Name;
    }

    public Fort() {
    }
    @Override
    public String toString(){
        return "Fort{"+
                "fortId="+Fort_id+
                ", fortName='" + Fort_Name + '\'' +
                ", geo='" + geo + '\'' +
                ", energy='" + Fort_Energy + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

}
