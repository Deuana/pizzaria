/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.apresentacao.pedido;




import com.pizzaria.dto.PedidoDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


/**
 * Implementação de Table Model para exibir os pedido.
 * 
 * @author Angelmário
 */
public class PedidoTableModel extends AbstractTableModel {
   
    private List<PedidoDTO> linhas;
    private String[] colunas = new String[] {
             "Número do pedido", "Nome", "Valor total do pedido", "Produto pedido", "Forma de pagamento"};


    public PedidoTableModel() {
        linhas = new ArrayList<PedidoDTO>();
    }

    public PedidoTableModel(List<PedidoDTO> listaPedido) {
        linhas = new ArrayList<PedidoDTO>(listaPedido);
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }


    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    };

    @Override
    public Class<?> getColumnClass(int columnIndex) {        
        switch (columnIndex) {

        case 0: // Primeira coluna é o nome, que é uma String.
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
                    JOptionPane.showMessageDialog(null, "O índice informado não existe!", "Atenção",  JOptionPane.INFORMATION_MESSAGE);
                    return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PedidoDTO pedido = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pedido.getId();
            case 1: 
                return pedido.getCliente().getNome();
            case 2: // Segunda coluna é o valor
                return pedido.getValor();
            case 3:
                return pedido.getProduto().get(0).getDescricao();
            case 4:
                return pedido.getFormaPagamento();
            default:               
                        JOptionPane.showMessageDialog(null, "O índice informado não existe!", "Atenção",  JOptionPane.INFORMATION_MESSAGE);
                        return null;
        }

    }
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    

    public PedidoDTO getPedido(int indiceLinha) {
            if(indiceLinha < linhas.size()){
                return linhas.get(indiceLinha);
            }
        return null;
    }

    public void addPedido(PedidoDTO pedido) {

        linhas.add(pedido);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }


    public void removePedido(int indiceLinha) { 
            if(indiceLinha < linhas.size()){
                linhas.remove(indiceLinha);

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
            }

    }

    public void addListaDePedidos(List<PedidoDTO> pedidos) {
        int tamanhoAntigo = getRowCount();

        linhas.addAll(pedidos);

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /* Remove todos os registros. */
    public void limpar() {

        linhas.clear();
        fireTableDataChanged();
    }

    /* Verifica se este table model está vazio. */
    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public Object getObject(int index) {
        return linhas.get(index);
    }

}

