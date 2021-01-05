package com.develop.frame.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by wangning on 2018/2/22.
 */

public class IApi {

    /**
     * 调出拨号盘 不直接拨打电话
     *
     * @param context
     * @param phone
     */
    public static void displayKeyPad(Context context, String phone) {
        if (!IValidate.checkIsPhone(phone)) {
            Toast.makeText(context, "号码错误", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 直接拨打电话
     *
     * @param context
     * @param phone
     */
    public static void call(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到apk的市场详情页
     *
     * @param activity
     * @param packAgeName
     */
    public static void goToMarket(Activity activity, String packAgeName) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packAgeName)));
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packAgeName)));
        }
    }

    /**
     * 打开第三方app
     */
    public static void lunchAppByPackage(Context context, String packageName) {
        try {
            context.getPackageManager().getLaunchIntentForPackage(packageName);
        } catch (Exception e) {
            ILog.e("the packageName may be not exit or wrong!");
        }
    }

}
