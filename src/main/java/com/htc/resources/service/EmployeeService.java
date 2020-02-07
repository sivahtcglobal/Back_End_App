package com.htc.resources.service;

import com.htc.resources.model.Employee;
import com.htc.resources.request.EmployeeRequest;
import com.htc.resources.response.EmployeeResponse;

import java.util.List;

/**
 * Created by giris on 27/1/20.
 */

public interface EmployeeService {

    void addEmployee(EmployeeRequest employeeRequest);
    List<EmployeeRequest> getEmployeeRequestList();
    List<EmployeeResponse> getEmployeeList();
    List<Employee> findByEmpId(int employeeId);
    String deleteEmployee(int employee);

}
