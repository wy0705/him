package com.lmxdawn.him.api.db.entry;
//生成一个用户时创建一张表，用户ID和用户攻击值
public class User_Attack{
    private String userId;
    private int attack;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public User_Attack() {
    }
}
