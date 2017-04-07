package com.demo.wpq.mydemo.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.qrcode.utils.ZXUtil;
import com.google.zxing.WriterException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc: 生成二维码
 * Created by wpq on 16/7/10.
 */
public class QRCodeCreateActivity extends AppCompatActivity {

    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.et_source)
    EditText etSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_create);
        ButterKnife.bind(this);


    }

    /**
     * 生成二维码
     * @param v
     */
    public void clickCreate(View v){
        String source = etSource.getText().toString().trim();
        if(TextUtils.isEmpty(source)){
            etSource.setError("内容不能为空");
            return;
        }

        try {
            Bitmap bitmap = ZXUtil.create2Decode(source);
            ivQrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
