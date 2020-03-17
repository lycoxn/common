package com.lycon.common.ui.hook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lycon.common.R;

/**
 * Created by liuyicen on 2019-10-18 15:52.
 */
public class ProxyHookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy_hook);
    }
}
