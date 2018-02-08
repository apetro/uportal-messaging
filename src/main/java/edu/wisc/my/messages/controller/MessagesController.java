package edu.wisc.my.messages.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wisc.my.messages.service.MessagesService;

@Controller
public class MessagesController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private MessagesService messagesService;
    
    @RequestMapping(value="/messages", method=RequestMethod.GET)
    public @ResponseBody void messages(HttpServletRequest request,
        HttpServletResponse response) {
            JSONObject json = messagesService.allMessages();
            response.setContentType("application/json");
            try {
                response.getWriter().write(json.toString());
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

    @RequestMapping(value = "/currentMessages", method = RequestMethod.GET)
    public @ResponseBody void currentMessages(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        JSONObject messages = messagesService.filteredMessages();
        try {
            response.getWriter().write(messages.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/")
    public @ResponseBody
    void index(HttpServletResponse response) {
        try {
            JSONObject responseObj = new JSONObject();
            responseObj.put("status", "up");
            response.getWriter().write(responseObj.toString());
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.error("Issues happened while trying to write Status", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    public void setMessagesService(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

}
