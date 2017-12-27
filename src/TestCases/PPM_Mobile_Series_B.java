//引用工程的包名
package TestCases;

import org.openqa.selenium.Keys;
//导入外部引用的类包
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//引用定义的java类
import CommonMethod.Give;

public class PPM_Mobile_Series_B {
  
  //设置java类别名
  public Give G;
    
  @BeforeTest
  public void Stup() throws Exception{
	  
	  //初始化java类
	  G=new Give();
	  
	  //启动引用的浏览器
	  G.AppointFirefoxDriver();
  }

  @Test
  public void Case0() throws Exception{G.TestReportRemarks("验证在数据库中,修改当前用户所有合同为取消状态后,可以正常修改成功");
  
      //修改合同状态
      G.SQLStatementUpdate("update cs_credit set STATUS='t' where ID_PERSON=15125203","修改当前用户所有合同为取消状态成功！");
  }
  
  @Test
  public void Case1() throws Exception{G.TestReportRemarks("验证在火狐浏览器中,输入达飞金融销售提单系统域名后,可以正常访问");
      
      //TODO 输入网站域名
      G.GetExcelUrl("F3");
      
      //设置检查点
      G.CheckeExcelQual(G.GetExcelText("D4", "F4"), "F6", "F7","Case1");
  }
  
  @Test
  public void Case2() throws Exception{G.TestReportRemarks("验证在登录界面,输入正确的账号和密码,点击登录按钮后,可以正常跳转至首页主界面");
  
      //TODO 输入账号
	  G.InputExcelValue("D8", "F8", "F9");
	  
	  //TODO 输入密码
	  G.InputExcelValue("D10", "F10", "F11");
	  
	  //TODO 输入验证码
	  G.InputExcelValue("D12", "F12", "F13");
	  
	  //点击登录
	  G.ClickExcelItem("D14", "F14");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D15", "F15"), "F17", "F18","Case2");
  }

  @Test
  public void Case3() throws Exception{G.TestReportRemarks("验证在选择产品界面,选择输入对应产品信息,点击搜索按钮后,可以正常搜索且自动弹出产品列表界面");
  
      //点击我的申请
      G.ClickExcelItem("D19", "F19");
      G.ImplicitWait(5000);
      
      //TODO 选择门店
	  G.ChoiceExcel("F20","D21", "F21");
	  
	  //TODO 选择产品版本
	  G.ForcedWait(500);
	  G.ChoiceExcel("F22","D23", "F23");
	  
	  //TODO 选择商品类型
	  G.ClickExcelItem("D24", "F24");

	  //TODO 选择产品类型
	  G.ClickExcelItem("D25", "F25");
	  
	  //TODO 输入销售价格
	  G.InputExcelValue("D26", "F26", "F27");
	  
	  //TODO 输入自付现金
	  G.InputExcelValue("D28", "F28", "F29");
	  
	  //点击搜索
	  G.ClickExcelItem("D30", "F30");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D31", "F31"), "F33", "F34","Case3");
  }
  
  @Test
  public void Case4() throws Exception{G.TestReportRemarks("验证在产品信息界面,选择点击对应产品信息后,可以正常选择且自动弹出商品信息和身份证信息界面");
      
      //TODO 选择产品信息
	  G.ClickExcelItem("D35", "F35");
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D36", "F36"), "F38", "F39","Case4");
  }
  
  @Test
  public void Case5() throws Exception{G.TestReportRemarks("验证在商品信息和身份证信息界面,选择输入对应商品信息和身份证信息,点击保存按钮后,可以正常保存且自动弹出地址信息界面");
      
      //商品信息
      //商品(一)
      //TODO 选择商品小类
      G.ChoiceExcel("F40","D41", "F41");
      
      //TODO 选择商品品牌
      G.ChoiceExcel("F42","D43", "F43");
      
      //TODO 输入商品型号
      G.InputExcelValue("D44", "F44", "F45");
	  
      //TODO 勾选手机健康服务
	  G.ClickExcelItem("D46", "F46");
	  
	  //TODO 选择分期代扣
	  G.ClickExcelItem("D47", "F47");
	  
	  //TODO 勾选手机延保服务
	  G.ClickExcelItem("D48", "F48");
	  
	  //TODO 选择分期代扣
	  G.ClickExcelItem("D49", "F49");
	  
	  
	  //身份证信息
	  //TODO 输入姓名
      G.InputExcelValue("D60", "F60", "F61");
	  
      //TODO 输入身份证号码
      G.InputExcelValue("D62", "F62", "F63");
	  
      //TODO 选择民族
      G.ChoiceExcel("F64","D65", "F65");
	  
      //TODO 勾选身份证有效期为长期
	  G.ClickExcelItem("D66", "F66");
	  
	  //点击保存
	  G.ClickExcelItem("D67", "F67");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D68", "F68"), "F70", "F71","Case5");
  }
  
