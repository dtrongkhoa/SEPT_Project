package com.rmit.sept.bk_loginservices;

import com.rmit.sept.ms_login;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ms_login.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserFunctionalityTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void testSetup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void registerAValidUser() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("fullName", "dummy data")
                                                    .add("password", "dummy data")
                                                    .add("confirmPassword", "dummy data")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    public void registerInvalidEmail() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata.com")
                                                    .add("fullName", "dummy data")
                                                    .add("password", "dummy data")
                                                    .add("confirmPassword", "dummy data")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void registerExistingEmail() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("fullName", "dummy data")
                                                    .add("password", "dummy data")
                                                    .add("confirmPassword", "dummy data")
                                                    .build();
        mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        MvcResult mvcResult = mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void registerDifferentPasswords() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("fullName", "dummy data")
                                                    .add("password", "dummy data")
                                                    .add("confirmPassword", "dummy data1")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void loginAValidUser() throws Exception {
        // Register a user beforehand
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("fullName", "dummy data")
                                                    .add("password", "dummy data")
                                                    .add("confirmPassword", "dummy data")
                                                    .build();
        mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("password", "dummy data")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void loginAnInvalidUser() throws Exception {
        // No registration
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("password", "dummy data")
                                                    .build();
        MvcResult mvcResult = mvc.perform(post("/api/users/login").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(401, mvcResult.getResponse().getStatus());
    }

    @Test
    public void editUserDetails() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("fullName", "dummy data")
                                                    .add("password", "dummy data")
                                                    .add("confirmPassword", "dummy data")
                                                    .build();
        mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        json = Json.createObjectBuilder().add("oldUser", Json.createObjectBuilder().add("username", "dummydata@dummydata.com").add("fullName", "dummy data").add("password", "dummy data").add("confirmPassword", "dummy data"))
                                         .add("newUser", Json.createObjectBuilder().add("username", "dummydata1@dummydata.com").add("fullName", "dummy data1").add("password", "dummy data1").add("confirmPassword", "dummy data1"))
                                         .build();
        MvcResult mvcResult = mvc.perform(post("/api/users/update").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
 
    @Test
    public void deleteUserUsingValidUsername() throws Exception {
        JsonObject json = Json.createObjectBuilder().add("username", "dummydata@dummydata.com")
                                                    .add("fullName", "dummy data")
                                                    .add("password", "dummy data")
                                                    .add("confirmPassword", "dummy data")
                                                    .build();
        mvc.perform(post("/api/users/register").contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn();
        MvcResult mvcResult = mvc.perform(delete("/api/users/delete/dummydata@dummydata.com")).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void deleteUserUsingInvalidUsername() throws Exception {
        MvcResult mvcResult = mvc.perform(delete("/api/users/delete/this name doesn't exist")).andReturn();
        Assert.assertEquals(404, mvcResult.getResponse().getStatus());
    }

}