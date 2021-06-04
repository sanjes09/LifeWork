#include <stdio.h>
#include <stdlib.h>
#include <string.h>


struct lista1{
	char nombre[100];
	char cedula[100];
	char direccion[100];
	char fecha[100];
	lista1 *prox;
};

struct organizacion{
	char nombre[100];
	lista1 *pers;
	organizacion *prox;
};

struct lista{
	char nombre[100];
	char mesas[100];
	char centro[100];
	char estado[100];
	char ciudad[100];
	char direccion[100];
	organizacion *testigo;
	lista *prox;
	lista1 *aba;
};
                                                  //verifica nueva persona en base de dato 2da parte
int personrepetida2(lista1 *p,char a[100]){
	lista1 *t=p; int cont=0; int x=100;
	while (t){
		if (strncmp(t->cedula,a,x)==0)
			cont++;
		t=t->prox;
	}
	return cont;
};

                                                  //verifica nueva persona en base de dato
int personrepetida(lista **p, char a[100]){
	lista *t=*p; int cont=0;
	while (t){
		cont+=personrepetida2(t->aba,a);
		t=t->prox;
	}
	if (cont > 0)
		return 1;
	else
		return 0;
};

                                                  //verifica nuevo centro en base de dato
int centrorepetido(lista *p, char a[100]){
	lista *t=p; int x=100;
	while (t){
		if (strncmp(t->centro,a,x)==0)
			return 1;
		else
			t=t->prox;
	}
	return 0;
};
                                                  //ordena las personas de menor a mayor con la cedula
void ordenper2(lista1 **p){
	lista1 *t=*p; lista1 *h; int cont=-1; int x,y;
	while (t->prox){
		x=atoi(t->prox->cedula);
		y=atoi((*p)->cedula);
		printf("x:%dy:%d\n",x,y);
		if (x < y){
			h=t->prox;
			t->prox=h->prox;
			h->prox=(*p);
			(*p)=h;
			t=(*p);}
		else
			t=t->prox;
	}
	if ((*p)->prox)
	ordenper2(&((*p)->prox));
};


void ordenper(lista **p){
	lista *t=*p;
	while (t->prox){
	ordenper2(&(t->aba));
	t=t->prox;
	}
};

                                                  //ordena a los centros de menor a mayor por codigo
void orden(lista **p){
	lista *t=*p; lista *h; int cont=-1; int x,y;
	while (t->prox){
		x=atoi(t->prox->centro);
		y=atoi((*p)->centro);
		printf("x:%dy:%d\n",x,y);
		if (x < y){
			h=t->prox;
			t->prox=h->prox;
			h->prox=(*p);
			(*p)=h;
			t=(*p);}
		else
			t=t->prox;
	}
	if ((*p)->prox)
	orden(&((*p)->prox));
};


                                                  // borra centros sin votantes
void centrovacio(lista **p){
	lista *t=*p; lista *h=t;
	while (t){
	if ((t->aba)==NULL){
		(*p)=(*p)->prox;
		delete t;
		printf("Se ha eliminado un centro de votacion sin votantes.\n");
		t=*p;
		h=t;}
	else{
		t=t->prox;
		while (t){
			if ((t->aba)==NULL){
				h->prox=t->prox;
				delete t;
				printf("Se ha eliminado un centro de votacion sin votantes.\n");
				t=h->prox;}
			else{
				h=h->prox;
				t=t->prox;}
		}
	}
	}
};
                                                  //Valida los centros
void validcentro(lista **p){
	lista *t=*p; lista *h=t->prox; int cont=0; int x=100;
	while ((t)&& (h)){
		
	while ((cont < 1) && (h)){
		if (strncmp(t->centro,h->centro,x)==0)
			cont++;
	    h=h->prox;};
	t=t->prox;
	h=t->prox;
	};
	if (cont > 0)
		printf("Hay algun centro con el mismo codigo...\n");
	else
		printf("Esta todo en orden con los centro...\n");
}
                                                  //escribe partido en archivo
