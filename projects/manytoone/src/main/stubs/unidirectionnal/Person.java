package fr.fteychene.orm.jpabasics.manytoone;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Person {

   private Long id;

   private String name;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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
