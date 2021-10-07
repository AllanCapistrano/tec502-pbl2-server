package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import models.PatientDevice;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.PatientToJson;

/**
 * Classe que lida com as requisições enviadas para o servidor.
 *
 * @author Allan Capistrano
 */
public class ConnectionHandler implements Runnable {

    private final Socket connection;
    private final ObjectInputStream input;
    private JSONObject received;

    /**
     * Método construtor.
     *
     * @param connection Socket - Conexão com o Client.
     * @throws IOException
     */
    public ConnectionHandler(Socket connection) throws IOException {
        this.connection = connection;

        this.input = new ObjectInputStream(connection.getInputStream());
    }

    @Override
    public void run() {
        try {
            /* Requisição recebida. */
            this.received = (JSONObject) this.input.readObject();

            /* Processandos a requisição. */
            this.processRequests(this.received);

            System.out.println("> Quantidade de dispositivos conectados: "
                    + Server.patientDeviceListSize());

            /* Finalizando as conexões. */
            input.close();
            connection.close();
        } catch (IOException ioe) {
            System.err.println("Erro de Entrada/Saída.");
            System.out.println(ioe);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Classe JSONObject não foi encontrada.");
            System.out.println(cnfe);
        }
    }

    /**
     * Processa as requisições que são enviados ao servidor.
     *
     * @param httpRequest JSONObject - Requisição HTTP.
     */
    private void processRequests(JSONObject httpRequest) {
        System.out.println("> Processando a requisição");

        switch (httpRequest.getString("method")) {
            case "GET": // Envia dados.
                System.out.println("\tMétodo GET");
                System.out.println("\t\tRota: " + httpRequest.getString("route"));

                if (httpRequest.getString("route").contains("/patients")) {
                    /* Envia a lista dos dispositivos dos pacientes. */
                    System.out.println("> Enviando lista de pacientes");

                    this.sendPatientDevicesList();

                } else if(httpRequest.getString("route").contains("/patient")){
                    /* Envia o dispositivo do paciente. */
                    System.out.println("> Enviando o dispositivo do paciente");
                    
                    String[] temp = httpRequest.getString("route").split("/");
                    
                    this.sendPatientDevice(temp[2]);
                    
                }

                break;
            case "POST": // Cria e adiciona os dispositivos dos pacintes na lista.
                System.out.println("\tMétodo POST");
                System.out.println("\t\tRota: " + httpRequest.getString("route"));

                if (httpRequest.getString("route").equals("/patients")) {
                    addPatientDevicesToServer(httpRequest.getJSONArray("body"));
                }

                break;
        }
        System.out.println("");
    }

    /**
     * Envia a lista dos dispositivos dos pacientes.
     */
    private void sendPatientDevicesList() {
        try {
            ObjectOutputStream output
                    = new ObjectOutputStream(connection.getOutputStream());

            /* Colocando a lista de pacientes no formato JSON. */
            JSONObject json
                    = PatientToJson.handle(Server.getPatientDevicesList(), true);
            
            output.writeObject(json);

            output.close();
        } catch (IOException ioe) {
            System.err.println("Erro ao tentar enviar a lista dos dispositivos "
                    + "dos pacientes.");
            System.out.println(ioe);
        }
    }
    
    /**
     * Envia o dispositivo do paciente.
     */
    private void sendPatientDevice(String deviceId) {
        try {
            ObjectOutputStream output
                    = new ObjectOutputStream(connection.getOutputStream());

            /* Colocando o dispositivo do paciente no formato JSON. */
            JSONObject json
                    = PatientToJson.handle(Server.getPatientDeviceById(deviceId));
            
            output.writeObject(json);

            output.close();
        } catch (IOException ioe) {
            System.err.println("Erro ao tentar enviar o dispositivo "
                    + "do paciente");
            System.out.println(ioe);
        }
    }

    /**
     * Adiciona todos os pacientes recebidos na lista de pacientes.
     *
     * @param jsonArray JSONArray - Lista dos dispositibos dos pacientes no
     * formato JSON.
     */
    private void addPatientDevicesToServer(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            addIndividualPatientDevice(jsonArray.getJSONObject(i));
        }
    }

    /**
     * Adiciona um dispositivo na lista de dispositivos dos pacientes.
     *
     * @param patient JSONObject - Informações do dispositivo do paciente no
     * formato JSON.
     */
    private void addIndividualPatientDevice(JSONObject patient) {
        String name = patient.getString("name");
        String deviceId = patient.getString("deviceId");
        float bodyTemperature = patient.getFloat("bodyTemperature");
        int respiratoryFrequency = patient.getInt("respiratoryFrequency");
        float bloodOxygenation = patient.getFloat("bloodOxygenation");
        int bloodPressure = patient.getInt("bloodPressure");
        int heartRate = patient.getInt("heartRate");
        boolean isSeriousCondition = patient.getBoolean("isSeriousCondition");
        String isSeriousConditionLabel = patient.getString("isSeriousConditionLabel");
        float patientSeverityLevel = patient.getFloat("patientSeverityLevel");

        if (!Server.devicePatientExists(deviceId)) {
            Server.addPatientDevice(
                    new PatientDevice(
                            name,
                            deviceId,
                            bodyTemperature,
                            respiratoryFrequency,
                            bloodOxygenation,
                            bloodPressure,
                            heartRate,
                            isSeriousCondition,
                            isSeriousConditionLabel,
                            patientSeverityLevel
                    )
            );
        } else {
            for (int i = 0; i < Server.patientDeviceListSize(); i++) {
                if (deviceId.equals(Server.getPatientDevice(i).getDeviceId())) {
                    Server.getPatientDevice(i).setBodyTemperature(bodyTemperature);
                    Server.getPatientDevice(i).setRespiratoryFrequency(respiratoryFrequency);
                    Server.getPatientDevice(i).setBloodOxygenation(bloodOxygenation);
                    Server.getPatientDevice(i).setBloodPressure(bloodPressure);
                    Server.getPatientDevice(i).setHeartRate(heartRate);
                    Server.getPatientDevice(i).setIsSeriousCondition(isSeriousCondition);
                    Server.getPatientDevice(i).setIsSeriousConditionLabel(isSeriousConditionLabel);
                    Server.getPatientDevice(i).setPatientSeverityLevel(patientSeverityLevel);

                    break;
                }
            }
        }
    }
}
