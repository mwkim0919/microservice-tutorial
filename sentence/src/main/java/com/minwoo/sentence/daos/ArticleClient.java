package com.minwoo.sentence.daos;

import com.minwoo.sentence.models.Word;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ARTICLE")
public interface ArticleClient {
    @GetMapping("/")
    public Word getWord();

    static class HystrixClientFallback implements ArticleClient {
        @Override
        public Word getWord() {
            return new Word();
        }
    }
}
