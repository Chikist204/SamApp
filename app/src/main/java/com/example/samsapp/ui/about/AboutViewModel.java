package com.example.samsapp.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class AboutViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AboutViewModel(){

        mText = new MutableLiveData<>();
        mText.setValue("wfefwefw");
    }

    public LiveData<String> getText() {
        return mText;
    }
}