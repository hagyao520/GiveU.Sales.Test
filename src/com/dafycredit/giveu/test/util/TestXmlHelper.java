package com.dafycredit.giveu.test.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dafycredit.giveu.test.base.StepAction;
import com.dafycredit.giveu.test.base.TestCase;
import com.dafycredit.giveu.test.base.TestStep;
import com.dafycredit.giveu.test.base.TestUnit;

public class TestXmlHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestXmlHelper.class);
	
	private static WebDriver driver;
	
	public static void main(String[] args) {
		TestUnit testUnit = TestXmlHelper.parse("TestUnit.xml");
        
        Map<String, TestCase> caseMap = testUnit.getCaseMap();
        for(Map.Entry<String,TestCase> e : caseMap.entrySet()){
        	TestCase testCase = e.getValue();
        	LOG.info("name=" + testCase.getName() + ", cancel=" + testCase.isCancel());
        	if(testCase.isCancel()){
        		continue;
        	}
        	List<TestStep> steps = testCase.getSteps();
        	LOG.info("  " + steps.size() + " steps");
        	
        	for(TestStep step : steps){
        		if(step.isCancel())
        			continue;
        		LOG.info("  " + step.getAction() + " driver=" + step.getDriver());
        		StepAction action = step.getAction();
        		if(action.handler() != null){
        			System.out.println("-----------------------" + action.handler());
        		}else{
        			action.run(step);
        		}
        	}
        }
	}
	
	public static void initWebDriver(){
		System.setProperty("webdriver.firefox.bin", ConfigUtil.getProperty("webdriver.firefox.bin", Constants.CONFIG_COMMON)); 
		driver = new FirefoxDriver();
		driver .manage().window().maximize();//全屏
	
	}
	
	public static void AppointFirefoxDriver(){

		File file = new File(ConfigUtil.getProperty("webdriver.profile", Constants.CONFIG_COMMON));
 	    FirefoxProfile profile = new FirefoxProfile(file);        
 	    driver = new FirefoxDriver(profile);
 	    driver .manage().window().maximize();//全屏
	}
	
	public static void close(){
		driver.quit();
	}
	
	//根据用例的名称，截取图片，进行保存
    public static void ScreenShot(String CaseName){
 	   
 	   int t =1;
// 	   String CurrentDir = System.getProperty("user.dir");
// 	   String AppointDir ="BugScreenshot\\";
 	   String AppointDir = ConfigUtil.getProperty("image.dir", Constants.CONFIG_COMMON);
 	   String picture =AppointDir+CaseName+".png";  
//        String picture =AppointDir+CaseName+"_"+getDateTime()+"_"+t+".png";  
        
        File file = new File(AppointDir);
        File[] fs = file.listFiles();
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
        //指定图片数量，过多删除
        try {
          if(fs.length >= 300){
         	 
            for(File f: fs){
              if(f.getName().contains("png"))
                     f.delete();
            }
          }
          
          FileUtils.copyFile(screenShot, new File(picture));
          ++t;  
          
        } catch (IOException e) {
     	     System.out.println("截图失败:"+CaseName);
             e.printStackTrace();
        }
        finally {
             System.out.println("截图成功:"+CaseName+" "+picture);
        }
    }
    
	public static TestUnit parse(String xmlPath){
		AppointFirefoxDriver();
		return parse(new File(xmlPath));
	}
	
	public static TestUnit parse(File xmlFile){

	    TestUnit testUnit = null;

	    if(xmlFile == null || !xmlFile.exists())
	        return testUnit;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			
			Element root = doc.getDocumentElement();
			NodeList cases = root.getElementsByTagName("case");
            LinkedHashMap<String,TestCase> caseMap = new LinkedHashMap<String,TestCase>();
			Element child;
			TestCase testCase;
			for(int i=0; i<cases.getLength();i++){
				child = (Element) cases.item(i);
				testCase = parseTestCase(child);
				if(testCase == null)
				    continue;
				if(caseMap.containsKey(testCase.getName()))
				    throw new RuntimeException("存在多个"+testCase.getName()+"，请检查name配置");
				else
				    caseMap.put(testCase.getName(), testCase);
			}
            testUnit = new TestUnit();
            testUnit.setCaseMap(caseMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return testUnit;
	}

	private static TestCase parseTestCase(Element element){
	    if(element == null)
	        return null;
        NamedNodeMap attrs = element.getAttributes();
        TestCase testCase = initByAttributes(attrs, new TestCase());
        NodeList stepNodes = element.getElementsByTagName("step");
        int len = stepNodes.getLength();
        Element stepNode;
        List<TestStep> stepList = new ArrayList<TestStep>(len);
        for(int i=0; i<len; i++){
            stepNode = (Element) stepNodes.item(i);
            stepList.add(parseTestStep(stepNode));
        }
        testCase.setSteps(stepList);
        return testCase;
    }
	
	private static TestStep parseTestStep(Element element) {
		if(element == null)
		    return null;
		TestStep testStep = initByAttributes(element.getAttributes(), new TestStep());
		testStep.setDriver(driver);
		return testStep;
	}

	private static <T> T initByAttributes(NamedNodeMap attrs, T t){
        if(attrs == null || attrs.getLength() == 0)
            return t;
        int len = attrs.getLength();
        Node attr;
        String name, value;
        for(int i=0; i<len; i++){
            attr = attrs.item(i);
            if(attr == null)
                continue;
            name = attr.getNodeName();
            value = attr.getNodeValue();
            initByReflect(name, value, t);
        }
        return t;
    }

	private static <T> T initByReflect(String name ,String value, T t){
	    if(t == null)
	        throw new RuntimeException("null object");
	    if(name == null || "".equals(name))
            throw new RuntimeException("empty name");
        Class<?> clazz = t.getClass();
        Method setter, getter;
	    try {
            String methodStr = name.substring(0,1).toUpperCase() + name.substring(1);
            getter = clazz.getMethod(("cancel".equals(name) ? "is" : "get") + methodStr, new Class<?>[]{});
            setter = clazz.getMethod("set" + methodStr, getter.getReturnType());
            if("action".equals(name))
                setter.invoke(t, StepAction.action(value));
            else if("cancel".equals(name))
                setter.invoke(t, "true".equals(value) ? true : false);
            else
                setter.invoke(t, value);
        } catch (Exception e){
//            System.out.println("对象反射初始化失败");
//            e.printStackTrace();
        }
        return t;
    }
	

}
