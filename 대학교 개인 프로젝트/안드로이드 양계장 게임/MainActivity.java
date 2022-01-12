package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

        import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
        import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
        import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4, textfarm1, textfarm2;
    ImageButton imageButton1, imageButton2, imageButton3;
    ImageView imageView;
    int MONEY = 30000, DAY = 0, NUM_EGG = 0, NUM_CHICK = 3, SELL_LV = 0, FOOD_LV = 0, EGG_LV = 0;
    int MAX_CHICK = 5;
    View dialog;
    Chicken[] chicken;
    String editText_null_check;

    ImageView[] multi_coop;
    int[] imageId = {
            R.id.imagecoop1,  R.id.imagecoop2, R.id.imagecoop3, R.id.imagecoop4, R.id.imagecoop5, R.id.imagecoop6
    };
    Button[][] coop_btn;
    int[][] btnId = {
            {R.id.coop_button1, R.id.coop_button2, R.id.coop_button3, R.id.coop_button4, R.id.coop_button5},
            {R.id.coop_button6, R.id.coop_button7, R.id.coop_button8, R.id.coop_button9, R.id.coop_button10},
            {R.id.coop_button11, R.id.coop_button12, R.id.coop_button13, R.id.coop_button14, R.id.coop_button15},
            {R.id.coop_button16, R.id.coop_button17, R.id.coop_button18, R.id.coop_button19, R.id.coop_button20},
            {R.id.coop_button21, R.id.coop_button22, R.id.coop_button23, R.id.coop_button24, R.id.coop_button25},
            {R.id.coop_button26, R.id.coop_button27, R.id.coop_button28, R.id.coop_button29, R.id.coop_button30}
    };
    ImageView[][] image_chick;
    int[][] chick_ImageId = {
            {R.id.chick_image1, R.id.chick_image2, R.id.chick_image3, R.id.chick_image4, R.id.chick_image5},
            {R.id.chick_image6, R.id.chick_image7, R.id.chick_image8, R.id.chick_image9, R.id.chick_image10},
            {R.id.chick_image11, R.id.chick_image12, R.id.chick_image13, R.id.chick_image14, R.id.chick_image15},
            {R.id.chick_image16, R.id.chick_image17, R.id.chick_image18, R.id.chick_image19, R.id.chick_image20},
            {R.id.chick_image21, R.id.chick_image22, R.id.chick_image23, R.id.chick_image24, R.id.chick_image25},
            {R.id.chick_image26, R.id.chick_image27, R.id.chick_image28, R.id.chick_image29, R.id.chick_image30}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("모바일컴퓨팅과 응용 Project");
        chicken = new Chicken[30];

        for(int i = 0; i < 30; i++) {
            chicken[i] = new Chicken();
        }
        for(int i = 0; i < 3; i++) {
            chicken[i].buy_chick();
        }


        textView1 = (TextView)findViewById(R.id.textView_day);
        textView2 = (TextView)findViewById(R.id.textView_egg);
        textView3 = (TextView)findViewById(R.id.textView_money);
        textView4 = (TextView)findViewById(R.id.textView_num_chick);
        imageButton1 = (ImageButton)findViewById(R.id.IB_chickenfarm);
        imageButton2 = (ImageButton)findViewById(R.id.IB_store);
        imageButton3 = (ImageButton)findViewById(R.id.IB_house);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chickClick(v);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeClick(v);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeClick(v);
            }
        });
        sync();

    }

    //Textview 에 자원들 동기화
    public void sync(){
        textView1.setText(" "+DAY);
        textView2.setText(" "+NUM_EGG);
        textView3.setText(" "+MONEY);
        textView4.setText(" "+ NUM_CHICK + " / " + MAX_CHICK);
    }

    //다음날 + 정산
    public void day_calculate() {
        int i;
        double sum_egg = 0;
        Random random = new Random();
        int rnd = random.nextInt(100)+1;

        DAY ++;

        for(i = 0, sum_egg = 0; i < MAX_CHICK; i++) {
            if(chicken[i].getLife() == false) continue;
            sum_egg += chicken[i].getOneday_layegg();
            if(EGG_LV == 1) {
                if(rnd > 75)
                    sum_egg += 1;
            }
            else if(EGG_LV >= 2){
                if(rnd > 50)
                    sum_egg += 1;
            }

            if(EGG_LV == 3)
                sum_egg += 1;
            else if(EGG_LV >= 4)
                sum_egg += 2;

            chicken[i].age_up(FOOD_LV);
        }

        if(EGG_LV == 5)
            sum_egg *= 1.5;

        NUM_EGG += (int)sum_egg;
    }

    public void chickClick(View v){
//        ListView lv;
//        ArrayList<PaintTitle> data;
//
//        lv = (ListView)findViewById(R.id.listView);
//
//        data = new ArrayList<PaintTitle>();
//        data.add(new PaintTitle(R.drawable.chick_coop, "팔기"));
//        data.add(new PaintTitle(R.drawable.chick_coop, "팔기"));
//
//        MyBaseAdapter adapter = new MyBaseAdapter(this, data);
//
//        lv.setAdapter(adapter);
        coop_btn = new Button[6][5];
        image_chick = new ImageView[6][5];
        multi_coop = new ImageView[6];

//        for(int i = 0; i < 6; i++){
////            for(int j = 0; j < 5; j++){
////                this.coop_btn[i][j].setOnClickListener(chicksell);
////            }
////        }

        dialog = (View) View.inflate(getApplication(), R.layout.chicken_farm, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

        textfarm1 = (TextView)dialog.findViewById(R.id.text_farm1);
        textfarm2 = (TextView)dialog.findViewById(R.id.text_farm2);
        Button cf_btn = (Button)dialog.findViewById(R.id.chickfarm_close);
        Button sellEgg_btn = (Button)dialog.findViewById(R.id.btn_sellEgg);
        final EditText text_sellEgg_num = (EditText)dialog.findViewById(R.id.editText_egg);

        textfarm1.setText(""+NUM_EGG);
        textfarm2.setText(""+MONEY);

        for(int i = 0; i < 6; i++){
            multi_coop[i] = (ImageView)dialog.findViewById(imageId[i]);
            for(int j = 0; j < 5; j++){
                coop_btn[i][j] = (Button)dialog.findViewById(btnId[i][j]);
                image_chick[i][j] = (ImageView)dialog.findViewById(chick_ImageId[i][j]);
            }
        }

        for(int i = (MAX_CHICK/5); i < 6; i++){
            multi_coop[i].setVisibility(View.INVISIBLE);
            for(int j = 0; j < 5; j++){
                coop_btn[i][j].setVisibility(View.INVISIBLE);
                image_chick[i][j].setVisibility(View.INVISIBLE);
            }
        }

        for(int i = 0; i < (MAX_CHICK/5); i++){
            for(int j = 0; j < 5; j++){
                if(chicken[(i*5)+j].getLife() == false) {
                    coop_btn[i][j].setText("닭 구입");
                    image_chick[i][j].setImageResource(R.drawable.chick_lv0);
                }
                else{
                    if(chicken[(i*5)+j].getAge() <= 50)
                        image_chick[i][j].setImageResource(R.drawable.chick_lv1);
                    else if(chicken[(i*5)+j].getAge() <= 100)
                        image_chick[i][j].setImageResource(R.drawable.chick_lv2);
                    else if(chicken[(i*5)+j].getAge() <= 150)
                        image_chick[i][j].setImageResource(R.drawable.chick_lv3);
                    else if(chicken[(i*5)+j].getAge() > 150)
                        image_chick[i][j].setImageResource(R.drawable.chick_lv4);
                }
            }
        }

        dlg.setView(dialog);
        final DialogInterface dialogInterface = dlg.show();


        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                coop_btn[i][j].setOnClickListener(chicksell);
            }
        }

        cf_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.dismiss();
                sync();
            }
        });
        sellEgg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_null_check = text_sellEgg_num.getText().toString();
                if(editText_null_check.matches(""))
                    Toast.makeText(getApplicationContext(), "달걀의 갯수를 입력해주세요.", Toast.LENGTH_SHORT).show();
                else if(Integer.parseInt(text_sellEgg_num.getText().toString()) <= NUM_EGG){
                    NUM_EGG -= Integer.parseInt(text_sellEgg_num.getText().toString());
                    MONEY += 10 * Integer.parseInt(text_sellEgg_num.getText().toString());
                    Toast.makeText(getApplicationContext(), "판매 완료", Toast.LENGTH_SHORT).show();
                    sync();
                    textfarm1.setText(""+NUM_EGG);
                    textfarm2.setText(""+MONEY);
                }
                else{
                    Toast.makeText(getApplicationContext(), "달걀이 부족합니다.", Toast.LENGTH_SHORT).show();
                    sync();
                    textfarm1.setText(""+NUM_EGG);
                    textfarm2.setText(""+MONEY);
                }
            }
        });
    }

    public void storeClick(View v){
        Intent intent = new Intent(MainActivity.this, Store.class);
        intent.putExtra("food_lv", FOOD_LV);
        intent.putExtra("egg_lv", EGG_LV);
        intent.putExtra("sell_lv", SELL_LV);
        intent.putExtra("max_chick", MAX_CHICK/5);
        intent.putExtra("money", MONEY);
        //startActivityForResult(intent, 0);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            MONEY = data.getIntExtra("receive_money", 0);
            FOOD_LV = data.getIntExtra("receive_food_lv", 0);
            EGG_LV = data.getIntExtra("receive_egg_lv", 0);
            SELL_LV = data.getIntExtra("receive_sell_lv", 0);
            MAX_CHICK = data.getIntExtra("receive_max_chick", 0);
        }
        sync();
    }

    public void homeClick(View v){
        dialog = (View) View.inflate(getApplication(), R.layout.home, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

        Button diabtn1 = (Button)dialog.findViewById(R.id.dialog_button1);
        Button diabtn2 = (Button)dialog.findViewById(R.id.dialog_button2);
//        dlg.setTitle("집");
        dlg.setView(dialog);
        final DialogInterface dialogInterface = dlg.show();

        diabtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day_calculate();
                sync();
                dialogInterface.dismiss();
            }
        });
        diabtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.dismiss();
            }
        });