void escribirpar(char txt[],organizacion *p){ 
   organizacion *t=p; FILE *archivo;
   char a[100];
   archivo= fopen(txt,"a");
   if (archivo !=NULL){
   while (t){
	   strcpy(a,"*partido");
	   fprintf(archivo,"%s\n",a);
       strcpy(a,t->nombre);
	   fprintf(archivo,"%s\n",a);
	   strcpy(a,t->pers->cedula);
	   fprintf(archivo,"%s\n",a);
	   t=t->prox;
   };
   fclose(archivo);
   };
};


                                                  //escribe persona en archivo
void escribirper(char txt[],lista1 *p){
	lista1 *t=p; FILE *archivo;
	char a[100];
	archivo= fopen(txt,"a");
	if (archivo !=NULL){
	while (t){
		strcpy(a,"*persona");
		fprintf(archivo,"%s\n",a);
        strcpy(a,t->nombre);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->cedula);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->direccion);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->fecha);
		fprintf(archivo,"%s\n",a);
		t=t->prox;
	};
	fclose(archivo);
	};
};
                                                  //escribe centro en archivo
void escribir(char txt[],lista *p){
	FILE *archivo;
	lista *t=p;
	char a[100];


	archivo = fopen (txt,"w");
	if (archivo !=NULL){
	while (t){
		strcpy(a,"*centro");
		fprintf(archivo,"%s\n",a);
        strcpy(a,t->nombre);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->mesas);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->centro);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->estado);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->ciudad);
		fprintf(archivo,"%s\n",a);
		strcpy(a,t->direccion);
		fprintf(archivo,"%s\n",a);
		fclose(archivo);
		escribirper("archivo.txt",(t->aba));
		escribirpar("archivo.txt",(t->testigo));
		archivo=fopen(txt,"a");
		t=t->prox;
	};
	fclose(archivo);
	};
};


                                                  //busca apuntador de una persona
lista1 *personatestigo(lista1 *p,char a[100]){
    lista1 *h=p;  int x=100;
	while (h){
		if (strncmp((h->cedula),a,x)==0)
			return h;
	else
	h=h->prox;
	}
};

                                                  //lee de archivo
void leercentro(char txt[],lista **p){  
	FILE *archivo;
	lista *t; lista1 *g;
	char a[100]; int c=20; char cen[20]; char per[20]; char par[20];
	strcpy(cen,"*centro");
	strcpy(per,"*persona");
    strcpy(par,"*partido");
	
	archivo =fopen(txt,"r");
    
	if (archivo !=NULL){
	while (!feof(archivo)){
          fscanf(archivo,"%s\n",a);
		  if ((strncmp(a,cen,c)!=0) && (strncmp(a,per,c)!=0)){
			  printf ("ARCHIVO INVALIDO, DEBE TENER CENTRO PRIMERO\n");
			  break;}
		  else{
          while ((strncmp(a,cen,c)==0) || (strncmp(a,per,c)==0)){
             if (strncmp(a,cen,c)==0){
				 lista *t=new lista;
                 fscanf(archivo,"%s\n",&a);
		         strcpy(t->nombre,a);
		         fscanf(archivo,"%s\n",&a);
		         strcpy(t->mesas,a);
		         fscanf(archivo,"%s\n",&a);
		         strcpy(t->centro,a);
		         fscanf(archivo,"%s\n",&a);
		         strcpy(t->estado,a);
		         fscanf(archivo,"%s\n",&a);
		         strcpy(t->ciudad,a);
		         fscanf(archivo,"%s\n",&a);
		         strcpy(t->direccion,a);
		         t->aba=NULL; t->testigo=NULL; 
		         t->prox=*p;
				 *p=t;
				 fscanf(archivo,"%s\n",a);
				 while (strncmp(a,per,c)==0){
                 if (strncmp(a,per,c)==0){
					 lista1 *aux=new lista1;
					 fscanf(archivo,"%s\n",&a);
					 strcpy(aux->nombre,a);
					 fscanf(archivo,"%s\n",&a);
					 strcpy(aux->cedula,a);
					 fscanf(archivo,"%s\n",&a);
					 strcpy(aux->direccion,a);
					 fscanf(archivo,"%s\n",&a);
					 strcpy(aux->fecha,a);
					 aux->prox=t->aba;
					 t->aba=aux;
					 fscanf(archivo,"%s\n",a);
				 }
				 }
				 while (strncmp(a,par,c)==0){
					 organizacion *par=new organizacion;
					 fscanf(archivo,"%s\n",&a);
					 strcpy(par->nombre,a);
					 fscanf(archivo,"%s\n",&a);
					 g=personatestigo(t->aba,a);
					 par->pers=g;
					 par->prox=t->testigo;
					 t->testigo=par;}					 

			 }
			};
		  };
	};


		fclose(archivo);	
	}
};


                                                  //insertar persona
