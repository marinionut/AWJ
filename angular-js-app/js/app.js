var app = angular.module('blog', [ ]);

app.controller('HomeController', ['$scope', '$http', '$window', function($scope, $http,$window) {
  $scope.helloWorld = 'Aplicatii Web cu suport Java!';

  $scope.persoane = [];
  $scope.keys = [];

  $scope.person = {};
  $scope.editPerson = {};



  $http.get('http://localhost:8080/persoana').then(
    function successCallback(response) {

    $scope.persoane = response;
    $scope.keys = Object.keys(response.data[0]);
  });
  

  $scope.addPersoana = function(person) {
    $scope.persoane.data.push(person);
    $http.post('http://localhost:8080/persoana', person);
    $scope.person = {};
  };

  $scope.setUpdatePerson = function(person) {
    $scope.editPerson = person;
  };

  $scope.updatePerson = function() {
    $http.put('http://localhost:8080/persoana', $scope.editPerson);
    $scope.editPerson = {};
  };

  $scope.deletePersoana = function(id) {
    $http.delete('http://localhost:8080/persoana/' + id)
    .then(
      function successCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };

  $scope.myArray = ['Elem 1', 'Elem 2', 'Elem 3', 'Elem 4'];
}]);


app.controller('AutorController', ['$scope', '$http', function($scope, $http) {

  $scope.autori = [];
  $scope.keys = [];

  $scope.autor = {};
  $scope.editAutor = {};
  $scope.readAutor = {};



   $http.get('http://localhost:8080/author').then(
     function successCallback(response) {

     $scope.autori = response;
     $scope.keys = Object.keys(response.data[0]);
   });


  $scope.addAutor = function(autor) {
    $scope.autori.data.push(autor);
    $http.post('http://localhost:8080/author', autor);
    $scope.autor = {};

  };

  // $scope.getById=function(id) {
  //   $http.get("http://localhost:8080/author/"+id).success(function(data){
  //     var x=JSON.stringify(data);
  //     var y=JSON.parse(x);
  //     $scope.readAutor=y;
  //    // alert($scope.readAutor.nume);
  //   })
  // }
  $scope.setUpdateAutor = function(autor) {
    $scope.editAutor = autor;
  };

  $scope.setReadAutor = function(autor) {
    $scope.readAutor = autor;
  }
  $scope.openAutor = function(autor) {
    alert(actor.nume + actor.prenume);
  }

  $scope.updateAutor = function() {
    $http.put('http://localhost:8080/author', $scope.editAutor);
    $scope.editAutor = {};
  };

  $scope.deleteAutor = function(id) {
    $http.delete('http://localhost:8080/author/' + id)
    .then(
      function successCallback(response) {
        /* code goes here */
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
}]);


app.controller('BookController', ['$scope', '$http', function($scope, $http) {

  $scope.books = [];
  $scope.keys = [];

  $scope.book = {};
  $scope.editBook = {};
  $scope.readBook = {};



   $http.get('http://localhost:8080/book').then(
     function successCallback(response) {

     $scope.books = response;
     $scope.keys = Object.keys(response.data[0]);
   });


  $scope.addBook = function(book) {
    $scope.books.data.push(book);
    $http.post('http://localhost:8080/book', book);
    $scope.book = {};

  };

  $scope.setUpdateBook = function(book) {
    $scope.editBook = book;
  };

  $scope.setReadBook = function(book) {
    $scope.readBook = book;
  }
  $scope.openBook = function(book) {
    alert(book.titlu + book.editura);
  }

  $scope.updateBook = function() {
    $http.put('http://localhost:8080/book', $scope.editBook);
    $scope.editBook = {};
  };

  $scope.deleteBook = function(id) {
    $http.delete('http://localhost:8080/book/' + id)
    .then(
      function successCallback(response) {
        /* code goes here */
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
}]);

app.controller('PlayerController', ['$scope', '$http', function($scope, $http) {

  $scope.players = [];
  $scope.keys = [];

  $scope.player = {};
  $scope.editPlayer = {};
  $scope.readPlayer = {};



   $http.get('http://localhost:8080/player').then(
     function successCallback(response) {

     $scope.players = response;
     $scope.keys = Object.keys(response.data[0]);
   });


  $scope.addPlayer = function(player) {
    $scope.players.data.push(player);
    $http.post('http://localhost:8080/player', player);
    $scope.player = {};

  };

  $scope.setUpdatePlayer = function(player) {
    $scope.editPlayer = player;
  };

  $scope.setReadPlayer = function(player) {
    $scope.readPlayer = player;
  }


  $scope.updatePlayer = function() {
    $http.put('http://localhost:8080/player', $scope.editPlayer);
    $scope.editPlayer = {};
  };

  $scope.deletePlayer = function(id) {
    $http.delete('http://localhost:8080/player/' + id)
    .then(
      function successCallback(response) {
        /* code goes here */
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
}]);

app.controller('TeamController', ['$scope', '$http', function($scope, $http) {

  $scope.teams = [];
  $scope.keys = [];

  $scope.team = {};
  $scope.editTeam = {};
  $scope.readTeam = {};
  $scope.isVisible = false;



   $http.get('http://localhost:8080/team').then(
     function successCallback(response) {

     $scope.teams = response;
     $scope.keys = Object.keys(response.data[0]);
   });


  $scope.addTeam = function(team) {
    $scope.teams.data.push(team);
    $http.post('http://localhost:8080/team', team);
    $scope.team = {};

  };

  $scope.setUpdateTeam = function(team) {
    $scope.editTeam = team;
  };

  $scope.setReadTeam = function(team) {
    $scope.readTeam = team;
    $scope.isVisible = true;
  }


  $scope.updateTeam = function() {
    $http.put('http://localhost:8080/team', $scope.editTeam);
    $scope.editTeam = {};
  };

  $scope.deleteTeam = function(id) {
    $http.delete('http://localhost:8080/team/' + id)
    .then(
      function successCallback(response) {
        /* code goes here */
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
}]);