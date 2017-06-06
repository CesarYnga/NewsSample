package com.cesarynga.newssample.data.repository.datasource;

import com.cesarynga.newssample.data.entity.NewsEntity;

import java.util.List;

public interface NewsDataSource {

    List<NewsEntity> newsEntityList() throws Exception;

    NewsEntity insertNewsEntity(NewsEntity newsEntity) throws Exception;
}
