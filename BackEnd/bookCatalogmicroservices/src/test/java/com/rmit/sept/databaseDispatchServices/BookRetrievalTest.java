package com.rmit.sept.databaseDispatchServices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.rmit.sept.bookCatalogApplication;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = bookCatalogApplication.class)
@AutoConfigureMockMvc
public class BookRetrievalTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void searchForValidBook() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/books/searchResults/just listen")).andExpect(jsonPath("$.[0].title").value("Just Listen")).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void searchForInvalidBook() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/books/searchResults/this book does not exist")).andExpect(jsonPath("$.*", Matchers.empty())).andReturn();
        Assert.assertEquals(204, mvcResult.getResponse().getStatus());
    }

}
