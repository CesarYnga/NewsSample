package com.cesarynga.newssample.data.net;

import com.cesarynga.newssample.data.entity.NewsEntity;

import java.util.List;

public interface RestApi {

    List<NewsEntity> getNewsEntityList() throws Exception;

    NewsEntity insertNewsEntity(NewsEntity newsEntity) throws Exception;
}
