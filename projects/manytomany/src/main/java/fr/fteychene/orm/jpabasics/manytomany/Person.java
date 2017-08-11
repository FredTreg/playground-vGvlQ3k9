package fr.fteychene.orm.jpabasics.manytomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String name;


    @ManyToMany
    @JoinTable(
            name = "ManyToMany_PERSON_ACCOUNT",
            joinColumns = {@JoinColumn(name = "PERSON_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_ID")}
    )
    private List<Account> accounts = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "ManyToManyPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
