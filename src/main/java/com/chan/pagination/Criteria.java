package com.chan.pagination;

public class Criteria {

	private Integer page;
	private Integer perPageNum;
	private String keyword;
	private Integer searchType;
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPerPageNum(Integer perPageNum) {
		this.perPageNum = perPageNum;
	}

	{
		this.page = 1;
		this.perPageNum = 20;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public Integer getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 20;
			return;
		}
		this.perPageNum = perPageNum;
	}

	public Integer getPageStart() {
		return (this.page - 1) * perPageNum;
	}

}
