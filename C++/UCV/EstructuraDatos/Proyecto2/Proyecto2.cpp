#include <iostream>
#include <string.h>
using namespace std;

class jugador{
private:
	int x;
	int y;
public:
	jugador(){
		x=0;
		y=0;
	}

	void setX(int a){
		x=a;
	}
	void setY(int b){
		y=b;
	}
	int getX(){
		return x;
	}
	int getY(){
		return y;
	}
	
};

int len(char x[],int n,int m){
	int cont=0;

	for(int i=0;i<(n*m*2);i++){
		if ((x[i]!='0')&&(x[i]!='q')){
			cont++;
		}
	}

	return cont;
}

void copiar(char reg[],char resp[],int n,int m){							//Copia solucion de un arreglo a otro
	for(int i=0;i<(n*m*2);i++){
		resp[i]=reg[i];
	}
}

void guardar(char resp[],char reg[],int n,int m,int tiempo,int &total){		//Guarda la mejor solucion
	if(tiempo<total){
		total=tiempo;
		copiar(reg,resp,n,m);
	}
	else{
		if(tiempo==total){
			if(len(reg,n,m)<len(resp,n,m))
				copiar(reg,resp,n,m);
		}
	}
}

int capd(jugador jug,char **ciudad,int n,int m,char &x,char &y,char &z){	//Valida si hay captura a distancia
	int x1,y1,i=0;
	x1=jug.getX();
	y1=jug.getY();
	while((x1>0)&&(ciudad[x1][y1]!='*')){			//ARRIBA
		i++;
		x1--;
		if ((ciudad[x1][y1]!='C')&&(ciudad[x1][y1]!='.')&&((int)(ciudad[x1][y1]-48)>=i)){
			x=x1;
			y=y1;
			z='N';
			return 1;
		}
	}
	i=0;
	x1=jug.getX();
	y1=jug.getY();
	while((x1<n-1)&&(ciudad[x1][y1]!='*')){			//ABAJO
		i++;
		x1++;
		if ((ciudad[x1][y1]!='C')&&(ciudad[x1][y1]!='.')&&((int)(ciudad[x1][y1]-48)>=i)){
			x=x1;
			y=y1;
			z='S';
			return 1;
		}
	}
	i=0;
	x1=jug.getX();
	y1=jug.getY();
	while((y1>0)&&(ciudad[x1][y1]!='*')){			//IZQUIERDA
		i++;
		y1--;
		if ((ciudad[x1][y1]!='C')&&(ciudad[x1][y1]!='.')&&((int)(ciudad[x1][y1]-48)>=i)){
			x=x1;
			y=y1;
			z='O';
			return 1;
		}
	}
	i=0;
	x1=jug.getX();
	y1=jug.getY();
	while((y1<m-1)&&(ciudad[x1][y1]!='*')){			//DERECHA
		i++;
		y1++;
		if ((ciudad[x1][y1]!='C')&&(ciudad[x1][y1]!='.')&&((int)(ciudad[x1][y1]-48)>=i)){
			x=x1;
			y=y1;
			z='E';
			return 1;
		}
	}
	return 0;
}

