package TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dafycredit.giveu.test.base.TestUnit;
import com.dafycredit.giveu.test.util.TestXmlHelper;

public class TestUnitDemo {
	
	private TestUnit testUnit;
	
	@BeforeTest
	private void stup() throws Exception{
		testUnit = TestXmlHelper.parse("TestUnit.xml");
//		testUnit.runCase("前置条件");
//		testUnit.TestReportRemarks("修改当前用户所有合同为取消状态成功！");
	}
	
//	@Test
//	public void case0() throws Exception{
//		testUnit.runCase("case0");
//		testUnit.TestReportRemarks("验证在数据库中,修改当前用户所有合同为取消状态后,可以正常修改成功");
//		
//	}
	
	@Test
	public void case1() throws Exception{
		testUnit.runCase("case1");
		testUnit.TestReportRemarks("验证在火狐浏览器中,输入达飞金融销售提单系统域名后,可以正常访问");
	}
	
	@Test
	public void case2() throws Exception{
		testUnit.runCase("case2");
		testUnit.TestReportRemarks("验证在登录界面,输入正确的账号和密码,点击登录按钮后,可以正常跳转至首页主界面");
	}
	
	@Test
	public void case3() throws Exception{
		testUnit.runCase("case3");
		testUnit.TestReportRemarks("验证在选择产品界面,选择输入对应产品信息,点击搜索按钮后,可以正常搜索且自动弹出产品列表界面");
	}

	@Test
	public void case4() throws Exception{
		testUnit.runCase("case4");
		testUnit.TestReportRemarks("验证在产品信息界面,选择点击对应产品信息后,可以正常选择且自动弹出商品信息和身份证信息界面");
	}
	
	@Test
	public void case5() throws Exception{
		testUnit.runCase("case5");
		testUnit.TestReportRemarks("验证在商品信息和身份证信息界面,选择输入对应商品信息和身份证信息,点击保存按钮后,可以正常保存且自动弹出地址信息界面");
	}
	
	@Test
	public void case6() throws Exception{
		testUnit.runCase("case6");
		testUnit.TestReportRemarks("验证在地址信息界面,选择输入对应地址信息,点击保存按钮后,可以正常保存且自动弹出个人信息界面");
	}
	
	@Test
	public void case7() throws Exception{
		testUnit.runCase("case7");
		testUnit.TestReportRemarks("验证在个人信息界面,选择输入对应个人信息,点击保存按钮后,可以正常保存且自动弹出银行卡信息界面");
	}
	
	@Test
	public void case8() throws Exception{
		testUnit.runCase("case8");
		testUnit.TestReportRemarks("验证在银行卡信息界面,选择输入对应银行卡信息,点击保存按钮后,可以正常保存且自动弹出家庭信息界面");
	}
	
	@Test
	public void case9() throws Exception{
		testUnit.runCase("case9");
		testUnit.TestReportRemarks("验证在家庭信息界面,选择输入对应家庭信息,点击保存按钮后,可以正常保存且自动弹出其他联系人信息界面");
	}
	
	@Test
	public void case_10() throws Exception{
		testUnit.runCase("case_10");
		testUnit.TestReportRemarks("验证在其他联系人信息界面,选择输入对应其他联系人信息,点击保存按钮后,可以正常保存且自动弹出其他信息界面");
	}
	
	@Test
	public void case_11() throws Exception{
		testUnit.runCase("case_11");
		testUnit.TestReportRemarks("验证在其他界面,选择输入对应其他信息,点击保存和下一步按钮后,可以正常保存且自动跳转至上传文件界面");
	}
	
	@Test
	public void case_12() throws Exception{
		testUnit.runCase("case_12");
		testUnit.TestReportRemarks("验证在上传文件界面,选择输入对应图片文件信息,点击保存并提交按钮后,可以正常保存提交且自动跳转至合同列表界面");
	}
	
	@Test
	public void case_13() throws Exception{
		testUnit.runCase("case_13");
		testUnit.TestReportRemarks("验证在合同列表界面,点击对应合同号信息,可以自动跳转至消费贷款申请表界面");
	}
	
	@Test
	public void case_14() throws Exception{
		testUnit.runCase("case_14");
		testUnit.TestReportRemarks("验证在消费贷款申请表界面,点击提供的申请材料后选项按钮,选择对应图片信息点击确认补传后,可以正常补传保存图片");
	}
	
	@Test
	public void case_15() throws Exception{
		testUnit.runCase("case_15");
		testUnit.TestReportRemarks("验证在消费贷款申请表界面,修改合同状态为通过且刷新当界面后,可以正常显示手机签章按钮");
	}
	
	@Test
	public void case_16() throws Exception{
		testUnit.runCase("case_16");
		testUnit.TestReportRemarks("验证在数据库中,输入SQL语句,可以正常查询对应的操作记录信息和蜜罐基本信息");
	}
	
	@AfterTest
	public void after(){
		testUnit.close();
	}
}
