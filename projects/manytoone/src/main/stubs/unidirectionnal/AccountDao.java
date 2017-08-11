package fr.fteychene.orm.jpabasics.manytoone;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by fteychene on 14/03/17.
 */
@Repository
public class AccountDao {

    @PersistenceContext
    private EntityManager em;


    public Account create(Account account) {
        return null;
    }

    public Account find(Long id) {
        return null;
    }
}
