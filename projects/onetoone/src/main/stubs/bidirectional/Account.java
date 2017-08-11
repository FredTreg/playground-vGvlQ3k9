package fr.fteychene.orm.jpabasics.onetoone.bidirectional;

import javax.persistence.*;

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

   public void setPerson(Person person) {
      this.person = person;
      person.setAccount(this);
   }

   @Override
   public String toString() {
      return "OneToOneAccount{" +
            "id=" + id +
            ", balance=" + balance +
            ", person=" + person +
            '}';
   }
}
