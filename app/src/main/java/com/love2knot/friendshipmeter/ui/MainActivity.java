package com.love2knot.friendshipmeter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.textclassifier.TextLanguage;
import android.widget.TextView;

import com.love2knot.friendshipmeter.adapter.NewsAdapter;
import com.love2knot.friendshipmeter.databinding.ActivityMainBinding;
import com.love2knot.friendshipmeter.fragments.BottomSheetSelect;
import com.love2knot.friendshipmeter.interfaces.LangClickListener;
import com.love2knot.friendshipmeter.utils.TranslateAPI;
import com.love2knot.friendshipmeter.utils.Util;
import com.love2knot.friendshipmeter.viewmodels.NewsViewModel;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity implements LangClickListener {
    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newsAdapter=new NewsAdapter(this,this);
        NewsViewModel viewModel= ViewModelProviders.of(this).get(NewsViewModel.class);
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.getArticles().observe(this,articles -> {
            if (articles!=null){
                newsAdapter.addArticle(articles);
            }
            binding.progressBar.setVisibility(View.GONE);
        });
        binding.rvNews.setLayoutManager(new LinearLayoutManager(this));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvNews);
        binding.rvNews.setAdapter(newsAdapter);

        Log.e(TAG,"*************"+ Util.getInstance(this).GetValue("lang"));



    }

    @Override
    public void clicked(TextView textView,int i) {
        BottomSheetSelect bottomSheetEditBio = new BottomSheetSelect(textView);
        bottomSheetEditBio.setCancelable(true);
        bottomSheetEditBio.show(getSupportFragmentManager(), TAG);
        newsAdapter.notifyItemChanged(i);
    }
}