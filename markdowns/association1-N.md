# Jointure

@[ManyToOne]({"stubs": ["src/main/stubs/unidirectionnal/Account.java", "src/main/stubs/unidirectionnal/Person.java", "src/main/stubs/unidirectionnal/AccountDao.java"],"command": "fr.fteychene.orm.jpabasics.manytoone.ManyToOneTest", "project":"manytoone"})

@[OneToMany]({"stubs": ["src/main/stubs/Account.java", "src/main/stubs/Person.java", "src/main/stubs/PersonDao.java"],"command": "fr.fteychene.orm.jpabasics.manytoone.ManyToOneTest", "project":"onetomany"})

@[Bidirectionnal]({
    "stubs": ["src/main/stubs/bidirectionnal/Account.java", "src/main/stubs/bidirectionnal/Person.java", "src/main/stubs/bidirectionnal/PersonDao.java"],
    "command": "fr.fteychene.orm.jpabasics.manytoone.ManyToOneBidirectionnalTest", 
    "project":"manytoone"
    })