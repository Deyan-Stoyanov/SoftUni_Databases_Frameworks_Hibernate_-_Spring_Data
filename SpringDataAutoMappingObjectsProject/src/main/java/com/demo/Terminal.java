package com.demo;

import com.demo.dto.DTOConvertUtil;
import com.demo.dto.EmployeeDto;
import com.demo.model.entities.Address;
import com.demo.model.entities.City;
import com.demo.model.entities.Employee;
import com.demo.services.api.AddressService;
import com.demo.services.api.CityService;
import com.demo.services.api.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class Terminal implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AddressService addressService;
    private final CityService cityService;

    @Autowired
    public Terminal(EmployeeService employeeService, AddressService addressService, CityService cityService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        City city = new City("Sofia", new ArrayList<>());
        Address address = new Address(city, "Tintyava 15", new ArrayList<>());
        Employee manager = new Employee();
        manager.setFirstName("Ivan");
        manager.setLastName("Ivanov");
        manager.setSalary(BigDecimal.valueOf(2549.36));
        manager.setAddress(address);
        manager.setEmployees(new ArrayList<>());
        Employee employee = new Employee("Pesho", "Petrov", format.parse("12-08-1995"), address, BigDecimal.valueOf(850.54), false, manager, new ArrayList<>());
        Employee employee1 = new Employee("Gosho", "Georgiev", format.parse("28-02-2000"), address, BigDecimal.valueOf(1055.45), true, manager, new ArrayList<>());
        this.cityService.registerCity(city);
        this.addressService.registerAddress(address);
        this.employeeService.registerEmployee(manager);
        this.employeeService.registerEmployee(employee);
        this.employeeService.registerEmployee(employee1);
        manager.getEmployees().add(employee);
        manager.getEmployees().add(employee1);

        ModelMapper employeeMapper = new ModelMapper();

        PropertyMap<Employee, EmployeeDto> employeeMap = new PropertyMap<Employee, EmployeeDto>(){
            @Override
            protected void configure() {
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setAddressCityName(source.getAddress().getCity().getName());
                map().setSalary(source.getSalary());
            }
        };

        employeeMapper.addMappings(employeeMap);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeMapper.map(employee, employeeDto);
        EmployeeDto employeeDto1 = employeeMapper.map(employee1, EmployeeDto.class);
        System.out.println(employeeDto.toString());
        System.out.println(employeeDto1.toString());

        EmployeeDto employeeDTO = DTOConvertUtil.convertEmployeeToEmployeeDto(employee1);
        System.out.println("Employee DTO: "+ employeeDTO.toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(employeeMap);
        EmployeeDto employeeDTO1 = modelMapper.map(employee1, EmployeeDto.class);
        System.out.println("Employee DTO: "+ employeeDTO.toString());
    }
}