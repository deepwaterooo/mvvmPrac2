package com.me.jv.repository;

import androidx.lifecycle.MutableLiveData;

import com.me.jv.BaseApplication;
import com.me.jv.db.beans.WallPaper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class PictureRepository {

    private final MutableLiveData<List<WallPaper>> wallPaper = new MutableLiveData<>();

    public final MutableLiveData<String> failed = new MutableLiveData<>();

    @Inject
    PictureRepository(){}

    public MutableLiveData<List<WallPaper>> getWallPaper() {
        Flowable<List<WallPaper>> listFlowable = BaseApplication.getDb().wallPaperDao().getAll();
        CustomDisposable.addDisposable(listFlowable, wallPapers -> {
                if (wallPapers.size() > 0) {
                    wallPaper.postValue(wallPapers);
                } else {
                    failed.postValue("暂无数据");
                }
            });
        return wallPaper;
    }
}
