package com.cesarynga.newssample.presentation.view;


public interface LoadingView extends BaseView {

    void showLoading();

    void hideLoading();

    void showError(String errorMessage);
}
