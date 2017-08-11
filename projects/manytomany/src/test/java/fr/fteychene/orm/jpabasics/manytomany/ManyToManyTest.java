package fr.fteychene.orm.jpabasics.manytomany;

import fr.fteychene.orm.jpabasics.AbstractJpaTest;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Offset;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManyToManyTest extends AbstractJpaTest {

    @Autowired
    PersonDao personDao;

    @Test
    public void _0_test_insert_person() {
        Person person1 = new Person();
        person1.setName("Augusto");
        Person actual = personDao.create(person1);

        assertThat(actual)
                .as("Insertion return something").isNotNull()
                .matches(p -> p.getId() != null, "Inserted person has generated id");
    }

    @Test
    public void _1_test_insert_person_with_accounts() {
        Person person1 = new Person();
        person1.setName("Augusto");
        Account account1 = new Account();
        account1.setBalance(100);
        Account account2 = new Account();
        account2.setBalance(200);

        person1.getAccounts().add(account1);
        person1.getAccounts().add(account2);

        Person actual = personDao.create(person1);

        assertThat(actual)
                .as("Insertion return something").isNotNull()
                .matches(p -> p.getId() != null, "Inserted person has generated id")
                .matches(p -> p.getAccounts() != null && !p.getAccounts().isEmpty(), "Inserted person has account");
        actual.getAccounts().stream().forEach(account -> {
            assertThat(account)
                    .matches(a -> a.getId() != null, "Account has id generated")
                    .matches(a -> a.getPeople() != null, "Account has persons")
                    .matches(a -> account.getPeople().contains(person1), "Account has inserted person");
        });
    }

    @Test
    @javax.transaction.Transactional
    public void _2_test_insert_multiple_person_with_accounts() {
        Person person1 = new Person();
        person1.setName("Augusto");
        Person person2 = new Person();
        person2.setName("Carlito");
        Account account1 = new Account();
        account1.setBalance(100);
        Account account2 = new Account();
        account2.setBalance(200);

        person1.getAccounts().add(account1);
        person1.getAccounts().add(account2);
        person2.getAccounts().add(account1);
        person2.getAccounts().add(account2);

        Person actual1 = personDao.create(person1);
        assertThat(actual1)
                .as("Insertion return something").isNotNull()
                .matches(p -> p.getId() != null, "Inserted person has generated id")
                .matches(p -> p.getAccounts() != null && !p.getAccounts().isEmpty(), "Inserted person has account");

        Person actual2 = personDao.create(person2);
        assertThat(actual2)
                .as("Insertion return something").isNotNull()
                .matches(p -> p.getId() != null, "Inserted person has generated id")
                .matches(p -> p.getAccounts() != null && !p.getAccounts().isEmpty(), "Inserted person has account");
        actual2.getAccounts().stream().forEach(account -> {
            assertThat(account)
                    .matches(a -> a.getId() != null, "Account has id generated")
                    .matches(a -> a.getPeople() != null, "Account has persons")
                    .matches(a -> account.getPeople().contains(person2), "Account has inserted person");
        });

        assertThat(person1.getAccounts()).hasSameElementsAs(actual2.getAccounts());
    }

    @Test
    @Transactional
    public void _3_load_person_with_accounts() {
        Person person = personDao.find(4L);
        assertThat(person).isNotNull();
        assertThat(person.getAccounts()).isNotNull().isNotEmpty().matches(l -> l.size() == 2);
    }

}