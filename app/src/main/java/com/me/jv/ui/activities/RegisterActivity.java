package com.me.jv.ui.activities;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.me.jv.R;
import com.me.jv.databinding.ActivityRegisterBinding;
import com.me.jv.db.beans.User;
import com.me.jv.viewmodels.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends BaseActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.getUser().setValue(new User(0, "", "", "", null, null));
        binding.setRegister(registerViewModel);
        initView();
    }

    private void initView() {
        back(binding.toolbar);
        binding.btnRegister.setOnClickListener(v -> {
                if (registerViewModel.user.getValue().getAccount().isEmpty()) {
                    showMsg("请输入账号");
                    return;
                }
                if (registerViewModel.user.getValue().getPwd().isEmpty()) {
                    showMsg("请输入密码");
                    return;
                }
                if (registerViewModel.user.getValue().getConfirmPwd().isEmpty()) {
                    showMsg("请确认密码");
                    return;
                }
                if (!registerViewModel.user.getValue().getPwd().equals(registerViewModel.user.getValue().getConfirmPwd())) {
                    showMsg("两次输入密码不一致");
                    return;
                }

                registerViewModel.register();
                registerViewModel.failed.observe(this, failed -> {
                        showMsg("200".equals(failed) ? "注册成功" : failed);
                        if ("200".equals(failed)) {
                            finish();
                        }
                    });
            });
    }
}