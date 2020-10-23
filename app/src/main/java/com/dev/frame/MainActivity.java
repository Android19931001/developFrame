package com.dev.frame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.develop.frame.bridge.OnChooseItemListener;
import com.develop.frame.bridge.OnSelectTextListener;
import com.develop.frame.view.dialog.SheetSelectDialog;

import java.util.ArrayList;
import java.util.List;

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

                new SheetSelectDialog<Student>(MainActivity.this)
                        .isSingleSelect(false)
                        .setSelectList(students)
                        .setSelectTitle("选择人物")
                        .setOnSelectListener(new OnChooseItemListener<Student>() {
                            @Override
                            public void singleSelectItem(int position, Student item) {

                            }

                            @Override
                            public void multiSelectItem(List<Student> selectedItems) {

                            }
                        }).show();

            }
        });

    }

    private class Student implements OnSelectTextListener {

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
    }

}
