package ru.study.springboot.to;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.study.springboot.model.Menu;
import ru.study.springboot.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor // ! if no create: JsonUtil.java:24 (like default constructor, exist)
public class RestaurantOut {
    @NotNull
    private Integer id;
    @NotNull
    private String name;

    private String address;
    private Integer rating;
    private List<Menu> menus;

    public RestaurantOut(Restaurant restaurant, Integer rating) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.menus = restaurant.getMenus();
        this.rating = rating;
    }

    public RestaurantOut(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = null;
        this.menus = null;
    }
}