package com.creativelabs.scriptscreator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "camps")
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(targetEntity = Npc.class,
            mappedBy = "camp",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Npc> npcs;
}