  @Test
  public void Case6() throws Exception{G.TestReportRemarks("验证在地址信息界面,选择输入对应地址信息,点击保存按钮后,可以正常保存且自动弹出个人信息界面");
      
      //地址信息
      //TODO 选择邮件地址
      G.ChoiceExcel("F72","D73", "F73");
      
      //户籍地址
      //TODO 选择城市
      G.ChoiceExcel("F74","D75", "F75");
      
      //TODO 选择区县
      G.ChoiceExcel("F76","D77", "F77");
      
      //TODO 输入镇
      G.ClearExcelValue("D78", "F78");
      G.InputExcelValue("D78", "F78", "F79");
      
      //TODO 输入街道/路/村
      G.ClearExcelValue("D80", "F80");
      G.InputExcelValue("D80", "F80", "F81");
      
      //TODO 输入小区/楼盘
      G.ClearExcelValue("D82", "F82");
      G.InputExcelValue("D82", "F82", "F83");
      
      //TODO 输入栋/单元/房间号
      G.ClearExcelValue("D84", "F84");
      G.InputExcelValue("D84", "F84", "F85");
      
      //现居地地址
      //TODO 选择城市
      G.ForcedWait(4000);
      G.ChoiceExcel("F86","D87", "F87");
      
      //TODO 选择区县
      G.ChoiceExcel("F88","D89", "F89");
      
      //TODO 输入镇
      G.ClearExcelValue("D90", "F90");
      G.InputExcelValue("D90", "F90", "F91");
      
      //TODO 输入街道/路/村
      G.ClearExcelValue("D92", "F92");
      G.InputExcelValue("D92", "F92", "F93");
      
      //TODO 输入小区/楼盘
      G.ClearExcelValue("D94", "F94");
      G.InputExcelValue("D94", "F94", "F95");
      
      //TODO 输入栋/单元/房间号
      G.ClearExcelValue("D96", "F96");
      G.InputExcelValue("D96", "F96", "F97");
      
      //工作单位地址
      //TODO 选择城市
      G.ChoiceExcel("F99","D100", "F100");
      
      //TODO 选择区县
      G.ForcedWait(500);
      G.ChoiceExcel("F101","D102", "F102");
      
      //TODO 输入镇
      G.ClearExcelValue("D103", "F103");
      G.InputExcelValue("D103", "F103", "F104");
      
      //TODO 输入街道/路/村
      G.ClearExcelValue("D105", "F105");
      G.InputExcelValue("D105", "F105", "F106");
      
      //TODO 输入小区/楼盘
      G.ClearExcelValue("D107", "F107");
      G.InputExcelValue("D107", "F107", "F108");
      
      //TODO 输入栋/单元/房间号
      G.ClearExcelValue("D109", "F109");
      G.InputExcelValue("D109", "F109", "F110");
      
      //点击保存
	  G.ClickExcelItem("D111", "F111");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D112", "F112"), "F114", "F115","Case6");
  }
  
