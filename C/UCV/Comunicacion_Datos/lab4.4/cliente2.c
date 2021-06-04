#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <pthread.h>
#include <unistd.h>
#include <dirent.h>

void misArchivos(){
	char buffer[100];
	DIR *folder1;
	struct dirent *archivo1;

	folder1=opendir("Client2");
	
	if (folder1 == NULL) printf("No puedo abrir el directorio");
	else{
		while ((archivo1 = readdir(folder1))) if ((strcmp(archivo1->d_name,".")!=0)&&(strcmp(archivo1->d_name,"..")!=0)) printf("%s\n",archivo1->d_name);
	}
}

void serverArchivos(int id){
	char buffer2[100];
	char buffer[100];

	bzero(buffer2,100);
	recv(id, buffer2, 100, 0);

	while(strcmp(buffer2,"0")!=0){
		printf("%s\n",buffer2);
		bzero(buffer2,100);
		recv(id, buffer2, 100, 0);
	}
}

void subirArchivo(int id){
	char archivo[100];
	char buffer[100];
	char buffer2[100];
	bzero(buffer,100);
	bzero(buffer2,100);
	bzero(archivo,100);
	FILE *f1;

	misArchivos();

	printf("\n¿que archivo va a subir?\n");
	scanf("%s",&buffer);	
	send(id,buffer,100,0);

	strcat(archivo,"Client2/");
	strcat(archivo,buffer);

	f1 = fopen (archivo, "rb");

	while(!feof(f1)){
		send(id,"1",1,0);
		bzero(buffer,100);
		fread(buffer,sizeof(char),1,f1);
		send(id,buffer,1,0);
	}
	send(id,"0",1,0);
	printf("Archivo enviado.\n");
	fclose(f1);
}

void recibirArchivo(int id){
	char archivo[100];
	char buffer[100];
	char buffer2[100];
	bzero(buffer,100);
	bzero(buffer2,100);
	bzero(archivo,100);
	FILE *f1;

	printf("\n");
	
	recv(id,buffer,100,0);

	strcat(archivo,"Client2/");
	strcat(archivo,buffer);
	bzero(buffer,100);

	f1 = fopen (archivo, "wb");
	recv(id, buffer2, 1, 0);
	while(strcmp(buffer2,"0")!=0){
		recv(id, buffer, 1, 0);
		fwrite(buffer,sizeof(char),1,f1);
		recv(id, buffer2, 1, 0);
	}
	printf("Archivo recibido.\n");

	fclose(f1);
}

void descargarArchivo(int id){
	char archivo[100];
	char buffer[100];
	char buffer2[100];
	bzero(buffer,100);
	bzero(buffer2,100);
	bzero(archivo,100);
	FILE *f1;

	printf("\n");

	serverArchivos(id);

	printf("\n¿que archivo va a descargar?\n");
	scanf("%s",&buffer);	
	send(id,buffer,100,0);

	strcat(archivo,"Client2/");
	strcat(archivo,buffer);
	bzero(buffer,100);

	f1 = fopen (archivo, "wb");
	recv(id, buffer2, 1, 0);
	while(strcmp(buffer2,"0")!=0){
		recv(id, buffer, 1, 0);
		fwrite(buffer,sizeof(char),1,f1);
		recv(id, buffer2, 1, 0);
	}
	printf("descarga completa.\n");

	fclose(f1);
}

void* hilo(void* n){
	int id=*((int *)n);
	char buffer[100];
	while(strcmp(buffer,"EXIT\n")!=0){
		bzero(buffer,100);
		fgets(buffer,100,stdin);
		//scanf("%s",&buffer);
		fflush(stdin);
		send (id, buffer,100,0);
		if(strcmp(buffer,"ENVIAR\n")==0)
			subirArchivo(id);
	}
	printf("se ha desconectado.\n");
}

void chatRoom(int id){

	char buffer2[100],buffer[100];
	char nombre[100];
	pthread_t c1;
	int* id2=&id;

	//NOMBRE
	bzero(buffer,100);
	bzero(buffer2,100);

	recv(id, buffer2, 100, 0);
	printf("%s\n",buffer2);
	fflush(stdin);
	//fgets(buffer,100,stdin);
	scanf("%s",&buffer);
	fflush(stdin);
	send (id, buffer,100,0);
	
	bzero(buffer,100);
	bzero(buffer2,100);

	printf("esperando a la otra persona...\n");
	recv(id, buffer2, 100, 0);
	strcpy(nombre,buffer2);
	printf("\nConectado con: %s\n",nombre);
	printf("\nPuede enviar archivos con el comando ENVIAR.\n");

	pthread_create(&c1, NULL, hilo, (void *)id2);
	do{
		bzero(buffer,100);
		recv(id, buffer, 100,0);
		if(strcmp(buffer,"EXIT\n")!=0){
			if(strcmp(buffer,"ENVIAR\n")==0)
				recibirArchivo(id);
			else 
				printf("\n%s dice: %s",nombre,buffer);
		}else{
			printf("\n%s se ha desconectado\n",nombre);
		}
	}while(strcmp(buffer,"EXIT\n")!=0);
	pthread_join(c1,NULL);
}

