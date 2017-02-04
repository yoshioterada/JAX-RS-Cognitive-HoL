# HoL : Create Cognitive Services Application with JAX-RS  

This is the HoL for Java begineers like students or biginner for JAX-RS client. If you already familir with Java. Please read the final source code directly?


## 1.  Create Maven Project
![Start NetBeans](https://c1.staticflickr.com/1/750/32648816036_6192827bf1_z.jpg)

![Create Maven Project](https://c1.staticflickr.com/1/370/32648816356_03a8399775_z.jpg)

![](https://c1.staticflickr.com/1/423/32648816526_0caccfcd37_z.jpg)

![](https://c1.staticflickr.com/1/713/32648816666_0e83cb495f_z.jpg)

## 2.  Modify the pom.xml file  
please add following dependencies onder properties ?  
![](https://c1.staticflickr.com/1/512/32648816846_879fc78000_z.jpg)

```
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>javax.ws.rs</groupId>
            <artifactId>javax.ws.rs-api</artifactId>
            <version>2.0</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.core</groupId>
            <artifactId>jersey-client</artifactId>
            <version>2.25</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-json-jackson</artifactId>
            <version>2.25</version>
        </dependency>
    </dependencies>
```
## 3. Build with Dependencies
In order to get the dependencies libraries like Jersey and Jackson,please execute the "Build with Dependencies" from the menu of NetBeans?

![](https://c1.staticflickr.com/1/293/32310386520_3161d8ce53.jpg)  
Then following screen will be showed.  
![](https://c1.staticflickr.com/1/752/32648817136_e2ca8e412f_z.jpg)  
And if you succeed to get the libraries, you can see the "BUILD SUCCESS" message on the console.  
![](https://c1.staticflickr.com/1/444/32648817236_89927562f1_z.jpg)  
##  4.  Create Main Class
In order to create Main class, plese open the "Source Packages" of the Project menu of left screen? Then you select the package and click the right mouse button on the package? Then following screen will be showed. Then please select "New" -> "Java Class..." ?  
![](https://c1.staticflickr.com/1/706/32648817376_77fa940627_z.jpg)  

Then you can see the following screen. And please change the name of "Class Name:" field to "Main"? And Please push the "Finish" button?  
![](https://c1.staticflickr.com/1/611/31846652114_7ea792ca99_z.jpg)  
Then you can see the following screen.  
![](https://c1.staticflickr.com/1/283/32310386880_4bdcf9d426_z.jpg)  
##  5. Basic of JAX-RS Programing(HTTP GET)
At first, Please write the following code ?  Following code will invoke the HTTP GET method to the target URL. After got the respose, it will show the response data to standard output as String.

```
package com.yoshio3;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yoshio Terada
 */
public class Main {

    public static void main(String... args) {
        Main main = new Main();
        main.execGETSample();
    }

    private void execGETSample() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.microsoft.com/ja-jp/");
        Response response = target
                .request(MediaType.TEXT_HTML)
                .get();
        System.out.println(response.readEntity(String.class));
    }
}
```

After write the code,please compile execute the "Run File" from the menu?  
![](https://c1.staticflickr.com/1/761/32648817746_645549cd71.jpg)  
Then you can see the **"BUILD SUCCES"** messages with the HTML code which you specified the URL. In this time, the HTML source code of [https://www.microsoft.com/ja-jp/](https://www.microsoft.com/ja-jp/ "Microsoft Japan") will be showed on console.  
![](https://c1.staticflickr.com/1/714/32648817976_ee063c004e_z.jpg)  
The Above code will output all of the response as String even though it failed. So you can't understand whether the response was succeed or not. In order to confirm the invocation success or failure, you can add like following code.

```
    private void execGETSample() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.microsoft.com/ja-jp/");
        Response response = target
                .request(MediaType.TEXT_HTML)
                .get();
        if (isRequestSuccess(response)) {
            System.out.println(response.readEntity(String.class));
        }else{
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
```

##  6. Basic of JAX-RS Programing(HTTP POST)
If you succeed the above HTTP GET code, it is easy to understand the POST code too. Compare with the above (HTTP GET) code, the POST code need additional code when invoke the post() method. Because GET method only get the data from the Web Server. However POST method need to send some data to the Server. In order to send some data to Server, POST is need additional code like follows. In this Sample, the program send the JSON data to the server.  
**Note: Following code is only sample code to understand the implementation of POST, so it doesn't run in realworld because invalid URL. Please don't write the following code?**

```
    private void execPOSTSample() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://yoshio3.com/");
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json("{\"url\":\"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png\"}"));
        if (isRequestSuccess(response)) {
            System.out.println(response.readEntity(String.class));
        } else {
            handleIllegalState(response);
        }
    }
```

##  7.  Invoke Cognitive Services from JAX-RS
Since now, we will implement the Cognitive Services by using JAX-RS client. Before write the code, please remember the HTTP invocation for Cognitive Services?

```
POST https://westus.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=age,gender,smile HTTP/1.1
Content-Type: application/json
Host: westus.api.cognitive.microsoft.com
Ocp-Apim-Subscription-Key: ••••••••••••••••••••••••••••••••

{
    "url":"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png"
}
```

In order to access to the Cognitive Services, at first you can write following code. Please write the following code?

```
package com.yoshio3;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Main {
    public static void main(String... args) {
        Main main = new Main();
        main.execPOSTSample();
    }
    
    private void execPOSTSample() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("https://westus.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=age,gender,smile,facialhair,headpose,glasses");
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", "3eef5*********************192a87")
                .post(Entity.json("{\n"
                        + "    \"url\":\"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png\"\n"
                        + "}"));
        if (isRequestSuccess(response)) {
            System.out.println(response.readEntity(String.class));
        } else {
            handleIllegalState(response);
        }
    }
}    
```

After compile the above code, please execute it?
then you can get the following result.

```
[  
  {  
  "faceId":"16ca5f7c-6e71-443e-b22e-e301f317a573",  
  "faceRectangle":{"top":38,"left":75,"width":42,"height":42},  
  "faceAttributes":{  
    "smile":0.999,  
    "gender":"male",  
    "age":51.2  
  }  
]
```
---
The above code is running correctly, however it is difficult to read and maintain. Expecially inside the client.target("http://...") invocation, it include a lot of informations like follows.

|Host|Path|
|:---|:---|
|westus.api.cognitive.microsoft.com|/face/v1.0/detect|

|Param|Value|
|:---|:---|
|returnFaceId|true|
|returnFaceLandmarks|false|
|returnFaceAttributes|age,gender,smile ...|

|Header|Value|
|:---|:---|
|Ocp-Apim-Subscription-Key|3eef5*********************192a87|

|Body|Value (ex:)|
|:---|:---|
|url|http://picture.site.com/president.jpg|

In order to cleaer the code, you can refactor the above code to following.

```
    private void execPOSTSample() {
        Client client = ClientBuilder.newClient();

        WebTarget target = client
                .target("https://westus.api.cognitive.microsoft.com")
                .path("/face/v1.0/detect")
                .queryParam("returnFaceId", "true")
                .queryParam("returnFaceLandmarks", "false")
                .queryParam("returnFaceAttributes", "age,gender,smile");

        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", "3eef5*********************192a87")
                .post(Entity.json("{\n"
                        + "    \"url\":\"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png\"\n"
                        + "}"));
        if (isRequestSuccess(response)) {
            System.out.println(response.readEntity(String.class));
        } else {
            handleIllegalState(response);
        }
    }
```

##  8. JSON Binding with JAX-B

Now, if we send the JSON data to the Cognitive Services, we can get the JSON data. 

**Send JSON data to Server (Request)** 

Following is the request body sample to send the Cognitive Services (Face API).

```
{
  "url":"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png"
}
```

**Received JSON data from Server (Response)**

Following is the response body sample from the Cognitive Services (Face API).

```
[  
  {  
  "faceId":"16ca5f7c-6e71-443e-b22e-e301f317a573",  
  "faceRectangle":{"top":38,"left":75,"width":42,"height":42},  
  "faceAttributes":{  
    "smile":0.999,  
    "gender":"male",  
    "age":51.2  
  }  
]
```


However for Java developers, we can treat the JSON data as Java Object. So since now, we bind the JSON data to Java Object by using JAX-B (After Java EE 8 is released, we will be able to use JSON-B).

**Create a new Java Package for sotre the binding Object**  

At first, please create the new "Java Package..."? please select the exsiting package like "com.yoshio3" then click the mouse right button ?  
![](https://c1.staticflickr.com/1/673/32310387200_a89819a3d4.jpg)  
Then you can select the "Java Package...". After selected the "Java Package...", you can see the following window. Then, please input the "com.yoshio3.entities" into the "Package Name:" and push the Finish button?  
![](https://c1.staticflickr.com/1/407/31846652314_2c295afbc6.jpg)  

**Create MyObjectMapperProvider class**  

Please create MyObjectMapperProvider class?
![](https://c1.staticflickr.com/1/765/32310387410_5e89a86d33.jpg)  
This class is needed to be able to use the JSON map functionality with Jackson (JSON Provider)? [The detail explanation was wrote on this link.](https://jersey.java.net/documentation/latest/media.html "")  

```
package com.yoshio3.entities;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * In order to map the "JSON map" to Java Map Object
 *
 * @author Yoshio Terada
 */
@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public MyObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {

        return defaultObjectMapper;

    }

    private static ObjectMapper createCombinedObjectMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, true)
                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
                .setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
    }

    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.enable(SerializationFeature.INDENT_OUTPUT);

        return result;
    }

    private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {
        final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
        return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
    }
}
```

**Create JSON Binding Object for Request**  
For request body, only "url" is needed. 

```
{
  "url":"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png"
}
```

For the above JSON data, pleas create FaceDetectRequestJSONBody class?  
![](https://c1.staticflickr.com/1/769/31846652504_51059e2942.jpg)  
![](https://c1.staticflickr.com/1/474/32310387620_8dd2731d50.jpg)  
In the class, please declare one instance valuable as url? After that please create setter and getter method for it? This is the binding object for above JSON data. 

```
package com.yoshio3.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FaceDetectRequestJSONBody {
    
    private String url ;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
```

**Create JSON Binding Object for Response**  
For response body, there is 3 element.  

```
[  
  {  
  "faceId":"16ca5f7c-6e71-443e-b22e-e301f317a573",  
  "faceRectangle":{"top":38,"left":75,"width":42,"height":42},  
  "faceAttributes":{  
    "smile":0.999,  
    "gender":"male",  
    "age":51.2  
  }  
]
```

For the above JSON data, pleas create FaceDetectResponseJSONBody class? 
![](https://c1.staticflickr.com/1/264/32648818866_9f74032e61.jpg)  
We need to declare 3 instance valuable inside of the class.
Please write the following code?  

```
package com.yoshio3.entities;

import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FaceDetectResponseJSONBody {

    private String faceId;
    private Map<String, Object> faceRectangle;
    private Map<String, Object> faceAttributes;

    /**
     * @return the faceId
     */
    public String getFaceId() {
        return faceId;
    }

    /**
     * @param faceId the faceId to set
     */
    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    /**
     * @return the faceRectangle
     */
    public Map<String, Object> getFaceRectangle() {
        return faceRectangle;
    }

    /**
     * @param faceRectangle the faceRectangle to set
     */
    public void setFaceRectangle(Map<String, Object> faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    /**
     * @return the faceAttributes
     */
    public Map<String, Object> getFaceAttributes() {
        return faceAttributes;
    }

    /**
     * @param faceAttributes the faceAttributes to set
     */
    public void setFaceAttributes(Map<String, Object> faceAttributes) {
        this.faceAttributes = faceAttributes;
    }

    @Override
    public String toString() {
        return "FaceDetectResponseJSONBody{" + "faceId=" + faceId + ", faceRectangle=" + faceRectangle + ", faceAttributes=" + faceAttributes + '}';
    }
}
```

**Modify the Request Code**  
Then you can use the binding object which you created the above. In order to use the Jackson functionality, at first, please modify the instance cratetion code of Client object by using ClientBuilder.newBuilder()? Then please create a instance of FaceDetectRequestJSONBody and specify the picture url to setUrl() method? This is the change for the Request code.     

```
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
                .header("Ocp-Apim-Subscription-Key", "3eef5*********************192a87")
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));

        if (isRequestSuccess(response)) {
            System.out.println(response.readEntity(String.class));
        } else {
            handleIllegalState(response);
        }
    }
```


**Modify the Response Code**  

After received the Response from Server, we can read the data by using readEntity() method. Until now, we got the data as "String.class". However since now, we can use the FaceDetectResponseJSONBody[].class instead of the String.class. After read the data, you can treat the JSON data to the Array of FaceDetectResponseJSONBody object.  


```
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
                .header("Ocp-Apim-Subscription-Key", "3eef5*********************192a87")
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
```

After modified the above code, please compile again and execute it? Then following result will be showed on concole.


## 9. Finally

All of source code for this HoL is exsiting on [this site](https://github.com/yoshioterada/JAX-RS-Cognitive-HoL/tree/master/src/main/java/com/yoshio3).

In this Hands on Lab, you can learn how to implement the Java RESTful Web Service(JAX-RS). This is very basic senario to write the REST client in Java. So if you change both url and mapped JSON binding object, you can easily reuse this code for every REST call. If you finished this, please try to another cognitive services like Emotional API ?

And you can see some additional sample on following. Please try or enjoy it?

**Aditional Sample as follows**  

1. [OCR Sample Application](https://github.com/yoshioterada/OCR-Sample-of-Cognitive-Service)  
2. [Face and Emotion detect on Java EE App](https://github.com/yoshioterada/Face-Detect-Cognitive-Service-with-Java-EE)  
3. [Office 365 Excel REST API](https://github.com/yoshioterada/Office-365-Excel-REST-API-for-Java)  
4. [Intrusion detection Java App on RaspberryPi with Microsoft Azure](https://github.com/yoshioterada/RaspberryPi-To-Azure-IoT-Hub)  
