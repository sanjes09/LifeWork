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

	int id, id2, new_id, new_id2; //descriptores servidor, cliente
	int tam1,tam2,PORT;
	char buffer1 [100];
	char buffer2 [100];

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
		send (new_id, "1",100,0);
		send (new_id, "Ingrese su nombre: ",100,0);

		//ACEPTA SEGUNDO CLIENTE
		tam2=sizeof(struct sockaddr_in);
		new_id2 = accept(id,(void *) &cliente2, &tam2); //si llega algo lo acepto
		if(new_id2<0){
			printf("Error al aceptar trafico\n");
			close(new_id2);
			return 1;
		}	
		printf("Se obtuvo conexion desde %s\n",inet_ntoa (cliente2.sin_addr));
		send (new_id2, "2",100,0);
		send (new_id2, "Ingrese su nombre: ",100,0);
		//LEE NOMBRES
		recv (new_id, buffer1, 100, 0);
		recv (new_id2, buffer2, 100, 0);
		//ENVIA NOMBRES
		send (new_id, buffer2,100,0);
		send (new_id2, buffer1,100,0);
		//EMPIEZA EL CHAT
		do{
			recv (new_id, buffer1, 100,0);
			send (new_id2, buffer1,100,0);
			recv (new_id2, buffer2, 100, 0);
			send (new_id, buffer2,100,0);
		}while((strcmp(buffer1,"EXIT\n")!=0)||(strcmp(buffer2,"EXIT\n")!=0));
		printf("conexion Terminada.\n");
		close(new_id);
		close(new_id2);
	}
	close(id);
	return 0;
}