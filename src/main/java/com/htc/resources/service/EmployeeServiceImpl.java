package com.htc.resources.service;


import com.htc.resources.dao.EmployeeRepository;
import com.htc.resources.model.*;
import com.htc.resources.request.EmployeeRequest;
import com.htc.resources.request.ProjectRequest;
import com.htc.resources.request.SkillRequest;
import com.htc.resources.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


/**
 * Created by giris on 27/1/20.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    Logger logger;
    /*@Override
    public List<Employee> getEmployeeList(){

        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }*/
    @Override
    public List<EmployeeResponse> getEmployeeList() {

        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Employee> employeeList=employeeRepository.findAll();
        for(Employee employees : employeeList){
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setEmpId(employees.getEmpId());
            employeeResponse.setEmpName(employees.getEmpName());
            employeeResponse.setDesignation(employees.getDesignation());
            employeeResponse.setPrimaryWorkLocation(employees.getPrimaryWorkLocation());
            employeeResponse.setOfficialEmailAddr(employees.getPersonalDetails().getOfficialEmailAddr());
            employeeResponse.setExtensionNumber(employees.getPersonalDetails().getExtensionNumber());
            employeeResponse.setMobileNumber(employees.getPersonalDetails().getMobileNumber());
            /*employeeResponse.setProjectList(employees.getProjectList());
            employeeResponse.setSkillList(employees.getSkillList());*/
            employeeResponseList.add(employeeResponse);
        }

        return employeeResponseList;
    }

    @Override
    public List<Employee> findByEmpId(int employeeId) {
        List<Employee> availableEmployeeList = employeeRepository.findByEmpId(employeeId);
        return availableEmployeeList;
    }

    @Override
    public String deleteEmployee(int employee) {
        employeeRepository.deleteById(employee);
        return "Deleted Successfully";
    }

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        PersonalDetails personalDetails=new PersonalDetails();
        prepareEmployee(employeeRequest,employee);
        preparePersonalDetails(employeeRequest, personalDetails,employee);
        employee.setPersonalDetails(personalDetails);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeRequest> getEmployeeRequestList() {
        List<EmployeeRequest> employeeRequestList = new ArrayList<>();
        List<Employee> employeeList=employeeRepository.findAll();
        for(Employee employee : employeeList){
            EmployeeRequest employeeRequest = new EmployeeRequest();
            employeeRequest.setEmpId(employee.getEmpId());
            employeeRequest.setEmpName(employee.getEmpName());
            employeeRequest.setDesignation(employee.getDesignation());
            employeeRequest.setPrimaryWorkLocation(employee.getPrimaryWorkLocation());
            employeeRequestList.add(employeeRequest);

        }
        return null;
    }

        private void prepareEmployee(EmployeeRequest employeeRequest,Employee employee){
            employee.setEmpId(employeeRequest.getEmpId());
            employee.setEmpName(employeeRequest.getEmpName());
            employee.setDesignation(employeeRequest.getDesignation());
            employee.setPrimaryWorkLocation(employeeRequest.getPrimaryWorkLocation());
            employee.setCreatedDate(new Date());
            employee.setActive(true);
            prepareSkill(employeeRequest.getSkills(),employee);
            prepareProject(employeeRequest.getProjects(),employee);
        }

        private void preparePersonalDetails(EmployeeRequest employeeRequest, PersonalDetails personalDetails, Employee employee){
            Address address = new Address();
            personalDetails.setOfficialEmailAddr(employeeRequest.getOfficialEmailAddr());
            personalDetails.setEmailAddr(employeeRequest.getEmailAddr());
            personalDetails.setExtensionNumber(employeeRequest.getExtensionNumber());
            personalDetails.setMobileNumber(employeeRequest.getMobileNumber());
            prepareAddress(employeeRequest, address);
            personalDetails.setAddress(address);
        }

        private void prepareAddress(EmployeeRequest employeeRequest,Address address){

            address.setAddressLine(employeeRequest.getAddressLine());
            address.setCity(employeeRequest.getCity());
            address.setState(employeeRequest.getState());
            address.setCountry(employeeRequest.getCountry());
            address.setPincode(employeeRequest.getPincode());
        }

        private void prepareSkill(List<SkillRequest> skillList, Employee employee) {
            List<Skill> skills = new ArrayList<>();
            for(SkillRequest skillRequest : skillList){
                Skill skill = new Skill();
                skill.setSkillName(skillRequest.getSkillName());
                skill.setExperience(skillRequest.getExperience());
                skill.setEmployee(employee);
                skills.add(skill);
            }
            employee.setSkillList(skills);
        }


        private void prepareProject(List<ProjectRequest> projectRequestList, Employee employee) {
            List<Project> projects= new ArrayList<>();
            for (ProjectRequest projectRequest : projectRequestList){
                Project project = new Project();
                project.setProjectName(projectRequest.getProjectName());
                project.setReportingTo(projectRequest.getReportingTo());
                project.setDeliveryHead(projectRequest.getDeliveryHead());
                project.setActive(true);
                project.setEmployee(employee);
                projects.add(project);
            }
            employee.setProjectList(projects);
        }
}


