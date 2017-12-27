package TestMethod;

import org.w3c.dom.*;

import CommonMethod.Give;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class LoginTest {
	
  public Give G; 
 
  @BeforeTest
  public void Stup() throws Exception{
	  
	  G=new Give();
	  G.AppointFirefoxDriver();
  }

  @Test
  public void Case1() throws Exception{
	  
//      G.SQLStatementUpdate("update cs_credit set STATUS='s' where ID_PERSON= 15125203 and STATUS='n'","修改当前用户的待审合同为通过状态成功！");
//	  G.GetUrl("http://10.10.11.132:3012/CreditDetail/PpmApplyView/6DCFD78546822A30");
//	  G.InputValue("xpath", "html/body/div[1]/form/div[1]/input", "666666");
//	  G.InputValue("xpath", "html/body/div[1]/form/div[2]/input", "612425");
//	  G.InputValue("xpath", "html/body/div[1]/form/div[3]/input", "0");
//	  G.ClickItem("xpath", ".//*[@id='saveSubmit']");
//	  G.ForcedWait(3000);
//	  
//	  //点击提供的申请材料选项按钮
//	  G.ClickExcelItem("D271", "F271");
//	  
//	  //等待5秒
//	  G.ImplicitWait(5000);
//	  
//	  //切换到当前最新窗口
//	  G.SwitchToCurrentNewestWindow();
//	  
//	  //PgDn向下滑动
//	  G.KeyboardGeneralButton(Keys.PAGE_DOWN);
//	  
//	  //点击上传客户社保卡照片按钮
//	  G.ClickExcelItem("D272", "F272");
//	  
//	  //点击选择文件按钮
//	  G.ClickExcelItem("D273", "F273");
////	  G.ClickItem("xpath", ".//*[@id='10-button']");
//	  
//	  //TODO 选择AutoIT文件路径
//	  G.ExecuteExelExE("F274");
//	  
//	  //点击确认补传
//	  G.ClickExcelItem("D275", "F275");
//	  
//	  //等待5秒
//	  G.ImplicitWait(5000);
//	  
//	  //关闭当前窗口
//	  G.KeyboardCombinationCommonKey(Keys.CONTROL, "w");
//	  
//	  //点击提供的申请材料选项按钮
//	  G.ClickExcelItem("D271", "F271");
//	  
//	  //等待5秒
//	  G.ImplicitWait(5000);
//
//	  //切换到当前最新窗口
//	  G.SpecialKeyForKeyboardCombination(Keys.CONTROL, Keys.TAB);
//	  G.SwitchToCurrentNewestWindow();
//	  
//	  //设置检查点(一)
//	  G.CheckeExcelQual(G.GetExcelText("D276", "F276"), "F278", "F279","Case14_补传图片预览界面");
	  Give.SQLStatementQuery("select * from wfi_fraud_metrix_result where id =666666","查询当前用户操作记录信息成功！");
  }

  @AfterTest
  public void End() {
	  G.Teardown();
  }
}