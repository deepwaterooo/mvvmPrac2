package com.me.jv.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;

import com.me.jv.model.VideoResponse;
import com.me.jv.repository.VideoRepository;

/**
 * VideoFragment数据提供
 */
public class VideoViewModel extends BaseViewModel {

    public LiveData<VideoResponse> video;

    private final VideoRepository videoRepository;

    @ViewModelInject
        VideoViewModel(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void getVideo() {
        failed = videoRepository.failed;
        video = videoRepository.getVideo();
    }
}