package org.pace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class PaceLog {
	
	
	 private String fileName="log";
	 private String filetype="txt";
	 private String logPath="";
	 
	 private String classPath=this.getClass().getClassLoader().getResource("").getPath();
	 
	 private String dir =classPath.replace("WEB-INF/classes/", "");
	 
	 private String folderName="PaceLog/";
	 
	  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	  DateFormat dfLog = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	  Date currentDate= new Date();
	  
	  String curDate=df.format(currentDate);
      String dateFormated=dfLog.format(currentDate);
	  
	 public PaceLog(){  this.logPath=dir+folderName+fileName+"-"+curDate+"."+filetype;  }
		 
     public  PaceLog(String folder,String fileName,String filetype) {
    	
    	this.folderName=folder;
    	this.fileName=fileName;
    	this.filetype=filetype;
    	this.logPath=dir+folderName+fileName+"-"+curDate+"."+filetype;
    	
     }
    
     public void writeString(String logdata) {
    	
      try {
	      if(filetype=="txt") {
	
	    	FileWriter fw = new FileWriter(logPath, true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        PrintWriter logWrite = new PrintWriter(bw) ;
	
	        logWrite.println("\n=================================="+dateFormated+"=======================================\n");
		
	        logWrite.println(logdata);
	        logWrite.flush();
	        logWrite.close();
	
	      }else { System.out.println("ERROR:file format should be txt.\n LOGDATA:"+logdata); }
      
      }catch(IOException ioEx) { System.out.println("ERROR:"+ioEx+".\n LOGDATA:"+logdata);  } 
	
    }
      
     public void writeObject(JSONObject logdata)  {
     	
    	 try {
    		 
	      if(filetype=="json") {
	    	  
	    	FileWriter fw = new FileWriter(logPath, true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        PrintWriter logWrite = new PrintWriter(bw) ;
	        
	        logdata.put("datetime", dateFormated);
	        
	        File file = new File(logPath);
	        
	        boolean empty =  file.length() == 0;
	
	        if(empty) {  logWrite.print("["); }
	        if(!empty) {  
	        	
	        	Process p;
	        	
	        	try {
	        	
	        	p=	Runtime.getRuntime().exec("truncate -s-1 " + logPath);
	            p.waitFor();
	            
	        	} catch (InterruptedException e) { e.printStackTrace();
			 	}
	        	
	        	logWrite.print(","); 
	        }
	        	
	        logWrite.print(logdata);
	        logWrite.print("]");
	        logWrite.flush();
	        logWrite.close();
	        
	      }else { System.out.println("ERROR:file format should be json.\n LOGDATA:"+logdata); }
	      

    	 }catch(IOException ioEx) { System.out.println("ERROR:"+ioEx+".\n LOGDATA:"+logdata);  } 
    	  catch(JSONException jEx){ System.out.println("ERROR:"+jEx+".\n LOGDATA:"+logdata); }
     
     }
}
