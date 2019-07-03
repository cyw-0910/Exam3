package com.example.exam3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit, passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*getDefaultSharedPreferences是getPreferenceManager类的一个方法，
        该方法会使用当前包名命名SharedPreferences文件，得到数据后，
        调用SharedPreferences.Editor调用apply（）方法实现存储*/
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //关联id
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);
        login = findViewById(R.id.login);
        //判断密码是否存在，不存在写入
        boolean isRemember = pref.getBoolean("remember_password", false);
        //Log.d(">>>",isRemember+"");
        if (isRemember) {
            //将账号密码设置到文本框
            String account = pref.getString("account", "");
            String password =pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        //设置登录按钮监听器
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    editor = pref.edit();
                    //检查复选框是否被选中,选中则使用put方法存储数据
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        //没有选中则使用clear（）方法清除数据
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

