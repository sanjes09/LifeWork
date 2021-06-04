import java.util.*;

class Nodo{
    String Persona;
    String Gen;
    Nodo S;
    Nodo(String a, String b){
        Persona=a;
        Gen=b;
        S=null;
    }
    void imprimir(){
        Nodo aux=S;
        System.out.println(Persona+" "+Gen);
        while(aux!=null){
            System.out.println(aux.Persona+" "+aux.Gen);
            aux=aux.S;
        }
    }
};

class Lista{
    Nodo P;
    Lista(){
        P=null;
    }

    void enlistar(String a,String b){ //Se agrega por orden de tiempo menor a mayor
        Nodo i=new Nodo(a,b);
        Nodo aux=P;
        if (P==null) P=i;//Si esta vacia
        else{
            if(P.Persona.compareTo(i.Persona)>0){//Si la primera es mayor
                i.S=P;
                P=i;
            }
            else{
                while(aux.S!=null){//Mientras que la siguiente sea menor
                    if(aux.S.Persona.compareTo(i.Persona)<0){
                        aux=aux.S;
                    }else break;
                }
                i.S=aux.S;
                aux.S=i;
            }
        }
    }
};

public class Tarea2 {
    static HashMap<String,String> gen=new HashMap();
    static HashMap<String,String[]> padres=new HashMap();
    static Lista lista=new Lista();
    
    static void calcular2(String key){
        String s="X";
        if(gen.get(padres.get(key)[0]).compareTo("X")==0) calcular2(padres.get(key)[0]);
        if(gen.get(padres.get(key)[1]).compareTo("X")==0) calcular2(padres.get(key)[1]);
        if((gen.get(padres.get(key)[0]).equals("Dominante")&&gen.get(padres.get(key)[1]).equals("Dominante"))||(gen.get(padres.get(key)[0]).equals("Dominante")&&gen.get(padres.get(key)[1]).equals("Recesivo"))||(gen.get(padres.get(key)[0]).equals("Recesivo")&&gen.get(padres.get(key)[1]).equals("Dominante"))){
            s="Dominante";
        }
        if((gen.get(padres.get(key)[0]).equals("Inexistente")&&gen.get(padres.get(key)[1]).equals("Dominante"))||(gen.get(padres.get(key)[0]).equals("Dominante")&&gen.get(padres.get(key)[1]).equals("Inexistente"))||(gen.get(padres.get(key)[0]).equals("Recesivo")&&gen.get(padres.get(key)[1]).equals("Recesivo"))){
            s="Recesivo";
        }
        if((gen.get(padres.get(key)[0]).equals("Inexistente")&&gen.get(padres.get(key)[1]).equals("Recesivo"))||(gen.get(padres.get(key)[0]).equals("Recesivo")&&gen.get(padres.get(key)[1]).equals("Inexistente"))||(gen.get(padres.get(key)[0]).equals("Inexistente")&&gen.get(padres.get(key)[1]).equals("Inexistente"))){
            s="Inexistente";
        }
        gen.put(key,s);
    }
    
    static void calcular(){
        Iterator it = gen.keySet().iterator();
        while(it.hasNext()){
          String key = it.next().toString();
          if(gen.get(key).equals("X")){
              calcular2(key);
          }
          lista.enlistar(key, gen.get(key));
        }
    }
    
    public static void main(String[] args) {
        Scanner leerInt=new Scanner(System.in);
        Scanner leerStr=new Scanner(System.in);
        int casos,nombres;
        String s;
        String[] s2,pad;
        casos=leerInt.nextInt();
        for(int i=0;i<casos;i++){
            nombres=leerInt.nextInt();
            for(int j=0;j<nombres;j++){
                pad=new String[2];
                pad[0]=""; pad[1]="";
                s=leerStr.nextLine();
                s2=s.split(" ");
                if(s2[1].equals("Recesivo")||s2[1].equals("Inexistente")||s2[1].equals("Dominante")) gen.put(s2[0], s2[1]);
                else{
                    if(padres.containsKey(s2[1])){
                        pad=padres.get(s2[1]);
                        //padres.remove(s2[1]);
                        pad[1]=s2[0];
                    }else pad[0]=s2[0];
                    padres.put(s2[1], pad);
                    if(!gen.containsKey(s2[1])) gen.put(s2[1],"X");
                }
            }
            calcular();
            System.out.println("");
            System.out.println("Caso: "+(i+1));
            lista.P.imprimir();
        }
    }
}
