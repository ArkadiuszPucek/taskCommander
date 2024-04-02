package com.arcadio.domain.task.repository;

import com.arcadio.domain.attachment.model.Attachment;
import com.arcadio.domain.company.model.Company;
import com.arcadio.domain.customer.model.Customer;
import com.arcadio.domain.task.model.Task;
import com.arcadio.domain.user.userDetails.model.User;
import com.arcadio.domain.user.userTask.model.UserTask;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Company findCompanyById(Long companyId);

    Customer findCustomerById(Long customerId);

    User findUserById(Long responsibleUserId);

    Attachment findAttachmentById(Long attachmentId);

    UserTask findUserTaskById(Long userTaskId);


//    List<Movie> findAllByPromotedIsTrue();
//    List<Movie> findAllByImdbId(String imdbId);
//    List<Movie> findAllByGenre(Genre genre);
//    List<Movie> findAll();
//    Movie findByTitleIgnoreCase(String title);
//    Optional<Movie> findMovieByImdbId(String imdbId);
//
//    List<Movie> findByTitleContainingIgnoreCaseOrStaffContainingIgnoreCaseOrDirectedByContainingIgnoreCase(String title, String actor, String director);
//    boolean existsByImdbId(String imdbId);
//    int countMoviesByGenre(Genre genre);
}
