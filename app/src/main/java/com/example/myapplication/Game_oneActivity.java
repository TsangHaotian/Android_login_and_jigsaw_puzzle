package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class Game_oneActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton IB_1, IB_2, IB_3, IB_4, IB_5, IB_6, IB_7, IB_8, IB_9;

    Button btreturn;

    // 每行的图片个数//
    private final int imageX = 3; // 行//
    private final int imageY = 3; // 列//

    // 图片总数//
    private final int imgCount = imageX * imageY;
    // 空白区域//
    private int blankSwap = imgCount - 1;
    // 初始化空白区域位置//
    private int blankImgid = R.id.imageButton9;

    // 存放碎片的数组，便于进行统一管理//
    private final int[] image = {R.drawable.a, R.drawable.b, R.drawable.c,
            R.drawable.d, R.drawable.e, R.drawable.f,
            R.drawable.g, R.drawable.h, R.drawable.i};
    // 声明下标数组，随机排列这个数组//
    private final int[] imageIndex = new int[image.length];

    // 计时器相关变量
    private int seconds = 0; // 记录秒数
    private TextView timerTextView; // 用于显示时间的TextView
    private Handler handler = new Handler(); // Handler对象
    private Runnable runnable; // Runnable任务




    // 打乱图片//
    private void disruptRandom() {
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
        // 规定20次，选择两个进行互换//
        int r1, r2;
        for (int j = 0; j < 20; j++) {
            // 随机生成第一个，0-8之间的随机数//
            r1 = (int) (Math.random() * (imageIndex.length - 1));
            // 第二次不与第一次相同//
            do {
                r2 = (int) (Math.random() * (imageIndex.length - 1));
            } while (r2 == r1);
            swap(r1, r2);
        }
        // 随机排列到指定控件
        IB_1.setImageResource(image[imageIndex[0]]);
        IB_2.setImageResource(image[imageIndex[1]]);
        IB_3.setImageResource(image[imageIndex[2]]);
        IB_4.setImageResource(image[imageIndex[3]]);
        IB_5.setImageResource(image[imageIndex[4]]);
        IB_6.setImageResource(image[imageIndex[5]]);
        IB_7.setImageResource(image[imageIndex[6]]);
        IB_8.setImageResource(image[imageIndex[7]]);
        IB_9.setImageResource(image[imageIndex[8]]);

        // 确保空白图块初始可见
        IB_9.setImageResource(R.drawable.blank);
    }

    // 交换数组指定角标上的数组
    private void swap(int r1, int r2) {
        int temp = imageIndex[r1];
        imageIndex[r1] = imageIndex[r2];
        imageIndex[r2] = temp;
    }

    private void initView() {
        IB_1 = findViewById(R.id.imageButton1);
        IB_2 = findViewById(R.id.imageButton2);
        IB_3 = findViewById(R.id.imageButton3);
        IB_4 = findViewById(R.id.imageButton4);
        IB_5 = findViewById(R.id.imageButton5);
        IB_6 = findViewById(R.id.imageButton6);
        IB_7 = findViewById(R.id.imageButton7);
        IB_8 = findViewById(R.id.imageButton8);
        IB_9 = findViewById(R.id.imageButton9);

        IB_1.setOnClickListener(this);
        IB_2.setOnClickListener(this);
        IB_3.setOnClickListener(this);
        IB_4.setOnClickListener(this);
        IB_5.setOnClickListener(this);
        IB_6.setOnClickListener(this);
        IB_7.setOnClickListener(this);
        IB_8.setOnClickListener(this);
        IB_9.setOnClickListener(this);

        btreturn = findViewById(R.id.bt_return);

        timerTextView = findViewById(R.id.timerTextView); // 获取计时器TextView
    }



    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.imageButton1) {
            move(R.id.imageButton1, 0);
        } else if (id == R.id.imageButton2) {
            move(R.id.imageButton2, 1);
        } else if (id == R.id.imageButton3) {
            move(R.id.imageButton3, 2);
        } else if (id == R.id.imageButton4) {
            move(R.id.imageButton4, 3);
        } else if (id == R.id.imageButton5) {
            move(R.id.imageButton5, 4);
        } else if (id == R.id.imageButton6) {
            move(R.id.imageButton6, 5);
        } else if (id == R.id.imageButton7) {
            move(R.id.imageButton7, 6);
        } else if (id == R.id.imageButton8) {
            move(R.id.imageButton8, 7);
        } else if (id == R.id.imageButton9) {
            move(R.id.imageButton9, 8);
        }
    }

    // 表示移动指定位置的按钮的函数//
    private void move(int imagebuttonId, int site) {
        // 判断照片的位置//
        int sitex = site % imageX;
        int sitey = site / imageY;
        // 空白区域图标//
        int blankx = blankSwap % imageX;
        int blanky = blankSwap / imageY;
        // 移动条件：在同一行，列数相减，绝对值为一；在同一列，列数相减，绝对值为一；//
        int x = Math.abs(sitex - blankx);
        int y = Math.abs(sitey - blanky);
        if ((x == 0 && y == 1) || (y == 0 && x == 1)) {
            // 通过ID，查找到可移动的按钮//
            ImageButton clickButton = findViewById(imagebuttonId);
            clickButton.setVisibility(View.INVISIBLE);
            // 查找到空白的按钮//
            ImageButton blankButton = findViewById(blankImgid);
            // 将空白区域设为图片//
            blankButton.setImageResource(image[imageIndex[site]]);
            // 移动之前不可见，移动之后可见//
            blankButton.setVisibility(View.VISIBLE);
            // 将改变角标的过程记录存储到图片位置数组中//
            swap(site, blankSwap);
            // 新的空白区域位置更新等于传入的点击按钮的位置//
            blankSwap = site;
            blankImgid = imagebuttonId;
        }
        // 判断是否完成拼图//
        judgeGamover();
    }

    // 判断拼图是否成功//
    private void judgeGamover() {
        boolean loop = true;
        for (int i = 0; i < imageIndex.length; i++) {
            if (imageIndex[i] != i) {
                loop = false;
                break;
            }
        }
        if (loop) {
            // 拼图成功，停止计时//
            handler.removeCallbacks(runnable); // 停止计时器
            // 禁止移动按钮//
            IB_1.setClickable(false);
            IB_2.setClickable(false);
            IB_3.setClickable(false);
            IB_4.setClickable(false);
            IB_5.setClickable(false);
            IB_6.setClickable(false);
            IB_7.setClickable(false);
            IB_8.setClickable(false);
            IB_9.setClickable(false);
            IB_9.setImageResource(image[8]);
            IB_9.setVisibility(View.VISIBLE);
            // 弹出成功对话框//
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("恭喜，拼图成功！用时：" + seconds + "秒")
                    .setPositiveButton("确认", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    // 开始计时器
    private void startGameTimer() {
        runnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                String time = String.format("用时：" + "%02d", seconds) + "秒";
                timerTextView.setText(time);
                handler.postDelayed(this, 1000); // 每秒更新一次
            }
        };
        handler.postDelayed(runnable, 1000); // 启动计时器
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);
        initView();
        View restartButton = findViewById(R.id.pt_btn_restart);
        restartButton.setOnClickListener(v -> restartGame());
        // 打乱图片函数//
        disruptRandom();
        // 开始计时器
        startGameTimer();

        // 初始化返回按钮并设置点击事件
        btreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game_oneActivity.this, firstActivity.class);
                startActivity(intent);
                finish(); // 结束当前活动
            }
        });

    }

    private void restartGame() {
        seconds = 0; // 重置秒数
        timerTextView.setText("用时：00秒"); // 重置显示的时间
        handler.removeCallbacks(runnable); // 移除之前的Runnable
        startGameTimer(); // 重新启动计时器

        // 重置图片碎片数组
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
        // 打乱图片碎片
        disruptRandom();
        //// 重置空白区域位置
        blankSwap = imgCount - 1;
        blankImgid = R.id.imageButton9;
        //// 重置按钮的可见性和可点击性
        IB_1.setVisibility(View.VISIBLE);
        IB_2.setVisibility(View.VISIBLE);
        IB_3.setVisibility(View.VISIBLE);
        IB_4.setVisibility(View.VISIBLE);
        IB_5.setVisibility(View.VISIBLE);
        IB_6.setVisibility(View.VISIBLE);
        IB_7.setVisibility(View.VISIBLE);
        IB_8.setVisibility(View.VISIBLE);
        IB_9.setVisibility(View.INVISIBLE); // 空白区域
        //// 重置按钮的可点击性
        IB_1.setClickable(true);
        IB_2.setClickable(true);
        IB_3.setClickable(true);
        IB_4.setClickable(true);
        IB_5.setClickable(true);
        IB_6.setClickable(true);
        IB_7.setClickable(true);
        IB_8.setClickable(true);
        IB_9.setClickable(true);
        //// 重置图片资源
        IB_9.setImageResource(R.drawable.blank);
    }


}