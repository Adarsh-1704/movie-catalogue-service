package com.codezone.moviecatalogueservice.MovieList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovieInfo {
    Integer movieId;
    String movieName;
    Integer year;
}