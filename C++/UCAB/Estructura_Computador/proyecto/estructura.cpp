#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>
#include "Posicion.h"
#include "Cohete.h"
#include "Perigeo.h"
#include "Datos.h"

int main (){
	int x=-1,y=-1,a=0,b=0,c=0,d=0;
	int m=0;
	printf("*Bienvenido al centro de calculo de la NASA 2014-2016 *\n\n");
	while (x!=0){
		printf("Indique en planeta en el que se encuentra actualmente:\n\n");
		printf("1.La Luna\n");
		printf("2.Tierra\n");
		printf("3.Marte\n");
		printf("0.Salir\n");
		scanf("%d",&x);
		_asm{
			mov eax,x;
			mov ebx,1;
		    cmp eax,ebx;
			je aa;
			
			mov ebx,2;
			cmp eax,ebx;
			je bb;

			mov ebx,3;
			cmp eax,ebx;
			je cc;

			mov ebx,0;
			cmp eax,ebx;
			je dd;

        aa:
			mov a,1;
			jmp fin;
         
        bb:
			mov b,1;
			jmp fin;

        cc:
			mov c,1;
			jmp fin;

        dd:
			mov d,1;
	
        fin:
		}
		
		if (a){
		            printf("Quiere ir a Marte?\n");
				    printf("1.Si\n");
				    printf("2.No\n");
					scanf("%d",&y);
					switch(y){
						case 1: marte(613573044.733000,103114675.845000);
								break;
						case 2: system("cls");
							    break;
					}
		}
		if (b){
			system("cls");
			printf("Indique a que planeta quiere ir:\n\n");
			printf("1.La Luna\n");
			printf("2.Tierra\n");
			printf("3.Marte\n");
			printf("0.Salir\n");
			scanf("%d",&y);
			switch (y){
				case 1: TierraLuna(m);
					    break;
				case 2: printf("Seccion en construccion\n");
						break;
				case 3: m=1;
					    TierraLuna(m);
					    break;
				case 0: system("cls");
					    break;
			}
		}
		if (c){
			printf("Seccion en construccion\n");
		}
		if (d){ 
			system("cls");
		}
	}
}

