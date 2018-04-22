package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import app.exam.parser.ValidationUtil;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.PositionRepository;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void create(EmployeeJSONImportDTO importDTO) {
        if(ValidationUtil.isValid(importDTO)){
            Employee employee = new Employee();
            employee.setName(importDTO.getName());
            employee.setAge(importDTO.getAge());
            Position position = new Position();
            if(this.positionRepository.findByName(importDTO.getPosition()) == null){
                position.setName(importDTO.getPosition());
            } else {
                position = this.positionRepository.findByName(importDTO.getPosition());
            }
            this.positionRepository.save(position);
            employee.setPosition(position);
            this.employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void createMany(EmployeeJSONImportDTO[] importDTO) {
        for (EmployeeJSONImportDTO dto:importDTO) {
            this.create(dto);
        }
    }
}