void jugar(jugador jug,char **ciudad,int **paso,int n,int m,int t,char reg[],int f,int cont,char resp[],int tiempo,int &total,int can){
	char w='0',x1='0',y1='0',z='0',l='0';
	int x,y,q;
	if(len(resp,n,m)>=f){
		if((cont!=0)&&(capd(jug,ciudad,n,m,x1,y1,z))){				//CAPTURA A DISTANCIA
			x=(int)(x1);
			y=(int)(y1);
			tiempo=tiempo+t;
			w=ciudad[x][y];
			ciudad[x][y]='.';
			reg[f]='C';
			f++;
			reg[f]='q';
			f++;
			reg[f]=z;
			f++;
			can++;
			cont--;
			jugar(jug,ciudad,paso,n,m,t,reg,f,cont,resp,tiempo,total,can);
			cont++;
			can--;
			f--;
			reg[f]='0';
			f--;
			reg[f]='0';
			f--;
			reg[f]='0';
			ciudad[x][y]=w;
			tiempo=tiempo-t;
		}
		if((cont!=0)&&(ciudad[jug.getX()][jug.getY()]!='C')&&(ciudad[jug.getX()][jug.getY()]!='.')&&(ciudad[jug.getX()][jug.getY()]!='*')){			//CAPTURA EN LA CASILLA
			w=ciudad[jug.getX()][jug.getY()];
			ciudad[jug.getX()][jug.getY()]='.';
			reg[f]='C';
			f++;
			can++;
			cont--;
			jugar(jug,ciudad,paso,n,m,t,reg,f,cont,resp,tiempo,total,can);
			cont++;
			can--;
			f--;
			reg[f]='0';
			ciudad[jug.getX()][jug.getY()]=w;
		}
		if((cont!=0)&&(jug.getX()-1>=0)&&(ciudad[jug.getX()-1][jug.getY()]!='*')&&(paso[jug.getX()-1][jug.getY()]!=can)){			//ARRIBA
			jug.setX(jug.getX()-1);
			tiempo++;
			reg[f]='N';
			f++;
			q=paso[jug.getX()][jug.getY()];
			paso[jug.getX()][jug.getY()]=can;
			jugar(jug,ciudad,paso,n,m,t,reg,f,cont,resp,tiempo,total,can);
			paso[jug.getX()][jug.getY()]=q;
			f--;
			reg[f]='0';
			tiempo--;
			jug.setX(jug.getX()+1);
		}
		if((cont!=0)&&(jug.getX()+1<n)&&(ciudad[jug.getX()+1][jug.getY()]!='*')&&(paso[jug.getX()+1][jug.getY()]!=can)){			//ABAJO
			jug.setX(jug.getX()+1);
			tiempo++;
			reg[f]='S';
			f++;
			q=paso[jug.getX()][jug.getY()];
			paso[jug.getX()][jug.getY()]=can;
			jugar(jug,ciudad,paso,n,m,t,reg,f,cont,resp,tiempo,total,can);
			paso[jug.getX()][jug.getY()]=q;
			f--;
			reg[f]='0';
			tiempo--;
			jug.setX(jug.getX()-1);
		}
		if((cont!=0)&&(jug.getY()+1<m)&&(ciudad[jug.getX()][jug.getY()+1]!='*')&&(paso[jug.getX()][jug.getY()+1]!=can)){			//DERECHA
			jug.setY(jug.getY()+1);
			tiempo++;
			reg[f]='E';
			f++;
			q=paso[jug.getX()][jug.getY()];
			paso[jug.getX()][jug.getY()]=can;
			jugar(jug,ciudad,paso,n,m,t,reg,f,cont,resp,tiempo,total,can);
			paso[jug.getX()][jug.getY()]=q;
			f--;
			reg[f]='0';
			tiempo--;
			jug.setY(jug.getY()-1);
		}
		if((cont!=0)&&(jug.getY()-1>=0)&&(ciudad[jug.getX()][jug.getY()-1]!='*')&&(paso[jug.getX()][jug.getY()-1]!=can)){			//IZQUIERDA
			jug.setY(jug.getY()-1);
			tiempo++;
			reg[f]='O';
			f++;
			q=paso[jug.getX()][jug.getY()];
			paso[jug.getX()][jug.getY()]=can;					
			jugar(jug,ciudad,paso,n,m,t,reg,f,cont,resp,tiempo,total,can);
			paso[jug.getX()][jug.getY()]=q;
			f--;
			reg[f]='0';
			tiempo--;
			jug.setY(jug.getY()+1);
		}
		if(cont==0)	guardar(resp,reg,n,m,tiempo,total);
	}
}

int main(){
	int n,m,t,cont=0,can=0,total;
	char c;
	jugador jug;

	cin>>n>>m>>t;      //Inicializacion
	
	total=2*n*m;
	char reg[n*m*2];
	char resp[n*m*2];
	for(int i=0;i<(n*m*2);i++){
		reg[i]='0';
		resp[i]='1';
	}

	char **ciudad;
	int **paso;

	ciudad=new char*[n];
	paso=new int*[n];
	for(int i=0; i<n; i++){
		ciudad[i]=new char [m];
		paso[i]=new int[m];
	}

	for(int i=0; i<n; i++){   //Rellena la matriz
		for(int j=0; j<m; j++){
			cin>>c;
			if ((c!='.')&&(c!='*')&&(c!='C')) cont++;
			ciudad[i][j]=c; 
			paso[i][j]=-1;
			if (c=='C'){    //Posiciona al jugador en la posicion inicial
				jug.setX(i);
				jug.setY(j);
				paso[i][j]=0;
			}
		}
	}

	jugar(jug,ciudad,paso,n,m,t,reg,0,cont,resp,0,total,can);
	
	cout<<total<<endl;
	for(int i=0;i<(n*m*2);i++){		//muestra solucion
		if ((resp[i]!='0')&&(resp[i]!='q')){
			if((resp[i]=='C')&&(resp[i+1]=='q')){
				cout<<resp[i]<<resp[i+2]<<" ";
				i=i+2;
			}else
			cout<<resp[i]<<" ";
		}
	}
	return 0;
}
