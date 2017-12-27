package com.dafycredit.giveu.test.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.dafycredit.giveu.test.handler.ClickActionHandler;
import com.dafycredit.giveu.test.handler.DBActionHandler;
import com.dafycredit.giveu.test.handler.WaitActionHandler;
import com.dafycredit.giveu.test.util.TestXmlHelper;

public enum StepAction {
	
	DB_QUERY("db-query", "数据库查询", DBActionHandler.class),
    DB_UPDATE("db-update", "数据库更新", DBActionHandler.class),
    DB_DELETE("db-delete", "数据库删除", DBActionHandler.class),
    DB_INSERT("db-insert", "数据库插入", DBActionHandler.class),

    GET("get", "访问地址"){
		@Override
		public void run(TestStep step) {
			step.getDriver().get(step.getValue());
		}
	},

    CHECK1("check1", "检查"){
		@Override
		public void run(TestStep step) {
			String text = step.getDriver().findElement(By.xpath(step.getXpath())).getText();
			Assert.assertEquals(text,step.getValue(),step.getTip());
		}
	},

    CHECK("check", "检查"){
		@Override
		public void run(TestStep step) {
			String Actual = step.getDriver().findElement(By.xpath(step.getXpath())).getText();
			String Expected = step.getValue();
			String FailHint = step.getTip();
			String FailedScreenshotCaseName = step.getCasename();
			try {
				Assert.assertEquals(Actual,Expected,FailHint);
				Thread.sleep(500);
            }
     	    catch (Error | InterruptedException e)  {
     	    	TestXmlHelper.ScreenShot(FailedScreenshotCaseName);
     	    	Assert.fail(FailHint +"  "+"Actual 【 "+ Actual +" 】"+"  "+"but found 【 "+ Expected +" 】");
     	    }
		}
	},
    
    INPUT("input", "输入"){
		@Override
		public void run(TestStep step) {
			WebElement e = step.getDriver().findElement(By.xpath(step.getXpath()));
			e.sendKeys(step.getValue());
			//TODO 时间配置待完善
			step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
	},
    
    SELECT("select", "选择"){
		@Override
		public void run(TestStep step) {
			WebDriver driver = step.getDriver();
			WebElement selectElem = driver.findElement(By.xpath(step.getXpath()));
            Actions actions = new Actions(driver);
            actions.moveToElement(selectElem).click().perform();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            WebElement optionElem = driver.findElement(By.xpath(step.getValue()));
            optionElem.click();
            //TODO 时间配置待完善
            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
	},

    Clear("clear", "清除"){
		@Override
		public void run(TestStep step){   
			WebElement e = step.getDriver().findElement(By.xpath(step.getXpath()));
			e.clear();
			step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }
	},
    
    ExecuteExE(".exe", "执行.exe程序"){
		@Override
		public void run(TestStep step){   
			final Runtime runtime = Runtime.getRuntime();  
	    	   try {
	    	    	runtime.exec(step.getValue());   	
	    	   } 
	    	   catch (final Exception e) {
	    	        System.out.println("Error exec!");  
	    	   }
	    	   step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }
	},
    
    SwitchToCurrentNewestWindow("stcnw", "切换到当前最新窗口"){
		@Override
		public void run(TestStep step){   
			String WindowHandle  = step.getDriver().getWindowHandle();
			   step.getDriver().switchTo().window(WindowHandle);
	    	   step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }
	},
    
    KeyboardGeneralButton("keybg", "模拟键盘普通按键，例如：Home键"){
		@Override
        public void run(TestStep step){
 	        Actions action = new Actions(step.getDriver()); 
 	        action.sendKeys(Keys.valueOf(step.getKey())).perform();
 	        step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
    },
	
    KeyboardCombinationCommonKey("keybc", "模拟键盘组合按键，例如：Ctrl+W"){
		@Override
        public void run(TestStep step){
			Actions action = new Actions(step.getDriver());
			action.keyDown(Keys.valueOf(step.getKey())).sendKeys(step.getValue()).perform();   
 	        step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
    },
    
    SpecialKeyForKeyboardCombination("skeybc", "模拟键盘特殊组合按键，例如：Ctrl+Tab"){
		@Override
        public void run(TestStep step){
 	        Actions action = new Actions(step.getDriver()); 
    	    action.keyDown(Keys.valueOf(step.getKey())).sendKeys(Keys.valueOf(step.getKeys())).keyUp(Keys.valueOf(step.getKey())).sendKeys(Keys.valueOf(step.getKeys())).perform(); 
 	        step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
    },
    
    CLICK("click", "点击", ClickActionHandler.class),
    CLICK_OK("click-ok", "点击弹出框_确定", ClickActionHandler.class),
    CLICK_CANCEL("click-cancel", "点击弹出框_取消", ClickActionHandler.class),
    
    WAIT_FORCED("wait-forced", "强制等待", WaitActionHandler.class),
    WAIT_IMPLICIT("wait-implicit", "隐性等待", WaitActionHandler.class);

    private String key;

    private String desc;

    private Class<?> handler;

    private static Map<String,StepAction> map;

    static{
        map = new HashMap<String,StepAction>();
        for(StepAction action : StepAction.values()){
            map.put(action.key(), action);
        }
    }

    private StepAction(String key, String desc, Class<?> handler) {
        this(key, desc);
        this.handler = handler;
    }

    private StepAction(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static StepAction action(String name){
        return map.get(name);
    }

    public String key(){
        return this.key;
    }

    public String desc(){
        return this.desc;
    }

    public Class<?> handler(){
        return this.handler;
    }
    
    public void run(TestStep step){
    	
    }
}
