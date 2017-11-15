package com.stackroute.moviecruiser.services;

import java.util.List;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;

public interface MovieService {

	public boolean saveMovie(Movie movie) throws MovieNotFoundException;

	public Movie updateMovieComments(Integer id, String comments) throws MovieNotFoundException;

	public boolean deleteMovieById(int id) throws MovieNotFoundException;

	public Movie getMovieById(int id) throws MovieNotFoundException;

	public List<Movie> getAllMovies() throws MovieNotFoundException;

}