void insertacab1 (lista **p,lista1 **h){
	char a[100];
         lista1 *t = new lista1;
		 
		 printf("Introduzca el nombre\n");
		 scanf("%s",&a);
		 strcpy(t->nombre,a);
		 
		 printf("Introduzca el numero de cedula\n");
		 scanf("%s",&a);
		 strcpy(t->cedula,a);
		 if ((personrepetida(p,a)==1)){
			 printf ("Ya hay una persona en la base de datos con este numero de cedula.\n");
			 delete t;
		 }
		 else{
			 printf("Introduzca la Direccion\n");
		     scanf("%s",&a);
		     strcpy(t->direccion,a); 
		 
		     printf("Introduzca la fecha de nacimiento (DD/MM/AAAA) \n");
		     scanf("%s",&a);
		     strcpy(t->fecha,a); 
		 
		     t->prox = *h;
		     *h= t;
		 }
};




                                                  //insertar partidos politicos
void insertes(organizacion **p,lista1 **b){
	char a[100]; organizacion *t =new organizacion; lista1 *h=*b;
	int x=100, v=0;
	printf ("inserte el nombre del partido\n");
	scanf("%s",&a);
	strcpy(t->nombre,a);
	t->pers=NULL;
	t->prox=(*p);
	*p=t;
	printf("inserte el numero de cedula de la persona que sera testigo de este partido\n");
	scanf("%s",&a);
	while (h){
		if (strncmp((h->cedula),a,x)==0){
			t->pers=h;
		    v++;
			break;
		}
		else
			h=h->prox;
    if (v!=0)
		printf("La persona no se encuentra en este centro\n");
	};
};




                                                  //insertar centros
void insertaCab(lista **p){
	char a[100]; int x=100;
         lista *t = new lista;
		 
		 printf("Introduzca el nombre\n");
		 scanf("%s",&a);
		 strcpy(t->nombre,a);
		 
		 printf("Introduzca el numero de mesas\n");
		 scanf("%s",&a);
		 strcpy(t->mesas,a);
		 
		 printf("Introduzca el codigo del centro electoral\n");
		 scanf("%s",&a);
		 strcpy(t->centro,a);
		 if (centrorepetido(*p,a)==1){
			 printf ("Ya hay un centro en la base de datos con este codigo.\n");
			 delete t;
		 }
		 else{
			 printf("Introduzca el estado\n");
     		 scanf("%s",&a);
	     	 strcpy(t->estado,a);
		 
	    	 printf("Introduzca la Ciudad\n");
	    	 scanf("%s",&a);
		     strcpy(t->ciudad,a);
		 
		     printf("Introduzca la Direccion\n");
			 scanf("%s",&a);
			 strcpy(t->direccion,a);         
			 
		 
			 t->prox = *p;
			 t->aba=NULL;
			 t->testigo=NULL;
			 *p= t;
		 };
};




                                                  //muestra los centros electorales
void muestra(lista *p){
		lista *t = p;
		while (t){
			printf("Nombre: %s  \n",t->nombre);
	        printf("Numero de mesa: %s  \n",t->mesas);
	        printf("Centro de votacion: %s  \n",t->centro);
	        printf("Estado: %s  \n",t->estado);
	        printf("Ciudad: %s  \n",t->ciudad);
	        printf("Direccion: %s  \n\n",t->direccion);
			t = t->prox;
		}
		printf("\n");
	}
                                                  //muestra a las personas 
