/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.connector.integration.test.googlespreadsheet;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

public class GooglespreadsheetConnectorIntegrationTest extends ConnectorIntegrationTestBase {
    private static final int sleepingTime = 5000;
    private Map<String, String> esbRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();
    private String apiEndpointUrl;

    /**
     * Set up the environment.
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        init("googlespreadsheet-connector-3.0.0");
        esbRequestHeadersMap.put("Content-Type", "application/json");
        apiRequestHeadersMap.put("Content-Type", "application/json");
        apiEndpointUrl = "https://www.googleapis.com/oauth2/v3/token?grant_type=refresh_token&client_id="
                + connectorProperties.getProperty("clientId") + "&client_secret="
                + connectorProperties.getProperty("clientSecret") + "&refresh_token="
                + connectorProperties.getProperty("refreshToken");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndpointUrl, "POST",
                apiRequestHeadersMap);
        final String accessToken = apiRestResponse.getBody().getString("access_token");
        connectorProperties.put("accessToken", accessToken);
        apiRequestHeadersMap.put("Authorization", "Bearer " + accessToken);
        apiRequestHeadersMap.putAll(esbRequestHeadersMap);
    }

    /**
     * Positive test case for getMultipleCellData method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getMultipleCellData} integration test with mandatory parameters.")
    public void testGetMultipleCellDataWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getMultipleCellData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "getMultipleCellDataMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values:batchGet?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for getMultipleCellData method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getMultipleCellData} integration test with negative cases.")
    public void testGetMultipleCellDataWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getMultipleCellData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "getMultipleCellDataNegative.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 401);
    }

    /**
     * Positive test case for getMultipleCellData method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getMultipleCellData} integration test with Optional parameters.")
    public void testGetMultipleCellDataWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getMultipleCellData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "getMultipleCellDataOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values:batchGet?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId") + "&ranges="
                + connectorProperties.getProperty("ranges1") + "&dateTimeRenderOption="
                + connectorProperties.getProperty("dateTimeRenderOption") + "&majorDimension="
                + connectorProperties.getProperty("majorDimension") + "&valueRenderOption="
                + connectorProperties.getProperty("valueRenderOption");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for editMultipleCell method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {editMultipleCell} integration test with mandatory parameters.")
    public void testEditMultipleCellWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:editMultipleCell");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_editMultipleCellMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values:batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_editMultipleCellMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for editMultipleCell method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {editMultipleCell} integration test with negative cases.")
    public void testEditMultipleCellWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:editMultipleCell");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "editMultipleCellNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values:batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for editMultipleCell method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {editMultipleCell} integration test with Optional parameters.")
    public void testEditMultipleCellWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:editMultipleCell");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "editMultipleCellOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values:batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_editMultipleCellMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for createSpreadsheet method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {createSpreadsheet} integration test with mandatory parameters.")
    public void testCreateSpreadsheetWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:createSpreadsheet");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "createSpreadsheetMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + esbRestResponse.getBody().get("spreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for createSpreadsheet method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {createSpreadsheet} integration test with Optional parameters.")
    public void testCreateSpreadsheetWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:createSpreadsheet");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_createSpreadsheetOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + esbRestResponse.getBody().get("spreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Negative test case for createSpreadsheet method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {createSpreadsheet} integration test with negative cases.")
    public void testCreateSpreadsheetWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:createSpreadsheet");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "createSpreadsheetNegative.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 401);
    }

    /**
     * Positive test case for getSheetMetaData method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getSheetMetaData} integration test with mandatory parameters.")
    public void testGetSheetMetaDataWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getSheetMetaData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "getSheetMetaDataMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "?includeGridData="
                + connectorProperties.getProperty("includeGridData") + "&fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for getSheetMetaData method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getSheetMetaData} integration test with negative cases.")
    public void testGetSheetMetaDataWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getSheetMetaData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getSheetMetaDataNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" + "";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for getSheetMetaData method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getSheetMetaData} integration test with Optional parameters.")
    public void testGetSheetMetaDataWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getSheetMetaData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getSheetMetaDataOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "?includeGridData="
                + connectorProperties.getProperty("includeGridData") + "&fields="
                + connectorProperties.getProperty("fieldSpreadsheetId") + "&ranges="
                + connectorProperties.getProperty("ranges1") + "&ranges="
                + connectorProperties.getProperty("ranges2");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for addSheetBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addSheetBatchRequest} integration test with mandatory parameters.")
    public void testAddSheetBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addSheetBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_addSheetBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addSheetBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Negative test case for addSheetBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addSheetBatchRequest} integration test with negative cases.")
    public void testAddSheetBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addSheetBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "addSheetBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for addSheetBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addSheetBatchRequest} integration test with Optional parameters.")
    public void testAddSheetBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addSheetBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "addSheetBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addSheetBatchRequestOptional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
    }

    /**
     * Positive test case for updateSheetPropertiesBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateSheetPropertiesBatchRequest} integration test with mandatory parameters.")
    public void testUpdateSheetPropertiesBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateSheetPropertiesBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_updateSheetPropertiesBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateSheetPropertiesBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for updateSheetPropertiesBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateSheetPropertiesBatchRequest} integration test with negative cases.")
    public void testUpdateSheetPropertiesBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateSheetPropertiesBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateSheetPropertiesBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for updateSheetPropertiesBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateSheetPropertiesBatchRequest} integration test with Optional parameters.")
    public void testUpdateSheetPropertiesBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateSheetPropertiesBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateSheetPropertiesBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateSheetPropertiesBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for addConditionalFormatRuleBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addConditionalFormatRuleBatchRequest} integration test with mandatory parameters.")
    public void testAddConditionalFormatRuleBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "addConditionalFormatRuleBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addConditionalFormatRuleBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for addConditionalFormatRuleBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addConditionalFormatRuleBatchRequest} integration test with negative cases.")
    public void testAddConditionalFormatRuleBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "addConditionalFormatRuleBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for addConditionalFormatRuleBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addConditionalFormatRuleBatchRequest} integration test with Optional parameters.")
    public void testAddConditionalFormatRuleBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "addConditionalFormatRuleBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addConditionalFormatRuleBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for appendDimensionBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {appendDimensionBatchRequest} integration test with mandatory parameters.")
    public void testAppendDimensionBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:appendDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_appendDimensionBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_appendDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for appendDimensionBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {appendDimensionBatchRequest} integration test with negative cases.")
    public void testAppendDimensionBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:appendDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "appendDimensionBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for appendDimensionBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {appendDimensionBatchRequest} integration test with Optional parameters.")
    public void testAppendDimensionBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:appendDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "appendDimensionBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_appendDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for autoResizeDimensionsBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {autoResizeDimensionsBatchRequest} integration test with mandatory parameters.")
    public void testAutoResizeDimensionsBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:autoResizeDimensionsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_autoResizeDimensionsBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_autoResizeDimensionsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"), apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for autoResizeDimensionsBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {autoResizeDimensionsBatchRequest} integration test with negative cases.")
    public void testAutoResizeDimensionsBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:autoResizeDimensionsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "autoResizeDimensionsBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for autoResizeDimensionsBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {autoResizeDimensionsBatchRequest} integration test with Optional parameters.")
    public void testAutoResizeDimensionsBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:autoResizeDimensionsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "autoResizeDimensionsBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_autoResizeDimensionsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for copyPasteBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {copyPasteBatchRequest} integration test with mandatory parameters.")
    public void testCopyPasteBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:copyPasteBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_copyPasteBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_copyPasteBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for copyPasteBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {copyPasteBatchRequest} integration test with negative cases.")
    public void testCopyPasteBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:copyPasteBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "copyPasteBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for copyPasteBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {copyPasteBatchRequest} integration test with Optional parameters.")
    public void testCopyPasteBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:copyPasteBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "copyPasteBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_copyPasteBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for cutPasteBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {cutPasteBatchRequest} integration test with mandatory parameters.")
    public void testCutPasteBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:cutPasteBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_cutPasteBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_cutPasteBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for cutPasteBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {cutPasteBatchRequest} integration test with negative cases.")
    public void testCutPasteBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:cutPasteBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "cutPasteBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for cutPasteBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {cutPasteBatchRequest} integration test with Optional parameters.")
    public void testCutPasteBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:cutPasteBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "cutPasteBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_cutPasteBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for deleteDimensionBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteDimensionBatchRequest} integration test with mandatory parameters.")
    public void testDeleteDimensionBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_deleteDimensionBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for deleteDimensionBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteDimensionBatchRequest} integration test with negative cases.")
    public void testDeleteDimensionBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "deleteDimensionBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for deleteDimensionBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteDimensionBatchRequest} integration test with Optional parameters.")
    public void testDeleteDimensionBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "deleteDimensionBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for getCellData method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getCellData} integration test with mandatory parameters.")
    public void testGetCellDataWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getCellData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "getCellDataMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeGetCellData") + "?fields="
                + connectorProperties.getProperty("fieldRange");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("range"),
                apiRestResponse.getBody().get("range"));
    }

    /**
     * Negative test case for getCellData method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getCellData} integration test with negative cases.")
    public void testGetCellDataWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getCellData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "getCellDataNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/" + " " + "?fields="
                + connectorProperties.getProperty("field_values");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for getCellData method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {getCellData} integration test with Optional parameters.")
    public void testGetCellDataWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:getCellData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "getCellDataOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeGetCellData") + "?fields="
                + connectorProperties.getProperty("fieldRange") + "&valueRenderOption="
                + connectorProperties.getProperty("valueRenderOption") + "&majorDimension="
                + connectorProperties.getProperty("majorDimension") + "&dateTimeRenderOption="
                + connectorProperties.getProperty("dateTimeRenderOption");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("range"),
                apiRestResponse.getBody().get("range"));
    }

    /**
     * Positive test case for insertDimensionBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {insertDimensionBatchRequest} integration test with mandatory parameters.")
    public void testInsertDimensionBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:insertDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_insertDimensionBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_insertDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for insertDimensionBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {insertDimensionBatchRequest} integration test with negative cases.")
    public void testInsertDimensionBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:insertDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "insertDimensionBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for insertDimensionBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {insertDimensionBatchRequest} integration test with Optional parameters.")
    public void testInsertDimensionBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:insertDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "insertDimensionBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_insertDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for mergeCellsBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {mergeCellsBatchRequest} integration test with mandatory parameters.")
    public void testMergeCellsBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:mergeCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_mergeCellsBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_mergeCellsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for mergeCellsBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {mergeCellsBatchRequest} integration test with negative cases.")
    public void testMergeCellsBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:mergeCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "mergeCellsBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for mergeCellsBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {mergeCellsBatchRequest} integration test with Optional parameters.")
    public void testMergeCellsBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:mergeCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "mergeCellsBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_mergeCellsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for moveDimensionBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {moveDimensionBatchRequest} integration test with mandatory parameters.")
    public void testMoveDimensionBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:moveDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_moveDimensionBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_moveDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for moveDimensionBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {moveDimensionBatchRequest} integration test with negative cases.")
    public void testMoveDimensionBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:moveDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "moveDimensionBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for moveDimensionBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {moveDimensionBatchRequest} integration test with Optional parameters.")
    public void testMoveDimensionBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:moveDimensionBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "moveDimensionBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_moveDimensionBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for repeatCellsBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {repeatCellsBatchRequest} integration test with mandatory parameters.")
    public void testRepeatCellsBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:repeatCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_repeatCellsBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_repeatCellsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for repeatCellsBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {repeatCellsBatchRequest} integration test with negative cases.")
    public void testRepeatCellsBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:repeatCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "repeatCellsBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for repeatCellsBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {repeatCellsBatchRequest} integration test with Optional parameters.")
    public void testRepeatCellsBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:repeatCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "repeatCellsBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_repeatCellsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for setDataValidationBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {setDataValidationBatchRequest} integration test with mandatory parameters.")
    public void testSetDataValidationBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:setDataValidationBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_setDataValidationBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_setDataValidationBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for setDataValidationBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {setDataValidationBatchRequest} integration test with negative cases.")
    public void testSetDataValidationBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:setDataValidationBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "setDataValidationBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for setDataValidationBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {setDataValidationBatchRequest} integration test with Optional parameters.")
    public void testSetDataValidationBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:setDataValidationBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "setDataValidationBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_setDataValidationBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for sortRangeBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {sortRangeBatchRequest} integration test with mandatory parameters.")
    public void testSortRangeBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:sortRangeBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_sortRangeBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_sortRangeBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for sortRangeBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {sortRangeBatchRequest} integration test with negative cases.")
    public void testSortRangeBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:sortRangeBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "sortRangeBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for sortRangeBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {sortRangeBatchRequest} integration test with Optional parameters.")
    public void testSortRangeBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:sortRangeBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "sortRangeBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_sortRangeBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for updateBordersBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateBordersBatchRequest} integration test with mandatory parameters.")
    public void testUpdateBordersBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateBordersBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_updateBordersBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateBordersBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for updateBordersBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateBordersBatchRequest} integration test with negative cases.")
    public void testUpdateBordersBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateBordersBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateBordersBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for updateBordersBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateBordersBatchRequest} integration test with Optional parameters.")
    public void testUpdateBordersBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateBordersBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateBordersBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateBordersBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for updateCellsBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateCellsBatchRequest} integration test with mandatory parameters.")
    public void testUpdateCellsBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_updateCellsBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateCellsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for updateCellsBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateCellsBatchRequest} integration test with negative cases.")
    public void testupdateCellsBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateCellsBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/" +
                connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for updateCellsBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateCellsBatchRequest} integration test with Optional parameters.")
    public void testUpdateCellsBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateCellsBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateCellsBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateCellsBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for updateConditionalFormatRuleBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateConditionalFormatRuleBatchRequest} integration test with mandatory parameters.")
    public void testUpdateConditionalFormatRuleBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_updateConditionalFormatRuleBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateConditionalFormatRuleBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for updateConditionalFormatRuleBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateConditionalFormatRuleBatchRequest} integration test with negative cases.")
    public void testUpdateConditionalFormatRuleBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateConditionalFormatRuleBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for updateConditionalFormatRuleBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateConditionalFormatRuleBatchRequest} integration test with Optional parameters.")
    public void testUpdateConditionalFormatRuleBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "updateConditionalFormatRuleBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateConditionalFormatRuleBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for updateDimensionPropertiesBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateDimensionPropertiesBatchRequest} integration test with mandatory parameters.")
    public void testUpdateDimensionPropertiesBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateDimensionPropertiesBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_updateDimensionPropertiesBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateDimensionPropertiesBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"), apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for updateDimensionPropertiesBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateDimensionPropertiesBatchRequest} integration test with negative cases.")
    public void testUpdateDimensionPropertiesBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateDimensionPropertiesBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateDimensionPropertiesBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for updateDimensionPropertiesBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {updateDimensionPropertiesBatchRequest} integration test with Optional parameters.")
    public void testUpdateDimensionPropertiesBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:updateDimensionPropertiesBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "updateDimensionPropertiesBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_updateDimensionPropertiesBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for copyTo method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {copyTo} integration test with mandatory parameters.")
    public void testCopyToWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:copyTo");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_copyToMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/sheets/"
                + connectorProperties.getProperty("sheetId")
                + ":copyTo?fields=sheetType";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_copyToMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("sheetType"),
                apiRestResponse.getBody().get("sheetType"));
    }

    /**
     * Negative test case for copyTo method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {copyTo} integration test with negative cases.")
    public void testCopyToWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:copyTo");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "copyToNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/sheets/"
                + connectorProperties.getProperty("sheetId")
                + ":copyTo?fields=sheetType";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for copyTo method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {copyTo} integration test with Optional parameters.")
    public void testCopyToWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:copyTo");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "copyToOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/sheets/"
                + connectorProperties.getProperty("sheetId")
                + ":copyTo?fields=" + connectorProperties.getProperty("fieldsSheetType");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_copyToMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("sheetType"),
                apiRestResponse.getBody().get("sheetType"));
    }

    /**
     * Positive test case for deleteConditionalFormatRuleBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteConditionalFormatRuleBatchRequest} integration test with mandatory parameters.")
    public void testDeleteConditionalFormatRuleBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_deleteConditionalFormatRuleBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteConditionalFormatRuleBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for deleteConditionalFormatRuleBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteConditionalFormatRuleBatchRequest} integration test with negative cases.")
    public void testDeleteConditionalFormatRuleBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "deleteConditionalFormatRuleBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for deleteConditionalFormatRuleBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteConditionalFormatRuleBatchRequest} integration test with Optional parameters.")
    public void testDeleteConditionalFormatRuleBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteConditionalFormatRuleBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "deleteConditionalFormatRuleBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteConditionalFormatRuleBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for deleteSheetBatchRequest method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteSheetBatchRequest} integration test with mandatory parameters.")
    public void testDeleteSheetBatchRequestWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteSheetBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_deleteSheetBatchRequestMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteSheetBatchRequestMandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Negative test case for deleteSheetBatchRequest method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteSheetBatchRequest} integration test with negative cases.")
    public void testDeleteSheetBatchRequestWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteSheetBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "deleteSheetBatchRequestNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId")
                + ":batchUpdate?fields=" + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for deleteSheetBatchRequest method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {deleteSheetBatchRequest} integration test with Optional parameters.")
    public void testDeleteSheetBatchRequestWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:deleteSheetBatchRequest");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "deleteSheetBatchRequestOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + ":batchUpdate?fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteSheetBatchRequestOptional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for editCell method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {editCell} integration test with mandatory parameters.")
    public void testEditCellWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:editCell");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "editCellMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeEditCell") + "?valueInputOption="
                + connectorProperties.getProperty("valueInputOptionEditCell") + "&fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "PUT",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for editCell method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {editCell} integration test with negative cases.")
    public void testEditCellWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:editCell");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "editCellNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeEditCell") + "?valueInputOption="
                + "" + "&fields=" + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "PUT",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for editCell method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {editCell} integration test with Optional parameters.")
    public void testEditCellWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:editCell");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_editCellOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeEditCell") + "?valueInputOption="
                + connectorProperties.getProperty("valueInputOptionEditCell") + "&fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "PUT",
                apiRequestHeadersMap, "api_editCellOptional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Positive test case for addRowsColumnsData method with mandatory parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addRowsColumnsData} integration test with mandatory parameters.")
    public void testAddRowsColumnsDataWithMandatoryParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addRowsColumnsData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "addRowsColumnsDataMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeaddRows") + ":append?valueInputOption="
                + connectorProperties.getProperty("valueInputOptionaddRows") + "&fields="
                + connectorProperties.getProperty("fieldSpreadsheetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }

    /**
     * Negative test case for addRowsColumnsData method.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addRowsColumnsData} integration test with negative cases.")
    public void testAddRowsColumnsDataWithNegativeCase() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addRowsColumnsData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "addRowsColumnsDataNegative.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeaddRows") + ":append?valueInputOption="
                + "" + "&fields=" + connectorProperties.getProperty("fieldUpdates");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 400);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 400);
    }

    /**
     * Positive test case for addRowsColumnsData method with Optional parameters.
     */
    @Test(groups = {"wso2.esb"}, description = "googlespreadsheet {addRowsColumnsData} integration test with Optional parameters.")
    public void testAddRowsColumnsDataWithOptionalParameters() throws Exception {
        Thread.sleep(sleepingTime);
        esbRequestHeadersMap.put("Action", "urn:addRowsColumnsData");
        RestResponse<JSONObject> esbRestResponse =
                sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                        "esb_addRowsColumnsDataOptional.json");
        String apiEndPoint = connectorProperties.getProperty("apiUrl") + "/"
                + connectorProperties.getProperty("spreadsheetId") + "/values/"
                + connectorProperties.getProperty("rangeaddRows") + ":append?valueInputOption="
                + connectorProperties.getProperty("valueInputOptionaddRows") + "&fields="
                + connectorProperties.getProperty("fieldSpreadsheetId")
                + "&insertDataOption=" + connectorProperties.getProperty("insertDataOption");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addRowsColumnsDataOptional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().get("spreadsheetId"),
                apiRestResponse.getBody().get("spreadsheetId"));
    }
}