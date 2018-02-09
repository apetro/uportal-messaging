package edu.wisc.my.messages.service;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import edu.wisc.my.messages.data.MessagesFromTextFile;
import edu.wisc.my.messages.model.User;
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

    private MessagesFromTextFile messageSource;

    public JSONObject allMessages() {
        try {
            List<Message> allMessages = messageSource.allMessages();

            JSONArray jsonMessageArray = new JSONArray(allMessages);
            JSONObject messagesJson = new JSONObject();
            messagesJson.put("messages", jsonMessageArray);

            return messagesJson;

        } catch (Exception e) {
            logger.warn("service exception " + e.getMessage());
            JSONObject responseObj = new JSONObject();
            responseObj.put("status", "error");
            return responseObj;
        }
    }

    public JSONObject filteredMessages(User user) {
        JSONObject validMessages = new JSONObject();
        JSONArray validMessageArray = new JSONArray();
        ObjectMapper mapper = new ObjectMapper();

        // needed this to get unit test working to support refactoring JSON out of the service layer
        // at which point won't need this to unit test service layer.
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        try {
            for (Message message : messageSource.allMessages()) {
                if (message.isValidToday()
                        && ((null == message.getAudienceFilter() || message.getAudienceFilter().matches(user) ) )
                    ) {
                    JSONObject validMessage = new JSONObject(message);
                    validMessageArray.put(validMessage);
                }
            }
            validMessages.put("messages", validMessageArray);
        } catch (Exception e) {
            logger.warn("Date filter failure ", e);
            return null;
        }
        return validMessages;
    }

    @Autowired
    public void setMessageSource(MessagesFromTextFile messageSource) {
        this.messageSource = messageSource;
    }
}
     