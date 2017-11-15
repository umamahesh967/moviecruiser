package com.stackroute.moviecruiser.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiser.repositories.MovieDAOImpl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MovieDAOImplTest {

	private MovieDAOImpl movieDAOImpl;
	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;
	@Mock
	private Movie movie;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		movieDAOImpl = new MovieDAOImpl();
		movieDAOImpl.setSessionFactory(sessionFactory);
		Mockito.when(movieDAOImpl.getSession()).thenReturn(session);
		movie = new Movie(1, "POC", "good Movie", "www.abc.com", "2015-03-31", 45.5, 112);

	}

	@Test
	public void testMockCreation() {
		assertNotNull(movie);
		assertNotNull(session);
	}

	@Test
	public void testSaveMovie() throws Exception {
		when(movieDAOImpl.getSession().save(movie)).thenReturn(true);
		boolean flag = movieDAOImpl.saveMovie(movie);
		assertEquals("saving movie failed", true, flag);
		verify(movieDAOImpl.getSession(), times(1)).save(movie);
		verify(movieDAOImpl.getSession(), times(1)).get(Movie.class, movie.getId());
	}

	@Test
	public void testUpdateMovieComments() throws MovieNotFoundException {

		when(session.get(Movie.class, 1)).thenReturn(movie);
		doNothing().when(session).update(movie);
		Movie movie = movieDAOImpl.updateMovieComments(1, "hi");
		assertEquals("updating movie failed", "hi", movie.getComments());
		verify(movieDAOImpl.getSession(), times(1)).update(movie);
		verify(movieDAOImpl.getSession(), times(1)).get(Movie.class, movie.getId());
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		when(session.get(Movie.class, 1)).thenReturn(movie);
		doNothing().when(session).delete(movie);
		boolean flag = movieDAOImpl.deleteMovieById(1);
		assertEquals("deleting movie failed", true, flag);
		verify(movieDAOImpl.getSession(), times(1)).delete(movie);
		verify(movieDAOImpl.getSession(), times(1)).get(Movie.class, movie.getId());
	}

	@Test
	public void testGetMovieById() throws Exception {
		when(session.get(Movie.class, 1)).thenReturn(movie);
		Movie movie1 = movieDAOImpl.getMovieById(1);
		assertEquals("fetching movie by id failed", movie1, movie);
		verify(movieDAOImpl.getSession(), times(1)).get(Movie.class, movie.getId());
	}
}