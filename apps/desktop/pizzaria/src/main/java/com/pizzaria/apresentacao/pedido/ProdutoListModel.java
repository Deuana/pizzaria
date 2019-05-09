/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.apresentacao.pedido;

import com.pizzaria.dto.ProdutoDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractListModel;
/**
 *
 * @author Angelmário
 */
public class ProdutoListModel extends AbstractListModel{
    private HashMap<String, ProdutoDTO> listaProduto = new HashMap<String, ProdutoDTO>();
    private List<String> lstDescricoes = new ArrayList<String>();
    private String selecao = null;
    
    public void add(ProdutoDTO produto){
        this.listaProduto.put( produto.getId() + " - " + produto.getDescricao().trim() + " - R$ " + produto.getValor(), produto);
        this.lstDescricoes.add(produto.getId() + " - " + produto.getDescricao().trim() + " - R$ " + produto.getValor());
    }
    
     public void addAll(List<ProdutoDTO> lista){
         for (ProdutoDTO produto : lista) {
              if(!listaProduto.containsKey(produto.getId() + " - " + produto.getDescricao().trim() + " - R$ " + produto.getValor())){
                this.listaProduto.put( produto.getId() + " - " + produto.getDescricao().trim() + " - R$ " + produto.getValor(), produto);
                this.lstDescricoes.add(produto.getId() + " - " + produto.getDescricao().trim() + " - R$ " + produto.getValor());
              }
         }
     }
    
    public List<ProdutoDTO> obterProdutos(List<String> produtos){
        List<ProdutoDTO> lst = new ArrayList<ProdutoDTO>();
        
        for (String prod : produtos) {
            lst.add(listaProduto.get(prod));
        }
        
        return lst;
    }
    
    public void removerAllItens(){
        this.listaProduto.clear();
        this.lstDescricoes.clear();
    }
    
    @Override
    public int getSize() {
        return this.lstDescricoes.size();
    }

    
    @Override
    public Object getElementAt(int index) {
         if(index > this.lstDescricoes.size() || index < 0){
           return null;
       }
       return this.lstDescricoes.get(index);
    }
    
    
    
    
}
