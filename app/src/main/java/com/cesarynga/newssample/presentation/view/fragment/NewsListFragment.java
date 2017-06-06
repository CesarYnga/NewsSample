package com.cesarynga.newssample.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cesarynga.newssample.R;
import com.cesarynga.newssample.presentation.model.NewsModel;
import com.cesarynga.newssample.presentation.presenter.NewsListPresenter;
import com.cesarynga.newssample.presentation.view.NewsListView;
import com.cesarynga.newssample.presentation.view.activity.AddNewsActivity;
import com.cesarynga.newssample.presentation.view.adapter.NewsListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewsListFragment extends Fragment implements NewsListView, NewsListAdapter.OnItemClickListener {

    private static final int REQUEST_CODE = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progress;

    private Unbinder unbinder;

    private NewsListAdapter newsListAdapter;

    private NewsListPresenter newsListPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initUi();

        newsListPresenter = new NewsListPresenter(this);
        if (savedInstanceState == null) {
            newsListPresenter.getNewsList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initUi() {
        newsListAdapter = new NewsListAdapter();
        newsListAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(newsListAdapter);
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(recyclerView, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void renderNewsList(List<NewsModel> newsModelList) {
        newsListAdapter.setNewsList(newsModelList);
    }

    @Override
    public void viewNews(NewsModel newsModel) {

    }

    @Override
    public void onNewsItemClick(NewsModel newsModel) {
        newsListPresenter.onNewsClicked(newsModel);
    }

    @OnClick(R.id.fab_add_news)
    public void onAddButtonClick() {
        startActivityForResult(new Intent(getContext(), AddNewsActivity.class), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            newsListAdapter.setNewsList(null);
            newsListPresenter.getNewsList();
        }
    }
}
