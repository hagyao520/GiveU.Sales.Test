package CommonOperation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonMethod.Give;

public class Visit {
	
	public Give G; 
	 
	@BeforeTest
	public void Stup() throws Exception{
		  
		  G=new Give();
		  G.AppointFirefoxDriver();
	}
    
	@Test
    public void Case1() throws Exception{
	    
    	//通过Excel单元格坐标,获取单元格的值,实现输入网站域名
    	G.GetExcelUrl("E3");
    	
    	//通过Excel单元格坐标,获取单元格的值,实现判断是否符合预期
    	G.CheckeExcelQual(G.GetExcelText("C4", "E4"), "E6", "E7","Case1");
    }
	
	@AfterTest
	public void End() {
		  G.Teardown();
	}
}