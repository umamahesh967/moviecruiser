package com.stackroute.moviecruiser.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "comments")
	private String comments;

	@Column(name = "poster_path")
	private String posterPath;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "vote_average")
	private Double voteAverage;

	@Column(name = "vote_count")
	private int voteCount;

	public Movie() {

	}

	public Movie(int id, String name, String comments, String posterPath, String releaseDate, Double voteAverage,
			int voteCount) {
		super();
		this.id = id;
		this.name = name;
		this.comments = comments;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

}
