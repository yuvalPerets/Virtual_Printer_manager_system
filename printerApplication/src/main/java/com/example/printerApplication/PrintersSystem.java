package com.example.printerApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class PrintersSystem {
    private ArrayList<Printer> printerList;
    private ArrayList<Job> jobList;

    PrintersSystem() {
        this.printerList = new ArrayList<Printer>();
        this.jobList = new ArrayList<Job>();
    }
    public ArrayList<Job> getServerJobList (){
        return this.jobList;
    }

    public void addPrinter(Long id) {
        this.printerList.add(new Printer(id));
    }

    public void addPrinter(Printer printer) {
        this.printerList.add(printer);
    }

    public ArrayList<Printer> getPrinterList() {
        return this.printerList;
    }
    public  ArrayList<Job> getAllJobList(){
        return  this.jobList;
    }

    public void PrintByJobId(Long jobId) {
        for (Printer printer : this.printerList) {
            if (printer.searchJobById(jobId) != null) {
                Job job = printer.searchJobById(jobId);
                printer.getJobList().remove(job);
                printer.getJobList().add(0,job);
                printer.printJob();
            }
        }
    }

    public Printer getPrinterById(Long printerId) {

        for (Printer printer : this.printerList)
            if (printer.getId().equals(printerId))
                return printer;
        return null;
    }

    public ArrayList<Job> getPrinterJobList(Long printerId) {
        Printer printer = getPrinterById(printerId);
        if (printer != null) {
            ArrayList<Job> allJobList = new ArrayList<Job>();
            for (Job workingJob : printer.getJobList()) {
                if (workingJob.getPrintedByPrinterId().equals(printer.getId()))
                    allJobList.add((workingJob));
            }
            return allJobList;
        }
        return null;
    }

    public void deletePrinter(Long printerId) {
        getPrinterById(printerId).setLiveness(false);
    }

    public void setAlivePrinter(Long printerId) {
        getPrinterById(printerId).setLiveness(true);
    }

    public Long createNewJob(Long printerId, Job jobToAdd) {
        getPrinterById(printerId).getJobList().add(jobToAdd);
        this.jobList.add(jobToAdd);
        return jobToAdd.getId();

    }

    public ArrayList<Job> getAllJobWithStatus(Long printerId, String status) {
        ArrayList<Job> temp = new ArrayList<Job>();
        for (Job workingJob : getPrinterById(printerId).getJobList()) {
            if (status.equals("all")) // אם לא תישלח סטטוס בפוקדת ה-GET נשלח מן הCONTROLLER את הסטטוס ALL
                temp.add(workingJob);
            else if (workingJob.getStatus().equals(status))
                temp.add(workingJob);

        }
        return temp;
    }

    public void updateJob(Long jobId, String newStatus) {
        for (Job workingJob : this.jobList) {
            if (workingJob.getId().equals(jobId))
                workingJob.setStatus(newStatus);
        }
    }

    public Job getJobDetails(Long jobId) {
        for (Job workingJob : this.jobList) {
            if (workingJob.getId().equals(jobId))
                return workingJob;
        }
        return null;
    }

    public ArrayList<Job> getAllJob(String statusFilter, Date dateFilter) { // if date isn't specifed sending the dawn of days
        ArrayList<Job> temp = new ArrayList<Job>();
        for (Job workingJob : this.jobList) {
            if (statusFilter.equals("all")) { // check only time because status wasn't specifed
                if (workingJob.getCreateDate().after(dateFilter))
                    temp.add(workingJob);
            } else {
                if (workingJob.getCreateDate().after(dateFilter) && workingJob.getStatus().equals(statusFilter))
                    temp.add(workingJob);
            }

        }
        return temp;
    }

    public Statistics getSystemStats() {
        long connectedPrinters = 0;
        long disConnectedPrinters = 0;
        for (Printer printer : this.printerList) {
            if (printer.getLiveness())
                connectedPrinters++;
            else
                disConnectedPrinters++;
        }
        Hashtable<String, Long> Status_amount_dir = new Hashtable<String, Long>();
        Hashtable<String, Long> Status_average_time_dir = new Hashtable<String, Long>();
        Date date;
        for (Job workingJob : this.jobList) {
            if (Status_amount_dir.containsKey(workingJob.getStatus())) {
                if (workingJob.getPrintDate()==null)
                     date = new Date();
                else
                    date = workingJob.getPrintDate();
                long workTime = Math.abs(date.getTime() - workingJob.getCreateDate().getTime());
                System.out.println(workTime);
                long totalWorkTime = Status_amount_dir.get(workingJob.getStatus()) * Status_average_time_dir.get(workingJob.getStatus());
                Status_amount_dir.put(workingJob.getStatus(), Status_amount_dir.get(workingJob.getStatus()) + 1);
                Status_average_time_dir.put(workingJob.getStatus(), (totalWorkTime + workTime) / Status_amount_dir.get(workingJob.getStatus()));
            } else {
                if (workingJob.getPrintDate()==null)
                    date = new Date();
                else
                    date = workingJob.getPrintDate();
                Status_amount_dir.put(workingJob.getStatus(), Long.valueOf(1));
                long workTime = Math.abs(date.getTime() - workingJob.getCreateDate().getTime());
                System.out.println(workTime);
                Status_average_time_dir.put(workingJob.getStatus(), workTime);
            }

        }
        return new Statistics(connectedPrinters, disConnectedPrinters, Status_amount_dir, Status_average_time_dir);

    }


}
