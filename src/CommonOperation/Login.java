package CommonOperation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonMethod.Give;

public class Login {
	
    public Give G; 

    @BeforeTest
	public void Stup() throws Exception{
		  
		  G=new Give();
		  G.AppointFirefoxDriver();
	}
    
    @Test
    public void Case1() throws Exception{
	    
        //通过Excel单元格坐标,获取单元格的值,实现输入账号
	    G.InputExcelValue("C8", "E8", "E9");

	    //通过Excel单元格坐标,获取单元格的值,实现输入密码
	    G.InputExcelValue("C10", "E10", "E11");
	    
	    //通过Excel单元格坐标,获取单元格的值,实现输入验证码
	    G.InputExcelValue("C12", "E12", "E13");
	    
	    //通过Excel单元格坐标,获取单元格的值,实现点击登录按钮
	    G.ClickExcelItem("C14", "E14");
	    
	    //等待3秒
	    G.ImplicitWait(3000);
	    
	    //通过Excel单元格坐标,获取单元格的值,实现判断是否符合预期
	    G.CheckeExcelQual(G.GetExcelText("C15", "E15"), "E17", "E18","Case1");
    }
    
    @AfterTest
	public void End() {
    	
		G.Teardown();
	}
}