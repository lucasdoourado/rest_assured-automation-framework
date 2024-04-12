package br.ce.lada.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class OlaMundoTeste {

    @Test
    public void testOlaMundo() {
        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(response.statusCode() == 200);

    }

    @Test
    public void devoConhecerOutrasFormasRestAssured() {
        // Modo Fluente
        given() // pré condições
        .when() // ação
                .get("https://restapi.wcaquino.me/ola")
        .then() // assertivas
                .statusCode(200);
    }

    @Test
    public void devoConhecerMatchersHamcrest(){
        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1, 3, 5, 7, 9));
        assertThat(impares, hasItem(1));
        assertThat(impares, hasItems(5, 3));
    }

    @Test
    public void devoValidarBody(){
        given()
        .when()
                .get("https://restapi.wcaquino.me/ola")
        .then()
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(not(nullValue())));
    }
}
