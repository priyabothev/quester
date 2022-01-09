package com.test.quest.dto;

public class FoodDTO {
    private String name;
    private int qty;

    public FoodDTO() {
    }

    public FoodDTO(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
