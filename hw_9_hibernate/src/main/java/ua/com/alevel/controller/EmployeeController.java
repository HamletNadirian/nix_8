package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.dto.employee.EmployeeResponseDto;
import ua.com.alevel.facade.EmployeeFacade;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employees")
public class EmployeeController extends AbstractController {

    private final EmployeeFacade employeeFacade;

    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    private List<SortData> generateSortDataList(String sort, String order) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", "id");
        map.put("first name", "firstName");
        map.put("last name", "lastName");
        map.put("count of departments", "departments");
        map.put("details", null);
        map.put("delete", null);
        return generateSortDataList(map, sort, order);
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        return initPageDataAndReturnEmployeesPage(employeeFacade.findAll(webRequest), model);
    }

    @PostMapping
    public ModelAndView findAllAndRedirect(WebRequest webRequest, ModelMap modelMap) {
        return findAllAndRedirect(webRequest, modelMap, "employees");
    }

    @GetMapping("/department/{departmentId}")
    public String findAll(Model model, @PathVariable Integer departmentId, WebRequest webRequest) {
        PageData<EmployeeResponseDto> pageData = employeeFacade.findAllByDepartmentId(departmentId, webRequest);
        pageData.setNewEntityRequest("/employees/department/" + departmentId + "/new");
        return initPageDataAndReturnEmployeesPage(pageData, model);
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Integer id) {
        model.addAttribute("employee", employeeFacade.findById(id));
        return "pages/employee/employee_details";
    }

    @GetMapping("/department/{departmentId}/new")
    public String redirectToNewEmployeeByDepartmentId(Model model, @PathVariable Integer departmentId) {
        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setDepartmentId(departmentId);
        model.addAttribute("employee", dto);
        model.addAttribute("requestUrl", "/employees/new");
        return "pages/employee/employee_new";
    }

    @PostMapping("/new")
    public String createNewEmployeeByDepartmentId(@ModelAttribute EmployeeRequestDto employee) {
        employeeFacade.create(employee);
        return "redirect:/employees";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateEmployeeByDepartmentId(Model model, @PathVariable Integer id) {
        EmployeeResponseDto employeeResponseDto = employeeFacade.findById(id);
        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setFirstName(employeeResponseDto.getFirstName());
        dto.setLastName(employeeResponseDto.getLastName());
        model.addAttribute("employee", dto);
        model.addAttribute("requestUrl", "/employees/update/" + id);
        return "pages/employee/employee_new";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@ModelAttribute EmployeeRequestDto employee, @PathVariable Integer id) {
        employeeFacade.update(employee, id);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        employeeFacade.delete(id);
        return "redirect:/employees";
    }

    private String initPageDataAndReturnEmployeesPage(PageData<EmployeeResponseDto> pageData, Model model) {
        pageData.setTableName("All Employees");
        pageData.setSearchRequest("/employees");
        pageData.setSortDataList(generateSortDataList(pageData.getSort(), pageData.getOrder()));
        model.addAttribute("pageData", pageData);
        return "pages/employee/employee_all";
    }
}
