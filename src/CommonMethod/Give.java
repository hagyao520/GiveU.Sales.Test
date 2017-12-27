package CommonMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement; 
import org.testng.Assert;
import org.testng.Reporter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Give {  
	
	   private static Connection conn;  
	   private static PreparedStatement ps;   
	   private static ResultSet rs; 
       static public WebDriver driver;
       
       public Give(){
    	   FirefoxDriver();
       }
       
       //指定浏览器驱动
       public void FirefoxDriver(){

    	   System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe"); 
    	   driver = new FirefoxDriver();
    	   driver .manage().window().maximize();//全屏
       }       

       //指定浏览器驱动+配置文件
       public void AppointFirefoxDriver(){

    	   File file = new File("C:\\Users\\520475\\AppData\\Local\\Mozilla\\Firefox\\Profiles\\t1h1otzn.default");
    	   FirefoxProfile profile = new FirefoxProfile(file);        
    	   driver = new FirefoxDriver(profile);
    	   driver .manage().window().maximize();//全屏
    	   
       }
       
       //获取浏览器窗口句柄
       public String GetCurrentWindowHandle(){
    	   
    	   String CurrentWindow  = driver.getWindowHandle();
    	   return CurrentWindow;
       }
       
       //切换到新窗口
       public void SwitchToNewestWindow() throws InterruptedException{
    	   
    	   for (String handle:driver.getWindowHandles())
    	   {
    	    driver.switchTo().window(handle);
    	    this.ImplicitWait(3000);
    	   }
       }

       //切换到指定窗口
       public void SwitchToSpecifiedWindow(String WindowHandle) throws InterruptedException{
    	   
   	       driver.switchTo().window(WindowHandle);
   	       this.ImplicitWait(3000);
       }
       
       //切换到当前最新窗口
       public void SwitchToCurrentNewestWindow() throws InterruptedException{
    	   
    	   String WindowHandle  = driver.getWindowHandle();
   	       driver.switchTo().window(WindowHandle);
   	       this.ImplicitWait(3000);
       }

       //模拟键盘普通按键，例如：Home键
       public void KeyboardGeneralButton(Keys Keys) throws InterruptedException{

    	   Actions action = new Actions(driver); 
    	   action.sendKeys(Keys).perform();   
    	   this.ImplicitWait(3000);
       }
       
       //模拟键盘组合按键，例如：Ctrl+W
       public void KeyboardCombinationCommonKey(Keys Keys,String Value) throws InterruptedException{

    	   Actions action = new Actions(driver);
    	   action.keyDown(Keys).sendKeys(Value).perform();
//    	   action.keyDown(Key).sendKeys(Value).keyUp(Key).sendKeys(Keys.NULL).perform();
    	   this.ForcedWait(1000);
       }
       
       //模拟键盘特殊组合按键，例如：Ctrl+Tab
       public void SpecialKeyForKeyboardCombination(Keys Keys,Keys Keys1) throws InterruptedException{

    	   Actions action = new Actions(driver);
    	   action.keyDown(Keys).sendKeys(Keys1).keyUp(Keys).sendKeys(Keys1).perform(); 
    	   this.ForcedWait(500);
       }
       
       //打开网址
       public void GetUrl(String url) throws Exception{
    	   
    	   driver.get(url);
       }   
       
       //通过Excel文档获取数据，打开网址
       public void GetExcelUrl(String Value) throws Exception{
           
           try{
    	       File file = new File("TestData/TestData.xls");  
    	       InputStream in = new FileInputStream(file);
    	       Workbook workbook = Workbook.getWorkbook(in);
    	      
    	       Sheet sheet = workbook.getSheet(0);
    	      
    	       Cell CellName = sheet.getCell(Value); 
    	       String url = CellName.getContents();
    	       driver.get(url);
               this.ImplicitWait(5000);
          }
          catch (IOException | BiffException | IndexOutOfBoundsException e) {  
    			  e.printStackTrace();
    	          System.out.println("Exception: " + e);
    	  }
       }
       
       //关闭窗口
       public void Close(){
    	   
    	   driver.close();
       }
       
       //退出浏览器
       public void Teardown(){  
    	   
           try {
                  driver.quit();                
           } 
            catch (Exception e){ 
                  e.printStackTrace();
            }
       }
 
       //线程休眠，强制等待
       public void ForcedWait(long time) {
    	    
    	   try {
    	           Thread.sleep(time);
    	   } 
    	    catch (InterruptedException e) {
    	    	
    	           e.printStackTrace();
    	    }
       }
       
       //隐式等待，得不到某个元素,我们就延迟一下...
       public void ImplicitWait(long time) throws InterruptedException{

    	   driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS); 
       }
       
       //获取元素文本
       public String GetText(String type,String location){
    	   
              WebElement elem=null;
              if(type.equals("xpath"))
              {
                     elem=driver.findElement(By.xpath(location));                  
              }

              if(type.equals("name"))
              {
                 elem=driver.findElement(By.name(location));                    
              }
              
              if(type.equals("id"))
              {
                     elem=driver.findElement(By.id(location));                
              }
              
              if(type.equals("classname"))
              {
                     elem=driver.findElement(By.className(location));                  
              }

              if(type.equals("css"))
              {
                     elem=driver.findElement(By.cssSelector(location));                 
              }
              
              return elem.getText();
       }
       
       //通过Excel文档获取数据，获取元素文本
       public String GetExcelText(String Type,String Location) {
  	     
    	   WebElement elem=null;
              try{
        	        File file = new File("TestData/TestData.xls");  
        	        InputStream in = new FileInputStream(file);
        	        Workbook workbook = Workbook.getWorkbook(in);
        	        Sheet sheet = workbook.getSheet(0);
        	        
        	        Cell TypeName = sheet.getCell(Type); 
        	        String TypeValue = TypeName.getContents();
        	        
             	  if(TypeValue.equals("xpath")){
             		  
             		   Cell CellName = sheet.getCell(Location); 
             		   String CellValue = CellName.getContents();
                       elem=driver.findElement(By.xpath(CellValue));     
                  }

                  if(TypeValue.equals("name")){
                       
                	   Cell CellName = sheet.getCell(Location); 
            		   String CellValue = CellName.getContents();
                	   elem=driver.findElement(By.name(CellValue));
                  }

                  if(TypeValue.equals("id")){
                       
                	   Cell CellName = sheet.getCell(Location); 
            		   String CellValue = CellName.getContents();
                	   elem=driver.findElement(By.id(CellValue));
                  } 
                  
                  if(TypeValue.equals("classname")) {
                	  
                	   Cell CellName = sheet.getCell(Location); 
           		       String CellValue = CellName.getContents();
                       elem=driver.findElement(By.className(CellValue));                  
                  }

                  if(TypeValue.equals("css")){
                	  
                	   Cell CellName = sheet.getCell(Location); 
          		       String CellValue = CellName.getContents();
                       elem=driver.findElement(By.cssSelector(CellValue));                 
                  }
              }
              catch (IOException | BiffException | IndexOutOfBoundsException e) {  
        			  e.printStackTrace();
        	          System.out.println("Exception: " + e);
        	  }
              
              return elem.getText();
       }
       
       //获取元素标题，一般为页面标题
       public String GetTitle(){
    	   
    	   return driver.getTitle();
       }
       
       //根据元素，执行点击操作
       public void ClickItem(String type,String location) throws InterruptedException{
    	   
    	   try{
              WebElement elem=null;
              if(type.equals("xpath"))
              {
                     elem=driver.findElement(By.xpath(location));                  
              }

              if(type.equals("classname"))
              {
                     elem=driver.findElement(By.className(location));                  
              }

              if(type.equals("text"))
              {
                     elem=driver.findElement(By.linkText(location));              
              }
              
              if(type.equals("name"))
              {

                     elem=driver.findElement(By.name(location));                  
              }
              elem.click();
              this.ImplicitWait(3000);
           }
    	    catch (Exception e) {  
  			  e.printStackTrace();
  	          System.out.println("Exception: " + e);
  	        }
       }
       
       //通过Excel文档获取元素数据，执行点击操作
       public void ClickExcelItem(String Type,String Location) throws InterruptedException{
    	   
    	   WebElement elem=null;
              try{
     	            File file = new File("TestData/TestData.xls");  
     	            InputStream in = new FileInputStream(file);
     	            Workbook workbook = Workbook.getWorkbook(in);
     	            Sheet sheet = workbook.getSheet(0);
     	        
     	            Cell TypeName = sheet.getCell(Type); 
     	            String TypeValue = TypeName.getContents();
     	        
          	     if(TypeValue.equals("xpath")){
          		  
          		    Cell CellName = sheet.getCell(Location); 
          		    String CellValue = CellName.getContents();
                    elem=driver.findElement(By.xpath(CellValue));     
                 }

                 if(TypeValue.equals("name")){
                    
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.name(CellValue));
                 }

                 if(TypeValue.equals("id")){
                    
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.id(CellValue));
                 }
              
                 if(TypeValue.equals("text")){
                   
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.linkText(CellValue));
                 }
               
                 if(TypeValue.equals("classname")){
                   
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.className(CellValue));
                  }
     	          elem.click();
     	          this.ForcedWait(500);
               }
               catch (IOException | BiffException | IndexOutOfBoundsException e) {  
     			  e.printStackTrace();
     	          System.out.println("Exception: " + e);
     	       }
       }
       
       //清除信息，一般指输入框的文本信息
       public void ClearValue(String type,String location){
    	   
              WebElement elem=null;
              if(type.equals("xpath"))
              {
                     elem=driver.findElement(By.xpath(location));
              }

              if(type.equals("name"))
              {
                 elem=driver.findElement(By.name(location));
              }
              elem.clear();
       }

       //通过Excel文档获取数据，清除输入框文本信息
       public void ClearExcelValue(String Type,String Location) throws InterruptedException{
    	   
    	   WebElement elem=null;
              try{
     	            File file = new File("TestData/TestData.xls");  
     	            InputStream in = new FileInputStream(file);
     	            Workbook workbook = Workbook.getWorkbook(in);
     	            Sheet sheet = workbook.getSheet(0);
     	        
     	            Cell TypeName = sheet.getCell(Type); 
     	            String TypeValue = TypeName.getContents();
     	        
          	     if(TypeValue.equals("xpath")){
          		  
          		    Cell CellName = sheet.getCell(Location); 
          		    String CellValue = CellName.getContents();
                    elem=driver.findElement(By.xpath(CellValue));     
                 }

                 if(TypeValue.equals("name")){
                    
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.name(CellValue));
                 }

                 if(TypeValue.equals("id")){
                    
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.id(CellValue));
                 }
              
                 if(TypeValue.equals("text")){
                   
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.linkText(CellValue));
                 }
               
                 if(TypeValue.equals("classname")){
                   
             	    Cell CellName = sheet.getCell(Location); 
         		    String CellValue = CellName.getContents();
             	    elem=driver.findElement(By.className(CellValue));
                  }
     	          elem.clear();
                  this.ImplicitWait(3000);
               }
               catch (IOException | BiffException | IndexOutOfBoundsException e) {  
     			  e.printStackTrace();
     	          System.out.println("Exception: " + e);
     	       }
       }
       
       //输入文本信息，一般指输入框的文本
       public void InputValue(String type,String location,String text) throws InterruptedException{
    	   
           WebElement elem=null;
              if(type.equals("xpath")){
            	  
                   elem=driver.findElement(By.xpath(location));           
              }

              if(type.equals("name")){
                     
            	   elem=driver.findElement(By.name(location));                  
              }

              if(type.equals("id")){
                     
            	   elem=driver.findElement(By.id(location));         
              }
              elem.sendKeys(text);
              this.ForcedWait(500);
       }

       //通过Excel文档获取数据，输入文本信息
       public void InputExcelValue(String Type,String Location,String Value) throws InterruptedException{
    	     
    	   WebElement elem=null;
              try{
        	        File file = new File("TestData/TestData.xls");  
        	        InputStream in = new FileInputStream(file);
        	        Workbook workbook = Workbook.getWorkbook(in);
        	        Sheet sheet = workbook.getSheet(0);
        	        
        	        Cell TypeName = sheet.getCell(Type); 
        	        String TypeValue = TypeName.getContents();
        	        
             	  if(TypeValue.equals("xpath")){
             		  
             		   Cell CellName = sheet.getCell(Location); 
             		   String CellValue = CellName.getContents();
                       elem=driver.findElement(By.xpath(CellValue));     
                  }

                  if(TypeValue.equals("name")){
                       
                	   Cell CellName = sheet.getCell(Location); 
            		   String CellValue = CellName.getContents();
                	   elem=driver.findElement(By.name(CellValue));
                  }

                  if(TypeValue.equals("id")){
                       
                	   Cell CellName = sheet.getCell(Location); 
            		   String CellValue = CellName.getContents();
                	   elem=driver.findElement(By.id(CellValue));
                  }
        	           Cell CellName = sheet.getCell(Value); 
        	           String CellValue = CellName.getContents();
        	           elem.sendKeys(CellValue);
//        	           this.ForcedWait(1000);
                       this.ImplicitWait(3000);
              }
              catch (IOException | BiffException | IndexOutOfBoundsException e) {  
        			  e.printStackTrace();
        	          System.out.println("Exception: " + e);
        	  }
       }

       //标记备注，一般展示在测试报告中
       public void TestReportRemarks(String RemarksName){
    	   
    	   Reporter.log(RemarksName);
       }
       
       //检查元素信息，一般作为检查点使用
       public void CheckeQual(String Actual,String Expected,String FailHint){
    	   
           Assert.assertEquals(Actual,Expected,FailHint);
       }
       
       //通过Excel文档获取数据，设置检查点，失败时截取图片，进行保存
       public void CheckeExcelQual(String Actual,String Expected,String FailHint,String FailedScreenshotCaseName) throws BiffException, IOException, InterruptedException{
    	   
    	   File file = new File("TestData/TestData.xls");  
	       InputStream in = new FileInputStream(file);
	       Workbook workbook = Workbook.getWorkbook(in);
	       Sheet sheet = workbook.getSheet(0);
	       
 	       Cell CellName = sheet.getCell(Expected); 
	       String CellValue = CellName.getContents();
	       
	       Cell CellName_1 = sheet.getCell(FailHint); 
 	       String CellValue_1 = CellName_1.getContents();
           try {

     	        Assert.assertEquals(Actual,CellValue,CellValue_1);
     	        this.ForcedWait(500);
           }
     	    catch (Error e)  {
     	    	
     	    	ScreenShot(FailedScreenshotCaseName);
     	    	Assert.fail(CellValue_1 +"  "+"Actual 【 "+ Actual +" 】"+"  "+"but found 【 "+ CellValue +" 】");
//     	    	System.out.println("Exception: " + e);
//     	    	verificationErrors.append(e.toString());
     	    }
       }
       
       //点击选项框，选择对应选项
       public void Choice(String Value1,String Value2) throws InterruptedException{

    	   WebElement from_inpox = driver.findElement(By.xpath(Value1));
           Actions actions = new Actions(driver);
           actions.moveToElement(from_inpox).click().perform();
           this.ClickItem("xpath", Value2);
           this.ForcedWait(800);
       }

       //通过Excel文档获取数据，点击选项框，选择对应选项
       public void ChoiceExcel(String Value1,String Type,String Value2) throws InterruptedException, BiffException, IOException{
    	   
    	   try {
    		     File file = new File("TestData/TestData.xls");  
	             InputStream in = new FileInputStream(file);
	             Workbook workbook = Workbook.getWorkbook(in);
	             Sheet sheet = workbook.getSheet(0);
	        
	             Cell CellName = sheet.getCell(Type); 
	             String CellValue = CellName.getContents();
 
	             Cell CellName_1 = sheet.getCell(Value1); 
	             String CellValue_1 = CellName_1.getContents();
	       
	             Cell CellName_2 = sheet.getCell(Value2); 
	             String CellValue_2 = CellName_2.getContents();
	       
    	         WebElement from_inpox = driver.findElement(By.xpath(CellValue_1));
                 Actions actions = new Actions(driver);
                 actions.moveToElement(from_inpox).click().perform();
                 this.ForcedWait(200);
                 this.ClickItem(CellValue, CellValue_2);
                 this.ImplicitWait(5000);
           }
    	    catch (IOException | BiffException | IndexOutOfBoundsException e) {  
 			     e.printStackTrace();
	             System.out.println("Exception: " + e);
	        }
       }
       
       //上传图片，一般根据开发实现的功能，对应使用，只能用于简单的选项上传图片，可以获取元素的name值
       public void UploadPictures(String w1,String w2) {
    	   
    	   WebElement  file = driver.findElement(By.name(w1));
    	   file.sendKeys(w2);
       }
       
       //执行.exe文件
       public void ExecuteExE(String path) throws InterruptedException {
    	   
     	   final Runtime runtime = Runtime.getRuntime();  
    	   try {
    		   
    	    	runtime.exec(path);   	
    	   } 
    	   catch (final Exception e) {
    		   
    	        System.out.println("Error exec!");  
    	   }
    	   this.ForcedWait(3000);
       } 

       //通过Excel文档获取数据，执行.exe文件
       public void ExecuteExelExE(String Value) throws InterruptedException {
    	   
     	   final Runtime runtime = Runtime.getRuntime();  
    	   try {
    		    File file = new File("TestData/TestData.xls");  
    	        InputStream in = new FileInputStream(file);
    	        Workbook workbook = Workbook.getWorkbook(in);
    	      
    	        Sheet sheet = workbook.getSheet(0);
    	      
    	        Cell CellName = sheet.getCell(Value); 
    	        String path = CellName.getContents();
    	    	runtime.exec(path);  
    	    	this.ForcedWait(3000);
    	   } 
    	   catch (IOException | BiffException | IndexOutOfBoundsException e) {  
 			  e.printStackTrace();
 	          System.out.println("Error exec!" + e);
 	       }  
       }  
       
       //点击弹出框的确定按钮，一般指浏览器的弹出框
       public void PopupAction_Ok() {
    	   
    	   driver.switchTo().alert().accept();
       } 
       
       //点击弹出框的取消按钮，一般指浏览器的弹出框
       public void PopupAction_Cancel() {
    	   
           driver.switchTo().alert().dismiss();
           
       } 
       
       //在弹出框输入文本信息，一般指浏览器弹出框
       public void PopupAction_Text(String text) {
    	   
           driver.switchTo().alert().sendKeys(text);;
       }

       //获取系统时间，一般指当前电脑系统的时间
       public static String getDateTime(){
    	   
           SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
           return df.format(new Date());
       }
 
       //根据用例的名称，截取图片，进行保存
       public void ScreenShot(String CaseName){
    	   
    	   int t =1;
    	   String CurrentDir = System.getProperty("user.dir");
    	   String AppointDir =CurrentDir+"\\BugScreenshot\\";
    	   String picture =AppointDir+CaseName+".png";  
//           String picture =AppointDir+CaseName+"_"+getDateTime()+"_"+t+".png";  
           
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
       
       //连接数据库，当前为Oracle，需要使用其他数据库，指定Driver和类别即可
       private static Connection ConnectDatabase() {
       	
           String driver = "oracle.jdbc.driver.OracleDriver";
           String url = "jdbc:oracle:thin:@idcdbtest.dafycredit.com:1521:dbtest01";
           String username = "dafy_sales";
           String password = "St$2016";

           try {
                 Class.forName(driver);
                  System.out.println("------------------------------------------------------------");
                  System.out.println("开始尝试连接数据库!");
                 conn = DriverManager.getConnection(url, username, password); 
                  System.out.println("连接成功!");
           }
           catch (ClassNotFoundException e) {
           	  CloseDatabase();  
                 e.printStackTrace();
                  System.out.println("数据库连接失败!");  
           }
           catch (SQLException e) {
           	  CloseDatabase();  
                 e.printStackTrace();
                  System.out.println("数据库登录失败!");  
           }
           return conn;
       } 
       
       //关闭数据库，断开连接
       public static void CloseDatabase() {  
     	  if (conn != null) {  
     		try {  
     		      conn.close();  
     		      conn = null; 
     		      System.out.println("数据库连接已关闭！");
     		      System.out.println("------------------------------------------------------------");
     		} 
     		catch (SQLException e) {  
     		    e.printStackTrace();  
     		}  
     	  }  
     	  if (ps != null) {  
     		try {  
     		      ps.close();  
     		      ps = null;
     		} 
     		catch (SQLException e) {  
     		  e.printStackTrace();  
     		}  
     	  }  
     	  if (rs != null) {  
     		try {  
     		      rs.close();  
     		      rs = null;
     		} 
     		catch (SQLException e) {  
     		  e.printStackTrace();  
     		}  
     	  }  
       }
     
       //指定SQL语句，执行修改操作
       public int SQLStatementUpdate(String SQL,String Prompt) {  
      		Connection conn = ConnectDatabase();
            int i = 0;
            String sql = SQL;
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(sql);
                i = ps.executeUpdate();
                System.out.println(Prompt + "  共: "+ i +" 条数据！");
//                conn.commit();  
//                System.out.println("提交成功!");
                CloseDatabase();
            } 
            catch (SQLException e) {
            	System.out.println("修改失败");
                e.printStackTrace();
            }
            return i;
        }
      	
        //指定SQL语句，执行查询操作
        public static void SQLStatementQuery(String SQL,String Prompt) {  
    	  	  Connection conn = ConnectDatabase();
    	        try {
    	              ps = conn.prepareStatement(SQL);
    	              rs = ps.executeQuery();
    	              int col = rs.getMetaData().getColumnCount(); 
    	              System.out.println("============================");
    	              while (rs.next()) {
    	                   for (int i = 1; i <= col; i++) {
    	                        System.out.print(rs.getString(i) + "\t");
    	                      if ((i == 2) && (rs.getString(i).length() < 8)) {
    	                             System.out.print("\t");
    	                      }
    	                   }
    	                System.out.println("");
    	              }
    	                System.out.println("============================");
    	                System.out.println(Prompt);
    	                CloseDatabase();
    	  	    }
    	        catch (SQLException e) {
    	        	System.out.println("查询失败");
    	            e.printStackTrace();
    	        }
    	}
      	
        //指定SQL语句，执行删除操作
        public static int SQLStatementDelete(String SQL) {  
      		Connection conn = ConnectDatabase();
            int i = 0;
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(SQL);
                i = ps.executeUpdate();
                System.out.println("SQL语句删除成功: "+ i +" 条数据！");
//                conn.commit();  
//                System.out.println("提交成功!");
                CloseDatabase();
                System.out.println("数据库连接已关闭！");
            } 
            catch (SQLException e) {
            	System.out.println("删除失败");
                e.printStackTrace();
            }
            return i;
        }
      	
        //指定存储过程名称和参数，执行存储过程，然后查询结果
        //{call prc_auto_daily(?)},4428763,select * from instalment where id_credit= 4428763
        public static void StoredProcedureExecution(String ProcessName,String Value,String Result) {  
      		Connection conn = ConnectDatabase();
      		CallableStatement cs;
      		PreparedStatement ps;
            try {
            	cs = conn.prepareCall(ProcessName);
            	cs.setString(1, Value);
            	System.out.println("开始执行存储过程: "+ ProcessName); 
            	cs.execute();
            	System.out.println("存储过程执行成功, 生成数据如下: "); 
            	ps = conn.prepareStatement(Result);
            	rs = ps.executeQuery();
            	int col = rs.getMetaData().getColumnCount();
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (rs.next()) {
                     for (int i = 1; i <= col; i++) {
                          System.out.print(rs.getString(i) + "\t");
                        if ((i == 2) && (rs.getString(i).length() < 8)) {
                               System.out.print("\t");
                        }
                     }
                  System.out.println("");
                }
                  System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                CloseDatabase();
                System.out.println("数据库连接已关闭！");
            } 
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("存储过程执行失败！");
            }
       }
       
       //登录
       public void login(String name,String psd) throws Exception{
    	     
           ClickItem("xpath", ".//*[@id='ng-app']/body/div[2]/div/div[2]/a[1]");
           Thread.sleep(3000);
           this.ClearValue("name", "username");
           this.InputValue("name", "username", name);
           this.ClearValue("name", "user_pwd");
           this.InputValue("name", "user_pwd", psd);
           Thread.sleep(1000);
           this.ClickItem("classname", "zc");
           Thread.sleep(5000);
       }
       
       //退出
       public void logout() throws Exception{
    	   
           this.GetUrl("http://www.zhongchou.com/sso-logout");
       }  
}