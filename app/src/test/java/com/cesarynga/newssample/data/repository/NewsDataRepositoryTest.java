package com.cesarynga.newssample.data.repository;

import com.cesarynga.newssample.data.entity.NewsEntity;
import com.cesarynga.newssample.data.entity.mapper.NewsEntityDataMapper;
import com.cesarynga.newssample.data.repository.datasource.NewsDataSource;
import com.cesarynga.newssample.data.repository.datasource.NewsDataSourceFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NewsDataRepositoryTest {

    private NewsDataRepository newsDataRepository;

    @Mock
    private NewsDataSourceFactory mockNewsDataSourceFactory;

    @Mock
    private NewsEntityDataMapper mockNewsEntityDataMapper;

    @Mock
    private NewsDataSource mockNewsDataSource;

    @Before
    public void setUp() throws Exception {
        this.newsDataRepository = new NewsDataRepository(mockNewsDataSourceFactory, mockNewsEntityDataMapper);
        given(mockNewsDataSourceFactory.createNetworkDataStore()).willReturn(mockNewsDataSource);
    }

    @Test
    public void testGetNewsHappyCase() throws Exception {
        List<NewsEntity> newsEntityList = new ArrayList<>();
        given(mockNewsDataSource.newsEntityList()).willReturn(newsEntityList);

        newsDataRepository.newsList();

        verify(mockNewsDataSourceFactory).createNetworkDataStore();
        verify(mockNewsDataSource).newsEntityList();
    }

}