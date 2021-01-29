package com.develop.frame.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.develop.frame.R;
import com.develop.frame.base.AppAdapter;
import com.develop.frame.bridge.OnChooseItemListener;
import com.develop.frame.bridge.OnSelectTextListener;
import com.develop.frame.utils.IScreen;
import com.develop.frame.utils.IToast;
import com.develop.frame.utils.IViewHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangning on 2018/9/7.
 */

public class SheetSelectDialog<T> extends Dialog implements View.OnClickListener {

    //选择框对象用于链式
    private static SheetSelectDialog mDialog;

    //上线文
    private Context mContext;

    //显示数据
    private ListView lvSelect;

    //数据适配器
    private AppAdapter<T> selectAdapter;

    //确定按钮和标题
    private TextView tvSelectSure, tvSelectTitle;

    //数据集合，多选时移除数据

    //显示被选中的数据
    private Map<Integer, ImageView> imgMap = new LinkedHashMap<>();

    //是否是单选默认是单选
    private boolean isSingleSelect = true;

    //选中回调
    private OnChooseItemListener<T> onChooseItemListener;


    //多选时被选中的数据
    private List<T> selectedItems = new ArrayList<>();


    public SheetSelectDialog(@NonNull Context context) {
        super(context, R.style.Translucent_NoTitle_ANIMA);
        mDialog = this;
        this.mContext = context;
        initView();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.sheet_select_dialog_layout, null);
        lvSelect = mView.findViewById(R.id.lv_select);
        tvSelectSure = mView.findViewById(R.id.tv_select_sure);
        tvSelectTitle = mView.findViewById(R.id.tv_select_title);
        tvSelectSure.setOnClickListener(this);
        setContentView(mView);
        initAdapter();
    }


    /**
     * 初始化适配器
     */
    private void initAdapter() {
        selectAdapter = new AppAdapter<T>(mContext, new ArrayList<T>(), R.layout.select_adapter_layout) {
            @Override
            public View getChildView(IViewHelper helper, final int position, final T t) {
                String text = null == t ? "" : (t instanceof OnSelectTextListener) ? ((OnSelectTextListener) t).getSelectText() : (t instanceof String) ? t.toString() : "";
                helper.setText(R.id.tv_select_text, text);
                final ImageView ivCheck = helper.getViews(R.id.iv_select_check);
                if (!isSingleSelect) {
                    if (imgMap.containsKey(position)) {
                        ivCheck.setVisibility(View.VISIBLE);
                    } else {
                        ivCheck.setVisibility(View.INVISIBLE);
                    }
                }
                helper.setClickListener(R.id.rl_select_content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isSingleSelect) {
                            handleMultiSelect(data, position, ivCheck);
                        } else {
                            onChooseItemListener.singleSelectItem(position, t);
                            mDialog.dismiss();
                        }
                    }
                });
                return helper.getView();
            }
        };
        lvSelect.setAdapter(selectAdapter);
    }


    /**
     * 多选时处理选中标志
     */
    private void handleMultiSelect(List<T> data, int position, ImageView ivCheck) {
        if (imgMap.containsKey(position)) {
            imgMap.get(position).setVisibility(View.INVISIBLE);
            imgMap.remove(position);
            selectedItems.remove(data.get(position));
        } else {
            imgMap.put(position, ivCheck);
            selectedItems.add(data.get(position));
            imgMap.get(position).setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置数据源
     *
     * @param list
     * @return
     */
    public SheetSelectDialog<T> setSelectList(List<T> list) {
        if (null == list || list.size() == 0) {
            IToast.show(mContext, "请输入数据源！");
            return mDialog;
        }
        selectAdapter.addData(list);
        int height = 0;
        if (list.size() >= 5) {
            height = 5 * (IScreen.dip2px(mContext, 45f)) + IScreen.dip2px(mContext, 50f);
        } else {
            height = list.size() * (IScreen.dip2px(mContext, 45f)) + IScreen.dip2px(mContext, 50f);
        }
        initDialog(height);
        return mDialog;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public SheetSelectDialog<T> setSelectTitle(String title) {
        tvSelectTitle.setText(title);
        return mDialog;
    }

    /**
     * 设置回调
     *
     * @param onChooseItemListener
     * @return
     */
    public SheetSelectDialog<T> setOnSelectListener(OnChooseItemListener<T> onChooseItemListener) {
        this.onChooseItemListener = onChooseItemListener;
        return mDialog;

    }

    /**
     * 是否是单选
     *
     * @param isSingleSelect
     * @return
     */
    public SheetSelectDialog<T> isSingleSelect(boolean isSingleSelect) {
        this.isSingleSelect = isSingleSelect;
        if (this.isSingleSelect) {
            tvSelectSure.setVisibility(View.INVISIBLE);
        }
        return mDialog;
    }

    /**
     * 初始化dialog
     */
    private void initDialog(int height) {
        this.setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.windowAnimations = R.style.translucent_animation;
        window.setLayout(IScreen.width(mContext), height);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.tv_select_sure == id) {
            if (!isSingleSelect) {
                if (onChooseItemListener != null) {
                    onChooseItemListener.multiSelectItem(selectedItems);
                    mDialog.dismiss();
                }
            }
        }
    }

    /**
     * 弹出对话框
     */
    public void showMe() {
        if (null == selectAdapter || selectAdapter.getCount() == 0) {
            IToast.show(mContext, "请传入数据源！");
            return;
        }
        mDialog.show();
    }
}
