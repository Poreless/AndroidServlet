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
	            System.out.println("���=="+activelist.get(i).getActive_name()+"\n"+
	            "��ص�=="+activelist.get(i).getActive_place()+"\n"+
	            "����ʱ��=="+activelist.get(i).getCreate_time()+"~~"+activelist.get(i).getFinish_time()+"\n"+
                "������=="+activelist.get(i).getUser().getUserName()+"\n");

	}*/
/*	@Test
	public void test2() {
		 Active active = new Active();
	     active = activeDaoImp.FindActiveByID("4");
	     System.out.println("���ҵ����");
        System.out.println("���=="+active.getActive_name()+"\n"+
        "��ص�=="+active.getActive_place()+"\n"+
        "����ʱ��=="+active.getCreate_time()+"~~"+active.getFinish_time()+"\n"+
        "������=="+active.getUser().getUserName()+"\n");

	}*/
	/*	@Test
	public void test3() {
		Boolean key2 = activeDaoImp.ActiveCreate("14211160104",
				"������������", "�緹","XXʳ��" ,"ѧ�����չ�^-^",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "3");
		if(key2 == true){
			System.out.println("����ɹ���");
		}else{
			System.out.println("����ʧ�ܣ�");
		}
	}*/
	@Test
	public void test4() {
		Boolean key2 = activeDaoImp.ActChangeByID( "32","�ȡ����", "�۲�", "���µص���", "����", 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "4");
	}
/*	@Test
	public void test3() {
		if(activeDaoImp.ActiveFinish("5")==true){
			System.out.println("");
		}

	}*/
}
