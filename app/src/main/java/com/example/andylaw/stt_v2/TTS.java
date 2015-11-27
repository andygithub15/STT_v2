package com.example.andylaw.stt_v2;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

import java.util.Locale;

/**
 * Created by Andy_Law on 19/11/2015.
 */
public class TTS {
    /*
    TextToSpeech engine;
    engine = new TextToSpeech(this,this);
    public void onInit(int status) {
        Log.d("Speech", "OnInit - Status [" + status + "]");

        if (status == TextToSpeech.SUCCESS) {
            Log.d("Speech", "Success!");
            engine.setLanguage(Locale.getDefault());
        }else if(status == TextToSpeech.ERROR){
            Log.d("Speech", "ERROR!");
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }
    public void speech(String toSpeech){
        //engine.speak(toSpeech, TextToSpeech.QUEUE_FLUSH, null, null);
        engine.speak(toSpeech, TextToSpeech.QUEUE_FLUSH, null);
    }
    */


    private TextToSpeech myTTS;
    private boolean readyToSpeak = false;
    private Context context;

    public TTS(Context baseContext)
    {
        this.context = baseContext;
    }

    public void initOrInstallTTS()
    {
        myTTS = new TextToSpeech(context, new OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                if (status == TextToSpeech.SUCCESS)
                {
                    myTTS.setLanguage(Locale.getDefault());
                    readyToSpeak = true;
                }
                else

                    installTTS();
            }
        });
    }

    private void installTTS()
    {
        Intent installIntent = new Intent();
        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
        context.startActivity(installIntent);
    }

    public void speak(String text)
    {
        if (readyToSpeak)
            myTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
}
