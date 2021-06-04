//JESUS MARTINEZ 21.194.440
//ALEJANDRA VENTO 26.648.099  

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <semaphore.h>
#include <unistd.h> 

sem_t A,B,C;

struct cola{
	char* s;
	struct cola *sig;
};

struct cola *inicioE;
struct cola *finalE;
struct cola *inicioD;
struct cola *finalD;

void imprimir(struct cola *p){
	struct cola *aux=p;
	while(aux!=NULL){
		printf("%s\n",aux->s);
		aux=aux->sig;
	}
}

void agregar(struct cola **p, struct cola **f, char* s){
	struct cola *new=malloc(sizeof(struct cola *));
	new->s=malloc(sizeof(char*)*512);
	strcpy(new->s,s);
	if(*p==NULL){
		*p=new;
		*f=new;
	}
	else{
		(*f)->sig=new;
		*f=new;
	}
}

void llenar(char *s){
	FILE *archivo;
	char* leer;
	archivo=fopen(s,"r");

	while(!feof(archivo)){
		leer=malloc(sizeof(char*)*512);
		fscanf(archivo,"%s\n",leer);
		agregar(&inicioE,&finalE,leer);
	}
	fclose(archivo);
}

void encolar(struct cola **p,struct cola **f, struct cola *n){
	while(n!=NULL){
		if(*p==NULL&&*f==NULL){
			*p=n;
			*f=n;
		}
		else{
			(*f)->sig=n;
			(*f)=n;
		}
		n=n->sig;
	}
}

void* desencriptar(){
	char* s=NULL;
	char* g=malloc(sizeof(char*)*512);
	char* seg=NULL;
	char seg2;
	int cont=0;
	int i=0,m=0,f=0,marca=0,x=0;
	while(inicioE!=NULL){
		sem_wait(&A);
		if(inicioE!=NULL){
			s=malloc(sizeof(char*)*512);
			strcpy(s,inicioE->s);
			inicioE=inicioE->sig;
		}else{
			sem_post(&A);
			break;
		}
		sem_post(&A);
		if(s[0]=='-'&&s[1]=='.'&&s[2]=='-'){
			marca=m=3;
		}
		for(m=marca;m<strlen(s)-2;m++){
			if(s[m]=='t'&&s[m+1]=='.'){
				seg=malloc(sizeof(char*)*512);
				m++;
				cont=0;
            f=0;
				while(s[m]!='t'){
					cont++;
					m++;
				}
				m++;
				while(cont>=1){
					seg[f]=(cont%10)+'0';
					cont=cont/10;
				}
				strcat(g,"Espera ");
				x+=7;
				for(f=strlen(seg)-1;f>=0;f--){
					g[x]=seg[f];
				}
				strcat(g," segundos");
				x+=9;
			}
			if(s[m]=='-'&&s[m+1]=='.'&&s[m+2]=='-'&&(m+2)<strlen(s)-2){
				m=m+2;
				g[x]=',';
				x++;
				g[x]=' ';
				x++;
			}else{
				if(s[m]!='-'){
					g[x]=s[m];
				}else{
					g[x]=' ';
				}
				x++;
			}
		}
		sem_wait(&B);
		agregar(&inicioD,&finalD,g);
		sem_post(&B);
		g=malloc(sizeof(char*)*512);
		x=0;
	}
	pthread_exit(NULL);
}

void* desplegar(){
   char *s;
   while(inicioD!=NULL){
      sem_wait(&C);
      s=malloc(sizeof(char*)*512);
      strcpy(s,inicioD->s);
      inicioD=inicioD->sig;
      sem_post(&C);
      printf("%s\n",s);
   }
	pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	inicioE=NULL;
	finalE=NULL;
	inicioD=NULL;
	finalD=NULL;
	sem_init(&A,0,1);
	sem_init(&B,0,1);
   sem_init(&C,0,1);
	char *s=argv[1];
	int i,rc;
	int trabajadores=atoi(argv[2]),despliegue=atoi(argv[3]);
	pthread_t workers[trabajadores];
	pthread_t deployers[despliegue];
	llenar(argv[1]);

	for(i=0;i<trabajadores;i++){
	    rc = pthread_create(&workers[i], NULL, desencriptar,NULL);
	    if (rc) {	
		    printf("ERR; pthread_create() ret = %d\n", rc);
		    exit(-1);
	    }
	}
	for(i=0;i<trabajadores;i++){
		pthread_join(workers[i],NULL);
	}

	for(i=0;i<despliegue;i++){
	    rc = pthread_create(&deployers[i], NULL, desplegar,NULL);
	    if (rc) {
		    printf("ERR; pthread_create() ret = %d\n", rc);
		    exit(-1);
	    }
	}
	for(i=0;i<despliegue;i++){
		pthread_join(deployers[i],NULL);
	}

	return 0;
}
