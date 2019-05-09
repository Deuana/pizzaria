/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.apresentacao.entregador;




import com.pizzaria.apresentacao.pedido.*;
import com.pizzaria.dto.PedidoDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


/**
 * Implementa��o de Table Model para exibir os Pedidos.
 * 
 * @author Angelm�rio 
 */
public class PedidoEntregadorTableModel extends AbstractTableModel {

    private List<PedidoDTO> linhas;
    private String[] colunas = new String[] {
             "N�mero do pedido", "Nome", "Valor total do pedido", "Produto pedido", "Forma de pagamento"};


    /* Cria um PedidoTableModelo vazio. */
    public PedidoEntregadorTableModel() {
        linhas = new ArrayList<PedidoDTO>();
    }

    /* Cria um PedidoTableModelo carregado com
     * a lista de pedidos especificada. */
    public PedidoEntregadorTableModel(List<PedidoDTO> listaPedido) {
        linhas = new ArrayList<PedidoDTO>(listaPedido);
    }


    /* Retorna a quantidade de colunas. */
    @Override
    public int getColumnCount() {
        // Est� retornando o tamanho do array "colunas".
        return colunas.length;
    }

    /* Retorna a quantidade de linhas. */
    @Override
    public int getRowCount() {
        // Retorna o tamanho da lista de pedidos.
        return linhas.size();
    }

    /* Retorna o nome da coluna no �ndice especificado.
     * Este m�todo � usado pela JTable para saber o texto do cabe�alho. */
    @Override
    public String getColumnName(int columnIndex) {
        // Retorna o conte�do do Array que possui o nome das colunas
        // no �ndice especificado.
        return colunas[columnIndex];
    };

    /* Retorna a classe dos elementos da coluna especificada.
     * Este m�todo � usado pela JTable na hora de definir o editor da c�lula. */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Retorna a classe referente a coluna especificada.
        // Aqui � feito um switch para verificar qual � a coluna
        // e retornar o tipo adequado. As colunas s�o as mesmas
        // que foram especificadas no array "colunas".
        switch (columnIndex) {

        case 0: // Primeira coluna � o nome, que � uma String.
            return Integer.class;
        case 1:
            return String.class;
        case 2: 
            return BigDecimal.class;
        case 3:
            return String.class;
        case 4:
            return String.class;
        default:
                    JOptionPane.showMessageDialog(null, "O �ndice informado n�o existe!", "Aten��o",  JOptionPane.INFORMATION_MESSAGE);
                    return null;
        }
    }

    /* Retorna o valor da c�lula especificada
     * pelos �ndices da linha e da coluna. */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Pega o pedido da linha especificada.       
        PedidoDTO pedido = linhas.get(rowIndex);

        // Retorna o campo referente a coluna especificada.
        // Aqui � feito um switch para verificar qual � a coluna
        // e retornar o campo adequado. As colunas s�o as mesmas
        // que foram especificadas no array "colunas".
        switch (columnIndex) {
            case 0:
                return pedido.getId();
            case 1: 
                return pedido.getCliente().getNome();
            case 2: // Segunda coluna � o valor
                return pedido.getValor();
            case 3:
                return pedido.getProduto().get(0).getDescricao();
            case 4:
                return pedido.getFormaPagamento();
            default:
                        JOptionPane.showMessageDialog(null, "O �ndice informado n�o existe!", "Aten��o",  JOptionPane.INFORMATION_MESSAGE);
                        return null;
        }

    }
    
   

    /* Retorna um valor booleano que define se a c�lula em quest�o
     * pode ser editada ou n�o.
     * Este m�todo � utilizado pela JTable na hora de definir o editor da c�lula.
     * Neste caso, estar� sempre retornando false, n�o permitindo que nenhuma
     * c�lula seja editada. */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    



    /* Retorna o pedido da linha especificada. */
    public PedidoDTO getPedido(int indiceLinha) {
            if(indiceLinha < linhas.size()){
                return linhas.get(indiceLinha);
            }
        return null;
    }

    /* Adiciona um registro. */
    public void addPedido(PedidoDTO pedido) {
        // Adiciona o registro.
        linhas.add(pedido);

        // Pega a quantidade de registros e subtrai um para achar
        // o �ltimo �ndice. � preciso subtrair um, pois os �ndices
        // come�am pelo zero.
        int ultimoIndice = getRowCount() - 1;

        // Reporta a mudan�a. O JTable recebe a notifica��o
        // e se redesenha permitindo que visualizemos a atualiza��o.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /* Remove a linha especificada. */
    public void removePedido(int indiceLinha) {
        // Remove o pedido da linha especificada.  
            if(indiceLinha < linhas.size()){
                linhas.remove(indiceLinha);

                // Reporta a mudan�a. O JTable recebe a notifica��o
                // e se redesenha permitindo que visualizemos a atualiza��o.
                fireTableRowsDeleted(indiceLinha, indiceLinha);
            }

    }

    /* Adiciona uma lista de pedido ao final dos registros. */
    public void addListaDePedidos(List<PedidoDTO> pedidos) {
        // Pega o tamanho antigo da tabela.
        int tamanhoAntigo = getRowCount();

        // Adiciona os registros.
        linhas.addAll(pedidos);

        // Reporta a mudan�a. O JTable recebe a notifica��o
        // e se redesenha permitindo que visualizemos a atualiza��o.
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /* Remove todos os registros. */
    public void limpar() {
        // Remove todos os elementos da lista de pedido.
        linhas.clear();

        // Reporta a mudan�a. O JTable recebe a notifica��o
        // e se redesenha permitindo que visualizemos a atualiza��o.
        fireTableDataChanged();
    }

    /* Verifica se este table model est� vazio. */
    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public Object getObject(int index) {
        return linhas.get(index);
    }

}

