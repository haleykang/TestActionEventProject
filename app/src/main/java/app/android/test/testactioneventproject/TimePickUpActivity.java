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

    // 1-3) 시간/분/초 보관 변수 -> 매개 변수 이름과 겹치는 걸 방지하기 위해 m을 붙이기!
    private int mHour;
    private int mMinute;
    private int mSecond;

    // 1-4) "AM", "PM" 저장 변수
    private String mAmPm;

    // 시간 출력 방식 정하기
    // 0 ~ 11 : Calendar.HOUR 사용
    // 0 ~ 23 : Calendar.HOUR_OF_DAY 사용
    // 초 : Calendar.SECOND 사용


    private TextView mTimeTextView;
    private Button mChangeTimeBt;

    /*
        사용자가 TimePickerDialog 클래스를 사용한 다이얼로그에서 시/분을 변경하는 경우에
        자동으로 실행되어야하는 함수 재정의
        사용자가 선택/변경한 시/분을 읽어오는 함수
     */
    private TimePickerDialog.OnTimeSetListener onTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    mHour = hourOfDay;
                    mMinute = minute;

                    /*
                        24시간(0~23) : "AM" 또는 "PM" 문자열 저장
                     */
                    /*if(mHour < 12) {
                        // 시간이 0~11인 경우
                        mAmPm = "AM";

                    } else {

                        // 시간이 12~23인 경우
                        mAmPm = "PM";
                        mHour -= 12;
                        // mHour = mHour-12;
                        // 그럼 정오(12:00 PM은 0:00PM인데...흠...
                    }*/


                    if(mHour < 12) {
                        mAmPm = "AM";
                    } else if(mHour == 12) {
                        mAmPm = "PM";
                    } else {
                        mAmPm = "PM";
                        mHour -= 12;
                    }


                    // 전역 변수에 저장된 시/분을 텍스트 뷰에 출력
                    mTimeTextView.setText(mHour + ":" + mMinute + " " + mAmPm);


                }
            };
    // onTimeSetListener 변수는 TimePickerDialog 클래스에서 무조건 사용함


    // 2. 재정의 함수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_pick_up);

        this.mTimeTextView = (TextView) findViewById(R.id.timeTextView);
        this.mChangeTimeBt = (Button) findViewById(R.id.changeTimeBt);

        this.mChangeTimeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePickerDialog = new TimePickerDialog(TimePickUpActivity.this,
                        onTimeSetListener, mHour, mMinute, true);
                /*
                    TimePickerDialog(TimePickUpActivity.this,
                        onTimeSetListener, 10,30,true) -> true의 경우 0~23 시간
                    TimePickerDialog(TimePickUpActivity.this,
                        onTimeSetListener, 10,30,false) -> false의 경우 0~12 시간
                 */

                timePickerDialog.show();
            }
        });

        readTimeAndShow();
    }

    // 3. 생성자 함수
    // -> 필요 없음

    // 4. 일반 함수

    // 4-1) 달력 객체에서 현재 시스템 시/분을 읽어오는 함수
    public void readTimeAndShow() {

        // 달력 객체 얻기
        calendar = Calendar.getInstance();

        // 달력에서 시간 가져오기
        mHour = calendar.get(Calendar.HOUR_OF_DAY);

        // 달력에서 분 가져오기
        mMinute = calendar.get(Calendar.MINUTE);

        // AM 또는 PM 문자열 저장
        /*if(mHour < 12) {
            mAmPm = "AM";
        } else {
            mAmPm = "PM";
            mHour -= 12;
        }*/

        if(mHour < 12) {
            mAmPm = "AM";
        } else if(mHour == 12) {
            mAmPm = "PM";
        } else {
            mAmPm = "PM";
            mHour -= 12;
        }

        // 시/분/AM or PM 출력

        mTimeTextView.setText(mHour + ":" + mMinute + " " + mAmPm);

    }

}