  @Test
  public void Case7() throws Exception{G.TestReportRemarks("验证在个人信息界面,选择输入对应个人信息,点击保存按钮后,可以正常保存且自动弹出银行卡信息界面");

      //TODO 输入单位全称
      G.ClearExcelValue("D116", "F116");
      G.InputExcelValue("D116", "F116", "F117");
      
      //TODO 选择职位
      G.ChoiceExcel("F118","D119", "F119");
       
      //TODO 选择行业类别
      G.ChoiceExcel("F120","D121", "F121");
      
      //TODO 选择单位性质
      G.ChoiceExcel("F122","D123", "F123");
      
      //TODO 输入任职部门
      G.ClearExcelValue("D124", "F124");
      G.InputExcelValue("D124", "F124", "F125");
      
      //TODO 选择教育程度
      G.ChoiceExcel("F126","D127", "F127");
  
      //TODO 选择入职时间
      G.ChoiceExcel("F128","D129", "F129");
      
      //TODO 选择工作年限
      G.ChoiceExcel("F130","D131", "F131");
      
      //TODO 输入个人月收入总额
      G.ClearExcelValue("D132", "F132");
      G.InputExcelValue("D132", "F132", "F133");
      
      //TODO 输入其他收入
      G.ClearExcelValue("D134", "F134");
      G.InputExcelValue("D134", "F134", "F135");
      
      //TODO 输入家庭月收入总额
      G.ClearExcelValue("D136", "F136");
      G.InputExcelValue("D136", "F136", "F137");
      
      //TODO 输入办公/个人电话
      G.ClearExcelValue("D138", "F138");
      G.InputExcelValue("D138", "F138", "F139");
      
      //TODO 输入办公/个体电话分机
      G.ClearExcelValue("D140", "F140");
      G.InputExcelValue("D140", "F140", "F141");
      
      //TODO 输入家庭固话
      G.ClearExcelValue("D142", "F142");
      G.InputExcelValue("D142", "F142", "F143");
      
      //TODO 输入手机
      G.ClearExcelValue("D144", "F144");
      G.InputExcelValue("D144", "F144", "F145");
      
      //TODO 输入电子邮箱
      G.ClearExcelValue("D146", "F146");
      G.InputExcelValue("D146", "F146", "F147");
      
      //TODO 输入QQ
      G.ClearExcelValue("D148", "F148");
      G.InputExcelValue("D148", "F148", "F149");
      
      //TODO 输入微信
      G.ClearExcelValue("D150", "F150");
      G.InputExcelValue("D150", "F150", "F151");

      //点击保存
	  G.ClickExcelItem("D152", "F152");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D153", "F153"), "F155", "F156","Case7");
  }
  
  @Test
  public void Case8() throws Exception{G.TestReportRemarks("验证在银行卡信息界面,选择输入对应银行卡信息,点击保存按钮后,可以正常保存且自动弹出家庭信息界面");

      //TODO 输入银行卡账号
      G.ClearExcelValue("D157", "F157");
      G.InputExcelValue("D157", "F157", "F158");

      //TODO 输入重复银行卡账号
      G.ClearExcelValue("D159", "F159");
      G.InputExcelValue("D159", "F159", "F160");
      
      //TODO 选择开户行省/直辖市
      G.ChoiceExcel("F161","D162", "F162");
      G.ForcedWait(500);
      G.ChoiceExcel("F163","D164", "F164");
      
      //TODO 输入支行名称
      G.ClearExcelValue("D165", "F165");
      G.InputExcelValue("D165", "F165", "F166");
      
      //点击保存
	  G.ClickExcelItem("D167", "F167");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D168", "F168"), "F170", "F171","Case8");
  }
  
  @Test
  public void Case9() throws Exception{G.TestReportRemarks("验证在家庭信息界面,选择输入对应家庭信息,点击保存按钮后,可以正常保存且自动弹出其他联系人信息界面");

      //TODO 选择住房情况
      G.ChoiceExcel("F172","D173", "F173");
      
      //TODO 输入个人月支出
      G.ClearExcelValue("D174", "F174");
      G.InputExcelValue("D174", "F174", "F175");
      
      //TODO 选择婚姻状况
	  G.ClickExcelItem("D176", "F176");
	  
	  //TODO 输入子女数目
      G.ClearExcelValue("D177", "F177");
      G.InputExcelValue("D177", "F177", "F178");
      
      //家庭成员(一)
      //TODO 选择关系
      G.ChoiceExcel("F179","D180", "F180");
  
      //TODO 输入姓名
      G.ClearExcelValue("D181", "F181");
      G.InputExcelValue("D181", "F181", "F182");

      //TODO 输入手机
      G.ClearExcelValue("D183", "F183");
      G.InputExcelValue("D183", "F183", "F184");
      
      //TODO 输入联系地址
      G.ClearExcelValue("D185", "F185");
      G.InputExcelValue("D185", "F185", "F186");
      
      //家庭成员(二)
      //TODO 选择关系
      G.ChoiceExcel("F187","D188", "F188");
  
      //TODO 输入姓名
      G.ClearExcelValue("D189", "F189");
      G.InputExcelValue("D189", "F189", "F190");

      //TODO 输入手机
      G.ClearExcelValue("D191", "F191");
      G.InputExcelValue("D191", "F191", "F192");
      
      //TODO 输入联系地址
      G.ClearExcelValue("D193", "F193");
      G.InputExcelValue("D193", "F193", "F194");
      
      //点击保存
	  G.ClickExcelItem("D195", "F195");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D196", "F196"), "F198", "F199","Case9");
  }
  
