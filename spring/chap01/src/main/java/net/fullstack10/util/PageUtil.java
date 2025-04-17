package net.fullstack10.util;

public class PageUtil {
    public static String printPagingArea(int totalCount, int pageNo, int pageSize, int pageBlockSize, String url) {
        StringBuilder sb = new StringBuilder();

        String baseURL = (url != null && !url.isEmpty() ? url : "");
        String pageURL = "";

        int totalPage = (int)Math.ceil(totalCount / (double)pageSize);
        if (totalPage < 1) return "";

        int pageBlockStart =  (int)Math.floor((pageNo - 1) / (float)pageBlockSize) * pageBlockSize + 1;

        int pageBlockEnd = (int)Math.ceil(pageNo / (double)pageBlockSize) * pageBlockSize;
        pageBlockEnd = Math.min(pageBlockEnd, totalPage);

        if (pageNo > 1) {
            pageURL = (baseURL.isEmpty() ? "?" : "&") + "page_no=1";
            sb.append("<div class='page-nav'><a href='" + baseURL + pageURL + "'><<</a></div>");
        }

        if (pageBlockStart > 1) {
            pageURL = (baseURL.isEmpty() ? "?" : "&") + "page_no=" + (pageBlockStart - 1);
            sb.append("<div class='page-nav'><a href='" + baseURL + pageURL + "'><</a></div>");
        }

        for(int i=pageBlockStart; i<=pageBlockEnd; i++) {
            if (pageNo == i) {
                sb.append("<div class='page-btn'>" + i + "</div>");
            } else {
                pageURL = (baseURL.isEmpty() ? "?" : "&") + "page_no=" + i;
                sb.append("<div class='page-btn'><a href='" + baseURL + pageURL + "'>" + i + "</a></div>");
            }
        }

        if (totalPage > pageBlockEnd) {
            pageURL = (baseURL.isEmpty() ? "?" : "&") + "page_no=" + (pageBlockEnd + 1);
            sb.append("<div class='page-nav'><a href='" + baseURL + pageURL + "'>></a></div>");
        }

        if (pageNo < totalPage) {
            pageURL = (baseURL.isEmpty() ? "?" : "&") + "page_no=" + totalPage;
            sb.append("<div class='page-nav'><a href='" + baseURL + pageURL + "'>>></a></div>");
        }

        return sb.toString();
    }
}
