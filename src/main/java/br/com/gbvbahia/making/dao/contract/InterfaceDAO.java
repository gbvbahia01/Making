package br.com.gbvbahia.making.dao.contract;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDAO<Etity, ID extends Serializable> {

   String PERSISTENCE_UNIT = "dao.test.tables.derby";

   void create(Etity entity) throws ConstrainViolationException;

   void update(Etity entity) throws ConstrainViolationException;

   void remove(Etity entity) throws ConstrainViolationException;

   Etity find(ID id);

   List<Etity> findAll();

   void closeEntityManager();

}
