package com.stackroute.moviecruiser.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiser.App;
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.services.MovieService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

	private MockMvc mockMVC;

	@MockBean
	private MovieService movieService;

	@InjectMocks
	private MovieController movieController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private Movie movie;
	static List<Movie> movies;

	@Before
	public void setUp() {
		mockMVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		movies = new ArrayList<>();
		movie = new Movie(1, "POC", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112);
		movies.add(movie);
		movie = new Movie(2, "POC-2", "very good Movie", "www.cde.com", "2013-09-23", 65.5, 110);
		movies.add(movie);
	}

	@Test
	public void testSaveNewMovieSuccess() throws Exception {
		when(movieService.saveMovie(movie)).thenReturn(true);
		mockMVC.perform(post("/v1.0/movieservice/movie")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(movie)))
				.andExpect(status().isCreated())
				.andDo(print());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testUpdateMovieSuccess() throws Exception {
		when(movieService.updateMovieComments(1, "hi")).thenReturn(movies.get(0));
		mockMVC.perform(put("/v1.0/movieservice/movie/{id:[0-9]+}", 1)
				.content("hi"))
				.andExpect(status().isOk());
		verify(movieService, times(1)).updateMovieComments(1, "hi");
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		when(movieService.deleteMovieById(1)).thenReturn(true);
		mockMVC.perform(delete("/v1.0/movieservice/movie/{id:[0-9]+}", 1))
				.andExpect(status().isOk());
		verify(movieService, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);
	}

	@Test
	public void testFetchMovieById() throws Exception {
		when(movieService.getMovieById(1)).thenReturn(movies.get(0));
		mockMVC.perform(get("/v1.0/movieservice/movie/{id:[0-9]+}", 1))
				.andExpect(status().isOk());
		verify(movieService, times(1)).getMovieById(1);
		verifyNoMoreInteractions(movieService);
	}

	// Parsing String format data into JSON format
	private static String jsonToString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
