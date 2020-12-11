package com.example.donotforget;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DetailActivity extends AppCompatActivity {

    public TextView mBirthDayForm;

    public int mYear;
    public int mMonth;
    public int mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar mToolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_18dp); //뒤로가기 버튼 이미지 지정

        mBirthDayForm = findViewById(R.id.birthDayForm);
        mBirthDayForm.setText(calcToday());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

     public String calcToday() {
        TimeZone tz;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREAN);
        tz = TimeZone.getTimeZone("Asia/Seoul");
        dateFormat.setTimeZone(tz);
        Date date = new Date();

        return dateFormat.format(date);
     }

    public void clickBirthDay(View v) {
        Intent intent = new Intent(getApplicationContext(), DatePickerActivity.class);

        startActivityForResult(intent, 0);
        Log.d("tag","클릭");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        mYear = data.getIntExtra("mYear", -1);
        mMonth = data.getIntExtra("mMonth", -1) + 1;
        mDay = data.getIntExtra("mDay", -1);

        if( requestCode == 0 ){
            if (resultCode==RESULT_OK) {
                Toast.makeText(this, mYear + "" + mMonth + "" + mDay, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "result cancle!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}