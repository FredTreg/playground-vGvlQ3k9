package fr.fteychene.orm.jpabasics.lazyloading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by fteychene on 20/03/17.
 */
@Repository
@Qualifier("lazyTestDao")
@Transactional
public class TestDao {

    private static final Logger logger =  LoggerFactory.getLogger(TestDao.class);

    @PersistenceContext
    EntityManager em;

    public void create(Person person) {
        logger.info("Create person "+ person);
        em.persist(person);
        if (person.getAccounts() != null) {
            person.setAccounts(person.getAccounts().stream()
                    .map(a -> em.merge(a))
                    .collect(Collectors.toList()));
        }
    }

    public Optional<Person> find(Long id) {
        logger.info("Load person with id "+ id);
        return Optional.ofNullable(em.find(Person.class, id));
    }

}
