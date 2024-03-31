package com.arcadio.domain.user.userTask.controller;

import com.arcadio.domain.UserUtils;
import com.arcadio.domain.user.userTask.UserTaskFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.puccini.cineflix.domain.UserUtils;
import pl.puccini.cineflix.domain.movie.dto.MovieDto;
import pl.puccini.cineflix.domain.series.main.series.seriesDto.SeriesDto;
import pl.puccini.cineflix.domain.user.userLists.UserListFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/library")
public class UserTasksController {
    private final UserUtils userUtils;
    private final UserTaskFacade userTaskFacade;

    public UserTasksController(UserUtils userUtils, UserTaskFacade userTaskFacade) {
        this.userUtils = userUtils;
        this.userTaskFacade = userTaskFacade;
    }


    @GetMapping
    public String viewList(@RequestParam(name = "filter", defaultValue = "all") String filter,
                           Authentication authentication,
                           Model model) {
        userUtils.addAvatarUrlToModel(authentication, model);
        Long userId = userUtils.getUserIdFromAuthentication(authentication);

        List<MovieDto> userMovies = userTaskFacade.getUserMovies(userId);
        List<SeriesDto> userSeries = userTaskFacade.getUserSeries(userId);

        if ("movies".equals(filter)) {
            model.addAttribute("activeAttribute", userMovies);
        } else if ("series".equals(filter)) {
            model.addAttribute("activeAttribute", userSeries);
        } else {
            List<Object> mixedResults = new ArrayList<>();
            mixedResults.addAll(userMovies);
            mixedResults.addAll(userSeries);
            Collections.shuffle(mixedResults);
            model.addAttribute("activeAttribute", mixedResults);
        }
        model.addAttribute("activeFilter", filter);

        return "/library";
    }

    @PostMapping("/add-to-list/{imdbId}")
    @ResponseBody
    public ResponseEntity<?> addItemToList(@PathVariable String imdbId, Authentication authentication) {
        try {
            Long userId = userUtils.getUserIdFromAuthentication(authentication);
            userTaskFacade.addItemToList(userId, imdbId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error message");
        }
    }

    @DeleteMapping("/remove-from-list/{imdbId}")
    public ResponseEntity<?> removeItemFromList(@PathVariable String imdbId, Authentication authentication) {
        try {
            Long userId = userUtils.getUserIdFromAuthentication(authentication);
            userTaskFacade.removeItemFromList(userId, imdbId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error message");
        }
    }
}
