# Working with Sheet Operations

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration) ]


## Overview

The following operations allow you to carry out sheet operations. Click an operation name to see details on how to use it.



| Operation | Description |
| ------------- |-------------|
| [addSheetBatchRequest](#Add-Sheet-Using-BatchRequest) | Add a sheet or multiple sheets to a spreadsheet |
| [deleteSheetBatchRequest](#Delete-Sheet-Using-BatchRequest) | Remove a sheet or multiple sheets from a given spreadsheet |
| [getSheetMetaData](#Retrieve-Sheet-metadata) | Provides the sheet metadata within a given spreadsheet |
| [updateSheetPropertiesBatchRequest](#Update-Sheet-Properties-Using-BatchRequest) | Update all sheet properties |
| [copyTo](#Copy-Sheet-Data) | Copy a single sheet from a spreadsheet to another spreadsheet |


## Operation details

This section provides details on each of the operations.



#### Add Sheet Using BatchRequest

This method allow you to add new sheets to an existing spreadsheet. You can specify the sheet properties for the new sheet. It is an error to provide a title that is used for an existing sheet.



###### addSheetBatchRequest

```xml
<googlespreadsheet.addSheetBatchRequest>
     <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
     <requests>{$ctx:requests}</requests>
     <fields>{$ctx:fields}</fields>
</googlespreadsheet.addSheetBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet.
* requests :- The spreadsheet is updated with the data that is sent in the request. To add multiple sheets within the spread sheet, need to repeat `addSheetBatchRequest` property within the `requests` attribute as below in the sample request.
* fields [Optional] :- Specify the fields that need to be included in a partial response. In the sample request given below, only the `spreadsheetId` will be included in the response.



###### Sample request

The sample request given below calls the `addSheetBatchRequest` operation. The request specifies the multiple sheet properties, such as the sheet name ("Expenses1", "Expenses2"), sheet type ("GRID"), and the dimension ((50,10), (70,10)) of the sheet as an array. The fields property is specified to get a partial response. The `spreadsheetId` and `replies` values will be included in the response. `replies` contains properties such as sheet name, type, row, column count, and sheetId.

```json
{
    "clientId":"617729022812-xxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A",
    "requests": [
      {
          "addSheet": {
              "properties": {
                "title": "Expenses1",
                "sheetType": "GRID",
                "gridProperties": {
                  "rowCount": 50,
                  "columnCount": 10
                }
              }
          }
      },
      {
          "addSheet": {
              "properties": {
                "title": "Expenses2",
                "sheetType": "GRID",
                "gridProperties": {
                  "rowCount": 70,
                  "columnCount": 10
                }
              }
          }
      }
    ],
      "fields": "spreadsheetId,replies"
}
```



###### Sample Response

```json
{
    "spreadsheetId": "1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A",
    "replies": [
        {
            "addSheet": {
                "properties": {
                    "sheetId": 372552230,
                    "title": "Expenses1",
                    "index": 1,
                    "sheetType": "GRID",
                    "gridProperties": {
                        "rowCount": 50,
                        "columnCount": 10
                    }
                }
            }
        },
        {
            "addSheet": {
                "properties": {
                    "sheetId": 568417391,
                    "title": "Expenses2",
                    "index": 2,
                    "sheetType": "GRID",
                    "gridProperties": {
                        "rowCount": 70,
                        "columnCount": 10
                    }
                }
            }
        }
    ]
}
```



###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#AddSheetRequest



#### Delete Sheet Using BatchRequest

This method allows you to remove sheets from a given spreadsheet using `sheetId`. You can get the `sheetId` using the `getSheetMetaData` method.



###### deleteSheetBatchRequest

```xml
<googlespreadsheet.deleteSheetBatchRequest>
     <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
     <requests>{$ctx:requests}</requests>
     <fields>{$ctx:fields}</fields>
</googlespreadsheet.deleteSheetBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To delete multiple sheets within the spread sheet, need to repeat `deleteSheet` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following sample request calls the deleteSheetBatchRequest operation. Specify the Id of the sheet that needs to be deleted.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
      {
          "deleteSheet":
          {
            "sheetId": 813171540
          }
        }
     ],
      "fields": "spreadsheetId"
}
```



###### Sample Response

```json
{
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA"
}
```



###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#deletesheetrequest



#### Retrieve Sheet metadata

This method can be used to acquire sheet properties and other metadata. If you only want to read the sheet properties, set the includeGridData query parameter to false to prevent the inclusion of the spreadsheet cell data. The Spreadsheet response contains an array of Sheet objects. The sheet titles and size information specifically can be found under the SheetProperties element of these objects.



###### getSheetMetaData

```xml
<googlespreadsheet.getSheetMetaData>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <includeGridData>{$ctx:includeGridData}</includeGridData>
    <ranges>{$ctx:ranges}</ranges>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.getSheetMetaData>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* includeGridData [Optional] :- True if grid data should be returned. This parameter is ignored if a field mask was set in the request.
* fields [Optional] :- Specifying which fields to include in a partial response
* ranges [Optional] :- The ranges to retrieve from the spreadsheet



###### Sample request

The following sample request calls the getSheetMetaData operation. Specify the spreadsheet Id, sheet details, and the ranges to retrieve from the spreadsheet. Here the Employees is the sheet name and A1:B2 is the cell range.

```json
{
	"clientId":"617729022812-xxxxxxxxxxxx.apps.googleusercontent.com",
	"clientSecret":"xxxxxxxxxx",
	"refreshToken":"1/xxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
	"accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
	"apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
	"spreadsheetId": "1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A",
	"includeGridData":"false",
	"ranges": "Employees!A1:B2"
}
```



###### Sample Response

In the response we will get the metadata of the spreadsheet such as spreadsheet name, locale,timeZone, spread sheet url and the metadata of the sheets such as sheet Id, sheet name, sheet type.

```json
{
    "spreadsheetId": "1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A",
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
                "sheetId": 789,
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
    "spreadsheetUrl": "https://docs.google.com/spreadsheets/d/1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A/edit"
}
```



###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/get



#### Update Sheet Properties Using BatchRequest

This method allows you to update the size, title, and other sheet properties.



###### updateSheetPropertiesBatchRequest

```xml
<googlespreadsheet.updateSheetPropertiesBatchRequest>
      <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
      <requests>{$ctx:requests}</requests>
      <fields>{$ctx:fields}</fields>
</googlespreadsheet.updateSheetPropertiesBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To Update multiple sheets properties within the spread sheet, need to repeat `updateSheetProperties` property within the `requests` property.
* fields (Outside the requests property) [Optional] :- Specifying which fields to include in a partial response. This is define outside the requests body.



###### Sample request

You need to specify the properties that need to change in the body of the request. The fields parameter within the requests property should explicitly list those properties (if you want to update all properties, use fields:"*" as a shorthand for listing them all). Here we specify that the title, and gridProperties (rowCount,columnCount) parameters need to be updated. For example, the following request specifies that the rowCount and columnCount of the given row and column of sheet `Sheet1` need to be updated.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A",
    "requests": [
      {
       "updateSheetProperties": {
        "properties": {
         "sheetId": 789,
         "gridProperties": {
          "columnCount": 25,
          "rowCount": 10
         },
         "title": "Sheet1"
        },
        "fields": "title,gridProperties(rowCount,columnCount)"
       }
      }
     ],
     "fields": "spreadsheetId"
}
```



###### Sample Response

```json
{
    "spreadsheetId": "1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A"
}
```



###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#UpdateSheetPropertiesRequest



#### Copy Sheet Data

This method allows you to Copy a single sheet from a spreadsheet to another spreadsheet. Returns the properties of the newly created sheet.



###### copyTo

```xml
<googlespreadsheet.copyTo>
     <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
     <sheetId>{$ctx:sheetId}</sheetId>
     <destinationSpreadsheetId>{$ctx:destinationSpreadsheetId}</destinationSpreadsheetId>
     <fields>{$ctx:fields}</fields>
</googlespreadsheet.copyTo>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* sheetId :- The ID of the sheet to copy
* destinationSpreadsheetId :- The ID of the spreadsheet to copy the sheet to
* fields [Optional] :- Specifying which fields to include in a partial response



###### Sample request

Following is a sample request that can be used for copyTo operation. With the following request we can specify source, destination spreadsheet Id and the source sheet Id from where we need to copy.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxx-fCyxRTyf-xxxxxxxxxxx",
    "accessToken":"ya29.xxxxxxxxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "1oGxpE3C_2elS4kcCxxxxxxxxxxxxLamC1CXZOgBzy9A",
    "sheetId":"789",
    "destinationSpreadsheetId":"12KoqoxmykLLYbxxxxxxxxxxxxxxxxxxxxEIFGCD9EBdrXFGA"
}
```



###### Sample Response

In the response, we will get the target sheet details such as sheet Id, sheet name, sheet type and etc.

```json
{
    "sheetId": 813171540,
    "title": "Copy of Sheet1",
    "index": 1,
    "sheetType": "GRID",
    "gridProperties": {
        "rowCount": 10,
        "columnCount": 25
    }
}
```



###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets.sheets/copyTo



## Sample configuration

Following example illustrates how to connect to Google Spreadsheet with the init operation and addSheetBatchRequest operation.

1. Create a sample proxy as below :

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <proxy xmlns="http://ws.apache.org/ns/synapse"
           name="addSheetBatchRequest"
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
             <property expression="json-eval($.spreadsheetId)" name="spreadsheetId"/>
             <property expression="json-eval($.requests)" name="requests"/>
             <property expression="json-eval($.fields)" name="fields"/>
             <googlespreadsheet.init>
                <clientId>{$ctx:clientId}</clientId>
                <clientSecret>{$ctx:clientSecret}</clientSecret>
                <refreshToken>{$ctx:refreshToken}</refreshToken>
                <accessToken>{$ctx:accessToken}</accessToken>
                <apiUrl>{$ctx:apiUrl}</apiUrl>
             </googlespreadsheet.init>
             <googlespreadsheet.addSheetBatchRequest>
                <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
                <requests>{$ctx:requests}</requests>
                <fields>{$ctx:fields}</fields>
             </googlespreadsheet.addSheetBatchRequest>
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

2. Create a json file called `addSheetBatchRequest.json` containing the following json:

    ```json
    {
        "clientId":"<Your_clientId>",
        "clientSecret":"<Your_clientSecret>",
        "refreshToken":"<Your_refreshToken>",
        "accessToken":"<Your_accessToken>",
        "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
        "spreadsheetId": "<spreadsheet_Id>",
        "requests": [
          {
              "addSheet": {
                  "properties": {
                    "title": "<Sheet_name>",
                    "sheetType": "GRID",
                    "gridProperties": {
                      "rowCount": 50,
                      "columnCount": 10
                    }
                  }
              }
          }
        ],
          "fields": "spreadsheetId,replies"
    }
    ```

3. Replace Your_clientId, Your_clientSecret, Your_refreshToken, Your_accessToken, spreadsheet_Id, Sheet_name with your values.

4. Execute the following cURL command:

    ```curl
    curl http://localhost:8280/services/addSheetBatchRequest -H "Content-Type: application/json" -d @addSheetBatchRequest.json
    ```

5. Spreadsheet will returns an json response as below :

    ```json
    {
      "spreadsheetId": "1oGxpE3C_2elS4kcCZaB3JqVMiXCYLamC1CXZOgBzy9A",
      "replies": [
        {
          "addSheet": {
            "properties": {
              "sheetId": 614707436,
              "title": "Expenses",
              "index": 3,
              "sheetType": "GRID",
              "gridProperties": {
                "rowCount": 50,
                "columnCount": 10
              }
            }
          }
        }
      ]
    }
    ```