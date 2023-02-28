package com.example.printerApplication;

import java.util.ArrayList;

public class PrinterWithJobs {
    private Printer printer ;
    private ArrayList<Job> jobList;

    PrinterWithJobs(){
        this.printer = new Printer(Long.valueOf(10));
        this.jobList=new ArrayList<Job>();
    }
    PrinterWithJobs(Printer printer , ArrayList<Job> jobList){
        this.printer = printer;
        this.jobList=jobList;
    }
    public  Printer getPrinter(){
        return this.printer;
    }
    public ArrayList<Job> getJobList(){
        return this.jobList;
    }
}
