package com.ra.demo.service.iplm;

import com.ra.demo.dao.IDAO;
import com.ra.demo.entity.Employee;
import com.ra.demo.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeServiceIplm implements IService<Employee,Integer,String> {
    @Autowired
    private IDAO<Employee,Integer,String> dao;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public boolean addNew(Employee employee) {
        return dao.addNew(employee);
    }

    @Override
    @Transactional
    public boolean update(Employee employee) {
        return dao.update(employee);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getByName(String name) {
        return dao.getByName(name);
    }
}
