package com_dog.dog.dog_java.dogdom.Modules;

public class ItemHouse {
    private int img;
    private String title, street;

    public ItemHouse(int img, String title, String street) {
        this.img = img;
        this.title = title;
        this.street = street;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
