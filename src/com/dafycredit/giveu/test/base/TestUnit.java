package com.dafycredit.giveu.test.base;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;

import com.dafycredit.giveu.test.util.TestXmlHelper;

public class TestUnit extends TestBase{
	
	private LinkedHashMap<String,TestCase> caseMap;

    public LinkedHashMap<String,TestCase> getCaseMap() {
        return caseMap;
    }

    public void setCaseMap(LinkedHashMap<String,TestCase> caseMap) {
        this.caseMap = caseMap;
    }

    public TestCase getCase(int index){
        int n = 0;
        if(caseMap == null)
            return null;
        int size = caseMap.size();
        if(index < 0 || (index > 0 && index >= size))
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        for(Map.Entry<String,TestCase> e : caseMap.entrySet()){
            if(n++ == index)
                return e.getValue();
        }
        return null;
    }

    public TestCase getCase(String name){
        return caseMap == null ? null : caseMap.get(name);
    }
    
    public void runCase(String name) throws Exception{
    	
    	TestCase testCase = getCase(name);
    	List<TestStep> steps = testCase.getSteps();
    	for(TestStep step : steps){
    		if(step.isCancel())
    			continue;
    		StepAction action = step.getAction();
    		Class<?> clazz = action.handler();
    		if(clazz != null){
    			String key = step.getAction().key();
    			Method m = clazz.getDeclaredMethod(getMethodName(key), new Class<?>[]{TestStep.class});
    			m.invoke(clazz.newInstance(), step);
    		}else{
    			action.run(step);
    		}
    	}
    }
    
    private String getMethodName(String actionKey){
    	if(actionKey == null || "".equals(actionKey))
    		throw new RuntimeException("empty action key");
    	char[] arr = actionKey.toCharArray();
    	char prevChar = '\0';
    	StringBuilder sb = new StringBuilder();
    	char splitChar = '-';
    	for(char c : arr){
    		if(c == splitChar){
    			prevChar = c;
    			continue;
    		}
    		if(prevChar == splitChar) {
				sb.append(Character.toUpperCase(c));
			} else 
    			sb.append(c);
    		prevChar = c;
    	}
    	return sb.toString();
    }
    
    public void close(){
    	TestXmlHelper.close();
    }
    
    //标记备注，一般展示在测试报告中
    public void TestReportRemarks(String RemarksName){
 	   
 	   Reporter.log(RemarksName);
    }
    
    public static void main(String[] args) {
    	
	}

}
