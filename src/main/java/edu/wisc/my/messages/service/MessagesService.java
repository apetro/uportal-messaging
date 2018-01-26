package edu.wisc.my.messages.service;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private Environment env;

    public JSONObject getRawMessages() {
        try{
            Resource resource = resourceLoader.getResource(env.getProperty("message.source"));
            InputStream is = resource.getInputStream();
            String jsonTxt = IOUtils.toString( is, "UTF-8" );
            JSONObject json = new JSONObject(jsonTxt);
        
           return json;

        } catch (Exception e) {
           logger.warn("service exception " + e.getMessage());
           JSONObject responseObj = new JSONObject();
            responseObj.put("status", "error");
            return responseObj;
        }
    }
}