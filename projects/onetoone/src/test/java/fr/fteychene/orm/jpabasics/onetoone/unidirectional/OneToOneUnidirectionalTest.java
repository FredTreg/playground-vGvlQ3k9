package fr.fteychene.orm.jpabasics.onetoone.unidirectional;

import fr.fteychene.orm.jpabasics.AbstractJpaTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneToOneUnidirectionalTest extends AbstractJpaTest {

   @Test
   public void _1_test() {
      Person person = new Person();
      person.setName("John");

      em().persist(person);
      assertThat(person.getId()).isNotNull();

      Account account = new Account();
      account.setBalance(120.20);

      account.setPerson(person);

      em().persist(account);

      assertThat(account.getId()).isNotNull();
      assertThat(account.getPerson().getId()).isNotNull();
   }

   @Test
   public void _2_load() {
      Account account = em().find(Account.class, 1L);

      assertThat(account.getId()).isNotNull();
      assertThat(account.getBalance()).isEqualTo(120.12, offset(0.1));

      Person person = account.getPerson();
      assertThat(person.getId()).isNotNull();
      assertThat(person.getName()).isEqualTo("John");

   }

}