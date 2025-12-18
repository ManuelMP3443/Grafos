package Main;

import Grafo.Grafo;
import Interfaz.Ventana;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Interfaz gráfica para visualizar algoritmos de grafos.
 * Actúa como puente entre la lógica de negocio (Grafo) y la UI (Ventana).
 */
public class MainF extends javax.swing.JFrame {
    private Grafo grafo;
    private Ventana ventanaVisualizacion;
    private int anchoInicial;
    private int altoInicial;
    private Point posicionPanelInicial;
    
    public MainF() {
        initComponents();
        inicializarComponentes();
        configurarListeners();
    }
    
    private void inicializarComponentes() {
        grafo = new Grafo();
        ventanaVisualizacion = new Ventana();
        anchoInicial = getWidth();
        altoInicial = getHeight();
        posicionPanelInicial = jPanel1.getLocation();
        actualizarCamposSegunAlgoritmo();
    }
    
    private void configurarListeners() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCamposSegunAlgoritmo();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        comboBox1 = new javax.swing.JComboBox<>();
        numGL = new javax.swing.JLabel();
        NumG = new javax.swing.JTextField();
        verf = new javax.swing.JTextField();
        verfL = new javax.swing.JLabel();
        boton1 = new javax.swing.JButton();
        veri = new javax.swing.JTextField();
        veriL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Aqui se mostrara el resultado");
        jTextArea1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        comboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prim", "Kruskal", "Dijkstra" }));
        comboBox1.setBorder(null);

        numGL.setText("No.Grafo");

