package com.arcadio.domain.task.service;

import com.arcadio.domain.task.TaskFactory;
import com.arcadio.domain.task.repository.TaskRepository;
import com.arcadio.domain.user.userTask.UserTaskFacade;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Collections;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskFactory taskFactory;
    private final UserTaskFacade userTaskFacade;
//    private final UserRatingFacade userRatingFacade;


    public TaskService(TaskRepository taskRepository, TaskFactory taskFactory, UserTaskFacade userTaskFacade) {
        this.taskRepository = taskRepository;
        this.taskFactory = taskFactory;
        this.userTaskFacade = userTaskFacade;
    }

//    public Movie addMovieByApiIfNotExist(MovieDto movieDto) throws IOException, InterruptedException {
//        if (existsByImdbId(movieDto.getImdbId())) {
//            throw new MovieAlreadyExistsException("The movie with the given IMDb id exists on the website!");
//        }
//        return addMovieByApi(movieDto);
//    }

//    public boolean existsByImdbId(String imdbId) {
//        return movieRepository.existsByImdbId(imdbId);
//    }

//    public void addMovieManual(MovieDto movieDto) {
//        Movie movie = movieFactory.createMovie(movieDto, null);
//        movieRepository.save(movie);
//    }
//
//    public Movie addMovieByApi(MovieDto movieDto) throws IOException, InterruptedException {
//        String type = imdbApiService.fetchIMDbForTypeCheck(movieDto.getImdbId());
//        if (type.equals("movie")){
//            MovieDto movieApiDto = imdbApiService.fetchIMDbDataForMovies(movieDto.getImdbId());
//            Movie movie = movieFactory.createMovie(movieApiDto, movieDto.isPromoted());
//            movieRepository.save(movie);
//            return movie;
//        }else {
//            throw new IncorrectTypeException("Incorrect imdbId type for movie");
//        }
//    }
//
//    public boolean updateMovie(MovieDto movieDto) {
//        Movie existingMovie = movieRepository.findMovieByImdbId(movieDto.getImdbId()).orElseThrow(()->new MovieNotFoundException("Movie not found"));
//        if (existingMovie != null) {
//            movieFactory.updateMovieWithDto(existingMovie, movieDto);
//            movieRepository.save(existingMovie);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean deleteMovieByImdbId(String imdbId) {
//        Movie movieByImdbId = movieRepository.findMovieByImdbId(imdbId).orElseThrow(()->new MovieNotFoundException("Movie not found"));
//        if (movieByImdbId != null) {
//            movieRepository.delete(movieByImdbId);
//            return true;
//        }
//        return false;
//    }
//
//    public List<MovieDto> getMovieByGenre(String genre, Long userId){
//        Genre genreByType = genreFacade.getGenreByType(genre);
//        List<MovieDto> moviesDtos = movieRepository.findAllByGenre(genreByType).stream()
//                .map(MovieDtoMapper::map)
//                .toList();
//        moviesDtos.forEach(movie -> {
//            movie.setOnUserList(userListFacade.isOnList(userId, movie.getImdbId()));
//            movie.setUserRating(userRatingFacade.getCurrentUserRatingForMovie(movie.getImdbId(), userId).orElse(null));
//        });
//        return moviesDtos;
//    }
//
//    public MovieDto findMovieByTitle(String title, Long userId) {
//        Movie movie = movieRepository.findByTitleIgnoreCase(title);
//        if (movie == null){
//            return null;
//        }
//        MovieDto mappedMovie = MovieDtoMapper.map(movie);
//        mappedMovie.setOnUserList(userListFacade.isOnList(userId, mappedMovie.getImdbId()));
//        mappedMovie.setUserRating(userRatingFacade.getCurrentUserRatingForMovie(mappedMovie.getImdbId(), userId).orElse(null));
//        return mappedMovie;
//    }
//
//    public List<MovieDto> findAllMoviesInService(Long userId){
//        List<MovieDto> allMoviesDto = movieRepository.findAll().stream()
//                .map(movie -> {
//                    MovieDto movieDto = MovieDtoMapper.map(movie);
//                    int rateUpCount = userRatingFacade.getRateUpCountForMovies(movie);
//                    int rateDownCount = userRatingFacade.getRateDownCountForMovies(movie);
//                    movieDto.setRateUpCount(rateUpCount);
//                    movieDto.setRateDownCount(rateDownCount);
//                    return movieDto;
//                })
//                .toList();
//
//        allMoviesDto.forEach(movie -> {
//            movie.setOnUserList(userListFacade.isOnList(userId, movie.getImdbId()));
//            movie.setUserRating(userRatingFacade.getCurrentUserRatingForMovie(movie.getImdbId(), userId).orElse(null));
//        });
//        return allMoviesDto;
//    }
//
//    public List<MovieDto> searchMovies(String query) {
//        String loweredQuery = query.toLowerCase();
//        if (query == null || query.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return movieRepository.findByTitleContainingIgnoreCaseOrStaffContainingIgnoreCaseOrDirectedByContainingIgnoreCase(loweredQuery , loweredQuery , loweredQuery).stream()
//                .map(MovieDtoMapper::map)
//                .toList();
//    }
//
//    public MovieDto findMovieByImdbId(String imdbId){
//        Movie movieByImdbId = movieRepository.findMovieByImdbId(imdbId).orElseThrow(()->new MovieNotFoundException("Movie not found"));
//        return MovieDtoMapper.map(movieByImdbId);
//    }
//
//    public Movie getMovieByImdbId(String imdbId){
//        return movieRepository.findMovieByImdbId(imdbId).orElseThrow(()->new MovieNotFoundException("Movie not found"));
//    }
//
//    public String getNormalizedMovieTitle(String title){
//        return title.toLowerCase().replace(' ', '-');
//    }
//
//    public int getNumberOfMoviesByGenre(Genre genreType) {
//        return movieRepository.countMoviesByGenre(genreType);
//    }
}
