/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.apresentacao.pedido;

import com.pizzaria.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Angelmário
 */
public class ClienteComboModel extends AbstractListModel implements ComboBoxModel {
    
    private HashMap<String, ClienteDTO> listaCliente = new HashMap<String, ClienteDTO>();
    private List<String> lstDescricoes = new ArrayList<String>();
    private String selecao = null;
   
    public void addCliente(ClienteDTO c) {
        this.listaCliente.put(c.getNome().trim() + " - " + c.getId() + " - " + c.getTelefone() + " [" + c.getEndereco() + "]", c);
        this.lstDescricoes.add(c.getNome().trim() + " - " + c.getId() + " - " + c.getTelefone() + " [" + c.getEndereco() + "]");
    }
    
    public ClienteDTO getCliente(String itemSelecionado){
        return listaCliente.get(itemSelecionado);
    }

    
    @Override
    public Object getElementAt(int index) {
       if(index > this.listaCliente.size() || index < 0){
           return null;
       }
       return this.lstDescricoes.get(index);
    }

    @Override
    public int getSize() {
       return this.listaCliente.size();
    }

    @Override
    public void setSelectedItem(Object anItem) {
      selecao =   (String) anItem; // to select and register an
    } // item from the pull-down list

      // Methods implemented from the interface ComboBoxModel
    @Override
    public Object getSelectedItem(){
        return selecao; // to add the selection to the combo box
    }
}
    

