package ru.job4j.shortcut.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.dto.UrlDTO;
import ru.job4j.shortcut.service.UrlService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/url")
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    /**
     *Запрос для регистрации URL
     * @return Объект содержащий уникальный код,
     * позволяющий перенаправить клиент по соответствующему URL
     */
    @PostMapping("/convert")
    public ResponseEntity convert(@RequestBody Map<String, String> body) {
        return service.convert(body);
    }


    /**
     * Запрос переадресует клиента по URI соответствующему уникальному коду
     * @return
     */
    @GetMapping("/redirect/{code}")
    public ResponseEntity redirect(@PathVariable String code) throws URISyntaxException {
        return service.redirect(code);
    }


    /**
     * Запрос на получение статистики посещения определенных URL
     * @return
     */
    @GetMapping("/statistic")
    public List<UrlDTO> statistic() {
        return service.statistic();
    }
}
