#include <iostream>
#include <math.h>
#include <fstream>
using namespace std;

class tronera{
private:
		float x;
		float y;
		int num;
public:

        float getX(){
	        return x;
        }

        float getY(){
	        return y;
        }

        int getNum(){
	        return num;
        }
				
				void setNum(int x){
					num=x;
				}
        void cargar(float g,float h, int n){
	        x=g;
	        y=h;
	        num=n;
        }

        void mostrar(){
		    cout <<"Tronera "<<num<<" X:"<<x<<" Y:"<<y<<endl;
        }

};

class bola{
private:
		float x;
		float y;
		int num;
		float vx;
		float vy;
		bool tr;
		int tro;		
public:	
		
        float getX(){
	        return x;
        }

        float getY(){
	        return y;
        }

        int getNum(){
	        return num;
        }

        float getVx(){
	        return vx;
        }

        float getVy(){
	        return vy;
        }

        bool getTr(){
	        return tr;
        }

        int getTro(){
	        return tro;
        }

        void setX(float xx){
			x=xx;
        }

        void setY(float yy){
			y=yy;
        }

        void setNum(int n){
	        num=n;
        }

        void setVx(float xx){
        	if(xx>10) xx=10;
	        if(xx<-10) xx=-10;
	        vx=xx;
        }

        void setVy(float yy){
	        if (yy>10) yy=10;
	        if (yy<-10) yy=-10;
	        vy=yy;
        }

        void setTr(bool bo){
	        tr=bo;
        }

        void setTro(int n){
	        tro=n;
        }

        void cargar(float g,float h, int n){
		        x=g;
		        y=h;
		        num=n;
		        vx=0;
		        vy=0; 
		        tr=false;
		        tro=0;
		
        }

        void mostrar(){
            string s="blanca";
            if(num==0) cout <<"Bola "<<s<<": "<<endl;
		        else cout <<"Bola "<<num<<": "<<endl;
		        cout <<"X: "<<x<<endl;
		        cout <<"Y: "<<y<<endl;
		        if(tr) cout <<"Se encuentra en la tronera: "<<tro<<endl;
		        else cout <<"No se encuentra en una tronera"<<endl;
        }

};

void cargarM(tronera t[],int &k1){
	float x,x1,y,y1,cont;
	cont=0;
	while (cont!=1){
	  cout <<"Introduzca X y Y de la primera tronera"<<endl;
	  cout <<"X: ";
	  cin >>x;
		cout <<"Y: ";
		cin >>y;
		cout <<"Introduzca X y Y de la ultima tronera"<<endl;
		cout <<"X: ";
		cin >>x1;
		cout <<"Y: ";
		cin >>y1;
		if ((x1<=x)||(y1>=y)) cout <<"Coordenadas invalidas, la ultima tronera debe estar abajo y a la derecha de la primera"<<endl;
		else{
			t[0].cargar(x,y,1);	
			t[1].cargar(((x1+x)/2),y,2);	
			t[2].cargar(x1,y,3);			
			t[3].cargar(x,y1,4);			
			t[4].cargar(((x1+x)/2),y1,5);
			t[5].cargar(x1,y1,6);			
			for (int i=0;i<6;i++){			
					t[i].mostrar();
			}
			k1=1;
			cont++;
		}	
    }
}

int validarColisionB(float bx,float by,float b1x,float b1y,int r){
	float x,x1,y1,y;
	x=bx-b1x;
	x1=b1x-bx;
	x=pow(x,2);
	x1=pow(x1,2);
	y=by-b1y;
	y1=b1y-by;
	y=pow(y,2);
	y1=pow(y1,2);
	x=x+y;
	x1=x1+y1;
	y=pow((r+r),2);
	if((x<=y)||(x1<=y)) return 1;
	else return 0;
}

int validarColisionT(float bx,float by,tronera t[],int r){
	int x,y;
	for(int i=0;i<=5;i++){
		x=bx-t[i].getX();
		x=pow(x,2);
		y=by-t[i].getY();
		y=pow(y,2);
		x=x+y;
		y=pow((r+r),2);
		if(x<=y){
			return i+1;
		}
	}
	return 0;
}

int validarB(bola b[],tronera t[],int r,int x){

	if(((b[x].getX()-r)<t[0].getX())||((b[x].getX()+r)>t[5].getX())||((b[x].getY()+r)>t[0].getY())||((b[x].getY()-r)<t[5].getY())){
		cout <<"Bola invalida, esta fuera de los limites de la mesa o tocando un borde"<<endl;
		return 1;
	}
	else {
		for (int i=0;i<x;i++){
			if (validarColisionB(b[i].getX(),b[i].getY(),b[x].getX(),b[x].getY(),r)){ 
				cout <<"La bola esta solapada con otra bola"<<endl; 
				return 1;
			}
		}
		if (validarColisionT(b[x].getX(),b[x].getY(),t,r)){ cout<<"Colisiona con una tronera"<<endl; return 1;}
		else return 0;
	}
}

