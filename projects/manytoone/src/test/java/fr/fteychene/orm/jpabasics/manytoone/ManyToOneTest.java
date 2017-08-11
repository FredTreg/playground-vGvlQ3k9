package fr.fteychene.orm.jpabasics.manytoone;

import fr.fteychene.orm.jpabasics.AbstractJpaTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManyToOneTest extends AbstractJpaTest {

   @Autowired
   AccountDao accountDao;

   @Test
   public void _1_test() {

      Person person = new Person();
      person.setName("Augusto");

      Account account1 = new Account();
      account1.setBalance(100);
      account1.setPerson(person);
      accountDao.create(account1);

      assertThat(account1).isNotNull();
      assertThat(account1.getId()).isNotNull();
      assertThat(account1.getPerson()).isNotNull();
      assertThat(account1.getPerson().getId()).isNotNull();

      Account account2 = new Account();
      account2.setBalance(200);
      account2.setPerson(person);
      accountDao.create(account2);

      assertThat(account2).isNotNull();
      assertThat(account2.getId()).isNotNull();
      assertThat(account2.getPerson()).isNotNull();
      assertThat(account2.getPerson().getId()).isNotNull();

   }

   @Test
   public void _2_load() {
      Account account = accountDao.find(1l);
      assertThat(account).isNotNull();
      Person person = account.getPerson();
      assertThat(person.getId()).isNotNull();
      assertThat(person.getName()).isEqualTo("Augusto");


   }
}