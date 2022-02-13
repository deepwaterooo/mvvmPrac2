package com.me.jv.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.me.jv.R;
import com.me.jv.databinding.ActivityLoginBinding;
import com.me.jv.model.User;
import com.me.jv.utils.Constant;
import com.me.jv.utils.MVUtils;
import com.me.jv.viewmodels.LoginViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity {

    @Inject
    MVUtils mvUtils;

    private ActivityLoginBinding dataBinding;
    private LoginViewModel loginViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注意: 这个activity layout里用了materialbutton，那么需要主其设置一个material family的theme才能正常运行
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        //Model → View
        User user = new User("", "");
        loginViewModel.getUser().setValue(user);
        //获取观察对象
        MutableLiveData<User> user1 = loginViewModel.getUser();
        user1.observe(this, user2 -> {
                Log.d("LoginActivity", "onCreate: " + user2.getAccount());
                dataBinding.setViewModel(loginViewModel); // layout视图中用的变量名是viewModel,那就setViewModel(v)
            });

        dataBinding.btnLogin.setOnClickListener(v -> {
                if (loginViewModel.user.getValue().getAccount().isEmpty()) {
                    showMsg("请输入账号");
                    return;
                }
                if (loginViewModel.user.getValue().getPwd().isEmpty()) {
                    showMsg("请输入密码");
                    return;
                }
                //检查输入的账户和密码是否是注册过的。
                checkUser();
            });
    }

    private void checkUser() {
        loginViewModel.getLocalUser();

        loginViewModel.localUser.observe(this, localUser -> {
                if (!loginViewModel.user.getValue().getAccount().equals(localUser.getAccount()) ||
                    !loginViewModel.user.getValue().getPwd().equals(localUser.getPwd())) {
                    showMsg("账号或密码错误");
                    return;
                }
                //记录已经登录过
                mvUtils.put(Constant.IS_LOGIN, true);
                showMsg("登录成功");
                jumpActivity(MainActivity.class);
            });
        loginViewModel.failed.observe(this, this::showMsg);
    }

    private long timeMillis;

    /**
     * Add a prompt to exit the application
     */
    @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                showMsg("再次按下退出应用程序");
                timeMillis = System.currentTimeMillis();
            } else {
                exitTheProgram();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void toRegister(View view) {
        jumpActivity(RegisterActivity.class);
    }
}
