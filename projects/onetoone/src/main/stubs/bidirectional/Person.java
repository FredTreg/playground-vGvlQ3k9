package fr.fteychene.orm.jpabasics.onetoone.bidirectional;

import javax.persistence.*;

public class Person {

   private Long id;

   private String name;

   private Account account;

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Account getAccount() {
      return account;
   }

   void setAccount(Account account) {
      this.account = account;
   }

   @Override
   public String toString() {
      return "OneToOnePerson{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
   }
}
