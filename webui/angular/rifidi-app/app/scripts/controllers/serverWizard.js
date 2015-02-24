'use strict';

/**
 * @ngdoc function
 * @name rifidiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the rifidiApp
 */
angular.module('rifidiApp')
  .controller('ServerWizardCtrl', function ($scope, $http, $routeParams) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

      $scope.submitForm = function(isValid) {

        // check to make sure the form is completely valid
        if (isValid) {
          alert('our form is amazing');
        }

      };




      //console.log("cleaning readerTypes: ");
      //$scope.readerTypes = [];
      //$scope.commandTypes = [];
      //$scope.commandInstances = [];
      //$scope.selectedReaderType = "xx";
      //console.log("set $scope.selectedReaderType:  " + $scope.selectedReaderType);
      //$scope.selectedCommandType = {};
      //$scope.selectedCommandInstance = {};
      //$scope.readersConnectionProperties = [];
      //$scope.customReaderId = "";
      //$scope.selectedReaderConnectionProperties = [];


      //loadReaderTypes();

      //Call the reader types service, and create a list with reader types
      //$scope.loadReaderTypes = function () {

        //retrieve the reader types
        var restProtocol = $routeParams.restProtocol;
        var ipAddress = $routeParams.ipAddress;
        var restPort = $routeParams.restPort;

        var host = restProtocol + "://" + ipAddress + ":" + restPort;

        //console.log("host: " + host);



      /*
        $scope.readerTypes = [
          { id: 1, name: 'Foo111', factoryID: 'xxx111' },
          { id: 2, name: 'Bar222', factoryID: 'yyy222' },
          { id: 22, name: 'Bar222BB', factoryID: 'yyy222BB' }
        ];
        */

        $http.get(host + '/readertypes')
            .success(function(data, status, headers, config) {

              //var sessionsResponseHost = headers('host');

              //console.log("data for readerstatus from host: " + sessionsResponseHost + ": " + data);

              var xmlReaderTypes;
              if (window.DOMParser)
              {
                var parser = new DOMParser();
                xmlReaderTypes = parser.parseFromString(data,"text/xml");
              }
              else // Internet Explorer
              {
                xmlReaderTypes = new ActiveXObject("Microsoft.XMLDOM");
                xmlReaderTypes.async=false;
                xmlReaderTypes.loadXML(data);
              }

              //get the xml response and extract the values to construct the reader types object

              var readerTypesXmlVector = xmlReaderTypes.getElementsByTagName("sensor");

              $scope.readerTypes = [];


              for(var index = 0; index < readerTypesXmlVector.length; index++) {

                var factoryID = readerTypesXmlVector[index].getElementsByTagName("factoryID")[0].childNodes[0];
                var description = readerTypesXmlVector[index].getElementsByTagName("description")[0].childNodes[0];

                //Add the reader type to the reader types list

                //define an empty command structure to hold the reader type
                var readerTypeElement = {
                  "factoryID": factoryID.nodeValue,
                  "description": description.nodeValue
                };

                //console.log("readerTypeElement: ");
                //console.log(readerTypeElement);
                $scope.readerTypes.push(readerTypeElement);

              }
/*
              $scope.readerTypes = [
                { id: 3, name: 'Foo33', factoryID: 'xxx33' },
                { id: 4, name: 'Bar44', factoryID: 'yyy44' }
              ];
*/
              //console.log("$scope.readerTypes");
              //console.log($scope.readerTypes);

            })
            .error(function(data, status, headers, config) {
              console.log("error reading reader types for sensor wizard");

              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });


      //load the connection properties
      $http.get(host + '/readermetadata')
          .success(function(data, status, headers, config) {


            var xmlMetadata;
            if (window.DOMParser)
            {
              var parser = new DOMParser();
              xmlMetadata = parser.parseFromString(data,"text/xml");
            }
            else // Internet Explorer
            {
              xmlMetadata = new ActiveXObject("Microsoft.XMLDOM");
              xmlMetadata.async=false;
              xmlMetadata.loadXML(data);
            }

            //get the xml response and extract the values to construct the connection properties for all readers

            var readerMetadataXmlVector = xmlMetadata.getElementsByTagName("reader");

            //console.log("readerMetadataXmlVector.length: " + readerMetadataXmlVector.length);

            $scope.readersConnectionProperties = [];


            for(var index = 0; index < readerMetadataXmlVector.length; index++) {


              var readerid = readerMetadataXmlVector[index].getElementsByTagName("readerid")[0].childNodes[0];
              var propertiesXmlVector = readerMetadataXmlVector[index].getElementsByTagName("property");

              //console.log("readerid: " + readerid.nodeValue);
              //console.log("properties length: " + propertiesXmlVector.length);
              //console.log("propertiesXmlVector");
              //console.log(propertiesXmlVector);

              //Create a properties object for this reader
              var readerConnectionProperties = {
                "readerid": readerid.nodeValue,
                "startSessionAut": false,
                "properties": []
              };



              for(var indexProp = 0; indexProp < propertiesXmlVector.length; indexProp++) {

                //console.log("indexProp: " + indexProp);

                var category = propertiesXmlVector[indexProp].getElementsByTagName("category")[0].childNodes[0];

                //console.log("category: '" + category.nodeValue + "'");

                if (category.nodeValue == 'connection') {

                  var name = propertiesXmlVector[indexProp].getElementsByTagName("name")[0].childNodes[0];
                  var displayname = propertiesXmlVector[indexProp].getElementsByTagName("displayname")[0].childNodes[0];
                  //console.log("displayname: " + displayname.nodeValue);
                  var defaultvalue = propertiesXmlVector[indexProp].getElementsByTagName("defaultvalue")[0].childNodes[0];
                  var description = propertiesXmlVector[indexProp].getElementsByTagName("description")[0].childNodes[0];
                  var type = propertiesXmlVector[indexProp].getElementsByTagName("type")[0].childNodes[0];
                  var maxvalue = 0;
                  var minvalue = 0;
                  if (type.nodeValue == 'java.lang.Integer') {
                    //maxvalue = propertiesXmlVector[indexProp].getElementsByTagName("maxvalue")[0].childNodes[0];
                    //minvalue = propertiesXmlVector[indexProp].getElementsByTagName("minvalue")[0].childNodes[0];
                  }


                  var writable = propertiesXmlVector[indexProp].getElementsByTagName("writable")[0].childNodes[0];
                  var ordervalue = propertiesXmlVector[indexProp].getElementsByTagName("ordervalue")[0].childNodes[0];

                  //if category equals to connection, then extract that property
                  if (category.nodeValue == 'connection') {

                    var customdefaultvalue;

                    if (type.nodeValue == 'java.lang.Integer'){
                      customdefaultvalue = parseInt(defaultvalue.nodeValue);
                    } else {
                      customdefaultvalue = defaultvalue.nodeValue;
                    }

                    var propertyElement = {
                      "name": name.nodeValue,
                      "displayname": displayname.nodeValue,
                      "description": description.nodeValue,
                      "type": type.nodeValue,
                      "maxvalue": maxvalue.nodeValue,
                      "minvalue": minvalue.nodeValue,
                      "category": category.nodeValue,
                      "writable": writable.nodeValue,
                      "ordervalue": ordervalue.nodeValue,
                      "value": customdefaultvalue,
                      "defaultvalue": customdefaultvalue
                    };

                    //Add the property to properties list
                    readerConnectionProperties.properties.push(propertyElement);

                  }
                }


              }


              $scope.readersConnectionProperties.push(readerConnectionProperties);


            }

            //console.log("$scope.readersConnectionProperties");
            //console.log($scope.readersConnectionProperties);

          })
          .error(function(data, status, headers, config) {
            console.log("error reading readermetadata for sensor wizard");

            // called asynchronously if an error occurs
            // or server returns response with an error status.
          });

      $scope.prepareCreateSessionStep = function(){
        console.log("prepareCreateSessionStep called");

        //Clean variable
        $scope.selectedReaderConnectionProperties = [];

        //console.log("$scope.selectedReaderType");
        //console.log($scope.selectedReaderType);

        //console.log("$scope.customReaderId");
        //console.log($scope.customReaderId);



        //$scope.selectedReaderType = selectedReaderType;

        //console.log("$scope.selectedReaderType");
        //console.log($scope.selectedReaderType);

        //console.log("selectedReaderType");
        //console.log(selectedReaderType);

        //load the properties for selected reader type
        $scope.readersConnectionProperties.forEach(function(readerConnectionProperties){




          if (readerConnectionProperties.readerid == $scope.selectedReaderType.factoryID){

            //console.log("match");

            //Add the property, assigning a copy of the property
            $scope.selectedReaderConnectionProperties = angular.copy(readerConnectionProperties);

          }

        });

        //console.log("$scope.selectedReaderConnectionProperties");
        //console.log($scope.selectedReaderConnectionProperties);



      }

      $scope.prepareCreateCommandStep = function(){
        console.log("prepareCreateCommandStep called");

        //Clean variables
        $scope.selectedCommandType = {};
        $scope.selectedCommandInstance = {};

        $scope.commandTypes = [];
        $scope.commandInstances = [];
        $scope.commandProperties = [];


        //console.log("$scope.selectedReaderType");
        //console.log($scope.selectedReaderType);

        //console.log("$scope.customReaderId");
        //console.log($scope.customReaderId);



        //$scope.selectedReaderType = selectedReaderType;

        //console.log("$scope.selectedReaderType");
        //console.log($scope.selectedReaderType);

        //console.log("selectedReaderType");
        //console.log(selectedReaderType);

        //load command templates for selected reader type
        $http.get(host + '/commandtypes')
            .success(function(data, status, headers, config) {


              var xmlCommandTypes;
              if (window.DOMParser)
              {
                var parser = new DOMParser();
                xmlCommandTypes = parser.parseFromString(data,"text/xml");
              }
              else // Internet Explorer
              {
                xmlCommandTypes = new ActiveXObject("Microsoft.XMLDOM");
                xmlCommandTypes.async=false;
                xmlCommandTypes.loadXML(data);
              }

              //get the xml response and extract the values to construct the local command type object
              var commandTypeXmlVector = xmlCommandTypes.getElementsByTagName("command");



              for(var index = 0; index < commandTypeXmlVector.length; index++) {

                var factoryID = commandTypeXmlVector[index].getElementsByTagName("factoryID")[0].childNodes[0];
                var description = commandTypeXmlVector[index].getElementsByTagName("description")[0].childNodes[0];
                var readerFactoryID = commandTypeXmlVector[index].getElementsByTagName("readerFactoryID")[0].childNodes[0];

                /*
                console.log("readerFactoryID.nodeValue:");
                console.log(readerFactoryID.nodeValue);

                console.log("$scope.selectedReaderType:");
                console.log($scope.selectedReaderType);
                */

                if (readerFactoryID.nodeValue == $scope.selectedReaderType.factoryID){

                  //Add the command type
                  var commandTypeElement = {
                    "factoryID": factoryID.nodeValue,
                    "description": description.nodeValue,
                    "readerFactoryID": readerFactoryID.nodeValue

                  }

                  $scope.commandTypes.push(commandTypeElement);
                }

              }

            })
            .error(function(data, status, headers, config) {
              console.log("error reading command types for sensor wizard");

              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });



      }

      $scope.readerTypeSelectAction = function(selectedReaderType){

        $scope.selectedReaderType = selectedReaderType;
        $scope.prepareCreateSessionStep();
        $scope.prepareCreateCommandStep();
      }

      $scope.commandTypeSelectAction = function(selectedCommandType){

        //clear command instances list
        $scope.commandInstances = [];
        $scope.selectedCommandType = selectedCommandType;

        //load the command instances for selected command type
        $http.get(host + '/commands')
            .success(function(data, status, headers, config) {


              var xmlCommands;
              if (window.DOMParser)
              {
                var parser = new DOMParser();
                xmlCommands = parser.parseFromString(data,"text/xml");
              }
              else // Internet Explorer
              {
                xmlCommands = new ActiveXObject("Microsoft.XMLDOM");
                xmlCommands.async=false;
                xmlCommands.loadXML(data);
              }

              //get the xml response and extract the values to construct the local command object
              var commandXmlVector = xmlCommands.getElementsByTagName("command");

              for(var index = 0; index < commandXmlVector.length; index++) {

                var commandID = commandXmlVector[index].getElementsByTagName("commandID")[0].childNodes[0];
                var factoryID = commandXmlVector[index].getElementsByTagName("factoryID")[0].childNodes[0];

                //console.log("readerFactoryID.nodeValue:");
                //console.log(readerFactoryID.nodeValue);

                //console.log("$scope.selectedReaderType:");
                //console.log($scope.selectedReaderType);

                if (factoryID.nodeValue == selectedCommandType.factoryID){

                  //Add the command instance
                  var commandInstanceElement = {
                    "commandID": commandID.nodeValue,
                    "factoryID": factoryID.nodeValue
                  }

                  $scope.commandInstances.push(commandInstanceElement);
                }

              }

              //Add the New command instance label
              var commandInstanceNewElement = {
                "commandID": "<New>",
                "factoryID": selectedCommandType.factoryID
              }

              $scope.commandInstances.push(commandInstanceNewElement);

            })
            .error(function(data, status, headers, config) {
              console.log("error reading command instances for sensor wizard");

              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });



      }

      $scope.readerIdChangeAction = function(customReaderId){

        $scope.customReaderId = customReaderId;

        //console.log("readerIdChangeAction:");
        //console.log($scope.customReaderId);

      }

      $scope.commandInstanceSelectAction = function(selectedCommandInstance){

        $scope.selectedCommandInstance = selectedCommandInstance;

        //Get the properties for the selected command type, from readermetadata
        //$scope.selectedReaderType
        //$scope.selectedCommandType

        $http.get(host + '/readermetadata')
            .success(function(data, status, headers, config) {

              var xmlMetadata;
              if (window.DOMParser)
              {
                var parser = new DOMParser();
                xmlMetadata = parser.parseFromString(data,"text/xml");
              }
              else // Internet Explorer
              {
                xmlMetadata = new ActiveXObject("Microsoft.XMLDOM");
                xmlMetadata.async=false;
                xmlMetadata.loadXML(data);
              }

              //get the xml response and extract the values to construct the command properties for selected command type

              var commandMetadataXmlVector = xmlMetadata.getElementsByTagName("command");

              //console.log("commandMetadataXmlVector.length: " + commandMetadataXmlVector.length);


              for(var index = 0; index < commandMetadataXmlVector.length; index++) {


                var propertiesXmlVector = commandMetadataXmlVector[index].getElementsByTagName("property");
                var id = commandMetadataXmlVector[index].getElementsByTagName("id")[0].childNodes[0];
                var readerID = commandMetadataXmlVector[index].getElementsByTagName("readerID")[0].childNodes[0];

                /*
                console.log("id: " + id.nodeValue);
                console.log("readerID: " + readerID.nodeValue);
                console.log("properties length: " + propertiesXmlVector.length);
                console.log("propertiesXmlVector");
                console.log(propertiesXmlVector);
                */

                //check if current command is the required one
                /*
                console.log("readerID.nodeValue:");
                console.log(readerID.nodeValue);
                console.log("$scope.selectedReaderType:");
                console.log($scope.selectedReaderType);
                console.log("id.nodeValue:");
                console.log(id.nodeValue);
                console.log("selectedCommandInstance.factoryID:");
                console.log(selectedCommandInstance.factoryID);
                */

                if (readerID.nodeValue == $scope.selectedReaderType.factoryID && id.nodeValue ==  selectedCommandInstance.factoryID){

                  //console.log("found selectedCommandInstance.factoryID: " + selectedCommandInstance.factoryID);

                  //Create the properties object for this command
                  $scope.commandProperties = {
                    "readerID": readerID.nodeValue,
                    "id": id.nodeValue,
                    "propertyCategoryList": []

                  };

                  //extract the properties
                  for(var indexProp = 0; indexProp < propertiesXmlVector.length; indexProp++) {


                    var name = propertiesXmlVector[indexProp].getElementsByTagName("name")[0].childNodes[0];
                    var displayname = propertiesXmlVector[indexProp].getElementsByTagName("displayname")[0].childNodes[0];
                    var defaultvalue = propertiesXmlVector[indexProp].getElementsByTagName("defaultvalue")[0].childNodes[0];
                    var description = propertiesXmlVector[indexProp].getElementsByTagName("description")[0].childNodes[0];
                    var type = propertiesXmlVector[indexProp].getElementsByTagName("type")[0].childNodes[0];
                    var maxvalue = 0;
                    var minvalue = 0;
                    if (type.nodeValue == 'java.lang.Integer') {
                      maxvalue = propertiesXmlVector[indexProp].getElementsByTagName("maxvalue")[0].childNodes[0];
                      minvalue = propertiesXmlVector[indexProp].getElementsByTagName("minvalue")[0].childNodes[0];
                    }
                    var category = propertiesXmlVector[indexProp].getElementsByTagName("category")[0].childNodes[0];
                    var writable = propertiesXmlVector[indexProp].getElementsByTagName("writable")[0].childNodes[0];
                    var ordervalue = propertiesXmlVector[indexProp].getElementsByTagName("ordervalue")[0].childNodes[0];

                    //check if current category already exists in propertyCategories
                    var existingCategory = false;

                    $scope.commandProperties.propertyCategoryList.forEach(function (propertyCategory) {

                      if (category.nodeValue == propertyCategory.category){
                        existingCategory = true;
                      }

                    });

                    if (!existingCategory){

                      var propertyCategory = {
                        "category": category.nodeValue,
                        "properties": []
                      };

                      $scope.commandProperties.propertyCategoryList.push(propertyCategory);
                    }

                    /*
                    var customdefaultvalue;

                    if (type.nodeValue == 'java.lang.Integer'){
                      customdefaultvalue = parseInt(defaultvalue.nodeValue);
                    } else {
                      customdefaultvalue = defaultvalue.nodeValue;
                    }
                    */

                    var propertyElement = {
                      "name": name.nodeValue,
                      "displayname": displayname.nodeValue,
                      "description": description.nodeValue,
                      "type": type.nodeValue,
                      "maxvalue": maxvalue.nodeValue,
                      "minvalue": minvalue.nodeValue,
                      "category": category.nodeValue,
                      "writable": writable.nodeValue,
                      "ordervalue": ordervalue.nodeValue,
                      "value": "",
                      "defaultvalue": ""
                    };

                    //Set the default value for property

                    if (type.nodeValue == 'java.lang.Integer'){
                      propertyElement.value = angular.copy(parseInt(defaultvalue.nodeValue));
                      propertyElement.defaultvalue = angular.copy(parseInt(defaultvalue.nodeValue));
                    } else {
                      propertyElement.value = angular.copy(defaultvalue.nodeValue);
                      propertyElement.defaultvalue = angular.copy(defaultvalue.nodeValue);
                    }

                    //Add the property to appropriate property category list
                    $scope.commandProperties.propertyCategoryList.forEach(function (propertyCategory) {

                      if (category.nodeValue == propertyCategory.category){

                        propertyCategory.properties.push(angular.copy(propertyElement));

                      }

                    });

                  }

                  //If user selects a command instance (not the <New> option), then load the current values for every property
                  if (selectedCommandInstance.commandID != '<New>'){

                    console.log("selectedCommandInstance.commandID != '<New>'");


                    //call the service to get properties of command instance
                    $http.get(host + '/getproperties/' + selectedCommandInstance.commandID)
                        .success(function(data, status, headers, config) {

                          var xmlCommandProperties;
                          if (window.DOMParser)
                          {
                            var parser = new DOMParser();
                            xmlCommandProperties = parser.parseFromString(data,"text/xml");
                          }
                          else // Internet Explorer
                          {
                            xmlCommandProperties = new ActiveXObject("Microsoft.XMLDOM");
                            xmlCommandProperties.async=false;
                            xmlCommandProperties.loadXML(data);
                          }

                          //get the xml response and extract the values for properties
                          var propertiesXmlVector = xmlCommandProperties.getElementsByTagName("entry");

                          for(var indexPropertyValue = 0; indexPropertyValue < propertiesXmlVector.length; indexPropertyValue++) {

                            var key = propertiesXmlVector[indexPropertyValue].getElementsByTagName("key")[0].childNodes[0];
                            var value = propertiesXmlVector[indexPropertyValue].getElementsByTagName("value")[0].childNodes[0];

                            //Iterate the loaded properties and set this value when property key matches
                            $scope.commandProperties.propertyCategoryList.forEach(function (propertyCategory) {

                              //Iterate al categories to find this property and set the value
                              propertyCategory.properties.forEach(function (property) {

                                if (property.name == key.nodeValue){

                                  //Set the value for property

                                  if (property.type == 'java.lang.Integer'){
                                    property.value = angular.copy(parseInt(value.nodeValue));
                                  } else {
                                    property.value = angular.copy(value.nodeValue);
                                  }

                                }

                              });


                            });


                          }

                        }).
                        error(function(data, status, headers, config) {
                          console.log("error reading command instance properties");


                          // called asynchronously if an error occurs
                          // or server returns response with an error status.
                        });

                  }

                }


              }


            })
            .error(function(data, status, headers, config) {
              console.log("error reading readermetadata for sensor wizard: command instance selection");

              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });






      }

      $scope.finishedSensorWizard = function(selectedReaderType){

        console.log("finishedSensorWizard");
        //create the reader

        $scope.showSensorCreationResponse = true;

        $scope.sensorCreationResponseStatus = {};
        $scope.sensorCreationResponseStatus.message = "";
        $scope.sensorCreationResponseStatus.description = "";

        $scope.commandCreationResponseStatus = {};
        $scope.commandCreationResponseStatus.message = "";
        $scope.commandCreationResponseStatus.description = "";

        $scope.setCommandPropertiesResponseStatus = {};
        $scope.setCommandPropertiesResponseStatus.message = "";
        $scope.setCommandPropertiesResponseStatus.description = "";

        $scope.executeCommandResponseStatus = {};
        $scope.executeCommandResponseStatus.message = "";
        $scope.executeCommandResponseStatus.description = "";


        var strProperties = "";

        console.log("$scope.selectedReaderConnectionProperties");
        console.log($scope.selectedReaderConnectionProperties);

        for (var i=0; i < $scope.selectedReaderConnectionProperties.properties.length; i++){
          strProperties += $scope.selectedReaderConnectionProperties.properties[i].name + "="
          + $scope.selectedReaderConnectionProperties.properties[i].value + ";"
        }

        //Quit the last semicolon ;
        if (strProperties.length > 0){
          strProperties = strProperties.substring(0, strProperties.length - 1);
        }

        //If there is customreaderid, then set
        if ($scope.customReaderId && $scope.customReaderId != ''){
          strProperties += ";readerID=" + $scope.customReaderId
        }

        //before create the reader, query the list of readers to know what is going to be the new reader id after creation

        var serviceIdListBeforeCreation = [];

        $http.get(host + '/readers')
            .success(function(data, status, headers, config) {



              var xmlReadersQueryResponse;
              if (window.DOMParser)
              {
                var parser = new DOMParser();
                xmlReadersQueryResponse = parser.parseFromString(data,"text/xml");
              }
              else // Internet Explorer
              {
                xmlReadersQueryResponse = new ActiveXObject("Microsoft.XMLDOM");
                xmlReadersQueryResponse.async=false;
                xmlReadersQueryResponse.loadXML(data);
              }

              //get the xml response and extract the values
              var xmlReaderList = xmlReadersQueryResponse.getElementsByTagName("sensor");

              for(var indexReader = 0; indexReader < xmlReaderList.length; indexReader++) {

                var serviceID = xmlReaderList[indexReader].getElementsByTagName("serviceID")[0].childNodes[0];
                serviceIdListBeforeCreation.push(serviceID);
              }

            })
            .error(function(data, status, headers, config) {
              console.log("error querying the readers by wizard");

              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });

        console.log("going to call create reader: " + host + '/createreader/' + $scope.selectedReaderConnectionProperties.readerid + "/" + encodeURIComponent(strProperties));

        $http.get(host + '/createreader/' + $scope.selectedReaderConnectionProperties.readerid + "/" + encodeURIComponent(strProperties))
            .success(function(data, status, headers, config) {


              var xmlCreateReaderResponse;
              if (window.DOMParser)
              {
                var parser = new DOMParser();
                xmlCreateReaderResponse = parser.parseFromString(data,"text/xml");
              }
              else // Internet Explorer
              {
                xmlCreateReaderResponse = new ActiveXObject("Microsoft.XMLDOM");
                xmlCreateReaderResponse.async=false;
                xmlCreateReaderResponse.loadXML(data);
              }

              //get the xml response and extract the value
              var message = xmlCreateReaderResponse.getElementsByTagName("message")[0].childNodes[0].nodeValue;

              //$scope.sensorCreationResponseStatus = {};
              $scope.sensorCreationResponseStatus.message = message;
              //$scope.sensorCreationResponseStatus.description = "";

              if (message == 'Success'){
                console.log("success creating reader by wizard");

                //Query the readers after creation and compare with readers created before creation, to get the new reader id
                var serviceIdListAfterCreation = [];

                $http.get(host + '/readers')
                    .success(function(data, status, headers, config) {

                      var xmlReadersQueryResponse;
                      if (window.DOMParser)
                      {
                        var parser = new DOMParser();
                        xmlReadersQueryResponse = parser.parseFromString(data,"text/xml");
                      }
                      else // Internet Explorer
                      {
                        xmlReadersQueryResponse = new ActiveXObject("Microsoft.XMLDOM");
                        xmlReadersQueryResponse.async=false;
                        xmlReadersQueryResponse.loadXML(data);
                      }

                      //get the xml response and extract the values
                      var xmlReaderList = xmlReadersQueryResponse.getElementsByTagName("sensor");

                      for(var indexReader = 0; indexReader < xmlReaderList.length; indexReader++) {

                        var serviceID = xmlReaderList[indexReader].getElementsByTagName("serviceID")[0].childNodes[0];
                        serviceIdListAfterCreation.push(serviceID);
                      }

                      //From serviceIdListBeforeCreation and serviceIdListAfterCreation arrays, extract the created reader id

                      var newIdFound = false;

                      serviceIdListAfterCreation.forEach(function (serviceIDAfter) {

                        //console.log("serviceIDAfter: ");
                        //console.log(serviceIDAfter);

                        if ( !newIdFound && !arrayContainsValue(serviceIdListBeforeCreation, serviceIDAfter)   ){

                          newIdFound = true;
                          console.log("not found ");
                          console.log(serviceIDAfter.nodeValue);

                          $scope.readerID = serviceIDAfter.nodeValue;

                          //Create a session on that created reader
                          $http.get(host + '/createsession/' + serviceIDAfter.nodeValue)
                              .success(function(data, status, headers, config) {

                                console.log("success creating session by wizard");

                                //construct the properties to be set on command, be it an already existing command or a new one
                                var strCommandProperties = "";

                                console.log("$scope.ommandProperties.propertyCategoryList");
                                console.log($scope.commandProperties.propertyCategoryList);
                                console.log("$scopecommandProperties.propertyCategoryList.length");
                                console.log($scope.commandProperties.propertyCategoryList.length);

                                for (var idxCat=0; idxCat < $scope.commandProperties.propertyCategoryList.length; idxCat++){

                                  console.log("$scope.commandProperties.propertyCategoryList[idxCat]");
                                  console.log($scope.commandProperties.propertyCategoryList[idxCat]);

                                  for (var idxProp=0; idxProp < $scope.commandProperties.propertyCategoryList[idxCat].properties.length; idxProp++){

                                    strCommandProperties += $scope.commandProperties.propertyCategoryList[idxCat].properties[idxProp].name + "="
                                    + $scope.commandProperties.propertyCategoryList[idxCat].properties[idxProp].value + ";"
                                  }


                                }

                                //Quit the last semicolon ;
                                if (strCommandProperties.length > 0){
                                  strCommandProperties = strCommandProperties.substring(0, strCommandProperties.length - 1);
                                }

                                console.log("strCommandProperties");
                                console.log(strCommandProperties);


                                //Check if need to create command
                                if($scope.selectedCommandInstance.commandID == '<New>'){

                                  //Create command
                                  console.log("going to create command");

                                  //Before creating command, query the commands to retrieve the new command id once it is created

                                  var commandIdListBeforeCreation = [];

                                  $http.get(host + '/commands')
                                      .success(function(data, status, headers, config) {

                                        var xmlCommandsQueryResponse;
                                        if (window.DOMParser)
                                        {
                                          var parser = new DOMParser();
                                          xmlCommandsQueryResponse = parser.parseFromString(data,"text/xml");
                                        }
                                        else // Internet Explorer
                                        {
                                          xmlCommandsQueryResponse = new ActiveXObject("Microsoft.XMLDOM");
                                          xmlCommandsQueryResponse.async=false;
                                          xmlCommandsQueryResponse.loadXML(data);
                                        }

                                        //get the xml response and extract the values
                                        var xmlCommandList = xmlCommandsQueryResponse.getElementsByTagName("command");

                                        for(var indexCommand = 0; indexCommand < xmlCommandList.length; indexCommand++) {

                                          var commandID = xmlCommandList[indexCommand].getElementsByTagName("commandID")[0].childNodes[0];
                                          commandIdListBeforeCreation.push(commandID);
                                        }


                                        //create command
                                        console.log("$scope.selectedCommandType");
                                        console.log($scope.selectedCommandType);
                                        console.log($scope.selectedCommandType.factoryID);
                                        console.log("going to execute: " + host + '/createcommand/' + $scope.selectedCommandType.factoryID + "/" + strCommandProperties);

                                        $http.get(host + '/createcommand/' + $scope.selectedCommandType.factoryID + "/" + strCommandProperties)
                                            .success(function(data, status, headers, config) {

                                              console.log("success response creating command in wizard");

                                              var xmlCreateCommandResponse;
                                              if (window.DOMParser)
                                              {
                                                var parser = new DOMParser();
                                                xmlCreateCommandResponse = parser.parseFromString(data,"text/xml");
                                              }
                                              else // Internet Explorer
                                              {
                                                xmlCreateCommandResponse = new ActiveXObject("Microsoft.XMLDOM");
                                                xmlCreateCommandResponse.async=false;
                                                xmlCreateCommandResponse.loadXML(data);
                                              }

                                              //get the xml response and extract the values for properties
                                              var createCommandMessage = xmlCreateCommandResponse.getElementsByTagName("message")[0].childNodes[0].nodeValue;

                                              $scope.commandCreationResponseStatus.message = createCommandMessage;

                                              if (createCommandMessage == 'Success') {
                                                console.log("success creating command by wizard");

                                                //Query the commands after creation and compare with commands created before creation, to get the new command id
                                                var commandIdListAfterCreation = [];

                                                $http.get(host + '/commands')
                                                    .success(function(data, status, headers, config) {

                                                      var xmlCommandsQueryResponse;
                                                      if (window.DOMParser)
                                                      {
                                                        var parser = new DOMParser();
                                                        xmlCommandsQueryResponse = parser.parseFromString(data,"text/xml");
                                                      }
                                                      else // Internet Explorer
                                                      {
                                                        xmlCommandsQueryResponse = new ActiveXObject("Microsoft.XMLDOM");
                                                        xmlCommandsQueryResponse.async=false;
                                                        xmlCommandsQueryResponse.loadXML(data);
                                                      }

                                                      //get the xml response and extract the values
                                                      var xmlCommandList = xmlCommandsQueryResponse.getElementsByTagName("command");

                                                      for(var indexCommand = 0; indexCommand < xmlCommandList.length; indexCommand++) {

                                                        var commandID = xmlCommandList[indexCommand].getElementsByTagName("commandID")[0].childNodes[0];
                                                        commandIdListAfterCreation.push(commandID);
                                                      }


                                                      //From commandIdListBeforeCreation and commandIdListAfterCreation arrays, extract the created command id

                                                      var newIdFound = false;

                                                      commandIdListAfterCreation.forEach(function (commandIDAfter) {

                                                        //console.log("commandIDAfter: ");
                                                        //console.log(commandIDAfter);

                                                        if ( !newIdFound && !arrayContainsValue(commandIdListBeforeCreation, commandIDAfter)   ) {

                                                          newIdFound = true;
                                                          console.log("not found ");
                                                          console.log(commandIDAfter.nodeValue);



                                                          continueExecutingCommand(commandIDAfter.nodeValue);

                                                        }

                                                      });


                                                    }).
                                                    error(function(data, status, headers, config) {
                                                      console.log("error querying commands after command creation in wizard");


                                                      // called asynchronously if an error occurs
                                                      // or server returns response with an error status.
                                                    });





                                              } else {
                                                var createCommandDescription = xmlCreateCommandResponse.getElementsByTagName("description")[0].childNodes[0].nodeValue;
                                                $scope.commandCreationResponseStatus.description = createCommandDescription;
                                                console.log("fail creating command by wizard");
                                                console.log(createCommandDescription);
                                              }




                                            }).
                                            error(function(data, status, headers, config) {
                                              console.log("error creating command in wizard");


                                              // called asynchronously if an error occurs
                                              // or server returns response with an error status.
                                            });





                                      })
                                      .error(function(data, status, headers, config) {
                                        console.log("error querying the commands by wizard");

                                        // called asynchronously if an error occurs
                                        // or server returns response with an error status.
                                      });



                                } else {

                                  console.log("NOT going to create command");
                                  //Set properties for already existing command instance

                                  console.log("going to set command properties: " + host + '/setproperties/' + $scope.selectedCommandInstance.commandID + '/' + strCommandProperties);

                                  $http.get(host + '/setproperties/' + $scope.selectedCommandInstance.commandID + '/' + strCommandProperties)
                                      .success(function(data, status, headers, config) {

                                        var xmlSetCommandPropertiesResponse;
                                        if (window.DOMParser)
                                        {
                                          var parser = new DOMParser();
                                          xmlSetCommandPropertiesResponse = parser.parseFromString(data,"text/xml");
                                        }
                                        else // Internet Explorer
                                        {
                                          xmlSetCommandPropertiesResponse = new ActiveXObject("Microsoft.XMLDOM");
                                          xmlSetCommandPropertiesResponse.async=false;
                                          xmlSetCommandPropertiesResponse.loadXML(data);
                                        }


                                        //get the xml response and extract the value
                                        var message = xmlSetCommandPropertiesResponse.getElementsByTagName("message")[0].childNodes[0].nodeValue;

                                        $scope.setCommandPropertiesResponseStatus.message = message;

                                        if (message == 'Success') {
                                          console.log("success setting properties for command in wizard");

                                          //Can continue with next service call in wizard: execute command, but must ensure that asynchronous call
                                          //was made and successfully finished
                                          //canExecuteCommand = true;
                                          continueExecutingCommand($scope.selectedCommandInstance.commandID);


                                        } else {
                                          var setCommandPropertiesDescription = xmlSetCommandPropertiesResponse.getElementsByTagName("description")[0].childNodes[0].nodeValue;
                                          $scope.setCommandPropertiesResponseStatus.description = setCommandPropertiesDescription;
                                          console.log("fail set command properties by wizard");
                                          console.log(setCommandPropertiesDescription);
                                        }


                                    })
                                    .error(function(data, status, headers, config) {
                                      console.log("error setting properties for existing command in wizard");

                                      // called asynchronously if an error occurs
                                      // or server returns response with an error status.
                                    });


                                }

                              })
                              .error(function(data, status, headers, config) {
                                console.log("error creating session by wizard");

                                // called asynchronously if an error occurs
                                // or server returns response with an error status.
                              });



                        }



                      });



                    })
                    .error(function(data, status, headers, config) {
                      console.log("error querying the readers after reader creation by wizard");

                      // called asynchronously if an error occurs
                      // or server returns response with an error status.
                    });



              } else {
                var description = xmlCreateReaderResponse.getElementsByTagName("description")[0].childNodes[0].nodeValue;
                $scope.sensorCreationResponseStatus.description = description;
                console.log("fail creating reader by wizard");
              }

              console.log($scope.sensorCreationResponseStatus);



        })
        .error(function(data, status, headers, config) {
          console.log("error creating reader by wizard");

          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });


      }

      function arrayContainsValue(array, value) {

        var found = false;
        console.log("arrayContainsValue called");
        console.log("array");
        console.log(array);
        console.log("value");
        console.log(value);


        array.forEach(function (data) {

          console.log("iterating data");
          console.log(data);

          console.log("data.nodeValue");
          console.log(data.nodeValue);

          if(!found){

            if ( data.nodeValue == value.nodeValue ){

              console.log("data == value");

              found = true;
            }

          }

        });

        console.log("going to return found: ");
        console.log(found);

        return found;
      }


      function continueExecutingCommand(commandId){

        console.log("continueExecutingCommand for command id: " + commandId);

        var sessionId = 1;
        var repeatInterval = -1;

        console.log("going to call execute command: " + host + '/executecommand/' + $scope.readerID + "/" + sessionId + "/" +  commandId + '/' + repeatInterval);

        $http.get(host + '/executecommand/' + $scope.readerID + "/" + sessionId + "/" + commandId + '/' + repeatInterval)
            .success(function(data, status, headers, config) {

              var xmlExecuteCommandResponse;
              if (window.DOMParser)
              {
                var parser = new DOMParser();
                xmlExecuteCommandResponse = parser.parseFromString(data,"text/xml");
              }
              else // Internet Explorer
              {
                xmlExecuteCommandResponse = new ActiveXObject("Microsoft.XMLDOM");
                xmlExecuteCommandResponse.async=false;
                xmlExecuteCommandResponse.loadXML(data);
              }


              //get the xml response and extract the value
              var message = xmlExecuteCommandResponse.getElementsByTagName("message")[0].childNodes[0].nodeValue;

              $scope.executeCommandResponseStatus.message = message;

              if (message == 'Success') {
                console.log("success executing command in wizard");

                //If start session chosen, then start the session

              } else {
                var executeCommandDescription = xmlExecuteCommandResponse.getElementsByTagName("description")[0].childNodes[0].nodeValue;
                $scope.executeCommandResponseStatus.description = executeCommandDescription;
                console.log("fail executing command by wizard");
                console.log(executeCommandDescription);
              }


            })
            .error(function(data, status, headers, config) {
              console.log("error setting properties for existing command in wizard");

              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });



      }





  });
