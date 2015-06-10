package com.mt.zt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.zt.mongodb.model.TestBean;
import com.mt.zt.mongodb.repository.ITestBeanRepository;
import com.mt.zt.service.ITestService;
import com.mt.zt.vo.entry.TestBeanEo;
import com.mt.zt.vo.view.TestBeanVo;

@Service("testService")
public class TestServiceImpl implements ITestService {

	@Autowired
	private ITestBeanRepository testBeanRepository;
	
	@Override
	public void save(TestBeanEo bean) {
		TestBean t = new TestBean();
		t.setName(bean.getName());
		t.setAge(bean.getAge());
		testBeanRepository.saveTestBean(t);
	}

	@Override
	public TestBeanVo query(String testId) {
		TestBean t = testBeanRepository.findOne(testId);
		TestBeanVo v = new TestBeanVo();
		v.setAge(t.getAge());
		v.setName(t.getName());
		return v;
	}

}
