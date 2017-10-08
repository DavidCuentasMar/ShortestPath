/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public class Frame1 extends javax.swing.JFrame {
    boolean ft = true;
    Edge a1,a2;
    String [][] matrizAd;
    int [] distancias;
    String [] padre;
    boolean [] visto;
    double px1=0,py1=0,px2=0,py2=0;
    ArrayList<Node> nodos;
    ArrayList<Edge> arcos;
    Node nodoinicial = null, nodofinal = null;
    boolean clicked = false;
    int cont;
    int iniciox, inicioy, finx, finy;
    
    /**
     * Creates new form Frame1
     */
    public Frame1() {
        cont=0;
        nodos = new ArrayList<>();
        arcos = new ArrayList<>();
        setLocation(20,10);
        setResizable(false);
        initComponents();
        Cargar();
    }
    
    private void Cargar() {
        String cadena;
        try(FileReader f = new FileReader("Files/Nodos.txt"); BufferedReader b = new BufferedReader(f)){            
            while((cadena = b.readLine())!=null) {
                String[] vector = cadena.split(",");  //NombreDeNodo,posx,posy
                nodos.add(new Node((vector[0]),Integer.parseInt(vector[1]),Integer.parseInt(vector[2]),Color.BLACK,0));
            }
        } catch (IOException ex) {

        }      
        try(FileReader f = new FileReader("Files/Aristas.txt"); BufferedReader b = new BufferedReader(f)){            
            while((cadena = b.readLine())!=null) {
                String[] vector = cadena.split(",");  //nodoinicial,x1,y1,nodofinal,x2,y2
                //int nodoinicial, int nodofinal, int x1, int y1, int x2, int y2, int dist
                arcos.add(new Edge(Integer.parseInt(vector[0]), Integer.parseInt(vector[1]), Integer.parseInt(vector[2]),
                        Integer.parseInt(vector[3]) ,Integer.parseInt(vector[4]), Integer.parseInt(vector[5]), Integer.parseInt(vector[6])));
            }
        } catch (IOException ex) {
            
        }
    }
    
    private void dibujar(Graphics g) {
        int conta=1;
        g.setColor(Color.black);
        for (Node nodo : nodos) {
//            g.fillOval(nodo.posx-8, nodo.posy-8, 20, 20);
            g.drawString(Integer.toString(conta), nodo.posx-11, nodo.posy-10);
            conta++;
        }
        for (Edge arco : arcos) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(arco.x1, arco.y1,arco.x2,arco.y2);
        }
    }
    
        
        private void Choosingpoints(Graphics g) {
        clicked = true;
        PanelMap.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(cont==1){
                    g.setColor(Color.red);
                    g.fillOval(e.getX()-13, e.getY()-13, 25, 25);
                    g.setColor(Color.white);
                    g.drawString("L", e.getX()-4, e.getY()+4);
                    finx=e.getX()-10;
                    finy=e.getY()-10;
                    cont++;
                    select.setEnabled(false);

                    //Calcularnodoscerca(iniciox,inicioy,finx,finy,PanelMap.getGraphics());
                }
                if(cont==0){
                    g.setColor(Color.red);
                    g.fillOval(e.getX()-13, e.getY()-13, 25, 25);
                    g.setColor(Color.white);
                    g.drawString("P", e.getX()-4, e.getY()+4);
                    iniciox=e.getX()-10;
                    inicioy=e.getY()-10;
                    cont++;
                    g.setColor(Color.blue);
                    g.fillOval(e.getX() - (25/2), e.getY() - (25/2), 5, 5);

//                    distanciaRectas(iniciox+10,inicioy+10);
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            

        });
    }
    
    private void distanciaRectas(int Cx, int Cy, Graphics g) {
        g.setColor(Color.red);
        if (cont==1) {
            g.fillOval((int)Cx, (int)Cy, 5, 5);
            g.drawString("P", (int)Cx, (int)Cy);           
        }else{
            g.fillOval((int)Cx, (int)Cy, 5, 5);
            g.drawString("L", (int)Cx, (int)Cy);        
        }

        double px = 0;
        double py = 0;
        double u = 0;
        double d=0;        
        double min = Double.POSITIVE_INFINITY;int n1=0; int n2=0;
        g.setColor(Color.blue);
        for (Edge arco : arcos) {
    //      u = ((Cx - arco.x1) * (arco.x2 - arco.x1) + (Cy - arco.y1) * (arco.y2 - arco.y1)) / (Math.pow(Xb - Xa, 2) + (Math.pow(Yb - Ya, 2)));
            u = (((Cx-arco.x1)*(arco.x2-arco.x1))+((Cy-arco.y1)*(arco.y2-arco.y1)))/(Math.pow(arco.x2-arco.x1, 2)+Math.pow(arco.y2-arco.y1, 2));            
            px = (arco.x1 + (u * (arco.x2 - arco.x1)));
            py = (arco.y1 + (u * (arco.y2 - arco.y1)));               
            //System.out.println(u);
            if (u>0 && u<1) {                
        //      double d = ((arco.x2-arco.x1)*(Cy-arco.y1)-(arco.y2-arco.y1)*(Cx-arco.x1))/Math.sqrt((Math.pow(arco.x2-arco.x1, 2))+(Math.pow(arco.y2-arco.y1, 2)));
                d = Math.sqrt(Math.pow(px - Cx, 2) + Math.pow(py - Cy, 2));                    
                if (min>d) {
                    min=d;
                    if (cont==1) {
                        px1=px;
                        py1=py;
                        a1 = new Edge(arco.nodoinicial,arco.nodofinal,arco.x1,arco.y1,arco.x2,arco.y2,arco.dist);
                    }else{
                        px2=px;
                        py2=py;
                        a2 = new Edge(arco.nodoinicial,arco.nodofinal,arco.x1,arco.y1,arco.x2,arco.y2,arco.dist);                              
                    }
//                  n1=arco.nodoinicial;n2=arco.nodofinal; //(Arista)
                }
            }                  
        }                  
        if (cont==2) {
            System.out.println("Shortest Arista P ["+a1.nodoinicial+","+a1.nodofinal+"]");
            System.out.println("Shortest Arista L ["+a2.nodoinicial+","+a2.nodofinal+"]");
            matrizDeAdyacencia();
            // a1.nodoinicial -  a2.nodoinicial
            int distancia1 = dijkstra(a1.nodoinicial);
            
            // a1.nodoinicial -  a2.nodofinal

 
            // a1.nodofinal / a2.nodoinicial

            
            // a1.nodofinal / a2.nodofinal

            
        }
            
        
    }
            
    private void Calcularnodoscerca(int x1, int y1, int x2, int y2, Graphics g) {
        
        //Buscar el nodo que esté más cerca del nodo inicial..
        int dist=2000,x=0,y=0;
        double dist2;
        for (Node nodo : nodos) {
            dist2=Math.sqrt(Math.pow(Math.abs(x1-nodo.posx), 2) + Math.pow(Math.abs(y1-nodo.posy), 2));
            if(dist2<dist){
                dist= (int)dist2;
                x=nodo.posx;
                y=nodo.posy;
            }
        }
        g.setColor(Color.blue);
        g.fillOval(x-8, y-8, 20, 20);
        
        //Buscar el nodo que esté más cerca del nodo final...
        dist=2000;
        x=0;
        y=0;
        for (Node nodo : nodos) {
            dist2=Math.sqrt(Math.pow(Math.abs(x2-nodo.posx), 2) + Math.pow(Math.abs(y2-nodo.posy), 2));
            if(dist2<dist){
                dist= (int)dist2;
                x=nodo.posx;
                y=nodo.posy;
            }
        }
        g.setColor(Color.blue);
        g.fillOval(x-8, y-8, 20, 20);
                
    }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMain = new javax.swing.JPanel();
        PanelMap = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelData = new javax.swing.JPanel();
        select = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        PanelTitle = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LabPicture.png"))); // NOI18N

        javax.swing.GroupLayout PanelMapLayout = new javax.swing.GroupLayout(PanelMap);
        PanelMap.setLayout(PanelMapLayout);
        PanelMapLayout.setHorizontalGroup(
            PanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelMapLayout.setVerticalGroup(
            PanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        select.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        select.setText("Seleccionar punto de partida y llegada");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        jButton1.setText("ver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDataLayout = new javax.swing.GroupLayout(PanelData);
        PanelData.setLayout(PanelDataLayout);
        PanelDataLayout.setHorizontalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(select))
                .addGap(31, 31, 31))
        );
        PanelDataLayout.setVerticalGroup(
            PanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDataLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout PanelTitleLayout = new javax.swing.GroupLayout(PanelTitle);
        PanelTitle.setLayout(PanelTitleLayout);
        PanelTitleLayout.setHorizontalGroup(
            PanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        PanelTitleLayout.setVerticalGroup(
            PanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addComponent(PanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        if (clicked==false) {            
            Choosingpoints(PanelMap.getGraphics());            
        }
        
    }//GEN-LAST:event_selectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dibujar(PanelMap.getGraphics());
//        for (Edge arco : arcos) {
//            System.out.println("("+arco.nodoinicial+","+arco.nodofinal+")");
//            
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (ft==true) {
            MouseListener m = new MouseListener(){
                
            @Override
            
            public void mouseClicked(MouseEvent e) {
                 
                if (cont<2) {
                    cont++;
                    finx=e.getX()-2;
                    finy=e.getY()-2;
                    //System.out.println(finx+","+finy);
                    distanciaRectas(finx,finy,PanelMap.getGraphics());                    
                }
                
                
            }

               @Override
               public void mousePressed(MouseEvent e) {
                  // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }

               @Override
               public void mouseReleased(MouseEvent e) {
              //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }

               @Override
               public void mouseEntered(MouseEvent e) {
             //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }

               @Override
               public void mouseExited(MouseEvent e) {
             //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }                    
            
            }; 
            ft=false;
             
               PanelMap.addMouseListener(m);      
        }
            
    
        
            
            
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.repaint();
        cont=0;
    }//GEN-LAST:event_jButton3ActionPerformed

    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelData;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelMap;
    private javax.swing.JPanel PanelTitle;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton select;
    // End of variables declaration//GEN-END:variables
     

    

    private void matrizDeAdyacencia() {
        matrizAd = new String[nodos.size()+1][nodos.size()+1];            
            int mX=1, mY=1;
            //System.out.println(cantNodos);
            for (Node nodo : nodos) {
                matrizAd[mX][0] = nodo.name+"";
                matrizAd[0][mY] = nodo.name+"";
                mX++; mY++;
            }
//            for (int x=0; x < matrizAd. length; x++) {
//                for (int y=0; y < matrizAd[x]. length; y++) {
//                    if (x>0 && y>0 || x==0 & y==0) {
//                        System.out.print (matrizAd[x][y]+","); 
//                    }else{
//                        System.out.print (matrizAd[x][y]+",");                  
//                    }
//                }
//                System.out.println("");
//            }
//            System.out.println(arcos.size());
            for (Edge arco : arcos) {
                for (int i = 0; i < matrizAd.length; i++) {
                    if (arco.nodoinicial==i) {
                        for (int j = 0; j < matrizAd.length; j++) {                       
                            if (arco.nodofinal==j) {
                                matrizAd[i][j]=arco.dist+"";
                                matrizAd[j][i]=arco.dist+"";
                            }
                        }                        
                    }                    
                }
                
            }
            
            for (int x=0; x < matrizAd. length; x++) {
                for (int y=0; y < matrizAd[x]. length; y++) {
                    if (matrizAd[x][y]==null) {
                        matrizAd[x][y]="*";
                    }
//                    if (x>0 && y>0 || x==0 & y==0) {
//                        System.out.print (matrizAd[x][y]+","); 
//                    }else{
//                        System.out.print (matrizAd[x][y]+",");                  
//                    }
                }
//                System.out.println("");
            }
            

    }

    private int dijkstra(int s) {
        Cola c = new Cola();
        int x=0;
        int distancia[] = new int[nodos.size()+1];
        int padre[] = new int[nodos.size()+1];
        boolean visto[] = new boolean[nodos.size()+1];
        for (int i = 1; i < distancia.length; i++) {
            distancia[i]=Integer.MAX_VALUE;
            padre[i]=0;
            visto[i]=false;                     
        }
        distancia[s]=0;
        c.addCola(new nodo(s,0));
        while(c.ptr!=null){
            nodo u = c.getMinimo();
            visto[u.name]=true;
//            System.out.println(u.name);
//            c.show();
            int[] adyacentes = getAdyacentes(u.name);
            for (int i = 0; i < adyacentes.length; i++) {
                int v = adyacentes[i];
                if (visto[v]==false && distancia[v]>distancia[u.name]+peso(u,v)) {
                    distancia[v]= distancia[u.name]+peso(u,v);
                    padre[v]=u.name;
                    c.addCola(new nodo(v,distancia[v]));                                       
                }                
            }        
        }
        System.out.println("Distancias ");
        for (int i = 1; i < distancia.length; i++) {
              System.out.println("["+i+"] "+distancia[i]);            
        }
        
        
        
        
        
        
        
        
        return x;
    }

    private int[] getAdyacentes(int u) {       
        String cadena="";
//        for (int i = 0; i < matrizAd.length; i++) {
//            for (int j = 0; j < matrizAd.length; j++) {
//                System.out.print(matrizAd[i][j]+",");
//            }
//            System.out.println("");
//            
//        }
        for (int i = 0; i < matrizAd.length; i++) {
            if (i==u) {
                for (int j = 1; j < matrizAd.length; j++) {
                    if (!matrizAd[i][j].equals("*")) {
                        cadena = cadena + matrizAd[0][j]+",";                        
                    }                                       
                }
            }            
        }
        cadena = cadena.substring(0, cadena.length()-1);
        System.out.println("Adyacentes ("+u+"):" + cadena);
        String[] aux = cadena.split(",");
        int[] vector = new int[aux.length];
        for (int i = 0; i < aux.length; i++) {
            vector[i]=Integer.parseInt(aux[i]);            
        }

        return vector;
    }

    private int peso(nodo u, int v) {
        for (int i = 0; i < matrizAd.length; i++) {
            if (i==u.name) {
                for (int j = 1; j < matrizAd.length; j++) {
                    if (j==v) {
//                        System.out.println("PESO: " + matrizAd[i][j]);
                        return Integer.parseInt(matrizAd[i][j]);
                        
                    }
                                       
                }
            }            
        }
        return 0;
    }
    

}
