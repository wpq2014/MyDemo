package com.demo.wpq.mydemo.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.customview.view.DirectionView;
import com.demo.wpq.mydemo.customview.view.MarqueeTextView;
import com.demo.wpq.mydemo.customview.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * Created by wpq on 16/7/11.
 */
public class TopBarActivity extends Activity {


    @BindView(R.id.topbar0)
    TopBar topbar0;
    @BindView(R.id.topbar1)
    TopBar topbar1;
    @BindView(R.id.directionView)
    DirectionView directionView;
    @BindView(R.id.marquee_textView)
    MarqueeTextView marqueeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar);
        ButterKnife.bind(this);

        topbar0.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarLeftButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "leftButton0 clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopBarRightButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "rightButton0 clicked", Toast.LENGTH_SHORT).show();
            }
        });



        topbar1.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarLeftButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "leftButton1 clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopBarRightButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "rightButton1 clicked", Toast.LENGTH_SHORT).show();
            }
        });

        directionView.setOnDirectionChangedListener(new DirectionView.OnDirectionChangedListener() {
            @Override
            public void onDirectionChanged(int currentDirection) {
                switch (currentDirection) {
                    case DirectionView.Direction.LEFT:
                        showToast("左");
                        break;
                    case DirectionView.Direction.RIGHT:
                        showToast("右");
                        break;
                    case DirectionView.Direction.UP:
                        showToast("前");
                        break;
                    case DirectionView.Direction.DOWN:
                        showToast("后");
                        break;
                }
            }
        });
    }

    private void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
