package com.stackroute.moviecruiser.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exceptions.MovieNotFoundException;
import com.stackroute.moviecruiser.services.MovieService;

@Controller
@RequestMapping(path = "/v1.0/movieservice/movie")

@CrossOrigin(origins = "*")
public class MovieController {

	@Autowired
	private MovieService movieService;

	// <-- Save Methods -->
	@PostMapping
	public @ResponseBody ResponseEntity<?> saveNewMovie(@RequestBody Movie movie) {
		try {
			movieService.saveMovie(movie);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
	}

	// <-- Update Methods -->
	@PutMapping(path = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> updateMovie(@PathVariable("id") Integer id, @RequestBody String comments) {
		Movie movie;
		System.out.println(comments);
		try {
			movie = movieService.updateMovieComments(id, comments);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	// <-- delete Methods -->
	@DeleteMapping(value = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> deleteMovieById(@PathVariable("id") int id) {
		try {
			movieService.deleteMovieById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("movie deleted successfully", HttpStatus.OK);
	}

	// <-- Getter Methods -->
	@GetMapping(path = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> fetchMovieById(@PathVariable("id") int id) {
		Movie thisMovie;
		try {
			thisMovie = movieService.getMovieById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movie>(thisMovie, HttpStatus.OK);
	}
//	fetchAllMovies methods
	@GetMapping(path = "")
	public @ResponseBody ResponseEntity<?> fetchAllMovies(){
		List<Movie> movieList;
		try {
			movieList = movieService.getAllMovies();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Movie>>( movieList, HttpStatus.OK);
		
	}
}
