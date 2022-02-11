package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.dto.ResponseDto;

import java.util.List;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);
    void update(REQ req, Integer id);
    void delete(Integer id);
    RES findById(Integer id);
    List<RES> findAll();
    PageData<RES> findAll(WebRequest request);
}
