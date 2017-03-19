var app = angular.module('libApp',['ngRoute']);

//app.factory('SharedProperties', ['$scope', function($scope){
app.service('SharedProperties', function(){
    var userId = null;
    /*return {
        getUserId: function () {
            return userId;
        },
        setUserId: function(value) {
        	userId = value;
        }
    };*/
    var cart = [];
});

app.controller('LoginController', function($scope,$http,$window,$location,SharedProperties) {
	$scope.loginFunc = function(){
//	$http.get("rest/libraryService/getMsg")
//    .then(function(response) {
//        $scope.msg = response.data;
//    });
		$http({
	        method : "POST",
	        url : "rest/libraryService/validateLogin",
	        //data : $scope.user,
	        data : angular.toJson($scope.user),
	        
//	        headers: {
//	        	   "Content-Type" : "application/json",
//	        	   "Accept": "application/json"
//	        	 }
//	        	 
			contentType: "application/json",
			dataType: "json",
			//accept: application/json
	    }).then(function mySucces(response) {
	    	SharedProperties.userId = response.data;
	    	if(response.data!=0)
	        $scope.msg = "Valid credentials";
	        if($scope.msg == "Valid credentials")
	        $location.path("/library");
	        else
	        	$location.path("/login");
//	        $window.location.href = '/library.html';
	    }, function myError(response) {
	        $scope.msg = response.statusText;
	    });
	}
});

app.controller('SignUpController', function($scope,$http,$window,$location,$rootScope,SharedProperties){
	$scope.phoneNumber =/^\d{1,45}$/;
	$scope.signUp = function(){
	if($scope.user.password!="" && $scope.user.password!=$scope.user.confirmedPassword)
		{
			$scope.msg1 = "Confirmed Password is not same as the password entered";
			alert("Confirmed Password is not same as the password entered");
		}
	else{
	$http({
        method : "POST",
        dataType: 'json',
        url : "rest/libraryService/signUp",
        //accept: application/json,
        data : $scope.user,
        headers: {
            "Content-Type": "application/json",
//            	"Accept": "application/json"
        }
//        headers: {
//        	accept: application/json
//        }
        //data : angular.toJson($scope.user)
    }).then(function mySuccess(response) {
    	SharedProperties.userId = response.data;
    	$scope.userId = response.data;
    	$rootScope.userId = response.data;
    	//$rootScope.userId = response.data;
    	//SharedProperties.setUserId = response.data;
    	$location.path("/library");
    }, function myError(response) {
        $scope.msg = response.statusText;
    });
	}
	}
});

app.controller('SearchController',function($scope,$http,$window,$location,SharedProperties){
	$scope.userId = SharedProperties.userId;
	$scope.searchFunc = function(){
	$http({
        method : "POST",
        //url : "library_books.json",
        url : "rest/libraryService/search",
    }).then(function mySucces(response) {
    	$scope.books=response.data;
    }, function myError(response) {
        $scope.msg = response.statusText;
    });
	}
	
	
	$scope.cart = [];
	$scope.addToCart = function (book) {
		var found = false;
		$scope.cart.forEach(function (item) {
			if (item.id === book.id) {
				//item.quantity++;
				//$scope.cart.length++;
				found = true;
			}
		});
		if (!found) {
			$scope.cart.push(angular.extend({quantity: 1}, book));
			book.availableunits--;
		}
	};
	SharedProperties.cart = $scope.cart;
});

app.controller('ProfileController',function($scope,$http,$window,$location,SharedProperties){
	$scope.userId = SharedProperties.userId;
	$http({
        method : "GET",
        //url : "rest/libraryService/myProfile", //For QueryParam
        url : "rest/libraryService/myProfile/"+ $scope.userId //PathParam
        //data : {userId: $scope.userId}
        //params : {"userId" : $scope.userId} // QueryParams
    }).then(function mySuccess(response) {
    	$scope.user=response.data;
    }, function myError(response) {
        $scope.msg = response.statusText;
    });
	}
);

//Define Routing for app
app.config(['$routeProvider','$locationProvider',
  function($routeProvider,$locationProvider) {
    $routeProvider
    .when('/login', {
        templateUrl: 'login.html',
        controller: 'LoginController'
    })
    .when('/signUp', {
        templateUrl: 'signUp.html',
        controller: 'SignUpController'
      })
      .when('/library', {
        templateUrl: 'library.html',
        controller: 'SearchController'
      })
      .when('/myCart', {
    	  templateUrl: 'cart.html',
    	  controller: 'CartController'
      })
      .when('/myProfile', {
    	  templateUrl: 'profile.html',
    	  controller: 'ProfileController'
      })
      .otherwise({
        redirectTo: ''
      });
    	
//    $locationProvider.html5Mode(true); //Remove the '#' from URL.
}]);

app.controller('CartController',function($scope,$http,$window,$location,SharedProperties){
	$scope.cart = SharedProperties.cart;
	$scope.userId = SharedProperties.userId;
	$scope.placeOrder = function()
	{
		//$scope.msg = "Order placed Successfully. Thank you!";
		$http({
	        method : "POST",
	        //url : "rest/libraryService/placeOrder/:userId/:cart",
	        url : "rest/libraryService/placeOrder",
	        data : {userId: $scope.userId, cart:$scope.cart}
	    }).then(function mySuccess(response) {
	    	$scope.msg=response.data;
	    }, function myError(response) {
	        $scope.msg = response.statusText;
	    });
	}
});


//Home Page Module
//angular.module('homeApp', []).config(['$routeProvider',
//function($routeProvider,$locationProvider) {
//  $routeProvider
//  .when('/home', {
//      templateUrl: 'home.html'
//  })
//  .when('/profile', {
//      templateUrl: 'profile.html',
//      controller: 'ProfileController'
//  })
//  .when('/settings', {
//      templateUrl: 'settings.html',
//      controller: 'SettingsController'
//    })
//    .otherwise({
//      redirectTo: '/home'
//    });
////  $locationProvider.html5Mode(true);
//}]);