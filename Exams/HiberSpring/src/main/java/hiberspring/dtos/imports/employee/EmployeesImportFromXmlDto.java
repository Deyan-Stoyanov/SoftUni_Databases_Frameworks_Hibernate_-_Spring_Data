package hiberspring.dtos.imports.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesImportFromXmlDto {
    @XmlElement(name = "employee")
    private List<EmployeeImportFromXmlDto> employees;

    public EmployeesImportFromXmlDto(List<EmployeeImportFromXmlDto> employees) {
        this.employees = employees;
    }

    public EmployeesImportFromXmlDto() {
    }

    public List<EmployeeImportFromXmlDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeImportFromXmlDto> employees) {
        this.employees = employees;
    }
}
