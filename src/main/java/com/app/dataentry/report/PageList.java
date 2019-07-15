package com.app.dataentry.report;

import java.util.ArrayList;

public class PageList {
	
	public ArrayList<Page> getPageList() {
		
		ArrayList<Page> pageList = new ArrayList<>();
		Page page1 = new Page();
		page1.setPhone1("1111");
		page1.setPhone2("2222");
		page1.setPhone3("3333");
		page1.setPhone4("4444");
		pageList.add(page1);
		
		Page page2 = new Page();
		page2.setPhone1("1111");
		page2.setPhone2("2222");
		page2.setPhone3("3333");
		page2.setPhone4("4444");
		pageList.add(page2);
		
		return pageList;
	}
}
