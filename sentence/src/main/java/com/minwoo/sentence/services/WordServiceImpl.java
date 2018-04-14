package com.minwoo.sentence.services;

import com.minwoo.sentence.daos.*;
import com.minwoo.sentence.models.Word;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    VerbClient verbClient;
    @Autowired
    SubjectClient subjectClient;
    @Autowired
    ArticleClient articleClient;
    @Autowired
    AdjectiveClient adjectiveClient;
    @Autowired
    NounClient nounClient;


    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackSubject")
    public Word getSubject() {
        return subjectClient.getWord();
    }

    @Override
    public Word getFallbackSubject() {
        return new Word("Subject Failed");
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackVerb")
    public Word getVerb() {
        return verbClient.getWord();
    }

    @Override
    public Word getFallbackVerb() {
        return new Word("Verb Failed");
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackArticle")
    public Word getArticle() {
        return articleClient.getWord();
    }

    @Override
    public Word getFallbackArticle() {
        return new Word("Article Failed");
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackAdjective")
    public Word getAdjective() {
        return adjectiveClient.getWord();
    }

    @Override
    public Word getFallbackAdjective() {
        return new Word("Adjective Failed");
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackNoun")
    public Word getNoun() {
        return nounClient.getWord();
    }

    @Override
    public Word getFallbackNoun() {
        return new Word("Noun Failed");
    }

}
