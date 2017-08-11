package fr.fteychene.orm.jpabasics.entity;

import fr.fteychene.orm.jpabasics.AbstractJpaTest;
import fr.fteychene.orm.jpabasics.JpaApplication;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by fteychene on 14/03/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = TestEntityBottomUp.OverrideConfiguration.class)
@ActiveProfiles("embeddedDatasource")
@Transactional
public class TestEntityBottomUp extends AbstractJpaTest {

    Logger logger = LoggerFactory.getLogger(TestEntityBottomUp.class);

    @Configuration
    @JpaApplication.OverrideConfiguration
    @PropertySource("classpath:singleentity/override.properties")
    static class OverrideConfiguration {

        @Bean
        @Qualifier("scripts")
        public String[] startupScripts() {
            return new String[] {"singleentity/create-db.sql"};
        }
    }

    @Test
    public void _1_create() {
        User singleTablePerson = new User();
        singleTablePerson.setFirstName("Linus");
        singleTablePerson.setLastName("Torvald");
        singleTablePerson.setDateOfBirth(Date.valueOf(LocalDate.of(1969, 12, 28)));
        singleTablePerson.setMartialStatus(MaritalStatus.MARIED);
        singleTablePerson.setEmail("linux.torvald@linux.org");
        singleTablePerson.setCounter(10);

        logger.info("Insert "+ singleTablePerson);

        em().persist(singleTablePerson);

        assertThat(singleTablePerson).isNotNull();
        assertThat(singleTablePerson.getId()).isNotNull();
        assertThat(singleTablePerson.getFirstName()).isEqualTo("Linus");
        assertThat(singleTablePerson.getLastName()).isEqualTo("Torvald");
        assertThat(singleTablePerson.getDateOfBirth()).isEqualTo(Date.valueOf(LocalDate.of(1969, 12, 28)));
        assertThat(singleTablePerson.getMartialStatus()).isEqualTo(MaritalStatus.MARIED);
        assertThat(singleTablePerson.getEmail()).isEqualTo("linux.torvald@linux.org");
        assertThat(singleTablePerson.getCounter()).isEqualTo(10);


    }

    @Test
    public void _2_load() {
        User singleTablePerson = em().find(User.class, 1L);

        logger.info("Loading User from database");

        assertThat(singleTablePerson).isNotNull();
        assertThat(singleTablePerson.getId()).isNotNull();
        assertThat(singleTablePerson.getFirstName()).isEqualTo("Linus");
        assertThat(singleTablePerson.getLastName()).isEqualTo("Torvald");
        assertThat(singleTablePerson.getDateOfBirth()).isEqualTo(Timestamp.valueOf(LocalDateTime.of(1969, 12, 28, 0, 0, 0)));
        assertThat(singleTablePerson.getMartialStatus()).isEqualTo(MaritalStatus.MARIED);
        assertThat(singleTablePerson.getEmail()).isEqualTo("linux.torvald@linux.org");
        assertThat(singleTablePerson.getCounter()).isNull();
    }
}
