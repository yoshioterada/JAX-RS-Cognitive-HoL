/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3;

import com.yoshio3.entities.FaceDetectRequestJSONBody;
import com.yoshio3.entities.FaceDetectResponseJSONBody;
import com.yoshio3.entities.MyObjectMapperProvider;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author Yoshio Terada
 */
public class Main {

    public static void main(String... args) {
        Main main = new Main();
        main.execPOSTSample();
    }

    private void execPOSTSample() {
        Client client = ClientBuilder.newBuilder()
                .register(MyObjectMapperProvider.class)
                .register(JacksonFeature.class)
                .build();
        WebTarget target = client
                .target("https://westus.api.cognitive.microsoft.com")
                .path("/face/v1.0/detect")
                .queryParam("returnFaceId", "true")
                .queryParam("returnFaceLandmarks", "false")
                .queryParam("returnFaceAttributes", "age,gender,smile");
        
        FaceDetectRequestJSONBody entity = new FaceDetectRequestJSONBody();
        entity.setUrl("https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png");

        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", "3eef51745aea42a29023205dd8192a87")
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));

        FaceDetectResponseJSONBody[] personsInPicture = null;
        if (isRequestSuccess(response)) {
            personsInPicture = response.readEntity(FaceDetectResponseJSONBody[].class);
        } else {
            handleIllegalState(response);
        }
       if(personsInPicture == null){
            return;
        }
        
        //For example, get the information of first person of the picture
        FaceDetectResponseJSONBody faceDetectData = personsInPicture[0];
        //Get age, gender, 
        Map<String, Object> faceAttributes = faceDetectData.getFaceAttributes();
        Double age = (Double) faceAttributes.get("age");
        String gender = (String) faceAttributes.get("gender"); 

        System.out.println("Age：" + age + "\tGender：" + gender);
    }

    private void execGETSample() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.microsoft.com/ja-jp/");
        Response response = target
                .request(MediaType.TEXT_HTML)
                .get();
        if (isRequestSuccess(response)) {
            System.out.println(response.readEntity(String.class));
        } else {
            handleIllegalState(response);
        }
    }

    /*
     Check the REST Invocation is success or failed.
     */
    private boolean isRequestSuccess(Response response) {
        Response.StatusType statusInfo = response.getStatusInfo();
        Response.Status.Family family = statusInfo.getFamily();
        return family != null && family == Response.Status.Family.SUCCESSFUL;
    }

    /*
     Operate when the REST invocaiton failed.
     */
    private void handleIllegalState(Response response)
            throws IllegalStateException {
        String error = response.readEntity(String.class);
        throw new IllegalStateException(error);
    }
}
