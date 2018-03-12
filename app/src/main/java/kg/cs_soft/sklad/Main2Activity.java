package kg.cs_soft.sklad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

public class Main2Activity extends AppCompatActivity {
    private BoomMenuButton bmb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (savedInstanceState != null) {
            finish();
        }
        SharedPreferences prefrences = getSharedPreferences("settings",this.MODE_PRIVATE);
        getSupportActionBar().setTitle(prefrences.getString("login",""));
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        /*bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_2_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_1);
        bmb.addBuilder(BuilderManager.getTextOutsideCircleButtonBuilder());
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            bmb.addBuilder(BuilderManager.getTextOutsideCircleButtonBuilder());
        }*/

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            if(i==0){
                TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                        .normalImageRes(R.drawable.settings)
                        .normalText("Настройки")
                        .listener(new OnBMClickListener(){

                            @Override
                            public void onBoomButtonClick(int index) {
                                Toast.makeText(Main2Activity.this, "Настройки", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .shadowEffect(true)
                        .shadowOffsetX(20)
                        .shadowOffsetY(0)
                        .shadowRadius(Util.dp2px(20))
                        .shadowCornerRadius(Util.dp2px(20))
                        .shadowColor(Color.parseColor("#ee000000"));
                        //.shadowColor(color(R.color.gray));
                bmb.addBuilder(builder);
            }
            if(i==1){
                TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                        .normalImageRes(R.drawable.ic_shopping_basket_black_24dp)
                        .normalText("Список китайских поставшиков")
                        .listener(new OnBMClickListener(){

                            @Override
                            public void onBoomButtonClick(int index) {
                                Toast.makeText(Main2Activity.this, "Список китайских поставшиков", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .shadowEffect(true)
                        .shadowOffsetX(20)
                        .shadowOffsetY(0)
                        .shadowRadius(Util.dp2px(20))
                        .shadowCornerRadius(Util.dp2px(20))
                        .shadowColor(Color.parseColor("#666666"));
                        //.shadowColor(color(R.color.gray));
                bmb.addBuilder(builder);
            }
            if(i==2){
                TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                        .normalImageRes(R.drawable.search)
                        .normalText("Поиск")
                        .listener(new OnBMClickListener(){

                            @Override
                            public void onBoomButtonClick(int index) {
                                Toast.makeText(Main2Activity.this, "Поиск", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .shadowEffect(true)
                        .shadowOffsetX(20)
                        .shadowOffsetY(0)
                        .shadowRadius(Util.dp2px(20))
                        .shadowCornerRadius(Util.dp2px(20))
                        .shadowColor(Color.parseColor("#ee000000"));
                        //.shadowColor(color(R.color.gray));
                bmb.addBuilder(builder);
            }
            if(i==3){
                TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                        .normalImageRes(R.drawable.statistics)
                        .normalText("Показать статистику")
                        .listener(new OnBMClickListener(){

                            @Override
                            public void onBoomButtonClick(int index) {
                                Toast.makeText(Main2Activity.this, "Статистика", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .shadowEffect(true)
                        .shadowOffsetX(20)
                        .shadowOffsetY(0)
                        .shadowRadius(Util.dp2px(20))
                        .shadowCornerRadius(Util.dp2px(20))
                        .shadowColor(Color.parseColor("#ee000000"));
                        //.shadowColor(color(R.color.gray));

                bmb.addBuilder(builder);
            }
        }

    }
    Intent intent = null;
    public void showNakladnye(View view){
        intent = new Intent(Main2Activity.this,NakladnyeActivity.class);
        startActivity(intent);
    }
    public void showOstatki(View view){
        intent = new Intent(Main2Activity.this,OstatkiActivity.class);
        startActivity(intent);
    }
    public void showOtchet(View view){
        intent = new Intent(Main2Activity.this,OtchetyActivity.class);
        startActivity(intent);
    }
    public void showPeremeshenie(View view){
        intent = new Intent(Main2Activity.this,PeremeshenieActivity.class);
        startActivity(intent);
    }
    public void showProdaja(View view){
        intent = new Intent(Main2Activity.this,ProdajaActivity.class);
        startActivity(intent);
    }
    public void showZakup(View view){
        intent = new Intent(Main2Activity.this,ZakupActivity.class);
        startActivity(intent);
    }
    public int color(@ColorRes int resId) {
        return getResources().getColor(resId);
    }
}
