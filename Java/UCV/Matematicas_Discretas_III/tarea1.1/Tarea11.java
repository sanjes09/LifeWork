import java.io.*;
import java.util.*;

class Nodo{
    int X;
    int T;
    Nodo S;
    Nodo(int a, int b){
        X=a;
        T=b;
        S=null;
    }
    void imprimir(){
        Nodo aux=S;
        System.out.println(X+" "+T);
        while(aux!=null){
            System.out.println(aux.X+" "+aux.T);
            aux=aux.S;
        }
    }
};

class Cola{
    Nodo P;
    Cola(){
        P=null;
    }

    void encolar(int a,int b){ //Se agrega por orden de tiempo menor a mayor
        Nodo i=new Nodo(a,b);
        Nodo aux=P;
        if (P==null) P=i;//Si esta vacia
        else{
            if(P.T>=i.T){//Si la primera es mayor
                i.S=P;
                P=i;
            }
            else{
                while(aux.S!=null){//Mientras que la siguiente sea menor
                    if(aux.S.T<=i.T){
                        aux=aux.S;
                    }else break;
                }
                i.S=aux.S;
                aux.S=i;
            }
        }
    }    
       
    boolean existe(){
        return (P!=null);
    }
};

class tiempo{
    int t;
    tiempo(int g){
        t=g;
    }
}

class Grafo{
    Nodo P;
    int tiempo;
    boolean visitado;
    Grafo(){
        P=null;
        tiempo=999999999;
        visitado=false;
    }
    
    void agregar(int a,int b){ //Se agrega por orden de tiempo menor a mayor
        Nodo i=new Nodo(a,b);
        Nodo aux=P;
        if (P==null) P=i;//Si esta vacia
        else{
            if(P.T>=i.T){//Si la primera es mayor
                i.S=P;
                P=i;
            }
            else{
                while(aux.S!=null){//Mientras que la siguiente sea menor
                    if(aux.S.T<i.T){
                        aux=aux.S;
                    }else break;
                }
                i.S=aux.S;
                aux.S=i;
            }
        }     
    }
    
    void imprimir(){
        Nodo aux=P;
        while(aux!=null){
            System.out.println(aux.X+" "+aux.T+" ");
            aux=aux.S;
        }
    }    
    
    boolean existe(){
        return (P!=null);
    }
};

public class Tarea11{
    static HashMap<Integer,Grafo> lista= new HashMap<Integer,Grafo>();
    static Queue<Integer> encargos=new LinkedList<Integer>();
    
    static void ingresar(int a, int b, int t){ //Guarda las nuevas aristas
        Grafo llenar=null;
        if(lista.containsKey(a)){
                llenar=lista.get(a);
                llenar.agregar(b,t);
            }
            else{
                llenar=new Grafo();
                llenar.agregar(b, t);
            }
            lista.put(a,llenar);
    }
      
    static void recorrer(int orden,int inicial,tiempo actual){
        Cola cola=new Cola();
        HashMap<Integer,Grafo> marca=new HashMap();
        marca.putAll(lista);
        Nodo s;
        cola.encolar(inicial, marca.get(inicial).tiempo);
        do{
            inicial=cola.P.X;
            cola.P=cola.P.S;
            s=marca.get(inicial).P;
            marca.get(inicial).visitado=true;
            while(s!=null){
                if(marca.get(s.X).visitado==false&&marca.get(s.X).tiempo>s.T+marca.get(inicial).tiempo){
                    marca.get(s.X).tiempo=s.T+marca.get(inicial).tiempo;
                    cola.encolar(s.X,marca.get(s.X).tiempo);
                }
                s=s.S;
            }
        }while(cola.existe());
        actual.t+=marca.get(orden).tiempo;
    }
    
    public static void main(String[] args) { 
        Scanner leer=new Scanner(System.in);
        Scanner leer2=new Scanner(System.in);
        int caso,a,b,t,encargo,tope2=0,cont=0;
        tiempo total=new tiempo(0);
        tiempo actual=new tiempo(0);
        String s;
        String[] s2;
        
        caso=leer.nextInt();//Lee el caso
        
        s=leer2.nextLine();//Lee las ordenes
        s2=s.split(" ");
        
        while(cont<s2.length){//Llena la cola de ordenes
            encargos.add(Integer.parseInt(s2[cont]));
            cont++;
        }
        
        do{//Lee las aristas
            s=leer2.nextLine();
            s2=s.split(" ");
            if(s2.length<2) break;
            a=Integer.parseInt(s2[0]);
            b=Integer.parseInt(s2[1]);
            t=Integer.parseInt(s2[2]);
            ingresar(a,b,t);//Ingresa las nuevas aristas
            ingresar(b,a,t);
            total.t=total.t+t;
        }while(true);
        
        System.out.println("Caso :"+caso);
        while(!encargos.isEmpty()){
            lista.get(tope2).tiempo=0;
            lista.get(tope2).visitado=true;
            recorrer(encargos.element(),tope2,actual);
            tope2=encargos.element();
            total.t+=actual.t;
            System.out.println("Encargo "+tope2+": "+actual.t);
            encargos.remove();
            //Limpiar Grafo
            Iterator it = lista.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();
                lista.get(e.getKey()).visitado=false;
                lista.get(e.getKey()).tiempo=999999999;
            }
        }
    }
}
