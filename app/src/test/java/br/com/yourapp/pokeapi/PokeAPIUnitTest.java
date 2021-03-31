package br.com.yourapp.pokeapi;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.EOFException;
import java.io.IOException;
import java.net.HttpURLConnection;

import br.com.yourapp.pokeapi.di.api.PokeAPI;
import br.com.yourapp.pokeapi.models.PokemonSearchResponse;
import br.com.yourapp.pokeapi.screens.list.PokemonListContract;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PokeAPIUnitTest {

    MockWebServer mockWebServer;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);

        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getPokemonsWithLimitSuccessfulTest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("{\n" +
                "  \"count\": 1118,\n" +
                "  \"next\": \"https://pokeapi.co/api/v2/pokemon?offset=2&limit=1\",\n" +
                "  \"previous\": \"https://pokeapi.co/api/v2/pokemon?offset=0&limit=1\",\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"name\": \"ivysaur\",\n" +
                "      \"url\": \"https://pokeapi.co/api/v2/pokemon/2/\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"));

        PokeAPI service = retrofit.create(PokeAPI.class);
        Call<PokemonSearchResponse> call = service.getPokemonsWithLimit(1, 1);

        try {
            assertTrue(call.execute() != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPokemonsWithLimitFailTest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(""));

        PokeAPI service = retrofit.create(PokeAPI.class);

        try {
            Call<PokemonSearchResponse> call = service.getPokemonsWithLimit(1, 1);
            call.execute();
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertTrue(true);
        }
    }
}