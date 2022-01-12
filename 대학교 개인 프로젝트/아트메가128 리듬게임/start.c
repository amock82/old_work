#include <mega128.h>
#include <delay.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <stdio.h>

#include "glcd_128.h"
#include "glcd_pic.h"
#include "Game.c"

// note
#define VLOA      9008    // octave 2
#define VLOAX   8580
#define VLOB      8098

#define LOC      7644    // 도 octave 3
#define LOCX   7214
#define LOD      6810    // 레
#define LODX   6427
#define LOE      6066    // 미
#define LOF      5726    // 파
#define LOFX   5404
#define LOG     5101    // 솔
#define LOGX   4815
#define LOA      4544    // 라
#define LOAX   4289
#define LOB     4049    // 시

#define MIC      3821    // 도 octave 4 (기본 음계)
#define MICX    3607
#define MID      3404    // 레
#define MIDX    3213
#define MIE      3033    // 미
#define MIF      2862    // 파
#define MIFX    2702
#define MIG      2550    // 솔
#define MIGX    2407
#define MIA      2272    // 라
#define MIAX    2144
#define MIB      2024    // 시
#define HIC     1910    // 도 octave 5
#define HICX    3607
#define HID     3404    // 레
#define HIDX    3213
#define HIE     3033    // 미
#define HIF     2862    // 파
#define HIFX    2702
#define HIG     2550    // 솔
#define HIGX    2407
#define HIA     2272    // 라
#define HIAX    2144
#define HIB     2024    // 시

// note length
#define NOTE32  1*3
#define NOTE16  2*3
#define NOTE16D 3*3
#define NOTE8   4*3
#define NOTE8D  6*3
#define NOTE4   8*3
#define NOTE4D  12*3
#define NOTE2   16*3
#define NOTE2D  24*3
#define NOTE1   32*3

// rest length
#define REST32  1*3
#define REST16  2*3
#define REST16D 3*3
#define REST8   4*3
#define REST8D  6*3
#define REST4   8*3
#define REST4D  12*3
#define REST2   16*3
#define REST2D  24*3
#define REST1   32*3

int g=0;
char key = 1;
bit key_flag = 0;
int flag=0;

void play_note(unsigned int sound, unsigned int note)
{
  OCR1A = sound;
  TCNT1 = 0x0000;

  TCCR1A = 0b01000000;    // COM1A1,A0(7,6)-01, mode 4 - CTC모드 / OCR1A (WGM11,10 - 00)
  TCCR1B = 0b00001010;    // mode 4(WGM13,12 - 01), 프리스케일 = 8

  delay_ms(note * 7);

  TCCR1B = 0b00001000;    // speaker off
}

void play_rest(unsigned int rest)
{
        delay_ms(rest * 7);
}

char key_in(void)
{
  char buf;

  if((PINF & 0x1f) != 0x1f)
  {
    buf = (PINF & 0x1f);
    key_flag = 1;
  }

  return buf;
}


void main(void)
{

    DDRG = 0x01;
    DDRA = 0xff;
    DDRE = 0xff;
    DDRF = 0x00;
    DDRB = 0xff;



    PORTE = 0x1f;

    //EIMSK = 0b00000001;
    //EICRA = 0b00000010;
    //SREG = 0x80;

    //TCCR1A = 0b01000000;    // COM1A1,A0(7,6)-01, mode 4 - CTC모드 / OCR1A (WGM11,10 - 00)
    //TCCR1B = 0b00001010;    // mode 4(WGM13,12 - 01), 프리스케일 = 8

    MCU_Init();
    GLCD_Init();


    Write_String_8x16(0,0,"20151946큖붴턻"); //20151946 안덕용

    //Rectangle(1, 1, 25, 5);
    //Rectangle(26, 6, 50, 10);
     delay_ms(5000);


while (1)
      {

      // play_note(NOTE4, LOD);
      // delay_ms(50000);

      game();

      }
}

/*
interrupt [EXT_INT0] void external_int0(void)
{
   flag = ++flag%3;
   delay_ms(5);
}
*/
