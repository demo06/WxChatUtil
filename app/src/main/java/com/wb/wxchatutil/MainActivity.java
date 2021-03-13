package com.wb.wxchatutil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NumberPicker nbPicker;
    Button btnSave;
    Button btnRun;
    Button btnCheck;
    Button btnWarn;
    CheckBox cbFriend;
    CheckBox cbGroup;
    CheckBox cbPrivate;
    EditText etGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        nbPicker = findViewById(R.id.nb_picker);
        btnSave = findViewById(R.id.btn_save);
        btnRun = findViewById(R.id.btn_run);
        btnCheck = findViewById(R.id.btn_check);
        btnWarn = findViewById(R.id.btn_warn);
        cbFriend = findViewById(R.id.cb_friend);
        cbGroup = findViewById(R.id.cb_group);
        cbPrivate = findViewById(R.id.cb_private);
        etGroupName = findViewById(R.id.et_groupname);
        nbPicker.setMinValue(1);
        nbPicker.setMaxValue(4);
        nbPicker.setValue(1);
        nbPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        nbPicker.setWrapSelectorWheel(false);
        nbPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {

            }
        });
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_check:
                Toast.makeText(this, "检查", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save:
                Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_run:
                Toast.makeText(this, "开始", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_warn:
                Toast.makeText(this, "提醒", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}