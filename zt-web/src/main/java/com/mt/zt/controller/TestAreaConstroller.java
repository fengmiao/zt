package com.mt.zt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mt.zt.service.ITestService;
import com.mt.zt.vo.entry.TestBeanEo;
import com.mt.zt.vo.view.RspResultVo;
import com.mt.zt.vo.view.TestBeanVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "test", description = "API 测试")
@RequestMapping("/test")
@RestController
public class TestAreaConstroller {
	
	@Autowired
	private ITestService testService;

	@RequestMapping(value = "/{testId}", method = RequestMethod.GET)
	@ApiOperation(value = "测试", notes = "测试")
	public RspResultVo<TestBeanVo> get(@PathVariable String testId) {
		RspResultVo<TestBeanVo> r = new RspResultVo<TestBeanVo>();
		TestBeanVo vo = testService.query(testId);
		r.addOneEntity(vo);
		return r;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "测试post", notes = "测试post")
	public RspResultVo<Void> post(@RequestBody TestBeanEo test) {
		RspResultVo<Void> r = new RspResultVo<Void>();
		testService.save(test);
		return r;
	}
	
}
