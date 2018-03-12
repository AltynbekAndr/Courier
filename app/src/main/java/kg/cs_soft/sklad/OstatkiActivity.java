package kg.cs_soft.sklad;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class OstatkiActivity extends AppCompatActivity {
    ListView myList = null;
    List<String> list = null;
    List<String> list2 = null;
    List<String> list3 = null;
    List<String> list4 = null;
    List<String> list5 = null;
    List<String> list6 = null;
    Spinner spinner =null;
    ArrayAdapter<?> adapter = null;
    private RotateLoading rotateLoading;
    LinearLayout titleLin = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ostatki);
        if (savedInstanceState != null) {
            finish();
        }
        SharedPreferences prefrences = getSharedPreferences("settings",this.MODE_PRIVATE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(prefrences.getString("login",""));
        myList = (ListView) findViewById(R.id.myList);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(OstatkiActivity.this, R.array.otdelenie, android.R.layout.simple_list_item_1);
        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        titleLin = (LinearLayout) findViewById(R.id.titleLin);

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        list5 = new ArrayList<>();
        list6 = new ArrayList<>();
        for(int i =0;i<100;i++){
            list.add("Floretta");
            list2.add("1кг");
            list3.add("6-штук");
            list4.add("50сом");
            list5.add("40сом");
            list6.add("240сом");
        }

        Toast.makeText(OstatkiActivity.this,"Отделение : Alina",Toast.LENGTH_SHORT).show();
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(OstatkiActivity.this,list,list2,list3,list4,list5,list6);
        SwingLeftInAnimationAdapter swingLeftInAnimationAdapter = new SwingLeftInAnimationAdapter(customArrayAdapter);
        swingLeftInAnimationAdapter.setAbsListView(myList);
        myList.setAdapter(swingLeftInAnimationAdapter);

        /*AlphaAnimationAdapter
                ScaleInAnimationAdapter
        SwingBottomInAnimationAdapter
                SwingLeftInAnimationAdapter
        SwingRightInAnimationAdapter*/
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(rotateLoading.getVisibility()== View.VISIBLE){
                    rotateLoading.setVisibility(rotateLoading.GONE);
                    rotateLoading.stop();
                    titleLin.setVisibility(titleLin.VISIBLE);
                    myList.setVisibility(myList.VISIBLE);
                }else if(rotateLoading.getVisibility()==View.GONE){
                    rotateLoading.setVisibility(rotateLoading.VISIBLE);
                    rotateLoading.start();
                    titleLin.setVisibility(titleLin.GONE);
                    myList.setVisibility(myList.GONE);
                }
                return true;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(1);

                    }
                });
                t.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }






















    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
