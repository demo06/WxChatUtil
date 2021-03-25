package com.wb.wxchatutil;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;
import com.wb.wxchatutil.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ActivityMainBinding bd;
    private MMKV mmkv;
    private String serverName = "com.wb.wxchatutil/.WechatAccessibility";
    private String groupName;
    private int counts;
    private boolean friends, groups, manage, privates;
    ArrayList<String> membersList;
    private OnConfirmListener onConfirmListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        initConfig();
        initView();
        initListener();
        initData();
    }

    private void initConfig() {
        MMKV.initialize(getFilesDir().getAbsolutePath() + "/mmkv");
        mmkv = MMKV.defaultMMKV();
        if (!isStartAccessibilityService(this, serverName)) {
            showDialog("开启无障碍服务", "本应用需开启无障碍服务才能运行,请到设置去开启", "去设置", true, () -> Utils.openAccessible(MainActivity.this));
        }
    }

    public void initView() {
        bd.nbPicker.setMinValue(1);
        bd.nbPicker.setMaxValue(4);

        bd.nbPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        bd.nbPicker.setWrapSelectorWheel(false);
        bd.nbPicker.setOnScrollListener((numberPicker, i) -> {
            mmkv.encode("counts", numberPicker.getValue());
        });
    }


    private void initData() {
        counts = mmkv.decodeInt("counts");
        groupName = mmkv.decodeString("groupName");
        friends = mmkv.decodeBool("friends");
        groups = mmkv.decodeBool("groups");
        manage = mmkv.decodeBool("manage");
        privates = mmkv.decodeBool("privates");
        bd.nbPicker.setValue(counts);
        bd.cbFriend.setChecked(friends);
        bd.cbPrivate.setChecked(privates);
        bd.cbGroup.setChecked(groups);
        bd.etGroupname.setText(groupName);
    }

    private void initListener() {
        bd.cbFriend.setOnCheckedChangeListener(this);
        bd.cbGroup.setOnCheckedChangeListener(this);
        bd.cbPrivate.setOnCheckedChangeListener(this);
    }


    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_check:
                Toast.makeText(this, "检查", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save:
                mmkv.encode("groupName", bd.etGroupname.getText().toString());
                toast("群名已保存");
                break;
            case R.id.btn_run:
                if (!isStartAccessibilityService(this, serverName)) {
                    showDialog("开启无障碍服务", "本应用需开启无障碍服务才能运行,请到设置去开启", "去设置", true, () -> Utils.openAccessible(MainActivity.this));
                } else {
                    showDialog("温馨提示", "软件运行期间请勿触摸屏幕,以防止操作失效", "我知道了", false, () -> Utils.openActivity(this, "com.tencent.mm"));
                }
                break;
            case R.id.btn_warn:
                break;
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_friend:
                mmkv.encode("friends", b);
                break;
            case R.id.cb_group:
                mmkv.encode("groups", b);
                break;
            case R.id.cb_private:
                mmkv.encode("privates", b);
                break;
        }
    }

    private void showDialog(String title, String content, String positiveText, boolean isShowCancel, OnConfirmListener onConfirmListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(content)
                .setPositiveButton(positiveText, (dialogInterface, i) -> onConfirmListener.onConfirm());
        if (isShowCancel) {
            builder.setNegativeButton("取消", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                toast("无障碍未开启无法开启脚本");
            });
        }
        builder.show();
    }


    /**
     * 判断AccessibilityService服务是否已经启动
     *
     * @param context
     * @param name    无障碍服务名   packegname/.accessbilityname
     * @return
     */
    public boolean isStartAccessibilityService(Context context, String name) {
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> serviceInfos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : serviceInfos) {
            String id = info.getId();
            Log.e("all -->", "isStartAccessibilityService: " + id);
            if (id.contains(name)) {
                return true;
            }
        }
        return false;
    }

    private void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    private interface OnConfirmListener {
        void onConfirm();
    }
}