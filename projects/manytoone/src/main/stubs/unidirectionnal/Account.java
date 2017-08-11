package fr.fteychene.orm.jpabasics.manytoone;

import javax.persistence.*;

@Entity
@Table
public class Account {

   private Long id;

   private double balance;


   private Person person;

   public Long getId() {
      return id;
   }

   public double getBalance() {
      return balance;
   }

   public void setBalance(double balance) {
      this.balance = balance;
   }

   public Person getPerson() {
      return person;
   }

   void setPerson(Person person) {
      this.person = person;
   }

   @Override
   public String toString() {
      return "Account{" +
            "id=" + id +
            ", balance=" + balance +
            '}';
   }
}
