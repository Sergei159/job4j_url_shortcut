package ru.job4j.shortcut.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.service.SiteService;


@RestController
@RequestMapping("/site")
public class SiteController {
    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }
    /**
     * Регистрация сайта по URL
     * @return  информацию с логином и паролем для доступа пользователя
     */
    @PostMapping("/registration")
    public ResponseEntity<String> create(@RequestBody Site site) {
        return siteService.registration(site);
    }

}