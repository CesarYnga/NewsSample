package com.cesarynga.newssample.domain.usecase;

import com.cesarynga.newssample.domain.executor.PostExecutionThread;
import com.cesarynga.newssample.domain.executor.ThreadExecutor;
import com.cesarynga.newssample.domain.model.News;
import com.cesarynga.newssample.domain.repository.NewsRepository;

import java.util.List;

public class GetNews extends UseCase<List<News>> {

    private final NewsRepository newsRepository;

    public GetNews(NewsRepository newsRepository,
                   ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.newsRepository = newsRepository;
    }

    @Override
    protected void buildUseCase() {
        try {
            List<News> newsList = newsRepository.newsList();
            notifyUseCaseSuccess(newsList);
        } catch (Exception e) {
            notifyUseCaseError(e);
        }
    }
}
