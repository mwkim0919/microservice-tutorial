package com.minwoo.sentence.daos;

import com.minwoo.sentence.models.Word;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("VERB")
public interface VerbClient {
    @GetMapping("/")
    public Word getWord();
}
