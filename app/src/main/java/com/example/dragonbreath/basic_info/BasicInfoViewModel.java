package com.example.dragonbreath.basic_info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BasicInfoViewModel extends ViewModel {
    private MutableLiveData<String> placeInfo;

    public BasicInfoViewModel() {
        placeInfo = new MutableLiveData<>();
    }
    public LiveData<String> getPlaceInfo() {
        return placeInfo;
    }

//    public void setPlaceInfo(String info) {
//        placeInfo.setValue(info);
//    }
}
