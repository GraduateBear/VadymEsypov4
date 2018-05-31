package com.aimprosoft.yesipov.demo.controller;

import com.aimprosoft.yesipov.demo.domain.Department;
import com.aimprosoft.yesipov.demo.service.DepartmentService;
import net.sf.oval.integration.spring.SpringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    private SpringValidator validator;

    @Autowired
    public DepartmentController(DepartmentService departmentService, SpringValidator validator) {
        this.departmentService = departmentService;
        this.validator = validator;
    }

    @GetMapping
    public ModelAndView getDepartmentsPage(ModelAndView modelAndView) {
        modelAndView.addObject("departmentList", departmentService.findAll());
        modelAndView.setViewName("departments");
        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView removeDepartment(ModelAndView modelAndView,
                                         @RequestParam(name = "id") long id) {
        modelAndView.addObject("remove_ID", id);
        Optional<Department> department = departmentService.findById(id);
        if (department.isPresent()) {
            departmentService.remove(department.get().getId());
        } else {
            modelAndView.addObject("errorMessage",
                    "A department with such id doesn't exist");
        }

        return getDepartmentsPage(modelAndView);
    }

    /*@PostMapping("/addDepartment")
    public ModelAndView addDepartment(ModelAndView modelAndView,
                                      @RequestParam(name = "departmentName") String name) {
        modelAndView.addObject("add_name", name);
        Optional<Department> optionalDepartment = departmentService.findByName(name);
        if (optionalDepartment.isPresent()) {
            modelAndView.addObject("errorMessage",
                    "A department with such name already exists");
        } else {
            Department department = new Department();
            department.setOriginalName(name);
            departmentService.add(department);
        }
        return returnAddEditDepartmentPage(modelAndView);
    }*/

    @ModelAttribute("department")
    public Department formBackingObject() {
        return new Department();
    }

    @PostMapping("/addDepartment")
    public ModelAndView addDepartment(@ModelAttribute(name = "department") Department department,
                                BindingResult result, ModelAndView modelAndView) {
        validator.validate(department, result);
        if (result.hasErrors()) {
            System.out.println(result.getFieldError("originalName").getCode());
            modelAndView.addObject("errorMessage", result);
            modelAndView.setViewName("add_edit_department");
            return modelAndView;
        }
        modelAndView.setViewName("add_edit_department");
        return modelAndView;
    }

    /*@PostMapping("/addDepartment")
    public String addDepartment(@ModelAttribute("department") Department department,
                                BindingResult result, Model model) {
        validator.validate(department, result);
        if (result.hasErrors()) {
            return "error";
        }

        return "add_edit_department";
    }*/

    @PostMapping("/editDepartment")
    public ModelAndView editDepartment(ModelAndView modelAndView,
                                       @RequestParam(name = "id") long id,
                                       @RequestParam(name = "departmentName") String name) {
        modelAndView.addObject("edit_ID", id);
        modelAndView.addObject("edit_name", name);

        Optional<Department> optionalById = departmentService.findById(id);
        Optional<Department> optionalByName = departmentService.findByName(name);

        if(optionalById.isPresent() && !optionalByName.isPresent()) {
            Department department = optionalById.get();
            department.setOriginalName(name);
            departmentService.edit(department);
        } else {
            modelAndView.addObject("errorMessage",
                    "A department with such name already exists or id is incorrect");
        }
        return returnAddEditDepartmentPage(modelAndView);
    }

    @GetMapping("/addEdit")
    public ModelAndView returnAddEditDepartmentPage(ModelAndView modelAndView) {
        modelAndView.addObject("departmentList", departmentService.findAll());
        modelAndView.setViewName("add_edit_department");
        return modelAndView;
    }

}
