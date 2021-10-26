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
     * @param amount int - Quantidade de pacientes mais graves da lista.
     * @param hasStatusCode - Status code.
     * @return JSONObject
     */
    public static JSONObject handle(
            List<PatientDevice> patientDevicesList,
            int amount,
            boolean hasStatusCode
    ) {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        if (hasStatusCode) {
            json.put("statusCode", 200);
        }

        amount = (amount > patientDevicesList.size())
                ? patientDevicesList.size()
                : amount;

        for (int i = 0; i < amount; i++) {
            JSONObject patientDeviceJson = new JSONObject();

            patientDeviceJson.put("name",
                    patientDevicesList.get(i).getName());
            patientDeviceJson.put("deviceId",
                    patientDevicesList.get(i).getDeviceId());
            patientDeviceJson.put("isSeriousConditionLabel",
                    patientDevicesList.get(i).getIsSeriousConditionLabel());
            patientDeviceJson.put("fogServer",
                    patientDevicesList.get(i).getFogServer());

            jsonArray.put(patientDeviceJson);
        }

        json.put("data", jsonArray);

        return json;
    }

}
