#include <stdio.h>
#include <stdlib.h>

void Perigeo(int mm, int aa){
	int a=0,b=0,c=0;
	_asm{
		mov eax,aa;
		mov ebx,2014;
		mov ecx,2015;
		mov edx,2016;
		
		cmp eax,ebx;
		je cuatro;

		cmp eax,ecx;
		je cinco;

		cmp eax,edx;
		je seis;

    cuatro:
		mov ebx,1;
		mov a,ebx;
		jmp fin;

    cinco:
        mov ecx,1;
		mov b,ecx;
		jmp fin;

    seis:
		mov edx,1;
		mov c,edx;
		
    fin:
	}
	if(a){
		switch (mm){
			case 4: printf("Los dia mas apropiado para salir es el 21-22-23 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 5: printf("Los dia mas apropiado para salir es el 16-17-18 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 6: printf("Los dia mas apropiado para salir es el 13-14-15 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 7: printf("Los dia mas apropiado para salir es el 11-12-13 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 8: printf("Los dia mas apropiado para salir es el 08-09-10 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 9: printf("Los dia mas apropiado para salir es el 06-07-08 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 10: printf("Los dia mas apropiado para salir es el 04-05-06 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 11: printf("Los dia mas apropiado para salir es el 02-03 y 26-27 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 12: printf("Los dia mas apropiado para salir es el 22-23-24 ya que hay un perigeo lunar en esa fecha\n");
				    break;
		}
	}
	else if(b){
		switch (mm){
			case 1: printf("Los dia mas apropiado para salir es el 19-20-21 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 2: printf("Los dia mas apropiado para salir es el 17-18-19 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 3: printf("Los dia mas apropiado para salir es el 17-18-19 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 4: printf("Los dia mas apropiado para salir es el 15-16-17 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 5: printf("Los dia mas apropiado para salir es el 13-14-15 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 6: printf("Los dia mas apropiado para salir es el 08-09-10 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 7: printf("Los dia mas apropiado para salir es el 03-04-05 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 8: printf("Los dia mas apropiado para salir es el 01-02 y 29-30 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 9: printf("Los dia mas apropiado para salir es el 26-27-28 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 10: printf("Los dia mas apropiado para salir es el 24-25-26 ya que hay un perigeo lunar en esa fecha\n");
				    break;
            case 11: printf("Los dia mas apropiado para salir es el 21-22-23 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 12: printf("Los dia mas apropiado para salir es el 19-20-21 ya que hay un perigeo lunar en esa fecha\n");
				    break;
		}
	}
	else if (c){
		switch (mm){
			case 1: printf("Los dia mas apropiado para salir es el 13-14-15 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 2: printf("Los dia mas apropiado para salir es el 09-10-11 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 3: printf("Los dia mas apropiado para salir es el 08-09-10 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 4: printf("Los dia mas apropiado para salir es el 05-06-07 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 5: printf("Los dia mas apropiado para salir es el 04-05-06 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 6: printf("Los dia mas apropiado para salir es el 01-02-03 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 7: printf("Los dia mas apropiado para salir es el 01-02 y 29-30 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 8: printf("Los dia mas apropiado para salir es el 26-27 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 9: printf("Los dia mas apropiado para salir es el 21-22 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 10: printf("Los dia mas apropiado para salir es el 17-18 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 11: printf("Los dia mas apropiado para salir es el 12-13 ya que hay un perigeo lunar en esa fecha\n");
				    break;
			case 12: printf("Los dia mas apropiado para salir es el 07-08 ya que hay un perigeo lunar en esa fecha\n");
				    break;
		}
	}
}			