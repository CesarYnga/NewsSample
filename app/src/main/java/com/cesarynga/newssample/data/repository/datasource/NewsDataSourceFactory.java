package com.cesarynga.newssample.data.repository.datasource;

import android.content.Context;

import com.cesarynga.newssample.data.net.RestApi;
import com.cesarynga.newssample.data.net.RestApiImpl;

public class NewsDataSourceFactory {

    private final Context context;

    public NewsDataSourceFactory(Context context) {
        this.context = context;
    }

    public NewsDataSource create() {
        RestApi restApi = new RestApiImpl(this.context);
        return new NetworkNewsDataSource(restApi);
    }
}
