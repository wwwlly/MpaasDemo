package com.mpaas.demo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alipay.mobile.cubedebug.crystal.CubeCardDebug;
import com.mpaas.demo.R;

public class AntCubActivity extends AppCompatActivity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, AntCubActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ant_cube);

    }

    public void onClickDebug(View view) {
        CubeCardDebug.openScanner(this);
    }

    public void onClickDemo(View view) {
        AntCubPageActivity.startActivity(this);
    }
}
