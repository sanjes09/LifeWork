#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int i;
void *buscar(void *n){
    char s[100];
    int j,cont=0;
    char *c=(char *) n;
    scanf("%s",&s);
    for(j=0;j<sizeof(s);j++){
        if(s[j]==*c) {
        	cont++;
        }
    }
    printf("Thread %i:",i);
    printf("la letra '%s' aparece %i veces en '%s'\n",c,cont,s);
    pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	pthread_t threads[argc];
	char p[2];
	int rc;
	for(i=0;i<argc-1;i++){
		
	    rc = pthread_create(&threads[i], NULL, buscar,(void *) argv[i+1]);
	    if (rc) {
		    printf("ERR; pthread_create() ret = %d\n", rc);
		    exit(-1);
	    }
	    pthread_join(threads[i],NULL);
	}

    
	return 0;
}
