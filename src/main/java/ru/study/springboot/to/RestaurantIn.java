package ru.study.springboot.to;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.study.springboot.model.AbstractBaseEntity;
import ru.study.springboot.model.Restaurant;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RestaurantIn extends AbstractBaseEntity {
    @NotNull
    String name;
    String address;

    public RestaurantIn(String name) {
        this.name = name;
        this.address = null;
    }

    public Restaurant toRestaurant() {
        return new Restaurant(null, name, address);
    }
}