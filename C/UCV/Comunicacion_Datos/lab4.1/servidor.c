#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>

//#define PORT 3006
//#define BACKALOG 2 

int main (int argc, char *argv[]){

	int id, new_id; //descriptores servidor, cliente
	int tam,PORT;
	char buffer [100];
	PORT=atoi(argv[1]);
	struct sockaddr_in server; //inf servidor
	struct sockaddr_in client; //inf cliente
	//estructura server del SERVIDOR
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(PORT); //el puerto que usare
	bzero(&(server.sin_zero),8);

	//socket del servidor
	id = socket(AF_INET, SOCK_STREAM, 0);
	bind (id, (void *) &server, sizeof (server)); //aqui esta la estructura para decir donde voy a funcionar
	listen (id, 2);//comienzo a escuchar
	
	while(1){

		tam=sizeof(struct sockaddr_in);
		new_id = accept(id,(void *) &client, &tam); //si llega algo lo acepto
		printf("Se obtuvo conexion desde %s\n",inet_ntoa (client.sin_addr));
		do{
			read (new_id, buffer, 100);
			write ('1', buffer, 100);
			printf("\n");
			printf("Mensaje del Servidor: %s\n", buffer);
		}while(strcmp(buffer,"EXIT")!=0);
	}
	close(new_id);
	return 0;
}