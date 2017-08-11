package fr.fteychene.orm.jpabasics.onetomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Person {

   private Long id;

   private String name;

   private List<Account> accounts = new ArrayList<>();


   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Account> getAccounts() {
      return accounts;
   }

   public void setAccounts(List<Account> accounts) {
      this.accounts = accounts;
   }

   @Override
   public String toString() {
      return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
   }
}
