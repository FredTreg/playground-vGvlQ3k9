package fr.fteychene.orm.jpabasics.onetomany;

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
        return null;
    }

    public Person find(Long id) {
        return null;
    }
}
