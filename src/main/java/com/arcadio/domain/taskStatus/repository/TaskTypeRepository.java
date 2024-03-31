//package com.arcadio.domain.taskStatus.repository;
//
//import com.arcadio.domain.taskStatus.model.TaskStatus;
//import org.springframework.data.repository.CrudRepository;
//import pl.puccini.cineflix.domain.genre.model.Genre;
//
//import java.util.List;
//
//public interface TaskTypeRepository extends CrudRepository<TaskStatus, Long> {
//    List<TaskStatus> findAll();
//    TaskStatus findByTaskType(String genreType);
//    TaskStatus findByTaskTypeIgnoreCase(String genreType);
////    @Query("SELECT g FROM TaskType g WHERE (SELECT COUNT(s) FROM Series s WHERE s.genre = g) >= :minSeriesCount")
////    List<Genre> findGenresWithMinimumSeries(@Param("minSeriesCount") int minSeriesCount);
////    @Query("SELECT g FROM TaskType g WHERE (SELECT COUNT(s) FROM Movie s WHERE s.genre = g) >= :minMoviesCount")
////    List<Genre> findGenresWithMinimumMovie(int minMoviesCount);
//}
