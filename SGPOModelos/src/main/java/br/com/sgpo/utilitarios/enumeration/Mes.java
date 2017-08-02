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

    public static Mes retornarMes(int mes) {
        switch (mes) {
            case 1: {
                return Mes.JAN;
            }
            case 2: {
                return Mes.FEV;
            }
            case 3: {
                return Mes.MAR;
            }
            case 4: {
                return Mes.ABR;

            }
            case 5: {
                return Mes.MAI;

            }
            case 6: {
                return Mes.JUN;

            }
            case 7: {
                return Mes.JUL;

            }
            case 8: {
                return Mes.AGO;

            }
            case 9: {
                return Mes.SET;

            }
            case 10: {
                return Mes.OUT;
            }
            case 11: {
                return Mes.NOV;
            }
            case 12: {
                return Mes.DEZ;
            }
            default:{
                return null;
            }
        }
    }

}