void eliminarArchivos(){
	char buffer[100];
	char archivo[100];
	bzero(buffer,100);
	bzero(archivo,100);

	misArchivos();

	printf("\n¿que archivo va a eliminar?\n");
	scanf("%s",&buffer);	

	strcat(archivo,"Client2/");
	strcat(archivo,buffer);

	remove(archivo);

}

void sincronizarArchivos(int id){
	char buffer[100];
	char buffer2[100];
	char ciclo[100];
	char archivo[100];
	FILE *f1;

	recv(id,ciclo,100,0);

	while(strcmp(ciclo,"archivo")==0){
		recv(id,buffer,100,0);
		strcpy(archivo,"Client2/");
		strcat(archivo,buffer);

		int val=open(archivo,O_RDONLY);
		if(val <0){
			send(id,"NO",100,0);
			f1 = fopen (archivo, "wb");
			recv(id, buffer2, 1, 0);
			while(strcmp(buffer2,"0")!=0){
				recv(id, buffer, 1, 0);
				fwrite(buffer,sizeof(char),1,f1);
				recv(id, buffer2, 1, 0);
			}

			fclose(f1);
		}else send(id,"SI",100,0);
		recv(id,ciclo,100,0);
	}

	printf("sincronizacion completa.\n");
}

int main (int argc, char *argv[]){

	int id;//descriptores servidor, cliente
	int menu;
	struct sockaddr_in server; //inf servidor
	struct hostent *he; //lo necesitare para el nombre de host o la ip
	char buffer2[100];


	if(argc != 3){
        printf("cantidad de parametros invalido \n");
        return 1;
    } 
	//NECESARIO PARA OBTENER LA IP
	
	if ((he=gethostbyname(argv[1]))==NULL){
		printf("error de gethostbyname()\n");
	}
	int puerto = atoi(argv[2]); //el segundo argumento
	
	//estructura del server del SERVIDOR
	server.sin_family = AF_INET;
	server.sin_addr = *((struct in_addr *)he->h_addr);
	server.sin_port= htons(puerto); //el puerto que usare (el segundo argumento de la llamada)
	
	bzero(&(server.sin_zero),8);

	//socket del cliente
	if((id=socket(AF_INET , SOCK_STREAM, 0))<0){
		printf("\nError no se pudo crear el socket \n");
        return 1;
	}

	if(connect (id, (void *) &server, sizeof (server))<0){
		printf("\n Fallo en conexion\n");
        return 1;
	}
	do{
		printf("\n");
		printf("****----MENU----****\n");
		printf("1. Chat room\n");
		printf("2. Subir archivo\n");
		printf("3. Descargar archivo\n");
		printf("4. Listar archivos mios\n");
		printf("5. Listar archivos en el servidor\n");
		printf("6. Eliminar archivos\n");
		printf("7. Sincronizar con servidor\n");
		printf("0. Salir\n");
		scanf("%i",&menu);
		printf("\n");
		switch(menu){
			case 1: send(id,"1",1,0);
					recv(id, buffer2, 100, 0);
					printf("%s\n",buffer2);
					chatRoom(id);
					break;
			case 2: send (id,"2",1,0);
					printf("Archivos:\n");
					subirArchivo(id);
					break;
			case 3: send (id,"3",1,0);
					printf("Archivos:\n");
					descargarArchivo(id);
					break;
			case 4: printf("Archivos:\n");
					misArchivos();
					break;
			case 5: send (id,"5",1,0);
					printf("Archivos:\n");
					serverArchivos(id);
					break;
			case 6: printf("Archivos: \n");
					eliminarArchivos();
					break;
			case 7: send(id,"7",1,0);
					sincronizarArchivos(id);
					break;
			case 0: break;
			default: printf("opcion invalida\n");
		}
	}while(menu!=0);
	close(id);
	return 0;
}