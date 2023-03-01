package com.example.printerApplication;

import com.sun.jdi.LongValue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PrinterTests {

    @Test
    public void CheckPrinterId()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        assertEquals(Long.valueOf(100), newPrinter.getId());
    }
    @Test
    public void CheckPrinterIslive()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        assertTrue(newPrinter.getLiveness());
    }

    @Test
    public void CheckPrinterIsNotAlive()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        newPrinter.setLiveness(false);
        assertFalse(newPrinter.getLiveness());
    }

    @Test
    public void CheckPrinterJobList()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        newPrinter.getJobList().add(job1);
        newPrinter.getJobList().add(job2);
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        assertEquals(jobs, newPrinter.getJobList());
    }

    @Test
    public void CheckSetPrinterJobList()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        newPrinter.setJobList(jobs);
        assertEquals(jobs, newPrinter.getJobList());
    }

    @Test
    public void CheckPrint()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        newPrinter.setJobList(jobs);
        int jobAmount = newPrinter.getJobList().size();
        newPrinter.printJob();
        assertEquals(jobAmount-1, newPrinter.getJobList().size());
    }

    @Test
    public void CheckJobPrintedStatus()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        newPrinter.setJobList(jobs);
        newPrinter.printJob();
        assertEquals("done", job1.getStatus());
    }

    @Test
    public void CheckJobPrintedById()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        newPrinter.setJobList(jobs);
        newPrinter.printJob();
        assertEquals(newPrinter.getId(), job1.getPrintedByPrinterId());
    }

    @Test
    public void CheckJobPrintDate()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        newPrinter.setJobList(jobs);
        //pause the program for 1 sec
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();}

        newPrinter.printJob();

        assertTrue( job1.getPrintDate().after(job1.getCreateDate()));
    }

    @Test
    public void SearchJobIdFound()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("first");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        newPrinter.setJobList(jobs);
        assertEquals( job2,newPrinter.searchJobById(job2.getId()));

    }

    @Test
    public void SearchJobIdNOTFound()
    {
        Printer newPrinter = new Printer(Long.valueOf(100));
        Job job1 = new Job("first");
        Job job2 = new Job("second job");
        Job job3 = new Job("third job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        newPrinter.setJobList(jobs);
        assertNull(newPrinter.searchJobById(job3.getId()));

    }

    @Test
    public void CheckPrinterServer()
    {
        String Server = "server";
        Printer newPrinter = new Printer( Server , true, null, Long.valueOf(100));
        assertEquals(Server ,newPrinter.getServer());
    }

    @Test
    public void CheckSetPrinterServer()
    {
        String Server = "server";
        Printer newPrinter = new Printer( Long.valueOf(100));
        newPrinter.setServer(Server);
        assertEquals(Server ,newPrinter.getServer());
    }
}
