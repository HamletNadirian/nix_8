package ua.com.alevel.datatable;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;

import java.util.List;

@Getter
@Setter
public class DataTableResponse<ENTITY extends BaseEntity> {

    private List<ENTITY> entities;
    private long count;
}
