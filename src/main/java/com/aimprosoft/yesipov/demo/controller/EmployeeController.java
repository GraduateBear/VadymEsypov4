package com.aimprosoft.yesipov.demo.controller;

import com.aimprosoft.yesipov.demo.domain.Department;
import com.aimprosoft.yesipov.demo.domain.Employee;
import com.aimprosoft.yesipov.demo.service.DepartmentService;
import com.aimprosoft.yesipov.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
            modelAndView.addObject("ID", id);
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

    @GetMapping("/addEmployeePage")
    public ModelAndView returnAddEmployeePage(ModelAndView modelAndView) {
        modelAndView.setViewName("add_employee");
        return modelAndView;
    }

    @PostMapping("/editEmployeePage")
    public ModelAndView returnEditEmployeePage(ModelAndView modelAndView,
                                               @RequestParam(name = "id") long id,
                                               @RequestParam(name = "firstName") String firstName,
                                               @RequestParam(name = "lastName") String lastName,
                                               @RequestParam(name = "birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                                               @RequestParam(name = "email") String email,
                                               @RequestParam(name = "position") String position,
                                               @RequestParam(name = "departmentName") String departmentName,
                                               @RequestParam(name = "salary") double salary) {
        long departmentId = departmentService.findByName(departmentName).get().getId();
        modelAndView.addObject("ID", id);
        remainParams(modelAndView, firstName, lastName,
                birthday, email, position, departmentId, salary);
        modelAndView.setViewName("edit_employee");
        return modelAndView;
    }

    private void remainParams(ModelAndView modelAndView, String firstName, String lastName, Date birthday,
                              String email, String position, long departmentId, double salary) {
        modelAndView.addObject("first_name", firstName);
        modelAndView.addObject("last_name", lastName);
        modelAndView.addObject("birth", birthday);
        modelAndView.addObject("mail", email);
        modelAndView.addObject("job", position);
        modelAndView.addObject("department_id", departmentId);
        modelAndView.addObject("wage", salary);
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
        remainParams(modelAndView, firstName, lastName, birthday, email, position, departmentId, salary);
        modelAndView.setViewName("add_employee");
        return modelAndView;
    }

    @PostMapping("/editEmployee")
    public ModelAndView editEmployee(ModelAndView modelAndView,
                                     @RequestParam(name = "id") long id,
                                     @RequestParam(name = "firstName") String firstName,
                                     @RequestParam(name = "lastName") String lastName,
                                     @RequestParam(name = "birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
                                     @RequestParam(name = "email") String email,
                                     @RequestParam(name = "position") String position,
                                     @RequestParam(name = "departmentId") Long departmentId,
                                     @RequestParam(name = "salary") Double salary) {
        Optional<Employee> optionalById = employeeService.findById(id);
        Optional<Employee> optionalByEmail = employeeService.findByEmail(email);
        Optional<Department> optionalByDepartmentId =
                departmentService.findById(departmentId == null ? 0 : departmentId);

        if (optionalById.isPresent()
                && (departmentId.equals(optionalById.get().getDepartment().getId()) || optionalByDepartmentId.isPresent())
                && (optionalById.get().getEmail().equals(email) || !optionalByEmail.isPresent())) {
            Employee employee = optionalById.get();

            employee.setFirstName(firstName.equals("") ? employee.getFirstName() : firstName);
            employee.setLastName(lastName.equals("") ? employee.getLastName() : lastName);
            employee.setBirthday(birthday == null ? employee.getBirthday() : birthday);
            employee.setEmail(email.equals("") ? employee.getEmail() : email);
            employee.setJob(position.equals("") ? employee.getJob() : position);
            employee.setDepartment(optionalByDepartmentId.get());
            employee.setSalary(salary == null ? employee.getSalary() : salary);

            employeeService.edit(employee);
        } else {
            modelAndView.addObject("errorMessage",
                    "An employee with such email already exists, or such id or/and departmentId doesn't exist");
        }

        modelAndView.addObject("ID", id);
        remainParams(modelAndView, firstName, lastName, birthday, email, position, departmentId, salary);

        modelAndView.setViewName("edit_employee");
        return modelAndView;
    }
}
