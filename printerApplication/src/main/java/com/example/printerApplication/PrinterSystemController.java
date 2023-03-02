package com.example.printerApplication;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rabbitmq.client.*;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class PrinterSystemController implements Consumer {

    private final PrintersSystem server ;



    PrinterSystemController () throws IOException, TimeoutException {
        this.server = new PrintersSystem();
        initSomeData();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = "testing";
        channel.queueDeclare(queueName, false, false, false, null);
        String printerID="";
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    int num = Integer.parseInt(message);
                    WorkWithBody(message);
                } catch (NumberFormatException e) {
                    if (!message.equals("random"))
                        System.out.println("invalid message not printer Id nor 'random' that represent random printer id");
                }
                WorkWithBody(message);
                //System.out.println("Received message: " + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);



        try {
            int messageCount = channel.queueDeclarePassive(queueName).getMessageCount();
            while (messageCount > 0) {
                GetResponse response = channel.basicGet(queueName, true);
                if (response != null){
                    byte[] body = response.getBody();

                     printerID = new String(body);
                    try {
                        int num = Integer.parseInt(printerID);
                        WorkWithBody(printerID);
                    } catch (NumberFormatException e) {
                        if (!printerID.equals("random"))
                            System.out.println("invalid message not printer Id nor 'random' that represent random printer id");
                    }
                    WorkWithBody(printerID);
                    messageCount = channel.queueDeclarePassive(queueName).getMessageCount();
                }
            }
        } catch ( Exception e ) {
            System.out.println(e);
        }

    }
    private  void  initSomeData() {
        // gives to the server some basic printers to show and work with
        Printer test = new Printer(Long.valueOf(1));
        test.setServer("main");
        Job jobTest = new Job("first job");
        this.server.getServerJobList().add(jobTest);
        test.getJobList().add(jobTest);
        this.server.addPrinter(test);
        Printer printer2 = new Printer(Long.valueOf(2));
        printer2.setServer("main");
        Printer printer3 = new Printer(Long.valueOf(3));
        printer3.setServer("secondary");
        Printer printer4 = new Printer(Long.valueOf(4));
        printer4.setServer("secondary");
        this.server.addPrinter(printer2);
        this.server.addPrinter(printer3);
        this.server.addPrinter(printer4);
        Printer test2 = new Printer(Long.valueOf(5));
        test2.setServer("removeServer");
        Job job2 = new Job("second job");
        this.server.getServerJobList().add(job2);
        test2.getJobList().add(job2);
        this.server.addPrinter(test2);

    }



    @GetMapping("/jobs")
    ArrayList<Job>  getAllJobs () {
        return this.server.getAllJobList();
    }


    @PutMapping("/printers")
    Printer addPrinter(@RequestBody longObject printerId) {
        Printer printer = new Printer(printerId.getValue());
        this.getPrinterList().add(printer);
        return  printer;
     }
    @GetMapping("printer/{id}")
        Printer getPrinterById (@PathVariable Long id) {
         return this.server.getPrinterById(id);
       }

    @GetMapping("/printers")
    ArrayList<Printer> getPrinterList(){
            return server.getPrinterList();
    }

    @GetMapping("printers/{id}/full")
  PrinterWithJobs jobOfThePrinter (@PathVariable Long id){
        Printer printer = this.server.getPrinterById(id);
        ArrayList<Job> jobList = new ArrayList<Job>();
        for (Job jobsNotDone : printer.getJobList()){
            jobList.add(jobsNotDone);
        }

        for (Job jobsDone: this.server.getServerJobList()) {
            if (jobsDone.getPrintedByPrinterId()==id)
                jobList.add(jobsDone);
        }
        PrinterWithJobs test = new PrinterWithJobs(printer , jobList);

        return test;

    }


    @DeleteMapping("/printers/{id}")
    void DeletePrinter (@PathVariable Long id) {
        this.server.getPrinterById(id).setLiveness(false);
    }

    @PostMapping("printers/{id}/liveness")
    void PostPrinter (@PathVariable Long id ) {
        this.server.getPrinterById(id).setLiveness(true);
    }
    @PutMapping("printers/{id}/printjobs")
    Long addPrintJob (@PathVariable Long id , @RequestBody String data ) {
        Printer printer = this.server.getPrinterById(id);
        Job newJob = new Job(data);
        printer.getJobList().add(newJob);
        this.server.getServerJobList().add(newJob);
        return  newJob.getId();
    }
    @GetMapping (value = "/printers/{id}/printjobs")
    ArrayList<Job> getAllJobsStatusWise (@PathVariable Long id ,@RequestParam(value = "status",required = false ,defaultValue = "all") String status) {
            return this.server.getAllJobWithStatus(id, status);
    }
    @PostMapping ("/printjobs/{jobid}")
    void updateJobStatus (@PathVariable Long jobid ,@RequestBody String status) {
        for (Job job : this.server.getServerJobList()) {
            if (job.getId() == jobid.longValue())
                job.setStatus(status);
        }
    }

    @GetMapping ("/printjobs/{jobid}")
    Job jobDetails (@PathVariable Long jobid){
        for (Job job : this.server.getServerJobList()) {
            if (job.getId() == jobid.longValue())
                return job;
        }
        return null;
    }

    @GetMapping("/printjobs")
    ArrayList<Job> jobFilters (@RequestParam(value = "status" ,defaultValue = "all") String status , @RequestParam(value = "since", required = false, defaultValue = "2000-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

            return this.server.getAllJob(status, date);
    }

    @GetMapping ("/statistics")
    Statistics getStatistics (){
        return this.server.getSystemStats();
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

    }


    @Override
    public void handleConsumeOk(String s) {

    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    public  void WorkWithBody (String message) {
        if (message.equals("random")){
            int tries = 0;
            int min = 0 ;

            int max = (int)this.server.getPrinterList().size();
            int range = (max - min) ;
            int printerIndex =  (int)(Math.random() * range) + min;
            while (this.server.getPrinterList().get(printerIndex).getJobList().size()==0 && tries < 10){
                printerIndex =  (int)(Math.random() * range) + min;
                tries++;
            }
            if (this.server.getPrinterList().get(printerIndex).getJobList().size()>0 )
                this.server.getPrinterList().get(printerIndex).printJob();
        }
        else{
            long printerId = Long.parseLong(message);
            for (Printer printer : this.server.getPrinterList()){
                if (printer.getId().equals(printerId))
                    printer.printJob();
            }
        }
    }





}
