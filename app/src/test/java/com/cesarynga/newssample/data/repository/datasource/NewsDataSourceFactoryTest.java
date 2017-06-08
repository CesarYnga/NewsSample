package com.cesarynga.newssample.data.repository.datasource;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NewsDataSourceFactoryTest {

    private NewsDataSourceFactory newsDataSourceFactory;

    @Mock
    private Context mockContext;

    @Before
    public void setUp() throws Exception {
        this.newsDataSourceFactory = new NewsDataSourceFactory(mockContext);
    }

    @Test
    public void testCreateNetworkDataStore() {
        NewsDataSource newsDataSource = newsDataSourceFactory.createNetworkDataStore();

        assertThat(newsDataSource, is(notNullValue()));
        assertThat(newsDataSource, is(instanceOf(NetworkNewsDataSource.class)));
    }

}