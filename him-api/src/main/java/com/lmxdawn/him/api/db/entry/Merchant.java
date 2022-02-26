package com.lmxdawn.him.api.db.entry;

import javax.naming.Name;

public class Merchant {

    private String MC_id;
    private String MC_Name;
    private String password;
    private String MC_ad;

    public String getMC_id() {
        return MC_id;
    }

    public void setMC_id(String MC_id) {
        this.MC_id = MC_id;
    }

    public String getMC_Name() {
        return MC_Name;
    }

    public void setMC_Name(String MC_Name) {
        this.MC_Name = MC_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMC_ad() {
        return MC_ad;
    }

    public void setMC_ad(String MC_ad) {
        this.MC_ad = MC_ad;
    }

    public Merchant() {
    }
}
