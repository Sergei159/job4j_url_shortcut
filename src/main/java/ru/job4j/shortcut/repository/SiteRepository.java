package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Site;

import java.util.Optional;

@Service
public interface SiteRepository extends CrudRepository<Site, Integer> {
    Optional<Site> findSiteByName(String name);
}