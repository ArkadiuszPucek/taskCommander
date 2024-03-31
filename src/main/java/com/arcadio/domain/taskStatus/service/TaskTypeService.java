//package com.arcadio.domain.taskStatus.service;
//
//import com.arcadio.domain.taskStatus.model.TaskStatus;
//import com.arcadio.domain.taskStatus.repository.TaskTypeRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskTypeService {
//
//    private final TaskTypeRepository taskTypeRepository;
//
//    public TaskTypeService(TaskTypeRepository taskTypeRepository) {
//        this.taskTypeRepository = taskTypeRepository;
//    }
//
//    public List<TaskStatus> getAllTaskTypes(){
//        return taskTypeRepository.findAll();
//    }
//
//    public TaskStatus getTaskByTaskType(String taskType) {
//        return taskTypeRepository.findByTaskType(taskType);
//    }
//
////    public List<TaskType> getGenresWithMinimumSeries(int minSeriesCount) {
////        return taskTypeRepository.findGenresWithMinimumSeries(minSeriesCount);
////    }
////
////    public List<TaskType> getGenresWithMinimumMovies(int minMoviesCount) {
////        return taskTypeRepository.findGenresWithMinimumMovie(minMoviesCount);
////    }
//}
