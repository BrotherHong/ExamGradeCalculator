package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsPage extends AppCompatActivity {

    private Button close;
    private Spinner subject;
    private EditText subjectN;

    private static final int request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        subject = (Spinner)findViewById(R.id.subject_choose);

        Intent intent = getIntent();
        String[] subjects = intent.getStringArrayExtra("subject");

        ArrayAdapter<String> ChooseSub = new ArrayAdapter<String>(
                this, R.layout.myspinner, subjects
        );
        ChooseSub.setDropDownViewResource(R.layout.myspinner);
        subject.setAdapter(ChooseSub);

        close = (Button)findViewById(R.id.close);
        subjectN = (EditText)findViewById(R.id.subjectN);

        close.setOnClickListener(closeListener);

    }

    //回主頁按鈕
    private Button.OnClickListener closeListener =
            new Button.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = getIntent();

                    String subjectName = subjectN.getText().toString();
                    int index = (int) subject.getSelectedItemId();

                    Bundle bundle = new Bundle();
                    bundle.putString("subjectName", subjectName);
                    bundle.putInt("index", index);
                    intent.putExtras(bundle);
                    setResult(request_code, intent);

                    finish();
                }
            };

}
