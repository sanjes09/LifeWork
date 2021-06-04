#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <semaphore.h>
#include <unistd.h> 

sem_t A,B;

struct stat{
	int lin;
	char* linL;
	char* linC;
};

struct pasar{
	char *s;
	struct stat *p;
};

struct lista{
	char *s;
	struct lista *sig;
};

struct lista *sortedArchivos=NULL;
struct lista *fsorted=NULL;
int cuantos=0;

struct lista *temps=NULL;
struct lista *tmp=NULL;

int boool=0;
int cantidad=0;
char *largo;
char *corto;

void imprimir(struct lista *p){
	struct lista *aux=p;
	while(aux!=NULL){
		printf("%s\n",aux->s);
		aux=aux->sig;
	}
}

int agregar(struct  lista **p,char *str){
	struct lista *aux=*p;
	struct lista *nueva=malloc(sizeof(struct lista));
	int x=0;
	nueva->s=str;
	nueva->sig=*p;
	if(*p==NULL){
		*p=nueva;
		return 1;
	}
	else{
		if(strcasecmp(str,(*p)->s)!=0){
			if(strcasecmp(str,(*p)->s)>0){
				nueva->sig=*p;
				*p=nueva;
				return 1;
			}
			else{
				while(aux->sig!=NULL){
					if(strcasecmp(str,aux->sig->s)!=0){
						if(strcasecmp(str,aux->sig->s)>0){
							nueva->sig=aux->sig;
							aux->sig=nueva;
							x=1;
							return 1;
						}
						else aux=aux->sig;
					}
					else{
						x=1;
						return 0;
					}
				}
				if(x==0){
					nueva->sig=aux->sig;
					aux->sig=nueva;
					return 1;
				}
			}
		}
		else{
			free(nueva);
			return 0;
		}
	}
}

int compare(const void* a, const void* b){
    const char *ia = *(char **)a;
    const char *ib = *(char **)b;
    return strcasecmp(ib, ia);
}

void aggSorted(char *s){
	struct lista *new=malloc(sizeof(struct lista));
	new->s=s;
	new->sig=NULL;
	if(sortedArchivos==NULL){
		sortedArchivos=new;
		fsorted=new;
	}
	else{
		fsorted->sig=new;
		fsorted=new;
	}
	++cuantos;
}
void temp(char *y){
	struct lista *new=malloc(sizeof(struct lista));
	new->s=y;
	new->sig=NULL;
	if(temps==NULL){
		temps=new;
		tmp=new;
	}else{
		tmp->sig=new;
		tmp=new;
	}
}
void* merge(void *n){
	FILE *r1,*r2, *w1;
	int lineas1=0,lineas2=0,total=0;
	char *s1,*s2, *s3,*leer;
	struct lista *s=NULL;
	s1=malloc(sizeof(char*));
	s2=malloc(sizeof(char*));
	s3=malloc(sizeof(char*));
	leer=malloc(sizeof(char*));
	if(cuantos>1){
		sem_wait(&A);
		s1=sortedArchivos->s;
		sortedArchivos=sortedArchivos->sig;
		--cuantos;
		s2=sortedArchivos->s;
		sortedArchivos=sortedArchivos->sig;
		--cuantos;
		sem_post(&A);
		r1=fopen(s1,"r");
		r2=fopen(s2,"r");
		strcpy(s3,s1);
		strcat(s3,".temp");
		w1=fopen(s3,"w");

		while(!feof(r1)||!feof(r2)){
			if(!feof(r1)){
				leer=malloc(sizeof(char*));
				fscanf(r1,"%s\n",leer);
				agregar(&s,leer);
				lineas1++;
			}
			if(!feof(r2)){
				leer=malloc(sizeof(char*));
				fscanf(r2,"%s\n",leer);
				agregar(&s,leer);
				lineas2++;
			}
		}

		while(s!=NULL){
			if(s->sig!=NULL) fprintf(w1, "%s\n",s->s);
			else fprintf(w1, "%s",s->s);
			s=s->sig;
			total++;
		}
		fclose(r1);
		fclose(r2);
		fclose(w1);
		sem_wait(&A);
		temp(s3);
		aggSorted(s3);
		sem_post(&A);
		printf("Fusionado %i líneas y %i líneas en %i líneas\n",lineas1,lineas2,total);
	}
}

