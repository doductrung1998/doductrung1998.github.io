package com.project.doductrung.android_ver1;

public class Menu1 {
    public int id;
    public String ten;
    public int giaTien;
    public byte[] picture;
    public int soluong;

    public Menu1(int id, String ten, int giaTien, byte[] picture,int soluong) {
        this.id = id;
        this.ten = ten;
        this.giaTien = giaTien;
        this.picture = picture;
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
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
