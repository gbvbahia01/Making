package br.com.gbvbahia.making.dao;

import br.com.gbvbahia.making.dao.contract.AbstractDAO;
import br.com.gbvbahia.making.entity.jsr303.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeDao extends AbstractDAO<Employee, Long> {

   protected EmployeeDao() {
      super(Employee.class);
   }

   private EntityManager manager = null;

   @Override
   protected EntityManager getEntityManager() {
      if (this.manager == null) {
         EntityManagerFactory factory = Persistence
               .createEntityManagerFactory(PERSISTENCE_TEST_UNIT);
         this.manager = factory.createEntityManager();
      }
      return this.manager;
   }

}
