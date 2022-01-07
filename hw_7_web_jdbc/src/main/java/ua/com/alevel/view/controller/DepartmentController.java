package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.view.dto.request.DepartmentRequestDto;
import ua.com.alevel.view.dto.response.DepartmentResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/departments")
public class DepartmentController extends BaseController {

    private final DepartmentFacade departmentFacade;
    private final EmployeeFacade employeeFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("department name", "departmentName", "department_name"),
            new HeaderName("budget", "budget", "budget"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null),
            new HeaderName("update", null, null)

    };

    public DepartmentController(DepartmentFacade departmentFacade, EmployeeFacade employeeFacade) {
        this.departmentFacade = departmentFacade;
        this.employeeFacade = employeeFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<DepartmentResponseDto> response = departmentFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/departments/all");
        model.addAttribute("createNew", "/departments/new");
        model.addAttribute("cardHeader", "All Departments");
        return "pages/department/department_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "departments");
    }

    @GetMapping("/new")
    public String redirectToNewEmployeePage(Model model) {
        model.addAttribute("department", new DepartmentRequestDto());
        return "pages/department/department_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("department") DepartmentRequestDto dto) {
        departmentFacade.create(dto);
        return "redirect:/departments";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("department", departmentFacade.findById(id));
        model.addAttribute("employees", employeeFacade.findAllByDepartmentId(id));
        return "pages/department/department_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        departmentFacade.delete(id);
        return "redirect:/departments";
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@ModelAttribute("department") DepartmentRequestDto dto, @PathVariable Long id) {
        departmentFacade.update(dto, id);
        return "redirect:/departments";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model mode) {
        mode.addAttribute("department", departmentFacade.findById(id));
        return "pages/department/department_update";
    }
}
