package app.android.test.testactioneventproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
// import android.icu.util.Calendar; 이것도 있는데 이걸로 쓰면 안됨



public class DatePickUpActivity extends AppCompatActivity {

    // 달력 변수 선언
    private Calendar calendar;

    // 날짜 정보 변수 선언
    private int year;
    private int month;
    private int day;

    private TextView textView;
    private Button changeBt;

    private TextView toTimePickUpBt;

    // 화면에 날짜 창을 출력할 때 사용할 DatePickerDialog 변수 선언
    private DatePickerDialog datePickerDialog;

    // 출력 전용 함수
    // 무조건 현재 달 + 1을 반환하는 함수
    public int showMonth(int monthValue) {

        return(monthValue + 1);

    }

    // 현재 연/월/일을 읽어와서 전역변수에 저장하고 textView창에 출력해주는 함수
    public void getDateAndShow() {

        // 달력 객체를 가져오기
        // (싱글톤 디자인 패턴을 사용 : getInstance() 함수 안에서 객체를 생성 후 반환

        // import android.icu.util.Calendar; 이걸로 쓰면 안됨
        // impot java.util.Calendar를 사용!!!!
        calendar = Calendar.getInstance();

        // 현재 날짜를 가져옴
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        textView.setText(year+"/" + showMonth(month) +"/" + day);
        // get(Calendar.MONTH) 함수는 1월 = 0 , 2월 = 1, 3월 = 2...
        // 이런식으로 값을 반환함 -> 정확한 달을 확인 하기 위해 +1 하는거!
        // month + 1 -> showMonth(month) 함수로 대체


    }

    // 사용자가 선택한 년/월/일을 받는 함수
    // 전역 변수에 각각 저장하고 TextView 창에도 출력
    public void setDateAndShow(int yearValue, int monthValue, int dayValue) {

        year = yearValue;
        month = monthValue;
        day = dayValue;

        Toast.makeText(this,
                "변경 : " + year + "/" + showMonth(month) + "/" + day,
                Toast.LENGTH_SHORT).show();

        textView.setText(year+"/"+showMonth(month)+"/"+day);


    }


    // 날짜 다이얼로그 창을 만들기 위해 필요한 리스너 객체 선언 & 생성
    // onDateSet() 함수 재정의
    // -> 사용자가 날짜 다이얼로그 창에서 출력된 년/월/일을 선택 & 변경 할 경우 자동으로 실행되는 함수
    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker datePicker, int yearValue, int monthValue,
                                      int dayValue) {

                    // 여기서는 this를 사용할 수 없음
                    // new 를 사용하면 이쪽은 다른 클래스가 됨
                    // 따라서 클래스 이름.this를 붙이기
                    Toast.makeText(DatePickUpActivity.this, "날짜 선택/변경",
                            Toast.LENGTH_SHORT).show();

                    // 위에서 선언한 전역 변수에 날짜 저장
                    // -> 사용자가 선택한 날짜를 다른 함수에서도 사용 가능
                    // 여기서 전역 변수와 매개 변수 이름이 같으면 안됨! 왜냐면 this.전역 변수를
                    // 사용 할 수 없기 때문!
                    // 여기서 this를 사용하면 OnDateSetListener 쪽 메모리에 저장됨
                    year = yearValue;
                    month = monthValue;
                    day = dayValue;

                    Toast.makeText(DatePickUpActivity.this,
                            "현재 : " + year + "/" + showMonth(month) + "/" + day,
                            Toast.LENGTH_SHORT).show();

                    setDateAndShow(year,month,day);



                }
            }; // end of dateSetListener


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_pick_up);

        this.textView = (TextView)findViewById(R.id.dateTextView);
        this.changeBt = (Button)findViewById(R.id.dateChangeBt);

        // 사용자가 버튼을 클릭하는 경우에는 화면네 날짜 창을 출력
        this.changeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog =
                        new DatePickerDialog(DatePickUpActivity.this,
                                dateSetListener, year,month,day);

                // show() 함수를 실행해서 날짜 창을 출력하기
                datePickerDialog.show();

            }
        });

        // 현재 날짜를 가져오는 함수
        getDateAndShow();

        this.toTimePickUpBt = (TextView)findViewById(R.id.toTimePickUpBt);

        this.toTimePickUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), TimePickUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
