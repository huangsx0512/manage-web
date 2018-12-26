package com.zimmur.platform.manage.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PageUtil<E> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNo=1; // 当前页码
	private int totalRecord; // 总记录数量
	private int totalPage; // 总页数
	private Integer pageSize = 10; // 每页记录数
	private int nextPage; // 下一页
	private int prevPage; // 上一页
	private List<E> itemList = new ArrayList<>(); // 每页记录集合
	private int pageBeginNo; // 标记
	
	// 当前页码
		public int getPageNo() {
			if (pageNo < 1) {
				pageNo = 1;
			}
			return pageNo;
		}

		public void setPageNo(int pageNo) {

			this.pageNo = pageNo;
		}

		// 总记录条数
		public int getTotalRecord() {
			
			return totalRecord;
		}

		public void setTotalRecord(int totalRecord) {
			
			this.totalPage=(totalRecord%pageSize==0?totalRecord/pageSize:(totalRecord/pageSize)+1);
			this.totalRecord = totalRecord;
		}

		// 总页数
		public int getTotalPage() {
			return totalPage;
		}



		// 每页总记录条数
		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}

		// 下一页
		public int getNextPage() {
			if (pageNo >= getTotalPage()) {
				nextPage = getTotalPage();
			} else {
				nextPage = pageNo + 1;
			}
			return nextPage;
		}

		public void setNextPage(int nextPage) {
			this.nextPage = nextPage;
		}

		// 上一页
		public int getPrevPage() {
			if (getPageNo() <= 1) {
				prevPage = 1;
			} else {
				prevPage = getPageNo() - 1;
			}
			return prevPage;
		}

		public void setPrevPage(int prevPage) {
			this.prevPage = prevPage;
		}

		// 每页记录存储集合
		public List<E> getItemList() {
			return itemList;
		}

		public void setItemList(List<E> itemList) {
			this.itemList = itemList;
			//totalRecord = this.itemList.size();
		}

		// 标记
		public int getPageBeginNo() {
			pageBeginNo = (getPageNo() - 1) * pageSize;
			return pageBeginNo;
		}

		public void setPageBeginNo(int pageBeginNo) {
			this.pageBeginNo = pageBeginNo;
		}


		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		
}
