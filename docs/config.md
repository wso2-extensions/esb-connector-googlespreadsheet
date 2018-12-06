## Configuring Google Spreadsheet Operations

To use the Google Spreadsheet connector, add the `<googlespreadsheet.init>` element to your proxy configuration before using any other Google Spreadsheet connector
operations. The `<googlespreadsheet.init>` element authenticates the user using OAuth2 authentication and it allows users to access the Google account which contains the spreadsheets. For more information on authorizing requests in Google Spreadsheets, see [API Doc](https://developers.google.com/sheets/api/guides/authorizing).

<br/>

> Follow this [guide](https://docs.wso2.com/display/IntegrationCloud/Get+Credentials+for+Google+Spreadsheet) to set up the Google Spreadsheets and get credentials such as clientId, clientSecret, accessToken, refreshToken.

<br/>

**init**
```xml
<googlespreadsheet.init>
    <accessToken>{$ctx:accessToken}</accessToken>
    <clientId>{$ctx:clientId}</clientId>
    <clientSecret>{$ctx:clientSecret}</clientSecret>
    <refreshToken>{$ctx:refreshToken}</refreshToken>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
</googlespreadsheet.init>
```

<br/>

To directly get the OAuth access token, call the **init** method (this method call getAccessTokenFromRefreshToken method itself ) or add  `<googlespreadsheet.getAccessTokenFromRefreshToken>` element before `<googlespreadsheet.init>` element in your configuration.

<br/>

**getAccessTokenFromRefreshToken**
```xml
<googlespreadsheet.getAccessTokenFromRefreshToken>
    <clientId>{$ctx:clientId}</clientId>
    <clientSecret>{$ctx:clientSecret}</clientSecret>
    <refreshToken>{$ctx:refreshToken}</refreshToken>
</googlespreadsheet.getAccessTokenFromRefreshToken>
```

<br/>

>> **Note:** When trying it out for the first time, you need to use a valid accessToken to use the connector operations. If the accessToken has expired then the token refreshing flow will be handled inside the connector.

<br/>

**Properties**

* accessToken:Access token which is obtained through the OAuth2 playground.
* apiUrl: The application URL of Google Sheet version v4.
* clientId: Value of your client id, which can be obtained via Google developer console.
* clientSecret: Value of your client secret, which can be obtained via Google developer console.
* refreshToken: Refresh token which is obtained through the OAuth2 playground. It is used to refresh the accesstoken.

<br/>

Now that you have connected to Google Spreadsheet, use the information in the following topics to perform various operations with the google spreadsheet connector.

* To work with Spreadsheet operation, See [Working with Spreadsheet Operations](workWithSpreadsheet.md).
* To work with sheets in Spreadsheet, See [Working with Sheet Operations](workWithSheet.md).
* To work with data within the sheet, see [Working with Sheet Data Operations](workWithSheetData.md).