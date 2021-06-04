#include <iostream>
#include <math.h>
using namespace std;

int main (){
	int cn;
        do{
        cn=-1;
        cout <<"1. Ejercicio 1"<<endl;
        cout <<"2. Ejercicio 2"<<endl;
        cout <<"3. Ejercicio 3"<<endl;
        cout <<"4. Ejercicio 4"<<endl;
        cout <<"0. Salir"<<endl;
        cin >>cn;
        switch (cn){
        case 1:
	cout <<"********** EJERCICIO 1 **********"<<endl;
	
	int f,d,m,a,finv,fval,D,M,A;
	D=15;
	M=01;
	A=2016;
	f=0; finv=0; fval=0;
	cout <<"introduzca la fecha de nacimiento sin espacios (AAAAMMDD) o ingrese -1 para salir"<<endl;
        cin >>f;
	while (f!=-1){
	    if ((f<=9999999)||(f>99999999)) {
	    	cout <<"Fecha invalida, debe tener 8 dígitos"<<endl<<endl;;
	    	finv++;
	    }
	    else{
	    	d=f%100;
	    	f=f/100;
	    	m=f%100;
	    	f=f/100;
	    	a=f;
	    	cout <<"Dia "<<d<<" mes "<<m<<" año "<<a<<endl<<endl;;
	    	if(a<1905|| a>2016){
			    cout<<"Año inválido, debe ser entre 1905 y 2016"<<endl;
			    finv++;
			}
	    	else if(m<1||m>12){
	    		     cout<<"Mes inválido, debe ser entre 01 y 12"<<endl;
	    		     finv++;
	    		 }
	    	     else if(d<1||d>31){
	    	     	      cout<<"Día invalido, debe ser entre 01 y 31"<<endl;
	    	     	      finv++;
	    	     	  }
	    	          else{
	    	          	fval++;
                                  if ((m==M) && (d<D)) cout <<"La persona cumplió año"<<endl;
                                  if ((m==M) && (d==D)) cout <<"La persona está cumpliendo año"<<endl;
                                  if (m>M) cout <<"La persona va a cumplir año"<<endl;
	    	          
	    	          	    
	    	          
					  }
		}
	
	cout<<"Fechas invalidas: "<<finv<<endl;
	cout<<"Fechas validas: "<<fval<<endl;
	cout<<endl;
	cout <<"introduzca la fecha de nacimiento sin espacios (AAAAMMDD) o ingrese -1 para salir"<<endl;
        cin >>f;
    	}
        break;
        
        case 2:	
        cout <<"********** EJERCICIO 2 **********"<<endl;
    
        int n,x,z;
        double i,j,par1,par2,par3,k,suma,total;
        i=1;
        j=0;
        k=1;
        par1=1;
        par2=0;
        par3=1;
        total=1;
        cout <<"Ingrese N,X,Z"<<endl;
        cin >>n>>x>>z;
        if (n<1) cout <<"Valos invalido, N debe ser mayor igual a 1"<<endl;
        else{
         while (i<=n){
         k=(x/(n+z));
         par1=par1*k;  
		 
		 j=1;    
         if (j>i) par2=0;
         else{
		    do{
         	   k=(((n*x)+j)/z);
               k=pow(k,3);
               par2=par2+k;
               j++;
            }while (j<=i);
         }
      
         j=n;
         if (j>i) par3=1;
         else{
         	do{
               k=(x*j);
               k=pow(k,3);
               par3=par3*k;
               j++;
            }while (j<=i);
         }
         
		 par2=par2+par3;
		 par2=pow(par2,(1.0/3.0));
         suma=par1+par2;
         total*=suma;
         i++;
     }
         total/=2;
         cout <<"TOTAL: "<<total<<endl;
     }
    break;

    case 3:
   
    cout <<"********** EJERCICIO 3 **********"<<endl;
    
	int o,inv,sum;
	inv=0;
	sum=0;
	cout <<"Ingrese un numero entero"<<endl;
	cin >>o;
	while (o>0){
		inv+=(o%10);
		if (o/10!=0) inv*=10;
		sum+=(o%10);
		o/=10;
	}
	cout <<"El numero invertido es: "<<inv<<endl;
	cout <<"La suma de los digitos es: "<<suma<<endl;	
    break;

    case 4:
			
	cout <<"********** EJERCICIO 4 **********"<<endl;
	int b,h,par,impar;
	
	for (b=1000; b<10000; b++){
		h=b;
		par=0; impar=0;
		par+=(h%10);
		h/=10;
		impar+=(h%10);
		h/=10;
		par+=(h%10);
		h/=10;
		impar+=(h%10);
		h/=10;
		if (par==impar) cout <<"El número "<<b<<" cumple con la condición"<<endl;
	}
   break;}
}while (cn!=0);

return 0;
}
