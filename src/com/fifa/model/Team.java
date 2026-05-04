package com.fifa.model;

public class Team extends Entity {
    private String name;
    private int ovrAttack;
    private int ovrDefense;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOvrAttack() {
        return ovrAttack;
    }

    public void setOvrAttack(int ovrAttack) {
        this.ovrAttack = ovrAttack;
    }

    public int getOvrDefense() {
        return ovrDefense;
    }

    public void setOvrDefense(int ovrDefense) {
        this.ovrDefense = ovrDefense;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
