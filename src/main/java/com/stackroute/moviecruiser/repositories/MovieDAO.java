package com.stackroute.moviecruiser.repositories;

import java.util.List;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;

public interface MovieDAO {

	public boolean saveMovie(Movie movie) throws MovieNotFoundException;

	public Movie updateMovieComments(Integer id, String comments) throws MovieNotFoundException;

	public boolean deleteMovieById(int id) throws MovieNotFoundException;

	public Movie getMovieById(int id) throws MovieNotFoundException;

	public List<Movie> getAll() throws MovieNotFoundException;
	
}
