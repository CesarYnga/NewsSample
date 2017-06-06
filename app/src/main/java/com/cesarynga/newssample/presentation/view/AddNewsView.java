package com.cesarynga.newssample.presentation.view;

import com.cesarynga.newssample.presentation.model.NewsModel;

public interface AddNewsView extends LoadingView {

    void saveNews(NewsModel newsModel);

    void onNewsSuccess(NewsModel newsModel);
}
