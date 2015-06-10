package com.mt.zt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mt.zt.service.ITestService;
import com.mt.zt.service.IUserLoginService;
import com.mt.zt.vo.entry.TestBeanEo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class TestTest {

	@Autowired
	private ITestService testService;
	
	@Autowired
	private IUserLoginService userLoginService;
	
	@Test
	public void test(){
		TestBeanEo t = new TestBeanEo();
		t.setAge("17");
		t.setName("aa");
		testService.save(t);
	}
	
	
	
}
