package com.cesarynga.newssample.presentation.presenter;

import com.cesarynga.newssample.data.entity.mapper.NewsEntityDataMapper;
import com.cesarynga.newssample.data.repository.NewsDataRepository;
import com.cesarynga.newssample.data.repository.datasource.NewsDataSourceFactory;
import com.cesarynga.newssample.domain.exception.DefaultErrorBundle;
import com.cesarynga.newssample.domain.exception.ErrorBundle;
import com.cesarynga.newssample.domain.executor.JobExecutor;
import com.cesarynga.newssample.domain.executor.UIThread;
import com.cesarynga.newssample.domain.model.News;
import com.cesarynga.newssample.domain.repository.NewsRepository;
import com.cesarynga.newssample.domain.usecase.GetNews;
import com.cesarynga.newssample.domain.usecase.UseCase;
import com.cesarynga.newssample.presentation.exception.ErrorMessageFactory;
import com.cesarynga.newssample.presentation.model.NewsModel;
import com.cesarynga.newssample.presentation.model.mapper.NewsModelDataMapper;
import com.cesarynga.newssample.presentation.view.NewsListView;

import java.util.List;

public class NewsListPresenter extends BasePresenter<NewsListView> {

    private final GetNews getNews;
    private final NewsModelDataMapper newsModelDataMapper;

    public NewsListPresenter(NewsListView view) {
        super(view);

        final NewsRepository newsRepository = new NewsDataRepository(
                new NewsDataSourceFactory(view.context()), new NewsEntityDataMapper());
        this.getNews = new GetNews(newsRepository, new JobExecutor(), new UIThread());
        this.newsModelDataMapper = new NewsModelDataMapper();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getNews.cancel();
        view = null;
    }

    private void renderNewsListInView(List<News> newsList) {
        view.renderNewsList(newsModelDataMapper.transform(newsList));
    }

    public void onNewsClicked(NewsModel newsModel) {
        view.viewNews(newsModel);
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(view.context(), errorBundle.getException());
        view.showError(errorMessage);
    }

    private void showLoadingView() {
        view.showLoading();
    }

    private void hideLoadingView() {
        view.hideLoading();
    }

    public void getNewsList() {
        showLoadingView();
        getNews.execute(new UseCase.Callback<List<News>>() {
            @Override
            public void onSuccess(List<News> newsList) {
                hideLoadingView();
                renderNewsListInView(newsList);
            }

            @Override
            public void onError(Throwable e) {
                hideLoadingView();
                showErrorMessage(new DefaultErrorBundle((Exception) e));
            }
        });
    }
}
