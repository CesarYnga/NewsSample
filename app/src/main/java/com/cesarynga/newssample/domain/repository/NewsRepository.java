package com.cesarynga.newssample.domain.repository;

import com.cesarynga.newssample.domain.model.News;

import java.util.List;

public interface NewsRepository {

    List<News> newsList() throws Exception;

    News insetNews(News news) throws Exception;
}
