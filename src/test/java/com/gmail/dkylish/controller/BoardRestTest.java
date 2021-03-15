package com.gmail.dkylish.controller;

import com.gmail.dkylish.entity.Board;
import com.gmail.dkylish.service.BoardService;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Comparator;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@Sql(scripts={"classpath:data/data.sql"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardRestTest {

    private int port=8080;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private BoardService boardService;

    @Autowired
    private WebApplicationContext context;

    private  MockMvc mvc;

    @Before
//    @WithMockUser(value = "admin", password = "admin")
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity()) // enable security for the mock set up
                .build();
    }
    @WithMockUser(roles="ADMIN")
    @Test
    public void createBoardTest() throws JSONException {
        List<Board> allBoardsWithoutRelations = boardService.getAllBoardsWithoutRelations();
        long lastId = allBoardsWithoutRelations.stream().map(Board::getId).max(Comparator.naturalOrder()).get() + 1;
        String name="Test3";
        HttpEntity<Board> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/board?name="+name),
                HttpMethod.POST, entity, String.class);

        String expected = "{id:"+lastId+",name:"+name+",columns:null}";

        JSONAssert.assertEquals(expected, response.getBody(),false);
    }
    @Test
    public void getAllBoardsTest() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/board"),
                HttpMethod.GET, entity, String.class);

        String expected = "[" +
                "    {" +
                "        id: 1," +
                "        name: Test1" +
                "    }," +
                "    {" +
                "        id: 2," +
                "        name: Test2" +
                "    }" +
                "]";

        JSONAssert.assertEquals(expected, response.getBody(),false);
    }



    @Test
    public void getBoardTest() throws JSONException {
        HttpEntity<Board> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/board/2"),
                HttpMethod.GET, entity, String.class);

        String expected = "{id:2,name:Test2}";

        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    @Test
    public void getFullBoardTest() throws JSONException {
        HttpEntity<Board> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/boardFull/2"),
                HttpMethod.GET, entity, String.class);

        String expected = "{id:2,name:Test2,columns:[{id:3,title:Italy,tasks: []}]}";


        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    @Test
    public void updateBoardTest() throws JSONException {
        Board board = new Board();
        board.setId(2L);
        board.setName("NewTest");
        HttpEntity<Board> entity = new HttpEntity<>(board, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/board/2"),
                HttpMethod.PUT, entity, String.class);

        String expected = "{id:2,name:NewTest}";

        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    @Test
    public void deleteBoardTest() throws JSONException {
        HttpEntity<Board> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/board/2"),
                HttpMethod.DELETE, entity, String.class);

        String expected = "{id: 2,name:Test2,columns: [{" +
                "            id: 3,title: Italy,tasks:[]}]}";

        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }



}
