package com.dafycredit.giveu.test.handler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.dafycredit.giveu.test.base.TestStep;

/**
 * 等待动作处理类
 */
public class WaitActionHandler {
	
	/**
	 * 强制等待
	 * @param map
	 * @param step
	 */
	public void waitForced(TestStep step){
		try {
			String waitTime = step.getValue();
			Thread.sleep(Long.valueOf(waitTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 隐式等待
	 * @param map
	 * @param step
	 * TODO 时间单位自定义
	 */
	public void waitImplicit(TestStep step){
		long waitTime = Long.valueOf(step.getValue());
		step.getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.MILLISECONDS);
	}

}
