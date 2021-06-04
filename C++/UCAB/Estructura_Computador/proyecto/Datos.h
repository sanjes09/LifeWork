#include <stdio.h>
#include <stdlib.h>


void TierraLuna(int m){
	int mm=-1,aa=-1,y; 
	printf("Introduzca la fecha que le gustaria realizar el lanzamiento:\n");
	while ((mm<1)||(mm>12)){
	printf("Mes:");
	scanf("%d",&mm);
	if ((mm<1)||(mm>12))
	  printf("Mes incorrecto, tienes que ser de 1 a 12\n");}
	while ((aa<2014)||(aa>2016)){
	printf("Año:");
	scanf("%d",&aa);
	if ((aa<2014)||(aa>2016))
		printf("Año incorrecto, tiene que ser de 2014 a 2016\n");}
	Perigeo(mm,aa);
    y=Salida();
	Datos(y,m);
};