package com.mt.zt.service;

import com.mt.zt.vo.entry.TestBeanEo;
import com.mt.zt.vo.view.TestBeanVo;


public interface ITestService {
	
	public void save(TestBeanEo bean);

	public TestBeanVo query(String testId);

}
