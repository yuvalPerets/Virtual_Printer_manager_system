package com.example.printerApplication;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Printer {


    private  Long id ;
    private String server ;
    private  boolean liveness;
    private ArrayList<Job> JobList;

    Printer(Long id){
        this.id = id;
        this.JobList = new ArrayList<Job>();
        this.liveness = true;

    }
    Printer(String server , boolean liveness , ArrayList<Job> jobList , Long id) {
        this.id = id;
        this.liveness = liveness;
        this.server = server;
        this.JobList = jobList;
    }
    public Long getId () {
        return  this.id;
    }
    public String getServer() {
        return  this.server;
    }
    public boolean getLiveness(){
        return  this.liveness;
    }
    public ArrayList<Job> getJobList (){
        return this.JobList;
    }
    public void setJobList(ArrayList<Job> jobList){
        this.JobList = jobList;
    }
    public void setServer (String server){
        this.server = server;
    }
    public void setLiveness(boolean liveness){
        this.liveness = liveness;
    }
    public void printJob(){
        if (this.JobList.size()>0) {
            Job temp = this.JobList.remove(0);
            System.out.println(temp.getData());
            temp.setStatus("done");
            temp.setPrintedByPrinterId(this.id);
            temp.setPrintDate(new Date() );
        }
    }
    public Job searchJobById (Long id){
        for (Job currentJob: this.JobList) {
            if (currentJob.getId().equals(id))
                return currentJob ;
        }
        return null;
    }


}
