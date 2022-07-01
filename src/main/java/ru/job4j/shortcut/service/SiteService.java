package ru.job4j.shortcut.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Person;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.PersonRepository;
import ru.job4j.shortcut.repository.SiteRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class SiteService {

    private final PersonRepository personRepository;
    private final SiteRepository siteRepository;
    private final BCryptPasswordEncoder encoder;


    public SiteService(PersonRepository personRepository, SiteRepository siteRepository, BCryptPasswordEncoder encoder) {
        this.personRepository = personRepository;
        this.siteRepository = siteRepository;
        this.encoder = encoder;
    }


    public ResponseEntity<String> registration(Site site) {
        Optional<Site> optSite = siteRepository.findSiteByName(site.getName());
        if (optSite.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(("registration : false. Site are already registered"));
        }
        siteRepository.save(site);
        String login = site.getName();
        String password = UUID.randomUUID().toString();
        personRepository.save(Person.of(login, encoder.encode(password), site));
        String body = String.format("\"registration\" : \"%s\",\n \"login\" : \"%s\", \"password\" : \"%s\"", "true", login, password);
        return ResponseEntity.ok().body(body);
    }

}
