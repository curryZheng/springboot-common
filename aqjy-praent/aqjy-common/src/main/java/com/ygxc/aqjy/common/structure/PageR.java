package com.ygxc.aqjy.common.structure;

/**
 * 
 * @author leiZheng  
 * @date 2019年6月21日
 */
public class PageR <T> extends R<T>{

	private long total;
	private int pages;
	private long current;
	private long pageSize;
	public long getTotal() {
		return total;
		
	}
	
	/**
	 * 默认操作成功
	 * @author leiZheng  
	 * @date 2019年6月18日
	 */
	public PageR(){
		this.setInfo(RConst.SUCCESS_INFO);
		this.setCode(RConst.SUCCESS_CODE);
	}
	public void setTotal(long total) {
		this.total = total;
		 this.total = total;
	        if (total == -1) {
	            pages = 1;
	            return;
	        }
	        if (pageSize > 0) {
	            pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
	        } else {
	            pages = 10;
	        }
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public long getCurrent() {
		return current;
	}
	public void setCurrent(long current) {
		this.current = current;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
