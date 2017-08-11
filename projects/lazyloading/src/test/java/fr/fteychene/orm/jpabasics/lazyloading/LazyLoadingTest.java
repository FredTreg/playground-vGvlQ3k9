package fr.fteychene.orm.jpabasics.lazyloading;

import fr.fteychene.orm.jpabasics.AbstractJpaTest;
import junit.framework.Assert;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import sun.reflect.Reflection;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by fteychene on 20/03/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LazyLoadingTest extends AbstractJpaTest {

    @Autowired
    @Qualifier("lazyTestDao")
    TestDao testDao;

    @Test
    public void _1_testFind() {
        Person person = new Person();
        person.setName("Lazarus");

        Account account1 = new Account();
        account1.setBalance(2000.0);
        Account account2 = new Account();
        account2.setBalance(8000.0);

        person.getAccounts().addAll(Arrays.asList(account1, account2));
        testDao.create(person);

        Optional<Person> actual = testDao.find(person.getId());
        assertTrue("Person from database should exist", actual.isPresent());

        assertThat(actual.get())
                .matches(p -> p.getAccounts() instanceof PersistentBag, "Accounts should be a proxy from hibernate")
                .matches(p -> emf().getPersistenceUnitUtil().isLoaded(p, "accounts"), "Accounts should have been initialized in the transaction");
        assertThat(actual.get().getAccounts()).size().isEqualTo(2);
    }
}
