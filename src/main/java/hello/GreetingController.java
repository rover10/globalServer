package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private int count=0;
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	count++;
    	System.out.println(count);
        
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/index")
    public String home(@RequestParam(value="home", defaultValue="World") String name) {
    	count++;
    	System.out.println(count);
    	return "<!DOCTYPE html><html><script src='http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js'></script><body><div ng-app='myApp' ng-controller='customersCtrl'>Hi <a href='#' ng-click='call()'>Get</a><input type='text' ng-keyup='call()'> <div> {{con}}</div></div><script>var app = angular.module('myApp', []);app.controller('customersCtrl', function($scope, $http) {$scope.con=''; $scope.call= function (){$http.get('http://localhost:8080/greeting').then(function (response) {$scope.con= $scope.con + response.data.content;});};});</script></body></html>";
    }
    
    
    @RequestMapping("greeting/login")
    public Greeting login(@RequestParam(value="password", defaultValue="*****") String password, @RequestParam(value="id", defaultValue="Guest") String id) {
    	count++;
    	System.out.println(count);
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, id + password));
    }
}
