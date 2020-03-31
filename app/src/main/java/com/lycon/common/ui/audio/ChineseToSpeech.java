package com.lycon.common.ui.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class ChineseToSpeech {
    public TextToSpeech textToSpeech;

    public ChineseToSpeech(Context context) {
        this.textToSpeech = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.CHINA);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(context, "不支持朗读功能", Toast.LENGTH_SHORT).show();
                } else {
                    textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {
                        }

                        @Override
                        public void onDone(String utteranceId) {
                            destroy();
                        }

                        @Override
                        public void onError(String utteranceId) {
                            destroy();
                        }
                    });
                }
            }
        });

    }

    public void speech(String text) {
        textToSpeech.setSpeechRate(0.3f);
        textToSpeech.setPitch(1f);
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, new HashMap<String, String>() {{
            put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "utterance");
        }});
    }

    public void destroy() {
//        if (textToSpeech != null) {
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
    }
}
