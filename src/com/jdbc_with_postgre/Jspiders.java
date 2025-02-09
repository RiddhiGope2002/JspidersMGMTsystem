package com.jdbc_with_postgre;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Jspiders {

	  public static int Jspiders_full_amount=0;

	  //This variable is for updating the total_amount in the fund table
	  public static int ami;


	    public static void main(String[] args) throws Exception  {
	    	
	    	
	    	//JDBC Connection steps: 
	    	
	    	//Load and register the driver:
	    	Class.forName("org.postgresql.Driver");
	    	
	    	//Establish the connection: 
	    	Connection c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/hi","postgres","#Riddhi25");
			
			
			
			
			// Terminal output for taking the user-input
	        System.out.println("Welcome to Jspiders--------------------------");
	        System.out.println("How many students do you want to enter?");

	        
	        
	        Scanner sc=new Scanner(System.in);
	        Random r=new Random();
	        int no_of_students=sc.nextInt();	//to enter no of students to be entered
	        
	        //creating 2 arraylist for list of students and teachers: 
	        ArrayList<StudentJSP> a=new ArrayList<>();
	        ArrayList<TeacherJSP> t=new ArrayList<>();
	        
	        
	        // Teacher objects and entering into the arraylist
	        TeacherJSP sridhar_sir=new TeacherJSP("Sridhar R",1);
	        TeacherJSP shankar_sir=new TeacherJSP("Shankar Narayan",2);
	        TeacherJSP shashank_sir=new TeacherJSP("Shashank",5);
	        TeacherJSP kiran_sir=new TeacherJSP("Kiran R",10);
	        t.add(sridhar_sir);
	        t.add(shankar_sir);
	        t.add(kiran_sir);
	        t.add(kiran_sir);
	      


	        
	        //Entering student details and storing in the table student of database hi
	        for(int i=0;i<no_of_students;i++){
	            System.out.println("Enter student no "+(i+1)+"'s name");
	            String stu_name=sc.next();		//entering student's name:
	            int stu_id=r.nextInt(899)+100;	// system generated student id:


	            System.out.println(stu_name+"'s id will be "+stu_id);

	            //creating StudentJSP object with parameterised constructor: 
	            StudentJSP s=new StudentJSP(stu_name, stu_id);
	            
	            //adding the object into the student arraylist:
	            a.add(s);

	            //entering the student's payment: 
	            System.out.println("how much you want to pay now(max-30k)?");
	            s.payment(sc.nextInt());
	            
	            
	            //entering the student details in the STUDENT table in the HI database: 
	            CallableStatement p=c.prepareCall("insert into student values(?,?,?)");
	            p.setInt(1, stu_id);
	            p.setString(2, stu_name);
	            p.setInt(3, s.getS_till_now_paid());
	            int result_of_jdbc=p.executeUpdate();
	            System.out.println(result_of_jdbc+"------------------------------> that means row is entered");

System.out.println();
	        }
	        
	      
	        
	        
	       
	        
	        
	        
 
	      //from the fund table getting the available fund(privious) and everytime student is paying, ami variable will be updated(i.e: ami+=privious;) and fund will be up to date:  
			  CallableStatement p_from_fund_table=c.prepareCall("select total_amount from fund");
			  ResultSet e_from_fund_table=p_from_fund_table.executeQuery();
			  
			  // privious is to get the available balance of fund table:
			  int privious=0;
			  
			  while(e_from_fund_table.next()) {
				  privious=e_from_fund_table.getInt(1);
			  }
			  ami+=privious;
	        
	        
			  //from the above ami variable is updted and up to date fund will be set in the fund table
	        CallableStatement p2=c.prepareCall("update fund set total_amount=?");	    
	        p2.setInt(1, ami);
	        int e_ami=p2.executeUpdate();
	     
	        
	        
	        // It will be asking whether salary has to be given to sirs:
	        System.out.println();
	        System.out.println("Salaries of sirs: 10k/sir");	        
	        System.out.println("Do you want to give salaries to all sirs,today? (y or n->lowercase)");
	        String salary_giving_or_not=sc.next();
	        
	        
	        
	        //this variable is for getting the up to date fund
	        int ami4=0;
	        if(salary_giving_or_not.equals("y")) {
	        	


	        	//Here we have to update the fund after giving salaries:
	        	CallableStatement p3=c.prepareCall("select total_amount from fund");
	        	ResultSet ami3=p3.executeQuery();
	        	
	        	while(ami3.next()) {
	        		ami4=ami3.getInt(1);	//getting the latest value of fund:
	        	}
	        	
	        	int updated_fund=ami4-t.size()*10000;	//giving the salaries to teachers:
	        	p2=c.prepareCall("update fund set total_amount=?");
	    	    
		        p2.setInt(1, updated_fund);
		        int atlast=p2.executeUpdate();
	        	System.out.println("After giving salaries to sirs,profit of Jspiders: "+updated_fund);
		   
		   
	        }
	        else {
	        	
	        	
	        	System.out.println("so the total income is: "+ami4);
	        	
	        	
	        }
	        
	        
	        
	        
	        
	        // This portion is only for printing today's record: 
	        System.out.println();
	        System.out.println("Today's record: ----------");
	        
	        for(int i=0;i<a.size();i++) {
	        	System.out.println(( a.get(i)).getS_name()+" has joined as id: "+a.get(i).getS_id()+", payment: "+a.get(i).getS_till_now_paid());
	        }
	        System.out.println("Today's income: "+Jspiders_full_amount);
	        
	        
	        
	        c.close();
	    
}


}
