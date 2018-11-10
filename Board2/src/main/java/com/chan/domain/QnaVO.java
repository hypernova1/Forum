package com.chan.domain;

import java.util.Date;

public class QnaVO {

	private Integer qno;
	private Integer groupno;
	private Integer seq;
	private Integer depth;
	private String title;
	private String content;
	private Integer mno;
	private Date regdate;
	private Integer recommend;
	private Integer view;
	private Integer commend;
	private Integer del;
	
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	public Integer getQno() {
		return qno;
	}
	public void setQno(Integer qno) {
		this.qno = qno;
	}
	
	public Integer getGroupno() {
		return groupno;
	}
	public void setGroupno(Integer groupno) {
		this.groupno = groupno;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
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
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
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
	public Integer getView() {
		return view;
	}
	public void setView(Integer view) {
		this.view = view;
	}
	public Integer getCommend() {
		return commend;
	}
	public void setCommend(Integer commend) {
		this.commend = commend;
	}
	
	@Override
	public String toString() {
		return "QnaVO [qno=" + qno + ", groupno=" + groupno + ", seq=" + seq + ", depth=" + depth + ", title=" + title
				+ ", content=" + content + ", mno=" + mno + ", regdate=" + regdate + ", recommend=" + recommend
				+ ", view=" + view + ", commend=" + commend + "]";
	}
}
