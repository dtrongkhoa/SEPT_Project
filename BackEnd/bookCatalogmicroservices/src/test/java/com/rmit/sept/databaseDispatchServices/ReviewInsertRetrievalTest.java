package com.rmit.sept.databaseDispatchServices;

import com.rmit.sept.bookCatalogApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.web.context.WebApplicationContext;

import javax.json.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = bookCatalogApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewInsertRetrievalTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void testSetup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void insertReviewUsingValidAccountTest() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("username", "test@test.com")
                                                    .add("title", "Test post")
                                                    .add("isbn", "014038572X")
                                                    .add("body", "Test body")
                                                    .add("rating", 5)
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/reviews/insert").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(201, mvcResult.getResponse().getStatus());
    }
 
    @Test
    public void likeReviewTest() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("uuid", "3")
                                                    .add("username", "test@test.com")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/reviews/like/").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    public void likeReviewTwiceTest() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("uuid", "3")
                                                    .add("username", "test@test.com")
                                                    .build();
        mvc.perform(post("/reviews/like/").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        // Like post after liking it
        MvcResult mvcResult = mvc.perform(post("/reviews/like/").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void dislikeValidReviewTest() throws Exception{
        JsonObject json = Json.createObjectBuilder().add("uuid", "1")
                                                    .add("username", "test@test.com")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/reviews/unlike/").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void dislikeInvalidReviewTest() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("uuid", "10")
                                                    .add("username", "test@test.com")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/reviews/unlike/").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

}