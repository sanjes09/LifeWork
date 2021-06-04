#include <iostream>
using namespace std;

class Cola{
	private:
		class Nodo{
			public:
				int dato;
				Nodo *prox;
		};
		Nodo *primero;
		Nodo *ultimo;
	public:
		Cola(){
			primero=NULL;
			ultimo=NULL;
		}
		void encolar(int elem){				//INSERTA NUEVO ELEMENTO EN LA COLA
			Nodo *nuevo=new Nodo();
			nuevo->dato=elem;
			if(primero==NULL){
				primero=nuevo;
				ultimo=primero;
			}else{
				ultimo->prox=nuevo;
				ultimo=nuevo;
			}
		}
		void desencolar(){					//ELIMINA EL PRIMER ELEMENTO DE LA COLA
			Nodo *borrar=primero;
			primero=primero->prox;
			delete borrar;
		}
		int &Dato(){						//RETORNA EL DATO QUE SE ENCUENTRA DE PRIMERO
			return primero->dato;
		}
		bool EsVacia(){						//RETORNA SI LA COLA ES VACIA O NO
			return primero==NULL;
		}
};

class Arbol{
	private:
		class Nodo{
		public:
			int dato;
			Nodo *h1,*h2,*h3,*h4;
			Nodo(){
				dato=0;
				h1=NULL;
				h2=NULL;
				h3=NULL;
				h4=NULL;
			}
		};
		Nodo *raiz;
	public:
		Arbol(){
			raiz=NULL;
		}
		Nodo *&r(){								//RETORNA LA RAIZ DEL ARBOL
			return raiz;
		}
		void imprimir(Nodo *a){					//IMPRIME EN IN-ORDEN
			if(a->h1) imprimir(a->h1);
			if(a->h2) imprimir(a->h2);
			cout<<a->dato<<" ";
			if(a->h3) imprimir(a->h3);
			if(a->h4) imprimir(a->h4);
		}
		void insertar(Nodo *&padre,Cola &C){	//INSERTA LA COLA AL ARBOL
			if(!C.EsVacia()){
				if(padre==NULL){
					Nodo *nuevo=new Nodo();
					nuevo->dato=C.Dato();
					C.desencolar();
					padre=nuevo;
					if(nuevo->dato==0){
						insertar(padre,C);
					}
				}
				else{
					if(!padre->h1){
						insertar(padre->h1,C);
					}
					if(!padre->h2){
						insertar(padre->h2,C);
					}
					if(!padre->h3){
						insertar(padre->h3,C); 
					}
					if(!padre->h4){
						insertar(padre->h4,C);
					}
				}
			}
		}

};

void Matriz(int **F,int x,int y,int n,int m,int h[],int &w){		//SE LLENA LA MATRIZ CON EL ARREGLO
	if(h[w]==0){													//SI EL ARREGLO ES IGUAL A 0 EN CIERTA POSICION, SE DIVIDE LA MATRIZ
		w++;
		Matriz(F,x,y,(x+n)/2,(y+m)/2,h,w);
		w++;
		Matriz(F,x,(y+m)/2,(x+n)/2,m,h,w);
		w++;
		Matriz(F,(x+n)/2,y,n,(y+m)/2,h,w);
		w++;
		Matriz(F,(x+n)/2,(y+m)/2,n,m,h,w);
	}
	else{															//SINO, SE LLENA CON EL MISMO NÚMERO
		for(int i=x;i<n;i++){
			for(int j=y;j<m;j++){
				F[i][j]=h[w];
			}
		}
	}
}

bool Diferente(int **F,int x,int y,int n,int m){					//SE COMPARA LA MATRIZ SI ES DIFERENTE DESDE X,Y HASTA N,M
	for(int i=x;i<n;i++){
		for(int j=y;j<m;j++){
			if(F[x][y]!=F[i][j]) return true;
		}
	}
	return false;
}

void Solucion(int **F,int x,int y,int n,int m,Cola &C,int col[]){	//SE LEE LA MATRIZ PARA SACAR EL IN-ORDEN DEL ARBOL
	if(Diferente(F,x,y,n,m)){										//SI SON DIFERENTES SE DIVIDE Y SE ANALIZA CADA CUADRANTE
		C.encolar(0);
		col[0]+=1;		//SE AUMENTA EL ARREGLO DE COLORES
		Solucion(F,x,y,(x+n)/2,(y+m)/2,C,col);
		Solucion(F,x,(y+m)/2,(x+n)/2,m,C,col);
		Solucion(F,(x+n)/2,y,n,(y+m)/2,C,col);
		Solucion(F,(x+n)/2,(y+m)/2,n,m,C,col);
	}
	else{															//SINO SE GUARDA EL MISMO NUMERO
		C.encolar(F[x][y]);
		col[F[x][y]]+=1;
	}
}

int main(){
	int **F;
	int w=0,n,m,q,k;
	cin>>n>>m>>q;

	F=new int*[n];								//SE CREA LA MATRIZ
	for(int i=0; i<n; i++){
		F[i]=new int[m];
	}
	switch(q){
		case 1:{									
			Arbol *A=new Arbol();				//ARBOL
			Cola C;								//COLA
			int col[]={0,0,0,0,0,0,0,0,0,0};	//ARREGLO DE COLORES
			for(int i=0;i<n;i++){				//SE LLENA MATRIZ
				for(int j=0;j<m;j++){
					cin>>F[i][j];
				}
			}
			Solucion(F,0,0,n,m,C,col);			//SE OPERA LA MATRIZ
			
			for(int i=0;i<10;i++){				//SE IMPRIMEN LOS COLORES
				if(i==0||col[i]!=0) cout<<col[i]<<" ";
			}
			A->insertar(A->r(),C);				//SE GUARDA EL ARREGLO EN EL ARBOL
			cout<<endl;
			A->imprimir(A->r());				//SE IMPRIME EL ARBOL
			break;
		}
		case 2:{
			cin>>k;								
			int h[k];							//SE CREA ARREGLO DE K
			for(int i=0;i<k;++i){				
				cin>>h[i];
			}
			Matriz(F,0,0,n,m,h,w);				//SE LLENA LA MATRIZ CON LOS VALORES DE K
			for(int i=0;i<n;i++){				//SE IMPRIME LA MATRIZ
				for(int j=0;j<m;j++){
					cout<<F[i][j]<<" ";
				}
				cout<<endl;
			}
			break;
		}
	}
	return 0;
}
