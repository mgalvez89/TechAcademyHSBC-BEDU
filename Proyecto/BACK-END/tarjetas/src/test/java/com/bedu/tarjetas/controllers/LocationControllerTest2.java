package com.bedu.tarjetas.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.entities.Request;
import com.bedu.tarjetas.helper.Status;
import com.bedu.tarjetas.services.ILocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationControllerTest2 {


    @MockBean
    private ILocationService service;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void allLocationsAPI() throws Exception {
        mockMvc.perform(get("/api/v1/locations"))
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    public void createLocationAPI() throws Exception
    {
        Location location = new Location();
        location.setIdLocation(800);
        location.setNameLocation("G-700");
        location.setCapacity(380);
        location.setSpaceUsed(0);
        location.setFreeSpace(380);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/v1/locations")
                        .content(asJsonString(location))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andDo(print());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateLocationAPI() throws Exception
    {
        Location location = new Location();
        location.setIdLocation(1);
        location.setNameLocation("G-700");
        location.setCapacity(380);
        location.setSpaceUsed(0);
        location.setFreeSpace(380);

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/v1/locations/6/400")
                        .content(asJsonString(location))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteLocationAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/api/v1/locations/{idLocation}", 19) )
                .andExpect(status().isAccepted());
    }


    @Test
    public void allPackageAPI() throws Exception {
        mockMvc.perform(get("/api/v1/packages"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(61))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].idPackage").value(1));

    }

    @Test
    public void createPackageAPI() throws Exception
    {
        Request request = new Request();
        request.setIdRequest(1);
        request.setTypeRequest("STORAGE");
        request.setStorageDate(new Date());
        request.setFileName("file.xlsx");
        request.setStatus(Status.SITUATED);
        request.setFilePath("/c:/fies");
        request.setRequestDate(new Date());
        request.setDeliveryDate(new Date());

        Location location = new Location();
        location.setIdLocation(1);
        location.setNameLocation("G-700");
        location.setCapacity(380);
        location.setSpaceUsed(0);
        location.setFreeSpace(380);

        Package aPackage = new Package();
        aPackage.setIdPackage(1);
        aPackage.setNamePackage("VD491566845_G1_I85_190716_15_IMPULSOO");
        aPackage.setNumberCards(200);
        aPackage.setStatus(Status.SITUATED);
        aPackage.setLocation(location);
        aPackage.setRequest(request);
        aPackage.setNumberCards(100);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/v1/packages")
                        .content(asJsonString(aPackage))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void updatePackageAPI() throws Exception
    {
        Request request = new Request();
        request.setIdRequest(1);
        request.setTypeRequest("STORAGE");
        request.setStorageDate(new Date());
        request.setFileName("file.xlsx");
        request.setStatus(Status.SITUATED);
        request.setFilePath("/c:/fies");
        request.setRequestDate(new Date());
        request.setDeliveryDate(new Date());

        Location location = new Location();
        location.setIdLocation(1);
        location.setNameLocation("G-700");
        location.setCapacity(380);
        location.setSpaceUsed(0);
        location.setFreeSpace(380);

        Package aPackage = new Package();
        aPackage.setIdPackage(1);
        aPackage.setNamePackage("VD491566845_G1_I85_190716_15_IMPULSOO");
        aPackage.setNumberCards(200);
        aPackage.setStatus(Status.SITUATED);
        aPackage.setLocation(location);
        aPackage.setRequest(request);
        aPackage.setNumberCards(100);

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/v1/packages/3/15")
                        .content(asJsonString(aPackage))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePackageAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/api/v1/packages/{idPackage}", 1))
                .andExpect(status().isAccepted());
    }

}