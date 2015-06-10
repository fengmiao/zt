package com.mt.zt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.zt.im.IUserHandler;
import com.mt.zt.im.model.ImUserModel;
import com.mt.zt.mongodb.model.UserInfo;
import com.mt.zt.mongodb.model.ZoneInfo;
import com.mt.zt.mongodb.repository.IUserRepository;
import com.mt.zt.mongodb.repository.IZoneRepository;
import com.mt.zt.service.IUserLoginService;
import com.mt.zt.vo.entry.UserLoginEo;
import com.mt.zt.vo.view.UserTokenVo;

@Service("userLoginService")
public class UserLoginServiceImpl  implements IUserLoginService{

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IZoneRepository zoneRepository;
	
	@Autowired
	private IUserHandler userHandler;
	
	@Override
	public UserTokenVo loginUser(UserLoginEo user) {
		UserTokenVo result = new UserTokenVo();
		ZoneInfo zone = zoneRepository.findByWifiMac(user.getLinkWifiMac());
		if(zone != null){
			result.setImGroup(zone.getImGroup());
		}
		UserInfo u = userRepository.findByIdentification(user.getIdentification());
		if (u == null) {
			UserInfo b = new UserInfo();
			b.setIdentification(user.getIdentification());
			b.setLinkWifiMac(user.getLinkWifiMac());
			b.setLinkWifiSsid(user.getLinkWifiMac());
			u = userRepository.saveUserInfo(b);
			ImUserModel um = new ImUserModel();
			um.setNickname(user.getNickName());
			um.setPassword(user.getIdentification());
			um.setUsername(user.getIdentification());
			userHandler.addUser(um);
		}
		copyProperties(u, result);
		return result;
	}

	private void copyProperties(UserInfo u,UserTokenVo v){
		v.setNickName(u.getNickName());
		v.setUserId(u.getUserId());
		v.setImAccount(u.getImAccount());
		v.setImPassword(u.getImAccount());
	}
	
	@Override
	public void loginOut(String userId) {
		
	}

}
