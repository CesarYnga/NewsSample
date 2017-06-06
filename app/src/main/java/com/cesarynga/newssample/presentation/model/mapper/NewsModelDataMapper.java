package com.cesarynga.newssample.presentation.model.mapper;

import com.cesarynga.newssample.domain.model.News;
import com.cesarynga.newssample.presentation.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsModelDataMapper {

    public NewsModel transform(News news) {
        NewsModel newsModel = new NewsModel();
        newsModel.setTitle(news.getTitle());
        newsModel.setDetail(news.getDetail());
        newsModel.setImageUrl(news.getImageUrl());
        return newsModel;
    }

    public List<NewsModel> transform(List<News> newsList) {
        List<NewsModel> newsModelList = new ArrayList<>();
        for(News news : newsList) {
            newsModelList.add(transform(news));
        }
        return newsModelList;
    }

    public News transform(NewsModel newsModel) {
        News news = new News();
        news.setTitle(newsModel.getTitle());
        news.setDetail(newsModel.getDetail());
        news.setImageUrl(newsModel.getImageUrl());
        return news;
    }
}
