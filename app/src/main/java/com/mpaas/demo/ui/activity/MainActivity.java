package com.mpaas.demo.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mpaas.demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 77700002
     * https://tech.antfin.com
     */
    public void onClickH5(View view) {
        NebulaActivity.startActivity(this, "77700002");
    }
}