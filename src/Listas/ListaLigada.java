package Listas;

import Grafo.Grafo;
import Nodos.Nodo;
import Nodos.NodoP;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;



public class ListaLigada implements Serializable {
    public Nodo listal;
    public int cont;
    
    public ListaLigada(){
        listal = null;
        cont = 0;
    }
    //este metodo sirve para buscar un elemento pasando correctamente inf, o buscar el final pasando inf = null, o buscar la iesima 
    //posicion pasando inf = null y cont = i-esima posicion, en el caso de los dos primeros basta con pasar cont con -1 o algo que no se vaya a encontrar
     public Nodo buscar(Object inf, Nodo aux,int cont){
        if( aux != null&& aux.inf != inf && aux.next != null && this.cont != cont){
            this.cont++;
            aux = buscar(inf, aux.next,cont);  
        }
        return aux;
    }
    
    //Metodos para insertar
    public void insertarini(Object inf){
        Nodo nuevo = new Nodo(inf) ;
        if(listal == null ){
            listal = nuevo;
        }else{
            nuevo.next = listal;
            listal = nuevo;
        }
    }
    
    public void insertarfin(Object inf){
        Nodo nuevo = new Nodo(inf);
        if(listal == null){
            listal = nuevo;
        }else {
            Nodo aux = listal;
            aux = buscar(null,aux,-1);
            aux.next = nuevo;
        }
    }
    
    public void insertarfinP(Object inf, int peso){
        Nodo nuevo = new Nodo(inf);
        nuevo.peso = peso;
        if(listal == null){
            listal = nuevo;
        }else {
            Nodo aux = listal;
            aux = buscar(null,aux,-1);
            aux.next = nuevo;
        }
    }
    
   public int insertarder(Object inf, Object x,Nodo aux, Integer peso){
       Nodo nuevo = new Nodo(inf);
       nuevo.peso = peso;
       int estado = 0;
        if(listal == null){
            estado = -1;
        }else{
            aux = buscar(x,aux,-1);
            if(aux.inf == x){ //si al regresar aux este no es el numero entonces no se encontro
                nuevo.next = aux.next;
                aux.next = nuevo;
                estado = 1;
            }else {
                estado = -1;
            }
        }
        return estado;
    }
   
   public int insertarizq(Object inf, Object x,Nodo aux, Integer peso){
       Nodo nuevo = new Nodo(inf);
       nuevo.peso = peso;
       Nodo aux1 = aux;
       int estado = 0,cont;
        if(listal == null){
            estado = -1;
        }else{
            this.cont = 0;
            aux = buscar(x,aux,-1);
            cont = this.cont;
            this.cont = 0;
            aux1 = buscar(null,aux1, cont -1);
            if(aux.inf == x && cont > 0 && cont - 1 != -1){ //si al regresar aux este no es el numero entonces no se encontro
                aux1.next = nuevo;
                nuevo.next = aux ;
                estado = 1;
            }else if(cont -1 == -1 || this.cont <= 0 && aux.inf == x){
                nuevo.next = aux;
                listal = nuevo;
            }else{
                estado = -1;
            }
        }
        return estado;
    }
   
   public int insertariesima(int cont, Object inf){
       Nodo aux = listal, aux1 = listal;
       Nodo nuevo = new Nodo(inf);
       int db = 0;
       this.cont = 0;
       aux = buscar(null,aux,cont);
       if (cont == this.cont && cont != 0){
           this.cont = 0;
           aux1 = buscar(null, aux1, cont - 1);
           nuevo.next = aux;
           aux1.next = nuevo;
           db = 1;
       }else if(cont == 0 && this.cont == cont){
           nuevo.next = aux;
           listal = nuevo;
           db = 1;
       }else{
           db = -1;
       }
       return db;
  
   }
   public void Insertarascedente(Object inf, int num) throws Exception{
       Nodo aux = listal.next ;
       Nodo nuevo = new Nodo(inf);
       int ver;
       this.cont = 0 ;
        ver = this.verticeinicial(aux.inf.toString()); 
       
       if(listal != null && aux.next != null){
           while(aux.next != null && num > ver){
               aux = aux.next;
               ver = this.verticeinicial(aux.inf.toString());
           }
           if(num < ver){
                this.insertarizq(nuevo.inf, aux.inf, listal, null);
             }else{
                this.insertarder(nuevo.inf, aux.inf, listal,null);
             }
       }else if(listal != null && aux.next == null){
             if(num < ver){
                this.insertarizq(nuevo.inf, aux.inf, listal,null);
             }else{
                this.insertarder(nuevo.inf, aux.inf, listal,null);
             }
       }else{
           listal = nuevo;
       }
   }
   
