#include <iostream>

using namespace std;

class ave{
private:
	int a;          //Edad

public:
	ave *prox;      //Ave iguiente

	void setA(int x){
		a=x;
	}

	int getA(){
		return a;
	}
};

class lista{	
public:
	ave *x;           //Apuntador a la lista
	ave *centro;      //Apuntador al centro de la lista
		
	void Cen(){       //Posicisiona el apuntador de centro
		ave *t=x;
		int cont=0;
		while(t){    //cuenta cuandas aves hay
			cont++;
			t=t->prox;
		}
		if (cont>2){
			cont=cont/2;   //se situa en el medio de la lista
	
			t=x;           //reinicia t al principio de la lista
	
			for (int i=1; i<=cont; i++){  //Mueve t hasta la mitad de la lista
				t=t->prox;
			}
			centro=t;
		}
		else{
			centro=x;
		}
	}
	ave* ret(){
		return x;	
	}
};

void ordenar(ave **p){         //Ordena de menor a mayor la lista
	ave *t=*p; 
	ave *h=*p;
	while (t->prox){			//Pone el menor de primero
		if ((t->prox->getA()) < ((*p)->getA())){
			h=t->prox;
			t->prox=h->prox;
			h->prox=(*p);
			(*p)=h;
			t=(*p);}
		else
			t=t->prox;
	}
	if ((*p)->prox)
	ordenar(&((*p)->prox));     //Llamado recursivo con el siguiente de la lista
}

void ingresar(ave **p){         //Agrega dos aves a la lista
	ave *t=new ave;
	ave *k=new ave;
	int x; int b=1;
	
	cin >>x;				//Crea ave 1
	t->setA(x);
	t->prox=NULL;
	
	cin >>x;				//Crea ave 2
	k->setA(x);
	k->prox=NULL;
		
	t->prox=*p;		//ingresa ave 1 a la lista
	*p=t;
	k->prox=*p;		//ingresa ave 2 a la lista
	*p=k;
	b=1;

	ordenar(&(*p));				//ordena la nueva lista
}

void elim(int x, ave **p){      //Elimina el ave de la lista
	ave *t=*p;
	ave *h=*p;
	if (h->getA()==x){			//Condicion si es el primero en la lista
		*p=h->prox;
		h->prox=NULL;
		delete h;
	}
	else{
		while (t->prox){		//Mueve hasta encontrarlo
			if (t->prox->getA()==x){
				h=t->prox;
				t->prox=h->prox;
				h->prox=NULL;
				delete h;
			}
			else
				t=t->prox;
		}
	}
}

void retirar (ave **p){   //Retira dos aves
	int x,y;
	cin>>x>>y;
	
	elim(x,&(*p));		//Elimina X de la lista
	elim(y,&(*p));		//Elimina Y de la lista

}

void mostrar(ave *p){    //Muestra todas las aves
	ave *t=p;
	cout <<"                ";
	while (t){
		cout <<t->getA()<<" ";
		t=t->prox;
	}
	cout <<endl;
}

int main(){
	int A,Q,k=-1;
	ave p;
	
	lista *T=new lista;
	cin>>A;
	p.setA(A);              //Crea la primera ave
	p.prox=NULL;
	
	T->x=&p;                 //La lista apunta a las aves
	T->Cen();
	
	cin>>Q;          //Lee el ave inicial y la cantidad de repeticiones
	
	while (Q>0){
		cin>>k;
		switch(k){
			case 1:	cout<<"                "<<T->centro->getA()<<endl;
					break;
			case 2: ingresar(&(T->x));
					T->Cen();
					break;
			case 3: mostrar(T->x);
					break;
			case 4: retirar(&(T->x));
					T->Cen();
					break;
			case 5: cout<<(T->ret())->getA();
		}
		Q--;
	}
	return 0;
}
