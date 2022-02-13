package com.me.jv.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;

import com.me.jv.db.beans.WallPaper;
import com.me.jv.repository.PictureRepository;

import java.util.List;

public class PictureViewModel extends BaseViewModel {

    private final PictureRepository pictureRepository;
    public LiveData<List<WallPaper>> wallPaper;

    @ViewModelInject
        PictureViewModel(PictureRepository pictureRepository){
        this.pictureRepository = pictureRepository;
    }

    public void getWallPaper() {
        failed = pictureRepository.failed;
        wallPaper = pictureRepository.getWallPaper();
    }
}
