#include <stdio.h>
#include <string.h>
#include "lib.h"

int valdig(char s[]){ /*Completa con 0 el numero*/
	int i,cont;
	char cero[9]="";
	cont=strlen(s);
	if (cont>8){
		printf("Por favor introduzca un entero hexadecimal de 8 dígitos\n");
		return 0;
	}
	else{
		for (i=0;i<(8-cont);i++){
			strcat(cero,"0");
		}
		strcat(cero,s);
		strcpy(s,cero);
		return 1;
	}
}

int val(char s[]){ /*Valida si son digitos validos*/
	int i,j,x=0,cont;
	char c[23]="0123456789ABCDEFabcdef";
	for (i=0;i<8;i++){
		x=0;
		for(j=0;j<23;j++){
			if (s[i]=='a') s[i]='A';
			if (s[i]=='b') s[i]='B';
			if (s[i]=='c') s[i]='C';
			if (s[i]=='d') s[i]='D';
			if (s[i]=='e') s[i]='E';
			if (s[i]=='f') s[i]='F';	

			if (s[i]==c[j]){
				x=1;
				break;
			}
		}
		if (x!=1){
			printf("Posee un digito no valido\n");
			return 0;
		}
	}
	return 1;
}
