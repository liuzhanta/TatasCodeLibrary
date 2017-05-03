package com.tata.android.bean;

public class Card implements BaseModel {
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    
    public String getName() {
        return name;
    }
    
    public Card(String name) {
        super();
        this.name = name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
