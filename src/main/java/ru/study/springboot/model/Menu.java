package ru.study.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "date", "restaurant_id"}, name = "unique_menu_date_restaurant_idx")})
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "restaurant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends AbstractNamedEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Meal> meals;

    public Menu(Integer id, String name, LocalDate date, List<Meal> meals) {
        super(id, name);
        this.meals = meals;
        this.date = date;
        meals.forEach(it -> it.setMenu(this));
    }

    public Menu(String name, LocalDate date, Restaurant restaurant, List<Meal> meals) {
        this(null, name, date, restaurant, meals);
    }

    public Menu(Integer id, String name, LocalDate date, Restaurant restaurant, List<Meal> meals) {
        super(id, name);
        this.restaurant = restaurant;
        this.meals = meals;
        this.date = date;
        meals.forEach(it -> it.setMenu(this));
    }


    public Menu(String name, LocalDate date, List<Meal> meals) {
        this(null, name, date, meals);
    }

    public Menu(String name, List<Meal> meals) {
        this(name, LocalDate.now(), meals);
    }

    public void setMeals(List<Meal> meals) {
        this.meals.clear();
        this.meals.addAll(meals);
        meals.forEach(it -> it.setMenu(this));
    }

    @Override
    public String toString() {
        return "{" +
                " id = " + id +
                ", name= " + name +
                ", date= " + date +
                ", meals= " + meals +
                '}';
    }
}
