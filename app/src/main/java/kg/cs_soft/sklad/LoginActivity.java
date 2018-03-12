package kg.cs_soft.sklad;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import dmax.dialog.SpotsDialog;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {
    SharedPreferences prefrences = null;
    SharedPreferences.Editor editor = null;
    CheckBox checkBox = null;
    EditText editText = null;
    EditText editText2 = null;
    private static final boolean AUTO_HIDE = true;

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
   AlertDialog  dialog =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        if (savedInstanceState != null) {
            finish();
        }
        prefrences =  getSharedPreferences("settings",this.MODE_PRIVATE);
        editor = prefrences.edit();
        String ischecked = prefrences.getString("ischecked","");
        if(ischecked!=null&&ischecked.equals("ischecked")){
            Intent intent = new Intent(LoginActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

    }
    public void showAlertDialog(View view){
        dialog = new SpotsDialog(LoginActivity.this);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<3;i++){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                Intent intent = new Intent(LoginActivity.this,Main2Activity.class);
                if(checkBox.isChecked()){
                    editor.putString("ischecked","ischecked");
                    editor.apply();
                }
                if(editText.getText().toString()!=null&&editText.getText().toString()!=""&&editText.getText().toString().length()!=0){
                    editor.putString("login",editText.getText().toString());
                    editor.apply();
                }else{
                    editor.putString("login","Имя ?");
                    editor.apply();
                }
                if (editText2.getText().toString()!=null&&editText2.getText().toString()!=""&&editText2.getText().toString().length()!=0){
                    editor.putString("password",editText2.getText().toString());
                    editor.apply();
                }else{
                    editor.putString("password","password");
                    editor.apply();
                }

                startActivity(intent);
                finish();
            }

        });
        dialog.show();
        thread.start();

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
