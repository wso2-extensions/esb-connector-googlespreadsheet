# Google Spreadsheet EI Connector

The Google Spreadsheet [connector](https://docs.wso2.com/display/EI640/Working+with+Connectors) allows you to work with spreadsheets on Google Drive, a free, web-based service that
allows users to create and edit spreadsheet documents online while collaborating in real-time with other users.
It allows you to access the [Google Spreadsheet API Version v4](https://developers.google.com/sheets/guides/concepts) through WSO2 Enterprise Integrator (WSO2 EI).


## Compatibility

| Connector version | Google spreadsheet API version | Supported WSO2 EI version |
| ------------- | ------------- | ------------- |
| [3.0.0](https://github.com/wso2-extensions/esb-connector-googlespreadsheet/releases/tag/org.wso2.carbon.connector.googlespreadsheet-3.0.0) | v4 | ESB 4.9.0, ESB 5.0.0, EI 6.1.1, EI 6.2.0, EI 6.3.0, EI 6.4.0 |
| [2.0.1](https://github.com/wso2-extensions/esb-connector-googlespreadsheet/releases/tag/org.wso2.carbon.connector.googlespreadsheet-2.0.1) (Deprecated since google spreadsheet API v3 is deprecated) | v3 | ESB 5.0.0 ESB 4.9.0 |
| [2.0.0](https://github.com/wso2-extensions/esb-connector-googlespreadsheet/releases/tag/org.wso2.carbon.connector.googlespreadsheet-2.0.0) (Deprecated since google spreadsheet API v3 is deprecated) | v3 | ESB 4.9.0 |
| [1.0.0](https://github.com/wso2-extensions/esb-connector-googlespreadsheet/releases/tag/org.wso2.carbon.connector.googlespreadsheet-1.0.0) (Deprecated since google spreadsheet API v3 is deprecated) | v3 | ESB 4.9.0 |

## Getting started

###### Download and install the connector

1. Download the connector from [WSO2 Store](https://store.wso2.com/store/assets/esbconnector/details/7181a316-bcac-4cbe-a617-a795abe4dcf3) by clicking the Download Connector button.
2. Then you can follow this [Documentation](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+the+Management+Console) to add and enable the connector via the Management Console in your EI instance.
3. For more information on using connectors and their operations in your EI configurations, see [Using a Connector](https://docs.wso2.com/display/EI640/Using+a+Connector).
4. If you want to work with connectors via EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+Tooling).

###### Configuring the connector operations

To get started with google spreadsheet connector and their operations, see [Configuring Google Spreadsheet Operations](docs/config.md).

## Building From the Source

If you want to build Google Spreadsheet connector from the source code:

1. Get a clone or download the source from [github](https://github.com/wso2-extensions/esb-connector-googlespreadsheet).
2. Run the following Maven command from the `esb-connector-googlespreadsheet` directory: `mvn clean install`.
3. The Google Spreadsheet connector zip file is created in the `esb-connector-googlespreadsheet/target` directory.

## How You Can Contribute

As an open source project, WSO2 extensions welcome contributions from the community.
Check the [issue tracker](https://github.com/wso2-extensions/esb-connector-googlespreadsheet/issues) for open issues that interest you. We look forward to receiving your contributions.