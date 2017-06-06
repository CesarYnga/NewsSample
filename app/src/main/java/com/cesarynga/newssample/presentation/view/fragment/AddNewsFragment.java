package com.cesarynga.newssample.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cesarynga.newssample.R;
import com.cesarynga.newssample.presentation.model.NewsModel;
import com.cesarynga.newssample.presentation.presenter.AddNewsPresenter;
import com.cesarynga.newssample.presentation.view.AddNewsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AddNewsFragment extends Fragment implements AddNewsView {

    @BindView(R.id.edt_news_title)
    TextInputEditText edtNewsTitle;

    @BindView(R.id.edt_news_image_url)
    TextInputEditText edtNewsImageUrl;

    @BindView(R.id.edt_news_detail)
    TextInputEditText edtNewsDetail;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    private Unbinder unbinder;

    private AddNewsPresenter addNewsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        addNewsPresenter = new AddNewsPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void saveNews(NewsModel newsModel) {
        addNewsPresenter.saveNews(newsModel);
    }

    @Override
    public void onNewsSuccess(NewsModel newsModel) {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    public void onSaveActionClick() {
        NewsModel newsModel = new NewsModel();
        newsModel.setTitle(edtNewsTitle.getText().toString());
        newsModel.setDetail(edtNewsDetail.getText().toString());
        newsModel.setImageUrl(edtNewsImageUrl.getText().toString());
        saveNews(newsModel);
    }
}
