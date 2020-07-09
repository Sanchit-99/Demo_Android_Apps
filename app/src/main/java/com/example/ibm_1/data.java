package com.example.ibm_1;

public class data {

    String name,num;
    int image;

    public data(String name, int image,String num) {
        this.name = name;
        this.image = image;
        this.num=num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
