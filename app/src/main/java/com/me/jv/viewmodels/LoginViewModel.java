package com.me.jv.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.me.jv.model.User;
import com.me.jv.repository.UserRepository;

public class LoginViewModel extends BaseViewModel {

    private final UserRepository userRepository;

    public MutableLiveData<User> user;

    public MutableLiveData<User> getUser(){
        if (user == null){
            user = new MutableLiveData<>();
        }
        return user;
    }

    @ViewModelInject
        LoginViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public LiveData<com.me.jv.db.beans.User> localUser;

    public void getLocalUser(){
        localUser = userRepository.getUser();
        failed = userRepository.failed;
    }
}
