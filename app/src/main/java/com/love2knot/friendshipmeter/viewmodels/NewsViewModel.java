package com.love2knot.friendshipmeter.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.love2knot.friendshipmeter.model.Articles;
import com.love2knot.friendshipmeter.repositories.NewsRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {

    MutableLiveData<List<Articles>> listMutableLiveData=new MutableLiveData<>();
    NewsRepository newsRepository=new NewsRepository();
    public MutableLiveData<List<Articles>> getArticles() {
        if (listMutableLiveData!=null){
            listMutableLiveData = newsRepository.getArticles();
        }
        return listMutableLiveData;
    }
}
