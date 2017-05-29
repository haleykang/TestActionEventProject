package app.android.test.testactioneventproject;

import android.app.Activity;
// 터치 이벤트 감지를 위해 사용하는 MotionEvent
import android.view.MotionEvent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Created by 202-18 on 2017-05-29.
 */

public class MyTouchButtonActivity extends Activity implements View.OnTouchListener {

    Button touchBt1;

    // onCreate() 함수 재정의
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.touch_bt_layout);

        this.touchBt1 = (Button) findViewById(R.id.touchBt1);
        this.touchBt1.setOnTouchListener(this);
        // -> setOnTouchListener(this) :
    }

    // 사용자가 버튼을 터치하는 경우에만 실행되는 onTouch() 함수 재정의
    @Override
    public boolean onTouch(View v, MotionEvent mv) {

        /*
            getAction() 함수 실행 : 터치 이벤트 판단
         */
        int action = mv.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(this, "Action_Down", Toast.LENGTH_SHORT).show();
            {
                // 사용자가 터치한 손의 위치(x,y 좌표) 알아오기
                float xPoint = mv.getX();
                float yPoint = mv.getY();

                Toast.makeText(this, "x좌표 : " + xPoint + "\ny좌표 : " + yPoint,
                        Toast.LENGTH_SHORT).show();

                // 이벤트가 발생한 시간 알아오기 -> 단위는 ms(1/1000초)
                long eventTime = mv.getEventTime();
                Toast.makeText(this, "이벤트 발생 시간 : " + (eventTime / 1000.),
                        Toast.LENGTH_SHORT).show();

            }
            break;
            case MotionEvent.ACTION_UP:
                Toast.makeText(this, "Action_Up", Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_MOVE:
                Toast.makeText(this, "Action_Move", Toast.LENGTH_SHORT).show();
            {
                // 이동 이벤트가 발생할 때 마다 실행되는 명령문
                float xPoint = mv.getX();
                float yPoint = mv.getY();

                Toast.makeText(this, "x좌표 : " + xPoint + "\ny좌표 : " + yPoint,
                        Toast.LENGTH_SHORT).show();

            }
            break;
        }
        return true;
    }


}
