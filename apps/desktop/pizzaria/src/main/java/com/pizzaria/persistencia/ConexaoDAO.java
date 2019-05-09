/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.persistencia;

import com.pizzaria.utilitarios.Utils;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Angelmário
 */
public class ConexaoDAO {
    private static String NOME_BANCO = "pizzaria_1.00.db";
    public static Connection conectar() {
        Connection conn = null;
        try{
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);

            // now we set up a set of fairly basic string variables to use in the body of the code proper
            String sTempDb = Utils.getDiretorioAtual() + NOME_BANCO;//"C:\\Users\\Deuana\\Desktop\\pizzariaDB.db"; //Altere para o diretório da sua máquina
            String sJdbc = "jdbc:sqlite";
            String sDbUrl = sJdbc + ":" + sTempDb;
            // create a database connection
            conn = DriverManager.getConnection(sDbUrl);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return conn;
    }
}
