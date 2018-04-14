package com.minwoo.sentence.services;

import com.minwoo.sentence.models.Word;

public interface WordService {

    Word getSubject();

    Word getFallbackSubject();

    Word getVerb();

    Word getFallbackVerb();

    Word getArticle();

    Word getFallbackArticle();

    Word getAdjective();

    Word getFallbackAdjective();

    Word getNoun();

    Word getFallbackNoun();

}
