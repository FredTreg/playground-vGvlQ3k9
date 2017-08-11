package fr.fteychene.orm.jpabasics.onetoone.bidirectional;

import javax.persistence.*;

@Entity(name = "Account_BI")
public class Account {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Column(name="balance")
   private double balance;

   @OneToOne
   @JoinColumn(name="PERSON_ID")
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
