#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef char registros[16];
registros R[8];	//REGISTROS
registros M[256]; //MEMORIA


void mostrarP(){		//MUESTRA ESTADO DEL PROCESADOR
	printf("Estado de los registros:\n");
	printf("S0: %s\n",R[0]);
	printf("S1: %s\n",R[1]);
	printf("S2: %s\n",R[2]);
	printf("S3: %s\n",R[3]);
	printf("S4: %s\n",R[4]);
	printf("S5: %s\n",R[5]);
	printf("S6: %s\n",R[6]);
	printf("S7: %s\n",R[7]);
}

int valHex(char h[]){ 	//VALIDA HEXADECIMAL DE 1 BYTE
	if(strlen(h)>4) return 0;
	if(h[0]!='0'&&(h[1]!='x'||h[1]!='X')) return 0;
	if(h[2]!='0'&&h[2]!='1'&&h[2]!='2'&&h[2]!='3'&&h[2]!='4'&&h[2]!='5'&&h[2]!='6'&&h[2]!='7'&&h[2]!='8'&&h[2]!='9'&&h[2]!='a'&&h[2]!='b'&&h[2]!='c'&&h[2]!='d'&&h[2]!='e'&&h[2]!='f'&&h[2]!='A'&&h[2]!='B'&&h[2]!='C'&&h[2]!='D'&&h[2]!='E'&&h[2]!='F') return 0;
	if(h[3]!='0'&&h[3]!='1'&&h[3]!='2'&&h[3]!='3'&&h[3]!='4'&&h[3]!='5'&&h[3]!='6'&&h[3]!='7'&&h[3]!='8'&&h[3]!='9'&&h[3]!='a'&&h[3]!='b'&&h[3]!='c'&&h[3]!='d'&&h[3]!='e'&&h[3]!='f'&&h[3]!='A'&&h[3]!='B'&&h[3]!='C'&&h[3]!='D'&&h[3]!='E'&&h[3]!='F') return 0;
	return 1;
}

void mostrarM(){		//MUESTRA ESTADO DE MEMORIA     FALTAAAA
	char a[10],b[10],aux[3];
	int i,x,j,k=0;
	do{
		printf("Ingrese el rango de direccion de memoria a mostrar: \n");
		scanf("%s",&a);
		scanf("%s",&b);
		if(!valHex(a)) printf("Primera direccion incorrecta, debe ser hexadecimal de 1 byte\n");
		if(!valHex(b)) printf("Segunda direccion incorrecta, debe ser hexadecimal de 1 byte\n");
		aux[0]=a[2];
		aux[1]=a[3];
		sscanf(aux,"%x",&i);
		aux[0]=b[2];
		aux[1]=b[3];
		sscanf(aux,"%x",&x);
		printf("%i %i\n",i,x);
		if(i>x) printf("La primera direccion debe ser mas baja que la segunda\n");
		else k=1;
	}while(!valHex(a)||!valHex(b)||!k);
	printf("\n");
	printf("Direccion\tContenido\n");
	for(j=i;j<=x;j++){
		printf("0x%x:\t\t%s\n",j,M[j]);
	}
}

void asignar(char aux[],char reg[]){//CAMBIA EL ID EN BINARIO POR EL NOMBRE DE LOS REGISTROS
	if(!strcmp(aux,"000")) strcpy(reg,"S0");
	if(!strcmp(aux,"001")) strcpy(reg,"S1");
	if(!strcmp(aux,"010")) strcpy(reg,"S2");
	if(!strcmp(aux,"011")) strcpy(reg,"S3");
	if(!strcmp(aux,"100")) strcpy(reg,"S4");
	if(!strcmp(aux,"101")) strcpy(reg,"S5");
	if(!strcmp(aux,"110")) strcpy(reg,"S6");
	if(!strcmp(aux,"111")) strcpy(reg,"S7");
}

