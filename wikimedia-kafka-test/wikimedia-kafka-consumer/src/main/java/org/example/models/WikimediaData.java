package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "WikimediaData")
@Getter
@Setter
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "wikiEventData",length = 3000)
    private String wikiEventData;

    @Override
    public String toString() {
        return "WikimediaData:" +
                "\n\tid= " + id +
                "\n\twikiEventData= '" + wikiEventData + '\'';
    }
}
