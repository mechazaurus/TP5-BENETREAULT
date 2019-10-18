package com.ybene.projects.httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ybene.projects.httprequest.tools.CallWebAPI;

public class IPAPIActivity extends AppCompatActivity {

    private EditText etIPAddress;
    private TextView tvResult;
    private Button bStart;
    private CallWebAPI callWebAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipapi);

        etIPAddress = findViewById(R.id.activity_ipapi_ip_address);
        tvResult = findViewById(R.id.activity_ipapi_result);
        bStart = findViewById(R.id.activity_ipapi_button_start);

        callWebAPI = new CallWebAPI(tvResult);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = "http://ip-api.com/xml/" + etIPAddress.getText().toString();
                callWebAPI.execute(ipAddress);
            }
        });


    }
}
