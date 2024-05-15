package com.ra.demo.controller;

import com.ra.demo.entity.Employee;
import com.ra.demo.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IService<Employee, Integer, String> employeeService;

    @GetMapping("/list")
    public String viewAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employees/employees";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/addEmployee";
    }

    @PostMapping("/insertEmployee")
    public String insertEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "employees/addEmployee";
        } else {
            employeeService.addNew(employee);
            return "redirect:/employees/list";
        }
    }

    @GetMapping("/edit")
    public String editEmployee(@RequestParam("empId") Integer empId, Model model) {
        Employee employee = employeeService.findById(empId);
        model.addAttribute("employee", employee);
        return "employees/editEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "employees/editEmployee";
        } else {
            employeeService.update(employee);
            return "redirect:/employees/list";
        }
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("empId") Integer empId) {
        employeeService.delete(empId);
        return "redirect:/employees/list";
    }

    @GetMapping("/search")
    public String searchEmployees(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {
        if (searchQuery != null && !searchQuery.isEmpty()) {
            List<Employee> filteredEmployees = employeeService.getByName(searchQuery);
            model.addAttribute("employees", filteredEmployees);
        } else {
            List<Employee> allEmployees = employeeService.getAll();
            model.addAttribute("employees", allEmployees);
        }
        return "employees/employees";
    }
}



