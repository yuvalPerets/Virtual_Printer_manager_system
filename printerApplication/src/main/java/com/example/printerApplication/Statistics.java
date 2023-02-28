package com.example.printerApplication;

import java.util.Hashtable;

public class Statistics {
    private long PrinterConnectedAmount ;
    private long PrinterDisConnectedAmount;
    private Hashtable <String , Long > Status_amount_dir ;
    private Hashtable <String , Long > Status_average_time_dir;

    Statistics(Long connectedAmount , Long disConnectedAmount , Hashtable<String,Long> statusAmount , Hashtable<String,Long> avergTime) {
        this.PrinterConnectedAmount =connectedAmount;
        this.PrinterDisConnectedAmount = disConnectedAmount;
        this.Status_amount_dir = statusAmount ;
        this.Status_average_time_dir = avergTime;
    }
    public long getPrinterConnectedAmount(){
        return this.PrinterConnectedAmount;
    }
    public long getPrinterDisConnectedAmount(){
        return this.PrinterDisConnectedAmount;
    }
    public Hashtable<String , Long> getStatus_amount_dir(){
        return  this.Status_amount_dir;
    }

    public Hashtable<String, Long> getStatus_average_time_dir() {
        return Status_average_time_dir;
    }

    public void setPrinterConnectedAmount(Long printerConnectedAmount) {
        PrinterConnectedAmount = printerConnectedAmount;
    }

    public void setPrinterDisConnectedAmount(Long printerDisConnectedAmount) {
        PrinterDisConnectedAmount = printerDisConnectedAmount;
    }

    public void setStatus_amount_dir(Hashtable<String, Long> status_amount_dir) {
        Status_amount_dir = status_amount_dir;
    }

    public void setStatus_average_time_dir(Hashtable<String, Long> status_average_time_dir) {
        Status_average_time_dir = status_average_time_dir;
    }
}
