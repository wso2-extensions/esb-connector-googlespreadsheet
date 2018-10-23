# Working with Spreadsheet Operation

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration) ]

## Overview

The following operations allow you to carry out Spreadsheet operations. Click an operation name to see details on how to use it.


| Operation | Description |
| ------------- |-------------|
| [createSpreadsheet](#Create-Spreadsheet) | Creates a new spreadsheet |

## Operation details

This section provides details on each of the operations.

#### Create Spreadsheet

This method allows you to creates a new spreadsheet by optionally add sheets to spreadsheet, set the spreadsheet id and sheet properties, and add named ranges.


###### createSpreadsheet

```xml
<googlespreadsheet.createSpreadsheet>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <properties>{$ctx:properties}</properties>
    <sheets>{$ctx:sheets}</sheets>
    <namedRanges>{$ctx:namedRanges}</namedRanges>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.createSpreadsheet>
```


###### Properties

* spreadsheetId [Optional] :- Unique value of the spreadsheet
* properties [Optional] :- Properties of the spreadsheet.
* sheets [Optional] :- List of sheets and their properties that you want to add into the spread sheet. You can add multiple sheets.
* namedRanges [Optional] :- Create names that refer to a single cell or a group of cells on the sheet. Following sample request will create name range with the name "Name" for  the range A1:A6.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the "spreadsheetId" will be included in the response.


###### Sample request


Following is a sample request that can be used for createSpreadsheet operation. With the following request we can specify spreadsheet details such as spreadsheet name("Company"),
sheets details such as sheet name("Employees") as an array. So, the spreadsheet will be created inside [Google Sheets](https://docs.google.com/spreadsheets/u/0/?tgif=d) with the name "Company" and the sheet will be created with the name "Employees".
Here we specify "fields" property to get partial response. As per the following request, only the "spreadsheetId" will be included in the response.


```json
{
    "clientId":"xxxxxxxxxxxxxxxxxxxxxxxn6f2m.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "properties":{
        "title": "Company"
      },
    "sheets":[
        {
          "properties":
          {
            "title": "Employees"
          }
        }
      ],
    "fields": "spreadsheetId"
}
```


###### Sample response


```json
{
    "spreadsheetId": "1bWbo72MAhKgeNDCPcE4Wj3uGgN7K9lW1ckDScZV8b30"
}
```


###### Related Google Spreadsheet API documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets#Spreadsheet


## Sample configuration

Following example illustrates how to connect to Google Spreadsheet with the init operation and createSpreadsheet operation.

1. Create a sample proxy as below :

    ```xml
    <proxy xmlns="http://ws.apache.org/ns/synapse"
           name="createSpreadsheet"
           startOnLoad="true"
           statistics="disable"
           trace="disable"
           transports="http,https">
        <target>
            <inSequence>
                <property expression="json-eval($.clientId)" name="clientId"/>
                <property expression="json-eval($.clientSecret)" name="clientSecret"/>
                <property expression="json-eval($.refreshToken)" name="refreshToken"/>
                <property expression="json-eval($.accessToken)" name="accessToken"/>
                <property expression="json-eval($.apiUrl)" name="apiUrl"/>
                <property expression="json-eval($.properties)" name="properties"/>
                <property expression="json-eval($.sheets)" name="sheets"/>
                <property expression="json-eval($.fields)" name="fields"/>
                <googlespreadsheet.init>
                    <clientId>{$ctx:clientId}</clientId>
                    <clientSecret>{$ctx:clientSecret}</clientSecret>
                    <refreshToken>{$ctx:refreshToken}</refreshToken>
                    <accessToken>{$ctx:accessToken}</accessToken>
                    <apiUrl>{$ctx:apiUrl}</apiUrl>
                </googlespreadsheet.init>
                <googlespreadsheet.createSpreadsheet>
                     <properties>{$ctx:properties}</properties>
                     <sheets>{$ctx:sheets}</sheets>
                     <fields>{$ctx:fields}</fields>
                </googlespreadsheet.createSpreadsheet>
                <respond/>
            </inSequence>
            <outSequence>
                <log/>
                <send/>
            </outSequence>
        </target>
        <description/>
    </proxy>
    ```


2. Create a json file called `createSpreadsheet.json` containing the following json:

    ```json
    {
        "clientId":"<Your_clientId>",
        "clientSecret":"<Your_clientSecret>",
        "refreshToken":"<Your_refreshToken>",
        "accessToken":"<Your_accessToken>",
        "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
        "properties":{
            "title": "Company"
          },
        "sheets":[
            {
              "properties":
              {
                "title": "Employees"
              }
            }
          ]
    }
    ```

3. Replace Your_clientId, Your_clientSecret, Your_refreshToken, Your_accessToken with your values.

4. Execute the following cURL command:

    ```curl
    curl http://localhost:8280/services/createSpreadsheet -H "Content-Type: application/json" -d @createSpreadsheet.json
    ```

5. Spreadsheet will returns an json response as below :

    ```json
    {
      "spreadsheetId": "16mEgWpzuoXczkLUA4PDDjGHzVmAI3D_QVQ9kguv2EC8",
      "properties": {
        "title": "Company",
        "locale": "en_US",
        "autoRecalc": "ON_CHANGE",
        "timeZone": "Etc/GMT",
        "defaultFormat": {
          "backgroundColor": {
            "red": 1,
            "green": 1,
            "blue": 1
          },
          "padding": {
            "top": 2,
            "right": 3,
            "bottom": 2,
            "left": 3
          },
          "verticalAlignment": "BOTTOM",
          "wrapStrategy": "OVERFLOW_CELL",
          "textFormat": {
            "foregroundColor": {},
            "fontFamily": "arial,sans,sans-serif",
            "fontSize": 10,
            "bold": false,
            "italic": false,
            "strikethrough": false,
            "underline": false
          }
        }
      },
      "sheets": [
        {
          "properties": {
            "sheetId": 67204741,
            "title": "Employees",
            "index": 0,
            "sheetType": "GRID",
            "gridProperties": {
              "rowCount": 1000,
              "columnCount": 26
            }
          }
        }
      ],
      "spreadsheetUrl": "https://docs.google.com/spreadsheets/d/16mEgWpzuoXczkLUA4PDDjGHzVmAI3D_QVQ9kguv2EC8/edit"
    }
    ```