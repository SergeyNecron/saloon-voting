package ru.study.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.study.springboot.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    Optional<Restaurant> getByName(String name);

    @Query("SELECT distinct r FROM Restaurant r " +
            "LEFT JOIN FETCH r.menus m " +
            "WHERE m.date = :date " +
            "ORDER BY r.name ASC")
    List<Restaurant> getAllRestaurantsWithMenuOnDate(LocalDate date);
}