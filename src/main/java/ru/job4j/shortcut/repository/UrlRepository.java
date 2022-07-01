package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.model.Url;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    Optional<Url> findUrlByShortcut(String shortcut);

    @Transactional
    @Modifying
    @Query(value = "update Url set calls = calls + 1 where shortcut = :shortcut")
    void updateCalls(@Param("shortcut") String shortcut);


    Optional<Url> findUrlByName(String name);
}