package fr.fteychene.orm.jpabasics.manytomany;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by fteychene on 16/03/17.
 */
@Repository
public class PersonDao {

    @PersistenceContext
    EntityManager em;

    public Person create(Person person) {
        Optional.ofNullable(person.getAccounts()).ifPresent(accounts -> {
            accounts.forEach((Account account) -> em.persist(account));
        });
        em.persist(person);

        if (person.getAccounts() != null) {
            for (Account account : person.getAccounts()) {
                if (account.getPeople() != null) {
                    account.getPeople().add(person);
                } else {
                    account.setPeople(Arrays.asList(person));
                }
            }
        }
        return person;
    }

    public Person find(Long id) {
        return em.find(Person.class, id);
    }
}
