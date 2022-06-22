package com.app.dataentry.report;

import java.util.ArrayList;

public class PageList {

	private ArrayList<Page> pageList = new ArrayList<>();

	public ArrayList<Page> getPageList() {
		return pageList;
	}

	public void addPage(Page page) {
		pageList.add(page);

	}
}
