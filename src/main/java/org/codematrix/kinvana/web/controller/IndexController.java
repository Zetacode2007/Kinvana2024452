package org.codematrix.kinvana.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.codematrix.kinvana.dominio.service.IClienteService;
import org.codematrix.kinvana.persistence.entity.Cliente;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente(){
        this.clienteSeleccionado = new Cliente();
    }

    public void guardarCliente(){
        logger.info("Cliente a guardar: " + this.clienteSeleccionado);
        //Agregar (insertar)
        if (this.clienteSeleccionado.getCodigoCliente() == null){
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cliente agregado"));
        }
        //modificar (update)
        else{
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente actualizado"));
        }
        //ocultar la venta modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        //Actualizar la tabla con un metodo AJAX
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje_emergente",
                                            "formulario-clientes:tabla-clientes");
        //Reset de clienteSeleccionado
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente(){
        //mostrar en la consola
        logger.info("Cliente a eliminar: " + this.clienteSeleccionado);
        //llamar a nuestro servicio de eliminacion de Cliente
        this.clienteService.EliminarCliente(clienteSeleccionado);
        //Eliminarlo de la lista de clientes
        this.clientes.remove(clienteSeleccionado);
        //Limpiar nuestro cliente Seleccionado
        this.clienteSeleccionado = null;
        //enviar un mensaje emergente de confirmacion
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado Correctamente"));
        //Actualiar la tabla con AJAX
        PrimeFaces.current().ajax().update(
                "formulario-clientes:mensaje_emergente",
                "formulario-clientes:tabla-clientes");
    }

}
