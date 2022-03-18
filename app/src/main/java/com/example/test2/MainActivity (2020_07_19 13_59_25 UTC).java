package com.example.test2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Switch checkAll;
    private Switch checkCh;
    private Switch checkEn;
    private Switch checkMath;
    private Switch checkPhy;
    private Switch checkEarth;
    private Switch checkGeo;
    private Switch checkHis;
    private Switch checkCit;

    private EditText scoreCh;
    private EditText scoreEn;
    private EditText scoreMath;
    private EditText scorePhy;
    private EditText scoreEarth;
    private EditText scoreGeo;
    private EditText scoreHis;
    private EditText scoreCit;

    private EditText weightCh;
    private EditText weightEn;
    private EditText weightMath;
    private EditText weightPhy;
    private EditText weightEarth;
    private EditText weightGeo;
    private EditText weightHis;
    private EditText weightCit;

    private Button calc;
    private Button clear;
    private Button settings;
    private Button radar;

    private TextView total;
    private TextView avgTotal;
    private TextView totalWeight;
    private TextView avgTotalWeight;

    private TextView chinese;
    private TextView english;
    private TextView math;
    private TextView phy;
    private TextView earth;
    private TextView geo;
    private TextView his;
    private TextView cit;

    private double[] Score = new double[8];
    private double[] Weight = new double[8];
    private boolean[] Checked = new boolean[] {true, true, true, true, true, true, true, true};

    private static final int request_code = 1;
    private int index;
    private String subjectName;
    private String[] names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //各科目開關變數
        checkAll = (Switch)findViewById(R.id.use); //控制所有開關
        checkCh = (Switch)findViewById(R.id.chC);
        checkEn = (Switch)findViewById(R.id.enC);
        checkMath = (Switch)findViewById(R.id.mathC);
        checkPhy = (Switch)findViewById(R.id.phyC);
        checkEarth = (Switch)findViewById(R.id.earthC);
        checkGeo = (Switch)findViewById(R.id.geoC);
        checkHis = (Switch)findViewById(R.id.hisC);
        checkCit = (Switch)findViewById(R.id.citC);

        //開關監聽
        checkCh.setOnCheckedChangeListener(checkedChangeListener);
        checkEn.setOnCheckedChangeListener(checkedChangeListener);
        checkMath.setOnCheckedChangeListener(checkedChangeListener);
        checkPhy.setOnCheckedChangeListener(checkedChangeListener);
        checkEarth.setOnCheckedChangeListener(checkedChangeListener);
        checkGeo.setOnCheckedChangeListener(checkedChangeListener);
        checkHis.setOnCheckedChangeListener(checkedChangeListener);
        checkCit.setOnCheckedChangeListener(checkedChangeListener);

        //各科分數變數
        scoreCh = (EditText)findViewById(R.id.chS);
        scoreEn = (EditText)findViewById(R.id.enS);
        scoreMath = (EditText)findViewById(R.id.mathS);
        scorePhy = (EditText)findViewById(R.id.phyS);
        scoreEarth = (EditText)findViewById(R.id.earthS);
        scoreGeo = (EditText)findViewById(R.id.geoS);
        scoreHis = (EditText)findViewById(R.id.hisS);
        scoreCit = (EditText)findViewById(R.id.citS);

        //各科加權變數
        weightCh = (EditText)findViewById(R.id.chW);
        weightEn = (EditText)findViewById(R.id.enW);
        weightMath = (EditText)findViewById(R.id.mathW);
        weightPhy = (EditText)findViewById(R.id.phyW);
        weightEarth = (EditText)findViewById(R.id.earthW);
        weightGeo = (EditText)findViewById(R.id.geoW);
        weightHis = (EditText)findViewById(R.id.hisW);
        weightCit = (EditText)findViewById(R.id.citW);

        //按鈕變數
        calc = (Button)findViewById(R.id.calc);
        clear = (Button)findViewById(R.id.clear);
        settings = (Button)findViewById(R.id.settings);
        radar = (Button)findViewById(R.id.radar_chart);


        //分數結果變數
        total = (TextView)findViewById(R.id.totalScore);
        avgTotal = (TextView)findViewById(R.id.avgScore);
        totalWeight = (TextView)findViewById(R.id.totalWeightScore);
        avgTotalWeight = (TextView)findViewById(R.id.avgWeightScore);

        //科目文字
        chinese = (TextView)findViewById(R.id.chinese);
        english = (TextView)findViewById(R.id.english);
        math = (TextView)findViewById(R.id.math);
        phy = (TextView)findViewById(R.id.physics);
        earth = (TextView)findViewById(R.id.earth);
        geo = (TextView)findViewById(R.id.geo);
        his = (TextView) findViewById(R.id.histroy);
        cit = (TextView)findViewById(R.id.citizen);

        //按鈕監聽
        checkAll.setOnClickListener(checkAllListener);
        calc.setOnClickListener(calcListener);
        clear.setOnClickListener(clearListener);
        settings.setOnClickListener(settingsListener);
        radar.setOnClickListener(radarListener);


    }

    //Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    //Option Menu's Item Selected Activity

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.day_theme:
                getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                restart();
                return true;
            case R.id.dark_theme:
                getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                restart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isDarkMode() {
        return (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void restart() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    //科目開關執行
    private Switch.OnCheckedChangeListener checkedChangeListener =
            new Switch.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                    switch (button.getId()) {
                        case R.id.chC:
                            Checked[0] = isChecked;
                            //Log.d("Boolean", Boolean.toString(isChecked));
                            break;
                        case R.id.enC:
                            Checked[1] = isChecked;
                            break;
                        case R.id.mathC:
                            Checked[2] = isChecked;
                            break;
                        case R.id.phyC:
                            Checked[3] = isChecked;
                            break;
                        case R.id.earthC:
                            Checked[4] = isChecked;
                            break;
                        case R.id.geoC:
                            Checked[5] = isChecked;
                            break;
                        case R.id.hisC:
                            Checked[6] = isChecked;
                            break;
                        case R.id.citC:
                            Checked[7] = isChecked;
                            break;
                    }
                }
            };


    //計算按鈕點擊
    private Button.OnClickListener calcListener =
            new Button.OnClickListener() {
                public void onClick(View v) {
                    setAllScore();
                    //Log.d("Check", Double.toString(getCheckedCount()));
                    double totalScore = RoundDouble(getTotal(Score));
                    double totalAvgScore = RoundDouble(totalScore/getCheckedCount());
                    double totalWeightScore = RoundDouble(getWeightedTotal(Score,Weight));
                    double totalWeightAvgScore = RoundDouble(totalWeightScore/getTotal(Weight));
                    TextViewSetText(total,TransType(totalScore));
                    TextViewSetText(avgTotal,TransType(totalAvgScore));
                    TextViewSetText(totalWeight,TransType(totalWeightScore));
                    TextViewSetText(avgTotalWeight,TransType(totalWeightAvgScore));
                }
    };

    //清除按鈕點擊
    private Button.OnClickListener clearListener =
            new Button.OnClickListener() {
                public void onClick(View v) {
                    setOriginal();
                }

            };

    //設定按鈕點擊
    private Button.OnClickListener settingsListener =
            new Button.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SettingsPage.class);
                    intent.putExtra("subject", getSubjectNameArray());
                    startActivityForResult(intent, request_code);
                }

            };

    //雷達圖按鈕
    private Button.OnClickListener radarListener =
            new Button.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Radar_Chart.class);
                    intent.putExtra("subjectName", getSubjectNameArray());
                    setAllScore();
                    intent.putExtra("Score", Score);
                    intent.putExtra("darkmode", isDarkMode());
                    startActivity(intent);
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case request_code:
                subjectName = data.getStringExtra("subjectName");
                index = data.getIntExtra("index", -1);
                Log.d("index: ", Integer.toString(index));
                Log.d("subject", subjectName);
                setSubjectName(index, subjectName);
                break;
        }
    }

    //設定科目文字顯示
    private void setSubjectName(int index, String name) {
        if (name.equals("")) return;
        switch (index) {
            case 0:
                chinese.setText(name);
                break;
            case 1:
                english.setText(name);
                break;
            case 2:
                math.setText(name);
                break;
            case 3:
                phy.setText(name);
                break;
            case 4:
                earth.setText(name);
                break;
            case 5:
                geo.setText(name);
                break;
            case 6:
                his.setText(name);
                break;
            case 7:
                cit.setText(name);
                break;
        }
    }

    //取得各科文字到陣列
    private String[] getSubjectNameArray() {
        String[] Subject = new String[8];
        Subject[0] = chinese.getText().toString();
        Subject[1] = english.getText().toString();
        Subject[2] = math.getText().toString();
        Subject[3] = phy.getText().toString();
        Subject[4] = earth.getText().toString();
        Subject[5] = geo.getText().toString();
        Subject[6] = his.getText().toString();
        Subject[7] = cit.getText().toString();
        return Subject;
    }


    //所有科目使用點擊
    private Switch.OnClickListener checkAllListener =
            new Switch.OnClickListener() {
                public void onClick(View v) {
                    boolean turn = checkAll.isChecked();
                    if (turn) {
                        ChangeAllSwitch(true);
                    } else {
                        ChangeAllSwitch(false);
                    }
                }
            };


    //更改各科開關
    private void ChangeAllSwitch(boolean turn) {
        checkCh.setChecked(turn);
        checkEn.setChecked(turn);
        checkMath.setChecked(turn);
        checkPhy.setChecked(turn);
        checkEarth.setChecked(turn);
        checkGeo.setChecked(turn);
        checkHis.setChecked(turn);
        checkCit.setChecked(turn);
    }

    //分數結果輸出
    private void TextViewSetText(TextView target,String str) {
        target.setText(str);
    }

    //取得輸入字串
    private String getEditText(EditText text) {
        return text.getText().toString();
    }

    //取得總數
    private double getTotal(double[] number) {
        double total = 0.0;
        for (int i = 0;i < number.length;i++) {
            if (Checked[i]) {
                total += number[i];
            }
        }
        return total;
    }

    //取得加權總數
    private double getWeightedTotal(double[] number, double[] weight) {
        double total = 0.0;
        for (int i = 0;i < number.length;i++) {
            if (Checked[i]) {
                total += number[i]*weight[i];
            }
        }
        return total;
    }

    //取得選用個數
    private double getCheckedCount() {
        int count = 0;
        for (boolean check : Checked) {
            if (check) {
                count++;
            }
        }
        return (double) count;
    }

    //字串轉小數
    private double TransType(String s) {
        return Double.valueOf(s);
    }

    //小數轉字串
    private String TransType(Double d) {
        return String.valueOf(d);
    }

    //初始化
    private void setOriginal() {
        scoreCh.setText("0");
        scoreEn.setText("0");
        scoreMath.setText("0");
        scorePhy.setText("0");
        scoreEarth.setText("0");
        scoreGeo.setText("0");
        scoreHis.setText("0");
        scoreCit.setText("0");

        weightCh.setText("4");
        weightEn.setText("4");
        weightMath.setText("4");
        weightPhy.setText("2");
        weightEarth.setText("2");
        weightGeo.setText("2");
        weightHis.setText("2");
        weightCit.setText("2");

        total.setText("0.0");
        avgTotal.setText("0.0");
        totalWeight.setText("0.0");
        avgTotalWeight.setText("0.0");
    }

    //四捨五入到小數點後第二位
    private double RoundDouble(double number) {
        return (double) ((Math.round(number*100.0)/100.0));
    }

    private void setAllScore() {
        try {

            //分數陣列
            Score[0] = TransType(getEditText(scoreCh));
            Score[1] = TransType(getEditText(scoreEn));
            Score[2] = TransType(getEditText(scoreMath));
            Score[3] = TransType(getEditText(scorePhy));
            Score[4] = TransType(getEditText(scoreEarth));
            Score[5] = TransType(getEditText(scoreGeo));
            Score[6] = TransType(getEditText(scoreHis));
            Score[7] = TransType(getEditText(scoreCit));

            //加權陣列
            Weight[0] = TransType(getEditText(weightCh));
            Weight[1] = TransType(getEditText(weightEn));
            Weight[2] = TransType(getEditText(weightMath));
            Weight[3] = TransType(getEditText(weightPhy));
            Weight[4] = TransType(getEditText(weightEarth));
            Weight[5] = TransType(getEditText(weightGeo));
            Weight[6] = TransType(getEditText(weightHis));
            Weight[7] = TransType(getEditText(weightCit));

        } catch (Exception e) {
            System.out.print("ERROR");
        }
    }

}
