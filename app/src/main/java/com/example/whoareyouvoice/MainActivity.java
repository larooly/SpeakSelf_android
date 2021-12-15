package com.example.whoareyouvoice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.Locale;





public class MainActivity extends AppCompatActivity {
    TextToSpeech mTTs;
    EditText mEditText;
    SeekBar mSeekBarQ;
    SeekBar mSeekBarS;
    Button Good;
    SpecialEditText kk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Good = findViewById(R.id.SpeakNow);
        mTTs = new TextToSpeech(this,new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = mTTs.setLanguage(Locale.KOREAN);//언어 설정
                    if (result == TextToSpeech.LANG_MISSING_DATA || result ==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Oh my god");
                    }else {
                        Good.setEnabled(true);
                    }
                }else {
                    Log.e("TTS","Oh my god2");
                }
            }
        });

        kk = findViewById(R.id.ttetsttets);
        kk.setKeyImeChangeListener(new SpecialEditText.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                if(KeyEvent.KEYCODE_BACK== event.getKeyCode()){

                    Log.e("TTS","tetste");
                }
            }
        });

        mEditText = findViewById(R.id.whatspeak);
        mSeekBarQ = findViewById(R.id.seekBarQ);
        mSeekBarS = findViewById(R.id.seekBarS);
        Good.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                speak();
            }
        });
        mEditText.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TTS","PPPPP");
                return false;
            }
        });




    }
    public void speak(){
        String text = mEditText.getText().toString();
        float pitch =(float)mSeekBarQ.getProgress()/50;
        if(pitch <0.1){pitch=0.1f;}
        float speed =(float)mSeekBarS.getProgress()/50;
        if(speed <0.1){speed=0.1f;}

        mTTs.setPitch(pitch);
        mTTs.setSpeechRate(speed);

        mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);
        

    }

}
