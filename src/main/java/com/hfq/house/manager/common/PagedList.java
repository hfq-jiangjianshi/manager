package com.hfq.house.manager.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;


public class PagedList<T> implements Serializable {
	
	private static final long serialVersionUID = 1;

	private int page = 1; // 当前页号，从1开始
	private int pageSize = 20; // 每页数量
	private int totalPages = 1; // 总页数
	private long total = 0; // 总记录数
	private List<T> rows; // 数据集合

	private PagedList() {

	}

	public static <T> PagedList<T> newMe() {
		PagedList<T> result = new PagedList<>();
		result.rows = new ArrayList<T>();
		return result;
	}
	
	public static <T> PagedList<T> newMe(int page, int pageSize, long total, List<T> rows) {
		PagedList<T> result = new PagedList<>();
		result.page = page;
		result.pageSize = pageSize;
		result.total = total;
		result.totalPages = (int) Math.ceil(((double) total) / pageSize);
		if (rows != null) {
			result.rows = rows;
		} else {
			result.rows = new ArrayList<T>();
		}
		return result;
	}
	
	public static <T> PagedList<T> newMe(PageInfo<T> pageInfo) {
		PagedList<T> result = new PagedList<>();
		result.page = pageInfo.getPageNum();
		result.pageSize = pageInfo.getPageSize();
		result.total = pageInfo.getTotal();
		result.totalPages = pageInfo.getPages();
		if (pageInfo.getList() != null) {
			result.rows = pageInfo.getList();
		} else {
			result.rows = new ArrayList<T>();
		}
		return result;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
