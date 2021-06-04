#include <iostream>
#include <fstream>
#include <string.h>
#include <stdlib.h>

using namespace std;

struct carro{
	string pla;
	string mar;
	string mod;
	string a;
	string pre;
};

int fact(long long x){
	long long i=x;
	while (i>1){
		i--;
		x=x*i;
	}
	return x;
}

void mostrar(long long a[]){
	int i;
	long cont,valor,mayor=a[1],menor=a[1];
	for (i=1;i<=8;i++){
		if (a[i]>mayor) mayor=a[i];
		if (a[i]<menor) menor=a[i];
	}
	cout <<"El mayor valor del arreglo B es: "<<mayor<<endl;
	cout <<"El menor valor del arreglo B es: "<<menor<<endl<<endl;
	for (i=1;i<=8;i++){
		if (a[i]==mayor) cout <<"En la posicion ["<<i<<"] hay valor igual al mayor"<<endl;
	}
	
}

void primo(long long n){
	long long x=1,cont=0;
	while (x<n){
		if ((n%x)==0) cont++;
		x++;
	}
	if (cont==1)	cout <<n<<" ";
}

void ejer1(){  
	long long i,j,a[8],b[8],n=0,s;
	int o,cont,cont1=0;
	cout <<"Ingrese los numeros para llenar el arreglo"<<endl;
	for (i=1;i<=8;i++){		//A
		do{
			cout <<i<<": ";			
			cin >>j;
			if(j<1 || j>10) cout <<"Numero invalido, debe ser entre 1 y 10"<<endl;
		}while (j<1 || j>10);
		a[i]=j;
	}
	for (i=1;i<=8;i++){		//1.1
		s=1;
		n++;
		for (j=1;j<=n;j++){
			s=s*a[j];
		}
		s=fact(s);
		b[i]=s;
	}
	mostrar(b);				//1.2 y 1.3
	for (i=1; i<=8;i++){	//1.4
		s=1;
		cout <<endl;
		cout <<"Del 1 al "<<b[i]<<" los numeros primos son: ";
		if (b[i]==1) cout <<"No hay"<<endl;		
		while(s<=b[i]){
		primo(s);
		s++;
		}
	}
	for (i=1; i<=8;i++){		//1.5
		cont=0;
		for(j=i;j<=8;j++){
			if (b[i]==b[j]) cont++;
		}
		if (cont > cont1){ o=i; cont1=cont;}
	}
	if (cont1==1) cout <<"No se repite ningun valor"<<endl;
	else cout <<"El valor que mas se repite es: "<<a[o]<<" y se repite "<<cont1<<" veces."<<endl;
}

void ejer2(){
	int x,y,i,j,n,in,in2;
	cout <<"Introduzca el N: ";
	cin>>n;
	fstream salida;
	salida.open("salidaEj2.txt",ios::out);
	int h[n][n];
	int k[n*2-1][n*2-1];
	
	for (i=0;i<n;i++){
		for (j=0;j<n;j++){
			cout <<"Introduzca un numero: ";
			cin>>h[i][j];
		}
	}
	for (i=0;i<(n*2-1);i++){		//LIMPIA 
		for (j=0;j<(n*2-1);j++){
			k[i][j]=0;
		}
	}
	
	in=n-1; in2=0;
	for (i=0;i<n;i++){
	    x=in; y=in2;
		for (j=0;j<n;j++){
		    k[x][y]=h[i][j];
		    x--; y++;
		}
		in++;
		in2++;
	}
	cout <<"Matriz Original"<<endl;	
	salida<<"Matriz Original"<<endl<<endl;
	for (i=0;i<n;i++){		//MOSTRAR
		for (j=0;j<n;j++){
			cout<<h[i][j]<<" ";
			salida<<h[i][j]<<" ";
		}
		cout<<endl;
		salida<<endl;
	}
	salida<<endl;
	cout<<"Invertida"<<endl;
	salida<<"Matriz Invertida"<<endl<<endl;
	for (i=0;i<(n*2-1);i++){		//MOSTRAR
		for (j=0;j<(n*2-1);j++){
			cout<<k[i][j]<<" ";
			salida<<k[i][j]<<" ";
		}
		cout <<endl;
		salida<<endl;
	}
}

void ejer3(){
	float a[20];
	int a1[20], a2[20];
	int i,j,m,n;
	cout <<"Ingrese las temperaturas para llenar el arreglo"<<endl;
	for (i=0;i<20;i++){	//CARGAR
		cin>>a[i];
		a1[i]=i;
		a2[i]=i;
	}
	
	for (i=0;i<20;i++){   //SELECCION
	    m=i;
		for (j=i+1;j<20;j++){
			if(a[(a1[j])]<a[(a1[m])]) m=j;
		}
		swap(a1[i],a1[m]);
	}
	n=20;
	while(n>1){		//BURBUJA
		for(j=0;j<n;j++){
			if(a[a2[j]]<a[a2[j+1]])	swap(a2[j],a2[j+1]);
		}
	
		n--;
	}
	cout<<endl;
	for (i=0;i<20;i++){
		cout <<i<<":	";
		cout <<"A:"<<a[i];
		cout <<"	A1: "<<a1[i];
		cout <<"	A2: "<<a2[i]<<endl;
	}
}

