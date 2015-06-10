package com.feixun.jd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mt.zt.config.ImApiConstant;
import com.mt.zt.im.IChatGroupHandler;
import com.mt.zt.im.IManageHandler;
import com.mt.zt.im.IUserHandler;
import com.mt.zt.im.model.ImChatGroupModel;
import com.mt.zt.im.model.ImUserModel;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = ImApiConstant.class)
public class ImTest {

	@Autowired
	private IUserHandler userHandler;
	
	@Autowired
	private IChatGroupHandler chatGroupHandler;
	
	@Autowired
	private IManageHandler manageHandler;
	
	
	@Test
	public void testQueryToken(){
		String token = manageHandler.queryManagerToken();
		System.out.println(token);
	}
	
	
	@Test
	public void testAddUser(){
		ImUserModel u = new ImUserModel();
		//u.setNickname("xl1111122");
		u.setUsername("testqqqqq");
		u.setPassword("pgaalsgbgbg");
		userHandler.addUser(u);
	}
	
	@Test
	public void testUpdateUserPassword(){
		userHandler.updateUserPassword("testqqqqq", "pgaalsggg");
	}
	
	@Test
	public void testUpdateUserNickName(){
		userHandler.updateUserNickName("testqqqqq", "ttt");
	}
	
	@Test
	public void testAddFriend(){
		userHandler.addFriend("feng1", "feng2");
		//userHandler.deleteFriend("testqqqqq", "aa");
		//userHandler.updateUserNickName("testqqqqq", "ttt");
	}
	
	@Test
	public void testAddBlocks(){
		userHandler.addBlocks("testqqqqq", new String[]{"aa"});
		//userHandler.deleteBlocks("testqqqqq", "aa");
	}
	
	@Test
	public void testUpdateChatGroup(){
		String groupId = "1414475238237083";
		chatGroupHandler.updateChatGroupName(groupId, "xxggg");
		//userHandler.deleteBlocks("testqqqqq", "aa");
	}
	
	
	
	@Test
	public void testAddGroup(){
		ImChatGroupModel model = new ImChatGroupModel();
		model.setGroupname("bbbb");
		model.setDesc("gaxggggg");
		model.setPublic(true);
		model.setMaxusers(300);
		model.setOwner("aa");
		//String aa = chatGroupHandler.addChatGroup(model);
		//System.out.println(aa);
		//"1419406563802387"
		//chatGroupHandler.addMember("1419406563802387", "testqqqqq");
		chatGroupHandler.deleteMember("1419406563802387", "testqqqqq");
	}
	
	@Test
	public void testGetUser(){
		//userHandler.getUser("a1");
		//userHandler.deleteUser("a2");
		userHandler.getUser("a4");
	}
	
	@Test
	public void testDeleteUser(){
		String imAccount = "imAccount";
		userHandler.deleteUser(imAccount);
	}
	
	@Test
	public void testAddChatGroupMembers(){
		//userHandler.getUser("a1");
		//userHandler.deleteUser("a2");
		String[] userIds = new String[]{"ssg","ssh"};
		String groupId = "1414475238237083";
		chatGroupHandler.addChatGroupMembers(groupId, userIds);
	}
	
}
