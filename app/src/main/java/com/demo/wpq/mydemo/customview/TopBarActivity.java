package com.demo.wpq.mydemo.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.customview.view.DirectionView;
import com.demo.wpq.mydemo.customview.view.TopBar;

/**
 * Desc:
 * Created by wpq on 16/7/11.
 */
public class TopBarActivity extends Activity{

    private TopBar topBar0 = null;
    private TopBar topBar1 = null;
    private DirectionView directionView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar);

        topBar0 = (TopBar) findViewById(R.id.topbar0);
        topBar0.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarLeftButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "leftButton0 clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopBarRightButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "rightButton0 clicked", Toast.LENGTH_SHORT).show();
            }
        });

        topBar1 = (TopBar) findViewById(R.id.topbar1);
        topBar1.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarLeftButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "leftButton1 clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTopBarRightButtonClick(View v) {
                Toast.makeText(TopBarActivity.this, "rightButton1 clicked", Toast.LENGTH_SHORT).show();
            }
        });

        directionView = (DirectionView) findViewById(R.id.directionView);
        directionView.setOnDirectionChangedListener(new DirectionView.OnDirectionChangedListener() {
            @Override
            public void onDirectionChanged(int currentDirection) {
                switch (currentDirection){
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

    private void showToast(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
