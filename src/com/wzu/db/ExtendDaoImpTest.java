package com.wzu.db;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.wzu.model.Extend;

public class ExtendDaoImpTest {
	private static ExtendDaoImp extendDaoImp = new ExtendDaoImp();
	@Test
	public void test() {
        ArrayList<Extend> extendlist = new ArrayList<Extend>();
        extendlist = extendDaoImp.FindAllExtend();
        for(int i = 0; i < extendlist.size(); i++){
        	System.out.println("广告名："+extendlist.get(i).getExtend_name()+"图片URL："+extendlist.get(i).getIcon_url()+"\n"+"链接URL："+extendlist.get(i).getWeb_url()+"\n");
        }
	}

}
