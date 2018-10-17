## Integration Tests: WSO2 EI Google Spreadsheet Connector for Google Sheets API Version v4.

### Pre-requisites:

    - Maven 3.x
    - Java 1.7 or above
    - The org.wso2.esb.integration.integration-base project is required. The test suite has been configured to download this project automatically. If the automatic download fails, download the following project and compile it using the mvn clean install command to update your local repository:
      https://github.com/wso2-extensions/esb-integration-base

### Tested Platforms:

    - Ubuntu 16.04
    - WSO2 EI 6.4.0
    - java 1.8


### Steps to follow in setting integration test.


 1. Download EI 6.4.0  by navigating to the following [URL](http://wso2.com/products/enterprise-service-bus/#).
 2. Copy the EI 6.4.0 zip to the location `Connector_Home/repository/`
 3. Follow this [Doc](https://docs.wso2.com/display/IntegrationCloud/Get+Credentials+for+Google+Spreadsheet) to get a client id, client secret, access token, refresh token from OAuth 2.0 Playground.

 4. Update the following properties in `esb-connector-googlespreadsheet.properties` file at `Connector_Home/repository/`
        refreshToken, clientId, clientSecret, accessToken, apiUrl
 5. Update the googlespreadsheet properties file at location `<Connector_Home>/src/test/resources/artifacts/ESB/connector/config` as below.

    <br/><br/>

    | Property | Description |
    | ------------- |-------------|
    | refreshToken | Use the Refresh token which is obtained in step 3 |
    | clientId | Use the Client ID which is obtained in step 3 |				                    - .
    | clientSecret | Use the Client Secret which is obtained in step 3 |						                    - .
    | accessToken | Use the accessToken which is obtained in step 3 |
    | apiUrl | Use the API URL of the google spreadsheet |
    | ranges, ranges1, ranges2 | Sheet cell range |
    | requestsaddSheetBatchRequest | Payload to add sheet to spreadsheet |
    | requestsaddSheetBatchRequest1 | Payload to add sheet  to spreadsheet |
    | requestsdeleteSheetBatchRequest | Payload to delete sheet |
    | requestsdeleteSheetBatchRequest1 | Payload to delete sheet |
    | requestsupdateSheetPropertiesBatchRequest | Payload to update sheet properties |
    | requestsaddConditionalFormatRuleBatchRequest | Payload to add contitional format rule |
    | requestsAppendDimension | Payload to append dimension |
    | requestsAutoResizeDimensionsBatchRequest | Payload to auto resize dimention |
    | requestsCopyPasteBatchRequest | Payload to copy and paste |
    | requestsCutPasteBatchRequest | Payload to cut paste |
    | requestsDeleteConditionalFormatRuleBatchRequest | Payload to delete conditional format rule |
    | requestDeleteDimensionBatchRequest | Payload to delete dimension |
    | requestsInsertDimensionBatchRequest | Payload to insert dimension |
    | requestsMergeCellsBatchRequest | Payload to merge cells |
    | requestsMoveDimensionBatchRequest | Payload to move dimension |
    | requestsRepeatCellsBatchRequest | Payload to repeat cells |
    | requestsSetDataValidationBatchRequest | Payload to set data validation |
    | requestsSortRangeBatchRequest | Payload to sort range |
    | requestsUpdateBordersBatchRequestMandatory | Payload to update borders |
    | requestsUpdateCellsBatchRequest | Payload to update cells |
    | requestsUpdateConditionalFormatRuleBatchRequest | Payload to update conditional format rule |
    | requestsUpdateDimensionPropertiesBatchRequest | Payload to update dimension properties |
    | rangeaddRows | Range to add rows|
    | data | To edit multiple cell data|
    | rangeEditCell | Range to edit cell data|
    | rangeGetCellData | Range to get cell data|
    | sheetName,sheetNameOptional,sheetNameOptional2 | Sheet name to create the sheet|

 6.  Navigate to `{EI_Connector_Home}/` and run the following command.
             `$ mvn clean install -Dskip-tests=false`

>>> Note: In Integration test Thread.sleep() method has been used because of the API allows limited no of API calls. If you want
to change the sleeping time, you can change (sleepingTime) constant value in Integration Test.