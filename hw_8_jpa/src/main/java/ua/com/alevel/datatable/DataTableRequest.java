package ua.com.alevel.datatable;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DataTableRequest {

    private int page;
    private int size;
    private String sort;
    private String order;
    private Map<String, Object> queryMap;
}
