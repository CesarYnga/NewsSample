package com.cesarynga.newssample.data.entity.mapper;

import com.cesarynga.newssample.data.entity.NewsEntity;
import com.cesarynga.newssample.domain.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsEntityDataMapper {

    public News transform(NewsEntity newsEntity) {
        News news = new News();
        news.setTitle(newsEntity.getTitle());
        news.setDetail(newsEntity.getDetail());
        news.setImageUrl(newsEntity.getImageUrl());
        return news;
    }

    public List<News> transform(List<NewsEntity> newsEntityList) {
        List<News> newsList = new ArrayList<>();
        for(NewsEntity newsEntity : newsEntityList) {
            newsList.add(transform(newsEntity));
        }
        return newsList;
    }

    public NewsEntity transform(News news) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle(news.getTitle());
        newsEntity.setDetail(news.getDetail());
        newsEntity.setImageUrl(news.getImageUrl());
        return newsEntity;
    }
}
