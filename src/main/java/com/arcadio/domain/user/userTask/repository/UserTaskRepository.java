package com.arcadio.domain.user.userTask.repository;

import com.arcadio.domain.user.userTask.model.UserTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends CrudRepository<UserTask, Long> {

    List<UserTask> findByUserId(Long userId);
//    void deleteByUserIdAndImdbId(Long userId, String imdbId);
//    void deleteByImdbId(String imdbId);
    void deleteUserById(Long userId);

//    boolean existsByUserIdAndImdbId(Long userId, String imdbId);
}
