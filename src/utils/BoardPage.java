package utils;

import common.BoardConfig;

public class BoardPage implements BoardConfig{
	public static String pagingStr(int totalCount, int pageNum, String reqURI) {
		
		String pagingStr = "";
		int totalPage = (int)Math.ceil((double)totalCount/PAGE_PER_SIZE);
		int pageTemp = (((pageNum - 1) / PAGE_PER_BLOCK) * PAGE_PER_BLOCK) + 1;
		if(pageTemp != 1) {
			pagingStr += "<a href='" + reqURI + "?pageNum=1'>[첫페이지]</a>";
			pagingStr += "<a href='" + reqURI + "?pageNum=" + (pageTemp -1) + "'>[이전블럭]</a>";
		}
		int blockCount = 1;
		
		while(blockCount <= PAGE_PER_BLOCK && pageTemp <= totalPage) {
			if(pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			} 
			else {
				pagingStr += "&nbsp;<a href= '" + reqURI + "?pageNum=" + pageTemp + "'>"
						+ pageTemp + "</a>&nbsp;"; 
			}
			pageTemp++;
			blockCount++;
		}
		if(pageTemp <= totalPage) {
			pagingStr += "<a href='" + reqURI + "?pageNum=" + pageTemp + "'>"
					+ "[다음블럭]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqURI + "?pageNum=" + totalPage + "'>"
					+ "[마지막페이지]</a>";
		}
		return pagingStr;
	}
	
	public static String pagingImg(int totalCount, int pageNum, String reqURI) {
		
		String pagingStr = "";
		int totalPage = (int)Math.ceil((double)totalCount/PAGE_PER_SIZE);
		int pageTemp = (((pageNum - 1) / PAGE_PER_BLOCK) * PAGE_PER_BLOCK) + 1;
		if(pageTemp != 1) {
			pagingStr += "<a href='" + reqURI + "?pageNum=1'><img src='../images/paging1.gif'></a>";
			pagingStr += "<a href='" + reqURI + "?pageNum=" + (pageTemp -1) + "'><img src='../images/paging2.gif'></a>";
		}
		int blockCount = 1;
		
		while(blockCount <= PAGE_PER_BLOCK && pageTemp <= totalPage) {
			if(pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			} 
			else {
				pagingStr += "&nbsp;<a href= '" + reqURI + "?pageNum=" + pageTemp + "'>"
						+ pageTemp + "</a>&nbsp;"; 
			}
			pageTemp++;
			blockCount++;
		}
		if(pageTemp <= totalPage) {
			pagingStr += "<a href='" + reqURI + "?pageNum=" + pageTemp + "'>"
					+ "<img src='../images/paging3.gif'></a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqURI + "?pageNum=" + totalPage + "'>"
					+ "<img src='../images/paging4.gif'></a>";
		}
		return pagingStr;
	}
	
	public static String pagingStr(int totalCount, int pageNum, String reqURI, String queryStr) {
		
		String pagingStr = "";
		int totalPage = (int)Math.ceil((double)totalCount/PAGE_PER_SIZE);
		int pageTemp = (((pageNum - 1) / PAGE_PER_BLOCK) * PAGE_PER_BLOCK) + 1;
		if(pageTemp != 1) {
			pagingStr += "<a href='" + reqURI + "?pageNum=1&" + queryStr + "'>[첫페이지]</a>";
			pagingStr += "<a href='" + reqURI + "?pageNum=" + (pageTemp -1) + "&" + queryStr +"'>[이전블럭]</a>";
		}
		int blockCount = 1;
		
		while(blockCount <= PAGE_PER_BLOCK && pageTemp <= totalPage) {
			if(pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			} 
			else {
				pagingStr += "&nbsp;<a href= '" + reqURI + "?pageNum=" + pageTemp + "&" + queryStr + "'>"
						+ pageTemp + "</a>&nbsp;"; 
			}
			pageTemp++;
			blockCount++;
		}
		if(pageTemp <= totalPage) {
			pagingStr += "<a href='" + reqURI + "?pageNum=" + pageTemp + "&" + queryStr + "'>"
					+ "[다음블럭]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqURI + "?pageNum=" + totalPage + "&" + queryStr + "'>"
					+ "[마지막페이지]</a>";
		}
		return pagingStr;
	}
	
	public static String pagingStr2(int totalCount, int pageNum, String reqURI, String queryStr) {
		
		String pagingStr = "";
		int totalPage = (int)Math.ceil((double)totalCount/PAGE_PER_SIZE);
		int pageTemp = (((pageNum - 1) / PAGE_PER_BLOCK) * PAGE_PER_BLOCK) + 1;
		if(pageTemp != 1) {
			pagingStr += "<li class='page-item'><a href='" + reqURI + "?pageNum=1&" + queryStr + "' class='page-link'><i class='fas fa-angle-double-left'></i></a></li>";
			pagingStr += "<li class='page-item'><a href='" + reqURI + "?pageNum=" + (pageTemp -1) + "&" + queryStr +"' class='page-link'><i class='fas fa-angle-left'></i></a></li>";
		}
		int blockCount = 1;
		
		while(blockCount <= PAGE_PER_BLOCK && pageTemp <= totalPage) {
			if(pageTemp == pageNum) {
				pagingStr += "&nbsp;<li class='page-item active'><a href='#' class='page-link'>" + pageTemp + "</a></li>&nbsp;";
			} 
			else {
				pagingStr += "&nbsp;<li class='page-item'><a href= '" + reqURI + "?pageNum=" + pageTemp + "&" + queryStr + "'class='page-link'>" + pageTemp + "</i></a></li>&nbsp;"; 
			}
			pageTemp++;
			blockCount++;
		}
		if(pageTemp <= totalPage) {
			pagingStr += "<li class='page-item'><a href='" + reqURI + "?pageNum=" + pageTemp + "&" + queryStr + "' class='page-link'><i class='fas fa-angle-right'></i></a></li>";
			pagingStr += "&nbsp;";
			pagingStr += "<li class='page-item'><a href='" + reqURI + "?pageNum=" + totalPage + "&" + queryStr + "' class='page-link'><i class='fas fa-angle-double-right'></i></a></li>";
		}
		return pagingStr;
	}
}
