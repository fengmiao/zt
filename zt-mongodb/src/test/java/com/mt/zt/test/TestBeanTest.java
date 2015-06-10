package com.mt.zt.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mt.zt.configuration.MongoDbConfiguration;

@ContextConfiguration(classes = {MongoDbConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBeanTest {

	//@Autowired
	//private ITestBeanRepository testBeanRepository;
	
	/**
	@Test
	public void testAA(){
		TestBean tb = new TestBean();
		tb.setName("aa");
		tb.setId("551520f7e413b19f2763f2ce");
		testBeanRepository.save(tb);
	}
	**/
	
}