void transform(char aux[], char m[]){  //TRANSFORMA A HEXADECIMAL LA DIRECCION DE MEMORIA
	int i,j,x;
	char f[5];
	strcpy(m,"0x");
	for (i=0;i<8;i+=4){
		strcpy(f,"");
		x=i;
		for (j=0;j<4;j++){
			if(aux[x]=='0') strcat(f,"0");
			else strcat(f,"1");
			x++;
		}
			if (strcmp(f,"0000")==0) strcat(m,"0");
			if (strcmp(f,"0001")==0) strcat(m,"1");
			if (strcmp(f,"0010")==0) strcat(m,"2");
			if (strcmp(f,"0011")==0) strcat(m,"3");
			if (strcmp(f,"0100")==0) strcat(m,"4");
			if (strcmp(f,"0101")==0) strcat(m,"5");
			if (strcmp(f,"0110")==0) strcat(m,"6");
			if (strcmp(f,"0111")==0) strcat(m,"7");
			if (strcmp(f,"1000")==0) strcat(m,"8");
			if (strcmp(f,"1001")==0) strcat(m,"9");
			if (strcmp(f,"1010")==0) strcat(m,"A");
			if (strcmp(f,"1011")==0) strcat(m,"B");
			if (strcmp(f,"1100")==0) strcat(m,"C");
			if (strcmp(f,"1101")==0) strcat(m,"D");
			if (strcmp(f,"1110")==0) strcat(m,"E");
			if (strcmp(f,"1111")==0) strcat(m,"F");
	}
}

void trans(char aux[], char t[]){ 	//TRANSFORMA DE HEXADECIAML A BINARIO
	int i;
	strcpy(aux,"");
	for(i=0;i<8;i++){
		switch (t[i]){
		   case '0':strcat (aux ,"0000");
				    break;
		   case '1':strcat (aux ,"0001");
		   		    break;
		   case '2':strcat (aux ,"0010");
		   			break;
		   case '3':strcat (aux ,"0011");
		   	  		break;
		   case '4':strcat (aux ,"0100");
		   			break;
		   case '5':strcat (aux ,"0101");
		    		break;
		   case '6':strcat (aux ,"0110");
		    		break;
		   case '7':strcat (aux ,"0111");
		    		break;
		   case '8':strcat (aux ,"1000");
		    		break;
		   case '9':strcat (aux ,"1001");
		    		break;
		   case 'A':strcat (aux ,"1010");
		    		break;
		   case 'B':strcat (aux ,"1011");
		    		break;
		   case 'C':strcat (aux ,"1100");
		    		break;
		   case 'D':strcat (aux ,"1101");
		    		break;
		   case 'E':strcat (aux ,"1110");
		    		break;
		   case 'F':strcat (aux ,"1111");
		    		break;
		   case 'a':strcat (aux ,"1010");
		    		break;
		   case 'b':strcat (aux ,"1011");
		    		break;
		   case 'c':strcat (aux ,"1100");
		    		break;
		   case 'd':strcat (aux ,"1101");
		    		break;
		   case 'e':strcat (aux ,"1110");
		    		break;
		   case 'f':strcat (aux ,"1111");
		    		break;
		  }
	}
}

