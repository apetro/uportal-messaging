package edu.wisc.my.messages.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.wisc.my.messages.model.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wisc.my.messages.service.MessagesService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Understands what HTTP requests are asking about messages, queries the
 * MessagesService accordingly, and replies in JSON.
 */
@Controller
public class MessagesController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private MessagesService messagesService;
    private IsMemberOfHeaderParser isMemberOfHeaderParser;

    /**
     * Messages filtered to the context of the request.
     *
     * Expects user group memberships as isMemberOf header with
     * semicolon-delimited values. Fails gracefully in absence of this header.
     *
     * The details of the filtering are NOT a semantically versioned aspect of
     * this API. That is, the microservice can get more sophisticated at
     * filtering and this will be considered a MINOR rather than MAJOR
     * (breaking) change.
     *
     * As currently implemented, EXCLUDES messages that are ANY of
     *
     * <ul>
     * <li>premature per not-before metadata on the message</li>
     * <li>expired per not-after metadata on the message</li>
     * <li>limited to groups none of which include the requesting user</li>
     * </ul>
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public @ResponseBody void currentMessages(HttpServletRequest request,
                                              HttpServletResponse response) {
        response.setContentType("application/json");

        String isMemberOfHeader = request.getHeader("isMemberOf");
        Set<String> groups = isMemberOfHeaderParser.groupsFromHeaderValue(isMemberOfHeader);
        User user = new User();
        user.setGroups(groups);

        JSONObject messages = messagesService.filteredMessages(user);
        try {
            response.getWriter().write(messages.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/allMessages", method=RequestMethod.GET)
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

    @Autowired
    public void setIsMemberOfHeaderParser(IsMemberOfHeaderParser isMemberOfHeaderParser) {
        this.isMemberOfHeaderParser = isMemberOfHeaderParser;
    }

}
