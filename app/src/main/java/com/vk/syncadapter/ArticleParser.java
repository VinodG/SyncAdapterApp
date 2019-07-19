package com.vk.syncadapter;

import org.json.JSONObject;

public class ArticleParser {
    public static Article parse(JSONObject jsonArticle) {
        Article article = new Article();
        article.setId(jsonArticle.optString("id"));
        article.setTitle(jsonArticle.optString("title"));
        article.setContent(jsonArticle.optString("content"));
        article.setLink(jsonArticle.optString("link"));
        return article;
    }
}
