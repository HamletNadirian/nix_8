package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.department.DepartmentRequestDto;
import ua.com.alevel.dto.department.DepartmentResponseDto;
import ua.com.alevel.facade.DepartmentFacade;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/departments")
public class DepartmentController extends AbstractController {

    private final DepartmentFacade departmentFacade;

    public DepartmentController(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    private List<SortData> generateSortDataList(String sort, String order) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", "id");
        map.put("name", "name");
        map.put("location", "location");
        map.put("budget", "budget");
        map.put("count of employees", "employees");
        map.put("details", null);
        map.put("delete", null);
        return generateSortDataList(map, sort, order);
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        return initPageDataAndReturnDepartmentsPage(departmentFacade.findAll(webRequest), model);
    }

    @GetMapping("/employee/{employeeId}")
    public String findAll(Model model, @PathVariable Integer employeeId, WebRequest webRequest) {
        PageData<DepartmentResponseDto> pageData = departmentFacade.findAllByEmployeeId(employeeId, webRequest);
        return initPageDataAndReturnDepartmentsPage(pageData, model);
    }

    @PostMapping
    public ModelAndView findAllAndRedirect(WebRequest webRequest, ModelMap modelMap) {
        return findAllAndRedirect(webRequest, modelMap, "departments");
    }

    @GetMapping("/new")
    public String redirectToCreateNewDepartment(Model model) {
        model.addAttribute("department", new DepartmentRequestDto());
        return "pages/department/department_new";
    }

    @PostMapping("/new")
    public String createNewDepartment(@ModelAttribute("department") DepartmentRequestDto departmentRequestDto) {
        departmentFacade.create(departmentRequestDto);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentFacade.delete(id);
        return "redirect:/departments";
    }

    private String initPageDataAndReturnDepartmentsPage(PageData<DepartmentResponseDto> pageData, Model model) {
        pageData.setTableName("All departments");
        pageData.setSearchRequest("/departments");
        pageData.setNewEntityRequest("/departments/new");
        pageData.setSortDataList(generateSortDataList(pageData.getSort(), pageData.getOrder()));
        model.addAttribute("pageData", pageData);
        return "pages/department/department_all";
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@ModelAttribute DepartmentRequestDto department, @PathVariable Integer id) {
        departmentFacade.update(department, id);
        return "redirect:/departments";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Integer id) {
        model.addAttribute("department", departmentFacade.findById(id));
        return "pages/department/department_details";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateDepartmentByEmployeeId(Model model, @PathVariable Integer id) {
        DepartmentResponseDto departmentResponseDto = departmentFacade.findById(id);
        DepartmentRequestDto dto = new DepartmentRequestDto();
        dto.setName(departmentResponseDto.getName());
        dto.setBudget(departmentResponseDto.getBudget());
        dto.setLocation(departmentResponseDto.getLocation());
        model.addAttribute("department", dto);
        model.addAttribute("requestUrl", "/departments/update/" + id);
        return "pages/department/department_new";
    }

}