   public void InsertarascedenteN(Object inf) throws Exception{
       Nodo aux = listal ;
       Nodo nuevo = new Nodo(inf);
       int ver, num;
       num = this.verticeinicial(((ListaLigada)(inf)).listal.inf.toString() );
       this.cont = 0 ;  
       if(listal != null && aux.next != null){
            ver = this.verticeinicial(((ListaLigada)aux.inf).listal.inf.toString()); 
           while(aux.next != null && num > ver){
               aux = aux.next;
               ver = this.verticeinicial(((ListaLigada)aux.inf).listal.inf.toString()); 
           }
           if(num < ver){
                this.insertarizq(nuevo.inf, aux.inf, listal, null);
             }else{
                this.insertarder(nuevo.inf, aux.inf, listal,null);
             }
       }else if(listal != null && aux.next == null){
            ver = this.verticeinicial(((ListaLigada)aux.inf).listal.inf.toString()); 
             if(num < ver){
                this.insertarizq(nuevo.inf, aux.inf, listal,null);
             }else{
                this.insertarder(nuevo.inf, aux.inf, listal,null);
             }
       }else{
           listal = nuevo;
       }
   }
   
   
   public void InsertarascedenteP(Object inf, int num,int peso) throws Exception{
       Nodo aux = listal.next;
       Nodo nuevo = new Nodo(inf);
       nuevo.peso = peso;
       int ver;
       this.cont = 0 ;
       ver = this.verticeinicial(aux.inf.toString());
       if(listal != null && aux.next != null){
           while(aux.next != null && num > ver){
               aux = aux.next;
               ver = this.verticeinicial(aux.inf.toString());
           }
           if(num < ver){
                this.insertarizq(nuevo.inf, aux.inf, listal, peso);
             }else{
                this.insertarder(nuevo.inf, aux.inf, listal,peso);
             }
       }else if(listal != null && aux.next == null){
             if(num < ver){
                this.insertarizq(nuevo.inf, aux.inf, listal,peso);
             }else{
                this.insertarder(nuevo.inf, aux.inf, listal,peso);
             }
       }else{
           listal = nuevo;
       }
   }
   
   public void InsertarascedenteA(Object inf,int num) throws Exception{
       Nodo aux = listal;
       Nodo nuevo = new Nodo(inf);
       nuevo.peso = num;
       String numS = "";
       char n;
       int num2 = 0, i = 0;
        
       if(listal != null && aux.next != null){
           num2 = aux.peso;
            while(aux.next != null && num > num2){
                aux = aux.next;  
                num2 = aux.peso;
            }
           if(num < num2){
                this.insertarizq(nuevo.inf, aux.inf, listal,num);
             }else{
                this.insertarder(nuevo.inf, aux.inf, listal, num);
             }
       }else if(listal != null && aux.next == null){
            num2 = aux.peso;
            if(num < num2){
               this.insertarizq(nuevo.inf, aux.inf, listal,num);
            }else{
               this.insertarder(nuevo.inf, aux.inf, listal,num);
            }
       }else{
           listal = nuevo;
       }
   }
   
   
   
    public int verticeinicial(String ver) throws Exception{ // este metodo sirve para recibir un valor de tipo string y convertirlo a numero para los arreglos
         int verticei = 0;
         try{
            verticei = Integer.parseInt(ver); //usamos un try-catch si no se puede convertir a un entero entonces no es un numero
        }catch(NumberFormatException e){
            if((ver).charAt(0) >= 'A' && (ver).charAt(0) <= 'Z' ){//usamos el codigo ascii para asignarle un valor numerico a las letras, siendo A = 0, para obtenerlo 
                verticei = (ver).charAt(0) - 65;//para obtenenerlo obtenemos el valor del caracter y le restamos el valor de A en ascii(65), asi B(66)  - A(65) nos da que B = 1
            }else if((ver).charAt(0) >= 'a' && (ver).charAt(0) <= 'z'){//no importa si es mayuscula o minusucla, funciona igual solo cambia el valor de a(97)
                verticei = (ver).charAt(0) - 97;
            }else{
               verticei = 0; // esto podemos usarlo en caso de que el vertice no sea un valor logico como por ejemplo sea una ',', pero no lo uso yo pero ahi esta la posibiladad
            }
        }
         return verticei;
     }
    
    //Metodos para borrar
   
   public Object borrarini() {
        Object db;
        if(listal != null && listal.next == null ){
            db = listal.inf;
            listal = null;
        }else if(listal != null){
            Nodo aux = listal.next;
            db = listal.inf;
            listal = aux;
        }else {
            db = null;
        }
        return db;
    }
   
    public Object borrarfin(){
        Object db;
        int cont;
        if(listal != null && listal.next == null){
            db = listal.inf;
            listal = null;
        }else if(listal != null){
            Nodo aux = listal;
            this.cont = 0;
            buscar(null,aux,-1);
            cont = this.cont;
            this.cont = 0;
            aux = buscar(null,aux,cont - 1);
            db = aux.next.inf;
            aux.next = null;
        }else{
            db = null;
        }
        return db;
    }    
    
