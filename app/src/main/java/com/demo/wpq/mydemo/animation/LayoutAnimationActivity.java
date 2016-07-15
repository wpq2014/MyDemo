package com.demo.wpq.mydemo.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demo.wpq.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Desc: 布局动画
 * <p/>
 * Created by wpq on 16/7/15.
 */
public class LayoutAnimationActivity extends AppCompatActivity {


    @Bind(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        for(int i=0; i<40; i++){
            list.add("LayoutAnimationController "+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //两种方式：布局文件设置或代码控制
//        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.layout_animation1));
//        lac.setInterpolator(new AccelerateDecelerateInterpolator());
//        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        listView.setLayoutAnimation(lac);
//        listView.startLayoutAnimation();
    }
}
