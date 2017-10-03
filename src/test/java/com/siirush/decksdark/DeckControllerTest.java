package com.siirush.decksdark;

import java.util.Scanner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

/*
Requirements:
·         Create a microservice that stores and shuffles card decks.
·         A card may be represented as a simple string such as “5-heart”, or “K-spade”.
·         A deck is an ordered list of 52 standard playing cards.
·         Expose a RESTful interface that allows a user to:
·         PUT an idempotent request for the creation of a new named deck.  New decks are created in some initial sorted order.
·         POST a request to shuffle an existing named deck.
·         GET a list of the current decks persisted in the service.
·         GET a named deck in its current sorted/shuffled order.
·         DELETE a named deck.
·         Design your own data and API structure(s) for the deck.
·         Persist the decks in-memory only, but stub the persistence layer such that it can be later upgraded to a durable datastore.
·         Implement a simple shuffling algorithm that simply randomizes the deck in-place.
·         Implement a more complex algorithm that simulates hand-shuffling, i.e. splitting the deck in half and interleaving the two halves, repeating the process multiple times.
·         Allow switching the algorithms at deploy-time only via configuration.
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeckControllerTest {
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private DeckController deckController;
    
    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-docs/snippets");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(documentationConfiguration(restDocumentation))
                                 .alwaysDo(document("deck"))
                                 .build();
    }
    
    @Test
    public void shouldBeAutowirable() {
        assertThat(deckController, not(nullValue()));
    }
    
    @Test
    public void shouldAcceptPutRequests() throws Exception {
        mockMvc.perform(put("/deck/test").content(resourceAsString("/validPutRequest.json")))
                .andExpect(status().isOk());
    }
    
    private String resourceAsString(String resourcePath) {
        try (Scanner scanner = new Scanner(getClass().getResourceAsStream(resourcePath), "UTF-8")) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