void desensamblarP(){	//DESENSAMBLA PROGRAMA
	char a[9999],op[5],inst[10],reg1[10],reg2[10],m[10],maux[10];
	int x,i=0,j=0,h=0;
	printf("Inserte programa a desensamblar: ");
	scanf("%s",&a);	//LEE EL CODIGO EN BINARIO
	printf("\n");
	x=strlen(a);

	while(i<x-1){
		strcpy(op,"");	//LIMPIA LOS ARREGLOS A USAR
		strcpy(inst,"");
		strcpy(reg1,"");
		strcpy(reg2,"");
		strcpy(m,"");
		strcpy(maux,"");
		h=0;

		for(j=0;j<3;j++){	//LEE LOS PRIMEROS 3 DIGITOS
			op[j]=a[i];
			i++;
		}
		if(!strcmp(op,"111")){
			printf("fin\n");
			break;
		}else{
			if(!strcmp(op,"000")) strcpy(inst,"rrmov");
			if(!strcmp(op,"100")) strcpy(inst,"sum");
			if(!strcmp(op,"101")) strcpy(inst,"res");
			if(!strcmp(op,"110")) strcpy(inst,"xor");
			if(!strcmp(op,"001")){
				strcpy(inst,"rimov");
				h=1;
			}
			if(!strcmp(op,"010")){
				strcpy(inst,"mrmov");
				h=2;
			}
			if(!strcmp(op,"011")){
				strcpy(inst,"rmmov");
				h=3;
			}

			for(j=0;j<3;j++){	//LEE LOS OTROS 3
				op[j]=a[i];
				i++;
			}
			asignar(op,reg1);	//ASIGNA EL NOMBRE DEL REGISTRO SEGUN SU ID

			for(j=0;j<3;j++){	//LEE LOS OTROS 3
				op[j]=a[i];
				i++;
			}
			asignar(op,reg2);	//ASIGNA EL NOMBRE DEL REGISTRO SEGUN SU ID

			if(h!=0){		//DEPENDE DE LA INSTRUCCION LEE LOS OTROS 8 DEL INMEDIATO
				for(j=0;j<8;j++){
					maux[j]=a[i];
					i++;
				}
				transform(maux,m);	//SE TRANSFORMA A HEXADECIMAL
				if(h==2) printf("%s %s,%s\n",inst,m,reg2);	//SE IMPRIME DEPEINDIENDO DE LA INSTRUCCION
				else printf("%s %s,%s\n",inst,reg1,m);
			}else printf("%s %s,%s\n",inst,reg1,reg2);
		}
		
	}
}

void saberR(char reg1[],int *x){	//ASIGNA UN ID DEL REGISTRO EN UN ENTERO
	if(!strcmp(reg1,"S0")) *x=0;
	if(!strcmp(reg1,"S1")) *x=1;
	if(!strcmp(reg1,"S2")) *x=2;
	if(!strcmp(reg1,"S3")) *x=3;
	if(!strcmp(reg1,"S4")) *x=4;
	if(!strcmp(reg1,"S5")) *x=5;
	if(!strcmp(reg1,"S6")) *x=6;
	if(!strcmp(reg1,"S7")) *x=7;
}

void sum(char aux1[],char aux2[]){	//SUMA Y DEVUELVE RESULTADO EN AUX1
	int i,x=0,res=0;
	for(i=7;i>=0;i--){
		x=0;
		if(aux1[i]=='1') x++;
		if(aux2[i]=='1') x++;
		x+=res;
		if(x==0){
			aux1[i]='0';
			res=0;
		}
		if(x==1){
			aux1[i]='1';
			res=0;
		}
		if(x==2){
			aux1[i]='0';
			res=1;
		}
		if(x==3){
			aux1[i]='1';
			res=1;
		}
	}
}

void sumar(char reg1[],char reg2[]){	//SUMA DOS REGISTROS
	char aux[3],aux1[16],aux2[16];
	aux[0]=reg1[2];
	aux[1]=reg1[3];
	trans(aux1,aux);	//SE TRASFORMA A BINARIO EN AUX1
	aux[0]=reg2[2];
	aux[1]=reg2[3];
	trans(aux2,aux);	//SE TRANSFORMA A BINARIO EN AUX2

	sum(aux1,aux2);		//SE SUMAN Y SE GUARDA EN AUX1

	transform(aux1,aux);//SE TRANSFORMA A HEXA EN AUX

	strcpy(reg1,aux);//SE GUARDA RESULTADO EN EL REGISTRO
}

void invertir(char aux[]){//INVIERTE Y SUMA 1 PARA RESTAR
	int i,x=strlen(aux);
	char uno[9];
	strcpy(uno,"00000001");
	for(i=0;i<x;i++){	//SE INVIERTEN LOS DIGITOS
		if(aux[i]=='1') aux[i]='0';
		else aux[i]='1';
	}
	sum(aux,uno);		//SE SUMA 1
}