void cargarB(bola b[],tronera t[],int bo,int r,int &k2){
	float x,y,z; int i;
	if(k2==1){
		do{
			cout <<"introduzca X y Y de la bola blanca"<<endl;
			cout <<"X: ";
			cin>>x;
			cout <<"Y: ";
			cin>>y;
			b[0].cargar(x,y,0);
			z=validarB(b,t,r,0);
		}while (z);
	}else{
		for (i=0;i<bo;i++){
			do{
				if (b[i].getNum()==0) cout <<"introduzca X y Y de la bola blanca"<<endl;
				else cout <<"introduzca X y Y de la bola "<<b[i].getNum()<<endl;
				cout <<"X: ";
				cin>>x;
				cout <<"Y: ";
				cin>>y;
				b[i].cargar(x,y,i);
				z=validarB(b,t,r,i);
			}while (z);
		}
		k2=1;
	}
}

int velo(bola b[],int bo){
	for (int i=0;i<bo;i++){
		if((b[i].getVx()>0.1)||(b[i].getVy()>0.1)||(b[i].getVx()<-0.1)||(b[i].getVy()<-0.1)) return 1;
	}
	return 0;
}

int colisionB(bola &b1, bola &b2, int r){
	float sepx,sepy,V,deltax,deltay,delta,xx,yy;
	deltax=b1.getX()-b2.getX();
	deltay=b1.getY()-b2.getY();
	delta=sqrt((pow((deltax),2)+pow((deltay),2)));
	sepx=deltax*((r+r)-delta)/delta;
	sepy=deltay*((r+r)-delta)/delta;
	V=((b1.getVx()-b2.getVx())*(sepx/delta))+((b1.getVy()-b2.getVy())*(sepy/delta));
	if (V<=0.0){
		xx=b1.getVx()+(-V*sepx);
		b1.setVx(xx);
		
		yy=b1.getVy()+(-V*sepy);
		b1.setVy(yy);
		
		xx=b2.getVx()-(-V*sepx);
		b2.setVx(xx);
		
		yy=b2.getVy()-(-V*sepy);
		b2.setVy(yy);
	}
}

void disparo(bola b[],tronera t[],float fr,int r,int bo){
	float x=0,y=0,cont=0;
	cout <<"ingrese la velocidad del disparo"<<endl;
	cout <<"X: ";
	cin >>x;
	cout <<"Y: ";
	cin >>y;
	b[0].setVx(x);
	b[0].setVy(y);
	while ((velo(b,bo))&&(cont <=360)){
		for (int i=0;i<bo;i++){
			if (!(b[i].getTr())){
				if (b[i].getVx()<=-0.1||b[i].getVx()>=0.1||b[i].getVy()<=-0.1||b[i].getVy()>=0.1){
					
					b[i].setX(b[i].getX()+b[i].getVx());
					b[i].setY(b[i].getY()+b[i].getVy()); 
					
					if((b[i].getX()-r)<=t[0].getX()) {b[i].setX(t[0].getX()+r); b[i].setVx(b[i].getVx()*(-1));}
					if((b[i].getX()+r)>=t[5].getX()) {b[i].setX(t[5].getX()-r); b[i].setVx(b[i].getVx()*(-1));}
					if((b[i].getY()+r)>=t[0].getY()) {b[i].setY(t[0].getY()-r); b[i].setVy(b[i].getVy()*(-1));}
					if((b[i].getY()-r)<=t[5].getY()) {b[i].setY(t[5].getY()+r); b[i].setVy(b[i].getVy()*(-1));}
					
					
					b[i].setTro(validarColisionT(b[i].getX(),b[i].getY(),t,r));
					
					if((b[i].getTro())>0) b[i].setTr(true);
				
					for(int j=0;j<bo;j++){
						if (b[i].getNum()!=b[j].getNum()){
							if(!(b[j].getTr())){
								if ((validarColisionB(b[i].getX(),b[i].getY(),b[j].getX(),b[j].getY(),r))) colisionB(b[i],b[j],r);
							}
						}
					}
					b[i].setVx(b[i].getVx()-(b[i].getVx()*fr));
					b[i].setVy(b[i].getVy()-(b[i].getVy()*fr));
				}
			}
		}
		cont++;
	}
	for (int i=0;i<bo;i++){
		b[i].mostrar();
	}
}

void guardarJ(bola b[],tronera t[],int bo,float fr,int r){
	fstream salida;
	salida.open("Partida.txt",ios::out);
	salida<<fr<<" "<<r<<" "<<bo<<endl;
	for(int i=0;i<6;i++){
		salida<<t[i].getX()<<" "<<t[i].getY()<<" "<<t[i].getNum()<<endl;
	}
	for(int i=0;i<bo;i++){
		salida<<b[i].getX()<<" "<<b[i].getY()<<" "<<b[i].getNum()<<" "<<b[i].getTr()<<" "<<b[i].getTro()<<endl;
	}
	salida.close();
}

