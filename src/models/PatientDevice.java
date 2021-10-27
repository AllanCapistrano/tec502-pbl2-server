package models;

import java.io.Serializable;

/**
 * Classe do paciente.
 *
 * @author Allan Capistrano e João Erick Barbosa.
 */
public class PatientDevice implements Serializable {

    private final String name;
    private final String deviceId;
    private String isSeriousConditionLabel;
    private float patientSeverityLevel;
    private FogServer fogServer;

    /**
     * Método construtor.
     *
     * @param name String - Nome do paciente.
     * @param deviceId String - Identificador do dispositivo do paciente.
     * @param isSeriousConditionLabel String - Condição atual do paciente em
     * formato de texto.
     * @param patientSeverityLevel - Nível de gravidade do paciente.
     * @param fogServer FogServer - Fog a qual o dispositivo está conectado.
     */
    public PatientDevice(String name, String deviceId, String isSeriousConditionLabel, float patientSeverityLevel, FogServer fogServer) {
        this.name = name;
        this.deviceId = deviceId;
        this.isSeriousConditionLabel = isSeriousConditionLabel;
        this.patientSeverityLevel = patientSeverityLevel;
        this.fogServer = fogServer;
    }
    
    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public String getIsSeriousConditionLabel() {
        return isSeriousConditionLabel;
    }

    public float getPatientSeverityLevel() {
        return patientSeverityLevel;
    }

    public void setIsSeriousConditionLabel(String isSeriousConditionLabel) {
        this.isSeriousConditionLabel = isSeriousConditionLabel;
    }

    public void setPatientSeverityLevel(float patientSeverityLevel) {
        this.patientSeverityLevel = patientSeverityLevel;
    }

    public FogServer getFogServer() {
        return fogServer;
    }
}
