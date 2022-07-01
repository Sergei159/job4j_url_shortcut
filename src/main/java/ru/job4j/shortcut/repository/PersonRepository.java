package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.model.Person;
import ru.job4j.shortcut.model.Site;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findPersonByLogin(String login);

    @Transactional
    Person findPersonBySite(Site site);


}