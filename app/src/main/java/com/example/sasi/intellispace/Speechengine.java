package com.example.sasi.intellispace;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Speechengine extends AppCompatActivity implements  TextToSpeech.OnInitListener,TextToSpeech.OnUtteranceCompletedListener  {
    private Context context;
    SpeechRecognizer speechRecognizer;
    Button speech;
    private TextToSpeech tvvs;
    RecognitionProgressView recognitionProgressView;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speechengine);
        context=Speechengine.this;
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speech=(Button)findViewById(R.id.speech);
        tvvs=new TextToSpeech(Speechengine.this,Speechengine.this);
        recognitionProgressView = (RecognitionProgressView) findViewById(R.id.recognition_view);
        int[] colors = {
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this,R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorPrimary)
        };
        int[] heights = {60, 76, 58, 80, 55};
        recognitionProgressView.setColors(colors);
        recognitionProgressView.setBarMaxHeightsInDp(heights);
        recognitionProgressView.play();
        recognitionProgressView.setSpeechRecognizer(speechRecognizer);
        recognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
            @Override
            public void onResults(Bundle results) {
                showResults(results);
            }
        });
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvvs.isSpeaking())
                {
                    HashMap<String,String> params=new HashMap<String, String>();
                    params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                    tvvs.speak("Hi ",TextToSpeech.QUEUE_ADD,params);
                    tvvs.speak("Hi Which Building are you looking for the meeting? ",TextToSpeech.QUEUE_ADD,params);

                }
                else
                {
                    tvvs.stop();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startRecognition();
                    }
                },3500);


            }
        });

    }
    private void startRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en");
        speechRecognizer.startListening(intent);

    }
    private void showResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        //Toast.makeText(this, matches.get(0), Toast.LENGTH_LONG).show();
        Iterator iterator=matches.iterator();

            System.out.println("Bow"+matches.get(0));
            if(matches.get(0).toLowerCase().equals("bird")) {
                if (!tvvs.isSpeaking()) {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampleText");
                    tvvs.speak("Ok! Which Type of Meeting are you looking for! ", TextToSpeech.QUEUE_ADD, params);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startRecognition();
                        }
                    },3500);


                } else {
                    tvvs.stop();
                }
            }
            if(matches.get(0).toLowerCase().equals("audio room")||matches.get(0).equals("Video Room")){
                    if(!tvvs.isSpeaking()){
                        HashMap<String,String>params = new HashMap<String,String>();
                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
                        tvvs.speak("Who are all the participants for the meeting ", TextToSpeech.QUEUE_ADD, params);
                    }
//                    else{
//                        HashMap<String,String>params = new HashMap<String,String>();
//                        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,"sampleText");
//                        tvvs.speak("Ok! Which Type of Meeting are you looking for! ", TextToSpeech.QUEUE_ADD,params);
//                    }
                else{
                        tvvs.stop();
                    }
                }


    }
    @Override
    protected void onDestroy() {
        if(tvvs!=null)
        {
            tvvs.stop();
            tvvs.shutdown();

            tvvs=null;
        }
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int i) {
        tvvs.setOnUtteranceCompletedListener(this);
    }

    @Override
    public void onUtteranceCompleted(String s) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Completed", Toast.LENGTH_SHORT).show();
               // recognitionProgressView.play();


            }
        });
    }
}
