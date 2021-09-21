package com.company.vmtours.controller;

import com.company.vmtours.service.TourService;
import com.company.vmtours.testhelper.TourTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourControllerTest {

    private MockMvc mockMvc;

    private final TourTestHelper tourTestHelper = new TourTestHelper();

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private TourService tourService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "admin", password = "admin12", roles = "ADMIN")
    @Test
    public void shouldRefreshTours() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/tours/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tourTestHelper.getFilterAsJson(null));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @WithMockUser(value = "john", password = "john12", roles = "USER")
    @Test
    public void shouldReturnTours() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/tours")
                .contentType(MediaType.APPLICATION_JSON)
                .param("filter", tourTestHelper.getFilterAsJson(null));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void shouldReturnUnauthorized() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/tours")
                .contentType(MediaType.APPLICATION_JSON)
                .param("filter", tourTestHelper.getFilterAsJson(null));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void shouldReturnForbidden() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/tours")
                .contentType(MediaType.APPLICATION_JSON)
                .param("filter", tourTestHelper.getFilterAsJson(null));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
    }

}
