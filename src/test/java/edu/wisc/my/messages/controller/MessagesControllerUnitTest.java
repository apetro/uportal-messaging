package edu.wisc.my.messages.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.wisc.my.messages.model.User;
import edu.wisc.my.messages.service.MessagesService;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class MessagesControllerUnitTest {


  /**
   * Test that the controller reads isMemberOf header from the request, asking its
   * IsMemberOfHeaderParser about it.
   */
  @Test
  public void usesIsMemberOfHeader() {

    MessagesService mockService = mock(MessagesService.class);
    IsMemberOfHeaderParser mockParser = mock(IsMemberOfHeaderParser.class);

    MessagesController controller = new MessagesController();
    controller.setIsMemberOfHeaderParser(mockParser);
    controller.setMessagesService(mockService);

    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    JSONObject mockJsonObject = mock(JSONObject.class);

    when(mockRequest.getHeader("isMemberOf")).thenReturn("group1;group2;");
    when(mockService.filteredMessages(any())).thenReturn(mockJsonObject);

    controller.currentMessages(mockRequest, mockResponse);

    verify(mockRequest).getHeader("isMemberOf");
    verify(mockParser).groupsFromHeaderValue("group1;group2;");

  }

  /**
   * Test that the controller passes on to the Service the groups it learned from its
   * IsMemberOfHeaderParser.
   */
  @Test
  public void passesGroupsToService() {

    MessagesService mockService = mock(MessagesService.class);
    IsMemberOfHeaderParser mockParser = mock(IsMemberOfHeaderParser.class);

    MessagesController controller = new MessagesController();
    controller.setIsMemberOfHeaderParser(mockParser);
    controller.setMessagesService(mockService);

    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    JSONObject mockJsonObject = mock(JSONObject.class);

    when(mockService.filteredMessages(any())).thenReturn(mockJsonObject);
    Set<String> groups = new HashSet<>();
    groups.add("someGroup");
    groups.add("someOtherGroup");
    groups.add("yetAnotherGroup");
    when(mockParser.groupsFromHeaderValue(anyString())).thenReturn(groups);

    controller.currentMessages(mockRequest, mockResponse);

    ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
    verify(mockService).filteredMessages(argument.capture());
    assertEquals(groups, argument.getValue().getGroups());
  }

}
