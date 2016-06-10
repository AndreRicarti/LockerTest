package com.example.bcf_andre.lockertest;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    protected static final int REQUEST_ENABLE = 0;
    DevicePolicyManager devicePolicyManager;
    ComponentName adminComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(btnListener);
    }

    Button.OnClickListener btnListener = new Button.OnClickListener() {
        public void onClick(View v) {
            adminComponent = new ComponentName(MainActivity.this, Darclass.class);
            devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

            if (!devicePolicyManager.isAdminActive(adminComponent)) {

                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
                startActivityForResult(intent, REQUEST_ENABLE);
            } else {
                devicePolicyManager.lockNow();
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_ENABLE == requestCode) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
