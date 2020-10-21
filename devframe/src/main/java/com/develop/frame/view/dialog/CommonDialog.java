package com.develop.frame.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.develop.frame.R;
import com.develop.frame.bridge.OnBtnClickListener;
import com.develop.frame.utils.IScreen;


/**
 * Created by wangning on 2018/9/5.
 */

public class CommonDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private static CommonDialog mDialog;

    private TextView tvDialogTitle;
    private TextView tvDialogContent;
    private TextView tvBtnSure;
    private TextView tvBtnCancel;

    private OnBtnClickListener sureOnClickListener, cancelOnclickListener;

    public CommonDialog(@NonNull Context mContext) {
        super(mContext, R.style.Translucent_NoTitle);
        this.mContext = mContext;
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    public static CommonDialog getDialog(Context context) {
        return mDialog = new CommonDialog(context);
    }

    /**
     * 设置dialoo
     */
    private void initView() {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.common_dialog_layout, null);

        RelativeLayout rlDialog = mView.findViewById(R.id.rl_dialog);
        int width = IScreen.width(mContext);
        int height = IScreen.height(mContext);
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(width, height / 4);
        rlDialog.setLayoutParams(rlParams);
        tvDialogTitle = mView.findViewById(R.id.tv_dialog_title);
        tvDialogContent = mView.findViewById(R.id.tv_dialog_content);
        tvBtnSure = mView.findViewById(R.id.tv_btn_sure);
        tvBtnSure.setOnClickListener(this);
        tvBtnCancel = mView.findViewById(R.id.tv_btn_cancel);
        tvBtnCancel.setOnClickListener(this);

        this.setContentView(mView);
    }

    /**
     * 初始化dialog
     */
    private void initDialog() {
        Window window = this.getWindow();
        this.setCanceledOnTouchOutside(false);
        window.setGravity(Gravity.CENTER);
    }


    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public CommonDialog setDialogTitle(String title) {
        tvDialogTitle.setText(title);
        return mDialog;
    }

    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public CommonDialog setDialogContent(String content) {
        tvDialogContent.setText(content);
        return mDialog;
    }

    /**
     * 设置标题颜色
     *
     * @param colorId
     * @return
     */
    public CommonDialog setTitleColor(int colorId) {
        tvDialogTitle.setTextColor(mContext.getResources().getColor(colorId));
        return mDialog;
    }

    /**
     * 设置确定按钮的颜色
     *
     * @return
     */
    public CommonDialog setSureBtnColor(int colorId) {
        tvBtnSure.setTextColor(mContext.getResources().getColor(colorId));
        return mDialog;
    }

    /**
     * 设置确定按钮的文本
     *
     * @param text
     * @return
     */
    public CommonDialog setSureBtnText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tvBtnSure.setText(text);
        }
        return mDialog;
    }

    /**
     * 设置取消按钮的颜色
     *
     * @param colorId
     * @return
     */
    public CommonDialog setCancelBtnColor(int colorId) {
        tvBtnCancel.setTextColor(mContext.getResources().getColor(colorId));
        return mDialog;
    }

    /**
     * 设置取消按钮的文本
     *
     * @param text
     * @return
     */
    public CommonDialog setCancelBtnText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tvBtnCancel.setText(text);
        }
        return mDialog;
    }

    /**
     * 设置确定按钮回调
     *
     * @param sureOnClickListener
     */
    public CommonDialog setOnSureOnClickListener(OnBtnClickListener sureOnClickListener) {
        this.sureOnClickListener = sureOnClickListener;
        return mDialog;
    }

    /**
     * 设置取消按钮回调
     *
     * @param cancelOnclickListener
     */
    public CommonDialog setOnCancelOnclickListener(OnBtnClickListener cancelOnclickListener) {
        this.cancelOnclickListener = cancelOnclickListener;
        return mDialog;
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_btn_sure) {
            if (sureOnClickListener != null) {
                sureOnClickListener.onClick(mDialog);
            } else {
                mDialog.dismiss();
            }
        } else if (i == R.id.tv_btn_cancel) {
            if (cancelOnclickListener != null) {
                cancelOnclickListener.onClick(mDialog);
            } else {
                mDialog.dismiss();
            }
        }
    }

}
