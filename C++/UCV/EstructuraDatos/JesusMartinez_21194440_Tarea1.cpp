#include <iostream>
#include <stdlib.h>
using namespace std;

void imprimir(float arr[],int n){
	for(int i=0;i<n;++i){
		if(arr[i]!=0)
			cout<<arr[i]<<" ";
	}
	cout<<endl;
}

void distribuir(float fin[],float a[], float b[], int n, float &peso){
	float x=0,y=0,p=0;
	float iz[n], der[n];
	int cont=n;
	for(int i=0;i<n;i++){
		iz[i]=fin[i];
		der[i]=0;
	}
	do{
		x=0; y=0;
		for(int i=0;i<n;i++){		//saca el peso total de cada cabina
			x=x+iz[i];
			y=y+der[i];
		}
		p=abs(x-y);
		if(p<peso){					//compara si el nuevo peso es menor para guardar la solucion
			peso=p;
			for(int i=0;i<n;i++){
				a[i]=iz[i];
				b[i]=der[i];
			}
		}
		else{						//pasa un numero de un arreglo a otro para mejor distribucion
			der[cont]=iz[cont];
			iz[cont]=0;
			cont--;
		}
	}while(cont>=0);
}

void ordenar(bool act[],float obj[],float fin[],int n,int s,float a[],float b[],float &peso){ 	// act[]=arreglo booleano para los numeros tomados, inicializado en F
	for(int i=0;i<n;++i){																		// obj[]=arreglo de los pesos																								
		if(act[i]==false){																		// fin[]=arreglo de cada solucion
			fin[s]=obj[i];																		// n=dimension del arreglo
			act[i]=true;																		// s=contdor para el arreglo booleano
			s++;																				// a[]=arreglo de cabina 1
			if(s>=n){																			// b[]=arreglo de cabina 2
				distribuir(fin,a,b,n,peso);														// peso=valor minimo de la diferencia de pesos de las cabinas
			}else{
				ordenar(act,obj,fin,n,s,a,b,peso);	
			}
			act[i]=false;
			fin[s]=0;
			s--;
		}
	}
}

int main(){
	int n;
	float x;
	float peso=0;
	
	do{
		cout<<"Cuantos objetos se van a distribuir?"<<endl;
		cin>>n;
	}while((n<1)||(n>15));

	float obj[n];		//creacion de los arreglos a utilizar
	float fin[n];
	float a[n];
	float b[n];
	bool act[n];
	cout<<"Ingrese los pesos de los objetos"<<endl;
	for(int i=0;i<n;++i){
		act[i]=false;   //limpia el arreglo de validacion
		fin[i]=-1;		//limpia el arreglo final
		a[i]=0;
		b[i]=0;
		do{
			cin>>x;
		}while(x<=0);
		obj[i]=x;		//inicializo obj con los pesos de los objetos
		peso=peso+x;	//inicializo peso con la suma total de los pesos
	}
	ordenar(act,obj,fin,n,0,a,b,peso);		// valores iniciales:
	cout<<"cabina a: ";						// act[]= todo en F
	imprimir(a,n);							// obj[]= todos los pesos ingresados
	cout<<"cabina b: ";						// fin[]= arreglo vaco iicializado todo en 0
	imprimir(b,n);							// n= valor leido por consola
	return 0;								// a[],b[] y peso valores inicializados previamente
} 
