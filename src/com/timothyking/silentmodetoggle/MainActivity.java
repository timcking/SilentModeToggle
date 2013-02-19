package com.timothyking.silentmodetoggle;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.media.AudioManager;

public class MainActivity extends Activity {
	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;
	private static final String TAG = "SilentModeApp";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        
        checkIfPhoneIsSilent();
        setButtonClickListener();
    }
        
        private void setButtonClickListener () {
            Button toggleButton = (Button)findViewById(R.id.toggleButton);
            toggleButton.setOnClickListener(new View.OnClickListener() {
                
                public void onClick(View v) {
                	if (mPhoneIsSilent) {
                		// Change back to normal mode
                		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                		mPhoneIsSilent = false;
                        Log.d(TAG, "** Now in normal mode");
                    } else {
                		// Change to silent mode
                		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                		mPhoneIsSilent = true;
                        Log.d(TAG, "** Now in silent mode");
                    }
                    
                	// Now toggle the UI again
                    toggleUi();
                }
            });
        }
        
    /**
     * Checks to see if the phone is currently in silent mode.
     */
    private void checkIfPhoneIsSilent() {
        int ringerMode = mAudioManager.getRingerMode();
       	if (ringerMode == AudioManager.RINGER_MODE_SILENT) {
       	    mPhoneIsSilent = true;
        } else {
            mPhoneIsSilent = false;
        }
    }

    /**
     * Toggles the UI images from silent to normal and vice versa.
     */
    private void toggleUi() {
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);
        Drawable newPhoneImage;
        
        if (mPhoneIsSilent) {
            newPhoneImage = getResources().getDrawable(R.drawable.phone_silent);
        } else {
            newPhoneImage = getResources().getDrawable(R.drawable.phone_on);
        }
        
        imageView.setImageDrawable(newPhoneImage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfPhoneIsSilent();
        toggleUi();
    }
}