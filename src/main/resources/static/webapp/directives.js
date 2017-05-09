/**
 * Created by lauearo on 08/05/2017.
 */
actionApp.directive('datePicker', function () {
    return{
        restrict: 'AC',
        link: function (scope, elem, attrs) {
            elem.datepicker();
        }
    }
});