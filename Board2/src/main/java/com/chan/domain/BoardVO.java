package com.chan.domain;

import java.util.Date;

public class BoardVO {

	private Integer bno;
	private Integer originno;
	private Integer groupord;
	private Integer grouplayer;
	private String title;
	private String content;
	private Integer writer;
	private Integer view;
	private Integer recommend;
	private Integer type;
	private Integer comment;
	
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	private Date regdate;
	
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getWriter() {
		return writer;
	}
	public void setWriter(Integer writer) {
		this.writer = writer;
	}
	public Integer getView() {
		return view;
	}
	public void setView(Integer view) {
		this.view = view;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getOriginno() {
		return originno;
	}
	public void setOriginno(Integer originno) {
		this.originno = originno;
	}
	public Integer getGroupord() {
		return groupord;
	}
	public void setGroupord(Integer groupord) {
		this.groupord = groupord;
	}
	public Integer getGrouplayer() {
		return grouplayer;
	}
	public void setGrouplayer(Integer grouplayer) {
		this.grouplayer = grouplayer;
	}
	
	
	
}
