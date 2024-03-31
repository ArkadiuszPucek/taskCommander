//package com.arcadio.domain.taskStatus;
//
//import com.arcadio.domain.taskStatus.model.TaskStatus;
//import com.arcadio.domain.taskStatus.service.TaskTypeService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskTypeFacade {
//    private final TaskTypeService taskTypeService;
//
//    public TaskTypeFacade(TaskTypeService taskTypeService) {
//        this.taskTypeService = taskTypeService;
//    }
//
//    public List<TaskStatus> getAllTaskTypes(){
//        return taskTypeService.getAllTaskTypes();
//    }
//
//    public TaskStatus getTaskByTaskType(String genreType) {
//        return taskTypeService.getTaskByTaskType(genreType);
//    }
//
////    public List<TaskType> getTaskTypesWithMinimumSeries(int minSeriesCount) {
////        return genreService.getGenresWithMinimumSeries(minSeriesCount);
////    }
////
////    public List<Genre> getGenresWithMinimumMovies(int minMoviesCount) {
////        return genreService.getGenresWithMinimumMovies(minMoviesCount);
////    }
//}
