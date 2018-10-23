# Working with Sheet Data Operations

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration) ]


## Overview

The following operations allow you to carry out spreadsheet operations. Click an operation name to see details on how to use it.



| Operation | Description |
| ------------- |-------------|
| [addRowsColumnsData](#Add-Rows-or-Columns-Data) | Append row/rows/column/columns of data |
| [deleteDimensionBatchRequest](#Delete-Dimension-Using-BatchRequest) | removing rows, columns and remove part of a row or column |
| [getCellData](#Get-Cell-Data) | Retrieve any set of cell data from a sheet and cell contents as input values (as would be entered by a user at a keyboard) and/or the outputs of formula (if numeric) |
| [getMultipleCellData](#Get-Multiple-Cell-Data) | Retrieve any set of cell data including multiple ranges from a sheet and cell contents as input values (as would be entered by a user at a keyboard) and/or the outputs of formula (if numeric) |
| [editCell](#Edit-Cell) | Edit the content of the cell with new values |
| [editMultipleCell](#Edit-Multiple-Cell) | Edit the content of multiple cell with new values |
| [updateCellsBatchRequest](#Update-Cells-Using-BatchRequest) | Updates all cells in a range with new data that can be cell value, formatting |
| [appendDimensionBatchRequest](#Append-Dimension-Using-BatchRequest) | Append empty rows or columns at the end of the sheet |
| [updateBordersBatchRequest](#Update-Borders-Using-BatchRequest) | Edit cell borders |
| [repeatCellsBatchRequest](#Repeat-Cells-Using-Batch-Request) | Repeat formatting of the cell into over a range of cells |
| [mergeCellsBatchRequest](#Merge-Cells-Using-Batch-Request) | Merge range of cells into a one cell |
| [setDataValidationBatchRequest](#Set-DataValidation-Using-Batch-Request) | Apply data validation rule to a range |
| [copyPasteBatchRequest](#Copy-Paste-Using-Batch-Request) | Copy cell formatting in one range and paste it into another range on the same sheet |
| [cutPasteBatchRequest](#Cut-Paste-Using-Batch-Request) | cuts the one range and pastes its data, formats, formulas, and merges to the another range on the same sheet |
| [updateConditionalFormatRuleBatchRequest](#Update-Conditional-FormatRule-Using-Batch-Request) | Update a conditional formatting rule or its priority |
| [addConditionalFormatRuleBatchRequest](#Add-Conditional-Format-Rule-Using-Batch-Request) | Establishes a new conditional formatting rule |
| [deleteConditionalFormatRuleBatchRequest](#Delete-Conditional-Format-Rule-Using-Batch-Request) | Delete a conditional formatting rule |
| [updateDimensionPropertiesBatchRequest](#Update-Dimension-Properties-Using-BatchRequest) | Adjust column width or row height |
| [autoResizeDimensionsBatchRequest](#Auto-Resize-Dimensions-Using-BatchRequest) | Automatically resize a column |
| [insertDimensionBatchRequest](#Insert-Dimension-Using-BatchRequest) | Insert an empty row or column at the end or in the middle |
| [moveDimensionBatchRequest](#Move-Dimension-Using-BatchRequest) | Move a row or column / range of rows or columns |
| [sortRangeBatchRequest](#Sort-Range-Using-BatchRequest) | Sort a range with multiple sorting specifications |


## Operation details

This section provides details on each of the operations.


#### Add Rows or Columns Data

This method allows you to add a new rows or columns of data to a sheet.


###### addRowsColumnsData

```xml
<googlespreadsheet.addRowsColumnsData>
     <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
     <range>{$ctx:range}</range>
     <insertDataOption>{$ctx:insertDataOption}</insertDataOption>
     <valueInputOption>{$ctx:valueInputOption}</valueInputOption>
     <fields>{$ctx:fields}</fields>
     <majorDimension>{$ctx:majorDimension}</majorDimension>
     <values>{$ctx:values}</values>
</googlespreadsheet.addRowsColumnsData>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* range :- The [A1 notation](https://developers.google.com/sheets/api/guides/concepts#a1_notation) of the values to retrieve.
* insertDataOption [Optional] :- How the input data should be inserted. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/spreadsheets.values/append#insertdataoption).
* valueInputOption :- How the input data should be interpreted. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/ValueInputOption).
* majorDimension [Optional] :- The major dimension that results should use. For more detail [click here](https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values#Dimension).
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `updates` will be included in the response.
* values [Optional] :- The data that was to be written. For more detail [click here](https://developers.google.com/protocol-buffers/docs/reference/google.protobuf#listvalue).


###### Sample request

The following request appends data in row major fashion. The range is used to search for existing data and find a "table" within that range. Values will be appended to the next row of the table, starting with the first column of the table.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CxxxxxxxxxxxxxxxxxxxxxxxxxxdrXFGA",
    "range":"Sheet1!A1:B2",
    "insertDataOption":"INSERT_ROWS",
    "majorDimension":"ROWS",
    "valueInputOption":"RAW",
    "values":[
          [
               "20",
               "21"
           ],
           [
               "22",
              "23"
           ]
      ]
}
```


###### Sample Response

The response include the updates details.

```json
{
    "spreadsheetId": "12KoqoxmykLLYbtsm6CxxxxxxxxxxxxxxxxxxxxxxxxxxdrXFGA",
    "updates": {
        "spreadsheetId": "12KoqoxmykLLYbtsm6CxxxxxxxxxxxxxxxxxxxxxxxxxxdrXFGA",
        "updatedRange": "Sheet1!A1:B2",
        "updatedRows": 2,
        "updatedColumns": 2,
        "updatedCells": 4
    }
}
```


###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values/append


#### Delete Dimension Using BatchRequest

This method allows you to delete rows or columns by specifying the dimension.


###### deleteDimensionBatchRequest

```xml
<googlespreadsheet.deleteDimensionBatchRequest>
     <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
     <requests>{$ctx:requests}</requests>
     <fields>{$ctx:fields}</fields>
</googlespreadsheet.deleteDimensionBatchRequest>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple delete operation within the spreadsheet, need to repeat `deleteDimension` property within the requests property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.


###### Sample request

The following request deletes the first three rows in the sheet since we specify `dimension` as `ROWS`.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"ry_AXMsEe5Sn9iVoOY7ATnb8",
    "refreshToken":"1/xxxxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
      {
       "deleteDimension": {
        "range": {
         "sheetId": 121832844,
         "dimension": "ROWS",
         "startIndex": 0,
         "endIndex": 3
        }
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#DeleteDimensionRequest


#### Get Cell Data

Allow you to retrieve any set of cell data from a sheet. It return cell contents not only as input values (as would be entered by a user at a keyboard) but also it grants full access to values, formulas, formatting, hyperlinks, data validation, and other properties.


###### getCellData

```xml
<googlespreadsheet.getCellData>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <range>{$ctx:range}</range>
    <dateTimeRenderOption>{$ctx:dateTimeRenderOption}</dateTimeRenderOption>
    <majorDimension>{$ctx:majorDimension}</majorDimension>
    <valueRenderOption>{$ctx:valueRenderOption}</valueRenderOption>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.getCellData>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* range :- The [A1 notation](https://developers.google.com/sheets/api/guides/concepts#a1_notation) of the values to retrieve.
* dateTimeRenderOption  [Optional] :- How dates, times, and durations should be represented in the output. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/DateTimeRenderOption).
* majorDimension  [Optional] :- The major dimension that results should use. For more detail [click here](https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values#Dimension).
* valueRenderOption [Optional] :- How values should be represented in the output. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/ValueRenderOption).
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `values` will be included in the response.


###### Sample request

The following returns the cells data in the range A1:E14 of sheet `Sheet1` in row-major order.

```json
{
	"clientId":"617729022812-xxxxxxx.apps.googleusercontent.com",
	"clientSecret":"xxxxxxxxxxxxxx",
	"refreshToken":"1/xxxxxxxxxxxx-x-LpK6fDWF9DgcM",
	"accessToken":"ya29.xxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
	"apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
	"spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
	"range":"Sheet1!A1:E14",
	"dateTimeRenderOption":"SERIAL_NUMBER",
	"majorDimension":"ROWS",
	"valueRenderOption":"UNFORMATTED_VALUE"
}
```


###### Sample Response

In the response cell values in the rage A1:E14 will be return.

```json
{
    "range": "Sheet1!A1:E14",
    "majorDimension": "ROWS",
    "values": [
        [
            "20",
            "21"
        ],
        [
            "22",
            "23"
        ]
    ]
}
```


###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values/get


#### Get Multiple Cell Data

Allow you to retrieve any set of cell data from a sheet (including multiple ranges). It return cell contents not only as input values (as would be entered by a user at a keyboard) but also it grants full access to values, formulas, formatting, hyperlinks, data validation, and other properties.


###### getMultipleCellData

```xml
<googlespreadsheet.getMultipleCellData>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <ranges>{$ctx:ranges}</ranges>
    <dateTimeRenderOption>{$ctx:dateTimeRenderOption}</dateTimeRenderOption>
    <majorDimension>{$ctx:majorDimension}</majorDimension>
    <valueRenderOption>{$ctx:valueRenderOption}</valueRenderOption>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.getMultipleCellData>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* ranges  [Optional] :- The [ranges](https://developers.google.com/sheets/api/guides/concepts#a1_notation) of the values to retrieve from the spreadsheet.
* dateTimeRenderOption  [Optional] :- How dates, times, and durations should be represented in the output. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/DateTimeRenderOption).
* majorDimension  [Optional] :- The major dimension that results should use. For more detail [click here](https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values#Dimension).
* valueRenderOption [Optional] :- How values should be represented in the output. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/ValueRenderOption).
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `valueRanges` will be included in the response.


###### Sample request

This will allow you to get cell data by specifying multiple cell range using `ranges` parameter. Here we can specify multiple cell ranges as a comma sperated.

```json
{
	"clientId":"617729022812-xxxxxxxxxxxx.apps.googleusercontent.com",
	"clientSecret":"xxxxxxxxxxxxxxxxxxxxxx",
	"refreshToken":"1/xxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
	"accessToken":"ya29.xxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
	"apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
	"spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
	"ranges":"Sheet1!A1:B2,Sheet1!B1:C2,Sheet1!D4:G5",
	"dateTimeRenderOption":"SERIAL_NUMBER",
	"majorDimension":"ROWS",
	"valueRenderOption":"UNFORMATTED_VALUE"
}
```


###### Sample Response

In the response we will get all cell data that is in the specified cell ranges.

```json
{
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "valueRanges": [
        {
            "range": "Sheet1!A1:B2",
            "majorDimension": "ROWS",
            "values": [
                [
                    "20",
                    "21"
                ],
                [
                    "22",
                    "23"
                ]
            ]
        },
        {
            "range": "Sheet1!B1:C2",
            "majorDimension": "ROWS",
            "values": [
                [
                    "21",
                    34
                ],
                [
                    "23",
                    47
                ]
            ]
        },
        {
            "range": "Sheet1!D4:G5",
            "majorDimension": "ROWS"
        }
    ]
}
```


###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values/batchGet


#### Edit Cell

Edit the content of the cell with new values.


###### editCell

```xml
<googlespreadsheet.editCell>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <range>{$ctx:range}</range>
    <valueInputOption>{$ctx:valueInputOption}</valueInputOption>
    <fields>{$ctx:fields}</fields>
    <majorDimension>{$ctx:majorDimension}</majorDimension>
    <values>{$ctx:values}</values>
</googlespreadsheet.editCell>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* range :- The [A1 notation](https://developers.google.com/sheets/api/guides/concepts#a1_notation) of the values to retrieve.
* valueInputOption :- How the input data should be interpreted. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/ValueInputOption).
* majorDimension [Optional] :- The major dimension that results should use. For more detail [click here](https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values#Dimension).
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `updatedRange` will be included in the response.
* values [Optional] :- The data that was to be written.  For more detail [click here](https://developers.google.com/protocol-buffers/docs/reference/google.protobuf#listvalue).


###### Sample request

In the request we can specify which sheet.

```json
{
	"clientId":"617729022812-xxxxxxxxxxxxxx.apps.googleusercontent.com",
	"clientSecret":"xxxxxxxxxxxxxxxxxx",
	"refreshToken":"1/Si2q4aOZsaMlYW7bBIoO-fCyxRTyf-xxxxxxxxxxxxx",
	"accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
	"apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
	"spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
	"range":"Sheet1!A1:E3",
    "majorDimension":"ROWS",
    "valueInputOption":"RAW",
    "values":[
      [
           "1111",
           "2222"
      ],
      [
           "3333",
           "4444"
      ]
     ]
}
```


###### Sample Response

In the response we will get updated details such as cell dimension, cell count, sheet range.

```json
{
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "updatedRange": "Sheet1!A1:B2",
    "updatedRows": 2,
    "updatedColumns": 2,
    "updatedCells": 4
}
```


###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values/update



#### Edit Multiple Cell

Edit the content of multiple cell with new values


###### editMultipleCell

```xml
<googlespreadsheet.editMultipleCell>
     <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
     <data>{$ctx:data}</data>
     <valueInputOption>{$ctx:valueInputOption}</valueInputOption>
     <fields>{$ctx:fields}</fields>
</googlespreadsheet.editMultipleCell>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* data [Optional] :- The new values to apply to the spreadsheet.
* valueInputOption :- How the input data should be interpreted. For more detail [click here](https://developers.google.com/sheets/api/reference/rest/v4/ValueInputOption).
* fields [Optional] :- Specifying which fields to include in a partial response.


###### Sample request

Edit the content of multiple cell ranges with new values. We can specify multiple cell ranges and values as JSON array in `data`.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxx",
    "refreshToken":"1/Si2q4aOZsaMlYW7bBIoO-xxxxxxxxxxxxx-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "valueInputOption":"RAW",
    "data": [
          {
            "values": [["7","8"],["9","10"]],
            "range": "Sheet1!A6"
       }
    ]
}
```


###### Sample Response

In the response we will get updated cell, range details as as array in `responses` property.

```json
{
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "totalUpdatedRows": 2,
    "totalUpdatedColumns": 2,
    "totalUpdatedCells": 4,
    "totalUpdatedSheets": 1,
    "responses": [
        {
            "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
            "updatedRange": "Sheet1!A6:B7",
            "updatedRows": 2,
            "updatedColumns": 2,
            "updatedCells": 4
        }
    ]
}
```


###### Related Google Spreadsheet documentation

https://developers.google.com/sheets/reference/rest/v4/spreadsheets.values/batchUpdate


#### Update Cells Using BatchRequest

This method allows you to removes all values from a sheet while leaving any formatting unaltered. Specifying userEnteredValue in fields(within the requests property) without providing a corresponding value is interpreted as an instruction to clear values in the range. This can be used with other fields as well. For example, changing the fields(within the requests property) value to userEnteredFormat and making the request clears the sheet of all formatting, but leaves the cell values untouched.


###### updateCellsBatchRequest

```xml
<googlespreadsheet.updateCellsBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.updateCellsBatchRequest>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple updateCells operation within the spread sheet, need to repeat `updateCells` property within the `requests` property.
* fields (Outside the requests property) [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.


###### Sample request

```json
{
    "clientId":"617729022812-cxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"ry_AXMsEe5Sn9iVoOY7ATnb8",
    "refreshToken":"1/xxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
         {
             "updateCells": {
                 "start": {
                     "columnIndex": 3,
                     "rowIndex": 2,
                     "sheetId": 121832844
                 },
                 "rows": [
                     {
                         "values": [
                             {"userEnteredValue": {"numberValue": 444}},
                             {"userEnteredValue": {"numberValue": 777}}
                         ]
                     }
                 ],
            "fields": "userEnteredValue"
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#updatecellsrequest


#### Append Dimension Using BatchRequest

This method allows you to appends empty rows and columns to the end of the sheet.


###### appendDimensionBatchRequest

```xml
<googlespreadsheet.appendDimensionBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.appendDimensionBatchRequest>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple appendDimension operation within the spread sheet, need to repeat `appendDimension` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.


###### Sample request

This sample requst allow you to append diamention in row wise with the length 2.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
         {
             "appendDimension": {
             "dimension": "ROWS",
             "sheetId": 121832844,
             "length": 2
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#appenddimensionrequest


#### Update Borders Using BatchRequest

This method allow you to edit cell borders.


###### updateBordersBatchRequest

```xml
<googlespreadsheet.updateBordersBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.updateBordersBatchRequest>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple updateBorders operation within the spread sheet, need to repeat `updateBorders` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.


###### Sample request

In following request we can specify for which range of the sheet the border need to be updated and the formatting details of the border.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
        {
          "updateBorders":
          {
            "range": {
              "sheetId": 121832844,
              "startRowIndex": 0,
              "endRowIndex": 10,
              "startColumnIndex": 0,
              "endColumnIndex": 6
            },
            "top": {
              "style": "DASHED",
              "width": 1,
              "color": {"blue": 1}
            },
            "bottom":
            {
              "style": "DASHED",
              "width": 1,
              "color": {"blue": 1}
            },
            "innerHorizontal": {
              "style": "DASHED",
              "width": 1,
              "color": {"blue": 1}
            }
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#updatebordersrequest


#### Repeat Cells Using Batch Request

This method allow you to updates all cells in the range to the values in the given Cell object. Only the fields listed in the fields(within the requests property)will be updated. Others are unchanged.

```xml
<googlespreadsheet.repeatCellsBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.repeatCellsBatchRequest>
```


###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple repeatCell operation within the spread sheet, need to repeat `repeatCell` property within the `requests` property.
* fields (Outside the requests property) [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.


###### Sample request

Here the formating specified in "cell" object will be repeted for row index from 13 to 15.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
    {
      "repeatCell": {
        "range": {
          "sheetId": 121832844,
          "startRowIndex": 13,
          "endRowIndex": 15
        },
        "cell": {
          "userEnteredFormat": {
            "backgroundColor": {
              "red": 0.0,
              "green": 0.0,
              "blue": 0.0
            }
          }
        },
        "fields": "userEnteredFormat(backgroundColor)"
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#repeatcellrequest



#### Merge Cells Using Batch Request

This method allow you to merges all cells in the range.



###### mergeCellsBatchRequest

```xml
<googlespreadsheet.mergeCellsBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.mergeCellsBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple mergeCells operation within the spread sheet, need to repeat `mergeCells` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response.For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

```json
{
    "clientId":"617729022812-xxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
    {
      "mergeCells": {
        "range": {
          "sheetId": 121832844,
          "startRowIndex": 0,
          "endRowIndex": 2,
          "startColumnIndex": 0,
          "endColumnIndex": 2
        },
        "mergeType": "MERGE_ALL"
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#mergecellsrequest



#### Set DataValidation Using Batch Request

Sets a data validation rule to every cell in the range. To clear validation in a range, call this with no rule specified.



###### setDataValidationBatchRequest

```xml
<googlespreadsheet.setDataValidationBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.setDataValidationBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple setDataValidation operation within the spread sheet, need to repeat `setDataValidation` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "refreshToken":"1/xx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
    {
      "mergeCells": {
        "range": {
          "sheetId": 121832844,
          "startRowIndex": 0,
          "endRowIndex": 2,
          "startColumnIndex": 0,
          "endColumnIndex": 2
        },
        "mergeType": "MERGE_ALL"
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#setdatavalidationrequest



#### Copy Paste Using Batch Request

Copies data from the source to the destination.



###### copyPasteBatchRequest

```xml
<googlespreadsheet.copyPasteBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.copyPasteBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple copyPaste operation within the spread sheet, need to repeat `copyPaste` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request copies the formatting in range A1:D10 and pastes it to the F1:I10 range on the same sheet. The original values in A1:I10 remain unchanged.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"ry_AXMsEe5Sn9iVoOY7ATnb8",
    "refreshToken":"1/xxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.xxxxxxxxxxx-pOuVvnbnHhkVn5u8t6Qr",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
    "requests": [
    {
      "copyPaste": {
        "source": {
          "sheetId": 121832844,
          "startRowIndex": 0,
          "endRowIndex": 10,
          "startColumnIndex": 0,
          "endColumnIndex": 4
        },
        "destination": {
          "sheetId": 121832844,
          "startRowIndex": 0,
          "endRowIndex": 10,
          "startColumnIndex": 5,
          "endColumnIndex": 9
        },
        "pasteType": "PASTE_FORMAT",
        "pasteOrientation": "NORMAL"
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#copypasterequest



#### Cut Paste Using Batch Request

Moves data from the source to the destination.



###### cutPasteBatchRequest

```xml
<googlespreadsheet.cutPasteBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.cutPasteBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple cutPaste operation within the spread sheet, need to repeat `cutPaste` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request cuts the range A1:D10 and pastes its data, formats, formulas, and merges to the F1:I10 range on the same sheet. The original source range cell contents are removed.

```json
{
    "clientId":"617729022812-xxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-xxxxxxxxxxxxx",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests": [
    {
      "cutPaste": {
        "source": {
          "sheetId": 1020069232,
          "startRowIndex": 0,
          "endRowIndex": 10,
          "startColumnIndex": 0,
          "endColumnIndex": 4
        },
        "destination": {
          "sheetId": 401088778,
          "rowIndex": 0,
          "columnIndex": 5
        },
        "pasteType": "PASTE_NORMAL"
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#cutpasterequest



#### Update Conditional FormatRule Using Batch Request

Updates a conditional format rule at the given index, or moves a conditional format rule to another index.



###### updateConditionalFormatRuleBatchRequest

```xml
<googlespreadsheet.updateConditionalFormatRuleBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.updateConditionalFormatRuleBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple updateConditionalFormatRule operation within the spread sheet, need to repeat `updateConditionalFormatRule` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request replaces the conditional formatting rule at index 0 with a new rule that formats cells containing the exact text specified ("Total Cost") in the A1:D5 range.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-xxxxxxxxxxxxxxxxxxxxxx-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests": [
    {
      "updateConditionalFormatRule": {
        "sheetId": 1020069232,
        "index": 0,
        "rule": {
          "ranges": [
            {
              "sheetId": 1020069232,
              "startRowIndex": 0,
              "endRowIndex": 5,
              "startColumnIndex": 0,
              "endColumnIndex": 4
            }
          ],
          "booleanRule": {
            "condition": {
              "type": "TEXT_EQ",
              "values": [
                {
                  "userEnteredValue": "Total Cost"
                }
              ]
            },
            "format": {
              "textFormat": {
                "bold": true
              }
            }
          }
        }
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#updateconditionalformatrulerequest



#### Add Conditional Format Rule Using Batch Request

Adds a new conditional format rule at the given index.



###### addConditionalFormatRuleBatchRequest

```xml
<googlespreadsheet.addConditionalFormatRuleBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.addConditionalFormatRuleBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple addConditionalFormatRule operation within the spread sheet, need to repeat `addConditionalFormatRule` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request establishes new gradient conditional formatting rules for row 10 and 11 of a sheet. The first rule states that cells in that row have their backgrounds colored according to their value. The lowest value in the row will be colored dark red, while the highest value will be colored bright green. The color of other values will be determined by interpolation.

```json
{
    "clientId":"617729022812-vjo2edd0i4bcb38ifu4qg17ke5nn6f2m.apps.googleusercontent.com",
    "clientSecret":"ry_AXMsEe5Sn9iVoOY7ATnb8",
    "refreshToken":"1/Si2q4aOZsaMlYW7bBIoO-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-CA9sR2IXoOaVg9fpRwf8fEhF8lqfOJL1FpRihUlNxEa8kw-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests": [
    {
      "addConditionalFormatRule": {
        "rule": {
          "ranges": [
            {
              "sheetId": 1020069232,
              "startRowIndex": 10,
              "endRowIndex": 11
            }
          ],
          "gradientRule": {
            "minpoint": {
              "color": {
                "green": 0.2,
                "red": 0.8
              },
              "type": "MIN"
            },
            "maxpoint": {
              "color": {
                "green": 0.9
              },
              "type": "MAX"
            }
          }
        },
        "index": 0
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#addconditionalformatrulerequest



#### Delete Conditional Format Rule Using Batch Request

Deletes a conditional format rule at the given index.



###### deleteConditionalFormatRuleBatchRequest

```xml
<googlespreadsheet.deleteConditionalFormatRuleBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.deleteConditionalFormatRuleBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple deleteConditionalFormatRule operation within the spread sheet, need to repeat `deleteConditionalFormatRule` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request deletes the conditional formatting rule having index 0 in the sheet specified by sheetId.

```json
{
    "clientId":"617729022812-vjo2edd0i4bcb38ifu4qg17ke5nn6f2m.apps.googleusercontent.com",
    "clientSecret":"ry_AXMsEe5Sn9iVoOY7ATnb8",
    "refreshToken":"1/Si2q4aOZsaMlYW7bBIoO-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-CA9sR2IXoOaVg9fpRwf8fEhF8lqfOJL1FpRihUlNxEa8kw-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests": [
    {
      "deleteConditionalFormatRule": {
        "sheetId": 1020069232,
        "index": 0
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#DeleteConditionalFormatRuleRequest



#### Update Dimension Properties Using BatchRequest

Updates properties of dimensions within the specified range.



###### updateDimensionPropertiesBatchRequest

```xml
<googlespreadsheet.updateDimensionPropertiesBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.updateDimensionPropertiesBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple updateDimensionProperties operation within the spreadsheet, need to repeat `updateDimensionProperties` property within the `requests` property.
* fields (Outside the requests property) [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request updates the width of column A to 160 pixels.

```json
{
    "clientId":"617729022812-vjo2edd0i4bcb38ifu4qg17ke5nn6f2m.apps.googleusercontent.com",
    "clientSecret":"ry_AXMsEe5Sn9iVoOY7ATnb8",
    "refreshToken":"1/Si2q4aOZsaMlYW7bBIoO-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-CA9sR2IXoOaVg9fpRwf8fEhF8lqfOJL1FpRihUlNxEa8kw-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests": [
    {
      "updateDimensionProperties": {
        "range": {
          "sheetId": 1020069232,
          "dimension": "COLUMNS",
          "startIndex": 0,
          "endIndex": 1
        },
        "properties": {
          "pixelSize": 160
        },
        "fields": "pixelSize"
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#updatedimensionpropertiesrequest



#### Auto Resize Dimensions Using BatchRequest

Automatically resizes one or more dimensions based on the contents of the cells in that dimension.



###### autoResizeDimensionsBatchRequest

```xml
<googlespreadsheet.autoResizeDimensionsBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.autoResizeDimensionsBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple autoResizeDimensions operation within the spread sheet, need to repeat `autoResizeDimensions` property within the `requests` property.
    * fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request turns on automatic resizing of columns A:C, based on the size of the column content. Automatic resizing of rows is not supported.

```json
{
    "clientId":"617729022812-xxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-xxxxxxxxxxx-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests": [
    {
      "autoResizeDimensions": {
        "dimensions": {
          "sheetId": 1020069232,
          "dimension": "COLUMNS",
          "startIndex": 0,
          "endIndex": 3
        }
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#autoresizedimensionsrequest



#### Insert Dimension Using BatchRequest

Inserts rows or columns in a sheet at a particular index.



###### insertDimensionBatchRequest

```xml
<googlespreadsheet.insertDimensionBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.insertDimensionBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple insertDimension operation within the spread sheet, need to repeat `insertDimension` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request inserts two blank columns at column C. The inheritBefore field, if true, tells the API to give the new columns or rows the same properties as the prior row or column; otherwise the new columns or rows acquire the properties of those that follow them. inheritBefore cannot be true if inserting a row at row 1 or a column at column A.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxx",
    "refreshToken":"1/Si2q4aOZsaMlYW7bBIxxxxxxxxxxxxxxoO-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-xxxxxxxxxxxxxxxxxx-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests":[
    {
      "insertDimension":
      {
        "range":
        {
          "sheetId": 1020069232,
          "dimension": "COLUMNS",
          "startIndex": 2,
          "endIndex": 4
        },
        "inheritFromBefore": true
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#insertdimensionrequest



#### Move Dimension Using BatchRequest

Moves one or more rows or columns.



###### moveDimensionBatchRequest

```xml
<googlespreadsheet.moveDimensionBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.moveDimensionBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple moveDimension operation within the spread sheet, need to repeat `moveDimension` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request moves column A to the column D position.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-xxxxxxxxxxxx-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests":[
    {
      "moveDimension":
      {
        "source":
        {
          "sheetId": 1020069232,
          "dimension": "COLUMNS",
          "startIndex": 0,
          "endIndex": 1
        },
        "destinationIndex": 3
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#movedimensionrequest



#### Sort Range Using BatchRequest

Sorts data in rows based on a sort order per column.



###### sortRangeBatchRequest

```xml
<googlespreadsheet.sortRangeBatchRequest>
    <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
    <requests>{$ctx:requests}</requests>
    <fields>{$ctx:fields}</fields>
</googlespreadsheet.sortRangeBatchRequest>
```



###### Properties

* spreadsheetId :- Unique value of the spreadsheet
* requests :- It contains data that is a kind of update to apply to a spreadsheet. To perform multiple sortRange operation within the spread sheet, need to repeat `sortRange` property within the `requests` property.
* fields [Optional] :- Specifying which fields to include in a partial response. For the following request only the `spreadsheetId` will be included in the response.



###### Sample request

The following request sorts the range A1:F10, first by column B in ascending order, then by column D in descending order, then by column E in descending order.

```json
{
    "clientId":"617729022812-xxxxxxxxxxxxxx.apps.googleusercontent.com",
    "clientSecret":"xxxxxxxxxxxx",
    "refreshToken":"1/xxxxxxxxxxxxx-fCyxRTyf-LpK6fDWF9DgcM",
    "accessToken":"ya29.Ci-xxxxxxxxxxx-kQ9Wri4bsf4TEulw",
    "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
    "spreadsheetId": "14PJALKcIXLr75rJWXlHhVjOt7z0Nby7AvcKXJGhMN2s",
    "requests": [
    {
      "sortRange": {
        "range": {
          "sheetId": 1020069232,
          "startRowIndex": 0,
          "endRowIndex": 10,
          "startColumnIndex": 0,
          "endColumnIndex": 6
        },
        "sortSpecs": [
          {
            "dimensionIndex": 1,
            "sortOrder": "ASCENDING"
          },
          {
            "dimensionIndex": 3,
            "sortOrder": "DESCENDING"
          },
          {
            "dimensionIndex": 4,
            "sortOrder": "DESCENDING"
          }
        ]
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

https://developers.google.com/sheets/reference/rest/v4/spreadsheets/request#sortrangerequest



## Sample configuration

Following example illustrates how to connect to Google Spreadsheet with the init operation and addRowsColumnsData operation.

1. Create a sample proxy as below :

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <proxy xmlns="http://ws.apache.org/ns/synapse"
           name="addRowsColumnsData"
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
             <property expression="json-eval($.range)" name="range"/>
             <property expression="json-eval($.insertDataOption)" name="insertDataOption"/>
             <property expression="json-eval($.valueInputOption)" name="valueInputOption"/>
             <property expression="json-eval($.fields)" name="fields"/>
             <property expression="json-eval($.majorDimension)" name="majorDimension"/>
             <property expression="json-eval($.values)" name="values"/>
             <googlespreadsheet.init>
                <clientId>{$ctx:clientId}</clientId>
                <clientSecret>{$ctx:clientSecret}</clientSecret>
                <refreshToken>{$ctx:refreshToken}</refreshToken>
                <accessToken>{$ctx:accessToken}</accessToken>
                <apiUrl>{$ctx:apiUrl}</apiUrl>
             </googlespreadsheet.init>
             <googlespreadsheet.addRowsColumnsData>
                <spreadsheetId>{$ctx:spreadsheetId}</spreadsheetId>
                <range>{$ctx:range}</range>
                <insertDataOption>{$ctx:insertDataOption}</insertDataOption>
                <valueInputOption>{$ctx:valueInputOption}</valueInputOption>
                <fields>{$ctx:fields}</fields>
                <majorDimension>{$ctx:majorDimension}</majorDimension>
                <values>{$ctx:values}</values>
             </googlespreadsheet.addRowsColumnsData>
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

2. Create a json file called addRowsColumnsDataRequest.json containing the following json:

    ```json
    {
        "clientId": "<Your_clientId>",
        "clientSecret": "<Your_clientSecret>",
        "refreshToken": "<Your_refreshToken>",
        "accessToken": "<Your_accessToken>",
        "apiUrl":"https://sheets.googleapis.com/v4/spreadsheets",
        "spreadsheetId": "<spreadsheet_Id>",
        "range":"<Sheet_name>!A1:B2",
        "insertDataOption":"INSERT_ROWS",
        "majorDimension":"ROWS",
        "valueInputOption":"RAW",
        "values":[["20", "21"],["22","23"]]
    }
    ```

3. Replace Your_clientId, Your_clientSecret, Your_refreshToken, Your_accessToken, spreadsheet_Id, Sheet_name with your values.

4. Execute the following cURL command:
    `curl http://localhost:8280/services/addRowsColumnsData -H "Content-Type: application/json" -d @addRowsColumnsDataRequest.json`

5. Spreadsheet will returns an json response as below :

    ```json
    {
      "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
      "tableRange": "Sheet1!A1:C2",
      "updates": {
        "spreadsheetId": "12KoqoxmykLLYbtsm6CEOggk5bTKMEIFGCD9EBdrXFGA",
        "updatedRange": "Sheet1!A3:B4",
        "updatedRows": 2,
        "updatedColumns": 2,
        "updatedCells": 4
      }
    }
    ```