/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.controle;

import br.com.hibernate.dao.ClienteDao;
import br.com.hibernate.dao.ClienteDaoImpl;
import br.com.hibernate.dao.HibernateUtil;
import br.com.hibernate.entidade.Cliente;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author gusta
 */

@ManagedBean(name = "clienteC")
@ViewScoped
public class ClienteControle {
    
    private Cliente cliente;
    private ClienteDao clienteDao;
    private Session session;
    private DataModel<Cliente> modelClientes;
    private int numeroAba = 0;
    
    public void pesquisarPorNome() {
        try {
            clienteDao = new ClienteDaoImpl();
            session = HibernateUtil.abrirSessao();
            List<Cliente> clientes = clienteDao.pequisarPorNome(cliente.getNome(), session);
            modelClientes = new ListDataModel(clientes);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar Cliente por nome.." + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void excluir() {
        cliente = modelClientes.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            session = HibernateUtil.abrirSessao();
            clienteDao = new ClienteDaoImpl();
            clienteDao.excluir(cliente, session);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso!", ""));
            cliente.setNome(null);
            modelClientes = null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir" + e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Excluír!", ""));

        } finally {
            session.close();
        }
    }

    public void alterar() {
        numeroAba = 1;
        cliente = modelClientes.getRowData();
    }
    
        public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        clienteDao = new ClienteDaoImpl();
        session = HibernateUtil.abrirSessao();
        try {
            clienteDao.salvarOuAlterar(cliente, session);
            context.addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
            cliente = new Cliente();
            numeroAba = 0;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar!" + e.getMessage(), ""));
        } finally {
            session.close();
        }
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DataModel<Cliente> getModelClientes() {
        return modelClientes;
    }

    public void setModelClientes(DataModel<Cliente> modelClientes) {
        this.modelClientes = modelClientes;
    }

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }
    
}
