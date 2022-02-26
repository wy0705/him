package com.lmxdawn.him.api.db.entry;

import java.io.Serializable;

public class Equip implements Serializable {
    private String Equip_Name;
    private String Equip_Type;
    private String Equip_Info;
    private String Equip_Energy;
    private int Equip_Money;

    public Equip(String equip_Name, String equip_Type, String equip_Info, String equip_Energy, int equip_Money) {
        Equip_Name = equip_Name;
        Equip_Type = equip_Type;
        Equip_Info = equip_Info;
        Equip_Energy = equip_Energy;
        Equip_Money = equip_Money;
    }

    public Equip() {
    }

    public String getEquip_Name() {
        return Equip_Name;
    }

    public void setEquip_Name(String equip_Name) {
        Equip_Name = equip_Name;
    }

    public String getEquip_Type() {
        return Equip_Type;
    }

    public void setEquip_Type(String equip_Type) {
        Equip_Type = equip_Type;
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
    public int getEquip_Money() {
        return Equip_Money;
    }

    public void setEquip_Money(int equip_Money) {
        Equip_Money = equip_Money;
    }
}
