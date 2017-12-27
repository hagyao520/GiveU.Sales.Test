package com.dafycredit.giveu.test.handler;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dafycredit.giveu.test.base.TestStep;

/**
 * 点击动作处理类
 */
public class ClickActionHandler {
	
	/**
	 * 点击
	 * @param map
	 * @param step
	 */
	public void click(TestStep step){
		WebElement e = step.getDriver().findElement(By.xpath(step.getXpath()));
		e.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void clickOk(TestStep step){
		step.getDriver().switchTo().alert().accept();
	}
	
	public void clickCancel(TestStep step){
		step.getDriver().switchTo().alert().dismiss();
	}
	
	public void clickText(TestStep step) {
		step.getDriver().switchTo().alert().sendKeys(step.getValue());
    }
}
