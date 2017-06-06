package com.cesarynga.newssample.presentation.presenter;

import com.cesarynga.newssample.presentation.view.BaseView;

public abstract class BasePresenter<V extends BaseView> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();
}
