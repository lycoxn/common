package com.lycon.common.ui.hook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lycon.common.R;

/**
 * Created by liuyicen on 2019-10-18 15:43.
 */
public class HookTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        findViewById(R.id.skip).setOnClickListener(v -> startActivity(new Intent(this, ProxyHookActivity.class)));
    }
}
