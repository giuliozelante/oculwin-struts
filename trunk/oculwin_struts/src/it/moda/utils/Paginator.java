package it.moda.utils;

public class Paginator {
	private int page;
	private int rowNums;
	private int totRows;
	private int numPages;
	
	public Paginator() {
	}
	public Paginator(int page,int rowNums,int totRows) {
		this.page=page;
		this.rowNums=rowNums;
		this.totRows=totRows;
		this.numPages=totRows%2==1?totRows/rowNums+1:totRows/rowNums;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowNums() {
		return rowNums;
	}
	public void setRowNums(int rowNums) {
		this.rowNums = rowNums;
	}
	public int getTotRows() {
		return totRows;
	}
	public void setTotRows(int totRows) {
		this.totRows = totRows;
	}
	public int getNumPages() {
		return numPages;
	}
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
}
