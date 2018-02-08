# uportal-messages

This messaging microservice is intended for use with [uportal-home](https://github.com/uPortal-Project/uportal-home).

uPortal messages include both notifications and announcements, and can be tailored for audiences as small as one person. 

## What is a message?

A message is an announcement or notification which can be targeted to an entire user population, a subset of the population, or a single user. 

This microservice processes messages in json form. The details of how that json is structured are found in the uportal-app-framework project's [documentation](https://github.com/uPortal-Project/uportal-app-framework/blob/master/docs/messaging-implementation.md). 

## Configuration

Set the source of your messages in the ``application.properties`` file. In this example, we've selected a json file in our resources directory. 
``` javascript
message.source=classpath:messages.json
```

## To Build

This project uses maven. ```$ mvn package ``` will build a warfile for deployment. 

To run locally, ```$ mvn spring-boot:run ``` will compile and run this microservice. 

## Endpoints



### {*/*}

calls:
```java 
    @RequestMapping("/")
    public @ResponseBody
    void index(HttpServletResponse response)   
```
returns:

```
{"status":"up"}
```
description:
This endpoint returns a small json object indicating that the status of the application is healthy. 

### {*/messages*}

calls:
``` java
   @RequestMapping(value="/messages", method=RequestMethod.GET)
    public @ResponseBody void messages(HttpServletRequest request,
        HttpServletResponse response) 
```
returns:
A json object containing every known message, unfiltered by group or date, and with data URLs unresolved. 

description:
Intended for admin and debugging use.

### {*/currentMessages*}
calls:
``` java
    @RequestMapping(value = "/currentMessages", method = RequestMethod.GET)
    public @ResponseBody void currentMessages(HttpServletRequest request, HttpServletResponse response) 
```

returns:
A json object, containing every known message which is valid for the current system date of the microservice's host. 

description:
An interim method, the functionality of which is to be folded into more robust filtering when authentication is implemented. 
