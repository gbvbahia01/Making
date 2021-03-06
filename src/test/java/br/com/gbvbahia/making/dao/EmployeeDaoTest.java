package br.com.gbvbahia.making.dao;

import br.com.gbvbahia.maker.MakeEntity;
import br.com.gbvbahia.making.entity.jsr303.Employee;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeDaoTest {
  private EmployeeDao employeeDao = null;

  @Before
  public void setup() throws Exception {
    this.employeeDao = new EmployeeDao();
    this.employeeDao.getEntityManager();
  }

  @After
  public void clear() {
    this.employeeDao.getEntityManager().close();
  }

  @Test
  public void testCreateEmployee() throws Exception {
    Employee employee = MakeEntity.make(Employee.class);
    this.employeeDao.create(employee);
    Assert.assertFalse("The emplyee list cannot be empty", this.employeeDao
        .findAll().isEmpty());
  }

}
