package com.wb.wxchatutil;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Intent;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;
import java.util.logging.Logger;

public class WechatAccessibility extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                String className = accessibilityEvent.getClassName().toString();
                if (className.equals("com.tencent.mm.ui.LauncherUI")) {
                    searchGroup();
                }
                break;
        }
    }

//    performGlobalAction(GLOBAL_ACTION_BACK);//模拟back键

    private void searchGroup() {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        List<AccessibilityNodeInfo> nodeInfos = rootNode.findAccessibilityNodeInfosByText("无障碍服务群");
        if (nodeInfos != null && nodeInfos.size() == 1) {
            Log.e("nodeInfos -->:", "" + nodeInfos.size());
            AccessibilityNodeInfo nodeInfo = nodeInfos.get(0);
            if (nodeInfo.isEnabled()) {
                Log.e("nodeInfo -->:", "能点击" );
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }else{
                Log.e("nodeInfo -->:", "不能点击" );
            }
        }

    }

    private void forceClick(AccessibilityNodeInfo nodeInfo) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        Log.d("==", "forceClick: " + rect.left + " " + rect.top + " " + rect.right + " " + rect.bottom);
        int x = (rect.left + rect.right) / 2;
        int y = (rect.top + rect.bottom) / 2;
    }


    @Override
    public void onInterrupt() {

    }

}