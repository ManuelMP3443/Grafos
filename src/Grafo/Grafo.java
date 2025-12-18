package Grafo;

import Listas.ListaLigada;
import Nodos.Nodo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que representa un grafo usando listas de adyacencia personalizadas.
 * Implementa algoritmos: Prim, Kruskal, Dijkstra, BFS y DFS.
 * Versión refactorizada sin dependencias de UI.
 */
public class Grafo {
    private int vertices;
    private ListaLigada columnas; // Lista de listas de adyacencia
    private ListaLigada posicionesX;
    private ListaLigada posicionesY;
    private int contadorBusqueda;
    private int contadorCiclos;
    
    public Grafo() {
        columnas = new ListaLigada();
        posicionesX = new ListaLigada();
        posicionesY = new ListaLigada();
        vertices = 0;
        contadorBusqueda = 0;
        contadorCiclos = 0;
    }
    
    // ==================== GETTERS ====================
    
    public int getNumeroVertices() {
        return vertices;
    }
    
    public ListaLigada getColumnas() {
        return columnas;
    }
    
    public ListaLigada getPosicionesX() {
        return posicionesX;
    }
    
    public ListaLigada getPosicionesY() {
        return posicionesY;
    }
    
    // ==================== OPERACIONES BÁSICAS ====================
    
    /**
     * Agrega un nuevo vértice al grafo.
     */
    public boolean agregarVertice(Object vertice) throws Exception {
        ListaLigada nuevaLista = new ListaLigada();
        Nodo nodoExistente = buscarVertice(vertice, columnas.listal, -1);
        
        if (columnas.listal == null || 
            !((ListaLigada)(nodoExistente.inf)).listal.inf.toString().equals(vertice.toString())) {
            nuevaLista.insertarfin(vertice);
            columnas.InsertarascedenteN(nuevaLista);
            vertices++;
            return true;
        }
        return false;
    }
    
