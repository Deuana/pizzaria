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
 * Implementação de Table Model para exibir os Pedidos.
 * 
 * @author Angelmário 
 */
public class PedidoEntregadorTableModel extends AbstractTableModel {

    private List<PedidoDTO> linhas;
    private String[] colunas = new String[] {
             "Número do pedido", "Nome", "Valor total do pedido", "Produto pedido", "Forma de pagamento"};


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
        // Está retornando o tamanho do array "colunas".
        return colunas.length;
    }

    /* Retorna a quantidade de linhas. */
    @Override
    public int getRowCount() {
        // Retorna o tamanho da lista de pedidos.
        return linhas.size();
    }

    /* Retorna o nome da coluna no índice especificado.
     * Este método é usado pela JTable para saber o texto do cabeçalho. */
    @Override
    public String getColumnName(int columnIndex) {
        // Retorna o conteúdo do Array que possui o nome das colunas
        // no índice especificado.
        return colunas[columnIndex];
    };

    /* Retorna a classe dos elementos da coluna especificada.
     * Este método é usado pela JTable na hora de definir o editor da célula. */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Retorna a classe referente a coluna especificada.
        // Aqui é feito um switch para verificar qual é a coluna
        // e retornar o tipo adequado. As colunas são as mesmas
        // que foram especificadas no array "colunas".
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

    /* Retorna o valor da célula especificada
     * pelos índices da linha e da coluna. */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Pega o pedido da linha especificada.       
        PedidoDTO pedido = linhas.get(rowIndex);

        // Retorna o campo referente a coluna especificada.
        // Aqui é feito um switch para verificar qual é a coluna
        // e retornar o campo adequado. As colunas são as mesmas
        // que foram especificadas no array "colunas".
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
    
   

    /* Retorna um valor booleano que define se a célula em questão
     * pode ser editada ou não.
     * Este método é utilizado pela JTable na hora de definir o editor da célula.
     * Neste caso, estará sempre retornando false, não permitindo que nenhuma
     * célula seja editada. */
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
        // o último índice. É preciso subtrair um, pois os índices
        // começam pelo zero.
        int ultimoIndice = getRowCount() - 1;

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /* Remove a linha especificada. */
    public void removePedido(int indiceLinha) {
        // Remove o pedido da linha especificada.  
            if(indiceLinha < linhas.size()){
                linhas.remove(indiceLinha);

                // Reporta a mudança. O JTable recebe a notificação
                // e se redesenha permitindo que visualizemos a atualização.
                fireTableRowsDeleted(indiceLinha, indiceLinha);
            }

    }

    /* Adiciona uma lista de pedido ao final dos registros. */
    public void addListaDePedidos(List<PedidoDTO> pedidos) {
        // Pega o tamanho antigo da tabela.
        int tamanhoAntigo = getRowCount();

        // Adiciona os registros.
        linhas.addAll(pedidos);

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /* Remove todos os registros. */
    public void limpar() {
        // Remove todos os elementos da lista de pedido.
        linhas.clear();

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
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

