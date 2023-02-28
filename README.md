# Almog_Yuval_Printer_manager_system
Yuval &amp; Almog Printer Manager System , Final Project for Software Enginerring 

## Project title : PrinterApplication
### this project is covering the rest API service 
## Prject title : virtualPrinterV_1
### this project is coverint the client that can work with the service 

## build Status
 no problem so far 
## Code style
java language , 
also names in Camel Casing 

## Installation
the proper way the install the project is :

1 : click on the green button with the name "code" <br>
2 : download all files into your own computer address <br>
3 : extart the files into a folder 
4 : open intellij <br>
5 : click  -> open -> go to where you extracted ur filed and click on the folder name printerApplication <br>
6 : then click on build -> build moudle <br>
7 : from there you should enter the printerApplication in : printerApplication -> src -> main -> java ->PrinterApplication <br>
8 : now you shold be able to run the server : ![image](https://user-images.githubusercontent.com/120096334/221839310-547533b2-5d49-4d10-a02a-44c06585d274.png) <br>
9 : then click, File -> open -> virtualPrinterV_1 
6 : paste the URL to where is says 'URL' , there u can choose where to save the files of the project  <br>


## Screenshots
after inital build , if done correctly should be able to run the apllications like so :![image](https://user-images.githubusercontent.com/120096334/221821299-1767ddaf-4703-46c4-9208-56e68d0a94ba.png)
we suggest first to open the server and then the client but both options are vaild.
if initSomeData is not removed from the code you will have some printer inside the server already that we defined .
![image](https://user-images.githubusercontent.com/120096334/221821952-14032502-f017-416a-afae-f7aa6a105860.png)
there are 5 printers in total and 2 jobs inside printer number 1 and number 5 
after running the virtual printer you should get this inside the console :
![image](https://user-images.githubusercontent.com/120096334/221822298-58496530-9f39-42fd-8190-aa017036192e.png)

option 1 is to enter printer id and that printer will print a job (if the printer got a job to print)
option 2 is to send a random request to print , the server will try to look for a printer with a job and print (the printer id is randomised from the printer list and will try 10 times before giving up ) 
option 3 is to stop the virtual printer from running .
example of using the virtual printer with the server : ![image](https://user-images.githubusercontent.com/120096334/221823290-e46d1363-3482-42ad-a1da-963984f08888.png)

## Tech/Framework used
Rest API ,
rabbitMQ,