  @Test
  public void Case_10() throws Exception{G.TestReportRemarks("验证在其他联系人信息界面,选择输入对应其他联系人信息,点击保存按钮后,可以正常保存且自动弹出其他信息界面");

      //联系人一
      //TODO 输入联系人一姓名
      G.ClearExcelValue("D200", "F200");
      G.InputExcelValue("D200", "F200", "F201");

      //TODO 选择关系
      G.ChoiceExcel("F202","D203", "F203");
      
      //TODO 输入联系人一手机
      G.ClearExcelValue("D204", "F204");
      G.InputExcelValue("D204", "F204", "F205");
      
      //联系人二
      //TODO 输入联系人二姓名
      G.ClearExcelValue("D206", "F206");
      G.InputExcelValue("D206", "F206", "F207");

      //TODO 选择关系
      G.ChoiceExcel("F208","D209", "F209");
      
      //TODO 输入联系人二手机
      G.ClearExcelValue("D210", "F210");
      G.InputExcelValue("D210", "F210", "F211");
      
      //联系人三
      //TODO 输入联系人三姓名
      G.ClearExcelValue("D212", "F212");
      G.InputExcelValue("D212", "F212", "F213");

      //TODO 选择关系
      G.ChoiceExcel("F214","D215", "F215");
      
      //TODO 输入联系人三手机
      G.ClearExcelValue("D216", "F216");
      G.InputExcelValue("D216", "F216", "F217");
      
      //点击保存
	  G.ClickExcelItem("D218", "F218");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D219", "F219"), "F221", "F222","Case_10");
  }
  
  @Test
  public void Case_11() throws Exception{G.TestReportRemarks("验证在其他界面,选择输入对应其他信息,点击保存和下一步按钮后,可以正常保存且自动跳转至上传文件界面");

      //TODO 选择评定内部代码
      G.ChoiceExcel("F223","D224", "F224");
  
      //TODO 输入备注
      G.ClearExcelValue("D225", "F225");
      G.InputExcelValue("D225", "F225", "F226");

      //TODO 勾选该单是否出自移动门店
	  G.ClickExcelItem("D227", "F227");
	  
      //点击保存
	  G.ClickExcelItem("D228", "F228");
	  
	  //点击下一步
	  G.ClickExcelItem("D229", "F229");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //设置检查点
	  G.CheckeExcelQual(G.GetExcelText("D230", "F230"), "F232", "F233","Case_11");
  }
  
  @Test
  public void Case_12() throws Exception{G.TestReportRemarks("验证在上传文件界面,选择输入对应图片文件信息,点击保存并提交按钮后,可以正常保存提交且自动跳转至合同列表界面");
 
	  //上传客户身份证照片
      //点击上传客户身份证照片按钮
	  G.ClickExcelItem("D234", "F234");
	  
	  //点击选择文件按钮
	  G.ClickExcelItem("D235", "F235");
	  
	  //TODO 选择AutoIT文件路径
	  G.ExecuteExelExE("F236");

	  //上传上传客户在门店照片
      //点击上传客户在门店照片按钮
	  G.ClickExcelItem("D237", "F237");
	  
	  //点击选择文件按钮
	  G.ClickExcelItem("D238", "F238");
	  
	  //TODO 选择AutoIT文件路径
	  G.ExecuteExelExE("F239");
	  
	  //上传收入证明照片
      //点击上传收入证明照片按钮
	  G.ClickExcelItem("D240", "F240");
	  
	  //点击选择文件按钮
	  G.ClickExcelItem("D241", "F241");
	  
	  //TODO 选择AutoIT文件路径
	  G.ExecuteExelExE("F242");
	  
	  //TODO 输入确认提交密码
      G.ClearExcelValue("D243", "F243");
      G.InputExcelValue("D243", "F243", "F244");
      
	  //点击保存并提交
	  G.ClickExcelItem("D245", "F245");
	  
	  //点击弹窗确定
	  G.PopupAction_Ok();
	  
	  //等待15秒
	  G.ForcedWait(15000);
	  
	  //设置检查点(一)
	  G.CheckeExcelQual(G.GetExcelText("D246", "F246"), "F248", "F249","Case_12");
	  
	  //设置检查点(二)
	  G.CheckeExcelQual(G.GetExcelText("D250", "F250"), "F252", "F253","Case_12");
	  
	  //设置检查点(三)
	  G.CheckeExcelQual(G.GetExcelText("D254", "F254"), "F256", "F257","Case_12");
  }
  
