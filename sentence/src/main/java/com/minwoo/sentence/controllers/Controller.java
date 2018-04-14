package com.minwoo.sentence.controllers;

import com.minwoo.sentence.services.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private SentenceService sentenceService;

    @GetMapping("/sentence")
    public @ResponseBody
    String getSentence() {
        return
                "<h3>Some Sentences</h3><br/>" +
                        sentenceService.buildSentenceUsingHystrix() + "<br/><br/>" +
                        sentenceService.buildSentenceUsingHystrix() + "<br/><br/>" +
                        sentenceService.buildSentenceUsingHystrix() + "<br/><br/>" +
                        sentenceService.buildSentenceUsingHystrix() + "<br/><br/>" +
                        sentenceService.buildSentenceUsingHystrix() + "<br/><br/>"
                ;
    }

}
