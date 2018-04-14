package com.minwoo.sentence.daos;

import com.minwoo.sentence.models.Word;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("SUBJECTIVE")
public interface SubjectClient {
    @GetMapping("/")
    public Word getWord();

    static class HystrixClientFallback implements SubjectClient {
        @Override
        public Word getWord() {
            return new Word();
        }
    }
}
