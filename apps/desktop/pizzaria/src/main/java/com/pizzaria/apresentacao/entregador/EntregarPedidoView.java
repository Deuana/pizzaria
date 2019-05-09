/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.apresentacao.entregador;

import com.pizzaria.apresentacao.pedido.PedidoTableModel;
import com.pizzaria.dto.BaseDTO;
import com.pizzaria.dto.PedidoDTO;
import com.pizzaria.regrasNegocio.PedidoRN;
import javax.swing.JOptionPane;

/**
 *
 * @author TASSIA
 *  Classe criada para tela de tabelas de Entrega dos Pedidos
 */
public class EntregarPedidoView extends javax.swing.JPanel {

    /**
     * Creates new form EntregarPedidoView
     */
    public EntregarPedidoView() {
        initComponents();
        carregarPedidos();
    }

    
    private void carregarPedidos(){
         PedidoRN pedidoRN = new PedidoRN();
         PedidoTableModel modelo = new PedidoTableModel();
         try{
            for (BaseDTO b : pedidoRN.obterPedidosProntos()) {
               modelo.addPedido(((PedidoDTO) b));
            }
          } catch (Exception e) {
            e.printStackTrace();
           // JOptionPane.showMessageDialog(null, e.getMessage(), "Aten��o",  JOptionPane.INFORMATION_MESSAGE);
        }

        tblPedidosProntos.setModel(modelo);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidosProntos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(817, 508));
        setMinimumSize(new java.awt.Dimension(817, 508));
        setPreferredSize(new java.awt.Dimension(817, 508));

        tblPedidosProntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPedidosProntos.setName("tblPedidosProntos"); // NOI18N
        jScrollPane1.setViewportView(tblPedidosProntos);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Finalizar ");
        jButton1.setName("btnFinalizar"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Entrega de Pedidos");
        jLabel1.setName("lblEntregaDePedidos"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       
           PedidoRN pedidoRN = new PedidoRN();
            
            try{
                for (Integer index : tblPedidosProntos.getSelectedRows()) {                    
                    PedidoDTO p = ((PedidoTableModel) tblPedidosProntos.getModel()).getPedido(index);
                    p.setPedidoFinalizado(Boolean.TRUE);
                    pedidoRN.atualizarPedido(p);
                }
            carregarPedidos();
            }catch(Exception e){
                 e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Aten��o",  JOptionPane.INFORMATION_MESSAGE);
            }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPedidosProntos;
    // End of variables declaration//GEN-END:variables
}