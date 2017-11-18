/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package half_duplex;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class client extends javax.swing.JFrame implements Runnable  {

    
    DatagramSocket dsocket=null;
   // DatagramSocket dsocketout=null;
    DatagramPacket packet=null;
    String temp="",temp1="";
   InetAddress address=null;
   DatagramPacket send=null;
      
    
    public client() {
        initComponents();
        
    }
    
    @Override
    public void run(){
        try{
         dsocket=new DatagramSocket(12345);
         address=InetAddress.getLocalHost();
             area.append("client ready to connect");
            do{
             byte[] data=new byte[10240];
          packet=new DatagramPacket(data,data.length);
         dsocket.receive(packet);
         if(packet!=null){
         temp1=new String(packet.getData(),0,packet.getLength());
        
         area.append("\n"+"client: "+temp1);
         if(temp1.equals("end")){
             dsocket.close();
             break;
         }
          //address=packet.getAddress();
         }
         }
           while(!temp1.equals("end"));
            area.append("\n"+"chat ended");
          
        }catch(IOException e){
            
        }
        
        
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        user = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("client");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(150, 10, 90, 20);

        area.setColumns(20);
        area.setRows(5);
        jScrollPane1.setViewportView(area);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 360, 200);

        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        getContentPane().add(user);
        user.setBounds(10, 280, 360, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        // TODO add your handling code here:
         try{
            // dsocket=new DatagramSocket(12344);
         temp=user.getText();
         send=new DatagramPacket(temp.getBytes(),temp.getBytes().length,address,12344);
         dsocket.send(send);
         area.append("\n"+"me: "+temp);
         if(temp.equals("end")){
             area.append("\n"+"chat ended");
             dsocket.close();
         }

         user.setText(null);
       }
       catch(IOException e){
        
       }
    }//GEN-LAST:event_userActionPerformed

  
    public static void main(String args[]) {
       client c=new client();
       c.setVisible(true);
       Thread t=new Thread(c);
       t.setDaemon(true);
       t.start();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables

}
