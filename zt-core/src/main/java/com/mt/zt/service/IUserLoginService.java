package com.mt.zt.service;

import com.mt.zt.vo.entry.UserLoginEo;
import com.mt.zt.vo.view.UserTokenVo;

public interface IUserLoginService {

	public UserTokenVo loginUser(UserLoginEo user);

	public void loginOut(String userId);

}
