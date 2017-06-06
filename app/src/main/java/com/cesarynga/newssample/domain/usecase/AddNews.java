package com.cesarynga.newssample.domain.usecase;

import com.cesarynga.newssample.domain.executor.PostExecutionThread;
import com.cesarynga.newssample.domain.executor.ThreadExecutor;
import com.cesarynga.newssample.domain.model.News;
import com.cesarynga.newssample.domain.repository.NewsRepository;

public class AddNews extends UseCase<News> {

    private final NewsRepository newsRepository;

    private News news;

    public AddNews(NewsRepository newsRepository,
                   ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.newsRepository = newsRepository;
    }

    public void setParams(News news) {
        this.news = news;
    }

    @Override
    protected void buildUseCase() {
        try {
            News returnedNews = this.newsRepository.insetNews(this.news);
            notifyUseCaseSuccess(returnedNews);
        } catch (Exception e) {
            notifyUseCaseError(e);
        }
    }
}