int cargarJ(bola b[],tronera t[],int bo){
	fstream entrada; float x,y; int num,tro; bool tr;
	entrada.open("Partida.txt",ios::in);
	entrada>>x;
	entrada>>x;
	entrada>>x;
	if(entrada.eof()){ cout <<"Archivo vacio"<<endl; return 0;}
	else{
		for(int i=0;i<6;i++){
			entrada>>x;
			entrada>>y;
			entrada>>num;
			t[i].cargar(x,y,num);		
		}
		for(int i=0;i<bo;i++){
			entrada>>x;
			entrada>>y;
			entrada>>num;
			entrada>>tr;
			entrada>>tro;
			if (num==0)
			b[i].cargar(x,y,num);
			else
			b[i].cargar(x,y,num);
			b[i].setTr(tr);
			b[i].setTro(tro);
		}
		return 1;
	}
	entrada.close();
}

int config(float &fr, int &r, int &bo){
		fstream entrada;
		entrada.open("Partida.txt",ios::in);
		entrada>>fr;
		if(entrada.eof()){ cout <<"Archivo vacio, cargue manualmente la configuracion"<<endl<<endl; return 1;}
		else{
			entrada>>r;
			entrada>>bo;				
			return 0;
		}
		entrada.close();
}

void bolasN(bola b[],int bo){
		for (int i=0;i<bo;i++){
			b[i].setNum(i);
		}
}

void tronerasN(tronera t[]){
		for (int i=0;i<6;i++){
			t[i].setNum(i);
		}
}

int Fin(bola b[],int bo){
	for (int i=1;i<bo;i++){
		if (b[i].getTr()==0) return 0;
	}
	return 1;
}

int main (){
	fstream salida;
	int op,op2,k1=0,k2=0,r,bo,i=1;
	float fr;
	salida.open("Partida.txt",ios::app);
	salida.close();
	cout <<"**********MENU DE CONFIGURACION(RADIO,FRICCION Y CANTIDAD DE BOLAS*********"<<endl;
	cout <<"1. Configuracion Automatica por archivo"<<endl;
	cout <<"2. Configuracion manual"<<endl;
	cin>>op;
	if (op==1) i=config(fr,r,bo);
	if (i==1){ 
		cout <<"**********CONFIGURACION**********"<<endl;
		cout <<"Radio: ";
		cin >>r;
		cout <<"Friccion: ";
		cin >>fr;
		do{
		cout <<"Cantidad de bolas (contando la blanca): ";
		cin >>bo;
		if ((bo<2)||(bo>16)) cout <<"la cantidad de bolas debe ser 2 como minimo y 16 maximo"<<endl;
		}while((bo<2)||(bo>16));
	}
	cout <<"Friccion: "<<fr<<" Radio: "<<r<<" Bolas: "<<bo<<endl;
	op=-1;
	bola b[bo]; bolasN(b,bo);
	tronera t[6]; tronerasN(t);
	while (op!=0){
		cout <<endl<<"**********MENU PRINCIPAL**********"<<endl;
		cout <<"	1. Jugar"<<endl;
		cout <<"	2. Desarrollador"<<endl;
		cout <<"	0. Salir"<<endl<<endl;
		cin >>op;
		op2=-1;
		switch (op){
			case 1: while (op2!=0){
				    cout <<"**********MENU DE JUEGO**********"<<endl;
					cout <<"	1. Cargar mesa"<<endl;
	   	        	cout <<"	2. Cargar bolas"<<endl;
	   	        	cout <<"	3. Hacer disparo"<<endl<<endl;
	   	        	cout <<"	4. Cargar juego"<<endl;
	   	        	cout <<"	5. Guardar juego"<<endl<<endl;
					cout <<"	6. Mostrar estado del juego"<<endl;
	   	        	cout <<"	9. Reset "<<endl;
	   	        	cout <<"	0. Atras"<<endl;
	   	        	cin >>op2;
	   	        	switch (op2){
	   	    	 		case 1: cargarM(t,k1);
	   	     		    	    break;
	   		     		case 2: if (k1==1) cargarB(b,t,bo,r,k2);
	   		     					else cout <<"Carga las mesa primero"<<endl;
	        	    		    break;
	        			case 3: if (b[0].getTr()==true) {cout <<"La bola blanca cayo en una tronera, reposicionela"<<endl; cargarB(b,t,bo,r,k2);}
					    		else if (Fin(b,bo)) cout <<"Fin del juego: todas las bolas estan en troneras"<<endl;
									else if((k2==1)&&(k1==1)) disparo(b,t,fr,r,bo);
					    			else cout <<"Cargue la mesa y las bolas primero"<<endl;
					    	    break;
	        	    	case 4: if(cargarJ(b,t,bo)) {k2=1; k1=1;}
	        	    			break;
	        	    	case 5: guardarJ(b,t,bo,fr,r);
	        	    			break;
						case 6: for (int i=0;i<6;i++){
									t[i].mostrar();
								}
								for (int i=0;i<bo;i++){
									b[i].mostrar();
								}
								break;
	            	}
            	}
		        	break;		
			case 2: cout <<"Nombres: Jesus Santiago"<<endl;
			        cout <<"Apellidos: Martinez Bello"<<endl;
			        cout <<"C.I: 21.194.440"<<endl<<endl;
			        break;
		}
    }
	return 0;
}
