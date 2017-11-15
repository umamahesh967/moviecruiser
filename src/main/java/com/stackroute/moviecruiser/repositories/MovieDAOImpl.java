package com.stackroute.moviecruiser.repositories;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;

@Repository
public class MovieDAOImpl implements MovieDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	/**
	 * Save the movie in the database.
	 * 
	 */
	public boolean saveMovie(Movie movie) throws MovieNotFoundException {
		if (getSession().get(Movie.class, movie.getId()) != null) {
			throw new MovieNotFoundException("Could not save movie , Movie already exists!");
		}
		getSession().save(movie);
		return true;
	}

	/**
	 * update the movie in the database.
	 */
	public Movie updateMovieComments(Integer id, String comments) throws MovieNotFoundException {
		Movie movie = getSession().get(Movie.class, id);
		if (movie == null) {
			throw new MovieNotFoundException("Couldn't update movie. Movie not found!");
		}
		movie.setComments(comments);
		getSession().update(movie);
		return movie;
	}

	/**
	 * Delete the movie from the database.
	 */
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		Movie movie = getSession().get(Movie.class, id);
		if (movie == null) {
			throw new MovieNotFoundException("Could not delete , Movie not found!");
		}
		getSession().delete(movie);
		return true;
	}

	/**
	 * Return the movie having the passed id.
	 */
	public Movie getMovieById(int id) throws MovieNotFoundException {
		Movie movie = getSession().get(Movie.class, id);
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found!");
		}
		return movie;
	}
	/**
	 * Return the movie list of all movies.
	 */
	@GetMapping(path = "/all")
	public List<Movie> getAll() throws MovieNotFoundException {
		String sql = "SELECT * FROM movie";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addEntity(Movie.class);
		List results = query.list();
		if (results == null) {
			throw new MovieNotFoundException("Movie not found!");
		}
		return results;
	}
}
