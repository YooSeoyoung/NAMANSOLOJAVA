package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    @Query("SELECT t FROM ToDo t WHERE t.user.username = :username AND t.type = :type")
    List<ToDo> findAllByUsernameAndType(String username, String type);

    @Query("SELECT t FROM ToDo t WHERE t.id = :id AND t.user.username = :username")
    Optional<ToDo> findByIdAndUsername(Long id, String username);

    @Query("SELECT t FROM ToDo t WHERE t.user.username = :username AND t.startDate >= :baseDate ORDER BY t.startDate ASC")
    List<ToDo> findUpcomingTodosAll(String username, LocalDate baseDate); // 추가 조건 : t.type로 정렬 가능

    @Query("SELECT t FROM ToDo t WHERE t.user.username = :username AND t.startDate < :baseDate ORDER BY t.startDate DESC")
    List<ToDo> findPastTodosAll(String username, LocalDate baseDate);

    @Query("SELECT t FROM ToDo t WHERE t.user.username = :username AND t.startDate >= :baseDate AND t.type = :type ORDER BY t.startDate ASC")
    List<ToDo> findUpcomingTodos(String username, LocalDate baseDate, String type); // 추가 조건 : t.type로 정렬 가능

    @Query("SELECT t FROM ToDo t WHERE t.user.username = :username AND t.startDate < :baseDate AND t.type = :type ORDER BY t.startDate DESC")
    List<ToDo> findPastTodos(String username, LocalDate baseDate, String type);

    @Query("SELECT t FROM ToDo t WHERE t.user.username = :username")
    List<ToDo> findAllByUsername(String username);

    Boolean existsByUserUsernameAndStartDateAndTitle(String username, LocalDate startDate, String title);

    List<ToDo> findAllByTitleAndEditable(String title, Boolean editable);

    @Query("SELECT t FROM ToDo t WHERE :date BETWEEN t.startDate AND t.lastDate")
    List<ToDo> findByTargetDateInRange(@Param("date") LocalDate date);

    @Query("SELECT t FROM ToDo t WHERE t.user.username = :username AND t.editable = false AND t.type = 'ANNIVERSARY'")
    List<ToDo> findOfficialAnniversariesByUsername(String username);

    List<ToDo> findByStartDate(LocalDate startDate);
}
