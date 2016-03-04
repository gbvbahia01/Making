package br.com.gbvbahia.making.dao.contract;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class AbstractDAO<Entity, ID extends Serializable> implements
InterfaceDAO<Entity, ID> {

   private Class<Entity> entityClass;

   protected AbstractDAO(Class<Entity> entityClasss) {
      this.entityClass = entityClasss;
   }

   protected abstract EntityManager getEntityManager();

   @Override
   public void create(Entity entity) throws ConstrainViolationException {
      this.getEntityManager().getTransaction().begin();
      try {
         this.validarEntity(entity);
         this.getEntityManager().persist(entity);
      } catch (javax.validation.ConstraintViolationException e) {
         this.validadionException(e.getConstraintViolations());
      } finally {
         this.getEntityManager().getTransaction().commit();
      }
   }

   @Override
   public void update(Entity entity) throws ConstrainViolationException {
      this.getEntityManager().getTransaction().begin();
      try {
         this.validarEntity(entity);
         this.getEntityManager().merge(entity);
      } catch (javax.validation.ConstraintViolationException e) {
         this.validadionException(e.getConstraintViolations());
      } finally {
         this.getEntityManager().getTransaction().commit();
      }
   }

   @Override
   public void remove(Entity entity) throws ConstrainViolationException {
      this.getEntityManager().getTransaction().begin();
      try {
         this.getEntityManager().remove(this.getEntityManager().merge(entity));
         this.getEntityManager().flush();
      } catch (PersistenceException p) {
      } catch (javax.validation.ConstraintViolationException e) {
         this.validadionException(e.getConstraintViolations());
      } finally {
         this.getEntityManager().getTransaction().commit();
      }
   }

   @Override
   public Entity find(ID id) {
      try {
         return this.getEntityManager().find(this.entityClass, id);
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public List<Entity> findAll() {
      javax.persistence.criteria.CriteriaQuery cq = this.getEntityManager()
            .getCriteriaBuilder().createQuery();
      cq.select(cq.from(this.entityClass));
      return this.getEntityManager().createQuery(cq).getResultList();
   }

   @Override
   public void closeEntityManager() {
      this.getEntityManager().close();
   }

   @SuppressWarnings("rawtypes")
   protected void validadionException(final Set<ConstraintViolation<?>> erros)
         throws ConstrainViolationException {
      String erro = "";
      for (ConstraintViolation cv : erros) {
         erro += cv.getMessage() + " ";
      }
      throw new ConstrainViolationException(erro);
   }

   protected void validarEntity(Entity entity)
         throws ConstrainViolationException {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      Set<ConstraintViolation<Entity>> msgs = validator.validate(entity);
      if (!msgs.isEmpty()) {
         this.validadionToNegocioException(msgs);
      }
   }

   @SuppressWarnings("rawtypes")
   private void validadionToNegocioException(
         final Set<ConstraintViolation<Entity>> erros)
         throws ConstrainViolationException {
      String erro = "";
      for (ConstraintViolation cv : erros) {
         erro += cv.getMessage() + " ";
      }
      throw new ConstrainViolationException(erro);
   }
}