void* ordenar(void * n){
	struct lista *p=NULL;
	struct lista *aux=NULL;
	struct pasar *st;
	st=(struct pasar*) n;
	unsigned int lineas=0,i;
	char *s,*y;
	s=st->s;
	FILE *archivo;
	//AGREGAR
	archivo=fopen(s,"r");

	while(!feof(archivo)){
		y=malloc(sizeof(char*)*512);
		fscanf(archivo,"%s\n",y);
		sem_wait(&B);
		if(boool==0){
			strcpy(largo,y);
			strcpy(corto,y);
			boool++;
		}
		else{
			if(strlen(largo)<strlen(y)) strcpy(largo,y);
			if(strlen(corto)>strlen(y)) strcpy(corto,y);
		}
		sem_post(&B);
		aux=malloc(sizeof(struct lista));
		aux->s=y;
		aux->sig=p;
		p=aux;
		lineas++;
	}
	cantidad+=lineas;

	fclose(archivo);
	char* lista[lineas];
	for(i=0;i<lineas;i++){
		lista[i]=p->s;
		p=p->sig;
	}
	
	qsort(lista,lineas,sizeof(char*),compare);
	
	//GUARDAR
	char *o=malloc(sizeof(char*));
	strcpy(o,s);
	strcat(o,".sorted");
	archivo=fopen(o,"w");
	aggSorted(o);
	for(i=0;i<lineas;i++){
		if(i<lineas-1) fprintf(archivo,"%s\n",lista[i]);
		else fprintf(archivo,"%s",lista[i]);
	}
	fclose(archivo);
	printf("Este hilo trabajador escribió %i lineas en '%s'\n",lineas,o);
	pthread_exit(st);
}

int main(int argc, char *argv[]){
	pthread_t threads[argc];
	struct lista *ok=NULL;
	struct pasar *st;
	int rc,i,hilos;
	largo=malloc(sizeof(char*)*512);
	corto=malloc(sizeof(char*)*512);

	printf("\n");
	sem_init(&A,0,1);
	sem_init(&B,0,1);

	for(i=0;i<argc-1;i++){
		st=malloc(sizeof(struct pasar));
		st->s=argv[i+1];
	    rc = pthread_create(&threads[i], NULL, ordenar, (void *)st);
	    if (rc) {
		    printf("ERR; pthread_create() ret = %d\n", rc);
		    exit(-1);
	    }
	}
	for(i=0;i<argc-1;i++){
		pthread_join(threads[i],NULL);
	}

	printf("\n");
	pthread_t threads2[cuantos/2];
	while(cuantos>1){
		hilos=cuantos/2;
		for(i=0;i<hilos;i++){
		    rc = pthread_create(&threads2[i], NULL, merge,NULL);
		    if (rc) {
			    printf("ERR; pthread_create() ret = %d\n", rc);
			    exit(-1);
		    }
		}
		for(i=0;i<hilos;i++){
			pthread_join(threads2[i],NULL);
		}
	}
	printf("total %i\nlinea mas larga: %s\nlinea mas corta:%s\n",cantidad,largo,corto);
	
	rename(sortedArchivos->s,"sorted.txt");

	ok=temps;
    while(ok!=NULL){
    	struct lista *p=ok;
        char* k=malloc(sizeof(char*));
        strcpy(k,ok->s);
        remove(k);
        ok=ok->sig;
        p->sig=NULL;
        free(p);
	}
	
	return 0;
}
