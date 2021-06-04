#include <iostream>
using namespace std;

int main(){

  cout <<"**********EJERCICIO 1**********"<<endl<<endl;;
  int nota1,nota2;
  string nombre1,nombre2;
  cout <<"Introduzca el nombre del Estudiante 1"<<endl;
  cin.clear();
  getline(cin,nombre1);
  cout <<"Introduzca el nombre del Estudiante 2"<<endl;
  cin.clear();
  getline (cin,nombre2);
  cout <<"Introduzca la nota del parcial de "<<nombre1<<": "<<endl;
  cin >>nota1;
  cout <<"Introduzca la nota del parcal de "<<nombre2<<": "<<endl;
  cin >>nota2;
  if ((nota1<0)||(nota1>20)) cout <<"La nota de "<<nombre1<<" es inválida, tiene que ser mayor a 0 y menor que 20"<<endl;
  if ((nota2<0)||(nota2>20)) cout <<"La nota de "<<nombre2<<" es inválida, tiene que ser mayor a 0 y menor que 20"<<endl;
  
  if (((nota1>=0)&&(nota1<=20))&&((nota2>=0)&&(nota2<=20))){ 
     if (nota1==nota2) cout <<"Los Estudiante tienen la misma nota"<<endl;
     if (nota1<nota2) cout <<nombre2<<" tiene más nota que "<<nombre1<<endl;
     if (nota1>nota2) cout <<nombre1<<" tiene más nota que "<<nombre2<<endl;
 }
  
   cout <<"**********EJERCICIO 2**********"<<endl<<endl;
   int d1,m1,a1,d,m,a,aa,conta;
   conta=0;
   d=11;
   m=12;
   a=2015;
   aa=2016;
   cout <<"introduzca el dia de la fecha de vencimiento del producto"<<endl;
   cin >>d1;
   cout <<"introduzca el mes de la fecha de vencimiento del producto"<<endl;
   cin >>m1;
   cout <<"introduzca el año de la fecha de vencimiento del producto"<<endl;
   cin >>a1;
   
   if ((d1<1 || d1>31)||(m1<1 ||m1>12)) cout <<"Fecha invalida, el Día debe ser entre 1 y 31 y Mes entre 1 y 12"<<endl;
   else{
   if (a1<a) cout<<"El producto esta vencido"<<endl;
   else{
   	if (a1==a){
      if (m1==m){
         if (d1>=d) cout<<"El producto está válido"<<endl;
         if (d1<d) {cout<<"El producto está vencido"<<endl; conta++;}
      }
      else
      if (m1<m) {cout<<"El producto está vencido"<<endl; conta++;}
   }
   else  cout <<"El producto esta valido"<<endl;
   }
   if ((a1>=aa)&&(m1>=3)&&(d1>=d)) cout <<"El producto no vencerá en los próximos 3 meses"<<endl;	
   
   else if (conta==0) cout <<"El producto vencerá en menos de 3 meses"<<endl;
   }
   cout <<"**********EJERCICIO 3**********"<<endl<<endl;
   
   float temp,temp2;
	cout <<"introduzca la temperatura en Grados Celsius"<<endl;
	cin >> temp;
	if (temp==0) cout<<"La temperatura es igual a 0 Grados Celsius"<<endl;
	if (temp<0) cout<<"La temperatura es negativa (es menor de 0 Grados Celsius)"<<endl;
	if (temp>0) cout<<"La temperatura es positiva (es mayor a 0 Grados Celsius)"<<endl;
	temp2=((9*temp)/5)+32;
	cout <<"La temperatura en grados Fahrenheit es "<<temp2<<endl;
	if (temp2<32) cout <<"La temperatura es menor al punto de congelación (32ºF)"<<endl;
	if (temp2==32) cout <<"La temperatura esta en el punto de congelación (32ºF)"<<endl;
	if (temp2==212) cout <<"La temperatura esta en el punto de ebullición (212ºF)"<<endl;
	if (temp2>212) cout<<"La temperatura es mayor al punto de ebullición (212ºF)"<<endl;
	if ((temp2 <212)&& (temp2 >32)) cout <<"La temperatura esta entre el punto de ebullición y congelación"<<endl;
   
   cout <<"**********EJERCICIO 4**********"<<endl<<endl;
   
   int numero,n1,n2,n3,n4,n5,cont;
   cont=0;
	cout <<"Ingrese un numero entero entre 0 y 99.999:"<<endl;
	cin >>numero;
	if ((numero<0) ||(numero>99999)) cout <<"Numero inválido, tiene que ser entre 0 y 99.999"<<endl;
	else{
	   if (numero>0){
		   n1=numero%10;
		   numero=numero/10;
		   cont++;
	   }
	   if (numero>0){
		   n2=numero%10;
		   numero=numero/10;	   
		   cont++;
	   }
	   if (numero>0){
		   n3=numero%10;
		   numero=numero/10;		   
		   cont++;
	   }
	   if (numero>0){
		   n4=numero%10;
		   numero=numero/10;		   
		   cont++;
	   }
	   if (numero>0){
		   n5=numero%10;
		   numero=numero/10;		   
		   cont++;
	   }
	   cout <<"El número posee "<<conta<<" dígitos"<<endl<<endl;
	   cout <<"primer numero: "<<n1<<endl;
	   cout <<"segundo numero: "<<n2<<endl;
	   cout <<"tercer numero: "<<n3<<endl;
	   cout <<"cuarto numero: "<<n4<<endl;
	   cout <<"quinto numero: "<<n5<<endl;
	}
   
   cout <<"**********EJERCICIO 5**********"<<endl<<endl;
   
   int n11,n22,n33,n44,n55,dist,igual,mayor,menor;
   dist=0;
   igual=-1;
   cout <<"introduzca 5 numeros"<<endl;
   cin >>n11>>n22>>n33>>n44>>n55;
   mayor=n11;
   menor=n11;
   if ((n11<0)||(n22<0)||(n33<0)||(n44<0)||(n55<0)) cout <<"los números tienen que ser enteros positivos"<<endl;
   else{
   if (n22<menor) menor=n22;
   if (n22>mayor) mayor=n22;
   if (n33<menor) menor=n33;
   if (n33>mayor) mayor=n33;
   if (n44<menor) menor=n44;
   if (n44>mayor) mayor=n44;
   if (n55<menor) menor=n55;
   if (n55>mayor) mayor=n55;
   if (mayor!=n11) dist++;
   if (mayor!=n22) dist++;
   if (mayor!=n33) dist++;
   if (mayor!=n44) dist++;
   if (mayor!=n55) dist++;
   if (menor==n11) igual++;
   if (menor==n22) igual++;
   if (menor==n33) igual++;
   if (menor==n44) igual++;
   if (menor==n55) igual++;
   cout <<"El mayor valor es "<<mayor<<", la cantidad de valores distintos a "<<mayor<<" son "<<dist<<endl;
   cout <<"El menor valor es "<<menor<<", la cantidad de valores iguales a "<<menor<<" son "<<igual<<endl;
   }
   
   return 0;

}