void muestra1(lista1 *p){
		lista1 *t = p;
		while (t){
			printf("Nombre: %s  \n",t->nombre);
	        printf("Cedula: %s  \n",t->cedula);
	        printf("Direccion: %s  \n",t->direccion);
			printf("Fecha: %s  \n\n",t->fecha);
			t = t->prox;
		}
		printf("\n");
	}

                                                  //muestra a los partidos politicos
void muestrapartido(organizacion *p){
	organizacion *t=p;
	while (t){
		printf ("Nombre del partido: %s\n",t->nombre);
		printf ("Datos de la persona: \n");
		printf ("Nombre: %s\n",t->pers->nombre);
		printf ("Numero de cedula: %s\n",t->pers->cedula);
		printf ("Direccion: %s\n",t->pers->direccion);
		printf ("Fecha de nacimiento: %s\n\n",t->pers->fecha);
		
		t=t->prox;
	};
};

                                                  //elimina partidos politicos
void eliminarpartido(organizacion **p){
	organizacion *t=*p , *aux; int x=100;
	char a[100];
	printf ("introduzca el nombre a borrar\n\n");
	scanf("%s",a);
	if (((*p)) && ((strncmp(((*p)->nombre),a,x)==0))){
		aux=t;
		(*p)= t->prox;
		t=t->prox;
		delete aux;}
	else{
		while ((t->prox)&&((strncmp((t->prox->nombre),a,x)!=0))){
			t=t->prox;}
		if (t->prox){
			aux=t->prox;
			t->prox=t->prox->prox;
			delete aux;
		};
	};
};

                                                  //Buscar un dato de centro
void buscar(lista **p){
  lista *t=*p; int x=100, y=-1, c=0, h=-1, j=-1; char a[100],b[100];
  printf ("Introduzca el dato a buscar\n\n");
  scanf("%s",a);
  while (t){
  if ((strncmp((t->nombre),a,x)==0)||(strncmp((t->mesas),a,x)==0)||(strncmp((t->centro),a,x)==0)||(strncmp((t->estado),a,x)==0)||(strncmp((t->ciudad),a,x)==0)||(strncmp((t->direccion),a,x)==0)){
	 printf("Nombre: %s  \n",t->nombre);
	 printf("Numero de mesa: %s  \n",t->mesas);
	 printf("Centro de votacion: %s  \n",t->centro);
	 printf("Estado: %s  \n",t->estado);
	 printf("Ciudad: %s  \n",t->ciudad);
	 printf("Direccion: %s  \n\n",t->direccion);
	 c++;
	 while (h!=0){
	 printf("************ MENU DE CENTROS ************\n");
     printf("1. Modificar dato del centro\n");
	 printf("2. Agregar personal al centro\n");
	 printf("3. Agregar partidos a este centro\n");
	 printf("4. Mostrar las personas de este centro\n");
	 printf("5. Mostrar los partidos politicos presentes en este centro\n");
	 printf("6. Eliminar un partido\n");
	 printf("0. Salir\n");
	 scanf ("%d",&y);
	 switch (y){
		 case 1:  printf("1.Cambiar nombre\n");
				  printf("2.Cambiar numero de mesas\n");
				  printf("3.Cambiar centro de votacion\n");
				  printf("4.Cambiar estado\n");
				  printf("5.Cambiar ciudad\n");
				  printf("6.Cambiar direccion\n");
				  scanf("%d",&j);
				  switch (j){
					  case 1: printf("introduzca el nuevo nombre\n");
						      scanf("%s",b);
							  strcpy(t->nombre,b);
							  break;
				      case 2: printf("introduzca el nuevo numero de mesas\n");
						  	  scanf("%s",b);
							  strcpy(t->mesas,b);
							  break;
			          case 3: printf("introduzca el nuevo centro de votacion\n");
				              scanf("%s",b);
				              strcpy(t->centro,b);
				              break;
			          case 4: printf("introduzca el nuevo estado\n");
				              scanf("%s",b);
				              strcpy(t->estado,b);
				              break;
                      case 5: printf("introduzca la nueva ciudad\n");
				              scanf("%s",b);
				              strcpy(t->ciudad,b);
				              break;
		              case 6: printf("introduzca la nueva direccion\n");
				              scanf("%s",b);
				              strcpy(t->direccion,b);
				              break;
					  };
	 
				  break;
		 case 2: insertacab1(&(*p),&(t->aba));				 
				 break;
				 
		 case 3: insertes(&(t->testigo),(&(t->aba)));
				 break;
	 
		 case 4: muestra1(t->aba);
				 break;



		 case 5: muestrapartido(t->testigo);
			     break;



		 case 6: eliminarpartido(&(t->testigo));
			     break;



		 case 0: h=0;
			     break;
	 }
	 }
  };
  
  t=t->prox;
  }
  if (c==0)
	  printf("El dato no se encuentra\n\n");
}; 


                                                  //elimina un centro
