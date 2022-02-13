package com.me.jv.model;

import java.util.List;

public class DigitalCoinResponse {
    String id;
    String name;
    String symbol;
    String rank;
    boolean is_new;
    boolean is_active;
    String type;
    public String getId() {
        return id; 
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public boolean isIs_new() {
        return is_new;
    }
    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }
    public boolean isIs_active() {
        return is_active;
    }
    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}     