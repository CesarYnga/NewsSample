package com.cesarynga.newssample.domain.usecase;

import com.cesarynga.newssample.domain.executor.PostExecutionThread;
import com.cesarynga.newssample.domain.executor.ThreadExecutor;
import com.cesarynga.newssample.domain.repository.NewsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetNewsTest {

    private GetNews getNews;

    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private NewsRepository mockNewsRepository;

    @Before
    public void setUp() throws Exception {
        getNews = new GetNews(mockNewsRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetNewsUseCaseHappyCase() throws Exception {
        getNews.buildUseCase();

        verify(mockNewsRepository).newsList();
        verifyNoMoreInteractions(mockNewsRepository);
    }

}