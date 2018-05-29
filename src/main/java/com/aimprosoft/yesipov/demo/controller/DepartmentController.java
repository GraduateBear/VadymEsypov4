package com.aimprosoft.yesipov.demo.controller;

import com.aimprosoft.yesipov.demo.domain.Department;
import com.aimprosoft.yesipov.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
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
        Optional<Department> department = departmentService.findById(id);
        if (department.isPresent()) {
            departmentService.remove(department.get().getId());
        } else {
            modelAndView.addObject("errorMessage",
                    "A department with such id doesn't exist");
        }

        return getDepartmentsPage(modelAndView);
    }

    @PostMapping("/addDepartment")
    public ModelAndView addDepartment(ModelAndView modelAndView,
                                      @RequestParam(name = "departmentName") String name) {
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
    }

    @PostMapping("/editDepartment")
    public ModelAndView editDepartment(ModelAndView modelAndView,
                                       @RequestParam(name = "id") long id,
        //                               @RequestParam(name = "newId") String newID,
                                       @RequestParam(name = "departmentName") String name) {
        //long newId = newID.equals("") ? -1 : Long.valueOf(newID);

        Optional<Department> optionalById = departmentService.findById(id);
        //Optional<Department> optionalByNewId = departmentService.findById(newId);
        Optional<Department> optionalByName = departmentService.findByName(name);

        if(optionalById.isPresent() //|| !optionalByNewId.isPresent() ||
                && !optionalByName.isPresent()) {
            Department department = optionalById.get();
            department.setOriginalName(name);
            //department.setId(newId == -1 ? id : newId);
            //department.setOriginalName(name.equals("") ? department.getOriginalName() : name);
            departmentService.edit(department, id);
        } else {
            modelAndView.addObject("errorMessage",
                    "A department with such name or id already exists");
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
