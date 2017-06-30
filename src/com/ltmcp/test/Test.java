package com.ltmcp.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ltmcp.condition.MailCondition;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.mobile.action.SaveCodeAction;
import com.ltmcp.mobile.biz.impl.MailMobileBizImpl;

public class Test {
	public static void main(String[] args) {
		
		SaveCodeAction sa = new  SaveCodeAction();
		sa.setBagCode1("bcxsdfsd");
		//利用对象来处理否则测试不会成功，并且数据插入到Dbc_BagCodeBind表中
		Dbc_BagCodeBind dbcb = new Dbc_BagCodeBind();
		dbcb.setBag_code("bcxsdfsd");
		sa.boxCodeBind();
		
	}

}
