module springboot {

  requires com.google.gson;
  requires spring.web;
  requires spring.boot;
  requires spring.context;
  requires spring.boot.autoconfigure;
  requires spring.beans;
  requires java.net.http;
  requires transitive core;

  exports movielist.springboot;
  opens movielist.springboot; 
    
}
