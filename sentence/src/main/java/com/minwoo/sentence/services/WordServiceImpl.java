package com.minwoo.sentence.services;

import com.minwoo.sentence.daos.*;
import com.minwoo.sentence.models.Word;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.Scheduler;

import java.util.concurrent.Executor;


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

    // For adding Asynchronous Behavior (Reactive)
    // This improves the performance of the sentence server
    @Autowired
    private Executor executor;


    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(
            fallbackMethod = "getFallbackSubject",
            commandProperties = {
                    // Over 20% failure rate in 10 second period, open breaker
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"),
                    // After 1 second, try closing breaker
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000")
            }
    )
    public Word getSubject() {
        return subjectClient.getWord();
    }

    @Override
    public Word getFallbackSubject() {
        return new Word("Someone", Word.Role.subject);
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackVerb")
    public Word getVerb() {
        return verbClient.getWord();
    }

//    @Override
//    // If this fails due to service not being able, then redirect to the method specified in the annotation
//    @HystrixCommand(fallbackMethod = "getFallbackVerb")
//    // The following is to make service call async ("reactively")
//    public Observable<Word> getVerb() {
//        return Observable.fromCallable(
//                () -> new Word(verbClient.getWord().getWord(), Word.Role.verb)
//        ).subscribeOn(Scheduler.from(executor));
//    }

    public Word getFallbackVerb() {
        return new Word("does", Word.Role.verb);
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackArticle")
    public Word getArticle() {
        return articleClient.getWord();
    }

    @Override
    public Word getFallbackArticle() {
        return new Word("Article Failed", Word.Role.article);
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackAdjective")
    public Word getAdjective() {
        return adjectiveClient.getWord();
    }

    @Override
    public Word getFallbackAdjective() {
        return new Word("Adjective Failed", Word.Role.adjective);
    }

    @Override
    // If this fails due to service not being able, then redirect to the method specified in the annotation
    @HystrixCommand(fallbackMethod = "getFallbackNoun")
    public Word getNoun() {
        return nounClient.getWord();
    }

    @Override
    public Word getFallbackNoun() {
        return new Word("Something", Word.Role.noun);
    }

}
