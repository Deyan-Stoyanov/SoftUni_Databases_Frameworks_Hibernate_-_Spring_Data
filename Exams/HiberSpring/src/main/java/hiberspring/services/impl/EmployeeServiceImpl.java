package hiberspring.services.impl;

import hiberspring.dtos.imports.employee.EmployeeImportFromXmlDto;
import hiberspring.dtos.views.EmployeeProductiveViewDto;
import hiberspring.entities.Employee;
import hiberspring.entities.EmployeeCard;
import hiberspring.parser.ValidationUtil;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.EmployeeCardRepository;
import hiberspring.repositories.EmployeeRepository;
import hiberspring.services.EmployeeService;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
    }

    @Override
    public void createOne(EmployeeImportFromXmlDto employeeImportFromXmlDto) {
        if(ValidationUtil.isValid(employeeImportFromXmlDto)){
            Employee employee = new Employee();
            employee.setFirstName(employeeImportFromXmlDto.getFirstName());
            employee.setLastName(employeeImportFromXmlDto.getLastName());
            employee.setPosition(employeeImportFromXmlDto.getPosition());
            if(this.employeeCardRepository.getEmployeeCardByNumber(employeeImportFromXmlDto.getCard())!= null){
                employee.setCard(this.employeeCardRepository.getEmployeeCardByNumber(employeeImportFromXmlDto.getCard()));
            }
            if(this.branchRepository.getBranchByName(employeeImportFromXmlDto.getBranch()) != null){
                employee.setBranch(this.branchRepository.getBranchByName(employeeImportFromXmlDto.getBranch()));
            }
            this.employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<EmployeeProductiveViewDto> getProductiveEmployees() {
        List<EmployeeProductiveViewDto> employeeProductiveViewDtos = this.employeeRepository.getAllEmployeesFromBranchesWithProducts();
        return employeeProductiveViewDtos;
    }

}
