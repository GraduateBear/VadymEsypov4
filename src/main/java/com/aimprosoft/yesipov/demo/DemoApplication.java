package com.aimprosoft.yesipov.demo;

import com.aimprosoft.yesipov.demo.domain.Department;
import com.aimprosoft.yesipov.demo.domain.Employee;
import com.aimprosoft.yesipov.demo.repository.DepartmentRepository;
import com.aimprosoft.yesipov.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public DemoApplication(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) throws Exception    {
        Department department1 = Department.builder().originalName("Accounting department").build();
        Department department2 = Department.builder().originalName("Purchasing department").build();
        Department department3 = Department.builder().originalName("Management").build();
        Department department4 = Department.builder().originalName("Administrative department").build();
        Department department5 = Department.builder().originalName("Logistics").build();
        Department department6 = Department.builder().originalName("IT department").build();
        Department department7 = Department.builder().originalName("Marketing").build();
        Department department8 = Department.builder().originalName("Public relations department").build();
        Department department9 = Department.builder().originalName("Sales department").build();
        Department department10 = Department.builder().originalName("Production department").build();
        Department department11 = Department.builder().originalName("Shipping department").build();
        Department department12 = Department.builder().originalName("Import department").build();
        Department department13 = Department.builder().originalName("Export department").build();
        Department department14 = Department.builder().originalName("Engineering department").build();
        Department department15 = Department.builder().originalName("Human resources").build();

        Employee employee1 = Employee.builder().firstName("Conor").lastName("McGregor").birthday(new Date()).email("c.mcgregor@gmail.com").job("Analyst").department(department1).salary(9937.52).build();
        Employee employee2 = Employee.builder().firstName("Max").lastName("Holloway").birthday(new Date()).email("m.holloway@gmail.com").job("Director of boards").department(department2).salary(4588.36).build();
        Employee employee3 = Employee.builder().firstName("Demetrius").lastName("Johnson").birthday(new Date()).email("d.johnson@gmail.com").job("CEO").department(department3).salary(4588.36).build();
        Employee employee4 = Employee.builder().firstName("Amanda").lastName("Nunes").birthday(new Date()).email("a.nunes@gmail.com").job("Purchasing manager").department(department4).salary(6712.43).build();
        Employee employee5 = Employee.builder().firstName("Michelle").lastName("Waterson").birthday(new Date()).email("m.waterson@gmail.com").job("CFO").department(department5).salary(123123).build();
        Employee employee6 = Employee.builder().firstName("Cody").lastName("Garbrandt").birthday(new Date()).email("c.garbrandt@gmail.com").job("Managing director").department(department6).salary(234).build();
        Employee employee7 = Employee.builder().firstName("Michael").lastName("Bisping").birthday(new Date()).email("m.bisping@gmail.com").job("Commercial agent").department(department7).salary(2342).build();
        Employee employee8 = Employee.builder().firstName("Holly").lastName("Holm").birthday(new Date()).email("h.holm@gmail.com").job("Service engineer").department(department8).salary(5343).build();
        Employee employee9 = Employee.builder().firstName("Stipe").lastName("Miocic").birthday(new Date()).email("s.miocic@gmail.com").job("Senior executive").department(department9).salary(234).build();
        Employee employee10 = Employee.builder().firstName("Travis").lastName("Brown").birthday(new Date()).email("t.brown@gmail.com").job("Sales representative").department(department10).salary(234).build();
        Employee employee11 = Employee.builder().firstName("Robert").lastName("Whittaker").birthday(new Date()).email("r.whittaker@gmail.com").job("Sales manager").department(department11).salary(2345).build();
        Employee employee12 = Employee.builder().firstName("Mark").lastName("Hunt").birthday(new Date()).email("m.hunt@gmail.com").job("Export manager").department(department12).salary(234).build();
        Employee employee13 = Employee.builder().firstName("Ronda").lastName("Rousey").birthday(new Date()).email("r.rousey@gmail.com").job("Import manager").department(department13).salary(12123).build();
        Employee employee14 = Employee.builder().firstName("Misha").lastName("Tate").birthday(new Date()).email("m.tate@gmail.com").job("Accountant").department(department14).salary(234234).build();
        Employee employee15 = Employee.builder().firstName("Dustin").lastName("Poirier").birthday(new Date()).email("d.poirier@gmail.com").job("PR manager").department(department15).salary(355).build();

        departmentRepository.saveAll(Stream.of(
                department1, department2, department3, department4, department5,
                department6, department7, department8, department9, department10,
                department11, department12, department13, department14, department15
        ).collect(Collectors.toList()));

        employeeRepository.saveAll(Stream.of(
                employee1, employee2, employee3, employee4, employee5,
                employee6, employee7, employee8, employee9, employee10,
                employee11, employee12, employee13, employee14, employee15
        ).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
