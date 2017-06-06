package com.cesarynga.newssample.presentation.view;

import com.cesarynga.newssample.presentation.model.NewsModel;

import java.util.List;

public interface NewsListView extends LoadingView {

    void renderNewsList(List<NewsModel> newsModelList);

    void viewNews(NewsModel newsModel);
}
