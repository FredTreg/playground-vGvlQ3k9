package fr.fteychene.orm.jpabasics.manytomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Account {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name="balance")
   private double balance;

   @ManyToMany(mappedBy = "accounts")
   private List<Person> people = new ArrayList<>();

   public Long getId() {
      return id;
   }

   public double getBalance() {
      return balance;
   }

   public void setBalance(double balance) {
      this.balance = balance;
   }

   public List<Person> getPeople() {
      return people;
   }

   public void setPeople(List<Person> people) {
      this.people = people;
   }

   @Override
   public String toString() {
      return "Account{" +
            "id=" + id +
            ", balance=" + balance +
            ", people=" + people +
            '}';
   }
}
