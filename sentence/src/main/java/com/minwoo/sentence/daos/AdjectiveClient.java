package com.minwoo.sentence.daos;

import com.minwoo.sentence.models.Word;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ADJECTIVE")
public interface AdjectiveClient {
    @GetMapping("/")
    public Word getWord();

    static class HystrixClientFallback implements AdjectiveClient {
        @Override
        public Word getWord() {
            return new Word();
        }
    }
}
