package com.arcadio.domain.task.dto;

import pl.puccini.cineflix.domain.movie.model.Movie;

public class TaskDtoMapper {
    public static TaskDto map(Movie movie) {
        TaskDto movieDto = new TaskDto();
        movieDto.setImdbId(movie.getImdbId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setReleaseYear(movie.getReleaseYear());
        movieDto.setImageUrl(movie.getImageUrl());
        movieDto.setBackgroundImageUrl(movie.getBackgroundImageUrl());
        movieDto.setMediaUrl(movie.getMediaUrl());
        movieDto.setTimeline(movie.getTimeline());
        movieDto.setAgeLimit(movie.getAgeLimit());
        movieDto.setDescription(movie.getDescription());
        movieDto.setStaff(movie.getStaff());
        movieDto.setDirectedBy(movie.getDirectedBy());
        movieDto.setLanguages(movie.getLanguages());
        movieDto.setGenre(movie.getGenre().getGenreType());
        movieDto.setPromoted(movie.isPromoted());
        movieDto.setImdbRating(movie.getImdbRating());
        movieDto.setImdbUrl(movie.getImdbUrl());
        movieDto.setOnUserList(null);
        movieDto.setUserRating(null);
        movieDto.setRateUpCount(0);
        movieDto.setRateDownCount(0);
        return movieDto;
    }
}
