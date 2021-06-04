import java.util.*;

class Cola{
    class nodo{
        String x;
        nodo s;
        nodo(String e){
            x=e;
            s=null;
        }
    }
    nodo P;
    nodo F;
    Cola(){
        P=null;
        F=P;
    }
    void enlistar(String a){ //Se agrega por orden de tiempo menor a mayor
        nodo i=new nodo(a);
        nodo aux=P;
        if (P==null) P=i;//Si esta vacia
        else{
            if(P.x.compareTo(i.x)>0){//Si la primera es mayor
                i.s=P;
                P=i;
            }
            else{
                while(aux.s!=null){//Mientras que la siguiente sea menor
                    if(aux.s.x.compareTo(i.x)<0){
                        aux=aux.s;
                    }else break;
                }
                i.s=aux.s;
                aux.s=i;
            }
        }
    }
    void encolar(String e){
        nodo aux=new nodo(e);
        if(P==null){
            P=aux;
            F=aux;
            P.s=aux.s;
            F.s=aux.s;
        }
        else{
            F.s=aux;
            F=aux;
        }
    }
    void desencolar(){
        P=P.s;
    }
    String getX(){
        return P.x;
    }
    boolean containsKey(String s){
        nodo aux=P;
        while(aux!=null){
            if(aux.x.equals(s)) return true;
            else aux=aux.s;
        }
        return false;
    }
    void imprimir(){
        nodo aux=P;
        while(aux!=null){
            System.out.print(aux.x+" ");
            aux=aux.s;
        }
    }
    boolean vacia(){
        if(P==null) return true;
        else return false;
    }
}

public class Tarea34{
    static Scanner leerI=new Scanner(System.in);
    static Scanner leerS=new Scanner(System.in);
    static HashMap<String,String[]> AFND=new HashMap();
    static HashMap<String,String[]> AFDListo=new HashMap();
    static Cola AFD=new Cola();
    static Cola cadenas=new Cola();
    static Cola finales=new Cola();
    static Cola estados=new Cola();
    static Cola lista=new Cola();
    static String[] a;
    
    static void recorrer(){
        String actual;
        String x;
        while(!cadenas.vacia()){
            actual="0";
            x=cadenas.getX();
            if(!x.equals("NULL")){
                for(int i=0;i<x.length();i++){
                    if(AFND.containsKey(actual)) actual=AFND.get(actual)[Integer.parseInt(Character.toString(x.charAt(i)))];
                    else break;
                }
            }
            if(finales.containsKey(actual)) System.out.println("Aceptada");
            else System.out.println("Rechazada");
            cadenas.desencolar();
        }
    }
    
    static void validar(String s){
        String[] g={"",""};
        if(!AFND.containsKey(s)){
            AFND.put(s, g);
        }
    }
    
    static void fin(String s){
        for(int i=0;i<s.length();i=i+2){
            if(finales.containsKey(Character.toString(s.charAt(i)))){
                finales.enlistar(s);
                break;
            }
        }
    }
    
    static void HacerAFD(){//DETERMINA LOS ESTADOS NUEVOS
        while(!AFD.vacia()){
            String key = AFD.getX();
            if(AFND.containsKey(key)) a=AFND.get(key);
            else{
                a=new String[2];
                a[0]=""; a[1]="";
            }
            for(int i=0;i<key.length();i=i+2){
                validar((Character.toString(key.charAt(i))));
                if(a[0].isEmpty())  a[0]=AFND.get(Character.toString(key.charAt(i)))[0];
                else{
                    if(!AFND.get(Character.toString(key.charAt(i)))[0].equals("")){
                        a[0]=a[0].concat("-");
                        a[0]=a[0].concat(AFND.get(Character.toString(key.charAt(i)))[0]);
                    }
                }
                if(a[1].isEmpty())  a[1]=AFND.get(Character.toString(key.charAt(i)))[1];
                else{
                    if(!AFND.get(Character.toString(key.charAt(i)))[1].equals("")){
                        a[1]=a[1].concat("-");
                        a[1]=a[1].concat(AFND.get(Character.toString(key.charAt(i)))[1]);
                    }
                }
            }
            AFND.put(key, a);
            if(!AFD.containsKey(a[0])&&a[0].length()>1) AFD.encolar(a[0]);
            if(!AFD.containsKey(a[1])&&a[1].length()>1) AFD.encolar(a[1]);
            fin(key);
            AFD.desencolar();
        }
    }
    
    static void LlenarAFD(){//PASA LOS NUEVOS ESTADOS PARA DETERMINAR
        Iterator it = AFND.keySet().iterator();
        while(it.hasNext()){
            String key = it.next().toString();
            if(AFND.get(key)[0].length()>1)
                if(!AFD.containsKey(AFND.get(key)[0])){
                    AFD.encolar(AFND.get(key)[0]);
                    estados.enlistar(AFND.get(key)[0]);
                }
            if(AFND.get(key)[1].length()>1)
                if(!AFD.containsKey(AFND.get(key)[1])){
                    AFD.encolar(AFND.get(key)[1]);
                    estados.enlistar(AFND.get(key)[1]);
                }
        }
        HacerAFD();
    }
    
    public static void main(String[] args) {
        int casos;
        String s;
        casos=leerI.nextInt();
        String[] x={"",""},s2;
        for(int i=0;i<casos;i++){//ciclo de casos
            s=leerS.nextLine();//lee estados
            s2=s.split(" ");
            for(int j=0;j<s2.length;j++){
                estados.enlistar(s2[j]);
            }
            s=leerS.nextLine();//lee finales
            s2=s.split(" ");
            for(int j=0;j<s2.length;j++){
                finales.enlistar(s2[j]);
            }
            do{
                String[] t;
                s=leerS.nextLine();
                if(!s.equals("FC")){
                    t=s.split(" ");
                    if(t.length==1){
                        cadenas.encolar(t[0]);
                    }
                    else{
                        if(AFND.containsKey(t[0])) a=AFND.get(t[0]);
                        else{
                            a=new String[2];
                            a[0]=""; a[1]="";
                        }
                        if(a[Integer.parseInt(t[2])].isEmpty())  a[Integer.parseInt(t[2])]=t[1];
                        else{
                            a[Integer.parseInt(t[2])]=a[Integer.parseInt(t[2])].concat("-");
                            a[Integer.parseInt(t[2])]=a[Integer.parseInt(t[2])].concat(t[1]);
                        }
                        AFND.put(t[0],a);
                    }
                }
            }while(!s.equals("FC"));//Llena el AFND
            LlenarAFD();
            System.out.println("Caso "+(i+1)+":");
            estados.imprimir();
            System.out.println("");
            finales.imprimir();
            System.out.println("");
            
            Iterator it = AFND.keySet().iterator();
            while(it.hasNext()){
              String key = it.next().toString();
              lista.enlistar(key);
            }
            while(!lista.vacia()){
                if(!AFND.get(lista.getX())[0].equals("")) System.out.println(lista.getX()+" "+AFND.get(lista.getX())[0]+" 0");
                if(!AFND.get(lista.getX())[1].equals(""))System.out.println(lista.getX()+" "+AFND.get(lista.getX())[1]+" 1");
                lista.desencolar();
            }
            recorrer();
        }
    }
}