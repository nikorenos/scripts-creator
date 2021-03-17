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
    private String description;
    @OneToMany(targetEntity = Npc.class,
            mappedBy = "camp",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Npc> npcList;

    public Camp(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
