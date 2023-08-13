package com.mpaas.demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mpaas.demo.R;

public class NebulaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nebula);
    }

    public void onClickNebulaUC(View view) {

    }

    public void onClickNebulaChrome(View view) {

    }

    public void onClickWebView(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }
}
