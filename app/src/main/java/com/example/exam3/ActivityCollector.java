package com.example.exam3;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

//ActivityCollector用于管理活动
public class ActivityCollector {
    public static List<Activity> activities =new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    //使用finishALL方法管理活动
    public static void finishAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                //活动结束
                activity.finish();
            }
        }
        //活动清除
        activities.clear();
    }
}
