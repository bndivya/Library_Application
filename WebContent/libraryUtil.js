var app = angular.module('libApp',['ngRoute']);
app.controller('LoginController', function($scope,$http,$window,$location) {
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
	        $scope.msg = response.data;
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

app.controller('SignUpController',function($scope,$http,$window,$location){
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
    }).then(function mySucces(response) {
    	$location.path("/library");
    }, function myError(response) {
        $scope.msg = response.statusText;
    });
	}
	}
});

app.controller('SearchController',function($scope,$http,$window,$location){
	$scope.searchFunc = function(){
	$http({
        method : "POST",
        url : "library_books.json",
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
			if (item.title === book.title) {
				item.quantity++;
				$scope.cart.length++;
				found = true;
			}
		});
		if (!found) {
			$scope.cart.push(angular.extend({quantity: 1}, book));
		}
	};
});

app.controller('ProfileController',function($scope,$http,$window,$location){
	$http({
        method : "POST",
        url : "library_books.json",
    }).then(function mySucces(response) {
    	$scope.books=response.data;
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

app.controller('CartController',function($scope,$http,$window,$location){
	$http({
        method : "GET",
        url : "rest/libraryService/myCart"
    }).then(function mySucces(response) {
    	$location.path("/myCart");
    }, function myError(response) {
        $scope.msg = response.statusText;
    });
	
	
	
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