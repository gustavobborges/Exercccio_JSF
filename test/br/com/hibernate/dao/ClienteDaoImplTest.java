/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.dao;

import br.com.hibernate.entidade.Cliente;
import br.com.hibernate.util.Gerador;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private Session sessao;
    private ClienteDao clienteDao;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    @Test
    public void testeSalvar() {
        cliente = new Cliente(null, "teste nome5",
                Gerador.gerarCpf(),
                "teste email" + Gerador.gerarNumero(5),
                "65326", "3030-3030");
        sessao = HibernateUtil.abrirSessao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();
        assertNotNull(cliente.getId());
    }

    //@Test
    public void testeSalvarCpfUnico() {
        testeSalvar();       
        Cliente cliente2 = new Cliente(null, "teste nome6",
                cliente.getCpf(),
                "teste email" + Gerador.gerarNumero(5),
                "65326", "3030-3030");
        try {
            sessao = HibernateUtil.abrirSessao();
            clienteDao.salvarOuAlterar(cliente2, sessao);
            sessao.close();
        } catch (Exception e) {
           if(e.getMessage().contains("Duplicate entry")){
               fail("CPF Duplicado");
           }
        }       
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
    }

    //@Test
    public void testListarTodo() {
        System.out.println("listarTodo");
    }

//    @Test
    public void testPequisarPorNome() {
        System.out.println("pequisarPorNome");
        String nome = "";
        Session sessao = null;
        ClienteDaoImpl instance = new ClienteDaoImpl();
        List expResult = null;
        List result = instance.pequisarPorNome(nome, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
