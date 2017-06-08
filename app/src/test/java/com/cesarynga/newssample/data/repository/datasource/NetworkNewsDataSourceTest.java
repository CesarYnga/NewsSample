package com.cesarynga.newssample.data.repository.datasource;

import com.cesarynga.newssample.data.entity.NewsEntity;
import com.cesarynga.newssample.data.net.RestApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NetworkNewsDataSourceTest {

    private NetworkNewsDataSource networkNewsDataSource;

    @Mock
    private RestApi mockRestApi;

    @Before
    public void setUp() throws Exception {
        this.networkNewsDataSource = new NetworkNewsDataSource(mockRestApi);
    }

    @Test
    public void testGetNewsFromApi() throws Exception {
        networkNewsDataSource.newsEntityList();
        verify(mockRestApi).getNewsEntityList();
    }

    @Test
    public void testInsertNews() throws Exception {
        NewsEntity newsEntity = new NewsEntity();
        networkNewsDataSource.insertNewsEntity(newsEntity);
        verify(mockRestApi).insertNewsEntity(newsEntity);
    }

}