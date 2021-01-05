# JAX-RS RESTful Web Service to Cognitive Services HoL

This is a Hands on Lab contents for creating Cognitive Services by Java RESTful Web Service(JAX-RS).  

**Note :**
-----  
Since 2017/1/17, the Cognitive service endpoint URL had changed to [https://westus.api.cognitive.microsoft.com/face/v1.0?WT.mc_id=java-0000-yoterada](https://westus.api.cognitive.microsoft.com/face/v1.0?WT.mc_id=java-0000-yoterada "https://westus.api.cognitive.microsoft.com/face/v1.0?WT.mc_id=java-0000-yoterada"). 

Even now you are using the OLD url ([https://api.projectoxford.ai/face/v1.0/](https://api.projectoxford.ai/face/v1.0/ "https://api.projectoxford.ai/face/v1.0/") ), please modify the URL ASAP? The old API endpoints will be deprecated 90 days(on 4/15).


Preparation  
-----  
##  1. [Accecss to the Cognitive Services Site](https://www.microsoft.com/cognitive-services/en-us/?WT.mc_id=java-0000-yoterada "Accecss to the Cognitive Services Site")  
Then you can see following screen. 
![Cognitive Services Site](https://c1.staticflickr.com/1/530/32562085411_14c8374e78_z.jpg "Cognitive Services Site")  
## 2. [Push the button of 'Get started for free'](https://www.microsoft.com/cognitive-services/en-us/sign-up?WT.mc_id=java-0000-yoterada "Get started for free")  
Then you can see following screen. So please "Sign up" by using (Microsoft account or GitHub or LinkedIn) account ? ![Get Started for free](https://c1.staticflickr.com/1/410/32305249570_76043c5247_z.jpg "Get Started for free")
## 3. Get the Key of Face & Emotion API  
After sign up the account, you can see following screen. If you get the key to access the cognitieve services, you can try it.  
 ![](https://c1.staticflickr.com/1/420/31841316184_ea1f2bdc3c_z.jpg "")  For example, Please search the key of "Face" and "Emotion" API as follows? ![Face & Emotion Key](https://c1.staticflickr.com/1/359/31841316904_523e839f51_z.jpg "Face & Emotion Key")  Then Please push the link of "Show"? You will be able see the access key like following detital number "3eef5*********************192a87". ![Face & Emotion Access Key](https://c1.staticflickr.com/1/779/31841317454_135bb40be6_z.jpg "Face & Emotion Access Key")
## 4. [Go to Face API site](https://www.microsoft.com/cognitive-services/en-us/face-api?WT.mc_id=java-0000-yoterada "Go to Face API site")
After you get the access key, please go to the Face API site? Please push the menu of "APIs" on top of the screen? Then advanced menu will be showed and push the link of "Face"? 
	![APIs](https://c1.staticflickr.com/1/381/31841317784_039721b1c5_z.jpg "APIs")  
	Then following "Face API" document site will be showed.
	![Face API document site](https://c1.staticflickr.com/1/520/31841318714_3385607336_z.jpg "Face API document site")
## 5. Push [API Reference](https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236 "Push API Reference") button
After you push the button, folliwng screen will be showed.  ![API Reference site](https://c1.staticflickr.com/1/261/31841319174_03f18af2a4_z.jpg "API Reference site")
## 6. Push [Open API Testing Console](https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236/console "Open API Testing Console") button  
If you scroll down the screen, you can see the [Open API Testing Console](https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236/console "Open API Testing Console") button. The please push the button?
	![Open API Testing Console](https://c1.staticflickr.com/1/653/32562090941_1f950ec095_z.jpg "Open API Testing Console")
## 7. Input Parameters  
If you push the button, some inpute text field will be showed on the screen. Then please input some parameters ? Especially pleaes input following parameters ?  
	![Input Parameters](https://c1.staticflickr.com/1/487/32562091261_0852589e0f_z.jpg "Input Parameters")  
 	**A. returnFaceAttributes** : age,gender,smile,facialhair,headpose,glasses  
 	**B. Ocp-Apim-Subscription-Key** : 3eef5*********************192a87  
	Please input the Access key to the above field ?   
	![Request body](https://c1.staticflickr.com/1/396/32531488272_f83d5fd373_z.jpg "Request body")
 	**C. Request body** :  
{  
    "url":"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png"  
}  
	 Please write the url of the picture (which is included a person who want to evaluate).
## 8. Push "Send" button
After input the parameters, please scroll down and you will be able to see the "Send" button. Then please push the button?
	![Push Send button](https://c1.staticflickr.com/1/496/32562091541_3666a06cce_z.jpg "Push Send button")
## 9. Confirm the Response
If there is no problem on the request and response, you can see following screen with Response Status Code as 200. If you can't get the response status code as 200, there is some miss configuration or request. So pleas modify the above configuration again ?
	![Confirm the Response](https://c1.staticflickr.com/1/730/32531488702_dd99f78bb6_z.jpg "Confirm the Response")
## 10. Confirm the Request URL and Request Body.  
In order to create the application, you need to understand the HTTP method call, URL and Request body. So please understand what kind of the information is needed for the service invocation ?

```
POST https://westus.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=age,gender,smile,facialhair,headpose,glasses HTTP/1.1
Content-Type: application/json
Host: westus.api.cognitive.microsoft.com
Ocp-Apim-Subscription-Key: ••••••••••••••••••••••••••••••••

{
    "url":"https://pbs.twimg.com/profile_images/1765776666/s-abetwitter1.png"
}
```

## 11. Next (Create Java Application)
[Create Cognitive Java Application with JAX-RS](./Create-App.md)


**Note :**
-----  
Since 2017/1/17, the Cognitive service endpoint URL had changed to [https://westus.api.cognitive.microsoft.com/face/v1.0?WT.mc_id=java-0000-yoterada](https://westus.api.cognitive.microsoft.com/face/v1.0?WT.mc_id=java-0000-yoterada "https://westus.api.cognitive.microsoft.com/face/v1.0?WT.mc_id=java-0000-yoterada"). 

Even now you are using the OLD url ([https://api.projectoxford.ai/face/v1.0/](https://api.projectoxford.ai/face/v1.0/ "https://api.projectoxford.ai/face/v1.0/") ), please modify the URL ASAP? The old API endpoints will be deprecated 90 days(on 4/15).

-----
