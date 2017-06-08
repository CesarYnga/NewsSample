package com.cesarynga.newssample.data.repository;

import com.cesarynga.newssample.data.entity.NewsEntity;
import com.cesarynga.newssample.data.entity.mapper.NewsEntityDataMapper;
import com.cesarynga.newssample.data.repository.datasource.NewsDataSource;
import com.cesarynga.newssample.data.repository.datasource.NewsDataSourceFactory;
import com.cesarynga.newssample.domain.model.News;
import com.cesarynga.newssample.domain.repository.NewsRepository;

import java.util.List;

public class NewsDataRepository implements NewsRepository {

    private final NewsDataSourceFactory newsDataSourceFactory;
    private final NewsEntityDataMapper newsEntityDataMapper;

    public NewsDataRepository(NewsDataSourceFactory newsDataSourceFactory, NewsEntityDataMapper newsEntityDataMapper) {
        this.newsDataSourceFactory = newsDataSourceFactory;
        this.newsEntityDataMapper = newsEntityDataMapper;
    }

    @Override
    public List<News> newsList() throws Exception {
        final NewsDataSource newsDataSource = this.newsDataSourceFactory.createNetworkDataStore();
        List<NewsEntity> newsEntityList = newsDataSource.newsEntityList();
        return newsEntityDataMapper.transform(newsEntityList);
    }

    @Override
    public News insetNews(News news) throws Exception {
        final NewsDataSource newsDataSource = this.newsDataSourceFactory.createNetworkDataStore();
        NewsEntity newsEntity = newsDataSource.insertNewsEntity(newsEntityDataMapper.transform(news));
        return newsEntityDataMapper.transform(newsEntity);
    }
}
