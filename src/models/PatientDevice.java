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
    private float bodyTemperature;
    private int respiratoryFrequency;
    private float bloodOxygenation;
    private int bloodPressure;
    private int heartRate;
    private boolean isSeriousCondition;
    private String isSeriousConditionLabel;
    private float patientSeverityLevel;

    /**
     * Método construtor.
     *
     * @param name String - Nome do paciente.
     * @param bodyTemperature float - Valor da temperatura corporal registrada
     * pelo sensor.
     * @param respiratoryFrequency int - Valor da frequência respiratória
     * registrada pelo sensor.
     * @param bloodOxygenation float - Nível de oxigênio no sangue registrado
     * pelo sensor.
     * @param bloodPressure int - Pressão arterial registrada pelo sensor.
     * @param heartRate int - Frequência cardíaca registrada pelo sensor.
     * @param deviceId String - Identificador do dispositivo do paciente.
     * @param isSeriousCondition boolean - Condição atual do paciente.
     * @param isSeriousConditionLabel String - Condição atual do paciente em
     * formato de texto.
     * @param patientSeverityLevel float - Nível de gravidade do paciente.
     */
    public PatientDevice(
            String name,
            String deviceId,
            float bodyTemperature,
            int respiratoryFrequency,
            float bloodOxygenation,
            int bloodPressure,
            int heartRate,
            boolean isSeriousCondition,
            String isSeriousConditionLabel,
            float patientSeverityLevel
    ) {
        this.name = name;
        this.deviceId = deviceId;
        this.bodyTemperature = bodyTemperature;
        this.respiratoryFrequency = respiratoryFrequency;
        this.bloodOxygenation = bloodOxygenation;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.isSeriousCondition = isSeriousCondition;
        this.isSeriousConditionLabel = isSeriousConditionLabel;
        this.patientSeverityLevel = patientSeverityLevel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setBodyTemperature(float bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public void setRespiratoryFrequency(int respiratoryFrequency) {
        this.respiratoryFrequency = respiratoryFrequency;
    }

    public void setBloodOxygenation(float bloodOxygenation) {
        this.bloodOxygenation = bloodOxygenation;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setIsSeriousCondition(boolean isSeriousCondition) {
        this.isSeriousCondition = isSeriousCondition;
    }

    public void setIsSeriousConditionLabel(String isSeriousConditionLabel) {
        this.isSeriousConditionLabel = isSeriousConditionLabel;
    }

    public void setPatientSeverityLevel(float patientSeverityLevel) {
        this.patientSeverityLevel = patientSeverityLevel;
    }
}
