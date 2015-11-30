package snippet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Snippet {
	private static Integer TOKEN_COLUMN = 3000;
	
	public static void main(String[] args) {
//	    ArrayList<String> studentTokens = new ArrayList<String>();
	    Set<String> studentTokens = new HashSet<String>(10000);
	    try {
	        // Open the file that is the first
	        // command line parameter
	        FileInputStream fstream = new FileInputStream("C:\\Users\\pankaj\\new1.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;
	        // Read File Line By Line
	        while ((strLine = br.readLine()) != null) {
	            strLine = strLine.trim();
	            strLine = strLine.replace("2015-08-21 10:58:03,", "");
	            strLine = strLine.replace("2015-08-21 10:58:04,", "");
	            strLine = strLine.replace("0", "");
	            strLine = strLine.replace("1", "");
	            strLine = strLine.replace("2", "");
	            strLine = strLine.replace("3", "");
	            strLine = strLine.replace("4", "");
	            strLine = strLine.replace("5", "");
	            strLine = strLine.replace("6", "");
	            strLine = strLine.replace("7", "");
	            strLine = strLine.replace("8", "");
	            strLine = strLine.replace("9", "");
	            strLine = strLine.replace("[", "");
	            strLine = strLine.replace("]", "");
	
	            if ((strLine.length()!=0) && (strLine.charAt(0)!='[')) {
	                String[] students = strLine.split("\\s+");
	                if(students.length>9){
	                	//studentTokens.add(students[0]+" "+students[1]+" "+students[2]+" "+students[3]+" "+students[4]+" "+students[5]+" "+students[6]+" "+students[7]+" "+students[8]);
	                } 
	                if(students.length==9){
	                	studentTokens.add(students[0]+" "+students[1]+" "+students[2]+" "+students[3]+" "+students[4]+" "+students[5]+" "+students[6]+" "+students[7]+" "+students[8]);
	                } 
	                else if(students.length==8){
	                	studentTokens.add(students[0]+" "+students[1]+" "+students[2]+" "+students[3]+" "+students[4]+" "+students[5]+" "+students[6]+" "+students[7]);
	                } 
	                else if(students.length==7){
	                	studentTokens.add(students[0]+" "+students[1]+" "+students[2]+" "+students[3]+" "+students[4]+" "+students[5]+" "+students[6]);
	                } 
	                else if(students.length==6){
	                	studentTokens.add(students[0]+" "+students[1]+" "+students[2]+" "+students[3]+" "+students[4]+" "+students[5]);
	                } 
	                else if(students.length==5){
	                	studentTokens.add(students[0]+" "+students[1]+" "+students[2]+" "+students[3]+" "+students[4]);
	                }
	                else if(students.length==4){
	                	studentTokens.add(students[0]+" "+students[1]+" "+students[2]+" "+students[3]);
	                }
	                else if(students.length==3){
	                	studentTokens.add(students[0]+" "+students[1]+" "+students[2]);
	                }
	                else if(students.length==2){
	                	studentTokens.add(students[0]+" "+students[1]);
	                }else if(students.length==1){
	                	studentTokens.add(students[0]);
	                }
	                
	            }
	
	
	        }
	
	        for (String s : studentTokens) {
	            System.out.println(s);
	        }
	
	        // Close the input stream
	        fstream.close();
	    } catch (Exception e) {// Catch exception if any
	    	e.printStackTrace();
	        System.err.println("Error: " + e.getMessage());
	    }
	}
}

