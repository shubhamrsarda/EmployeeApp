package com.getarrays.employeemanager;

import com.getarrays.employeemanager.model.Employee;
import com.getarrays.employeemanager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestParam(value = "id") int id,
                           @RequestParam(value = "width", required = false, defaultValue = "200") int width,
                           @RequestParam(value = "height", required = false, defaultValue = "200") int height ){
            String url = "https://picsum.photos/id/" + id + "/" + width + "/" + height;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url,byte[].class);


    }

    @GetMapping("/getEmployeeParam")
    public Employee getEmployeeWithParam(@RequestParam(value = "name") String name,
                                         @RequestParam(value = "phone") String phone){

        return employeeService.getEmployeeParam(name,phone);
    }



    @GetMapping("/greet")
    public GreetResponce greet(){
        return  new GreetResponce("Hello Boot","boot Name",List.of("jv","cpp","py"));
    }

    record GreetResponce(String greet,String name,List<String> lng ){

    }
    record Lang(List<String> lng){};

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity< Employee> getEmployee(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity< Void> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

}