void eliminar(lista **p){
	lista *t=*p , *aux; int x=100;
	char a[100];
	printf ("introduzca el nombre a borrar\n\n");
	scanf("%s",a);
	if (((*p)) && ((strncmp(((*p)->nombre),a,x)==0))){
		aux=t;
		(*p)= t->prox;
		t=t->prox;
		delete aux;}
	else{
		while ((t->prox)&&((strncmp((t->prox->nombre),a,x)!=0))){
			t=t->prox;}
		if (t->prox){
			aux=t->prox;
			t->prox=t->prox->prox;
			delete aux;
		};
	};
};

                                                  //mueve persona a un nuevo centro
void agreg(lista1 **p,lista1 **l){
	(*l)->prox=(*p);
	(*p)=(*l);
};
                                                  //devulelve apuntador de parsona por cedula
lista1 *person(lista1 *p,char a[100]){
	lista1 *t=p; int x=100;
	while (t){
		if (strncmp(t->cedula,a,x)==0)
			return t;
		else
			t=t->prox;
	};
	return 0;
};


                                                  //mueve a la parsona
void move3(lista1 **p, lista1 **h){
	lista1 *t=*p; 
	(*h)->prox=t->prox;
	t->prox=*h;
};

void move2(lista **p, lista1 **h){
	lista *t=*p; lista1 *g; char a[100]; int x=100; int cont=0;
	printf("Introduzca el codigo de centro al que se desea mover esta persona: ");
	scanf("%s",&a);
	while ((t) && (cont ==0)){
		if (strncmp((t->centro),a,x)==0){
			cont++;
			if(t->aba!=NULL)
			move3(&(t->aba),&(*h));
		}
		else
			t=t->prox;
	};
};



void move(lista1 **p,lista1 *h){
	lista1 *t=*p; int x=100; int cont=0;
	printf("1.");
	if (t)
		if (strncmp(((*p)->cedula),(h->cedula),x)==0)
			(*p)=(*p)->prox;
	else{
		while ((t)&&(cont==0)){
			if (strncmp((t->prox->cedula),(h->cedula),x)==0){
				t->prox=h->prox;
				cont++;
			    h->prox=NULL;}
			else
				t=t->prox;
		}
	};
};

                                                  //elimina persona
void elim(lista1 **p,lista1 **h){
	lista1 *t=*p; int x=100; int cont=0; lista1 *v;
	if (t)
		if (strncmp(((*p)->cedula),((*h)->cedula),x)==0){
			(*p)=(*p)->prox;
			delete h;}
	else{
		while ((t->prox)&&(cont==0)){
			if (strncmp((t->prox->cedula),((*h)->cedula),x)==0){
				v=t->prox;
				t->prox=t->prox->prox;
				cont++;
				delete v;}
			else
				t=t->prox;
		}
	};
};


                                                  //elimina el partido politico al mover o eliminar persona
