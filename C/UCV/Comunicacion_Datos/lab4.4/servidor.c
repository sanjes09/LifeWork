#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <dirent.h>

sem_t archivos;

struct ids{
	int *id1;
	int *id2;
};

void serverArchivos(int id){
	char buffer[100];
	DIR *folder;
	struct dirent *archivo;

	folder=opendir("Server");
	
	if (folder == NULL) printf("No puedo abrir el directorio");
	else{
		while ((archivo = readdir(folder))){
			if ((strcmp(archivo->d_name,".")!=0)&&(strcmp(archivo->d_name,"..")!=0)){
				bzero(buffer,100);
				strcpy(buffer,archivo->d_name);
				send(id,buffer,100,0);
			}
		}
		bzero(buffer,100);
		strcpy(buffer,"0");
		send(id,buffer,100,0);
	}

}

void archivar(int new_id, int new_id2){
	char buffer1[100];
	char buffer2[100];
	recv (new_id, buffer1, 100,0);
	send (new_id2, buffer1, 100,0);
	bzero(buffer1,100);
	bzero(buffer2,100);

	recv (new_id, buffer2, 1,0);
	send (new_id2, buffer2, 1,0);
	while(strcmp(buffer2,"0")!=0){
		recv (new_id, buffer1, 1,0);
		send (new_id2, buffer1, 1,0);
		recv (new_id, buffer2, 1,0);
		send (new_id2, buffer2, 1,0);
	}
}

void* hilo(void* n){
	struct ids *i=(struct ids*)n;
	int new_id=*i->id1;
	int new_id2=*i->id2;
	char buffer1 [100];

	while(strcmp(buffer1,"EXIT\n")!=0){
		recv (new_id, buffer1, 100,0);
		send (new_id2, buffer1, 100,0);
		if(strcmp(buffer1,"ENVIAR\n")==0)
			archivar(new_id,new_id2);	
	}

}

void chatRoom(int new_id, int new_id2){
	char buffer1 [100];
	char buffer2 [100];
	pthread_t c1,c2;
	struct ids *i,*j;
	i=malloc(sizeof(struct ids));
	j=malloc(sizeof(struct ids));

	send (new_id, "Ingrese su nombre: ",100,0);
	send (new_id2, "Ingrese su nombre: ",100,0);
	//LEE NOMBRES
	recv (new_id, buffer1, 100, 0);
	recv (new_id2, buffer2, 100, 0);
	//ENVIA NOMBRES
	send (new_id, buffer2,100,0);
	send (new_id2, buffer1,100,0);
	//EMPIEZA EL CHAT
	i->id1=&new_id;
	i->id2=&new_id2;
	j->id2=&new_id;
	j->id1=&new_id2;
	pthread_create(&c1, NULL, hilo, (void *)i);
	pthread_create(&c2, NULL, hilo, (void *)j);
	pthread_join(c1,NULL);
	pthread_join(c2,NULL);
}

void subirArchivo(int id){
	char archivo[100];
	char buffer[100];
	char buffer2[100];
	bzero(buffer,100);
	bzero(buffer2,100);
	bzero(archivo,100);
	int recibido=1;
	FILE *f1;

	recv(id, buffer, 100, 0);
	strcat(archivo,"Server/");
	strcat(archivo,buffer);
	bzero(buffer,100);

	f1 = fopen (archivo, "wb");
	recv(id, buffer2, 1, 0);

	while(strcmp(buffer2,"0")!=0){
		recv(id, buffer, 1, 0);
		fwrite(buffer,sizeof(char),1,f1);
		recv(id, buffer2, 1, 0);
	}

	fclose(f1);
}

void descargarArchivo(int id){
	char archivo[100];
	char buffer[100];
	char buffer2[100];
	bzero(buffer,100);
	bzero(buffer2,100);
	bzero(archivo,100);
	int recibido=1;
	FILE *f1;

	serverArchivos(id);

	recv(id, buffer, 100, 0);
	strcat(archivo,"Server/");
	strcat(archivo,buffer);

	printf("%s\n",archivo);
	sleep(1);

	f1 = fopen (archivo, "rb");

	while(!feof(f1)){
		send(id,"1",1,0);
		bzero(buffer,100);
		fread(buffer,sizeof(char),1,f1);
		send(id,buffer,1,0);
	}

	send(id,"0",1,0);

	fclose(f1);
}

