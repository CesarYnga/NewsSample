package com.cesarynga.newssample.data.repository.datasource;

import com.cesarynga.newssample.data.entity.NewsEntity;
import com.cesarynga.newssample.data.net.RestApi;

import java.util.List;

public class NetworkNewsDataSource implements NewsDataSource {

    private final RestApi restApi;

    public NetworkNewsDataSource(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public List<NewsEntity> newsEntityList() throws Exception {
        return restApi.getNewsEntityList();
    }

    @Override
    public NewsEntity insertNewsEntity(NewsEntity newsEntity) throws Exception {
        return restApi.insertNewsEntity(newsEntity);
    }
}
