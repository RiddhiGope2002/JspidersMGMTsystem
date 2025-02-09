package com.jdbc_with_postgre;



public class TeacherJSP {
	   private String t_name;
	    private int t_id;
	    
	    TeacherJSP(String t_name,int t_id){
	    	this.t_name=t_name;
	    	this.t_id=t_id;
	    }

	    public String getT_name() {
	        return t_name;
	    }

	    public void recieveSal(int amount,TeacherJSP t){
	        System.out.println(t.getT_name()+" got the salary");
	        System.out.println("Right now Total balance is: ");
}
}
