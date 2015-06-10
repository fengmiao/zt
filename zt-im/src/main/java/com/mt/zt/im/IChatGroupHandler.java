package com.mt.zt.im;

import com.mt.zt.im.model.ImChatGroupModel;

public interface IChatGroupHandler {
	
	/**
	 * 建群
	 * @param group
	 */
	public String addChatGroup(ImChatGroupModel group);
	
	
	/**
	 * 增加成员
	 * @param groupId
	 * @param userName
	 */
	public void addMember(String groupId,String userName);
	
	
	/**
	 * 删除成员
	 * @param groupId
	 * @param userName
	 */
	public void deleteMember(String groupId,String userName);

	/**
	 * 增加成员
	 * @param roomId
	 * @param userIds
	 */
	public void addChatGroupMembers(String groupId, String[] userIds);
	
	/**
	 * 修改群名
	 * @param groupId
	 * @param group
	 */
	public void updateChatGroupName(String groupId, String name);

	/**
	 * 修改群最大人数
	 * @param groupId
	 * @param maxusers
	 */
	public void updateChatGroupMaxusers(String groupId, Integer maxusers);
}