//        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                day_calculate();
//                sync();
//            }
//        });
//        dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//
//                Toast.makeText(getApplication(), "cancel action", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    Button.OnClickListener chicksell = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 5; j++){
                    if(v.getId() == coop_btn[i][j].getId()){
                        if( chicken[(i*5)+j].getLife() == true) {
                            sell_chick(chicken[(i*5)+j]);
                            coop_btn[i][j].setText("닭 구입");
                            image_chick[i][j].setImageResource(R.drawable.chick_lv0);
                            textfarm2.setText(""+MONEY);
                        }
                        else {
                            buy_chick(chicken[(i*5)+j]);
                            if( chicken[(i*5)+j].getLife() == true) {
                                coop_btn[i][j].setText("판매");
                                image_chick[i][j].setImageResource(R.drawable.chick_lv1);
                                textfarm2.setText(""+MONEY);
                            }
                        }
                    }
                }
            }
        }
    };

    public void sell_chick(Chicken chicken){
        int payout = 0;

        if(chicken.getAge() <= 50)
            payout = 10;
        else if(chicken.getAge() <= 100)
            payout = 30;
        else if(chicken.getAge() <= 150)
            payout = 80;
        else
            payout = 10;

        if (SELL_LV >= 1)
            payout += 20;
        if (SELL_LV >= 2)
            payout += 20;

        if (SELL_LV == 3)
            payout *= 1.5;
        else if (SELL_LV >= 4)
            payout *= 2;

        if (SELL_LV == 5)
            payout *= 2;


        chicken.set_die();
        MONEY += payout;
        NUM_CHICK -= 1;
    }

    public void buy_chick(Chicken chicken){
        if(MONEY >= 100){
            chicken.buy_chick();
            MONEY -= 100;
            NUM_CHICK += 1;
        }
        else
            Toast.makeText(getApplication(), "가진 돈이 모자랍니다.", Toast.LENGTH_SHORT).show();
    }
//    class MyBaseAdapter extends BaseAdapter {
//
//        Context mContext = null;
//        ArrayList<PaintTitle> mData = null;
//
//        public MyBaseAdapter(Context context, ArrayList data) {
//
//            mContext = context;
//            mData = data;
//
//        }
//        @Override
//        public int getCount() {
//            return mData.size();
//            // return 100;  // for test
//
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public PaintTitle getItem(int position) {
//            return mData.get(position%2);
//        }
//
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            View itemLayout;
//            int newposition = position % 2; // make all
//
//            if (convertView == null) {
//                itemLayout = View.inflate(mContext, R.layout.test_item, null);
//            } else {
//                itemLayout = convertView;
//            }
//
//            ImageView imageView = (ImageView) itemLayout.findViewById(R.id.testimageView);
//            imageView.setImageResource(mData.get(newposition).imageId);
//
//            Button test_btn = (Button) itemLayout.findViewById(R.id.test_btn);
//            test_btn.setText(mData.get(newposition).title);
//            return itemLayout;
//
//        }
//    }
//
//    class PaintTitle {
//        int imageId;
//        String title;
//
//        public PaintTitle(int id, String str) {
//            imageId = id;
//            title = str;
//        }
//
//    }
}
