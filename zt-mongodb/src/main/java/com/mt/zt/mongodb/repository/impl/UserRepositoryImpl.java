package com.mt.zt.mongodb.repository.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mt.zt.mongodb.model.UserInfo;
import com.mt.zt.mongodb.repository.IUserRepository;

@Repository("userRepository")
public class UserRepositoryImpl  extends BaseRepositoryImpl<UserInfo, String> implements IUserRepository {

	@Override
	public UserInfo findByIdentification(String identification) {
		Query query = new Query().addCriteria(Criteria.where("identification").is(identification));
		return mongoTemplate.findOne(query, UserInfo.class,"user_info");
	}

	@Override
	public UserInfo saveUserInfo(UserInfo b) {
		mongoTemplate.save(b, "user_info");
		return b;
	}



}
