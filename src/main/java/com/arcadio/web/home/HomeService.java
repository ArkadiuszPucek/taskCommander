package com.arcadio.web.home;

import com.arcadio.domain.user.userTask.UserTaskFacade;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    private final UserTaskFacade userTaskFacade;

    public HomeService(UserTaskFacade userTaskFacade) {
        this.userTaskFacade = userTaskFacade;
    }

//    public Object getRandomPromotedItem(Long userId) {
//        List<MovieDto> allPromotedMovies = promotionItemFacade.findAllPromotedMovies();
//        allPromotedMovies.forEach(movie -> movie.setOnUserList(userListFacade.isOnList(userId, movie.getImdbId())));
//
//        List<SeriesDto> allPromotedSeries = promotionItemFacade.findAllPromotedSeries();
//        allPromotedSeries.forEach(series -> {
//            series.setOnUserList(userListFacade.isOnList(userId, series.getImdbId()));
//            EpisodeDto firstUnwatchedEpisode = episodeFacade.findFirstUnwatchedEpisode(series.getImdbId(), userId);
//            series.setFirstUnwatchedEpisodeId(firstUnwatchedEpisode != null ? firstUnwatchedEpisode.getId() : null);
//        });
//
//
//        List<Object> promotedItems = new ArrayList<>();
//        promotedItems.addAll(allPromotedMovies);
//        promotedItems.addAll(allPromotedSeries);
//
//        int size = promotedItems.size();
//        if (size > 0) {
//            int randomIndex = ThreadLocalRandom.current().nextInt(size);
//            return promotedItems.get(randomIndex);
//        } else {
//            return null;
//        }
//
//    }
//
//    public List<SeriesCarouselConfigDto> getSeriesCarouselsByActiveGenres(Long userId) {
//        List<String> activeGenres = carouselFacade.getSelectedGenresForSeries();
//        List<SeriesCarouselConfigDto> carousels = new ArrayList<>();
//
//        for (String genre : activeGenres) {
//            if (!genre.isEmpty()) {
//                SeriesCarouselConfigDto config = new SeriesCarouselConfigDto();
//                config.setGenre(genre);
//                config.setSeries(seriesFacade.getSeriesByGenre(genre, userId));
//                config.setActive(true);
//                carousels.add(config);
//            }
//        }
//        return carousels;
//    }
//
//    public List<MoviesCarouselConfigDto> getMoviesCarouselsByActiveGenres(Long userId) {
//        List<String> activeGenres = carouselFacade.getSelectedGenresForMovies();
//
//        List<MoviesCarouselConfigDto> carousels = new ArrayList<>();
//
//        for (String genre : activeGenres) {
//            if (!genre.isEmpty()) {
//                MoviesCarouselConfigDto config = new MoviesCarouselConfigDto();
//                config.setGenre(genre);
//                config.setMovies(movieFacade.getMovieByGenre(genre, userId));
//                config.setActive(true);
//                carousels.add(config);
//            }
//        }
//        return carousels;
//    }

}
