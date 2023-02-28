package com.example.printerApplication;

import java.util.Date;

public class Job {
    private static long jobCount = 1 ;

    private  long id;
    private  String status ;
    private String data ;
    private Date createDate ;
    private Date printDate ;
    private Long PrintedByPrinterId;
    Job( ){
        this.id =this.jobCount++;
        this.createDate = new Date ();
        this.status = "padding";
    }
    Job (String data){
        this.id =this.jobCount++;
        this.createDate = new Date ();
        this.data = data;
        this.status = "padding";
    }
    public Long getId () {
        return this.id;
    }
    public String getStatus(){
        return this.status;
    }
    public String getData (){
        return this.data;
    }
    public Date getCreateDate(){
        return  this.createDate;
    }
    public Date getPrintDate(){
        return  this.printDate;
    }
    public Long getPrintedByPrinterId(){
        return  this.PrintedByPrinterId;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public void setPrintedByPrinterId(Long id){
        this.PrintedByPrinterId=id;
    }
    public void setPrintDate(Date date) {this.printDate = date ;}
}
