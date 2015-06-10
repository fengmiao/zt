package com.mt.zt.mongodb.repository;

import com.mt.zt.mongodb.model.UserInfo;

public interface IUserRepository {

	public UserInfo findByIdentification(String identification);

	public UserInfo saveUserInfo(UserInfo b);

}
