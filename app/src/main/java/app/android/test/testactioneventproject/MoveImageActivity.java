package app.android.test.testactioneventproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/*
    TouchEvent를 뷰 그룹에 설정
 */

public class MoveImageActivity extends AppCompatActivity implements View.OnTouchListener {


    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    // 사용자가 터치한 좌표를 저장할 변수
    private int xPoint = 0;
    private int yPoint = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_image);

        this.linearLayout = (LinearLayout)this.findViewById(R.id.moveImageLayout);
        this.imageView = (ImageView)this.findViewById(R.id.moveImageView);
        this.textView = (TextView)this.findViewById(R.id.textView);

        this.linearLayout.setOnTouchListener(this);

        this.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MoveImageActivity.this, DatePickUpActivity.class);
                startActivity(intent);
            }
        });


    }



    /*
        1. 사용자가 발생시킨 3가지 종류의 터치 이벤트에 대해서 동일하게 처리
             1) MoveEvent.ACTION_DOWN, ACTION_UP, ACTION_MOVE

     */

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();

        //Toast.makeText(this,"무브무브",Toast.LENGTH_SHORT).show();
        // 그림 중앙에 넣기 위해 이    미지 크기만큼 좌표 빼봄
        float fxPoint = motionEvent.getX()-50;
        float fyPoint = motionEvent.getY()-50;

        // 실수 데이터를 객체로 변환
        // 굳이 객체로 안하고 정수 값으로 바꿔도 될듯..
        Float fxPointOb = new Float(fxPoint);
        Float fyPointOb = new Float(fyPoint);

        // 객체가 갖고 있는 실수 데이터를 정수 데이터로 변환
        xPoint = fxPointOb.intValue();
        yPoint = fyPointOb.intValue();
/*
               Toast.makeText(this,"**터치 좌표**\nx좌표 : " + xPoint + "\ny좌표 : " + yPoint,
                       Toast.LENGTH_SHORT).show();*/

        // ImageView 클래스가 갖고 있는 setX(), setY() 함수를 실행해서 이미지의 위치를
        // 사용자가 터치한 곳으로 바꾸기

        switch (action) {
            // 3가지 모두 하나의 명령어로 묶을 경우
            // ACTION_DOWN 다음에 ACTION_UP 이나 ACTION_MOVE가 와야 할 듯
            case MotionEvent.ACTION_DOWN:
                // Toast.makeText(this,"다운다운",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"**터치 좌표**\nx좌표 : " + xPoint + "\ny좌표 : " + yPoint,
                        Toast.LENGTH_SHORT).show();
            case MotionEvent.ACTION_UP :
                // Toast.makeText(this,"업업",Toast.LENGTH_SHORT).show();
            case MotionEvent.ACTION_MOVE:
           {
               /*/Toast.makeText(this,"무브무브",Toast.LENGTH_SHORT).show();
               // 그림 중앙에 넣기 위해 이    미지 크기만큼 좌표 빼봄
               float fxPoint = motionEvent.getX()-100;
               float fyPoint = motionEvent.getY()-100;

               // 실수 데이터를 객체로 변환
               Float fxPointOb = new Float(fxPoint);
               Float fyPointOb = new Float(fyPoint);

               // 객체가 갖고 있는 실수 데이터를 정수 데이터로 변환
               xPoint = fxPointOb.intValue();
               yPoint = fyPointOb.intValue();
*//*
               Toast.makeText(this,"**터치 좌표**\nx좌표 : " + xPoint + "\ny좌표 : " + yPoint,
                       Toast.LENGTH_SHORT).show();*//*

               // ImageView 클래스가 갖고 있는 setX(), setY() 함수를 실행해서 이미지의 위치를
               // 사용자가 터치한 곳으로 바꾸기

               주석 부분을 위로 올림 -> ACTION_DOWN의 경우만 좌표 나오도록 하기 위해서!
               */


               imageView.setX(fxPoint);
               imageView.setY(fyPoint);
           }
           break;
        }


        return true;
    }
}