void resta(char reg1[],char reg2[]){
	char aux[3],aux1[16],aux2[16];
	aux[0]=reg2[2];
	aux[1]=reg2[3];
	trans(aux2,aux);	//TRANSFORMA A BINARIO REG2
	aux[0]=reg1[2];
	aux[1]=reg1[3];
	trans(aux1,aux);	//TRANSFORMA A BINARIO REG1
	invertir(aux2);		//SE INVIERTE Y SE SUMA 1 PARA LA RESTA
	sum(aux1,aux2);		//SE SUMAN LOS VALORES
	transform(aux1,aux);	//SE TRANSFORMA RESULTADO A HEXADECIMAL
	strcpy(reg1,aux);	//SE GUARDA RESULTADO EN EL REGISTRO
}

void xorr(char reg1[],char reg2[]){
	char aux[3],aux1[16],aux2[16];
	int i;
	aux[0]=reg1[2];
	aux[1]=reg1[3];
	trans(aux1,aux);	//SE TRANSFORMA A BINARIO EN AUX1
	aux[0]=reg2[2];
	aux[1]=reg2[3];
	trans(aux2,aux);	//SE TRANSFORMA A BINARIO EN AUX2

	for(i=0;i<8;i++){	//SE HACE EL XOR
		if(aux1[i]==aux2[i]) aux1[i]='0';
		else aux1[i]='1';
	}

	transform(aux1,aux);	//SE PASA EL RESULTADO A HEXADECIAML

	strcpy(reg1,aux);	//SE GUARDA RESULTADO EN EL REGISTRO	
}

void operar1(char inst[],char reg1[], char reg2[]){
	int x,y;
	saberR(reg1,&x);	//ASIGNA EL NUMERO DEL REGISTRO1 
	saberR(reg2,&y);	//ASIGNA EL NUMERO DEL REGISTRO2
	if(!strcmp(inst,"rrmov")) strcpy(R[x],R[y]);//SE COPIA DE UN REGISTRO A OTRO
	else
		if(!strcmp(inst,"sum"))	sumar(R[x],R[y]);//SE SUMA UN REGISTRO CON OTRO
		else
			if(!strcmp(inst,"res")) resta(R[x],R[y]);//SE RESTA UN REGISTRO CON OTRO
			else
				if(!strcmp(inst,"xor")) xorr(R[x],R[y]);//SE HACE XOR DE UN REGISTRO CON OTRO			
}

void operar2(char inst[],char reg1[], char reg2[],char m[]){
	int x,y,g;
	char m2[3];
	saberR(reg1,&x);	//ASIGNA EL NUMERO DEL REGISTRO1 
	saberR(reg2,&y);	//ASIGNA EL NUMERO DEL REGISTRO2

	m2[0]=m[2];
	m2[1]=m[3];
	sscanf(m2,"%x",&g);	//TRANSFORMA EL NUMERO HEXADECIMAL A UN ENTERO PARA USARSE DE INDICE

	if(!strcmp(inst,"rimov")){	//MUEVE EL INMEDIATO A LA MEMORIA
		strcpy(R[x],m);
	}
	if(!strcmp(inst,"mrmov")){	//MUEVE EL REGISTRO A LA MEMORIA
		strcpy(M[g],R[y]);
	}
	if(!strcmp(inst,"rmmov")){	//MUEVE DE LA MEMORIA A REGISTRO
		strcpy(R[x],M[g]);
	}
}

