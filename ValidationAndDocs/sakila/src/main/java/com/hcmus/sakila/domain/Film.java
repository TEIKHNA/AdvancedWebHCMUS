package com.hcmus.sakila.domain;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.domain.type.RatingTypeConverter;
import com.hcmus.sakila.domain.type.SpecialFeatureType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
=======
import com.hcmus.sakila.domain.type.MpaaRating;
>>>>>>> feat-CRUD-film
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
<<<<<<< HEAD
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
=======
>>>>>>> feat-CRUD-film

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "film")
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_gen")
    @SequenceGenerator(name = "film_id_gen", sequenceName = "film_film_id_seq", allocationSize = 1)
    @Column(name = "film_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Integer releaseYear;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @NotNull
    @ColumnDefault("3")
    @Column(name = "rental_duration", nullable = false)
    private Short rentalDuration;

    @NotNull
    @ColumnDefault("4.99")
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @NotNull
    @ColumnDefault("19.99")
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

<<<<<<< HEAD
    @Column(name = "rating", columnDefinition = "mpaa_rating")
    private RatingType rating;

    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private List<String> specialFeatures;
=======
    @ColumnDefault("'G'")
    @Column(name = "rating", columnDefinition = "mpaa_rating")
    @Enumerated(EnumType.STRING)
    private MpaaRating rating;

    @Column(name = "special_features", columnDefinition = "TEXT[]")
    private String[] specialFeatures;
>>>>>>> feat-CRUD-film

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

<<<<<<< HEAD

}

=======
    // public Set<SpecialFeatureType> getSpecialFeaturesSet() {
    // if (specialFeatures == null || specialFeatures.isEmpty()) {
    // return Set.of();
    // }
    // return Stream.of(specialFeatures.split(","))
    // .map(String::trim)
    // .map(SpecialFeatureType::fromString)
    // .collect(Collectors.toSet());
    // }
    //
    // public void setSpecialFeaturesSet(Set<SpecialFeatureType> features) {
    // this.specialFeatures = features.stream()
    // .map(SpecialFeatureType::toString)
    // .collect(Collectors.joining(","));
    // }

}
>>>>>>> feat-CRUD-film
