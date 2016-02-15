/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios.enumeration;

/**
 *
 * @author ari
 */
public enum Mes {
      JAN(1),
    FEV(2),
    MAR(3),
    ABR(4),
    MAI(5),
    JUN(6),
    JUL(7),
    AGO(8),
    SET(9),
    OUT(10),
    NOV(11),
    DEZ(12);
    
    private final int referencia;

    private Mes(int referencia) {
        this.referencia = referencia;
    }

    public int getReferencia() {
        return referencia;
    }
    
}
