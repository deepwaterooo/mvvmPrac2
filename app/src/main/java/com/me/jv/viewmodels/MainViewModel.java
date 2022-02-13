package com.me.jv.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;

import com.me.jv.model.BiYingResponse;
import com.me.jv.model.WallPaperResponse;
import com.me.jv.repository.MainRepository;

public class MainViewModel extends BaseViewModel {

    public LiveData<BiYingResponse> biying;

    public LiveData<WallPaperResponse> wallPaper;

    private final MainRepository mainRepository;

    @ViewModelInject
        MainViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void getBiying() {
        failed = mainRepository.failed;
        biying = mainRepository.getBiYing();
    }

    public void getWallPaper() {
        failed = mainRepository.failed;
        wallPaper = mainRepository.getWallPaper();
    }
}
