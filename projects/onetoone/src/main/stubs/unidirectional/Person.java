package fr.fteychene.orm.jpabasics.onetoone.unidirectional;

import javax.persistence.*;

public class Person {

   private Long id;

   private String name;

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
   }
}
