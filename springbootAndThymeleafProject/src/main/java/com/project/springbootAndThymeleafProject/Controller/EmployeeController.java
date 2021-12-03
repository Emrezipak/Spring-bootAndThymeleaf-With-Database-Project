package com.project.springbootAndThymeleafProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.springbootAndThymeleafProject.Entities.Employee;
import com.project.springbootAndThymeleafProject.Service.Abstracts.IEmployeeService;

@Controller
//RestController yerine controller kullanmamızın sebebi, metodların projemizdeki class'lar yerine view(görüntü) dosyalarını döndürmesini belirtiyoruz.
public class EmployeeController {
	
	private IEmployeeService employeeService;
	@Autowired
	public EmployeeController(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
		
	@GetMapping("/")
	public String getAllEmployee(Model model, String email){
		
		if(email!=null) {
			model.addAttribute("employeeList",this.employeeService.getByEmail(email));
		}
		else {
			model.addAttribute("employeeList",this.employeeService.getAllEmployee());
		}
		
		return "index";
	}
	
	@GetMapping("/addEmployee")
    public String add(Model model){
		Employee employee=new Employee();
		model.addAttribute("employee", employee);
		return "AddEmployee";
    }
	
	@PostMapping("/addNewEmployee")
	public String addNewEmployee(@ModelAttribute("employee") Employee employee) {
		this.employeeService.add(employee);
		return "redirect:/";
	}
	
	@PostMapping("/updateNewEmployee")
	public String updateNewEmployee(@ModelAttribute("employee")Employee employee) {
		this.employeeService.updateEmployee(employee);
		
		return "redirect:/";
	}
	
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable(value="id") long id,Model model) {
		Employee employee=employeeService.getById(id);
		model.addAttribute("employee",employee);
		return "update_Employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String delete(@PathVariable(value="id") long id) {
		this.employeeService.delete(id);
		return "redirect:/";
	}
	@GetMapping("/employeeDetail/{id}")
	public String employeeDetail(@PathVariable(value="id") long id,Model model) {
		
		Employee employee=this.employeeService.getById(id);
		model.addAttribute("employee", employee);
		return "detail";
	}
	
	

}
