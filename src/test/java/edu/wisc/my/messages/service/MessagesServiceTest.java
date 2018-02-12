package edu.wisc.my.messages.service;

import edu.wisc.my.messages.data.MessagesFromTextFile;
import edu.wisc.my.messages.model.AudienceFilter;
import edu.wisc.my.messages.model.Message;
import edu.wisc.my.messages.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class MessagesServiceTest {


    /**
     * Test that passes along all messages from repository.
     */
    @Test
    public void handsAlongAllMessagesFromRepository() {
        MessagesService service = new MessagesService();

        Message firstMessage = new Message();
        firstMessage.setId("uniqueMessageId-1");

        Message secondMessage = new Message();
        secondMessage.setId("anotherMessageId-2");

        List<Message> messagesFromRepository = new ArrayList<>();
        messagesFromRepository.add(firstMessage);
        messagesFromRepository.add(secondMessage);

        MessagesFromTextFile messageSource = mock(MessagesFromTextFile.class);
        when(messageSource.allMessages()).thenReturn(messagesFromRepository);

        service.setMessageSource(messageSource);

        JSONObject result = service.allMessages();

        // result should be a message array containing both messages

        assertNotNull(result);

        JSONArray resultMessages = result.getJSONArray("messages");
        assertNotNull(resultMessages);
        assertEquals(2, resultMessages.length());

        JSONObject firstResultMessage = resultMessages.getJSONObject(0);
        assertEquals("uniqueMessageId-1", firstResultMessage.getString("id"));

        JSONObject secondResultMessage = resultMessages.getJSONObject(1);
        assertEquals("anotherMessageId-2", secondResultMessage.getString("id"));
    }


    /**
     * Test that filters away messages with AudienceFilters reporting no match.
     */
    @Test
    public void includesOnlyMessagesMatchingAudienceFilters() {
        MessagesService service = new MessagesService();

        AudienceFilter yesFilter = mock(AudienceFilter.class);
        when(yesFilter.test(any())).thenReturn(true);
        Message matchingMessage = new Message();
        matchingMessage.setAudienceFilter(yesFilter);
        matchingMessage.setId("uniqueMessageId");

        AudienceFilter noFilter = mock(AudienceFilter.class);
        when(noFilter.test(any())).thenReturn(false);
        Message unmatchingMessage = new Message();
        unmatchingMessage.setAudienceFilter(noFilter);

        List<Message> unfilteredMessages = new ArrayList<>();
        unfilteredMessages.add(matchingMessage);
        unfilteredMessages.add(unmatchingMessage);

        MessagesFromTextFile messageSource = mock(MessagesFromTextFile.class);
        when(messageSource.allMessages()).thenReturn(unfilteredMessages);

        service.setMessageSource(messageSource);

        User user = new User();

        JSONObject result = service.filteredMessages(user);

        assertNotNull(result);

        JSONArray resultMessages = result.getJSONArray("messages");
        assertNotNull(resultMessages);

        assertEquals(1, resultMessages.length());

        JSONObject resultMessage = resultMessages.getJSONObject(0);

        assertEquals("uniqueMessageId", resultMessage.getString("id"));
    }

}
