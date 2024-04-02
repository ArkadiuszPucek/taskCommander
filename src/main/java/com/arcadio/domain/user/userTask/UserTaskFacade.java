package com.arcadio.domain.user.userTask;

import com.arcadio.domain.user.userTask.service.UserTaskService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserTaskFacade {
    private final UserTaskService userTaskService;

    public UserTaskFacade(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

//    public List<MovieDto> getUserMovies(Long userId) {
//        return userListService.getUserMovies(userId);
//    }
//
//    public List<SeriesDto> getUserSeries(Long userId) {
//        return userListService.getUserSeries(userId);
//    }
//
//    public void addItemToList(Long userId, String imdbId) {
//        userListService.addItemToList(userId, imdbId);
//    }
//
//    public void removeItemFromList(Long userId, String imdbId) {
//        userListService.removeItemFromList(userId, imdbId);
//    }
//
//    public boolean isOnList(Long userId, String imdbId) {
//        return userListService.isOnList(userId, imdbId);
//    }
}
