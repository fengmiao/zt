package com.mt.zt.im;

import com.mt.zt.im.model.ImUserModel;

public interface IUserHandler {
	
	/**
	 * 获取用户
	 * @param imAccount
	 * @return
	 */
	public ImUserModel getUser(String imAccount);
	
	/**
	 * 注册用户
	 * @param user
	 */
	public void addUser(ImUserModel user);
	
	/**
	 * 删除用户
	 * @param imAccount
	 */
	public void deleteUser(String imAccount);
	
	/**
	 * 更新用户密码
	 * @param userName
	 * @param password
	 */
	public void updateUserPassword(String userName,String password);
	
	/**
	 * 修改用户昵称
	 * @param userName
	 * @param nickName
	 */
	public void updateUserNickName(String userName,String nickName);
	
	
	/**
	 * 增加朋友
	 * @param userName
	 * @param friendName
	 */
	public void addFriend(String userName,String friendName);
	
	/**
	 * 删除朋友
	 * @param userName
	 * @param friendName
	 */
	public void deleteFriend(String userName,String friendName);
	
	/**
	 * 加入黑名单
	 * @param userName
	 * @param friendName
	 */
	public void addBlocks(String userName, String[] friendNames);
	
	/**
	 * 移除黑名单
	 * @param userName
	 * @param friendName
	 */
	public void deleteBlocks(String userName,String friendName);
	
}
