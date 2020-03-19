package com.project.doductrung.android_ver1;

public class Table {
    public int idTable;
    public int statusTable;
    public int moneyTable;
    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public int getMoneyTable() {
        return moneyTable;
    }

    public void setMoneyTable(int moneyTable) {
        this.moneyTable = moneyTable;
    }

    public int getStatusTable() {
        return statusTable;
    }

    public void setStatusTable(int statusTable) {
        this.statusTable = statusTable;
    }

    public Table(int idTable, int moneyTable, int statusTable) {
        this.idTable = idTable;
        this.moneyTable = moneyTable;
        this.statusTable = statusTable;
    }
}
