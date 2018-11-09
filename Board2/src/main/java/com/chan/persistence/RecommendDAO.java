package com.chan.persistence;

public interface RecommendDAO {

	public void insert(Integer bno, Integer mno, Integer type);
	public int select(Integer bno, Integer mno, Integer type);
}
