package app.android.test.testactioneventproject;

// TimePickerDialog 클래스를 사용하는 예제(시/분 변경)

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TimePickerDialog;
// 버튼 클릭 함수를 정의할 때
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
// 달력 클래스 사용
import java.util.Calendar;


public class TimePickUpActivity extends AppCompatActivity {

    // 1. 변수 선언
    // 1-1) TimePickerDialog 클래스를 사용한 참조 변수
    private TimePickerDialog timePickerDialog;

    // 1-2) 달력 변수
    private Calendar calendar;

    // 1-3) 시간/분 보관 변수 -> 매개 변수 이름과 겹치는 걸 방지하기 위해 m을 붙이기!
    private int mHour;
    private int mMinute;



    private TextView timeTextView;
    private Button changeTimeBt;

    // 2. 재정의 함수

    // 3. 생성자 함수

    // 4. 일반 함수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_pick_up);
    }
}
