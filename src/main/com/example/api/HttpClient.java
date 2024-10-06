package com.example.api;

import com.example.api.responses.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class HttpClient {
    private final Logger logger;
    private final Gson gson;

    public HttpClient(Logger logger) {
        this.logger = logger;
        this.gson = new Gson();
    }

    public String generateAuthorizationToken(String environment, String endpoint, String consumerKey, String clientSecret){
        String body = "grant_type" +
                "=" +
                "client_credentials" +
                "&" +
                "client_id" +
                "=" +
                consumerKey +
                "&" +
                "client_secret" +
                "=" +
                clientSecret;

        StringEntity content = new StringEntity(body, ContentType.APPLICATION_FORM_URLENCODED);
        ClassicHttpRequest request = ClassicRequestBuilder.post(
                createUrl(environment,endpoint))
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .setEntity(content)
                .build();

        AuthenticationResponse authenticationResponse = gson.fromJson(
                sendHttpRequest(request,"Generating auth token"),
                AuthenticationResponse.class);

        return authenticationResponse.access_token();
    }

    public String createSalesforceObject(String environment, String endpoint, String token, JSONObject body){
        StringEntity content = new StringEntity(body.toString(), ContentType.APPLICATION_JSON);
        ClassicHttpRequest request = ClassicRequestBuilder.post(
                createUrl(environment,endpoint))
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .addHeader(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                .setEntity(content)
                .build();

        SObjectResponse sObjectResponse = gson.fromJson(
                sendHttpRequest(request,"Creating Salesforce Object"),
                SObjectResponse.class);

        String id = sObjectResponse.id();
        logger.info("Object created successfully with id: " + id);
        return id;
    }

    public void updateFieldsFromSalesforceObject(String environment, String endpoint, String token, String objectId, Map<String,String> fieldsWithValues){
        StringEntity content = new StringEntity(gson.toJson(fieldsWithValues), ContentType.APPLICATION_JSON);
        ClassicHttpRequest request =
                ClassicRequestBuilder
                        .patch(createUrl(environment,endpoint) + objectId)
                        .addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .addHeader(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .setEntity(content)
                        .build();

        sendHttpRequest(request,"Updating Salesforce Object");

        logger.info("Successfully updated record with id: " + objectId);
        for (Map.Entry<String, String> entry : fieldsWithValues.entrySet()) {
            logger.info(entry.getKey() + " => " + entry.getValue());
        }
    }

    public void deleteSalesforceObject(String environment, String endpoint, String token, String objectId) {
        ClassicHttpRequest request =
                ClassicRequestBuilder
                        .delete(createUrl(environment,endpoint) + objectId)
                        .addHeader(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .build();

        sendHttpRequest(request,"Deleting Salesforce Object");

        logger.info("Successfully removed record with id: " + objectId);
    }

    public <T extends Identifiable> List<T> fetchData(String environment, String endpoint, String token, String query, Class<T> type){
        SOQLResponse response = executeSOQLQuery(createUrl(environment,endpoint),token,query);

        if(response == null)
            return null;

        String json = gson.toJson(response.records());

        Type listType = TypeToken.getParameterized(List.class, type).getType();
        return gson.fromJson(json,listType);
    }

    private SOQLResponse executeSOQLQuery(String uri, String token, String query){
        ClassicHttpRequest request =
                ClassicRequestBuilder
                        .get(uri)
                        .addHeader(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                        .addParameter("q",query)
                        .build();

        return gson.fromJson(
                sendHttpRequest(request,"Executing SOQL query"),
                SOQLResponse.class);
    }
    private String sendHttpRequest(ClassicHttpRequest request, String message){
        String returnValue = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){

            logger.info("Sending request: " + request.toString());

            returnValue = httpClient.execute(request, response -> {
                final int responseStatusCode = response.getCode();
                final HttpEntity responseEntity = response.getEntity();
                final String result = responseEntity != null ? EntityUtils.toString(responseEntity) : null;
                if(responseStatusCode < 200 || responseStatusCode >= 300){
                    logger.error(message + " http request failed with status code: " + responseStatusCode);
                    logger.error(result);

                    throw new RuntimeException(message + " http request failed!");
                }

                logger.info(message + " finished successfully!");
                EntityUtils.consume(responseEntity);
                return result;
            });
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return returnValue;
    }

    private String createUrl(String environment, String endpoint){
        return "https://" + environment + ".develop.my.salesforce.com" + endpoint;
    }
}
