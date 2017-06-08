package com.cesarynga.newssample.data.entity.mapper;

import com.cesarynga.newssample.data.entity.NewsEntity;
import com.cesarynga.newssample.domain.model.News;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class NewsEntityDataMapperTest {

    private static final String FAKE_TITLE = "Test title";
    private static final String FAKE_DETAILS = "Test details";
    private static final String FAKE_IMAGE_URL = "http://image.test.jpg";

    private NewsEntityDataMapper newsEntityDataMapper;

    @Before
    public void setUp() throws Exception {
        this.newsEntityDataMapper = new NewsEntityDataMapper();
    }

    @Test
    public void testTransformNewsEntity() {
        NewsEntity newsEntity = createFakeNewsEntity();
        News news = newsEntityDataMapper.transform(newsEntity);

        assertThat(news, is(instanceOf(News.class)));
        assertThat(news.getTitle(), is(FAKE_TITLE));
        assertThat(news.getDetail(), is(FAKE_DETAILS));
        assertThat(news.getImageUrl(), is(FAKE_IMAGE_URL));
    }

    @Test
    public void testTransformNewsEntityList() {
        NewsEntity newsEntity1 = mock(NewsEntity.class);
        NewsEntity newsEntity2 = mock(NewsEntity.class);

        List<NewsEntity> newsEntityList =  new ArrayList<>();
        newsEntityList.add(newsEntity1);
        newsEntityList.add(newsEntity2);

        List<News> newsList = newsEntityDataMapper.transform(newsEntityList);

        assertThat(newsList.get(0), is(instanceOf(News.class)));
        assertThat(newsList.get(1), is(instanceOf(News.class)));
        assertThat(newsList.size(), is(2));
    }

    private NewsEntity createFakeNewsEntity() {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitle(FAKE_TITLE);
        newsEntity.setDetail(FAKE_DETAILS);
        newsEntity.setImageUrl(FAKE_IMAGE_URL);
        return newsEntity;
    }

}