    public Object borrarder(Object x){
        Object db = null;
        if(listal == null){
            db = null;
        }else {
            Nodo aux = listal;
            aux =  buscar(x,aux,-1);
            if(aux.inf == x && aux.next != null){
            db = aux.next.inf ;
            aux.next = null;
            }else{
                db = null;
            }
        }
        return db;
    }
    
    public Object borrarizq(Object x){
        Nodo aux = listal, aux1 = listal;
        Object estado = null;
        int cont;
        this.cont = 0;
        aux = buscar(x,aux,-1);
        cont = this.cont;
        if(listal != null && cont == 1){
            estado = listal.inf;
            listal = aux;
        }else if(aux.inf == x && cont > 1){
            this.cont = 0;
            aux1 = buscar(null,aux1,cont - 2);
            //si al regresar aux este no es el numero entonces no se encontro
            estado = aux1.next.inf;
            aux1.next = aux;
        }else{
            estado = null;
        }
        
        return estado;
    }
    
    public Object borrariesima(int cont){
        Object db = null;
        Nodo aux = listal, aux1 = listal;
        this.cont = 0;
        aux = buscar(null,aux,cont);
        if(this.cont == cont && cont == 0){
            db = listal.inf;
        }else if(this.cont == cont && cont > 0){
            this.cont = 0;
            aux1 = buscar(null,aux1,cont-1);
            db = aux.inf;
        }else{
            db = null;
        }
        return db;
    }
    
    public Object borrarx(Object x, int i ) {
        Object db = null;
        Nodo aux = listal,aux1 = listal;
        int cont;
        this.cont = 0;
        this.buscar(x, aux, -1);
        cont = this.cont;
        if(i == 1){
            aux = buscar(x,aux,-1);
            if(listal != null && listal.next == null && x.toString().equals(aux.inf.toString())){
               db = listal.inf;
               listal = null;
            } else if(x.toString().equals(aux.inf.toString()) && aux != listal && cont >0){
                this.cont = 0;
                aux1 = buscar(x,aux1,cont-1);
                db = aux.inf;
                aux1.next = aux.next;
            }else if(aux == listal && x.toString().equals(aux.inf.toString())){
                db = aux.inf;
                listal = aux.next;
            }else{  
                db = null;
            }
            return db;
        }else{
            aux = buscarg(x,aux);
            if(listal != null && listal.next == null && x.toString().equals(((ListaLigada)(aux.inf)).listal.inf.toString())){
                db = listal.inf;
                listal = null;
            } else if(x.toString().equals(((ListaLigada)(aux.inf)).listal.inf.toString()) && aux != listal && cont >0){
                this.cont = 0;
                aux1 = buscarg(x,aux1);
                db = aux.inf;
                aux1.next = aux.next;
            }else if(aux == listal && x.toString().equals(((ListaLigada)(aux.inf)).listal.inf.toString()) ){
                db = aux.inf;
                listal = aux.next;
            }else{  
                db = null;
            }
            return db;
        }
    }
    
    public Nodo buscarg(Object inf, Nodo aux){ // funciona igual que el otro buscar, pero este es para buscar una listaligada y no un valor especifico
        if( aux != null && !((ListaLigada)(aux.inf)).listal.inf.toString().equals(inf)  && aux.next != null){
            aux = buscarg(inf, aux.next);  
            this.cont++;
        }
        return aux;
    }
    
   /* public void borrarTx(Object x){
        ListaLigada db = new ListaLigada();
        Object tem;
        
            while( (tem = this.borrarx(x)) != null){
                db.insertarini(tem); 
            }
        System.out.println("Los Datos Borrados son:\n" + db.ver("",db.listal));
    }
    //recomiendo pasar exps como cadena vacia y aux como listaligada
    public String ver(String exps, Nodo aux) {
        String exp= exps;
        if(aux != null && aux.next != null){
            exps += aux.inf + ", ";
            exp = ver(exps,aux.next);
        }else if(aux != null){
            exp += aux.inf;
        }
        return exp;
    }*/
    
    public ListaLigada invertir(){
        Nodo aux = listal;
        ListaLigada ls = new ListaLigada();
        Object temp;
        int cont;
        this.cont = 0;
        buscar(null,aux,-1);
        cont = this.cont;
        for(int i = 0; i <= cont ; i++){
            temp = this.borrarini();
            ls.insertarini(temp);
        }
        return ls;
    }
    
    public boolean estaVacia() {
    return listal == null;
}

    
   /* public ListaLigada mezclar(Object ls){
        ListaLigada l = (ListaLigada)ls;
        ListaLigada m = new ListaLigada();
        Object temp;
        while((temp = l.borrarini()) != null){
            m.Insertarascedente(temp);
        }
        while((temp = this.borrarini()) != null){
            m.Insertarascedente(temp);
        }
        return m;
    }*/
    
}
