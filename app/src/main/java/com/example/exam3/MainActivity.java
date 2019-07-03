package com.example.exam3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//主方法、程序的入口
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button office= findViewById(R.id.office);
        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用意图传递广播信息
                Intent intent=new Intent("com.example.broadcast_practice.FORCE_OFFICE");
                sendBroadcast(intent);
            }
        });
    }
}
