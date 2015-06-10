package com.mt.zt.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mt.zt.service.IUserLoginService;
import com.mt.zt.vo.entry.UserLoginEo;
import com.mt.zt.vo.view.RspResultVo;
import com.mt.zt.vo.view.UserTokenVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "entrants", description = "登录 API")
@RequestMapping("/entrants")
@RestController
public class EntrantController {
	private static final Logger logger = LoggerFactory.getLogger(EntrantController.class);

	@Autowired
	private IUserLoginService userLoginService;


	@RequestMapping(value = "/others", method = RequestMethod.POST)
	@ApiOperation(value = "第三方登陆", notes = "第三方登陆")
	public RspResultVo<UserTokenVo> addOAuth(@RequestBody UserLoginEo user,
			HttpServletRequest request) {
		RspResultVo<UserTokenVo> r = new RspResultVo<UserTokenVo>();
		UserTokenVo ut = userLoginService.loginUser(user);
		r.addOneEntity(ut);
		return r;
	}
	
	

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ApiOperation(value = "用户退出", notes = "用户退出")
	public RspResultVo<Void> deleteEntrant(@PathVariable String userId,
			HttpServletRequest request) {
		RspResultVo<Void> r = new RspResultVo<Void>();
		userLoginService.loginOut(userId);
		return r;
	}

}
