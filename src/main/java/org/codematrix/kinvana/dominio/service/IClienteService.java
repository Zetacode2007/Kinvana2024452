package org.codematrix.kinvana.dominio.service;

import  org.codematrix.kinvana.persistence.entity.Cliente;

import java.util.List;

public interface IClienteService {
    //firmas de metodo del CRUD
    public List<Cliente> listarClientes();
    Cliente buscarClientePorId(Integer codigo);
    void guardarCliente(Cliente cliente);
    void EliminarCliente(Cliente cliente);
}
