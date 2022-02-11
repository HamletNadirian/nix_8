package ua.com.alevel.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;

import java.util.Map;

public final class WebRequestUtil {

    private WebRequestUtil() { }

    public static final String ORDER_PARAM = "order";
    public static final String SORT_PARAM = "sort";
    public static final String PAGE_PARAM = "page";
    public static final String SIZE_PARAM = "size";

    public static final String DEFAULT_ORDER = "desc";
    public static final String DEFAULT_SORT = "id";
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_SIZE = 10;

    public static DataTableRequest generateDataTableRequest(WebRequest request) {
        final DataTableRequest dataTableRequest = defaultDataTableRequest();
        final Map<String, String[]> queryMap = request.getParameterMap();
        if (MapUtils.isEmpty(queryMap)) {
            return dataTableRequest;
        }
        final String sort = request.getParameter(SORT_PARAM);
        if (StringUtils.isNotBlank(sort)) {
            dataTableRequest.setSort(sort);
        }
        final String order = request.getParameter(ORDER_PARAM);
        if (StringUtils.isNotBlank(order)) {
            dataTableRequest.setOrder(order);
        }
        final String page = request.getParameter(PAGE_PARAM);
        if (StringUtils.isNotBlank(page)) {
            dataTableRequest.setPage(Integer.parseInt(page));
        }
        final String size = request.getParameter(SIZE_PARAM);
        if (StringUtils.isNotBlank(size)) {
            dataTableRequest.setSize(Integer.parseInt(size));
        }

        return dataTableRequest;
    }

    private static DataTableRequest defaultDataTableRequest() {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setOrder(DEFAULT_ORDER);
        dataTableRequest.setPage(DEFAULT_PAGE);
        dataTableRequest.setSize(DEFAULT_SIZE);
        dataTableRequest.setSort(DEFAULT_SORT);
        return dataTableRequest;
    }
}
