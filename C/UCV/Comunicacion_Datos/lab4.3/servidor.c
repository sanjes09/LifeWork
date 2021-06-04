#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <dirent.h>

//#define PORT 3006
//#define BACKALOG 2 

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

void chatRoom(int new_id, int new_id2){
	char buffer1 [100];
	char buffer2 [100];

	send (new_id, "Ingrese su nombre: ",100,0);
	send (new_id2, "Ingrese su nombre: ",100,0);

	//LEE NOMBRES
	recv (new_id, buffer1, 100, 0);
	recv (new_id2, buffer2, 100, 0);
	//ENVIA NOMBRES
	send (new_id, buffer2,100,0);
	send (new_id2, buffer1,100,0);
	//EMPIEZA EL CHAT

	do{
		bzero(buffer1,100);
		bzero(buffer2,100);

		recv (new_id, buffer1, 100,0);
		send (new_id2, buffer1, 100,0);
		recv (new_id2, buffer2, 100, 0);
		send (new_id, buffer2,100,0);
		
	}while((strcmp(buffer1,"EXIT\n")!=0)||(strcmp(buffer2,"EXIT\n")!=0));
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

int main (int argc, char *argv[]){

	int id, opt, opt2, new_id, new_id2; //descriptores servidor, cliente
	int tam1,tam2,PORT;
	char buffer1 [100];
	char buffer2 [100];
	char opcion [10], opcion2 [10];

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
		
		//ACEPTA SEGUNDO CLIENTE
		tam2=sizeof(struct sockaddr_in);
		new_id2 = accept(id,(void *) &cliente2, &tam2); //si llega algo lo acepto
		if(new_id2<0){
			printf("Error al aceptar trafico\n");
			close(new_id2);
			return 1;
		}	
		printf("Se obtuvo conexion desde %s\n",inet_ntoa (cliente2.sin_addr));
		

		recv(new_id, opcion,1,0);
		opt=atoi(opcion);
		printf("%i\n",opt);
		switch(opt){
			case 1:	chatRoom(new_id, new_id2);
					break;
			case 2:	subirArchivo(new_id);
					break;
			case 3: descargarArchivo(new_id);
					break;
			case 5: serverArchivos(new_id);
					break;
			default: printf("opt invalido\n");
		}
		
		recv(new_id2, opcion2,1,0);
		opt2=atoi(opcion2);
		printf("%i\n",opt2);
		switch(opt2){
			case 1:
					break;
			case 2:	subirArchivo(new_id2);
					break;
			case 3: descargarArchivo(new_id2);
					break;
			case 5: serverArchivos(new_id2);
					break;
			default: printf("opt invalido\n");
		}
		
		close(new_id);
		close(new_id2);
	}
	close(id);
	return 0;
}