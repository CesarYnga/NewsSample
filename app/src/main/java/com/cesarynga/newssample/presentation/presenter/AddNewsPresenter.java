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
import com.cesarynga.newssample.domain.usecase.AddNews;
import com.cesarynga.newssample.domain.usecase.UseCase;
import com.cesarynga.newssample.presentation.exception.ErrorMessageFactory;
import com.cesarynga.newssample.presentation.model.NewsModel;
import com.cesarynga.newssample.presentation.model.mapper.NewsModelDataMapper;
import com.cesarynga.newssample.presentation.view.AddNewsView;

public class AddNewsPresenter extends BasePresenter<AddNewsView> {

    private final AddNews addNews;
    private final NewsModelDataMapper newsModelDataMapper;

    public AddNewsPresenter(AddNewsView view) {
        super(view);

        final NewsRepository newsRepository = new NewsDataRepository(
                new NewsDataSourceFactory(view.context()), new NewsEntityDataMapper());
        this.addNews = new AddNews(newsRepository, new JobExecutor(), new UIThread());
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
        addNews.cancel();
        view = null;
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

    private void notifySaveNewsSuccess(News news) {
        view.onNewsSuccess(newsModelDataMapper.transform(news));
    }

    public void saveNews(NewsModel newsModel) {
        showLoadingView();
        addNews.setParams(newsModelDataMapper.transform(newsModel));
        addNews.execute(new UseCase.Callback<News>() {
            @Override
            public void onSuccess(News news) {
                hideLoadingView();
                notifySaveNewsSuccess(news);
            }

            @Override
            public void onError(Throwable e) {
                hideLoadingView();
                showErrorMessage(new DefaultErrorBundle((Exception) e));
            }
        });
    }
}