void sincronizarArchivos(int id){
	char buffer[100];
	char archivo2[100];
	char ciclo[100];
	FILE *f1;
	DIR *folder;
	struct dirent *archivo;

	folder=opendir("Server");
	
	if (folder == NULL) printf("No puedo abrir el directorio");
	else{
		while ((archivo = readdir(folder))){
			if ((strcmp(archivo->d_name,".")!=0)&&(strcmp(archivo->d_name,"..")!=0)){
				send(id,"archivo",100,0);
				printf("manda %s\n",archivo->d_name);

				bzero(buffer,100);
				strcpy(buffer,archivo->d_name);
				send(id,buffer,100,0);
				recv(id,ciclo,100,0);
				printf("recibe %s\n",buffer);
				if(strcmp(ciclo,"NO")==0){
					strcpy(archivo2,"Server/");
					strcat(archivo2,buffer);
					printf("archivo:%s\n",archivo2);						
					f1=fopen(archivo2,"rb");
					printf("abre\n");
					while(!feof(f1)){
						send(id,"1",1,0);
						bzero(buffer,100);
						fread(buffer,sizeof(char),1,f1);
						send(id,buffer,1,0);
					}
					send(id,"0",1,0);

					fclose(f1);
				}
			}
		}
		send(id,"no",100,0);
	}
}

int main (int argc, char *argv[]){

	int id, opt, opt2, new_id, new_id2;
	int *newid;
	int *newid2; //descriptores servidor, cliente
	int tam1,tam2,PORT;
	char buffer1 [100];
	char buffer2 [100];
	char opcion [10], opcion2 [10];
	int ch1=0, ch2=0;
	pthread_t c1;
	pthread_t c2;
	sem_init(&archivos,0,1);

	if(argc != 2){
        printf("cantidad de parametros invalido \n");
        return 1;
    } 

	PORT=atoi(argv[1]);
	struct sockaddr_in server; //inf servidor
	struct sockaddr_in cliente1; //inf cliente1
	struct sockaddr_in cliente2; //inf cliente2
	//estructura server del SERVIDOR
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(PORT); //el puerto que usare
	bzero(&(server.sin_zero),8);

	//socket del servidor
	if((id=socket(AF_INET , SOCK_STREAM, 0))<0){
		printf("\nError no se pudo crear el socket \n");
        return 1;
	}

	if(bind (id, (void *) &server, sizeof (server))<0){ //aqui esta la estructura para decir donde voy a funcionar
		printf("Error al asociar el puerto a la conexion\n");
	    close(id);
	    return 1;
	}listen (id, 2);//comienzo a escuchar

	while(1){
		//ACEPTA PRIMER CLIENTE
		tam1=sizeof(struct sockaddr_in);
		new_id = accept(id,(void *) &cliente1, &tam1); //si llega algo lo acepto
		if(new_id<0){
			printf("Error al aceptar trafico\n");
			close(new_id);
			return 1;
		}	
		printf("Se obtuvo conexion desde %s\n",inet_ntoa (cliente1.sin_addr));

		int* h=&new_id;
		//pthread_create(&c1, NULL, hilo, (void *)h);

		//ACEPTA SEGUNDO CLIENTE
		tam2=sizeof(struct sockaddr_in);
		new_id2 = accept(id,(void *) &cliente2, &tam2); //si llega algo lo acepto
		if(new_id2<0){
			printf("Error al aceptar trafico\n");
			close(new_id2);
			return 1;
		}	
		printf("Se obtuvo conexion desde %s\n",inet_ntoa (cliente2.sin_addr));
		
		int* h2=&new_id;
		/*pthread_create(&c2, NULL, hilo, (void *)h2);
		
		pthread_join(c1, NULL);
		pthread_join(c2, NULL);*/

		do{
			if(ch1!=1){
				recv(new_id, opcion,1,0);
				opt=atoi(opcion);
				printf("opt %i\n",opt);
				switch(opt){
					case 1:	ch1=1;
							send(new_id,"Esperando conexion...",100,0);
							break;
					case 2:	subirArchivo(new_id);
							break;
					case 3: descargarArchivo(new_id);
							break;
					case 5: serverArchivos(new_id);
							break;
					case 7: sincronizarArchivos(new_id);
							break;
					case 0: break;
					default: printf("opt invalido\n");
				}
			}
			if(ch2!=1){
				recv(new_id2, opcion2,1,0);
				opt2=atoi(opcion2);
				printf("opt2 %i\n",opt2);
				switch(opt2){
					case 1:	ch2=1;
							send(new_id2,"Esperando conexion...",100,0);
							break;
					case 2:	subirArchivo(new_id2);
							break;
					case 3: descargarArchivo(new_id2);
							break;
					case 5: serverArchivos(new_id2);
							break;
					case 7: sincronizarArchivos(new_id2);
							break;
					case 0: break;
					default: printf("opt invalido\n");
				}
			}
			if(ch1==1&&ch2==1){
				chatRoom(new_id,new_id2);
				ch1=0; ch2=0;
			}
		}while(opt!=0&&opt!=0);
		close(new_id);
		close(new_id2);
	}
	close(id);
	return 0;
}