    /**
     * Agrega una arista entre dos vértices.
     */
    public boolean agregarArista(Object destino, Object origen) throws Exception {
        Nodo nodoOrigen = columnas.listal;
        nodoOrigen = buscarVertice(origen, nodoOrigen, -1);
        
        Nodo aristaExistente = buscarElemento(destino.toString(), 
            ((ListaLigada)(nodoOrigen.inf)).listal, -1);
        
        if (!aristaExistente.inf.toString().equals(destino.toString())) {
            int numeroDestino = convertirVerticeAIndice(destino.toString());
            ListaLigada listaAdyacencia = (ListaLigada)(nodoOrigen.inf);
            
            if (listaAdyacencia.listal.next == null) {
                listaAdyacencia.insertarfin(destino);
            } else {
                listaAdyacencia.Insertarascedente(destino, numeroDestino);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Agrega una arista con peso entre dos vértices.
     */
    public void agregarAristaConPeso(Object destino, Object origen, int peso) throws Exception {
        Nodo nodoOrigen = columnas.listal;
        nodoOrigen = buscarVertice(origen, nodoOrigen, -1);
        
        int numeroDestino = convertirVerticeAIndice(destino.toString());
        ListaLigada listaAdyacencia = (ListaLigada)(nodoOrigen.inf);
        
        if (listaAdyacencia.listal.next == null) {
            listaAdyacencia.insertarfinP(destino, peso);
        } else {
            listaAdyacencia.InsertarascedenteP(destino, numeroDestino, peso);
        }
    }
    
    /**
     * Elimina un vértice del grafo.
     */
    public void eliminarVertice(Object vertice) {
        columnas.borrarx(vertice, 0);
    }
    
    /**
     * Elimina una arista entre dos vértices.
     */
    public void eliminarArista(Object destino, Object origen) {
        Nodo nodoOrigen = columnas.listal;
        nodoOrigen = buscarVertice(origen, nodoOrigen, -1);
        ((ListaLigada)(nodoOrigen.inf)).borrarx(destino, 1);
    }
    
    // ==================== ALGORITMO DE PRIM ====================
    
    public static class ResultadoPrim {
        public String[] orden;
        public Integer[] distancias;
        public String[] padres;
        public int costoTotal;
        
        public ResultadoPrim(int numVertices) {
            orden = new String[numVertices];
            distancias = new Integer[numVertices];
            padres = new String[numVertices];
            costoTotal = 0;
        }
        
        public String obtenerCadenaFormateada() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < orden.length; i++) {
                if (orden[i] != null) {
                    sb.append("[").append(orden[i]).append("] ");
                }
            }
            sb.append("\nEl costo es: ").append(costoTotal);
            return sb.toString();
        }
    }
    
    public ResultadoPrim ejecutarPrim(String verticeInicial) throws Exception {
        ResultadoPrim resultado = new ResultadoPrim(vertices);
        boolean[] visitados = new boolean[vertices];
        
        for (int i = 0; i < vertices; i++) {
            visitados[i] = false;
            resultado.distancias[i] = null;
            resultado.padres[i] = verticeInicial;
        }
        
        int ordenIndex = 0;
        Nodo aux = columnas.listal;
        Nodo nodoInicial = buscarVertice(verticeInicial, columnas.listal, -1);
        
        for (int i = 0; i < vertices; i++) {
            if (aux != null) {
                Nodo aristaAdyacente = buscarElemento(
                    ((ListaLigada)(aux.inf)).listal.inf.toString(),
                    ((ListaLigada)(nodoInicial.inf)).listal, -1);
                
                if (aristaAdyacente.inf.toString().equals(
                    ((ListaLigada)(aux.inf)).listal.inf.toString())) {
                    int indiceVertice = obtenerIndiceVertice(
                        ((ListaLigada)(aux.inf)).listal.inf.toString());
                    resultado.distancias[indiceVertice] = aristaAdyacente.peso;
                }
            }
            aux = aux.next;
        }
        
        int indiceInicial = obtenerIndiceVertice(
            ((ListaLigada)(nodoInicial.inf)).listal.inf.toString());
        resultado.distancias[indiceInicial] = 0;
        visitados[indiceInicial] = true;
        resultado.orden[ordenIndex++] = ((ListaLigada)(nodoInicial.inf)).listal.inf.toString();
        
        aux = nodoInicial;
        int visitadosCount = 0;
        
        while (visitadosCount < vertices - 1) {
            Integer pesoMinimo = null;
            Nodo verticeMinimo = null;
            
            for (int i = 0; i < vertices; i++) {
                if (!visitados[i] && resultado.distancias[i] != null) {
                    if (pesoMinimo == null || pesoMinimo > resultado.distancias[i]) {
                        pesoMinimo = resultado.distancias[i];
                        contadorBusqueda = 0;
                        verticeMinimo = buscarVertice(null, columnas.listal, i);
                    }
                } else if (!visitados[i] && pesoMinimo == null) {
                    pesoMinimo = resultado.distancias[i];
                    contadorBusqueda = 0;
                    verticeMinimo = buscarVertice(null, columnas.listal, i);
                }
            }
            
            if (verticeMinimo == null) break;
            
            Nodo verticeActual = ((ListaLigada)(verticeMinimo.inf)).listal;
            int indiceActual = obtenerIndiceVertice(verticeActual.inf.toString());
            visitados[indiceActual] = true;
            resultado.orden[ordenIndex++] = verticeActual.inf.toString();
            
            Nodo listaAdyacencia = buscarVertice(verticeActual.inf.toString(), 
                columnas.listal, -1);
            Nodo adyacente = ((ListaLigada)(listaAdyacencia.inf)).listal;
            
            while (adyacente.next != null) {
                adyacente = adyacente.next;
                int indiceAdyacente = obtenerIndiceVertice(adyacente.inf.toString());
                
                if (!visitados[indiceAdyacente]) {
                    if (resultado.distancias[indiceAdyacente] != null) {
                        if (resultado.distancias[indiceAdyacente] > adyacente.peso) {
                            resultado.distancias[indiceAdyacente] = adyacente.peso;
                            resultado.padres[indiceAdyacente] = verticeActual.inf.toString();
                        }
                    } else {
                        resultado.distancias[indiceAdyacente] = adyacente.peso;
                        resultado.padres[indiceAdyacente] = verticeActual.inf.toString();
                    }
                }
            }
            
            visitadosCount++;
        }
        
        for (int i = 0; i < vertices; i++) {
            if (resultado.distancias[i] != null) {
                resultado.costoTotal += resultado.distancias[i];
            }
        }
        
        return resultado;
    }
    
    // ==================== ALGORITMO DE KRUSKAL ====================
    
    public static class ResultadoKruskal {
        public String[] aristasSeleccionadas;
        public int costoTotal;
        public int numeroAristas;
        
        public ResultadoKruskal(int numVertices) {
            aristasSeleccionadas = new String[numVertices - 1];
            costoTotal = 0;
            numeroAristas = 0;
        }
        
        public String obtenerCadenaFormateada() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numeroAristas; i++) {
                sb.append("[").append(aristasSeleccionadas[i]).append("] ");
            }
            sb.append("\nEl costo es: ").append(costoTotal);
            return sb.toString();
        }
    }
    
    public ResultadoKruskal ejecutarKruskal() throws Exception {
        ResultadoKruskal resultado = new ResultadoKruskal(vertices);
        boolean[] visitados = new boolean[vertices];
        
        for (int i = 0; i < vertices; i++) {
            visitados[i] = false;
        }
        
        ListaLigada aristas = new ListaLigada();
        Nodo nodoColumna = columnas.listal;
        
        while (nodoColumna != null) {
            Nodo nodoAdyacente = ((ListaLigada)(nodoColumna.inf)).listal;
            String aristaBase = nodoAdyacente.inf.toString() + "-";
            
            while (nodoAdyacente != null) {
                int indiceVertice = obtenerIndiceVertice(nodoAdyacente.inf.toString());
                String aristaNueva = aristaBase;
                
                if (!visitados[indiceVertice] && nodoAdyacente.peso != null) {
                    aristaNueva += nodoAdyacente.inf.toString();
                    aristas.InsertarascedenteA(aristaNueva, nodoAdyacente.peso);
                }
                nodoAdyacente = nodoAdyacente.next;
            }
            
            int indiceColumna = obtenerIndiceVertice(
                ((ListaLigada)(nodoColumna.inf)).listal.inf.toString());
            visitados[indiceColumna] = true;
            nodoColumna = nodoColumna.next;
        }
        
        Grafo subgrafo = new Grafo();
        
        for (int i = 0; i < vertices - 1;) {
            if (aristas.listal == null) break;
            
            int pesoArista = (aristas.listal.peso != null) ? aristas.listal.peso : 0;
            String arista = aristas.borrarini().toString();
            
            String[] partesArista = separarArista(arista);
            String origen = partesArista[0];
            String destino = partesArista[1];
            
            Nodo nodoOrigen = buscarVertice(origen, columnas.listal, -1);
            Nodo nodoDestino = buscarElemento(destino, 
                ((ListaLigada)(nodoOrigen.inf)).listal, -1);
            
            if (!detectarCiclo(subgrafo, nodoOrigen, nodoDestino)) {
                subgrafo.agregarVertice(
                    ((ListaLigada)(nodoOrigen.inf)).listal.inf.toString());
                subgrafo.agregarArista(nodoDestino.inf.toString(), 
                    ((ListaLigada)(nodoOrigen.inf)).listal.inf.toString());
                subgrafo.agregarVertice(nodoDestino.inf.toString());
                subgrafo.agregarArista(
                    ((ListaLigada)(nodoOrigen.inf)).listal.inf.toString(), 
                    nodoDestino.inf.toString());
                
                resultado.aristasSeleccionadas[resultado.numeroAristas++] = arista;
                resultado.costoTotal += pesoArista;
                i++;
            }
        }
        
        return resultado;
    }
    
    private boolean detectarCiclo(Grafo subgrafo, Nodo verticeOrigen, 
                                  Nodo verticeDestino) throws Exception {
        subgrafo.agregarVertice(
            ((ListaLigada)(verticeOrigen.inf)).listal.inf.toString());
        subgrafo.agregarArista(verticeDestino.inf.toString(), 
            ((ListaLigada)(verticeOrigen.inf)).listal.inf.toString());
        subgrafo.agregarVertice(verticeDestino.inf.toString());
        subgrafo.agregarArista(
            ((ListaLigada)(verticeOrigen.inf)).listal.inf.toString(), 
            verticeDestino.inf.toString());
        
        subgrafo.contadorCiclos = 0;
        subgrafo.ejecutarBFS(verticeDestino.inf.toString());
        
        if (subgrafo.contadorCiclos > 0) {
            subgrafo.eliminarArista(verticeDestino.inf.toString(), 
                ((ListaLigada)(verticeOrigen.inf)).listal.inf.toString());
            subgrafo.eliminarArista(
                ((ListaLigada)(verticeOrigen.inf)).listal.inf.toString(), 
                verticeDestino.inf.toString());
            return true;
        }
        return false;
    }
    
    // ==================== ALGORITMO DE DIJKSTRA ====================
    
    public static class ResultadoDijkstra {
        public Integer[] distancias;
        public String[] predecesores;
        public int numVertices;
        
        public ResultadoDijkstra(int numVertices) {
            this.numVertices = numVertices;
            distancias = new Integer[numVertices];
            predecesores = new String[numVertices];
        }
        
        public String obtenerCamino(String verticeDestino, Grafo grafo) throws Exception {
            int indice = grafo.obtenerIndiceVertice(verticeDestino);

            if (distancias[indice] == null) {
                throw new Exception("No existe camino al vértice " + verticeDestino);
            }

            StringBuilder camino = new StringBuilder();
            construirCamino(verticeDestino, grafo, camino);

            camino.setLength(camino.length() - 1); // quitar "-"
            return camino.toString();
        }
        
        private void construirCamino(String vertice, Grafo grafo,
                             StringBuilder camino) throws Exception {

            int indice = grafo.obtenerIndiceVertice(vertice);
            String predecesor = predecesores[indice];
            if (predecesor != null && !vertice.equals(predecesor)) {
                construirCamino(predecesores[indice], grafo, camino);
            }

            camino.append(vertice).append("-");
        }
        
        public String obtenerCaminoConCosto(String verticeDestino, Grafo grafo) 
                throws Exception {
            String camino = obtenerCamino(verticeDestino, grafo);
            int indice = grafo.obtenerIndiceVertice(verticeDestino);
            int costo = (distancias[indice] != null) ? distancias[indice] : 0;
            return camino + "\nEL costo es: " + costo;
        }
    }
    
    public ResultadoDijkstra ejecutarDijkstra(String verticeInicial) throws Exception {
        ResultadoDijkstra resultado = new ResultadoDijkstra(vertices);
        boolean[] visitados = new boolean[vertices];
        
        for (int i = 0; i < vertices; i++) {
            visitados[i] = false;
            resultado.distancias[i] = null;
            resultado.predecesores[i] = null;
        }
        
        Nodo nodoInicial = buscarVertice(verticeInicial, columnas.listal, -1);
        int indiceInicial = obtenerIndiceVertice(verticeInicial);
        visitados[indiceInicial] = true;
        resultado.distancias[indiceInicial] = 0;
        
        Nodo aux = columnas.listal;
        for (int i = 0; i < vertices; i++) {
            if (aux != null) {
                Nodo aristaAdyacente = buscarElemento(
                    ((ListaLigada)(aux.inf)).listal.inf.toString(),
                    ((ListaLigada)(nodoInicial.inf)).listal, -1);
                
                if (aristaAdyacente.inf.toString().equals(
                    ((ListaLigada)(aux.inf)).listal.inf.toString())) {
                    int indiceVertice = obtenerIndiceVertice(
                        ((ListaLigada)(aux.inf)).listal.inf.toString());
                    resultado.distancias[indiceVertice] = aristaAdyacente.peso;
                    resultado.predecesores[indiceVertice] = verticeInicial;
                }
            }
            aux = aux.next;
        }
        
        resultado.distancias[indiceInicial] = 0;
        
        for (int i = 0; i < vertices - 1; i++) {
            Integer pesoMinimo = null;
            Nodo verticeMinimo = null;
            
            for (int j = 0; j < vertices; j++) {
                if (!visitados[j] && resultado.distancias[j] != null) {
                    if (pesoMinimo == null || pesoMinimo > resultado.distancias[j]) {
                        pesoMinimo = resultado.distancias[j];
                        contadorBusqueda = 0;
                        verticeMinimo = buscarVertice(null, columnas.listal, j);
                    }
                } else if (!visitados[j] && pesoMinimo == null) {
                    pesoMinimo = resultado.distancias[j];
                    contadorBusqueda = 0;
                    verticeMinimo = buscarVertice(null, columnas.listal, j);
                }
            }
            
            if (verticeMinimo == null) break;
            
            aux = verticeMinimo;
            aux = ((ListaLigada)(verticeMinimo.inf)).listal;
            int indiceActual = obtenerIndiceVertice(aux.inf.toString());
            visitados[indiceActual] = true;
            
            while (aux != null) {
                int indiceAdyacente = obtenerIndiceVertice(aux.inf.toString());
                
                if (!visitados[indiceAdyacente]) {
                    int nuevaDistancia = resultado.distancias[indiceActual] + aux.peso;
                    
                    if (resultado.distancias[indiceAdyacente] != null && 
                        nuevaDistancia < resultado.distancias[indiceAdyacente]) {
                        resultado.distancias[indiceAdyacente] = nuevaDistancia;
                        resultado.predecesores[indiceAdyacente] = 
                            ((ListaLigada)(verticeMinimo.inf)).listal.inf.toString();
                    } else if (resultado.distancias[indiceAdyacente] == null) {
                        resultado.distancias[indiceAdyacente] = nuevaDistancia;
                        resultado.predecesores[indiceAdyacente] = 
                            ((ListaLigada)(verticeMinimo.inf)).listal.inf.toString();
                    }
                }
                aux = aux.next;
            }
        }
        
        return resultado;
    }
    
    // ==================== BÚSQUEDAS BFS/DFS ====================
    
    public static class ResultadoBusqueda {
        public String[] ordenVisita;
        public int numeroVerticesVisitados;
        public String recorridoFormateado;
        
        public ResultadoBusqueda(int numVertices) {
            ordenVisita = new String[numVertices];
            numeroVerticesVisitados = 0;
            recorridoFormateado = "";
        }
    }
    
    public ResultadoBusqueda ejecutarBFS(String verticeInicial) throws Exception {
        ResultadoBusqueda resultado = new ResultadoBusqueda(vertices);
        boolean[] visitados = new boolean[vertices];
        ListaLigada cola = new ListaLigada();
        StringBuilder recorrido = new StringBuilder();
        
        for (int i = 0; i < vertices; i++) {
            visitados[i] = false;
        }
        
        Nodo nodoInicial = buscarVertice(verticeInicial, columnas.listal, -1);
        int indiceInicial = obtenerIndiceVertice(
            ((ListaLigada)(nodoInicial.inf)).listal.inf.toString());
        visitados[indiceInicial] = true;
        cola.insertarfin(((ListaLigada)(nodoInicial.inf)).listal);
        resultado.ordenVisita[resultado.numeroVerticesVisitados++] = 
            ((ListaLigada)(nodoInicial.inf)).listal.inf.toString();
        
        Object nodoSacado;
        while ((nodoSacado = cola.borrarini()) != null) {
            Nodo nodoColumna = buscarVertice(((Nodo)nodoSacado).inf, 
                columnas.listal, -1);
            
            ListaLigada listaAdyacencia = (ListaLigada)(nodoColumna.inf);
            listaAdyacencia.cont = 0;
            listaAdyacencia.buscar(null, listaAdyacencia.listal, -1);
            int numAdyacentes = listaAdyacencia.cont;
            
            Nodo adyacente = listaAdyacencia.listal;
            int ciclosDetectados = 0;
            
            recorrido.append("\n")
                     .append(((ListaLigada)(nodoColumna.inf)).listal.inf.toString())
                     .append(": ");
            
            for (int i = 0; i <= numAdyacentes; i++) {
                int indiceAdyacente = obtenerIndiceVertice(adyacente.inf.toString());
                
                if (!visitados[indiceAdyacente]) {
                    recorrido.append(adyacente.inf.toString()).append(", ");
                    resultado.ordenVisita[resultado.numeroVerticesVisitados++] = 
                        adyacente.inf.toString();
                    visitados[indiceAdyacente] = true;
                    cola.insertarfin(adyacente);
                } else if (i > 0) {
                    ciclosDetectados++;
                }
                
                adyacente = adyacente.next;
                if (adyacente != null) {
                    indiceAdyacente = obtenerIndiceVertice(adyacente.inf.toString());
                }
            }
            
            if (ciclosDetectados > 1) {
                this.contadorCiclos++;
            }
        }
        
        resultado.recorridoFormateado = recorrido.toString();
        return resultado;
    }
    
    public ResultadoBusqueda ejecutarDFS(String verticeInicial) throws Exception {
        ResultadoBusqueda resultado = new ResultadoBusqueda(vertices);
        boolean[] visitados = new boolean[vertices];
        
        for (int i = 0; i < vertices; i++) {
            visitados[i] = false;
        }
        
        StringBuilder recorrido = new StringBuilder();
        Nodo nodoInicial = buscarVertice(verticeInicial, columnas.listal, -1);
        
        ejecutarDFSRecursivo(nodoInicial, visitados, resultado, recorrido);
        resultado.recorridoFormateado = recorrido.toString();
        
        return resultado;
    }
    
    private void ejecutarDFSRecursivo(Nodo nodoActual, boolean[] visitados, 
                                     ResultadoBusqueda resultado, 
                                     StringBuilder recorrido) throws Exception {
        int indiceActual = obtenerIndiceVertice(
            ((ListaLigada)(nodoActual.inf)).listal.inf.toString());
        visitados[indiceActual] = true;
        resultado.ordenVisita[resultado.numeroVerticesVisitados++] = 
            ((ListaLigada)(nodoActual.inf)).listal.inf.toString();
        
        recorrido.append(((ListaLigada)(nodoActual.inf)).listal.inf.toString())
                 .append(", ");
        
        ListaLigada listaAdyacencia = (ListaLigada)(nodoActual.inf);
        listaAdyacencia.cont = 0;
        listaAdyacencia.buscar(null, listaAdyacencia.listal, -1);
        int numAdyacentes = listaAdyacencia.cont;
        
        Nodo adyacente = listaAdyacencia.listal;
        
        for (int i = 0; i < numAdyacentes; i++) {
            adyacente = adyacente.next;
            int indiceAdyacente = obtenerIndiceVertice(adyacente.inf.toString());
            
            if (!visitados[indiceAdyacente]) {
                Nodo nodoAdyacente = buscarVertice(adyacente.inf.toString(), 
                    columnas.listal, -1);
                ejecutarDFSRecursivo(nodoAdyacente, visitados, resultado, recorrido);
            }
        }
    }
    
    // ==================== LECTURA DE ARCHIVOS ====================
    
    public void leerGrafoDesdeArchivo(int numeroGrafo) throws FileNotFoundException, IOException {
        String linea = " ";
        String nodo = "";
        String nodoInicial = "";
        int i, j;
        vertices = 0;
        
        try {
            BufferedReader buffer = new BufferedReader(
                new FileReader("Grafo" + numeroGrafo + ".txt"));
            
            try {
                while (linea != null) {
                    linea = buffer.readLine();
                    i = 0;
                    j = 0;
                    
                    while (j < linea.length()) {
                        while (j < linea.length() && linea.charAt(j) != ',') {
                            nodo += linea.charAt(j);
                            j++;
                        }
                        j++;
                        
                        if (i == 0) {
                            this.agregarVertice(nodo);
                            nodoInicial = nodo;
                        } else {
                            this.agregarArista(nodo, nodoInicial);
                        }
                        nodo = "";
                        i++;
                    }
                }
            } catch (Exception e) {
                // Fin del archivo
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No existe ese grafo");
        }
    }
    
    public DatosGrafo leerGrafoConPesos(int numeroGrafo) throws FileNotFoundException, IOException {
        String linea = " ";
        String nodo = "";
        String peso = "";
        String nodoInicial = "";
        int i, j;
        vertices = 0;
        
        try {
            BufferedReader buffer = new BufferedReader(
                new FileReader("recursos\\grafo\\Grafo" + numeroGrafo + ".txt"));
            
            try {
                while (linea != null) {
                    linea = buffer.readLine();
                    i = 0;
                    j = 0;
                    
                    while (j < linea.length()) {
                        while (j < linea.length() && linea.charAt(j) != ',') {
                            if (linea.charAt(j) != '.') {
                                nodo += linea.charAt(j);
                            } else {
                                j++;
                                while (j < linea.length() && linea.charAt(j) != ',') {
                                    peso += linea.charAt(j);
                                    j++;
                                }
                                j--;
                            }
                            j++;
                        }
                        j++;
                        
                        if (i == 0) {
                            this.agregarVertice(nodo);
                            nodoInicial = nodo;
                        } else {
                            this.agregarAristaConPeso(nodo, nodoInicial, 
                                Integer.parseInt(peso));
                        }
                        peso = "";
                        nodo = "";
                        i++;
                    }
                }
            } catch (Exception e) {
                // Fin del archivo
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No existe ese grafo");
        }
        
        return leerPosicionesGrafo(numeroGrafo);
    }
    
    public static class DatosGrafo {
        public int ancho;
        public int alto;
        public String rutaImagen;
        
        public DatosGrafo(int ancho, int alto, String rutaImagen) {
            this.ancho = ancho;
            this.alto = alto;
            this.rutaImagen = rutaImagen;
        }
    }
    
    private DatosGrafo leerPosicionesGrafo(int numeroGrafo) throws FileNotFoundException, IOException {
        String coordenada = "";
        String linea = "";
        int j = 0, i = 0;
        int ancho = 0, alto = 0;
        
        try {
            BufferedReader buffer = new BufferedReader(
                new FileReader("recursos\\posiciones\\Grafopos" + numeroGrafo + ".txt"));
            
            try {
                linea = buffer.readLine();
                
                while (j < linea.length()) {
                    while (j < linea.length() && linea.charAt(j) != ',') {
                        coordenada += linea.charAt(j);
                        j++;
                    }
                    j++;
                    
                    if (i == 0) {
                        ancho = Integer.parseInt(coordenada);
                        i++;
                    } else {
                        alto = Integer.parseInt(coordenada);
                    }
                    coordenada = "";
                }
                
                while (linea != null) {
                    linea = buffer.readLine();
                    j = 0;
                    i = 0;
                    
                    while (j < linea.length()) {
                        while (j < linea.length() && linea.charAt(j) != ',') {
                            coordenada += linea.charAt(j);
                            j++;
                        }
                        j++;
                        
                        if (i == 0) {
                            posicionesX.insertarfin(coordenada);
                            i++;
                        } else {
                            posicionesY.insertarfin(coordenada);
                        }
                        coordenada = "";
                    }
                }
            } catch (Exception e) {
                // Fin del archivo
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No existe ese grafo");
        }
        
        return new DatosGrafo(ancho, alto, 
            "recursos\\Imagenes\\Grafo" + numeroGrafo + ".png");
    }
    
    // ==================== UTILIDADES ====================
    
    public int obtenerGrado(String vertice) {
        Nodo aux = buscarVertice(vertice, columnas.listal, -1);
        
        if (((ListaLigada)(aux.inf)).listal.inf.toString().equals(vertice)) {
            ((ListaLigada)(aux.inf)).cont = 0;
            ((ListaLigada)(aux.inf)).buscar(null, 
                ((ListaLigada)(aux.inf)).listal, -1);
            return ((ListaLigada)(aux.inf)).cont;
        }
        return -1;
    }
    
    public String[] obtenerVerticesAdyacentes(String vertice) {
        int grado = obtenerGrado(vertice);
        if (grado == -1) return null;
        
        String[] adyacentes = new String[grado];
        Nodo aux = buscarVertice(vertice, columnas.listal, -1);
        aux = ((ListaLigada)(aux.inf)).listal;
        
        int i = 0;
        while (i < grado) {
            aux = aux.next;
            adyacentes[i] = aux.inf.toString();
            i++;
        }
        
        return adyacentes;
    }
    
    public void mostrarGrafoEnConsola() {
        Nodo aux = columnas.listal;
        Nodo aux1;
        
        if (columnas != null) {
            while (aux != null) {
                aux1 = ((ListaLigada)(aux.inf)).listal;
                
                while (aux1 != null) {
                    if (aux1.peso != null) {
                        System.out.print("|" + aux1.inf + "|" + aux1.peso + "|-> ");
                    } else {
                        System.out.print("|" + aux1.inf + "|-> ");
                    }
                    aux1 = aux1.next;
                }
                System.out.println(" null");
                aux = aux.next;
            }
        }
    }
    
    public void limpiarPosiciones() {
        if (!posicionesX.estaVacia()) {
            while (posicionesX.borrarfin() != null && posicionesY.borrarfin() != null) {}
        }
    }
    
    // ==================== MÉTODOS DE BÚSQUEDA INTERNOS ====================
    
    private Nodo buscarVertice(Object vertice, Nodo nodo, int posicion) {
        if (nodo != null && 
            !((ListaLigada)(nodo.inf)).listal.inf.toString().equals(vertice) && 
            nodo.next != null && posicion != this.contadorBusqueda) {
            this.contadorBusqueda++;
            nodo = buscarVertice(vertice, nodo.next, posicion);
        }
        return nodo;
    }
    
    private Nodo buscarElemento(Object elemento, Nodo nodo, int posicion) {
        if (nodo != null && 
            !nodo.inf.toString().equals(elemento.toString()) && 
            nodo.next != null && this.contadorBusqueda != posicion) {
            this.contadorBusqueda++;
            nodo = buscarElemento(elemento, nodo.next, posicion);
        }
        return nodo;
    }
    
    public int obtenerIndiceVertice(String vertice) throws Exception {
        Nodo aux = columnas.listal;
        contadorBusqueda = 0;
        buscarVertice(vertice, aux, -1);
        return contadorBusqueda;
    }
    
    private int convertirVerticeAIndice(String vertice) throws Exception {
        int indice = 0;
        try {
            indice = Integer.parseInt(vertice);
        } catch (NumberFormatException e) {
            if (vertice.charAt(0) >= 'A' && vertice.charAt(0) <= 'Z') {
                indice = vertice.charAt(0) - 65;
            } else if (vertice.charAt(0) >= 'a' && vertice.charAt(0) <= 'z') {
                indice = vertice.charAt(0) - 97;
            } else {
                indice = 0;
            }
        }
        return indice;
    }
    
    private String[] separarArista(String arista) {
        String[] partes = new String[2];
        partes[0] = "";
        partes[1] = "";
        boolean encontradoSeparador = false;
        
        for (int i = 0; i < arista.length(); i++) {
            if (arista.charAt(i) != '-' && !encontradoSeparador) {
                partes[0] += arista.charAt(i);
            } else if (arista.charAt(i) == '-' || encontradoSeparador) {
                if (arista.charAt(i) == '-') {
                    i++;
                }
                partes[1] += arista.charAt(i);
                encontradoSeparador = true;
            }
        }
        
        return partes;
    }
}