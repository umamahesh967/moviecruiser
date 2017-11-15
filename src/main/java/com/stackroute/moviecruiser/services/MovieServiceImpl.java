package com.stackroute.moviecruiser.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiser.repositories.MovieDAO;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDAO movieDAO;

	/**
	 * Save the movie.
	 * 
	 */
	public boolean saveMovie(Movie movie) throws MovieNotFoundException {
		movieDAO.saveMovie(movie);
		return true;
	}

	/**
	 * update the movie.
	 */
	public Movie updateMovieComments(Integer id, String comments) throws MovieNotFoundException {
		return movieDAO.updateMovieComments(id, comments);

	}

	/**
	 * Delete the movie.
	 */
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		return movieDAO.deleteMovieById(id);
	}

	/**
	 * Return the movie having the passed id.
	 */
	public Movie getMovieById(int id) throws MovieNotFoundException {
		return movieDAO.getMovieById(id);
	}

	@Override
	public List<Movie> getAllMovies() throws MovieNotFoundException{
		
		return movieDAO.getAll();
	}

}
