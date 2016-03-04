package br.com.gbvbahia.making.dao.contract;

public class ConstrainViolationException extends Exception {

   private static final long serialVersionUID = -5883667031300326395L;

   public ConstrainViolationException(String message) {
      super(message);
   }

}
