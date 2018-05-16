package com.wzu.model;

import java.util.ArrayList;

public interface CommentDao {
	public abstract boolean FindComment(String uid,String aid);
	public abstract boolean AddComment(String uid,String aid,String text,String num);
	public abstract ArrayList<Comment> FindCommentByActID(int aid);
	
	public abstract boolean DeletebyID(String userid);
	
	public abstract boolean DeletebyactID(String actid);
}
