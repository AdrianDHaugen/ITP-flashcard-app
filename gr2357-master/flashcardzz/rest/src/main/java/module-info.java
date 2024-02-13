module rest {
    requires com.fasterxml.jackson.databind;

    requires spring.beans;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;

    requires core;

    opens rest;
    // opens rest to spring.beans, spring.context, spring.web;
}