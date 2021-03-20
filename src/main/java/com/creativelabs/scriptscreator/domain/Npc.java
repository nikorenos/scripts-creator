package com.creativelabs.scriptscreator.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "npcs")
public class Npc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "longtext")
    private String description;
    @JsonBackReference(value = "npc-camp")
    @ManyToOne(cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "camp_id")
    private Camp camp;
    private String trelloCardId;
    private String trelloCardUrl;
    private String attachmentUrl;

    public Npc(Long id, String name, String description, String trelloCardId, String trelloCardUrl, String attachmentUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.trelloCardId = trelloCardId;
        this.trelloCardUrl = trelloCardUrl;
        this.attachmentUrl = attachmentUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Npc npc = (Npc) o;

        if (id != null ? !id.equals(npc.id) : npc.id != null) return false;
        return name != null ? name.equals(npc.name) : npc.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
