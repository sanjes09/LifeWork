#include <iostream>
#include <stdlib.h>
#include <math.h>
using namespace std;

int main(){
	/*cout <<"****************** Ejercicio 1 ******************"<<endl<<endl;
	cout<<"Soy Jesus Martinez, seccion C4 y este es mi primer programa en C++"<< endl<<endl;
	cout<<"Presione enter para continuar"<<endl<<endl;
	cin.ignore().get();
	system("/usr/bin/clear");
	
	int x,y,z,res;
	float re;
	cout <<"****************** Ejercicio 2 ******************"<<endl<<endl;
	cout <<"introduzca x: "<<endl;
	cin >> x;
	cout <<"introduzca y: "<<endl;
	cin >> y;
	cout <<"introduzca z: "<<endl;
	cin >> z;
	re=(pow(x,2) + 8 % 2 / y / (3 - 4))/ (8 - x / 7 + z * 11 % 3);
	res= (int)(pow(x,2) + 8 % 2 / y / (3 - 4))/ (8 - x / 7 + z * 11 % 3); 
	cout <<"Respuesta real:"<<re<<endl;
	cout <<"Respuesta entera:"<<res<<endl<<endl;	
	cout<<"Presione enter para continuar"<<endl<<endl;
	cin.ignore().get();
	system("/usr/bin/clear");
	
	
	
	float d1,d2,d3,d4,max,prom;
	cout <<"****************** Ejercicio 3 ******************"<<endl<<endl;
	cout<<"indique la distancia 1: "<<endl;
	cin >> d1;
	cout<<"indique la distancia 2: "<<endl;
	cin >> d2;
	cout<<"indique la distancia 3: "<<endl;
	cin >> d3;
	cout<<"indique la distancia 4: "<<endl;
	cin >> d4;
	max=d1+d2+d3+d4;
	prom=(max/4);
	cout<<"Distancia total recorrida: "<<max<<endl;
	cout<<"Distancia en promedio por recorrido: "<<prom<<endl<<endl;
	cout<<"Presione enter para continuar"<<endl<<endl;
	cin.ignore().get();
	system("/usr/bin/clear");
	
	
	
	float p1,p2,sub,imp,total,iva;
	cout <<"****************** Ejercicio 4 ******************"<<endl<<endl;
	cout <<"Introduzca el precio del primer producto: "<<endl;
	cin >>p1;
	cout <<"Introduzca el precio del segundo producto: "<<endl;
	cin >>p2;
	sub=p1+p2;
	cout <<"Introduzca el porcentaje de IVA: "<<endl;
	cin >>iva;
	cout <<"Subtotal: "<<sub<<endl;
	iva=iva/100;
	imp=iva*sub;
	cout<<"Impuestos: "<<imp<<endl;
	total=imp+sub;
	cout<<"Total: "<<total<<endl<<endl;
	cout<<"Presione enter para continuar"<<endl<<endl;
	cin.ignore().get();
	system("/usr/bin/clear");*/
	
	
	float nota,fin;
	string nombre;
	int cedula;
	fin=0; 
	cout <<"****************** Ejercicio 5 ******************"<<endl<<endl;
	cout<<"Introduzca el nombre: "<<endl;
	getline (cin,nombre);//cin >> nombre;
	cout <<nombre<<endl;
	cout<<"Introduzca la cedula: "<<endl;
	cin >> cedula;
	cout<<"Introduzca la nota del primer parcial: "<<endl;
	cin >> nota;
    fin+=(nota*0.1);
	cout<<"Introduzca la nota del segundo parcial: "<<endl;
	cin >> nota;
    fin+=(nota*0.1);
    cout<<"Introduzca la nota del tercer parcial: "<<endl;
	cin >> nota;
    fin+=(nota*0.1);
    cout<<"Introduzca la nota del primer proyecto: "<<endl;
	cin >> nota;
    fin+=(nota*0.25);
    cout<<"Introduzca la nota del segundo proyecto: "<<endl;
	cin >> nota;
    fin+=(nota*0.25);
    cout<<"Introduzca la nota del primer quiz: "<<endl;
	cin >> nota;
    fin+=(nota*0.05);
    cout<<"Introduzca la nota del segundo quiz: "<<endl;
	cin >> nota;
    fin+=(nota*0.05);
    cout<<"Introduzca la nota del laboratorio: "<<endl;
	cin >> nota;
    fin+=(nota*0.1);
    cout<<"La nota final: "<<fin<<endl;
    
    return 0;
    
}
