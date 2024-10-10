package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class firstActivity extends AppCompatActivity {

    Button btplay;
    Button btrelog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        // 初始化按钮
        btplay = findViewById(R.id.bt_play);
        btrelog = findViewById(R.id.bt_relogin);

        // 设置开始游戏按钮的点击事件
        btplay.setOnClickListener(v -> {
            // 跳转到游戏活动
            Intent intent = new Intent(firstActivity.this, Game_oneActivity.class);
            startActivity(intent);
        });

        // 设置重置登录按钮的点击事件
        btrelog.setOnClickListener(v -> {
            // 跳转到登录活动
            Intent intent = new Intent(firstActivity.this, loginActivity.class);
            startActivity(intent);
        });
    }
}