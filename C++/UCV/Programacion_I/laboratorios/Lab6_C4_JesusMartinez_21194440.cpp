#include <iostream>
#include <math.h>
#include <fstream>
#include <stdlib.h>

using namespace std;

class producto{
private:
	int codigo;
	string des;
	float precio;
	int d; 
	int m;       
	int a;
public:
	
	void cargarP(int cod, string s, float pre, int dd, int mm, int aa){
		codigo=cod;
		des=s;
		precio=pre;
		d=dd;
		m=mm;
		a=aa;				
	}

	int getCodigo(){
		return codigo;
	}
	
	
	string getDes(){
		return des;
	}
	
	
	float getPrecio(){
		return precio;
	}
	
	void setPrecio(float x){
		precio=x;
	}
	
	int getD(){
		return d;
	}
	
	
	int getM(){
		return m;
	}
	
	
	int getA(){
		return a;
	}
	
	void mostrar(){
		cout <<"Codigo: "<<codigo<<endl;
		cout <<"Descripcion: "<<des<<endl;
		cout <<"Precio: "<<precio<<endl;
		cout <<"Fecha: "<<d<<"/"<<m<<"/"<<a<<endl<<endl;
	}
};

class monedero{
private:
	float dinero;

public:
	monedero(float x){
		dinero=x;
	}
	
	void deposito(float x){
		dinero+=x;
	}
	
	void retiro(float x){
		if (x>dinero) cout <<"Saldo insuficiente"<<endl;
		else dinero-=x;
	}
	
	float consulta(){
		return dinero;
	}
};

class recurso{
protected:
	string titulo;
	string autor;
	string ubicacion;
	string cota;
	int cantidad;
public:
	
};

class libro : public recurso{
private:
	int aa;
	string edit;	
};

class revista : public recurso{
private:
	int aa;
	int volumen;
	int numero;
};

class publicacion : public recurso{
private:
	int dd;
	int mm;
	int aa;
	string escuela;
};

class trabajo : public recurso{
private:
	int dd;
	int mm;
	int aa;
	string escuela;
	string tutor[2];
	string jurado[3];
};
	
int validar(producto p,producto x){
	int h,g;
	h=p.getA()*100+p.getM()*10+p.getD();
	g=x.getA()*100+x.getM()*10+x.getD();
	if(h<g) return 1;
	else return 0;
}

void ordenar(producto p[]){
		int i,m,j;
		
		for (i=0;i<3;i++){
	    m=i;
			for (j=i+1;j<3;j++){
				if(validar(p[j],p[m])) m=j;
			}
			swap(p[i],p[m]);
	}
}

void ejer1(){
	producto p[3];
	int i,d,m,a,cod,iva;
	float precio;
	string s;
	for (i=0; i<3; i++){
		cod=rand() % 99999 + 10000;
		cout <<"Descripcion del libro: "<<endl;
		cin>>s;
		do{
			cout <<"Precio del libro: "<<endl;
			cin>>precio;
			if(precio<=0) cout <<"Precio incorrecto, debe ser mayor a 0"<<endl;
		}while(precio<=0);
		cout <<"Fecha de creacion"<<endl;
		do{
			cout <<"Dia: "<<endl;
			cin>>d;
			if (d<1||d>31) cout <<"Dia incorrecto, debe ser de 1 a 31"<<endl;
		}while (d<1||d>31);
		do{
			cout <<"Mes: "<<endl;
			cin>>m;
			if (m<1||m>12) cout <<"Mes incorrecto, debe ser de 1 a 12"<<endl;
		}while (m<1||m>12);
		do{
			cout <<"Año: "<<endl;
			cin>>a;
			if(a>2016) cout <<"Año incorrecto, debe ser menos o igual al año actual"<<endl;
		}while(a>2016);
		p[i].cargarP(cod,s,precio,d,m,a);
	}
	cout <<"Introduzca el IVA"<<endl;
	cin>>iva;
	
	for (i=0;i<3;i++){
		p[i].setPrecio((p[i].getPrecio()+(p[i].getPrecio()*iva/100)));	
	}
	
	for (i=0;i<3;i++){
		p[i].mostrar();
	}

	ordenar(p);

	for (i=0;i<3;i++){
		p[i].mostrar();
	}
}

void ejer2(){
	float x;
	monedero m(1200);
	cout <<"Saldo inicial: "<<m.consulta()<<endl;
	m.deposito(500);
	cout <<"Deposito: 500"<<endl;
	cout <<"Saldo disponible: "<<m.consulta()<<endl;
	cout <<"Retiro: 2000"<<endl;
	m.retiro(2000);
	x=m.consulta();
	cout <<"Saldo disponible: "<<x<<endl;
	cout <<"Retiro de todo el dinero"<<endl;
	m.retiro(x);
	cout <<"Saldo disponible: "<<m.consulta()<<endl;
}

void validar(int &q,int &w,int &e,int &r){
	fstream entrada;
	string s; int x;
	entrada.open("ejer3.txt",ios::in);
	while (!entrada.eof()){
		entrada>>x;
		switch(x){
			case 1: for (int i=0; i<7;i++){
						entrada>>s;
					}
					q++;
					break;
			case 2: for (int i=0; i<8;i++){
						entrada>>s;
					}
					w++;
					break;
			case 3: for (int i=0; i<9;i++){
						entrada>>s;
					}
					e++;
					break;
			case 4: for (int i=0; i<11;i++){
						entrada>>s;
					}
					r++;
					break;
		}
	}
	
}

void ejer3(){
	int q=0,w=0,e=0,r=0;
	validar(q,w,e,r);
}

int main (){
	int op=-1;
	while (op!=0){
		cout <<endl;
		cout <<"**********Menu principal**********"<<endl;
		cout <<"	1. Ejercicio 1"<<endl;
		cout <<"	2. Ejercicio 2"<<endl;
		cout <<"	3. Ejercicio 3"<<endl;
		cout <<"	0. Salir"<<endl;
		cin >>op;
		switch (op){
			case 1: ejer1();
					break;
			case 2: ejer2();
					break;
			case 3: ejer3();
					break;
		}
	}
}