void elimtestigo(organizacion **p,lista1 *h){
	organizacion *t=*p; int x=100; int cont=0; organizacion *v;
	if (strncmp(((*p)->pers->cedula),(h->cedula),x)==0){
		    printf("1");
			(*p)=(*p)->prox;
			delete t;}
	else
		while ((t->prox)&&(cont==0)){
			if (strncmp((t->prox->pers->cedula),(h->cedula),x)==0){
				v=t->prox;
			    t->prox=t->prox->prox;
				delete v;
				cont++;
			}
			else
				t=t->prox;
		}
};

                                                  //devuelve el apuntador del partido politico de un testigo
organizacion *partido(organizacion *p, lista1 *h){
	organizacion *org=p; int a=100;
	while (org){
		if (strncmp((org->pers->cedula),(h->cedula),a)==0)
			return org;
		else
			org=org->prox;
	}
	return 0;
};
                                                  //elimina o mueve a una persona
void buscaper(lista **p){
	lista *t=*p; char a[100]; lista1 *h=NULL; organizacion *x; int g;
	printf ("Ingrese el numero de cedula de la persona\n");
	scanf("%s",&a);
	system("cls");
	while ((h==NULL) && (t!=NULL)){
	h=person(t->aba,a);
	if (h==NULL)
		t=t->prox;
	else{
		printf("Nombre: %s  \n",h->nombre);
	    printf("Cedula: %s  \n",h->cedula);
	    printf("Direccion: %s  \n",h->direccion);
	    printf("Fecha: %s  \n\n",h->fecha);
		printf("***Pertenece al centro:\n");
	    printf("Nombre: %s  \n",t->nombre);
	    printf("Numero de mesa: %s  \n",t->mesas);
	    printf("Centro de votacion: %s  \n",t->centro);
	    printf("Estado: %s  \n",t->estado);
	    printf("Ciudad: %s  \n",t->ciudad);
	    printf("Direccion: %s  \n\n",t->direccion);
		x=partido(t->testigo,h);
		if (x){
			printf("***Es testigo de mesa de este partido:\n");
			printf("%s\n",x->nombre);}
		else
			printf("***No es testigo de mesa\n");
		printf("Que desea hacer?:\n");
		printf("1.Mover a otro centro\n");
		printf("2.Eliminar\n");
		printf("0. Salir\n");
		scanf("%d",&g);
		switch (g){
			case 1:move(&(t->aba),h);
				   elimtestigo(&(t->testigo),h);
			       move2(&(*p),&h);
			       break;
			case 2:elimtestigo(&(t->testigo),h);
				   elim(&(t->aba),&h);
				   break;
			
		};
	};
	};
	if (h==NULL)
		printf("***No esta esta persona en la base de datos \n\n");
};

		    



int main ( )
{
 lista * p=NULL; 
 char a[100]; int b;
 int x = -1; 
 int op;
 op=-1;
 while (op!=0){
    printf ("************ MENU PRINCIPAL ************\n");
	printf ("1. Agregar Centro al registro\n");
	printf ("2. Buscar y/o modificar centro y agregar personas y partidos\n");
	printf ("3. Eliminar centro del registro\n");
	printf ("4. Eliminar o mover persona\n");
	printf ("5. Mostrar registro por pantalla\n");
	printf ("6. Cargar centro de archivos\n");
	printf ("7. Guardar en archivos\n");
	printf ("8. Validar base de datos\n");
	printf ("9. Limpiar pantalla\n");
	printf ("0. Salir\n\n");
	scanf ("%d", &op);
	switch (op){
		case 1: insertaCab(&p);
			    break;
	    case 2: buscar(&p);
			    break;
		case 3: eliminar(&p);
			    break;
		case 4: buscaper(&p);
			    break;
		case 5: muestra(p);
			    break;
		case 6: leercentro("archivo.txt",&p);
			    break;
		case 7: escribir("archivo.txt",p);
			    break;
		case 8: orden(&p);
			    validcentro(&p);
			    centrovacio(&p);
				ordenper(&p);
			    break;
		case 9: system("cls");
			    break;
	};
 };
}