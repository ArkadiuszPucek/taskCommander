package com.arcadio.domain.task.repository;

import com.arcadio.domain.task.model.Task;
import org.springframework.data.repository.CrudRepository;
import pl.puccini.cineflix.domain.genre.model.Genre;
import pl.puccini.cineflix.domain.movie.model.Movie;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
//    List<Movie> findAllByPromotedIsTrue();
    List<Movie> findAllByImdbId(String imdbId);
    List<Movie> findAllByGenre(Genre genre);
    List<Movie> findAll();
    Movie findByTitleIgnoreCase(String title);
    Optional<Movie> findMovieByImdbId(String imdbId);

    List<Movie> findByTitleContainingIgnoreCaseOrStaffContainingIgnoreCaseOrDirectedByContainingIgnoreCase(String title, String actor, String director);
    boolean existsByImdbId(String imdbId);
    int countMoviesByGenre(Genre genre);
}