void ejecutarP(){
	char a[9999],op[5],inst[10],reg1[10],reg2[10],m[10],maux[10];
	int x,i=0,j=0,h=0;
	printf("Inserte programa a ejecutar: ");
	scanf("%s",&a);	//LEE EL CODIGO EN BINARIO
	x=strlen(a);
	while(i<x-1){
		strcpy(op,"");		//LIMPIA LOS ARREGLOS A USAR
		strcpy(inst,"");
		strcpy(reg1,"");
		strcpy(reg2,"");
		strcpy(m,"");
		strcpy(maux,"");
		h=0;

		for(j=0;j<3;j++){	//LEE LOS PRIMEROS 3 DIGITOS
			op[j]=a[i];
			i++;
		}
		if(!strcmp(op,"111")){	//COMPARA CON FIN
			break;
		}else{
			if(!strcmp(op,"000")) strcpy(inst,"rrmov");	//COMPARA
			if(!strcmp(op,"100")) strcpy(inst,"sum");
			if(!strcmp(op,"101")) strcpy(inst,"res");
			if(!strcmp(op,"110")) strcpy(inst,"xor");
			if(!strcmp(op,"001")){
				strcpy(inst,"rimov");
				h=1;
			}
			if(!strcmp(op,"010")){
				strcpy(inst,"mrmov");
				h=2;
			}
			if(!strcmp(op,"011")){
				strcpy(inst,"rmmov");
				h=3;
			}

			for(j=0;j<3;j++){	//LEE LOS OTROS 3
				op[j]=a[i];
				i++;
			}
			asignar(op,reg1);	//ASIGNA EL NOMBRE DEL REGISTRO SEGUN SU ID
			
			for(j=0;j<3;j++){	//LEE LOS OTROS 3
				op[j]=a[i];
				i++;
			}
			asignar(op,reg2);	//ASIGNA EL NOMBRE DEL REGISTRO SEGUN SU ID
		
			if(h!=0){			//DEPENDE DE LA INSTRUCCION LEE LOS OTROS 8 DEL INMEDIATO
				for(j=0;j<8;j++){
					maux[j]=a[i];
					i++;
				}
				transform(maux,m);//TRANSFORMA EN HEXADECIAML LA DIRECCION O EL DATO
				operar2(inst,reg1,reg2,m);	//OPERA CON INMEDIATO
			}else operar1(inst,reg1,reg2);	//OPERA SIN INMEDIATO
		}
	}
}

int main(){
	int i;
	char a='t';
	char g;
	for(i=0;i<8;i++){	//SE INICIALIZAN LOS REGISTROS EN 0X00
		strcpy(R[i],"0x00");
	}
	for(i=0;i<=255;i++){	//SE INICIALIZA LA MEMORIA EN 0X00
		strcpy(M[i],"0x00");
	}
	while(a!='0'){
		printf("\nMENU PRINCIPAL:\n");
		printf("1) Mostrar el estado del procesador.\n");
		printf("2) Mostrar el estado de memoria.\n");
		printf("3) Desensamblar un programa\n");
		printf("4) Ejecutar un programa completo\n");
		printf("5) Créditos\n");
		printf("0) Salir\n\n>>");
		scanf("%c",&a);
		switch(a){
			case '1':	mostrarP();	//MUESTRA LOS REGISTROS
						printf("\n\nPresione Enter para volver al menu principal\n");
						setbuf(stdin, NULL);	//SE LIMPIA EL BUFFER
	  					getchar();	//SE ESPERA UNA TECLA PRESIONADA
						break;
			case '2':	mostrarM();	//MUESTRA LA MEMORIA
						printf("\n\nPresione Enter para volver al menu principal\n");
						setbuf(stdin, NULL);	//SE LIMPIA EL BUFFER
	  					getchar();	//SE ESPERA UNA TECLA PRESIONADA
						break;
			case '3':	desensamblarP();	//DESENSABLA CÓDIGO
						printf("\n\nDesensamblado completado...\nPresione Enter para volver al menu principal\n");
						setbuf(stdin, NULL);	//SE LIMPIA EL BUFFER
	  					getchar();	//SE ESPERA UNA TECLA PRESIONADA
						break;
			case '4':	ejecutarP();		//EJECUTA CÓDIGO
						printf("\n\nEjecucion exitosa...\nPresione Enter para volver al menu principal\n");
						setbuf(stdin, NULL);	//SE LIMPIA EL BUFFER
	  					getchar();	//SE ESPERA UNA TECLA PRESIONADA
						break;
			case '5':	printf("\nCREDITOS:\nJesús Martínez\n21.194.440\nOrganización y estructura del computador I\nProyecto 2");
						printf("\n\nPresione Enter para volver al menu principal\n");
						setbuf(stdin, NULL);	//SE LIMPIA EL BUFFER
	  					getchar();	//SE ESPERA UNA TECLA PRESIONADA
						break;
			case '0':	break;

			default:	printf("\n\nOpcion invalida\n\n");
						break;
		}
	}
}