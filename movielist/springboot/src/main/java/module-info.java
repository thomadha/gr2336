module movielist.springboot {

  requires com.google.gson;
  requires spring.web;
  requires spring.boot;
  requires spring.context;
  requires spring.boot.autoconfigure;
  requires spring.beans;
  requires core;
  requires ui;
  

  opens movielist.springboot to movielist.springboot, spring.beans, spring.web, spring.context, com.google.gson, core, ui;
  
}
