package com.example.myapplication;

import java.util.Random;

public class Chicken {
    int age;
    int gender;
    int satiety;
    int oneday_layegg;
    boolean life;

    public Chicken(){
        age = 0;
//        gender = 0;
        oneday_layegg = 0;
        life = false;
    }

    public void buy_chick(){
//        double random = Math.random();
//        if (random >= 0.5)
//            gender = 1;
//        else
//            gender = 2;
        life = true;
        age = 1;
        oneday_layegg = 1;
    }


//    public void setGender(int gender){
//        this.gender = gender;
//    }
    public void setAge(int age){
        this.age = age;
    }
    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }
    public void setLife(boolean life) {
        this.life = life;
    }


//    public int getGender(){
//        return gender;
//    }
    public int getAge(){
        return age;
    }
    public int getOneday_layegg() { return oneday_layegg;}
    public boolean getLife() { return life;}

    public void age_up(int food_lv){
        age += 10;
        Random random = new Random();
        int rnd = random.nextInt(100)+1;

        if(age <= 50){
            oneday_layegg = 1;
            if(food_lv >= 1) age += 5;
            if(food_lv >= 2) age += 5;
        }
        else if(age <= 100) oneday_layegg = 2;
        else if(age <= 150){
            if (rnd > 50)
                oneday_layegg = 2;
            else
                oneday_layegg = 3;

            if (food_lv == 3) {
                if (rnd > 75) {
                    if(150 <= age && age <= 160)
                        age = 110;
                    else
                        age -= 10;
                }
            }
            else if (food_lv == 4){
                if (rnd > 50) {
                    if (150 <= age && age <= 160)
                        age = 110;
                    else
                        age -= 10;
                }
            }
            else if (food_lv == 5){
                age = 125;
            }
        }
        else if(age <= 200) oneday_layegg = 1;
        else oneday_layegg = 0;
    }
    public void set_die(){
        age = 0;
        oneday_layegg = 0;
        life = false;
    }
}
