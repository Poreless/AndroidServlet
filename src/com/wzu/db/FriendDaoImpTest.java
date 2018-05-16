package com.wzu.db;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.wzu.model.Friend;

public class FriendDaoImpTest {
	private static FriendDaoImp friendDaoImp = new FriendDaoImp();
	ArrayList<String> fidlist = new ArrayList<String>();
	ArrayList<Friend> list = new ArrayList();
	@Test
	public void test() {
/*		list = friendDaoImp.RequestByUserID("14211160105");
		 for (int i = 0; i<list.size(); i++) {
			              System.out.println(list.get(i).getId());
		  }*/
		System.out.println(friendDaoImp.ChangeFriendRequestNum("14211160109","14211160110","1"));
	}

}