void ejer4(){
	int a,a1,i,A,y;
	float pre,pre1,P;
	carro m;
	y=0;
	fstream entrada,salida;
	entrada.open("carrosEj4.bin",ios::in);
	
	cout <<"Introduza rango de precio y año"<<endl;
	cout <<"Precio"<<endl<<"Desde: ";
	cin >>pre;
	cout <<"Hasta: ";
	cin >>pre1;
	cout <<"Año"<<endl<<"Desde: ";
	cin >>a;
	cout <<"Hasta: ";
	cin >>a1;
	
	entrada>>m.pla; entrada>>m.mar;	entrada>>m.mod;	entrada>>m.a; entrada>>m.pre;
	
	if(entrada.eof()) cout <<"Archivo vacio"<<endl;
	else{
		while(!entrada.eof()){
			A=atoi(m.a.c_str());
			P=atof(m.pre.c_str());
			if(((A>=a)&&(A<=a1))&&((P>=pre)&&(P<=pre1))) y++;
			entrada>>m.pla; entrada>>m.mar;	entrada>>m.mod;	entrada>>m.a; entrada>>m.pre;
		}
		entrada.close();
		entrada.open("carrosEj4.bin",ios::in);
		carro h[y];
		entrada>>m.pla;	entrada>>m.mar;	entrada>>m.mod;	entrada>>m.a; entrada>>m.pre;
		i=0;
		while(!entrada.eof()){
		
			A=atoi(m.a.c_str());
			P=atof(m.pre.c_str());
			if(((A>=a)&&(A<=a1))&&((P>=pre)&&(P<=pre1))) {
				h[i].pla=m.pla;
				h[i].mar=m.mar;
				h[i].mod=m.mod;
				h[i].a=m.a;
				h[i].pre=m.pre;
				i++;
			}
			entrada>>m.pla;	entrada>>m.mar;	entrada>>m.mod;	entrada>>m.a; entrada>>m.pre;
		}
		entrada.close();

		//		1.3
		for (i=0;i<y;i++){
		    int j,k=i;
			for (j=i+1;j<y;j++){
				if((atof(h[j].pre.c_str()))<(atof(h[k].pre.c_str()))) k=j;
			}
			swap(h[i],h[k]);
		}

	cout <<endl<<"Busqueda de carros entre "<<a<<" y "<<a1<<", y precio entre "<<pre<<" y "<<pre1<<endl<<endl;    

	//		1.2
	salida.open("opcionesEj4.bin",ios::out);
	salida<<"Busqueda de carros entre "<<a<<" y "<<a1<<", y precio entre "<<pre<<" y "<<pre1<<endl;
	for (i=0; i<y;i++){
			salida <<h[i].pla<<" "<<h[i].mar<<" "<<h[i].mod<<" "<<h[i].a<<" "<<h[i].pre<<endl;
	}
	salida.close();
	//		1.4 
	cout <<"El vehiculo mas economico:"<<endl;
	cout <<h[0].pla<<" "<<h[0].mar<<" "<<h[0].mod<<" "<<h[0].a<<" "<<h[0].pre<<endl;
	cout <<"El vehiculo mas caro:"<<endl;
	cout <<h[y-1].pla<<" "<<h[y-1].mar<<" "<<h[y-1].mod<<" "<<h[y-1].a<<" "<<h[y-1].pre<<endl;

	}
}

void ejer5(){
	fstream entrada,salida;
	entrada.open("letrasEj5.txt",ios::in);
	int total=0,i,cont;
	char c,s,m;
	entrada>>c;
	if (entrada.eof()) cout <<"Archivo vacio"<<endl;
	else{
		while (!entrada.eof()){
			total++;
			entrada>>c;
		}
		entrada.close();
		char a[total];
		entrada.open("letrasEj5.txt",ios::in);
		entrada>>c;
		i=0;
		while (!entrada.eof()){
			a[i]=c;
			i++;		
			entrada>>c;	
		}
		i=0;
		int b[total];
		for (i=0;i<total;i++){
			cont=0;
			for (int j=0;j<total;j++){
				if (a[i]==a[j]) cont++;
			}
			b[i]=cont;
			cout <<"La letra: "<<a[i]<<endl<<"Se repite "<<cont<<" veces."<<endl<<"Su frecuencia de aparicion es: "<<cont*1.0/total<<endl<<endl;
		}
	}
}

int main (){
	int op=-1;
	while (op!=0){
		cout <<endl;
		cout <<"**********Menu principal**********"<<endl;
		cout <<"	1. Ejercicio 1"<<endl;
		cout <<"	2. Ejercicio 2"<<endl;
		cout <<"	3. Ejercicio 3"<<endl;
		cout <<"	4. Ejercicio 4"<<endl;
		cout <<"	5. Ejercicio 5"<<endl;
		cout <<"	0. Salir"<<endl;
		cin >>op;
		switch (op){
			case 1: ejer1();
							break;
			case 2: ejer2();
							break;
			case 3: ejer3();
							break;
			case 4: ejer4();
							break;
			case 5: ejer5();
							break;
		}
	}
}
