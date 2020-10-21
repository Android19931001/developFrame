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
import com.develop.frame.bridge.OnSelectItemListener;
import com.develop.frame.utils.IScreen;
import com.develop.frame.utils.IViewHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangning on 2018/9/7.
 */

public class SheetSelectDialog extends Dialog implements View.OnClickListener {

    private static SheetSelectDialog mDialog;
    private Context mContext;

    private ListView lvSelect;
    private TextView tvSelectSure, tvSelectTitle;
    private AppAdapter<String> selectAdapter;

    private List<String> contentList = new ArrayList<>();

    private Map<Integer, ImageView> imgMap = new LinkedHashMap<>();

    private boolean isSingleSelect = false;

    private OnSelectItemListener onSelectItemListener;

    private List<Integer> positions = new ArrayList<>();
    private List<String> selectedItems = new ArrayList<>();

    public SheetSelectDialog(@NonNull Context context) {
        super(context, R.style.Translucent_NoTitle_ANIMA);
        this.mContext = context;
        initView();
        initAdapter();
    }

    public static SheetSelectDialog getDialog(Context context) {
        return mDialog = new SheetSelectDialog(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
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
    }

    /**
     * 移除选中
     */
    public void removeCheck(String content) {
        for (int i = 0; i < contentList.size(); i++) {
            if (contentList.get(i).equals(content)) {
                imgMap.remove(i);
                selectedItems.remove(contentList.get(i));
            }
        }
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        selectAdapter = new AppAdapter<String>(mContext, contentList, R.layout.select_adapter_layout) {
            @Override
            public View getChildView(final int position, IViewHelper helper) {
                final String text = dataList.get(position);
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
                            if (imgMap.containsKey(position)) {
                                imgMap.get(position).setVisibility(View.INVISIBLE);
                                imgMap.remove(position);
                                positions.remove(((Object) position));
                                selectedItems.remove(contentList.get(position));
                            } else {
                                imgMap.put(position, ivCheck);
                                positions.add(position);
                                selectedItems.add(contentList.get(position));
                                imgMap.get(position).setVisibility(View.VISIBLE);
                            }
                        } else {
                            onSelectItemListener.singleSelectItem(position, text);
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
     * 设置数据源
     *
     * @param list
     * @return
     */
    public SheetSelectDialog setSelectList(List<String> list) {
        selectAdapter.addData(list);
        return mDialog;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public SheetSelectDialog setSelectTitle(String title) {
        tvSelectTitle.setText(title);
        return mDialog;
    }

    /**
     * 设置回调
     *
     * @param onSelectListener
     * @return
     */
    public SheetSelectDialog setOnSelectListener(OnSelectItemListener onSelectListener) {
        this.onSelectItemListener = onSelectListener;
        return mDialog;

    }

    /**
     * 是否是单选
     *
     * @param isSingleSelect
     * @return
     */
    public SheetSelectDialog isSingleSelect(boolean isSingleSelect) {
        this.isSingleSelect = isSingleSelect;
        if (this.isSingleSelect) {
            tvSelectSure.setVisibility(View.INVISIBLE);
        }
        return mDialog;
    }

    /**
     * 使出话dialog
     */
    private void initDialog() {
        this.setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.windowAnimations = R.style.translucent_animation;
        window.setLayout(IScreen.width(mContext), IScreen.height(mContext) * 3 / 8);
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
                if (onSelectItemListener != null) {
                    onSelectItemListener.multiSelectItem(positions,selectedItems);
                    mDialog.dismiss();
                }
            }
        }
    }
}
