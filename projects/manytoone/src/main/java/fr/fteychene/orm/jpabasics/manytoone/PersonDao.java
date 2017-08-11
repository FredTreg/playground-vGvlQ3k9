package fr.fteychene.orm.jpabasics.manytoone;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * Created by fteychene on 15/03/17.
 */
@Repository
public class PersonDao {

    @PersistenceContext
    EntityManager em;

    public Person create(Person person) {
        if (person.getAccounts() != null) {
            person.setAccounts(person.getAccounts().stream()
                    .map(a -> em.merge(a))
                    .map(a -> {a.setPerson(person); return a;})
                    .collect(Collectors.toList()));
        }
        em.persist(person);
        return person;
    }
}
