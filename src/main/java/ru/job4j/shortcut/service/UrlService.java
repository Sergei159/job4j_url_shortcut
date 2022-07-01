package ru.job4j.shortcut.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.dto.UrlDTO;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.repository.UrlRepository;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    public ResponseEntity convert(Map<String, String> body) {
        String url = body.get("url");
        Optional<Url> urlEntity = urlRepository.findUrlByName(url);
        if (url == null || url.isEmpty() || urlEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid url value");
        }
        String shortcut = UUID.randomUUID().toString();
        urlRepository.save(Url.of(url, shortcut));
        return ResponseEntity.ok("\"shortcut\" :" + "\"" + shortcut + "\"");
    }

    public ResponseEntity redirect(String code) {
        var url = transactionalUpdateCalls(code);
        if (url.isPresent()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(url.get().getName()));
            return new ResponseEntity(httpHeaders, HttpStatus.FOUND);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Url not found");
    }

    public List<UrlDTO> statistic() {
        List<Url> urls = new ArrayList<>();
        urlRepository.findAll().forEach(urls::add);
        return urls.stream().map(url -> UrlDTO.of(url.getName(), url.getCalls()))
                .collect(Collectors.toList());
    }

    @Transactional
    private Optional<Url> transactionalUpdateCalls(String shortcut) {
        urlRepository.updateCalls(shortcut);
        return urlRepository.findUrlByShortcut(shortcut);
    }


}
