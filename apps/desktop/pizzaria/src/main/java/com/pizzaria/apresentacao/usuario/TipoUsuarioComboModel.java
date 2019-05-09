/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.apresentacao.usuario;

import com.pizzaria.dto.TipoUsuarioDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Angelmário
 */
public class TipoUsuarioComboModel extends AbstractListModel implements ComboBoxModel {
    
    private HashMap<String, TipoUsuarioDTO> listaTipoUsuario = new HashMap<String, TipoUsuarioDTO>();
    private List<String> lstDescricoes = new ArrayList<String>();
    private String selecao = null;
   
    public void addTipoUsuario(TipoUsuarioDTO tpu) {
        this.listaTipoUsuario.put(tpu.getDescricao().trim() + " - " + tpu.getId(), tpu);
        this.lstDescricoes.add(tpu.getDescricao().trim() + " - " + tpu.getId());
    }
    
    public TipoUsuarioDTO getTipoUsuario(String itemSelecionado){
        return listaTipoUsuario.get(itemSelecionado);
    }

    
    @Override
    public Object getElementAt(int index) {
       if(index > this.listaTipoUsuario.size() || index < 0){
           return null;
       }
       return this.lstDescricoes.get(index);
    }

    @Override
    public int getSize() {
       return this.listaTipoUsuario.size();
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
    

