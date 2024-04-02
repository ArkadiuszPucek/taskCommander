package com.arcadio.domain.task.controller;

import com.arcadio.domain.UserUtils;
import com.arcadio.domain.task.TaskFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class TaskController {
    private final UserUtils userUtils;
    private final TaskFacade taskFacade;

    public TaskController(UserUtils userUtils, TaskFacade taskFacade) {
        this.userUtils = userUtils;
        this.taskFacade = taskFacade;
    }

//    @GetMapping("/{value}")
//    public String movieValueHandle(@PathVariable String value, Model model, Authentication authentication, HttpServletResponse response) {
//        userUtils.addAvatarUrlToModel(authentication, model);
//        Long userId = userUtils.getUserIdFromAuthentication(authentication);
//        String searchingFormatTitle = value.toLowerCase().replace("-", " ");
//
//        TaskDto taskDto = taskFacade.findMovieByTitle(searchingFormatTitle, userId);
//
//        if (movieDto != null) {
//            model.addAttribute("title", value);
//
//            List<MovieDto> movieByGenre = movieFacade.getMovieByGenre(movieDto.getGenre(), userId);
//            model.addAttribute("moviesByGenre", movieByGenre);
//
//            model.addAttribute("movie", movieDto);
//
//            response.setStatus(HttpServletResponse.SC_OK);
//            return "movie-title";
//        } else {
//            String capitalizedGenre = Character.toUpperCase(value.charAt(0)) + value.substring(1);
//            Genre genreByType = genreFacade.getGenreByType(capitalizedGenre);
//
//            if (genreByType != null) {
//                model.addAttribute("genre", capitalizedGenre);
//
//                List<MovieDto> moviesByGenre = movieFacade.getMovieByGenre(capitalizedGenre, userId);
//                model.addAttribute("moviesByGenre", moviesByGenre);
//
//                List<Genre> allGenres = genreFacade.getAllGenres();
//                List<Genre> filteredGenres = allGenres.stream()
//                        .filter(genre -> movieFacade.getNumberOfMoviesByGenre(genre) > 1).toList();
//                model.addAttribute("genres", filteredGenres);
//                response.setStatus(HttpServletResponse.SC_OK);
//
//                return "moviesByGenre";
//            }
//        }
//        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        return "error/not-found";
//    }
//
//    @GetMapping
//    public String moviesPage(Authentication authentication, Model model) {
//        userUtils.addAvatarUrlToModel(authentication, model);
//        Long userId = userUtils.getUserIdFromAuthentication(authentication);
//
//        List<Genre> allGenres = genreFacade.getAllGenres();
//        List<Genre> filteredGenres = allGenres.stream()
//                .filter(genre -> movieFacade.getNumberOfMoviesByGenre(genre) > 1)
//                .collect(Collectors.toList());
//        model.addAttribute("genres", filteredGenres);
//
//        List<MovieDto> allMoviesInService = movieFacade.findAllMovies(userId);
//        model.addAttribute("allMoviesInService", allMoviesInService);
//
//        return "movies";
//    }
//
//    @GetMapping("/play-movie/{imdbId}")
//    public String playMovie(@PathVariable String imdbId, Authentication authentication, Model model) {
//        userUtils.addAvatarUrlToModel(authentication, model);
//
//        MovieDto movieDto = movieFacade.getMovieDtoByImdbId(imdbId);
//
//        if (movieDto == null) {
//            return "error/not-found";
//        }
//
//        String youTubeUrl = mediaUtils.extractVideoId(movieDto.getMediaUrl());
//        model.addAttribute("mediaUrl", youTubeUrl);
//        model.addAttribute("movieTitle", movieDto.getTitle());
//        return "movie-player";
//    }


}
