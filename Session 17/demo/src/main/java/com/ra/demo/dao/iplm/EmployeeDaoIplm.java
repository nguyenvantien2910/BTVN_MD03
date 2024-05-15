package com.ra.demo.dao.iplm;

import com.ra.demo.dao.IDAO;
import com.ra.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoIplm implements IDAO<Employee, Integer, String> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.openSession();
        try {
            List employees = session.createQuery("from Employee").list();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Employee findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            Employee employee = session.get(Employee.class, id);
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean addNew(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Employee> getByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            if (name == null || name.isEmpty())
                name = "%";
            else
                name = "%" + name + "%";
            List list = session.createQuery("from Employee where empName like :empName")
                    .setParameter("empName", name)
                    .list();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}

