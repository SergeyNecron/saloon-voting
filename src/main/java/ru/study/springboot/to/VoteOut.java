package ru.study.springboot.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.study.springboot.model.Vote;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor // ! if no create: JsonUtil.java:24 (like default constructor, exist)
public class VoteOut {
    @NotNull
    private Integer userId;
    @NotNull
    private LocalDate date;
    @NotNull
    private Integer restaurantId;
    @NotNull
    private String action;

    public VoteOut(Vote vote, boolean action) {
        this.userId = vote.id();
        this.date = vote.getDate();
        this.restaurantId = vote.getRestaurant().id();
        this.action = action ? "create" : "update";
    }
}