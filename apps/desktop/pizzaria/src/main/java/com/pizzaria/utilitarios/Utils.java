/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pizzaria.utilitarios;

import com.pizzaria.App;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Angelm�rio
 */
public class Utils {
    /***
     * M�todo para verificar se o objeto � nulo ou vazio
     * @param obj
     * @return boolean
     */
    public static boolean isNullOrEmpty(Object obj){
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return obj.toString().trim().equals("");
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof List) {
            return ((List) obj).size() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        return false;
    }
    
    /***
     * M�todo para para obter o valor decimal a partir de uma String
     * @param valor
     * @return BigDecimal
     */
    public static BigDecimal obterBigDecimal(String valor){
        
        if(isNullOrEmpty(valor)){
            return new BigDecimal(BigInteger.ZERO);
        }else{
            return new BigDecimal(valor.replace(',', '.'));
        }
        
    }
    
    /**
     * Retorna o diret�rio onde a aplica��o est� sendo executada.
     * 
     * @return Diret�rio atual da aplica��o.
     */
    public static String getDiretorioAtual() {
        String path = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return path;
    }
}