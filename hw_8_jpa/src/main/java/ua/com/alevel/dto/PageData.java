package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.datatable.DataTableResponse;

import java.util.List;

@Getter
@Setter
@ToString
public class PageData<DTO extends ResponseDto> {

    private List<DTO> items;
    private int pageSize = 10;
    private int currentPage = 1;
    private int totalPages = 0;
    private int[] pageSizeItems = new int[] { 1, 10, 25, 50, 100 };
    private long itemsSize = 0;
    private int currentShowToEntries;
    private int currentShowFromEntries;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private String sort;
    private String order;
    private String tableName;
    private List<AbstractController.SortData> sortDataList;
    private String searchRequest;
    private String newEntityRequest;

    public PageData(final List<DTO> items) {
        this.items = items;
    }

    public void initPageDataState(DataTableResponse response) {
        this.itemsSize = response.getCount();
        if (this.itemsSize != 0) {
            if (this.itemsSize % this.pageSize == 0) {
                this.totalPages = (int) (this.itemsSize / this.pageSize);
            } else {
                this.totalPages = (int) (this.itemsSize / this.pageSize) + 1;
            }
            this.currentShowFromEntries = (currentPage - 1) * pageSize + 1;
            this.currentShowToEntries = this.currentShowFromEntries - 1 + this.items.size();
            this.showFirst = this.currentPage > 1;
            this.showPrevious = this.showFirst;
            this.showNext = this.currentPage < this.totalPages;
            this.showLast = this.showNext;
        }
    }
}
