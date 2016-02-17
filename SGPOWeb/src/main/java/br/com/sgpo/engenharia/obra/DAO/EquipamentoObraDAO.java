/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.DAO;

import br.com.sgpo.engenharia.obra.modelo.EquipamentoObra;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author ari
 */
@Stateless
public class EquipamentoObraDAO extends DAOGenerico<EquipamentoObra, Long> implements Serializable{
    
    public EquipamentoObraDAO() {
        super(EquipamentoObra.class);
    }
    
}
