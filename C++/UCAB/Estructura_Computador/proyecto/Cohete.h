#include <stdio.h>
#include <stdlib.h>


void cohete(long peso){
	int Saturno=118000; int Transbordador=26000; int Atlas=29000; int Delta=25000; 
	if (peso <=Delta){
		printf("*El cohete mas indicado para la mision es el Delta IV\n\n");
		}
	else
		if (peso <= Transbordador){
			printf("*El cohete mas indicado para la mision es el Transbordador Espacial\n\n");
			}
		else
			if (peso <= Atlas){
				printf ("*El cohete mas indicado para la mision es el Atlas V\n\n");
				}
			else
				if (peso <= Saturno){
					printf("*El cohete mas indicado para la mision es el Saturno V\n\n");
					}
				else {
					printf("*Mucho peso,No hay cohete disponible\n\n");
				}
}

int Salida(){
     long personas,herra,equipos,promedio,peso; char co[100]=""; int y=-1;
	 printf("Cuantas personas estaran en el vuelo?:\n");
	  scanf("%d",&personas);
	 printf("Peso promedio de las personas:\n");
	  scanf("%d",&promedio);
	 peso=promedio*personas;
	 printf("Cuantos Kgs de equipos personales se llevara? (son 2500kg aproximadamente):\n");
	  scanf("%d",&equipos);
	 printf("Peso de herramientas para llevar al espacio:\n");
	  scanf("%d",&herra);
	 //peso=peso+equipos+herra;
	 _asm{
		 mov eax,peso;
		 mov ebx,equipos;
		 add eax,ebx;
		 mov ebx,herra;
		 add eax,ebx;		 
		 mov ebx,2000;
		 add eax,ebx;
		 mov peso,eax;
		 }
	 while((y<1) || (y>2)){
	 printf("¿Que tipo de motor desea utilizar?\n\n");
	 printf("1. Motor Tradicional\n");
	 printf("2. Motor de Plasma\n");
	 scanf("%d",&y);
	 if((y<1) || (y>2))
		 printf("Opcion invalida\n\n");
	 }
	 system("cls");
	 printf("Se llevaran %d kg de equipos personales como: utiles, vestimenta, productos de aseo personal y alimento(Tubos y Deshidratados)\n\n",equipos);
	 printf("Se llevaran %d kg de herramientas como: Traje espacial, Correas de seguridad, Taladro inalambrico, Rastreador y analizador de gases, Brazo robotico\n\n",herra);
	 printf("Se llevaran 2000kg de equipos de propulsion: ");
	 
	 if(y==1){
		 printf("Motor tradicional\n\n");
	     printf("El cohete llevara un combustible total de:\n");
	     printf("RP-1 = 952500\n");
	     printf("LOX = 2840850\n");
		 printf("LH2 = 135450\n\n");}
	 else{
		 printf("Motor de plasta VASIMR VF-200\n\n");
		 printf("El cohete llevara un combustible de:\n");
	     printf("Deuterio (isotopo del hidrogeno)\n\n");}
	 
	 printf("*El peso total es de %d Kg\n\n",peso);
	 cohete(peso);	 	
	 printf("Se recuerda que la densidad atmosferica de la tierra es 1,225kb/m2\n");
	 printf("Se recuerda que la densidad de la luna es despreciable\n\n");
	 return y;
}