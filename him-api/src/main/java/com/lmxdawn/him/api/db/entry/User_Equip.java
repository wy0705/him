package com.lmxdawn.him.api.db.entry;

import java.io.Serializable;

public class User_Equip implements Serializable {
    private String UEId;
    private String userid;
    private String Equip_id;
    private String Equip_Name;
    private String Equip_Info;
    private String Equip_Energy;

    public String getUEId() {
        return UEId;
    }

    public void setUEId(String UEId) {
        this.UEId = UEId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEquip_id() {
        return Equip_id;
    }

    public void setEquip_id(String equip_id) {
        Equip_id = equip_id;
    }

    public String getEquip_Name() {
        return Equip_Name;
    }

    public void setEquip_Name(String equip_Name) {
        Equip_Name = equip_Name;
    }

    public String getEquip_Info() {
        return Equip_Info;
    }

    public void setEquip_Info(String equip_Info) {
        Equip_Info = equip_Info;
    }

    public String getEquip_Energy() {
        return Equip_Energy;
    }

    public void setEquip_Energy(String equip_Energy) {
        Equip_Energy = equip_Energy;
    }

    public User_Equip() {
    }
}
