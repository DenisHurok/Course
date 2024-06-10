package com.example.course.service;

import com.example.course.model.Place;
import com.example.course.model.TypeOfPlace;
import com.example.course.repos.PlaceRepository;
import com.example.course.repos.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TypeRepository typeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public List<TypeOfPlace> getAllTypes() {
        return typeRepository.findAll();
    }

    public Optional<Place> getPlaceById(int id) {
        return placeRepository.findById(id);
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public Optional<Place> updatePlace(int id, Place placeDetails) {
        return placeRepository.findById(id).map(place -> {
            place.setName(placeDetails.getName());
            place.setRating(placeDetails.getRating());
            place.setMapUrl(placeDetails.getMapUrl());
            place.setTypes(placeDetails.getTypes());
            place.setTypeIds(placeDetails.getTypeIds());
            return placeRepository.save(place);
        });
    }

    public boolean deletePlace(int id) {
        return placeRepository.findById(id).map(place -> {
            placeRepository.delete(place);
            return true;
        }).orElse(false);
    }

    public List<Place> filterPlaces(String name, Double rating, Double typeId) {
        System.out.println(name+rating+typeId);
        List<Place> allPlaces = placeRepository.findAll();
        return allPlaces.stream()
                .filter(place -> (name == null || place.getName().contains(name))
                        && (rating == null || Math.abs(Double.parseDouble(place.getRating()) - rating) < 0.5)
                        && (typeId == null || (place.getTypes() != null && place.getTypes().stream().anyMatch(type -> type.getId() == typeId))))
                .collect(Collectors.toList());
    }
}