        NumG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumGActionPerformed(evt);
            }
        });

        verf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verfActionPerformed(evt);
            }
        });

        verfL.setText("Vertice Final");

        boton1.setText("Mostrar");
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        veriL.setText("Vertice Inicial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NumG, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(veri, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numGL, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(veriL, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(verfL, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(verf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(boton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numGL)
                            .addComponent(veriL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(veri)
                            .addComponent(NumG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(verfL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>

    private void NumGActionPerformed(java.awt.event.ActionEvent evt) {
        this.cargarGrafo();
    }

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            ejecutarAlgoritmo();
        } catch (Exception ex) {
            Logger.getLogger(MainF.class.getName()).log(Level.SEVERE, null, ex);
            jTextArea1.setText("Error al ejecutar el algoritmo: " + ex.getMessage());
        }
    }

    private void verfActionPerformed(java.awt.event.ActionEvent evt) {
        // No se requiere acción
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField NumG;
    private javax.swing.JButton boton1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel numGL;
    private javax.swing.JTextField verf;
    private javax.swing.JLabel verfL;
    private javax.swing.JTextField veri;
    private javax.swing.JLabel veriL;
    // End of variables declaration

    // ==================== MÉTODOS PRIVADOS DE LÓGICA ====================

    /**
     * Actualiza la visibilidad de campos según el algoritmo seleccionado.
     */
    private void actualizarCamposSegunAlgoritmo() {
        String algoritmoSeleccionado = (String) comboBox1.getSelectedItem();
        
        switch (algoritmoSeleccionado.charAt(0)) {
            case 'P': // Prim
                verfL.setVisible(false);
                verf.setVisible(false);
                veriL.setVisible(true);
                veri.setVisible(true);
                break;
                
            case 'K': // Kruskal
                verfL.setVisible(false);
                verf.setVisible(false);
                veriL.setVisible(false);
                veri.setVisible(false);
                break;
                
            case 'D': // Dijkstra
                verfL.setVisible(true);
                verf.setVisible(true);
                veriL.setVisible(true);
                veri.setVisible(true);
                break;
        }
    }

    /**
     * Ejecuta el algoritmo seleccionado y muestra los resultados.
     */
    private void ejecutarAlgoritmo() throws Exception {
        this.cargarGrafo();
        String verticeInicial = veri.getText();
        String verticeFinal = verf.getText();
        String algoritmo = (String) comboBox1.getSelectedItem();
        
        jTextArea1.setText("");
        
        switch (algoritmo.charAt(0)) {
            case 'P': // Prim
                ejecutarPrim(verticeInicial);
                break;
                
            case 'K': // Kruskal
                ejecutarKruskal();
                break;
                
            case 'D': // Dijkstra
                ejecutarDijkstra(verticeInicial, verticeFinal);
                break;
                
            default:
                jTextArea1.setText("Algoritmo no válido");
                break;
        }
    }

    /**
     * Ejecuta el algoritmo de Prim y visualiza el resultado.
     */
    private void ejecutarPrim(String verticeInicial) throws Exception {
        if (verticeInicial == null || verticeInicial.trim().isEmpty()) {
            jTextArea1.setText("Error: Ingrese el vértice inicial");
            return;
        }
        
        Grafo.ResultadoPrim resultado = grafo.ejecutarPrim(verticeInicial);
        
        // Mostrar texto del resultado
        jTextArea1.append(resultado.obtenerCadenaFormateada());
        
        // Dibujar líneas en la visualización
        dibujarArbolPrim(resultado);
    }

    /**
     * Ejecuta el algoritmo de Kruskal y visualiza el resultado.
     */
    private void ejecutarKruskal() throws Exception {
        Grafo.ResultadoKruskal resultado = grafo.ejecutarKruskal();
        
        // Mostrar texto del resultado
        jTextArea1.append(resultado.obtenerCadenaFormateada());
        
        // Dibujar líneas en la visualización
        dibujarArbolKruskal(resultado);
    }

    /**
     * Ejecuta el algoritmo de Dijkstra y visualiza el resultado.
     */
    private void ejecutarDijkstra(String verticeInicial, String verticeFinal) throws Exception {
        if (verticeInicial == null || verticeInicial.trim().isEmpty()) {
            jTextArea1.setText("Error: Ingrese el vértice inicial");
            return;
        }
        
        if (verticeFinal == null || verticeFinal.trim().isEmpty()) {
            jTextArea1.setText("Error: Ingrese el vértice final");
            return;
        }
        
        Grafo.ResultadoDijkstra resultado = grafo.ejecutarDijkstra(verticeInicial);
        
        // Mostrar texto del resultado
        String camino = resultado.obtenerCaminoConCosto(verticeFinal, grafo);
        jTextArea1.append(camino);
        
        // Dibujar líneas en la visualización
        dibujarCaminoDijkstra(resultado, verticeFinal);
    }

    /**
     * Dibuja las líneas del árbol de expansión mínima de Prim.
     */
    private void dibujarArbolPrim(Grafo.ResultadoPrim resultado) throws Exception {
        if (grafo.getPosicionesX().estaVacia()) {
            return; // No hay posiciones para dibujar
        }
        
        for (int i = 0; i < grafo.getNumeroVertices(); i++) {
            if (resultado.orden[i] == null || resultado.padres[i] == null) {
                continue;
            }
            
            // Obtener posiciones del vértice actual
            int posX1 = Integer.parseInt(
                grafo.getPosicionesX().borrariesima(i).toString());
            int posY1 = Integer.parseInt(
                grafo.getPosicionesY().borrariesima(i).toString());
            
            // Obtener posiciones del padre
            int indicePadre = grafo.obtenerIndiceVertice(resultado.padres[i]);
            int posX2 = Integer.parseInt(
                grafo.getPosicionesX().borrariesima(indicePadre).toString());
            int posY2 = Integer.parseInt(
                grafo.getPosicionesY().borrariesima(indicePadre).toString());
            
            ventanaVisualizacion.dibujarL(posX1, posX2, posY1, posY2);
        }
    }

    /**
     * Dibuja las líneas del árbol de expansión mínima de Kruskal.
     */
    private void dibujarArbolKruskal(Grafo.ResultadoKruskal resultado) throws Exception {
        if (grafo.getPosicionesX().estaVacia()) {
            return; // No hay posiciones para dibujar
        }
        
        for (int i = 0; i < resultado.numeroAristas; i++) {
            String arista = resultado.aristasSeleccionadas[i];
            String[] vertices = separarArista(arista);
            
            int indice1 = grafo.obtenerIndiceVertice(vertices[0]);
            int indice2 = grafo.obtenerIndiceVertice(vertices[1]);
            
            int posX1 = Integer.parseInt(
                grafo.getPosicionesX().borrariesima(indice1).toString());
            int posY1 = Integer.parseInt(
                grafo.getPosicionesY().borrariesima(indice1).toString());
            int posX2 = Integer.parseInt(
                grafo.getPosicionesX().borrariesima(indice2).toString());
            int posY2 = Integer.parseInt(
                grafo.getPosicionesY().borrariesima(indice2).toString());
            
            ventanaVisualizacion.dibujarL(posX1, posX2, posY1, posY2);
        }
    }

    /**
     * Dibuja las líneas del camino más corto de Dijkstra.
     */
    private void dibujarCaminoDijkstra(Grafo.ResultadoDijkstra resultado, 
                                       String verticeFinal) throws Exception {
        if (grafo.getPosicionesX().estaVacia()) {
            return; // No hay posiciones para dibujar
        }
        
        dibujarCaminoRecursivo(resultado, verticeFinal);
    }

    /**
     * Método recursivo auxiliar para dibujar el camino de Dijkstra.
     */
    private void dibujarCaminoRecursivo(Grafo.ResultadoDijkstra resultado, 
                                        String verticeActual) throws Exception {
        int indiceActual = grafo.obtenerIndiceVertice(verticeActual);
        
        if (resultado.predecesores[indiceActual] != null && 
            !resultado.predecesores[indiceActual].equals(verticeActual)) {
            
            dibujarCaminoRecursivo(resultado, resultado.predecesores[indiceActual]);
            
            int indicePadre = grafo.obtenerIndiceVertice(
                resultado.predecesores[indiceActual]);
            
            int posX1 = Integer.parseInt(
                grafo.getPosicionesX().borrariesima(indiceActual).toString());
            int posY1 = Integer.parseInt(
                grafo.getPosicionesY().borrariesima(indiceActual).toString());
            int posX2 = Integer.parseInt(
                grafo.getPosicionesX().borrariesima(indicePadre).toString());
            int posY2 = Integer.parseInt(
                grafo.getPosicionesY().borrariesima(indicePadre).toString());
            
            ventanaVisualizacion.dibujarL(posX1, posX2, posY1, posY2);
        }
    }

    /**
     * Limpia el grafo anterior y reinicia la visualización.
     */
    private void limpiarGrafoAnterior() {
        grafo.limpiarPosiciones();
        ventanaVisualizacion.borrarImagen();
        grafo = new Grafo();
        jTextArea1.setText("Aquí se mostrará el resultado");
    }

    /**
     * Configura la visualización con los datos del grafo cargado.
     */
    private void configurarVisualizacion(Grafo.DatosGrafo datos) {
        Dimension dimensionGrafo = new Dimension(datos.ancho, datos.alto);
        
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setPreferredSize(dimensionGrafo);
        jPanel1.add(ventanaVisualizacion, BorderLayout.CENTER);
        
        ventanaVisualizacion.cargarImagen(datos.rutaImagen);
        
        // Ajustar tamaño de la ventana si es necesario
        ajustarTamanoVentana(dimensionGrafo);
        
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    /**
     * Ajusta el tamaño de la ventana para acomodar el grafo.
     */
    private void ajustarTamanoVentana(Dimension dimensionGrafo) {
        int nuevoAncho = anchoInicial;
        int nuevoAlto = altoInicial;
        
        int anchoNecesario = posicionPanelInicial.x + dimensionGrafo.width;
        int altoNecesario = posicionPanelInicial.y + dimensionGrafo.height;
        
        if (anchoNecesario > anchoInicial) {
            nuevoAncho = anchoNecesario + 40;
        }
        
        if (altoNecesario > altoInicial) {
            nuevoAlto = altoNecesario + 60;
        }
        
        if (nuevoAncho != anchoInicial || nuevoAlto != altoInicial) {
            setSize(nuevoAncho, nuevoAlto);
        }
    }

    /**
     * Separa una arista en formato "A-B" en sus dos vértices.
     */
    private String[] separarArista(String arista) {
        String[] vertices = new String[2];
        vertices[0] = "";
        vertices[1] = "";
        boolean encontradoSeparador = false;
        
        for (int i = 0; i < arista.length(); i++) {
            if (arista.charAt(i) != '-' && !encontradoSeparador) {
                vertices[0] += arista.charAt(i);
            } else if (arista.charAt(i) == '-' || encontradoSeparador) {
                if (arista.charAt(i) == '-') {
                    i++;
                }
                if (i < arista.length()) {
                    vertices[1] += arista.charAt(i);
                }
                encontradoSeparador = true;
            }
        }
        
        return vertices;
    }
    
    private void cargarGrafo(){
        String numeroGrafoTexto = NumG.getText();
        
        if (numeroGrafoTexto != null && !numeroGrafoTexto.trim().isEmpty()) {
            try {
                limpiarGrafoAnterior();
                int numeroGrafo = Integer.parseInt(numeroGrafoTexto);
                
                Grafo.DatosGrafo datos = grafo.leerGrafoConPesos(numeroGrafo);
                configurarVisualizacion(datos);
                
                jTextArea1.setText("Grafo cargado exitosamente");
                
            } catch (FileNotFoundException e) {
                jTextArea1.setText("Error: No se encontró el archivo del grafo");
            } catch (IOException ex) {
                jTextArea1.setText("Error: Problema al leer el archivo del grafo");
            } catch (NumberFormatException ex) {
                jTextArea1.setText("Error: Ingrese un número válido");
            } catch (Exception e) {
                jTextArea1.setText("Error inesperado al cargar el grafo");
            }
        } else {
            jTextArea1.setText("Error: Ingrese el número del grafo");
        }
    }
}