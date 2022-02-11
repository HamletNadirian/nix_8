package ua.com.alevel.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractController {

    protected void showInfo(Model model, String message) {
        model.addAttribute("message", message);
        showMessage(model, true);
    }

    protected void showInfo(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("message", message);
    }

    protected void showError(Model model, String message) {
        model.addAttribute("errorMessage", message);
        showMessage(model, true);
    }

    protected void showError(RedirectAttributes redirectAttributes, String error) {
        redirectAttributes.addFlashAttribute("errorMessage", error);
    }

    protected void showWarn(Model model, String message) {
        model.addAttribute("warnMessage", message);
        showMessage(model, true);
    }

    protected void showWarn(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("warnMessage", message);
    }

    protected void showMessage(Model model, boolean show) {
        model.addAttribute("showMessage", show);
    }

    protected ModelAndView findAllAndRedirect(WebRequest webRequest, ModelMap modelMap, String path) {
        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(modelMap::addAttribute);
        }
        return new ModelAndView("redirect:/" + path, modelMap);
    }

    protected List<SortData> generateSortDataList(Map<String, String> fieldColumnMap, String sort, String order) {
        List<SortData> sortDataList = new ArrayList<>();
        fieldColumnMap.forEach((column, field) -> {
            SortData sortData = new SortData();
            sortData.setColumnName(column);
            sortData.setFieldName(field);
            if (StringUtils.isNotBlank(field)) {
                if (field.equals(sort)) {
                    sortData.setSortable(true);
                    sortData.setOrder(order);
                } else {
                    sortData.setSortable(false);
                }
           }
            sortDataList.add(sortData);
        });
        return sortDataList;
    }

    @Getter
    @Setter
    @ToString
    public static class SortData {

        private String columnName;
        private String fieldName;
        private String order;
        private boolean sortable;
    }
}
