package com.develop.frame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.frame.R;
import com.develop.frame.base.BaseActivity;
import com.develop.frame.utils.IGlide;

import java.util.ArrayList;
import java.util.List;

public class IShowPicActivity extends BaseActivity {

    private ViewPager vpIv;
    private TextView tvTitle;
    private List<String> list = new ArrayList<>();

    private static final String IMAGE_URLS = "IMAGE_URLS";
    private static final String IMAGE_POSITION = "IMAGE_POSITION";

    public static void showImage(Context context, ArrayList<String> list, int position) {
        if (null == list || list.size() == 0) {
            Toast.makeText(context, "图片集合为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (position >= list.size()) {
            Toast.makeText(context, "索引大于图片集合大小！", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(new Intent(context, IShowPicActivity.class)
                .putStringArrayListExtra(IMAGE_URLS, list)
                .putExtra(IMAGE_POSITION, position));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishow_pic);
        immersionBar.fitsSystemWindows(true).statusBarColor(R.color.blue_3EADFD).init();
        initView();
        initData();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        list = getIntent().getStringArrayListExtra(IMAGE_URLS);
        int position = getIntent().getIntExtra(IMAGE_POSITION, 0);
        tvTitle = findView(R.id.tv_title_frame_center);
        vpIv = findView(R.id.vp_iv);
        findView(R.id.rl_view_top_app).setBackgroundResource(R.color.blue_3EADFD);
        tvTitle.setText((position + 1) + "/" + list.size());
        vpIv.setAdapter(new ImagePageAdapter());
        vpIv.setCurrentItem(position);
    }

    @Override
    public void initData() {
        findView(R.id.iv_back_frame_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        vpIv.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText((++position) + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Images's adapter for viewPage
     */
    private class ImagePageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(getActivity());
            IGlide.showImage(getActivity(), list.get(position), iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }
    }
}
