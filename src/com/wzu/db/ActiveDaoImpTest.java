package com.wzu.db;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.wzu.model.Active;

public class ActiveDaoImpTest {
	private static ActiveDaoImp activeDaoImp = new ActiveDaoImp();
/*	@Test
	public void test() {
		 ArrayList<Active> activelist = new ArrayList<Active>();
	        activelist = activeDaoImp.FindFreshByUserID("14211160102");
	        for(int i = 0; i < activelist.size(); i++)
	            System.out.println("活动名=="+activelist.get(i).getActive_name()+"\n"+
	            "活动地点=="+activelist.get(i).getActive_place()+"\n"+
	            "持续时间=="+activelist.get(i).getCreate_time()+"~~"+activelist.get(i).getFinish_time()+"\n"+
                "创建人=="+activelist.get(i).getUser().getUserName()+"\n");

	}*/
/*	@Test
	public void test2() {
		 Active active = new Active();
	     active = activeDaoImp.FindActiveByID("4");
	     System.out.println("查找单个活动");
        System.out.println("活动名=="+active.getActive_name()+"\n"+
        "活动地点=="+active.getActive_place()+"\n"+
        "持续时间=="+active.getCreate_time()+"~~"+active.getFinish_time()+"\n"+
        "创建人=="+active.getUser().getUserName()+"\n");

	}*/
	/*	@Test
	public void test3() {
		Boolean key2 = activeDaoImp.ActiveCreate("14211160104",
				"新人来报道了", "午饭","XX食堂" ,"学长求照顾^-^",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "3");
		if(key2 == true){
			System.out.println("插入成功！");
		}else{
			System.out.println("插入失败！");
		}
	}*/
	@Test
	public void test4() {
		Boolean key2 = activeDaoImp.ActChangeByID( "32","活动取消了", "聚餐", "换新地点了", "如题", 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "4");
	}
/*	@Test
	public void test3() {
		if(activeDaoImp.ActiveFinish("5")==true){
			System.out.println("");
		}

	}*/
}
