
int i,j,k,l, T;
int x[5]={1,20,39,58,77};
int y[20]={3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60};
char key2 = 1;
bit key_flag2 = 0;
int score = 0;

struct note{
    int a;  //°¡·Î
    int b;  //¼¼·Î
    int M;  //À½»ö
    int s;  //Á¡¼öÆÇÁ¤
};

char key_in2(void)
{
  char buf;

  if((PINF & 0x1f) != 0x1f)
  {
    buf = (PINF & 0x1f);
    key_flag2 = 1;
  }

  return buf;
}


void game(void)
{
    struct note test[50] =
    {{0,0,5,0},{1,-6,2,0},{3,-12,0,0},{4,-21,0,0},{0,-27,0,0},{0,-33,0,0},
     {1,-45,0,0},{2,-51,0,0},{3,-57,0,0},{2,-63,0,0},{2,-69,0,0},{2,-75,0,0}
    };

    for(i=12; i<100; i++)
    {
        test[i].b =19;
    }
    score = 0;
    T=0;
    j=0;
    while(T < 100)
    {
        i = 0;
        lcd_clear();

        for(k=0 ; k<8 ; k++)       // GLCD¿¡¼­
            for(l=0; l<128; l++)   // ÇÑ¹ø Ãâ·Â‰ç´ø °÷
                screen[k][l] = '\0';   // °è¼Ó Ãâ·ÂµÇ´Â °ÅÁö°°Àº°Å ÇÈ½º

        Line(1,59,95,59);
        Line(1,61,95,61);
        Line(95,1,95,60);

        Write_String_8x16(12,2,"¸ñ®");
        Write_Dec_6x8(102, 22, score);

        while(i<50)
        {
            if((test[i].b)+j > 0 && (test[i].b)+j <20)
                Rectangle(x[test[i].a], y[test[i].b+j], x[test[i].a]+17, y[test[i].b+j]+6);
            if(test[i].b+j >17 && test[i].b+j <20)
                test[i].s = 2;
            else if(test[i].b+j >15 && test[i].b+j <21)
                test[i].s = 1;
            else
                test[i].s = 0;
            i++;
            delay_ms(2);
        }

        key2 = key_in2();
        i = 0;
        while(i<50 && key_flag2 ==1)
        {
            if(test[i].s != 0)
            {
                switch(key2)
                {
                case 0x1e: //sw1
                    if(test[i].a == 0)
                    {
                        score += test[i].s;
                        test[i].s = 0;
                        test[i].b = 20;
                        key_flag2 = 0;
                    }
                break;

                case 0x1d: //sw2
                    if(test[i].a == 1)
                    {
                        score += test[i].s;
                        test[i].s = 0;
                        test[i].b = 20;
                        key_flag2 = 0;
                    }
                break;

                case 0x1b: //sw3
                    if(test[i].a == 2)
                    {
                        score += test[i].s;
                        test[i].s = 0;
                        test[i].b = 20;
                        key_flag2 = 0;
                    }
                break;

                case 0x17: //sw4;
                    if(test[i].a == 3)
                    {
                        score += test[i].s;
                        test[i].s = 0;
                        test[i].b = 20;
                        key_flag2 = 0;
                    }
                break;

                case 0x0f: //sw5
                    if(test[i].a == 4)
                    {
                        score += test[i].s;
                        test[i].s = 0;
                        test[i].b = 20;
                        key_flag2 = 0;
                    }
                break;
                }
            }
            i++;
        }

        j++;
        T++;

    }
    lcd_clear();
    for(k=0 ; k<8 ; k++)       // GLCD¿¡¼­
            for(l=0; l<128; l++)   // ÇÑ¹ø Ãâ·Â‰ç´ø °÷
                screen[k][l] = '\0';   // °è¼Ó Ãâ·ÂµÇ´Â Çö»ó ÇÈ½º

    Write_String_8x16(0,0,"”w?· ¸ñ®:");
    Write_Dec_6x8(90, 1, score);

    Write_String_8x16(0,3," press any key");
    while((PINF & 0x1f) == 0x1f)
    {

    }
}