#include <iostream>
#include <stdlib.h>
#include <ctype.h> 
using namespace std;

template <class T>

class ColaArreglo {
	private:
		T *letras;
		int pri;
		int ult;
		int cant;
	public:
		ColaArreglo(int x){
			letras=new T[x];
			cant=x;
			pri=0;
			ult=0;
		}
		~ColaArreglo(){
			while(!(EsVacia())){
				desencolar();
			}
		}
		void encolar(T elem){
			letras[ult]=elem;
			ult++;
			if(ult>cant) ult=0;
		}
		void desencolar(){
			int x;
			x=pri;
			pri++;
			if(pri>cant) pri=0;
			letras[x]=' ';
		}
		T frente(){
			return letras[pri];
		}
		bool EsVacia(){
			if(pri==ult) return 1;
			else return 0;
		}
		int tamano(){
			return abs(pri-ult);
		}
};

int len(string s){
	int cont=0;
	while(s[cont]){
		cont++;
	}
	return cont;
}

int main (){
	string S;
	int x,y=1;
	cout<<"PALABRA"<<endl;
	cin>>S;
	x=len(S);
	for(int i=0;i<x;i++){
		S[i]=tolower(S[i]);
	}
	
	ColaArreglo <char> M(x);
	ColaArreglo <char> N(x);

	for(int i=0;i<x;i++){
		M.encolar(S[i]);
	}
	for(int i=x-1;i>=0;i--){
		N.encolar(S[i]);
	}

	while((!M.EsVacia())&&(!N.EsVacia())){
		if(M.frente()!=N.frente()){
			cout<<M.frente()<<endl;
			y=0;
			break;
		}
		else{
			M.desencolar();
			N.desencolar();
		}
	}
	if(y==0) cout<<"No es palindrome"<<endl;
	else cout<<"Es palindrome"<<endl;

	return 0;	
}
