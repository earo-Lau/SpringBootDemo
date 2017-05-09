/**
 * Created by lauearo on 08/05/2017.
 */
actionApp.controller('View1Controller',[
    '$rootScope',
    '$scope',
    '$http',
    function (
        $rootScope,
        $scope,
        $http) {
            $scope.$on('$viewContentLoaded', function (p1, p2) {
                console.info('page loaded in View1Controller');
            });
            $scope.search=function () {
                personName = $scope.personName;
                $http.get('/search', {
                    params:{
                        personName: personName
                    }
                }).success(function (data) {
                    $scope.person=data;
                });
            };
        }
    ]
);

actionApp.controller('View2Controller', [
    '$rootScope',
    '$scope',
    function (
        $rootScope,
        $scope) {
        $scope.$on('$ViewContentLoaded', function (p1, p2) {
            console.info('page loaded in View2Controller')
        })
    }
])