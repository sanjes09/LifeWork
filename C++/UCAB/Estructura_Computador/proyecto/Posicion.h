#include <stdio.h>
#include <stdlib.h>

void marte(double xo, double yo){
    double x,y,a,cos,sen,s,g;
	long t,vo;
	sen = 0.984; // seno del  angulo de lanzamiento
	cos = 0.173; // coseno del  angulo de lanzamiento

	printf("Fase hacia Marte\n\n");
	system("PAUSE");
	t=6030;
	vo=0;
	a=1.63;
	while (t < 319680){
		
		x = ((vo*cos)*t)+ xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+24000;
		//vo=vo+379;
		_asm{
			mov eax,t;
			add eax,24000;
			mov t,eax;
			mov eax,vo;
			add eax,379;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(700);
	}
	printf("El cohete llego a marte\n");
}
void posicion (double xo, double yo, int m){
	double x,y,a,cos,sen,rp,lox,lh2; long RP1,LOX,LH2;
	long t,vo;
	sen = 0.984; // seno del  angulo de lanzamiento
	cos = 0.173; // coseno del  angulo de lanzamiento
	vo=0; t=0; a=39; RP1=952500; LOX=2840850; LH2=135450;
	printf("Inicio de la Primera fase:\n\n");
	system("PAUSE");
	while (t < 150){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+25;
		//vo=vo+398;
		_asm{
			mov eax,t;
			add eax,25;
			mov t,eax;
			mov eax,vo;
			add eax,398;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(500);

	}
	printf("Se usaron:\n");
    printf("RP-1= 635.000 kg(kerosen especial)\n");
	RP1=RP1-635000;
	printf("LOX= 1.440.000 kg(Oxigeno liquido)\n");
	LOX=LOX-1440000;
	printf("En la primera fase...\n");
	system("PAUSE");
	system("cls");
	
	printf("Inicio de la Segunda fase:\n\n");
	system("PAUSE");
	sen = 0.642;
	cos = 0.766;
	a=5.9;
	xo=x;
	yo=y;
	vo=2388.88;
	while (t < 510){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+90;
		//vo=vo+1433.32;
		_asm{
			mov eax,t;
			add eax,90;
			mov t,eax;
			mov eax,vo;
			add eax,1433;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(500);
	}
	printf("Se usaron:\n");
    printf("LH2= 72.300 kg(Hidrogeno liquido)\n");
	LH2=LH2-72300;
	printf("LOX= 365.300 kg(Oxigeno liquido)\n");
	LOX=LOX-365300;
	printf("En la Segunda fase...\n");
	system("PAUSE");
	system("cls");
	
	printf("Inicio de la Tercera fase:\n\n");
	system("PAUSE");
	sen = 0.342;
	cos = 0.939;
	a=1.06;
	xo=x;
	yo=y;
	vo=8122.19;
	while (t < 630){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+60;
		//vo=vo+955.55;
		_asm{
			mov eax,t;
			add eax,60;
			mov t,eax;
			mov eax,vo;
			add eax,956;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(500);
	}
	printf("Se usaron:\n");
    printf("LH2= 18.000 kg(Hidrogeno liquido)\n");
	LH2=LH2-18000;
	printf("LOX= 88.600 kg(Oxigeno liquido)\n");
	LOX=LOX-88600;
	printf("En la Tercera fase...\n");
	printf("El cohete llego a la orbita de la tierra\n\n");
	printf("El cohete tiene:\n\n");
	printf("RP1:%dKg\n",RP1);
	printf("LOX:%dKg\n",LOX);
	printf("LH2:%dKg\n",LH2);
	system("PAUSE");
	system("cls");
	
	printf("Fase orbital\n\n");
	system("PAUSE");
	xo=x;
	yo=y;
	t=0;
	vo=10033.29;
	a=0;
	while (t < 5400){
		x = ((a*t*t)/2)+vo*t+xo;
		y = ((a*t*t)/2)+vo*t;
		//t=t+1800;
		//vo= vo+10095.57;
		_asm{
			mov eax,t;
			add eax,1800;
			mov t,eax;
			mov eax,vo;
			add eax,10096;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(700);
	}	
	printf("El cohete llega a la velocidad de escape, tardando aproximadamente 90 minutos para salir de la orbita terrestre.\n");
	system("PAUSE");
	system("cls");
	
	printf("Fase hacia la luna\n\n");
	system("PAUSE");
	xo=x;
	yo=y;
	t=6030;
	vo=40320;
	a=1.63;
	while (t < 144000){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+24000;
		//vo=vo-18;
		_asm{
			mov eax,t;
			add eax,24000;
			mov t,eax;
			mov eax,vo;
			sub eax,18;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(700);
	}
	
 printf("\n");
 printf("El cohete llego a la luna\n");
 system("PAUSE");
 system("cls");
  if (m==1)
	 printf("Con motor tradicional no es posible llegar a marte\n");
}

void posicion1 (double xo, double yo,int m){
	double x,y,a,cos,sen,rp,lox,lh2; 
	long t,vo;
	sen = 0.984; // seno del  angulo de lanzamiento
	cos = 0.173; // coseno del  angulo de lanzamiento
	vo=0; t=0; a=200; 
	printf("Inicio de la Primera fase:\n\n");
	system("PAUSE");
	while (t < 15){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+2.5;
		//vo=vo+2169.32;
		_asm{
			mov eax,t;
			add eax,3;
			mov t,eax;
			mov eax,vo;
			add eax,2169;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(500);
	}
	system("PAUSE");
	system("cls");
	
	printf("Inicio de la Segunda fase:\n\n");
	system("PAUSE");
	sen = 0.642;
	cos = 0.766;
	a=5.9;
	xo=x;
	yo=y;
	vo=13115.89;
	while (t < 51){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+9;
		//vo=vo+1115.27;
		_asm{
			mov eax,t;
			add eax,9;
			mov t,eax;
			mov eax,vo;
			add eax,1115;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(500);
	}
	system("PAUSE");
	system("cls");
	
	printf("Inicio de la Tercera fase:\n\n");
	system("PAUSE");
	sen = 0.342;
	cos = 0.939;
	a=1.06;
	xo=x;
	yo=y;
	vo=18849.20;
	while (t < 63){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+6;
		//vo=vo+300;
		_asm{
			mov eax,t;
			add eax,6;
			mov t,eax;
			mov eax,vo;
			add eax,300;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(500);
	}
	printf("En la Tercera fase...\n");
	printf("El cohete llego a la orbita de la tierra\n\n");
	system("PAUSE");
	system("cls");
	
	printf("Fase orbital\n\n");
	system("PAUSE");
	xo=x;
	yo=y;
	t=0;
	vo=22033.29;
	a=0;
	while (t < 540){
		x = ((a*t*t)/2)+vo*t+xo;
		y = ((a*t*t)/2)+vo*t;
		//t=t+180;
		//vo= vo+10095.57;
		_asm{
			mov eax,t;
			add eax,180;
			mov t,eax;
			mov eax,vo;
			add eax,10096;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(700);
	}	
	printf("El cohete llega a la velocidad de escape, tardando aproximadamente 90 minutos para salir de la orbita terrestre.\n");
	system("PAUSE");
	system("cls");
	
	printf("Fase hacia la luna\n\n");
	system("PAUSE");
	xo=x;
	yo=y;
	t=603;
	vo=50520;
	a=1.63;
	while (t < 14400){
		x = ((vo*cos)*t) + xo;
		y = (-0.5*a*t*t) + ((vo*sen)*t) + yo;
		//t=t+2400;
		//vo=vo-18;
		_asm{
			mov eax,t;
			add eax,2400;
			mov t,eax;
			mov eax,vo;
			sub eax,18;
			mov vo,eax;
		}
		system("cls");
		printf ("El cohete esta en la latitud= %f \n", x);
		printf ("El cohete esta en la longitud= %f \n", y);
		Sleep(700);
	}
	
 printf("\n");
 printf("El cohete llego a la luna\n");
 if (m==1)
	 marte(x,y);
 system("cls");
}

void Datos (int y,int m){
	int x,a=0,b=0; 
	printf("De que estacion espacial desea salir?:\n\n");
	printf("1. John F. Kennedy Space Center, Florida\n");
	printf("2. George C. Marshall Space Flight Center, Alabama\n");
	printf("3. Lyndon B. Johnson Space Center, Houston\n");
	scanf("%d",&x);
	system("cls");
	//if (y==1)
	_asm{
		mov eax,y;
		cmp eax,1;
		je normal;

		cmp eax,2;
		je plasma;

    normal:
		mov ebx,1;
		mov a,ebx;
		jmp fin;
    
    plasma:
		mov ebx,2;
		mov b,ebx;

   fin:
	}
	if(a)
	    switch (x){
		case 1: posicion((-8036268,7),(7813802,57),m);
			    break;
		case 2: posicion((-7107566.91),(9399398.8),m);
			    break;
		case 3: posicion((10503168,88),(14238558,45),m);
			    break;
	}
	if(b)
		switch (x){
		case 1: posicion1((-8036268,7),(7813802,57),m);
			    break;
		case 2: posicion1((-7107566.91),(9399398.8),m);
			    break;
		case 3: posicion1((10503168,88),(14238558,45),m);
			    break;
	}

	system("PAUSE");
}