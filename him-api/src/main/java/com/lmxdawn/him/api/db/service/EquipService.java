package com.lmxdawn.him.api.db.service;

import com.lmxdawn.him.api.db.dao.hbaseImpl.EquipUtil;
import com.lmxdawn.him.api.db.entry.Equip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EquipService {
    //上新装备款式
    @Autowired
    private EquipUtil equipUtil;

    public Equip add(Equip equip) throws IOException {
        equipUtil.add(equip.getEquip_Name(),equip.getEquip_Type(),equip.getEquip_Info(),equip.getEquip_Energy(),equip.getEquip_Money());
        return equip;
    }

    public void queryAll(){
        equipUtil.query();
    }

    public void getData(String name) throws IOException {
        equipUtil.Data(name);
    }
}
