#include <stdio.h>
#include <string.h>
#include "lib.h"

typedef char enteros[9];
typedef char binario[33];



void operar(char c, char final[], char bin[]){  /*Realiza las operaciones de binarios*/
	int i,x,y,z;
	switch(c){
		case '&': for (i=0;i<32;i++){
					if (final[i]=='1') x=1; else x=0;
					if (bin[i]=='1') y=1; else y=0;
					if ((x==1)&&(y==1)) final[i]='1'; else final[i]='0';
				  }
				  break;
		case '|': for (i=0;i<32;i++){
					if (final[i]=='1') x=1; else x=0;
					if (bin[i]=='1') y=1; else y=0;
					if ((x==1)||(y==1)) final[i]='1'; else final[i]='0';
				  }
				  break;
		case '^': for (i=0;i<32;i++){
					if (final[i]=='1') x=1; else x=0;
					if (bin[i]=='1') y=1; else y=0;
					if (((x==1)&&(y==1))||((x==0)&&(y==0))) final[i]='0'; else final[i]='1';
				  }
				  break;
	}
}

void mostrarbin(char bin[]){  /*Muestra el resultado fianl*/
	int i;
	for (i=0;i<32;i++){
		if ((i % 8)==0) printf("  %c",bin[i]);
		else printf("%c",bin[i]);
	}
}

void main(){
	char c='c';
	int valido,i,j,x=-1;
	char s[9];
	enteros finalhex="";
	binario finalbin="";
	while (c!='q'){                                   /*Ciclo principal*/
		x=-1;
		c='c';
		printf("Introduzca una operacion |, &, ^  o q para salir\n");
		scanf("%s",&c);
		fflush(stdin);
		if(c!='q'){
			if ((c!='|')&&(c!='&')&&(c!='^')) printf("Por favor introduzca |, &, ^ o q");
			else{
				while(x<=0){     /*Lee la cantidad de enteros a operar*/
					x=-1;
					printf("Introduzca la cantidad de enteros\n");
					scanf("%i",&x);
					fflush(stdin);	
					if (x<=0) printf("Numero invalido, debe ser mayor o igual a 1");
				}
				
		        enteros h[x]; /*arreglo de cantidad de enteros*/
		        
				for (i=0;i<x;i++){   /*Llena y valida los numeros*/
					valido=0;
					while (valido==0){
						strcpy(s,"");
						printf("Introduzca el numero %i\n",i+1);
						scanf("%s",&s);	
						fflush(stdin);
						if ((valdig(s))&&(val(s))){   /*valida todo*/
							strcpy(h[i],s);
							valido=1;					
						}
					}
				}
				
				binario b[x];  /*arreglo binario de entero*/
				
				char aux[33];
				
				for (i=0;i<x+1;i++){    /*Cambia a binario*/
					trans(aux,h[i]);
					strcpy(b[i],aux);	
				}

				strcpy(finalbin,b[0]); 

				for (i=1;i<x;i++){    /*Guarda el resultado final en binario*/
					operar(c,finalbin,b[i]);
				}
				printf("\n\n");
				transform(finalbin,finalhex);
				for (i=0;i<x;i++){
					if(i==0) printf("   %s\n",h[i]);
					else printf(" %c %s\n",c,h[i]);
				}
				printf(" ----------\n = %s\n\n",finalhex);
				
				for (i=0;i<x;i++){    /*Muesta la operacion en binario*/
					if(i==0){
						printf("   "); 
						mostrarbin(b[i]);
						printf("\n");
					}
					else{
						printf(" %c ",c); 
						mostrarbin(b[i]);
						printf("\n");
					}
				}
				printf(" ------------------------------------------\n = ");
				mostrarbin(finalbin);
				printf("\n\n");
				
			}
		}
	}
}
