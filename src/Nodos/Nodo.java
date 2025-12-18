package Nodos;


import java.io.Serializable;

public class Nodo implements Serializable{
    public Object inf;
    public Integer peso;
    public Nodo next;
    public Nodo(Object inf){
        this.inf = inf;
        next = null;
        peso = null;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
}
