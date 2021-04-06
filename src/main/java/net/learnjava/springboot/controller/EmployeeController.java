package net.learnjava.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.learnjava.springboot.exception.ResourceNotFoundException;
import net.learnjava.springboot.model.Employee;
import net.learnjava.springboot.repository.EmployeeRepository;
//when testing with post man comment this @CrossOrigin
@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepo;      
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}
	
	//create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return empRepo.save(emp);
	}
	
	/**
	 * Get Employee by Id
	 * @param id
	 * @return
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		//if record not exists in data base then throws  exception using orElseThrow
		Employee emp = empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Fund"));
		return ResponseEntity.ok(emp);
	}
	
	/**
	 * 
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
		//if record not exists in data base then throws  exception using orElseThrow
		System.out.println("updating emp: "+employee.toString());
		Employee emp = empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Fund"));
		emp.setFirstName(employee.getFirstName());
		emp.setEmailId(employee.getEmailId());
		emp.setLastName(employee.getLastName());
		Employee updatedEmp = empRepo.save(emp);
		return ResponseEntity.ok(updatedEmp);
	}
}
