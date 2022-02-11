package ua.com.alevel.service;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.BaseEntity;

import java.util.List;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Integer id);
    ENTITY findById(Integer id);
    List<ENTITY> findAll();
    DataTableResponse<ENTITY> findAll(DataTableRequest request);
}
