package com.example.printerApplication;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PrinterSystemTests {

    @Test
    public void CheckCreatePrinterList()
    {
        PrintersSystem system = new PrintersSystem();
        assertEquals(0,system.getPrinterList().size());
    }

    @Test
    public void CheckCreateJobList()
    {
        PrintersSystem system = new PrintersSystem();
        assertEquals(0,system.getServerJobList().size());
    }

    @Test
    public void CheckJobList()
    {
        PrintersSystem system = new PrintersSystem();

        Job job1 = new Job("first");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);

        system.getServerJobList().add(job1);
        system.getServerJobList().add(job2);

        assertEquals(jobs,system.getServerJobList());

    }

    @Test
    public void CheckAddPrint()
    {
        PrintersSystem system = new PrintersSystem();
        Printer newprinter = new Printer(Long.valueOf(100));
        system.getPrinterList().add(newprinter);

        assertEquals(newprinter,system.getPrinterById(newprinter.getId()));

    }

    @Test
    public void CheckAddPrintById()
    {
        PrintersSystem system = new PrintersSystem();
        system.addPrinter(Long.valueOf(100));
        assertEquals(1,system.getPrinterList().size());
    }

    @Test
    public void CheckSystemJobList()
    {
        PrintersSystem system = new PrintersSystem();
        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        ArrayList<Job> jobs = new ArrayList<Job>();
        jobs.add(job1);
        jobs.add(job2);
        system.getServerJobList().add(job1);
        system.getServerJobList().add(job2);
        assertEquals(jobs,  system.getServerJobList());
    }

    @Test
    public void CheckSystemPrintersList()
    {
        PrintersSystem system = new PrintersSystem();

        Printer printer1 = new Printer(Long.valueOf(100));
        Printer printer2 = new Printer(Long.valueOf(200));

        ArrayList<Printer> Printers = new ArrayList<>();
        Printers.add(printer1);
        Printers.add(printer2);
        system.getPrinterList().add(printer1);
        system.getPrinterList().add(printer2);

        assertEquals(Printers,  system.getPrinterList());
    }

    @Test
    public void CheckPrintJob()
    {
        PrintersSystem system = new PrintersSystem();

        Printer printer1 = new Printer(Long.valueOf(100));
        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        Job job3 = new Job("Third job");

        printer1.getJobList().add(job1);
        printer1.getJobList().add(job2);
        printer1.getJobList().add(job3);

        system.getAllJobList().add(job1);
        system.getAllJobList().add(job2);
        system.getAllJobList().add(job3);

        system.getPrinterList().add(printer1);

        int size = printer1.getJobList().size();
        system.PrintByJobId(job3.getId());

        assertEquals(size-1,printer1.getJobList().size());
    }

    @Test
    public void CheckJobStatus()
    {
        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));
        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        Job job3 = new Job("Third job");

        printer1.getJobList().add(job1);
        printer1.getJobList().add(job2);
        printer1.getJobList().add(job3);

        system.getAllJobList().add(job1);
        system.getAllJobList().add(job2);
        system.getAllJobList().add(job3);

        system.getPrinterList().add(printer1);

        system.PrintByJobId(job3.getId());

        assertEquals("done",job3.getStatus());
    }

    @Test
    public void CheckSystemJobSize()
    {
        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));
        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        Job job3 = new Job("Third job");

        printer1.getJobList().add(job1);
        printer1.getJobList().add(job2);
        printer1.getJobList().add(job3);

        system.getAllJobList().add(job1);
        system.getAllJobList().add(job2);
        system.getAllJobList().add(job3);

        system.getPrinterList().add(printer1);
        int size = system.getAllJobList().size();

        system.PrintByJobId(job3.getId());

        assertEquals(size,system.getAllJobList().size());
    }

    @Test
    public void CheckPrinterInSystem()
    {
        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));
        Printer printer2 = new Printer(Long.valueOf(200));
        system.getPrinterList().add(printer1);
        system.getPrinterList().add(printer2);
        Printer print = system.getPrinterById(Long.valueOf(200));
        assertEquals(printer2,print);

    }
    @Test
    public void CheckPrinterIsNotInSystem()
    {
        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));
        Printer printer2 = new Printer(Long.valueOf(200));
        system.getPrinterList().add(printer1);
        Printer print = system.getPrinterById(Long.valueOf(200));
        assertNull(print);

    }

   // @Test
/*    public void CheckPrinterJobListEmpty()
    {
        PrintersSystem system = new PrintersSystem();

        Printer printer1 = new Printer(Long.valueOf(100));

        Job job1 = new Job("first job");
        Job job2 = new Job("second job");

        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);

        system.getPrinterList().add(printer1);

        system.getAllJobList().add(job1);
        system.getAllJobList().add(job2);


        ArrayList<Job> jobsFromSystem =system.getPrinterJobList(Long.valueOf(100));
        assertNull(jobsFromSystem);
    }*/

//    @Test
//    public void CheckPrinterJobList()
//    {
//        PrintersSystem system = new PrintersSystem();
//        Printer printer1 = new Printer(Long.valueOf(100));
//        Job job1 = new Job("first job");
//        Job job2 = new Job("second job");
//        Job job3 = new Job("Third job");
//
//        ArrayList<Job> jobs = new ArrayList<>();
//        jobs.add(job1);
//        jobs.add(job2);
//        jobs.add(job3);
//        printer1.setJobList(jobs);
//        system.getPrinterList().add(printer1);
//
//
//        system.getAllJobList().add(job1);
//        system.getAllJobList().add(job2);
//        system.getAllJobList().add(job3);
//
//        ArrayList<Job> jobsFromSystem = system.getPrinterJobList(Long.valueOf(100));
//        assertEquals(jobs,  jobsFromSystem);
//    }


    }

