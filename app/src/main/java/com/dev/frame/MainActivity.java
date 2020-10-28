package com.dev.frame;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.develop.frame.bridge.OnChooseItemListener;
import com.develop.frame.bridge.OnSelectTextListener;
import com.develop.frame.utils.IDate;
import com.develop.frame.utils.ILoading;
import com.develop.frame.utils.ILog;
import com.develop.frame.utils.IToast;
import com.develop.frame.view.dialog.SheetSelectDialog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tipTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> students = new ArrayList<>();
                students.add(new Student("1", "张三"));
                students.add(new Student("2", "李四"));
                students.add(new Student("3", "王五"));
                students.add(new Student("4", "赵六"));
                students.add(new Student("5", "王二麻子"));
                students.add(new Student("6", "东海龙宫"));

                Student student = new Student("123123", "甲方来得及");
                try {
                    Student student1 = (Student) student.clone();
                    ILog.e("克隆结果是：" + student1.name + " student1的地址是：" + student1.toString() + " student的地址是" + student.toString());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                new SheetSelectDialog<Student>(MainActivity.this)
                        .isSingleSelect(false)
                        .setSelectList(students)
                        .setSelectTitle("选择人物")
                        .setOnSelectListener(new OnChooseItemListener<Student>() {
                            @Override
                            public void singleSelectItem(int position, Student item) {

                            }
                        }).showMe();

            }
        });
        FormBody.Builder builder = new FormBody.Builder();
        ILog.e("格式化时间是：" +
                "\n" + IDate.format(IDate.YMDTHMS, IDate.HMS, "2020-10-19T16:23:32.323Z") +
                "\n" + IDate.format(System.currentTimeMillis()) +
                "\n" + IDate.format(IDate.YMD, System.currentTimeMillis()) +
                "\n" + IDate.format(IDate.YMDHMS, IDate.HMS, "2019-12-23 12:23:32") +
                "\n" + IDate.format(IDate.HMS, System.currentTimeMillis()));

        ILoading.open(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IToast.show("加载成功。。。");
                ILoading.close();
            }
        }, 10000);
    }

    private class Student implements OnSelectTextListener, Cloneable {

        private String id;
        private String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String getSelectText() {
            return name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}
