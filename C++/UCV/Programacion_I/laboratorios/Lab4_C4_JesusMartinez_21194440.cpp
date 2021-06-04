#include <iostream>
#include <math.h>
using namespace std;

int calFactorial(int x){
	int i=x;
	while (i>1){
		i--;
		x=x*i;
	}
	return x;
}

void calcularRes(int n, int x, int z, double &res){
	double i,j,par1,par2,par3,k,suma,total;
	i=1;
	j=0;
	k=1;
	par1=1;
    par2=0;
    par3=1;
    total=1;
	while (i<=n){
		k=calFactorial(x);
        k=(k/(n+z));
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
        		k=calFactorial(x);
        		k=k*j;
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
        res=total;
}

void ejer1(){
	int n,x,z;
	double res;
	cout <<"Ingrese N de [1..4],X de [0..5], Z[1..8]"<<endl;
    cin >>n>>x>>z;
    if (n<1|| n>4||x<0||x>5||z<1||z>8) cout <<"Valoress invalido, N debe ser [1..4], X de [0..5], Z de [1..8]"<<endl;
    else{
    	calcularRes(n,x,z,res);
    	cout <<"Resultado: "<<res<<endl;
    }
}

int esPrimo(int n){
	int x=1,cont=0;
	while (x<n){
		if ((n%x)==0) cont++;
		x++;
	}
	cout <<cont;
	if (cont==1){
		cout <<"El numero "<<n<<" es primo"<<endl;
		return 1;
	}
	else{
		cout <<"El numero "<<n<<" NO es primo"<<endl;
		return 0;
	}
}

void ejer2(){
	int x,cont,z;
	x=-1;
	while (x!=0){
		cout <<"Ingrese el numero a evaluar si es primo o no"<<endl;
		cin >>x;
		if(x!=0) z=esPrimo(x);
		if (z) cont++;
	}
	cout <<"Cantidad de numeros primos ingresados: "<<cont<<endl;
}

string procesarNotas(int n,float &max){
	string nombre, cedula,total; float nota;
	while (n){
		cout <<"introduzca el nombre del alumno"<<endl;
		cin >>nombre;
		cout <<"Introduzca la cedula del alumno"<<endl;
		cin >>cedula;
		cout <<"Introduzca la nota del primer parcial"<<endl;
		cin >>nota;
		while(nota<0 || nota>20){
			 cout<<"Nota invalida. Debe estar entre 1 y 20"<<endl;
			 cin>>nota;
		}
			
		if(nota>max){ 
			max=nota;
			total="";
		}
		if(nota==max){
			total=total+nombre+" "+cedula+"\n";	 
		}
		
		n--;
	}
	return total;
	
}

void ejer3(){
	int n; float max=0; string todo;
	cout <<"ingrese el numero de estudiantes"<<endl;
	cin >>n;
	todo=procesarNotas(n,max);
	cout <<todo<<endl;
	cout <<"Nota mayor: "<<max<<endl;	
}

void dibujarPrisma(int basePrisma){
	int x,y,i,j,h;
	j=1;
	i=1;
	x=1;
	h=basePrisma-1;
	y=1;
	while (y<=basePrisma){
		while(j<=h){
			cout <<" ";
			j++;
		}
		while (x<=i){
			cout <<"* ";
			x++;
		}
		cout <<endl;
		j=1;		
		y++;
		h--;
		i++;
		x=1;
	}
	j=1;
	h=1;
	basePrisma--;
	i=basePrisma;
	while (basePrisma>=1){
		while(j<=h){
			cout <<" ";
			j++;
		}
		while (x<=i){
			cout <<"* ";
			x++;
		}
		cout <<endl;
		j=1;
		h++;
		basePrisma--;
		i--;
		x=1;
	}
}

void ejer4(){
	int p;
	p=-1;
	while (p<0){
		cout <<"Ingrese el numero de base del prisma"<<endl;
		cin >>p;
	}
	dibujarPrisma(p);
}

int multiplicacion(int a, int b){
	int x,i=1;
	while (i<=b){
		x=x+a;
		i++;
	}
	return x;
}

int factorial(int n){
	int i=n,x=0;
	while (i>1){
		i--;
		n=multiplicacion(n,i);
	}
	return n;
}

int potencia(int base, int exp){
	int x=1,o=base;
	while (x<exp){
		base=multiplicacion(base,o);
		x++;
	}
	return base;
}

float raiz(int x,int raiz){
    float r = x, t = 0;
    if (raiz==2){
	    while (t != r){
	        t = r;
	        r = (x/r + r)/2;
	    }
	    return r;
	}
}

void ejer5(){
	int a,b;
	float r;
	a=-1; b=-1;
	while ((a<1)|| (b<1)){
		cout <<"Ingrese dos numeros enteros positivos"<<endl;
		cin >>a>>b;
		if (a<1) cout <<"PRIMER numero incorrecto, debe ser un entero positivo"<<endl;
		if (b<1) cout <<"SEGUNDO numero incorrecto, debe ser un entero positivo"<<endl;
	}
    // ( A! * B! )^(1/3)+ B^(A * B) + A^2 + B^5 /  B^(1/2) 
	r=pow((multiplicacion(factorial(a),factorial(b))),1.0/3.0)+potencia(b,(multiplicacion(a,b)))+potencia(a,2)+potencia(b,5)/raiz(b,2);
    cout <<"El Resultiado de la expresion es: "<<r<<endl;
}

int main (){
	int op;
	op=-1;
	while (op!=0){
		cout <<"1. Ejercicio 1"<<endl;
		cout <<"2. Ejercicio 2"<<endl;
		cout <<"3. Ejercicio 3"<<endl;
		cout <<"4. Ejercicio 4"<<endl;
		cout <<"5. Ejercicio 5"<<endl;
		cout <<"0. Salir"<<endl;
		cin >> op;
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
return 0;
}
