package Interfaz;

import Listas.ListaLigada;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JPanel{
    Integer posx1,posx2,posy1,posy2,cont;
    Image imagen;
    ListaLigada posx = new ListaLigada();
    ListaLigada posxf = new ListaLigada();
    ListaLigada posy = new ListaLigada();
    ListaLigada posyf = new ListaLigada();
    
    public Ventana() {    
    }
    
    public void cargarImagen(String ruta){
        File file = new File(ruta);
        if (!file.exists()) {//verifica la ruta, si no existe manda un mensaje 
            JOptionPane.showMessageDialog(this, "No se encontró el archivo: " + ruta, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
         imagen = new ImageIcon(ruta).getImage();
         repaint();
    }
    
    public void dibujarL(int posx1, int posx2, int posy1, int posy2){//cambia las posiciones para pintar la linea y manda un repaint, para pintar la siguiente linea
        posx.insertarini(posx1);
        posy.insertarini(posy1);
        posxf.insertarini(posx2);
        posyf.insertarini(posy2);
        repaint();
    }
    
    public void borrarImagen(){
         imagen = null;
         while((posx.borrarfin()) != null && (posy.borrarfin()) != null && (posxf.borrarfin()) != null && (posyf.borrarfin()) != null  ){}
    }
    
    @Override
    protected void paintComponent(Graphics g){//pinta una linea con las cordenadas deseadas
         super.paintComponent(g);
         g.drawImage(imagen, 0, 0, this);
         g.setColor(Color.red);
         Graphics2D g2d = (Graphics2D) g;
         if(!posx.estaVacia()){
             posx.cont = 0;
             posx.buscar(null, posx.listal, -1);
             cont = posx.cont;
             for (int i = 0; i <= cont; i++) {
                posx1 = (Integer)posx.borrarfin();
                posy1 = (Integer)posy.borrarfin();
                posx2 = (Integer)posxf.borrarfin();
                posy2 = (Integer)posyf.borrarfin();
                
                // Ajustar el grosor de la línea
                g2d.setStroke(new BasicStroke(3)); // Grosor de 5 píxeles
                
                // Dibujar la línea
                g2d.setColor(Color.RED);
                g2d.drawLine(posx1, posy1, posx2, posy2);
                
                //regresa las posiciones a la lineas para evitar borrarlas con el repaint()
                posx.insertarini(posx1);
                posy.insertarini(posy1);
                posxf.insertarini(posx2);
                posyf.insertarini(posy2);
             }
         }
    }
}
