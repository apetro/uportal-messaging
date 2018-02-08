package edu.wisc.my.messages.service;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import edu.wisc.my.messages.model.Message;
import edu.wisc.my.messages.model.MessageArray;

@Service
public class MessagesService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private Environment env;

    public JSONObject allMessages() {
        try {
            Resource resource = resourceLoader.getResource(env.getProperty("message.source"));
            InputStream is = resource.getInputStream();
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            JSONObject json = new JSONObject(jsonTxt);

            return json;

        } catch (Exception e) {
            logger.warn("service exception " + e.getMessage());
            JSONObject responseObj = new JSONObject();
            responseObj.put("status", "error");
            return responseObj;
        }
    }

    public JSONObject filteredMessages() {
        JSONObject rawMessages = allMessages();
        JSONObject validMessages = new JSONObject();
        JSONArray validMessageArray = new JSONArray();
        ObjectMapper mapper = new ObjectMapper();

        try {
            MessageArray messageArray = mapper.readValue(rawMessages.toString(), MessageArray.class);
            for (Message message : messageArray.getMessages()) {
                if (message.isValidToday()) {
                    JSONObject validMessage = new JSONObject(mapper.writeValueAsString(message));
                    validMessageArray.put(validMessage);
                }
            }
            validMessages.put("messages", validMessageArray);
        } catch (Exception e) {
            logger.warn("Date filter failure " + e.getMessage());
            return null;
        }
        return validMessages;
    }
}
     