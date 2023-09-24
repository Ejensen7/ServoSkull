package com.erikjensenworks.ServoSkull.AOS.Pojos;

import com.erikjensenworks.ServoSkull.AOS.enums.Faction;
import com.erikjensenworks.ServoSkull.AOS.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Name cannot be blank")
    String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Faction faction;

    @Column(nullable = false)
    @Min(value = 10, message = "Points should be at least 10")
    @Max(value = 2000, message = "Points should not exceed 2000")
    Integer points;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
