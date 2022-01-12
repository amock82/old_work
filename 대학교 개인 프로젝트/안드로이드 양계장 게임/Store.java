package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Store extends AppCompatActivity {

    int MONEY, EGG_LV, FOOD_LV, SELL_LV, MAX_CHICK_LV;
    Button btn_back;
    ImageView item_image1, item_image2, item_image3, item_image4;
    TextView item_text1, item_text2, item_text3, item_text4, say_text, money_text;
    Button[] item_btn = new Button[4];
    ImageButton IB_storeManager;
    int[] item_btnId = {
                R.id.button_item1, R.id.button_item2, R.id.button_item3, R.id.button_item4
           };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        setTitle("상점");

        view_matching();
        get_intent();
        sync();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backClick(v);
            }
        });
        for(int i = 0; i < 4; i++){
            item_btn[i].setOnClickListener(item_buy);
        }

    }

    public void view_matching(){
        item_image1 = (ImageView)findViewById(R.id.image_ItemImage1);
        item_image2 = (ImageView)findViewById(R.id.image_ItemImage2);
        item_image3 = (ImageView)findViewById(R.id.image_ItemImage3);
        item_image4 = (ImageView)findViewById(R.id.image_ItemImage4);
        item_text1 = (TextView)findViewById(R.id.text_item1);
        item_text2 = (TextView)findViewById(R.id.text_item2);
        item_text3 = (TextView)findViewById(R.id.text_item3);
        item_text4 = (TextView)findViewById(R.id.text_item4);
        say_text = (TextView)findViewById(R.id.text_say);
        money_text = (TextView)findViewById(R.id.text_store_money);
        item_btn[0] = (Button)findViewById(item_btnId[0]);
        item_btn[1] = (Button)findViewById(item_btnId[1]);
        item_btn[2] = (Button)findViewById(item_btnId[2]);
        item_btn[3] = (Button)findViewById(item_btnId[3]);
        IB_storeManager = (ImageButton)findViewById(R.id.ib_StoreManager);
        btn_back = (Button)findViewById(R.id.store_close);
    }

    public void get_intent(){
        Intent inIntent = getIntent();
        MONEY = inIntent.getIntExtra("money", 0);
        EGG_LV = inIntent.getIntExtra("egg_lv",0);
        FOOD_LV = inIntent.getIntExtra("food_lv", 0);
        SELL_LV = inIntent.getIntExtra("sell_lv", 0);
        MAX_CHICK_LV = inIntent.getIntExtra("max_chick", 0) - 1;
    }

    public void backClick(View v){
        Intent outIntent = new Intent(this, MainActivity.class);
        outIntent.putExtra("receive_money", MONEY);
        outIntent.putExtra("receive_food_lv", FOOD_LV);
        outIntent.putExtra("receive_egg_lv", EGG_LV);
        outIntent.putExtra("receive_sell_lv", SELL_LV);
        outIntent.putExtra("receive_max_chick", (MAX_CHICK_LV + 1) * 5);

        setResult(RESULT_OK, outIntent);

        finish();
    }

    public void sync(){
        money_text.setText(""+MONEY);
        set_itemImageTextBtn();
    }

    public void set_itemImageTextBtn(){
        String[] food_text = {
                "여러가지 영양분을 골고루 섭취할 수 있도록 만들어진 사료입니다. 어린 닭들의 성장속도가 50% 빨라집니다",
                "기존의 사료의 양을 대폭 늘렸습니다. 어린 닭들의 성장속도가 100% 빨라집니다.",
                "유전자 변형 식품으로 만들어진 사료입니다. 전성기 닭들이 25% 확률로 나이를 먹지 않습니다.",
                "유전자 변형 식품으로 만든 사료를 대량으로 사들입니다. 전성기 닭들이 50% 확률로 나이를 먹지 않습니다.",
                "출처를 알 수 없는 사료입니다. 소문으로는, 이 사료를 먹은 닭들은 절대 늙지 않는다고 합니다."
        };
        String[] egg_text = {
                "닭 전용 영양제 입니다. 아직은 시제품이라고 합니다. 닭들이 25% 확률로 알을 1개 더 낳습니다.",
                "기존의 영양제를 대량으로 사들입니다. 닭들이 50% 확률로 알을 1개 더 낳습니다.",
                "성분을 알 수 없는 영양제입니다. 하지만 효과는 확실하다고 합니다. 닭들이 1개의 알을 추가적으로 낳습니다.",
                "의심스러운 영양제를 대량으로 사들입니다. 닭들이 2개의 알을 추가적으로 낳습니다.",
                "거리의 뒷골목에서 암암리에 거래되는 약입니다. 닭들이 낳는 알의 양이 50% 증가합니다."
        };
        String[] sell_text = {
                "낡은 계약서입니다. 닭들의 몸값을 두둑히 받을 수 있을 것입니다. 닭의 판매단가가 20원 오릅니다.",
                "조촐한 계약서입니다. 닭들의 몸값을 한 층더 올립니다. 닭의 판매단가가 20원 더 오릅니다.",
                "그럴듯한 계약서입니다. 닭들을 한층 더 비싸게 팔 수 있습니다. 닭의 판매단가가 150%가 됩니다.",
                "제법 정성이 담긴 계약서입니다. 닭들이 더욱 더 비싸집니다. 닭의 판매단가가 200%가 됩니다.",
                "굉장히 호화로운 계약서입니다. 꼼꼼히 살펴봐야 할것입니다. 닭의 최종판매가가 2배가됩니다."
        };
        String[] MaxChick_text = {
                "양계장의 빈공간을 닭장으로 채웁니다. 닭장을 5개 추가합니다.",
                "효율적인 배치를 하여 더 많은 공간을 창출합니다. 닭장을 5개 추가합니다",
                "업체를 불러 양계장을 증축합니다. 닭장을 5개 추가합니다.",
                "업체에 양계장의 추가증축을 요청합니다. 닭장을 5개 추가합니다.",
                "3차원에 그치지 않고 다른차원을 활용합니다. 닭장을 5개 추가합니다."
        };
        int[] food_ImageID = {
            R.drawable.feed_lv1, R.drawable.feed_lv2, R.drawable.feed_lv3, R.drawable.feed_lv4, R.drawable.feed_lv5
        };
        int[] egg_ImageID = {
                R.drawable.pill_lv1, R.drawable.pill_lv2, R.drawable.pill_lv3, R.drawable.pill_lv4, R.drawable.pill_lv5
        };
        int[] sell_ImageID = {
                R.drawable.contract_lv1, R.drawable.contract_lv2, R.drawable.contract_lv3, R.drawable.contract_lv4, R.drawable.contract_lv5
        };
        int[] MaxChick_ImageID = {
                R.drawable.extension_lv1, R.drawable.extension_lv2, R.drawable.extension_lv3, R.drawable.extension_lv4, R.drawable.extension_lv5
        };
        String[] food_btn_text = {
                "100", "200", "500", "1000", "2000", "품절"
        };
        String[] egg_btn_text = {
                "200", "500", "1000", "2000", "5000", "품절"
        };
        String[] sell_btn_text = {
                "300", "700", "1500", "3000", "7000", "품절"
        };
        String[] MaxChick_btn_text = {
                "1000", "2500", "5000", "10000", "25000", "품절"
        };

        int i;

        for(i = 0; i < 5; i++){
            if(FOOD_LV == i) {
                item_text1.setText(food_text[i]);
                item_image1.setImageResource(food_ImageID[i]);
                item_btn[0].setText(food_btn_text[i]);
                break;
            }
        }
        if(FOOD_LV == 5){
            item_btn[0].setEnabled(false);
            item_btn[0].setText(food_btn_text[5]);
        }
        for(i = 0; i < 5; i++){
            if(EGG_LV == i) {
                item_text2.setText(egg_text[i]);
                item_image2.setImageResource(egg_ImageID[i]);
                item_btn[1].setText(egg_btn_text[i]);
                break;
            }
        }
        if(EGG_LV == 5){
            item_btn[1].setEnabled(false);
            item_btn[1].setText(food_btn_text[5]);
        }
        for(i = 0; i < 5; i++){
            if(SELL_LV == i) {
                item_text3.setText(sell_text[i]);
                item_image3.setImageResource(sell_ImageID[i]);
                item_btn[2].setText(sell_btn_text[i]);
                break;
            }
        }
        if(SELL_LV == 5){
            item_btn[2].setEnabled(false);
            item_btn[2].setText(food_btn_text[5]);
        }
        for(i = 0; i < 5; i++){
            if(MAX_CHICK_LV == i) {
                item_text4.setText(MaxChick_text[i]);
                item_image4.setImageResource(MaxChick_ImageID[i]);
                item_btn[3].setText(MaxChick_btn_text[i]);
                break;
            }
        }
        if(MAX_CHICK_LV == 5){
            item_btn[3].setEnabled(false);
            item_btn[3].setText(food_btn_text[5]);
        }
    }

    Button.OnClickListener item_buy = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Random random = new Random();
            int rnd = random.nextInt(100)+1;

            for(int i = 0; i < 4; i++){
                if(v.getId() == item_btn[i].getId()){
                    if(MONEY >= Integer.parseInt(item_btn[i].getText().toString())){
                        switch (i){
                            case 0 :
                                FOOD_LV += 1;
                                break;
                            case 1 :
                                EGG_LV += 1;
                                break;
                            case 2 :
                                SELL_LV += 1;
                                break;
                            case 3 :
                                MAX_CHICK_LV += 1;
                                break;
                        }
                        MONEY -= Integer.parseInt(item_btn[i].getText().toString());
                        Toast.makeText(getApplicationContext(), "구입 완료!", Toast.LENGTH_SHORT).show();
                        if (rnd > 66)
                            say_text.setText("물건의 품질은 \n보증하지.");
                        else if (rnd > 33)
                            say_text.setText("앞으로도 많은 \n이용 부탁하네.");
                        else
                            say_text.setText("우리 가게의 물건은 확실하다구?");
                        sync();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "소지금이 부족합니다.", Toast.LENGTH_SHORT).show();
                        if (rnd > 66)
                            say_text.setText("당신, 그것을 \n사기에는 돈이 \n부족해 보이는군.");
                        else if (rnd > 33)
                            say_text.setText("돈이 없으면 물건은 넘겨줄 수 없다네.");
                        else
                            say_text.setText("당신, 충분한 금액을 소지하고 있는것이 확실한가?");
                    }
                }
            }
        }
    };
//    상점 품목별로 이동할 엑티비티
//    public void Click(View v){
//        Intent intent = new Intent(MainActivity.this, Store.class);
//        //intent.putExtra("rate1", rate1);
//        //intent.putExtra("rate2", rate2);
//        //intent.putExtra("rate3", rate3);
//        startActivityForResult(intent, 1);
//    }
}
