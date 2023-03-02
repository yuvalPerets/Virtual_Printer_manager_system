package com.example.printerApplication;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

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
        ArrayList<Job> jobs = new ArrayList<>();
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
        ArrayList<Job> jobs = new ArrayList<>();
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

    @Test
   public void CheckPrinterJobListEmpty()
    {
        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));

        Job job1 = new Job("first job");
        Job job2 = new Job("second job");

        system.getPrinterList().add(printer1);

        system.getAllJobList().add(job1);
        system.getAllJobList().add(job2);

        ArrayList<Job> jobsFromSystem =system.getPrinterJobList(Long.valueOf(100));

        assertEquals(0,jobsFromSystem.size());
    }

    @Test
    public void CheckPrinterJobList()
    {
        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));
        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        Job job3 = new Job("Third job");

        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        printer1.setJobList(jobs);
        system.getPrinterList().add(printer1);


        system.getAllJobList().add(job1);
        system.getAllJobList().add(job2);
      //  system.getAllJobList().add(job3);


        system.PrintByJobId(job3.getId());
        ArrayList<Job> jobsFromSystem = system.getPrinterJobList(Long.valueOf(100));
        assertEquals(jobs,  jobsFromSystem);
    }


        @Test
        public void CheckIfPrinterDelete()
        {
            PrintersSystem system = new PrintersSystem();

            Printer printer1 = new Printer(Long.valueOf(100));
            system.getPrinterList().add(printer1);
            system.deletePrinter(printer1.getId());

            assertFalse( printer1.getLiveness());

        }

        @Test
        public void SetAliveDeletePrinter()
        {
            PrintersSystem system = new PrintersSystem();

            Printer printer1 = new Printer(Long.valueOf(100));
            system.getPrinterList().add(printer1);
            system.deletePrinter(printer1.getId());

            system.setAlivePrinter(printer1.getId());
            assertTrue( printer1.getLiveness());

        }

        //Tests for CreateNewJob function

        @Test
        public void CheckAssertNewJobToPrinter(){
            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            int size = printer1.getJobList().size();
            system.getPrinterList().add(printer1);
            Job job1 =new Job("new job");

            system.createNewJob(printer1.getId(), job1);
            assertEquals(size+1, printer1.getJobList().size());
        }

        @Test
        public void CheckAssertNewJobToSystem(){
            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            int size = system.getAllJobList().size();
            system.getPrinterList().add(printer1);
            Job job1 =new Job("new job");

            system.createNewJob(printer1.getId(), job1);
            assertEquals(size+1, system.getAllJobList().size());
        }

        @Test
        public void CheckJobIdReturn(){
            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));

            system.getPrinterList().add(printer1);
            Job job1 =new Job("new job");

            Long id = system.createNewJob(printer1.getId(), job1);
            assertEquals(id, job1.getId());
        }


        //Tests for getAllJobWithStatus function
        @Test
        public void CheckJobsWithStatusPadding()
        {
            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            ArrayList<Job> jobs = new ArrayList<>();
            Job job1 =new Job("new job");

            printer1.getJobList().add(job1);
            system.getPrinterList().add(printer1);
            jobs.add(job1);

            ArrayList<Job>  check = system.getAllJobWithStatus(printer1.getId(),"padding");
            assertEquals(check,jobs);
        }


        @Test
        public void CheckJobsWithStatusDone()
        {
            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            ArrayList<Job> jobs = new ArrayList<>();
            Job job1 =new Job("new job");

            system.getServerJobList().add(job1);

            printer1.getJobList().add(job1);
            system.getPrinterList().add(printer1);
            printer1.printJob();
            jobs.add(job1);

            ArrayList<Job>  check = system.getAllJobWithStatus(printer1.getId(),"done");
            assertEquals(check,jobs);
        }

        //Test for updateJob function
        @Test
        public void CheckUpdateJob()
        {
            PrintersSystem system = new PrintersSystem();
            Job job1 =new Job("new job");

            system.getAllJobList().add(job1);
            String status = "check";
            system.updateJob(job1.getId(), status);
            assertEquals(status,job1.getStatus());
        }

        //Test for getJobDetails function
        @Test
        public void getJobDetailsCheck()
        {
            PrintersSystem system = new PrintersSystem();
            Job job1 =new Job("new job");
            system.getAllJobList().add(job1);

            Job job = system.getJobDetails(job1.getId());
            assertEquals(job, job1);
        }

         @Test
         public void getJobDetailsCheck1()
        {
            PrintersSystem system = new PrintersSystem();
            Job job1 =new Job("new job");

            Job job = system.getJobDetails(job1.getId());
            assertNull(job);
        }

    //Test for getAllJob function

        @Test
        public void CheckGetJobsNoStatusNoDate()
        {
            Date startDate = new Date();
            //pause the program for 1 sec
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();}

            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            ArrayList<Job> jobs = new ArrayList<>();

            Job job1 = new Job("first job");
            Job job2 = new Job("second job");
            Job job3 = new Job("third job");

            jobs.add(job1);
            jobs.add(job2);
            jobs.add(job3);


           //system.getPrinterList().add(printer1);

            system.getAllJobList().add(job1);
            system.getAllJobList().add(job2);
            system.getAllJobList().add(job3);

            printer1.getJobList().add(job3);
            printer1.printJob();

            ArrayList<Job> check = system.getAllJob("all",startDate);

            assertEquals(check, jobs);

        }

        @Test
        public void CheckGetJobsByStatusPadding()
        {
            Date startDate = new Date();
            //pause the program for 1 sec
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();}

            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            ArrayList<Job> jobs = new ArrayList<>();

            Job job1 = new Job("first job");
            Job job2 = new Job("second job");
            Job job3 = new Job("third job");

            jobs.add(job1);
            jobs.add(job2);

            system.getAllJobList().add(job1);
            system.getAllJobList().add(job2);
            system.getAllJobList().add(job3);

            printer1.getJobList().add(job3);
            printer1.printJob();

            ArrayList<Job> check = system.getAllJob("padding",startDate);

            assertEquals(check, jobs);

        }

    @Test
    public void CheckGetJobsByStatusDone()
    {
        Date startDate = new Date();
        //pause the program for 1 sec
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();}

        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));
        ArrayList<Job> jobs = new ArrayList<>();

        Job job1 = new Job("first job");
        Job job2 = new Job("second job");
        Job job3 = new Job("third job");

        jobs.add(job3);

        system.getAllJobList().add(job1);
        system.getAllJobList().add(job2);
        system.getAllJobList().add(job3);

        printer1.getJobList().add(job3);
        printer1.printJob();

        ArrayList<Job> check = system.getAllJob("done",startDate);

        assertEquals(check, jobs);

    }

        @Test
        public void CheckGetJobsByStatusAndDate()
        {


            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            ArrayList<Job> jobs = new ArrayList<>();

            Date startDate = new Date();
            Job job1 = new Job("first job");
            //pause the program for 1 sec
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();}


            Job job2 = new Job("second job");
            Job job3 = new Job("third job");

            jobs.add(job2);

            system.getAllJobList().add(job1);
            system.getAllJobList().add(job2);
            system.getAllJobList().add(job3);

            printer1.getJobList().add(job3);
            printer1.printJob();

            ArrayList<Job> check = system.getAllJob("padding",startDate);

            assertEquals(check, jobs);

        }

        //Test for Statistics

        //Test number of alive printers
        //Test number of delete printers

        @Test
        public void CheckPrintersStatusAlive(){

            PrintersSystem system = new PrintersSystem();
            Printer printer1 = new Printer(Long.valueOf(100));
            Printer printer2 = new Printer(Long.valueOf(200));

            system.getPrinterList().add(printer1);
            Statistics newStatistic = new Statistics(Long.valueOf(1),Long.valueOf(0),null,null);

            assertEquals(newStatistic.getPrinterConnectedAmount(),system.getSystemStats().getPrinterConnectedAmount());
        }

    @Test
    public void CheckPrintersDisConnected(){

        PrintersSystem system = new PrintersSystem();
        Printer printer1 = new Printer(Long.valueOf(100));
        Printer printer2 = new Printer(Long.valueOf(200));

        system.getPrinterList().add(printer1);
        system.deletePrinter(printer1.getId());
        Statistics newStatistic = new Statistics(Long.valueOf(0),Long.valueOf(1),null,null);
        assertEquals(newStatistic.getPrinterDisConnectedAmount(),system.getSystemStats().getPrinterDisConnectedAmount());
    }


    }

