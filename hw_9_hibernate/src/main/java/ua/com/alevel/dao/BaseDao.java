package ua.com.alevel.dao;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Integer id);
    boolean existById(Integer id);
    Optional<ENTITY> findById(Integer id);
    List<ENTITY> findAll();
    List<ENTITY> findAll(DataTableRequest request);
    long count();
}
