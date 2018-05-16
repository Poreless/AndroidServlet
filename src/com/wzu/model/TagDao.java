package com.wzu.model;

import java.util.ArrayList;

public interface TagDao {
	public abstract Boolean TagCreate(String uid,String tag);
	public abstract ArrayList<String> TagFindByID(String uid);
	
	public abstract boolean DeletebyID(String userid);
	
}
