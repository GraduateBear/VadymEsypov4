package com.aimprosoft.yesipov.demo.controller;

import com.aimprosoft.yesipov.demo.domain.Department;
import com.aimprosoft.yesipov.demo.domain.Employee;
import com.aimprosoft.yesipov.demo.service.DepartmentService;
import com.aimprosoft.yesipov.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @PostMapping("/filteredList")
    public ModelAndView getFilteredList(ModelAndView modelAndView, @RequestParam(name = "id") long id) {
        Optional<Department> department = departmentService.findById(id);
        if (department.isPresent()) {
            modelAndView.addObject("employeeList", employeeService.getFilteredList(id));
            modelAndView.setViewName("employees");
        } else {
            modelAndView.addObject("errorMessage", "A department with such id doesn't exist");
            modelAndView.addObject("departmentList", departmentService.findAll());
            modelAndView.setViewName("departments");
        }

        return modelAndView;
    }

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView modelAndView) {
        modelAndView.addObject("employeeList", employeeService.findAll());
        modelAndView.setViewName("all_employees");
        return modelAndView;
    }

    @GetMapping("/addEdit")
    public ModelAndView returnAddEditEmployeePage(ModelAndView modelAndView) {
        modelAndView.addObject("departmentList", departmentService.findAll());
        modelAndView.setViewName("add_edit_employee");
        return modelAndView;
    }

    @PostMapping("/removeEmployee")
    public ModelAndView removeDepartment(ModelAndView modelAndView,
                                         @RequestParam(name = "email") String email) {
        Optional<Employee> employee = employeeService.findByEmail(email);
        long id = 0;
        if (employee.isPresent()) {
            id = employee.get().getDepartment().getId();
            employeeService.remove(employee.get().getId());
        } else {
            modelAndView.addObject("errorMessage",
                    "An employee with such email doesn't exist");
        }

        return getFilteredList(modelAndView, id);
    }

    @PostMapping("/addEmployee")
    public ModelAndView addEmployee(ModelAndView modelAndView,
                                      @RequestParam(name = "firstName") String firstName,
                                      @RequestParam(name = "lastName") String lastName,
                                      @RequestParam(name = "birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                                      @RequestParam(name = "email") String email,
                                      @RequestParam(name = "position") String position,
                                      @RequestParam(name = "departmentId") Long departmentId,
                                      @RequestParam(name = "salary") Double salary) {
        Optional<Employee> optionalEmployee = employeeService.findByEmail(email);
        Optional<Department> optionalById = departmentService.findById(departmentId);
        if (optionalEmployee.isPresent() || !optionalById.isPresent()) {
            modelAndView.addObject("errorMessage",
                    "An employee with such name already exists or a department with such id doesn't exist");
        } else {
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setBirthday(birthday);
            employee.setEmail(email);
            employee.setJob(position);
            employee.setDepartment(optionalById.get());
            employee.setSalary(salary);
            employeeService.add(employee);
        }
        return returnAddEditEmployeePage(modelAndView);
    }

    @PostMapping("/editEmployee")
    public ModelAndView editEmployee(ModelAndView modelAndView,
                                     @RequestParam(name = "id") Long id,
                                     @RequestParam(name = "firstName") String firstName,
                                     @RequestParam(name = "lastName") String lastName,
                                     @RequestParam(name = "birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                                     @RequestParam(name = "email") String email,
                                     @RequestParam(name = "position") String position,
                                     @RequestParam(name = "departmentId") Long departmentId,
                                     @RequestParam(name = "salary") Double salary) {
        Optional<Employee> optionalById = employeeService.findById(id);
        Optional<Employee> optionalByEmail = employeeService.findByEmail(email);
        System.out.println(departmentId);
        Optional<Department> optionalByDepartmentId =
                departmentService.findById(departmentId == null ? 0 : departmentId);
        System.out.println(optionalByDepartmentId);
        System.out.println(optionalByDepartmentId.isPresent());

        if(optionalById.isPresent() && !optionalByEmail.isPresent() && optionalByDepartmentId.isPresent()) {
            Employee employee = optionalById.get();

            employee.setFirstName(firstName.equals("") ? employee.getFirstName() : firstName);
            employee.setLastName(lastName.equals("") ? employee.getLastName() : lastName);
            employee.setBirthday(birthday == null ? employee.getBirthday() : birthday);
            employee.setEmail(email);
            employee.setJob(position.equals("") ? employee.getJob() : position);
            employee.setDepartment(optionalByDepartmentId.get());
            employee.setSalary(salary == null ? employee.getSalary() : salary);

            employeeService.edit(employee);
        } else {
            modelAndView.addObject("errorMessage",
                    "An employee with such email already exists, or such id or/and departmentId doesn't exist");
        }
        return returnAddEditEmployeePage(modelAndView);
    }
}
