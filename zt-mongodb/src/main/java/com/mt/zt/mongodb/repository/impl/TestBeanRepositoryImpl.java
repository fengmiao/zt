package com.mt.zt.mongodb.repository.impl;



import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mt.zt.mongodb.model.TestBean;
import com.mt.zt.mongodb.repository.ITestBeanRepository;

@Repository("testBeanRepository")
public class TestBeanRepositoryImpl extends BaseRepositoryImpl<TestBean, String> implements ITestBeanRepository {

	
	
	@Override
	public void saveTestBean(TestBean t) {
		mongoTemplate.save(t, "test_bean");
	}

	@Override
	public TestBean findOne(String testId) {
		Query query = getIdQuery(testId);
		return mongoTemplate.findOne(query, TestBean.class, "test_bean");
	}

	
	
}
