package br.com.gbvbahia.making.entity.jsr303;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE_TB")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID_EMPLOYEE")
   private Long Id;

   @NotNull(message = "{Employee.name.null}")
   @Size(max = 200, message = "{Employee.name.size}")
   @Column(name = "NAME", length = 200, nullable = false)
   private String name;

   @NotNull(message = "{Employee.dateBirth.null}")
   @Temporal(TemporalType.DATE)
   @Past(message = "Employee.dateBirth.past")
   @Column(name = "data_feriado", nullable = false)
   private Date dateBirth;

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.Id == null) ? 0 : this.Id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (this.getClass() != obj.getClass())
         return false;
      Employee other = (Employee) obj;
      if (this.Id == null) {
         if (other.Id != null)
            return false;
      } else if (!this.Id.equals(other.Id))
         return false;
      return true;
   }

   public Long getId() {
      return this.Id;
   }

   public void setId(Long id) {
      this.Id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Date getDateBirth() {
      return this.dateBirth;
   }

   public void setDateBirth(Date dateBirth) {
      this.dateBirth = dateBirth;
   }

}
