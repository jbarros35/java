(function() {
  var app = angular.module("main", []);
  
  app.controller('LoginController', ['$scope','$http', function($scope, $http){
	  console.log('LoginController');
	  this.postJson = function() {
		  var json = {"id":9,"firstName":"Adriana","lastName":"Barrer","age":12};
		  
		  $http.post('rest/hello/send', json).
			success(function(data, status, headers, config) {
				 console.log("Response:"+data);
			 // $scope.events = data;
		   }).
			error(function(data, status, headers, config) {
			  // log error
			  console.log("Error "+data);
			});	  
	  };
	  //$http.defaults.useXDomain = true;
		
  }]);
})();
