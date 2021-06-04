#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

//#define PORT 3005
//#define BACKLOG 2

int main (int argc, char *argv[]){

	int id;//descriptores servidor, cliente
	char buffer[100] = "hola";
	char nombre[100],turno[100];
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
	//server.sin_addr.s_addr = inet_addr(argv[1]);
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
	//NOMBRE
	bzero(buffer,100);
	recv(id, turno, 100, 0);
	recv(id, buffer, 100, 0);
	printf("%s\n",buffer);
	bzero(buffer,100);
	fgets(buffer,100,stdin);
	//scanf("%s",&buffer);
	send (id, buffer,100,0);
	bzero(buffer,100);

	printf("esperando conexion...\n");
	recv(id, buffer, 100, 0);
	strcpy(nombre,buffer);
	printf("Conectado con: %s\n",nombre);
	//ya en teoria la conexion al server esta hecha
	if(strcmp(turno,"1")==0){
		do{
			printf("> ");
			bzero(buffer,100);
			fgets(buffer,100,stdin);
			//scanf("%s",&buffer);
			send (id, buffer,100,0);
			if(strcmp(buffer,"EXIT\n")!=0){
				bzero(buffer,100);
				recv(id, buffer, 100, 0);
				if(strcmp(buffer,"EXIT\n")!=0){
					printf("%s dice: %s\n",nombre,buffer);
				}
				else{
					printf("%s se ha desconectado.\n",nombre);
					bzero(buffer,100);
					strcpy(buffer,"EXIT\n");
					send (id, "EXIT\n",100,0);
				}
			}
		}while(strcmp(buffer,"EXIT\n")!=0);
	}else{
		do{
			bzero(buffer,100);
			recv(id, buffer, 100, 0);
			if(strcmp(buffer,"EXIT\n")!=0){
				printf("%s dice: %s\n",nombre,buffer);
				printf("> ");
				bzero(buffer,100);
				fgets(buffer,100,stdin);
				//scanf("%s",&buffer);
				send (id, buffer,100,0);
			}
			else{
				printf("%s se ha desconectado.\n",nombre);
				strcpy(buffer,"EXIT\n");
				send (id, buffer,100,0);
			}
		}while(strcmp(buffer,"EXIT\n")!=0);
	}
	close(id);
	return 0;
}