#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <dirent.h>

void misArchivos(){
	char buffer[100];
	DIR *folder1;
	struct dirent *archivo1;

	folder1=opendir("Client1");
	
	if (folder1 == NULL) printf("No puedo abrir el directorio");
	else{
		while ((archivo1 = readdir(folder1))) if ((strcmp(archivo1->d_name,".")!=0)&&(strcmp(archivo1->d_name,"..")!=0)) printf("%s\n",archivo1->d_name);
	}
}

void serverArchivos(int id,int op){
	char buffer2[100];
	char buffer[100];

	if(op==0){
		strcpy(buffer,"5");
		send (id, buffer,100,0);
	}

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

	strcat(archivo,"Client1/");
	strcat(archivo,buffer);

	f1 = fopen (archivo, "rb");

	while(!feof(f1)){
		send(id,"1",1,0);
		bzero(buffer,100);
		fread(buffer,sizeof(char),1,f1);
		send(id,buffer,1,0);
	}
	send(id,"0",1,0);
	printf("archivo enviado.\n");
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

	serverArchivos(id,1);

	printf("\n¿que archivo va a descargar?\n");
	scanf("%s",&buffer);	
	send(id,buffer,100,0);

	strcat(archivo,"Client1/");
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

void chatRoom(int id){

	char buffer2[100],buffer[100];
	char nombre[100];

	//NOMBRE
	bzero(buffer,100);
	bzero(buffer2,100);

	recv(id, buffer2, 100, 0);
	printf("%s\n",buffer2);
	fgets(buffer,100,stdin);
	//scanf("%s",&buffer);
	fflush(stdin);
	send (id, buffer,100,0);
	
	bzero(buffer,100);
	bzero(buffer2,100);

	printf("esperando conexion...\n");
	recv(id, buffer2, 100, 0);
	strcpy(nombre,buffer2);
	printf("Conectado con: %s\n",nombre);
	//ya en teoria la conexion al server esta hecha

	do{
		bzero(buffer,100);
		bzero(buffer2,100);
		printf("> ");
		fgets(buffer,100,stdin);
		fflush(stdin);
		send (id, buffer,100,0);
		recv (id, buffer2,100,0);	

		if(strcmp(buffer2,"EXIT\n")!=0){
			printf("%s dice: %s\n",nombre,buffer2);
		}
		else{
			printf("%s se ha desconectado.\n",nombre);;
		}

	}while(strcmp(buffer,"EXIT\n")!=0&&strcmp(buffer2,"EXIT\n")!=0);
}

int main (int argc, char *argv[]){

	int id;//descriptores servidor, cliente
	int menu;
	struct sockaddr_in server; //inf servidor
	struct hostent *he; //lo necesitare para el nombre de host o la ip


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
		printf("0. Salir\n");
		scanf("%i",&menu);
		printf("%i\n",menu);
		sleep(1);
		switch(menu){
			case 1: send(id,"1",1,0);
					chatRoom(id);
					break;
			case 2: send (id, "2",1,0);
					subirArchivo(id);
					break;
			case 3: send (id, "3",1,0);
					descargarArchivo(id);
					break;
			case 4: misArchivos();
					break;
			case 5: send (id, "5",1,0);
					serverArchivos(id,0);
					break;
			default: printf("opcion invalida\n");
		}
	}while(menu!=0);
	close(id);
	return 0;
}