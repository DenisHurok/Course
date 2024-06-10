package com.example.course.controller;

import com.example.course.model.Place;
import com.example.course.model.TypeOfPlace;
import com.example.course.repos.TypeRepository;
import com.example.course.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping
    public String getAllPlaces(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Double rating,
                               Model model) {
        List<Place> places = placeService.filterPlaces(name, rating, null);
        List<TypeOfPlace> types = placeService.getAllTypes();
        model.addAttribute("places", places);
        model.addAttribute("types", types);
        model.addAttribute("name", name);
        model.addAttribute("rating", rating);
        return "places";
    }

    @GetMapping("/{id}")
    public String getPlaceById(@PathVariable int id, Model model) {
        Optional<Place> place = placeService.getPlaceById(id);
        if (place.isPresent()) {
            model.addAttribute("place", place.get());
            return "place";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/addPlace")
    public String showCreateForm(Model model) {
        model.addAttribute("place", new Place());
        model.addAttribute("allTypes", placeService.getAllTypes());
        return "addPlace";
    }

    @PostMapping
    public String createPlace(@ModelAttribute Place place) {
        place.setMapUrl(extractIframeUrl(place.getMapUrl()));
        place.setTypes(place.getTypeIds().stream()
                .map(id -> typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found")))
                .collect(Collectors.toList()));
        placeService.createPlace(place);
        return "redirect:/places";
    }

    @GetMapping("/{id}/edit")
    public String showEditPlaceForm(@PathVariable("id") int id, Model model) {
        Place place = placeService.getPlaceById(id).orElseThrow(() -> new RuntimeException("Place not found"));
        List<TypeOfPlace> allTypes = typeRepository.findAll();
        model.addAttribute("place", place);
        model.addAttribute("allTypes", allTypes);
        return "editPlace";
    }

    @PostMapping("/{id}/edit")  // Add the {id} path variable here
    public String updatePlace(@PathVariable int id, @ModelAttribute Place placeDetails) {
        placeDetails.setMapUrl(extractIframeUrl(placeDetails.getMapUrl()));
        placeService.updatePlace(id, placeDetails);
        return "redirect:/places";
    }


    @PostMapping("/{id}/delete")
    public String deletePlace(@PathVariable int id) {
        placeService.deletePlace(id);
        return "redirect:/places";
    }

    public static String extractIframeUrl(String iframeHtml) {
        String regex = "src=\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(iframeHtml);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
