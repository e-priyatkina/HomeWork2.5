package pro.sky.homework25;

import org.springframework.stereotype.Service;
import pro.sky.homework25.exception.EmployeeAlreadyAddedException;
import pro.sky.homework25.exception.EmployeeNotFoundException;
import pro.sky.homework25.exception.EmployeeStorageIsFullException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //коллекция сотрудников
    private final List<Employee> employees;

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    //максимально возможное количество сотрудников
    final int maxEmployees = 3;
    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        employees.add(newEmployee);
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен");
        } else if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник существует");
        } else {
            return newEmployee;
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee removeEmployee = new Employee(firstName, lastName);
        if (!employees.contains(removeEmployee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            employees.remove(removeEmployee);
            return removeEmployee;
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (!employees.contains(findEmployee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            return findEmployee;
        }
    }

    @Override
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableList(employees);
    }
}
