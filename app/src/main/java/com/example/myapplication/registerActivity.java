package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {

    private Button reg;
    private EditText musername;
    private EditText mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setfuntion();

        reg = findViewById(R.id.btn_register);
        musername = findViewById(R.id.uer_name);
        mpassword = findViewById(R.id.uer_password);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = musername.getText().toString();
                String password = mpassword.getText().toString();
                Intent intent = null;
                //设置如果密码错误，弹出的内容
                String noname = "请填写您的用户名";
                String nopassword = "请填写您的密码";
                String elseelse = "注册成功";

                //假设真确
                if (username.isEmpty()) {
                    //用户名为空
                    Toast.makeText(getApplicationContext(), noname, Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    //密码为空
                    Toast.makeText(getApplicationContext(), nopassword, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), elseelse, Toast.LENGTH_SHORT).show();

                }
            }


        });

    }

    private void setfuntion() {
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();/**结束当前的页面，也就是相当于跳回到之前的页面**/
            }
        });

    }

}