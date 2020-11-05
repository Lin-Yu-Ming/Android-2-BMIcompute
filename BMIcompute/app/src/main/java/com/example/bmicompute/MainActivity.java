package com.example.bmicompute;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText height;                //宣告全域變數
    EditText weight;                //宣告全域變數
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height = (EditText)findViewById(R.id.Height);                           // 取得身高物件
        weight = (EditText)findViewById(R.id.Weight);                           // 取得體重物件
        Button submit = (Button)findViewById(R.id.output);             // 取得按鈕物件 如果ID有誤請回到res->Layout查看預設Button是多少

        // 按下按鈕 觸發事件
        submit.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View arg0) {
                //判斷條件 身高 跟 體重 都有輸入值才執行
                if ( !("".equals(height.getText().toString())
                        || "".equals(weight.getText().toString())) )
                {
                    float fh = Float.parseFloat(height.getEditableText().toString());      // 取得 身高輸入值
                    float fw = Float.parseFloat(weight.getEditableText().toString());     // 取得 體重輸入值
                    float RESULT;                                     // BMI值 計算結果
                    TextView result = (TextView)findViewById(R.id.Result);// 取得 顯示結果 物件
                    fh = fh/100; // 計算BMI
                    fh = fh*fh;  // 計算BMI

                    NumberFormat nf = NumberFormat.getInstance();   // 數字格式
                    nf.setMaximumFractionDigits(2);                 // 限制小數第二位
                    RESULT = fw/fh;                                // 計算BMI
                    result.setText(nf.format(fw/fh) +"");           // 顯示BMI計算結果
                    TextView dia = (TextView)findViewById(R.id.Diagnose);// 取得 顯示診斷 物件

                    // 診斷結果 顯示
                    if (RESULT<18.5)
                        dia.setText("體重過輕");
                    else if (18.5 <= RESULT && RESULT< 24)
                        dia.setText("正常範圍");
                    else if (24 <=RESULT && RESULT < 27)
                        dia.setText("過    重");
                    else if (27 <=RESULT && RESULT < 30)
                        dia.setText("輕度肥胖");
                    else if (30 <= RESULT && RESULT < 35)
                        dia.setText("中度肥胖");
                    else if (RESULT >= 35)
                        dia.setText("重度肥胖        ");
                }
            }
        });
    }
}