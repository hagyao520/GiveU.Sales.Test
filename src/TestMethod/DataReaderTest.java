package TestMethod;

import org.w3c.dom.*;

import CommonMethod.DataProvide;
import CommonMethod.DataReader;
import CommonMethod.Give;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class DataReaderTest extends DataProvide{
	
  public Give king;
  public DataReader dr;
	
  @BeforeTest
  public void Stup() throws Exception{
	  
	  dr=new DataReader();
	  king=new Give();
	  king.AppointFirefoxDriver();
	  init("src/TestData/King.xml");
  }
  
  @Test(dataProvider="Test_xml_dataprovider")
  public void Case2(Document params) throws Exception{
	  Reporter.log("Case2:验证在登录界面_输入正确的账号和密码_可以正常登录");
	  king.InputValue("xpath",dr.readnodevalue(params, "Login", "用户名选项框"),dr.readnodevalue(params, "Login", "用户名"));
	  king.InputValue("xpath",dr.readnodevalue(params, "Login", "密码选项框"),dr.readnodevalue(params, "Login", "密码"));
	  king.InputValue("xpath",dr.readnodevalue(params, "Login", "验证码选项框"),dr.readnodevalue(params, "Login", "验证码"));
	  king.ClickItem("xpath",dr.readnodevalue(params, "Login", "登录按钮"));
	  king.ImplicitWait(2000);
	  king.CheckeQual(king.GetText("xpath",dr.readnodevalue(params,"Login", "登录成功检查点")),dr.readnodevalue(params,"Login", "登录成功预期值"),dr.readnodevalue(params, "Login", "登录失败提示值"));
  }
  

  @AfterTest
  public void End() {
	  king.Teardown();
  }
}