package utils;

import java.util.List;
import models.PatientDevice;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe para transformar a lista de pacientes em JSON.
 *
 * @author Allan Capistrano
 */
public class PatientToJson {

    /**
     * Transforma a lista de pacientes no formato JSON.
     *
     * @param patientDevicesList List<PatientDevice> - Lista de pacientes.
     * @param hasStatusCode
     * @return JSONObject
     */
    public static JSONObject handle(
            List<PatientDevice> patientDevicesList,
            boolean hasStatusCode
    ) {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        
        if (hasStatusCode){
            json.put("statusCode", 200);
        }

        for (PatientDevice patientDevice : patientDevicesList) {
            JSONObject patientDeviceJson = new JSONObject();

//            patientDeviceJson.put("deviceId",
//                    patientDevice.getDeviceId());
//            patientDeviceJson.put("name",
//                    patientDevice.getName());
//            patientDeviceJson.put("bodyTemperatureSensor",
//                    patientDevice.getBodyTemperature());
//            patientDeviceJson.put("respiratoryFrequencySensor",
//                    patientDevice.getRespiratoryFrequency());
//            patientDeviceJson.put("bloodOxygenationSensor",
//                    patientDevice.getBloodOxygenation());
//            patientDeviceJson.put("bloodPressureSensor",
//                    patientDevice.getBloodPressure());
//            patientDeviceJson.put("heartRateSensor",
//                    patientDevice.getHeartRate());

            jsonArray.put(patientDeviceJson);
        }

        json.put("data", jsonArray);

        return json;
    }
}
