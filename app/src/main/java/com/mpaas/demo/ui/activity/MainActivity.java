package com.mpaas.demo.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.mpaas.demo.R;
import com.mpaas.demo.adapter.MainAdapter;
import com.mpaas.demo.adapter.MainItemData;
import com.mpaas.demo.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        MainAdapter adapter = new MainAdapter(this, getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<MainItemData> getData() {
        List<MainItemData> intentList = new ArrayList<>();

        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory("com.mpaas.demo.MAIN_ACTIVITY");

        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);

        if (resolveInfoList == null || resolveInfoList.isEmpty())
            return null;

        for (int i = 0; i < resolveInfoList.size(); i++) {
            ResolveInfo resolveInfo = resolveInfoList.get(i);

            Intent actIntent = new Intent();
            actIntent.setClassName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name);

            String lable = resolveInfo.loadLabel(packageManager).toString();
            Logger.d("lable: " + lable);

            MainItemData itemData = new MainItemData(lable, "", actIntent);
            intentList.add(itemData);
        }
        return intentList;
    }

    @Override
    public void onItemClick(@NonNull MainItemData mainItemData) {
        startActivity(mainItemData.getIntent());
    }
}