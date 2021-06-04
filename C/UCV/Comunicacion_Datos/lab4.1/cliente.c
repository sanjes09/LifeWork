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

	int id; //descriptores servidor, cliente
	char buffer[100] = "hola";
	struct sockaddr_in server; //inf servidor
	struct hostent *he; //lo necesitare para el nombre de host o la ip

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
	id=socket(AF_INET , SOCK_STREAM, 0);
	connect (id, (void *) &server, sizeof (server));
	
	//ya en teoria la conexion al server esta hecha
	do{
		scanf("%s",&buffer);
		write(id, buffer,100);
		read('1', buffer, 100);
	}while(strcmp(buffer,"EXIT")!=0);

	close(id);
	return 0;
}