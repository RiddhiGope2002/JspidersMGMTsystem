package com.jdbc_with_postgre;



public class StudentJSP {
	 private  String s_name;
	    private  int s_id;
	    private int till_now_paid;

	    StudentJSP(String s_name,int s_id){
	        this.s_name=s_name;
	        this.s_id=s_id;
	    }

	    public  void payment(int amount){
	        Jspiders.Jspiders_full_amount+=amount;
	        till_now_paid+=amount;
	        Jspiders.ami+=amount;
	    }

		public  String getS_name() {
			return s_name;
		}

		
		public  int getS_id() {
			return s_id;
		}
		
		public  int getS_till_now_paid() {
			return till_now_paid;
		}

}
