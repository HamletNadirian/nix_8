package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.view.dto.request.EmployeeRequestDto;
import ua.com.alevel.view.dto.response.EmployeeResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/employees")
public class EmployeeController extends BaseController {

    private final EmployeeFacade employeeFacade;
    private final DepartmentFacade departmentFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("first name", "firstName", "first_name"),
            new HeaderName("last name", "lastName", "last_name"),
            new HeaderName("department count", "departmentCount", "departmentCount"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null),
            new HeaderName("update", null, null)

    };

    public EmployeeController(EmployeeFacade employeeFacade, DepartmentFacade departmentFacade) {
        this.employeeFacade = employeeFacade;
        this.departmentFacade = departmentFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<EmployeeResponseDto> response = employeeFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/employees/all");
        model.addAttribute("createNew", "/employees/new");
        model.addAttribute("cardHeader", "All Employees");
        return "pages/employee/employee_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "employees");
    }

    @GetMapping("/new")
    public String redirectToNewEmployeePage(Model model) {
        model.addAttribute("employee", new EmployeeRequestDto());
        return "pages/employee/employee_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("employee") EmployeeRequestDto dto) {
        employeeFacade.create(dto);
        return "redirect:/employees";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewEmployeePage(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeFacade.findById(id));
        model.addAttribute("departments", departmentFacade.findByEmployeeId(id));
        return "pages/employee/employee_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeFacade.delete(id);
        return "redirect:/employees";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@ModelAttribute("employee") EmployeeRequestDto dto, @PathVariable Long id) {
        employeeFacade.update(dto, id);
        return "redirect:/employees";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model mode) {
        mode.addAttribute("employee", employeeFacade.findById(id));
        return "pages/employee/employee_update";
    }
}
