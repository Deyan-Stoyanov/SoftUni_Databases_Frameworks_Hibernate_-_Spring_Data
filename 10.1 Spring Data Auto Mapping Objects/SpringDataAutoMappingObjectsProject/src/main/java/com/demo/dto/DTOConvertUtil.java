package com.demo.dto;

import com.demo.model.entities.Employee;
import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DTOConvertUtil {
    public DTOConvertUtil() {
    }

    public static <S, D> D convert(S source, Class<D> destinationClass){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(source, destinationClass);
    }

    public static <S, D> List<D> convert(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        List<D> resultList = new ArrayList<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultList.add(d);
        }

        return resultList;
    }

    public static <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        Set<D> resultSet = new HashSet<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultSet.add(d);
        }

        return resultSet;
    }

    public static EmployeeDto convertEmployeeToEmployeeDto(Employee employee) {
        Converter<String, String> toUpperConverter = ctx -> ctx.getSource() == null ? "" : ctx.getSource().toUpperCase();
        ExpressionMap<Employee, EmployeeDto> mappingWithConverter =
                m -> m.using(toUpperConverter)
                        .map(Employee::getLastName, EmployeeDto::setLastName);
        ExpressionMap<Employee, EmployeeDto> skipping = m -> m.skip(EmployeeDto::setSalary);
        return convertCustom(employee, EmployeeDto.class, mappingWithConverter, skipping);
    }

    public static Employee convertEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        Converter<String, String> toLowerConverter = ctx -> ctx.getSource() == null ? "" : ctx.getSource().toLowerCase();

        ExpressionMap<EmployeeDto, Employee> mappingWithConverter =
                m -> m.using(toLowerConverter)
                        .<String> map(EmployeeDto::getLastName, (dest, v) -> dest.getManager().setLastName(v));

        ExpressionMap<EmployeeDto, Employee> skipping = m -> m.using(toLowerConverter).skip(Employee::setSalary);
        return convertCustom(employeeDto, Employee.class, mappingWithConverter, skipping);
    }

    private  static <S, D> D convertCustom(S source, Class<D> destClass, ExpressionMap<S, D>... exprMaps) {
        ModelMapper mapper = new ModelMapper();
        TypeMap<S, D> typeMap = mapper.createTypeMap((Class<S>) source.getClass(), destClass);
        for (ExpressionMap<S, D> exprMap : exprMaps) {
            typeMap.addMappings(exprMap);
        }
        return typeMap.map(source);
    }

    public static Set<EmployeeDto> convertEmployeeSetToEmployeeDtoSet(Iterable<Employee> employees) {
        ModelMapper mapper = new ModelMapper();

        Set<EmployeeDto> resultList = new HashSet<>();

        for (Employee employee : employees) {
            EmployeeDto employeeDto = convertEmployeeToEmployeeDto(employee);
            resultList.add(employeeDto);
        }

        return resultList;
    }

    public static List<ManagerDto> convertEmployeeListToManagerDtoList(List<Employee> employees) {
        ModelMapper mapper = new ModelMapper();
        List<ManagerDto> resultList = new ArrayList<>();
        for (Employee e : employees) {
            ManagerDto managerDto = convert(e, ManagerDto.class);
            managerDto.setEmployees((List<EmployeeDto>) convertEmployeeSetToEmployeeDtoSet(e.getEmployees()));
            resultList.add(managerDto);
        }

        return resultList;
    }
}