  @Test
  public void Case_13() throws Exception{G.TestReportRemarks("验证在合同列表界面,点击对应合同号信息,可以自动跳转至消费贷款申请表界面");
 
      //点击合同号
	  G.ClickExcelItem("D258", "F258");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //切换到当前最新窗口
	  G.SwitchToCurrentNewestWindow();
	  
	  //设置检查点(一)
	  G.CheckeExcelQual(G.GetExcelText("D259", "F259"), "F261", "F262","Case_13");
	  
	  //设置检查点(二)
	  G.CheckeExcelQual(G.GetExcelText("D263", "F263"), "F265", "F266","Case_13");
	  
	  //设置检查点(三)
	  G.CheckeExcelQual(G.GetExcelText("D267", "F267"), "F269", "F270","Case_13");
  }
  
  @Test
  public void Case_14() throws Exception{G.TestReportRemarks("验证在消费贷款申请表界面,点击提供的申请材料后选项按钮,选择对应图片信息点击确认补传后,可以正常补传保存图片");
 
	  //点击提供的申请材料选项按钮
	  G.ClickExcelItem("D271", "F271");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //切换到当前最新窗口
	  G.SwitchToCurrentNewestWindow();
	  
	  //PgDn向下滑动
	  G.KeyboardGeneralButton(Keys.PAGE_DOWN);
	  
	  //点击上传客户社保卡照片按钮
	  G.ClickExcelItem("D272", "F272");
	  
	  //点击选择文件按钮
	  G.ClickExcelItem("D273", "F273");
	  
	  //TODO 选择AutoIT文件路径
	  G.ExecuteExelExE("F274");
	  
	  //点击确认补传
	  G.ClickExcelItem("D275", "F275");
	  
	  //等待5秒
	  G.ImplicitWait(5000);
	  
	  //关闭当前窗口
	  G.KeyboardCombinationCommonKey(Keys.CONTROL, "w");
	  
	  //点击提供的申请材料选项按钮
	  G.ClickExcelItem("D271", "F271");
	  
	  //等待5秒
	  G.ImplicitWait(5000);

	  //切换到当前最新窗口
	  G.SpecialKeyForKeyboardCombination(Keys.CONTROL, Keys.TAB);
	  G.SwitchToCurrentNewestWindow();
	  
	  //设置检查点(一)
	  G.CheckeExcelQual(G.GetExcelText("D276", "F276"), "F278", "F279","Case_14");
  }
  
  @Test
  public void Case_15() throws Exception{G.TestReportRemarks("验证在消费贷款申请表界面,修改合同状态为通过且刷新当界面后,可以正常显示手机签章按钮");
  
      //合同信息查询
      Give.SQLStatementQuery("select * from cs_credit where ID_PERSON= 15125203 and STATUS='s'","查询当前用户合同信息成功！");
  
      //修改合同状态
      G.SQLStatementUpdate("update cs_credit set STATUS='n' where ID_PERSON= 15125203 and STATUS='s'","修改当前用户的待审合同为通过状态成功！");
          
      //切换到当前最新窗口
	  G.SpecialKeyForKeyboardCombination(Keys.CONTROL, Keys.TAB);
	  G.SpecialKeyForKeyboardCombination(Keys.CONTROL, Keys.TAB);
	  G.SwitchToCurrentNewestWindow();
	  
	  //刷新当前窗口
	  G.KeyboardGeneralButton(Keys.F5);
	  
	  //设置检查点(一)
	  G.CheckeExcelQual(G.GetExcelText("D280", "F280"), "F282", "F283","Case_15");
  }
  
  @Test
  public void Case_16() throws Exception{G.TestReportRemarks("验证在数据库中,输入SQL语句,可以正常查询对应的操作记录信息和蜜罐基本信息");
  
      //查询操作记录
      Give.SQLStatementQuery("select * from wfi_fraud_metrix_result where id =666666","查询当前用户操作记录信息成功！");
      
      //查询蜜罐基本信息
      Give.SQLStatementQuery("select * from external_miguan_basic_info where user_id =666666","查询当前用户蜜罐基本信息成功！");
  }
  
  @AfterTest
  public void End() {
	  
	  G.Teardown();
  }
}