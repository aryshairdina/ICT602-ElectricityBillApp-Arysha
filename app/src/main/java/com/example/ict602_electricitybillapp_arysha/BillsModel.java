package com.example.ict602_electricitybillapp_arysha;


import java.io.Serializable;

public class BillsModel implements Serializable {
    public String month;
    public int units;
    public double rebate, total, finalCost;

    public BillsModel(String month, int units, double rebate, double total, double finalCost) {
        this.month = month;
        this.units = units;
        this.rebate = rebate;
        this.total = total;
        this.finalCost = finalCost;
    }
}

