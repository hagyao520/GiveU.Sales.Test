package com.dafycredit.giveu.test.handler;

import com.dafycredit.giveu.test.base.TestStep;
import com.dafycredit.giveu.test.util.DBHelper;

public class DBActionHandler {
	
	public void dbInsert(TestStep step){
		int n = DBHelper.insert(step.getValue());
		if(n > 0){
			System.out.println(step.getTip());
		}
	}
	
	public void dbDelete(TestStep step){
		int n = DBHelper.delete(step.getValue());
		if(n > 0){
			System.out.println(step.getTip());
		}
	}
	
	public void dbUpdate(TestStep step){
		int n = DBHelper.update(step.getValue());
		if(n > 0){
			System.out.println(step.getTip());
		}
	}
	
	public void dbQuery(TestStep step){
        DBHelper.query(step.getValue());
		System.out.println(step.getTip());
	}
	
}
