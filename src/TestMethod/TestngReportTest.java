package TestMethod;

import CommonMethod.DataProvide;
import CommonMethod.DataReader;
import CommonMethod.Give;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class TestngReportTest extends DataProvide{
	
  public Give king;
  public DataReader dr;
	
  @BeforeTest
  public void Stup() throws Exception{
	  
	  dr=new DataReader();
	  king=new Give();
	  king.AppointFirefoxDriver();
	  init("src/TestData/King.xml");
  }

  @Test(parameters ={"取消合同"})
  public void Case0(String SQL) throws Exception{
	  Reporter.log("Case0：购买PPM模式_手机系列B产品的_前置条件");
//	  king.SQLStatementUpdate(SQL);
  }
  
  @AfterTest
  public void End() {
	  king.Teardown();
  }
}