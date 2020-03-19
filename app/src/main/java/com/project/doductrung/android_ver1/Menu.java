package com.project.doductrung.android_ver1;

public class Menu {
    public int id;
    public String ten;
    public int giaTien;
    public byte[] picture;

    public Menu(int id, String ten, int giaTien, byte[] picture) {
        this.id = id;
        this.ten = ten;
        this.giaTien = giaTien;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
