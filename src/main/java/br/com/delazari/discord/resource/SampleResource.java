package br.com.delazari.discord.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delazari.discord.service.SampleService;

@RestController
@RequestMapping(value = "/sample", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SampleResource {

    @Autowired
    private SampleService service;

    @GetMapping("/all")
    public String albuns() {
        return service.getSampleList();
    }
}
