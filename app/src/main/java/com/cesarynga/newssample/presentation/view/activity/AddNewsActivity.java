package com.cesarynga.newssample.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.cesarynga.newssample.R;
import com.cesarynga.newssample.presentation.view.fragment.AddNewsFragment;

public class AddNewsActivity extends AppCompatActivity {

    private static final String TAG_ADD_NEWS_FRAGMENT = "add_news_fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, new AddNewsFragment(), TAG_ADD_NEWS_FRAGMENT);
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_news:
                AddNewsFragment addNewsFragment = (AddNewsFragment) getSupportFragmentManager()
                        .findFragmentByTag(TAG_ADD_NEWS_FRAGMENT);
                if (addNewsFragment != null) {
                    addNewsFragment.onSaveActionClick();
                }
                break;
        }
        return true;
    }
}
