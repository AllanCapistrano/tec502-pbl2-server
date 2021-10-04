package main;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import models.PatientDevice;

/**
 * Classe do Server.
 *
 * @author Allan Capistrano e João Erick Barbosa.
 */
public class Server {

    /*-------------------------- Constantes ----------------------------------*/
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 12244;
    /*------------------------------------------------------------------------*/

    private static ServerSocket server;

    private static final List<PatientDevice> patientDevices
            = Collections.synchronizedList(new ArrayList());

    private static ArrayList<ConnectionHandler> connHandler = new ArrayList<>();
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        System.out.println("> Iniciando o servidor");
        System.out.println("> Aguardando conexão");

        try {
            /* Definindo a porta do servidor. */
            Server.server = new ServerSocket();
            InetAddress addr = InetAddress.getByName(IP_ADDRESS);
            InetSocketAddress inetSocket = new InetSocketAddress(addr, PORT);
            server.bind(inetSocket);

            while (true) {
                /* Serviço que lida com as requisições utilizando threads. */
                ConnectionHandler connectionThread = new ConnectionHandler(server.accept());
                connHandler.add(connectionThread);

                /* Executando as threads. */
                pool.execute(connectionThread);
            }

        } catch (BindException be) {
            System.err.println("A porta já está em uso.");
            System.out.println(be);
        } catch (IOException ioe) {
            System.err.println("Erro de Entrada/Saída.");
            System.out.println(ioe);
        }
    }

    /**
     * Adiciona o dispositivo de um paciente na lista.
     *
     * @param patient PatientDevice - Dispositivo a ser adicionado.
     */
    public static void addPatientDevice(PatientDevice patient) {
        patientDevices.add(patient);
    }

    /**
     * Retorna a lista de dispositivos dos pacientes.
     *
     * @return List<PatientDevice>
     */
    public static List<PatientDevice> getPatientDevicesList() {
        return patientDevices;
    }

    /**
     * Retorna o tamanho da lista de dispositivos dos pacientes.
     *
     * @return int
     */
    public static int patientDeviceListSize() {
        return patientDevices.size();
    }

    /**
     * Retorna o dispositivo de um paciente com base na sua posição na lista.
     *
     * @param index int - Posição na lista.
     * @return PatientDevice
     */
    public static PatientDevice getPatientDevice(int index) {
        return patientDevices.get(index);
    }

}
