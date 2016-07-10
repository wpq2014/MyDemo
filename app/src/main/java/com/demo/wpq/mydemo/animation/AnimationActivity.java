package com.demo.wpq.mydemo.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.wpq.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 动画 慕课网视频学习http://www.imooc.com/learn/263
 * Created by wpq on 16/6/28.
 */
public class AnimationActivity extends AppCompatActivity {

    @Bind(R.id.btn_translateAnimation)
    Button btnTranslateAnimation;
    @Bind(R.id.btn_objectAnimator)
    Button btnObjectAnimator;
    @Bind(R.id.imageview0)
    ImageView imageview0;
    @Bind(R.id.imageview1)
    ImageView imageview1;
    @Bind(R.id.imageview2)
    ImageView imageview2;
    @Bind(R.id.imageview3)
    ImageView imageview3;
    @Bind(R.id.imageview4)
    ImageView imageview4;
    @Bind(R.id.btn_arcMenu)
    Button btnArcMenu;


    private Context context;
    private boolean flagTranslationAnimation = false;
    private boolean flagObjectAnimator = false;
    private boolean flagArcMenu = false;

    private List<View> listViews = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        context = this;

        listViews.add(imageview1);
        listViews.add(imageview2);
        listViews.add(imageview3);
        listViews.add(imageview4);

    }

    @OnClick({R.id.btn_translateAnimation, R.id.btn_objectAnimator, R.id.btn_arcMenu, R.id.imageview0, R.id.imageview1, R.id.imageview2, R.id.imageview3, R.id.imageview4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_translateAnimation: //传统动画TranslateAnimation
            {
                if (!flagTranslationAnimation) {
                    TranslateAnimation translateAnimation = new TranslateAnimation(0f, 400f, 0f, -400f);
                    translateAnimation.setFillAfter(true);
                    translateAnimation.setDuration(600);
                    imageview0.startAnimation(translateAnimation);
                    flagTranslationAnimation = true;
                } else {
                    TranslateAnimation translateAnimation = new TranslateAnimation(400f, 0f, -400f, 0f);
                    translateAnimation.setFillAfter(true);
                    translateAnimation.setDuration(600);
                    imageview0.startAnimation(translateAnimation);
                    flagTranslationAnimation = false;
                }
                break;
            }

            case R.id.btn_objectAnimator: //属性动画ObjectAnimator
            {
                /**
                 * 常用属性：translationX  translationY  rotation  rotationX  rotationY  scaleX  scaleY  X  Y  alpha
                 */

                if (!flagObjectAnimator) {
                    //第一种写法
//                    ObjectAnimator.ofFloat(imageview0, "rotation", 0f, 360f).setDuration(600).start();
//                    ObjectAnimator.ofFloat(imageview0, "translationX", 0f, 400f).setDuration(600).start();
//                    ObjectAnimator.ofFloat(imageview0, "translationY", 0f, -400f).setDuration(600).start();

                    //第二种写法
//                    PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
//                    PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationX", 0f, 400f);
//                    PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("translationY", 0f, -400f);
//                    ObjectAnimator.ofPropertyValuesHolder(imageview0, p1, p2, p3).setDuration(600).start();

                    //第三种写法
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageview0, "rotation", 0f, 360f);
                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageview0, "translationX", 0f, 400f);
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageview0, "translationY", 0f, -400f);
                    AnimatorSet set = new AnimatorSet();
//                    set.playTogether(animator1, animator2, animator3);
//                    set.playSequentially(animator1, animator2, animator3);
                    set.play(animator1).with(animator2);
                    set.play(animator3).after(animator2);
                    set.setDuration(600);
//                    set.addListener(new Animator.AnimatorListener() {
//                        @Override
//                        public void onAnimationStart(Animator animation) {
////                            showToastOnTop("动画开始");
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            showToastOnTop("动画结束，todo sth ~");
//                        }
//
//                        @Override
//                        public void onAnimationCancel(Animator animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animator animation) {
//
//                        }
//                    });
                    set.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            showToastOnTop("动画结束，todo sth ~");
                        }
                    });
                    set.start();
                    flagObjectAnimator = true;
                } else {
                    //one
                    ObjectAnimator.ofFloat(imageview0, "rotation", 360f, 0f).setDuration(600).start();
                    ObjectAnimator.ofFloat(imageview0, "translationX", 400f, 0f).setDuration(600).start();
                    ObjectAnimator.ofFloat(imageview0, "translationY", -400f, 0f).setDuration(600).start();
                    flagObjectAnimator = false;
                }
                break;
            }

            case R.id.btn_arcMenu: //扇形菜单
            {
                if(flagArcMenu){
                    closeArcMenu();
                    flagArcMenu = false;
                }else{
                    showArcMenu();
                    flagArcMenu = true;
                }
                break;
            }

            case R.id.imageview0:
                showToastOnTop("imageview0");
                if(flagArcMenu){
                    closeArcMenu();
                    flagArcMenu = false;
                }else{
                    showArcMenu();
                    flagArcMenu = true;
                }
                break;
            case R.id.imageview1:
                showToastOnTop("imageview1");
                closeArcMenu();
                flagArcMenu = false;
                break;
            case R.id.imageview2:
                showToastOnTop("imageview2");
                closeArcMenu();
                flagArcMenu = false;
                break;
            case R.id.imageview3:
                showToastOnTop("imageview3");
                closeArcMenu();
                flagArcMenu = false;
                break;
            case R.id.imageview4:
                showToastOnTop("imageview3");
                closeArcMenu();
                flagArcMenu = false;
                break;
        }
    }

    private void showToastOnTop(String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }

    private void showArcMenu(){
        for(int i=0; i<listViews.size(); i++){
            double degree = i * 27;
            //第一种写法
            PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
            float x = (float) (400 * Math.cos(degree * Math.PI / 180));
            PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationX", 0f, x);
            float y = (float) (400 * Math.sin(degree * Math.PI / 180));
            PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("translationY", 0f, -y);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(listViews.get(i), p1, p2, p3);
            animator.setDuration(600);
//        animator.setInterpolator(new AccelerateInterpolator()); //加速
//        animator.setInterpolator(new AnticipateInterpolator()); //低于预期
//        animator.setInterpolator(new LinearInterpolator()); //线性
//        animator.setInterpolator(new DecelerateInterpolator()); //减速
//        animator.setInterpolator(new BounceInterpolator()); //弹性
            animator.setInterpolator(new OvershootInterpolator()); //超过预期
            animator.start();
//        Log.e("x , y = ", x + "," + y);
        }
    }

    private void closeArcMenu(){
        for(int i=0; i<listViews.size(); i++){
            double degree = i * 27;
            //第一种写法
            PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation", 360f, 0f);
            float x = (float) (400 * Math.cos(degree * Math.PI / 180));
            PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationX", x, 0f);
            float y = (float) (400 * Math.sin(degree * Math.PI / 180));
            PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("translationY", -y, 0f);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(listViews.get(i), p1, p2, p3);
            animator.setDuration(600);
//        animator.setInterpolator(new AccelerateInterpolator()); //加速
//        animator.setInterpolator(new AnticipateInterpolator()); //低于预期
//        animator.setInterpolator(new LinearInterpolator()); //线性
//        animator.setInterpolator(new DecelerateInterpolator()); //减速
            animator.setInterpolator(new BounceInterpolator()); //弹性
//        animator.setInterpolator(new OvershootInterpolator()); //超过预期
            animator.start();
//        Log.e("x , y = ", x + "," + y);
        }
    }

}
