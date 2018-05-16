package com.wzu.db;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.wzu.model.ModelRequest;

public class ActiveRequestDaoImpTest {
	private static ActiveRequestDaoImp activeDaoImp = new ActiveRequestDaoImp();
	ArrayList<ModelRequest> ListReq = new ArrayList();
	ArrayList<String> IDList = new ArrayList();
/*	@Test
	public void test() {
		//activeDaoImp.AddActiveRequest("14211160152", "4");
		//System.out.println(activeDaoImp.RequestSuccessNum("4"));
		ListReq = activeDaoImp.RequestByActID("4");
		for(int i=0;i<ListReq .size();i++){
			System.out.println("Active_id:"+ListReq .get(i).getActive_id());
			System.out.println("User_id:"+ListReq .get(i).getUser_id());
			System.out.println("Date:"+ListReq .get(i).getDate());
			System.out.println("num:"+ListReq .get(i).getNum());
		}
	}*/
	@Test
	public void test2() {
		//activeDaoImp.AddActiveRequest("14211160152", "4");
		//System.out.println(activeDaoImp.RequestSuccessNum("4"));
		//System.out.println(activeDaoImp.SearchNum("4", "14211160115"));
		//System.out.println(activeDaoImp.ChangeRequestNum("4", "14211160115", "1"));
		IDList = activeDaoImp .ReqSuccessByUserID("14211160106");
		for(int i=0;i<IDList.size();i++){
			System.out.println("Active_id:"+IDList.get(i));
		}
	}

}
