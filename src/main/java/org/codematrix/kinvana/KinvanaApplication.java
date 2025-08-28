package org.codematrix.kinvana;

import org.codematrix.kinvana.dominio.service.IClienteService;
import org.codematrix.kinvana.persistence.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class KinvanaApplication implements CommandLineRunner {

    //Inyeccion de dependencia
    @Autowired
    private IClienteService clienteService;
    //Crear nuestro Objeto (herramienta) Logger para interactuar con la consola.
    private static final Logger logger = LoggerFactory.getLogger(KinvanaApplication.class);
    //Crear un objeto String para saltos de linea.
    String sl = System.lineSeparator();//Salto de línea


	public static void main(String[] args) {
        logger.info("AQUI INICIA NUESTRA APLICACION");
		SpringApplication.run(KinvanaApplication.class, args);
        logger.info("AQUI TERMINO LA APLICACION");
	}

    @Override
    public void run(String... args) throws Exception {
        kinvanaClienteApp();
    }

    private void kinvanaClienteApp(){
        logger.info("++++++APLICACIÓN DE REGISTRO DE CLIENTES+++++++");
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir){
            var opcion = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(sl);
        }
    }

    private int mostrarMenu(Scanner consola){
        logger.info("""
                \n ***Aplicacion***
                1. Listar todo los clientes.
                2. Buscar cliente por codigo.
                3.Agregar nuevo cliente.
                4. Modificar cliente.
                5. Eliminar cliente
                6. Salir
                Elije una opcion: \s """);
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }
    private boolean ejecutarOpciones(Scanner consola, int opcion){
        var salir = false;
        switch (opcion){
            case 1 ->{
                logger.info(sl+"Listado de todos los Clientes"+sl);
                List<Cliente> clientes = clienteService.listarClientes();
                clientes.forEach( cliente -> logger.info(cliente.toString()+sl));
            }
            case 2 ->{
                logger.info(sl+"Buscar Cliente por su codigo"+sl);
                var codigo = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteService.buscarClientePorId(codigo);
                if (cliente != null){
                    logger.info("Cliente encontrado con exito: " +sl+cliente+sl);
                }else{
                    logger.info("Cliente no encontrado"+sl+cliente+sl);
                }
            }
            case 3 ->{
                logger.info(sl+"Agregar nuevo Cliente"+sl);
                logger.info("Ingrese el nombre: ");
                var nombre = consola.nextLine();
                logger.info("Ingrese el apellido: ");
                var apellido = consola.nextLine();
                logger.info("Ingrese el telefono: ");
                var telefono = consola.nextLine();
                logger.info("Ingrese el correo: ");
                var correo = consola.nextLine();
                logger.info("Ingrese el genero: ");
                var genero = consola.nextLine();
                logger.info("Ingrese la edad: ");
                var edad = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                cliente.setCorreo(correo);
                cliente.setGenero(genero);
                cliente.setEdad(edad);
                clienteService.guardarCliente(cliente);
                logger.info("Cliente agregado: " +sl+cliente+sl);
            }
            case 4 ->{
                logger.info(sl+"Modificar Cliente"+sl);
                logger.info(sl+"Ingrese el codigo del cliente a editar"+sl);
                var codigo = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteService.buscarClientePorId(codigo);
                if (cliente !=null){
                    logger.info("Ingrese el nombre: ");
                    var nombre = consola.nextLine();
                    logger.info("Ingrese el apellido: ");
                    var apellido = consola.nextLine();
                    logger.info("Ingrese el telefono: ");
                    var telefono = consola.nextLine();
                    logger.info("Ingrese el correo: ");
                    var correo = consola.nextLine();
                    logger.info("Ingrese el genero: ");
                    var genero = consola.nextLine();
                    logger.info("Ingrese la edad: ");
                    var edad = Integer.parseInt(consola.nextLine());
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setTelefono(telefono);
                    cliente.setCorreo(correo);
                    cliente.setGenero(genero);
                    cliente.setEdad(edad);
                    clienteService.guardarCliente(cliente);
                    logger.info("Cliente Editado correctamente: " +sl+cliente+sl);
                }else{
                    logger.info("Cliente No encontrado."+sl+cliente+sl);
                }
            }
            case 5 ->{
                logger.info(sl+"Eliminar Cliente"+sl);
                logger.info("Ingrese el código del Cliente a eliminar");
                var codigo = Integer.parseInt(consola.nextLine());
                var cliente = clienteService.buscarClientePorId(codigo);
                if (cliente != null){
                    clienteService.EliminarCliente(cliente);
                    logger.info("Cliente eliminado, adios"+sl+cliente+sl);
                }else {
                    logger.info("Cliente no encontrado"+sl+cliente+sl);
                }
            }
            case 6 ->{
                logger.info("Hasta pronto, Vaquero!🤠"+sl+sl);
                salir = true;
            }
            default -> logger.info("Opcion no valida!!!");
        }
        return salir;
    }

}
