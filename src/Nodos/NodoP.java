package Nodos;


import java.io.Serializable;

public class NodoP implements Serializable{
    public Object inf;
    public NodoP next;
    public Object peso;
    public NodoP(Object inf,Object peso){
        this.inf = inf;
        next = null;
        this.peso = peso;
    }
}