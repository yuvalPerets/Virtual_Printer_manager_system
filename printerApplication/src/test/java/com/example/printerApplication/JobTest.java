package com.example.printerApplication;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JobTest {
     @Test
    void CheckJobStatusPadding(){
         Job job=new Job();
         assertEquals("padding", job.getStatus());
     }

   @Test
     public void changeJobStatus(){
        Job job=new Job();
        String status = "new status";
        job.setStatus(status);
        assertEquals(status, job.getStatus());
    }

    @Test
    public void CheckJobCreateDate()
    {
        Job job = new Job();
        //pause the program for 1 sec
        try{
        Thread.sleep(1000);
        } catch (InterruptedException e) {
        e.printStackTrace();}

        Date now = new Date();

        boolean validDate = job.getCreateDate().before(now);
        assertEquals(validDate, true);
    }

    @Test
    public void CheckJobPrinterId()
    {
        Job job = new Job();
        assertEquals(null,job.getPrintedByPrinterId());
    }

    @Test
    public void CheckJobData(){
         Job job = new Job("Data to check");
        assertEquals("Data to check",job.getData());
    }

    @Test
    public void CheckJobDataNull(){
        Job job = new Job();
        assertEquals(null,job.getData());
    }

    @Test
    public void CheckJobPrintDate(){
        Job job = new Job();
        assertEquals(null,job.getPrintDate());
    }
}
