package com.shinhands.mu.Stationary.controller;

import com.shinhands.mu.Stationary.dto.RatingDTO;
import com.shinhands.mu.Stationary.entity.Rating;
import com.shinhands.mu.Stationary.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController("")
@RequestMapping(value="/rating")
public class RatingController {
    @Autowired
    RatingService RatingService;
    @RequestMapping(value="",method= RequestMethod.GET)
    public ResponseEntity getRating()
    {
        return ResponseEntity.ok().body(RatingService.getAllRatings());
    }
    @PostMapping()
    public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO categoryDTO)
    {
        return ResponseEntity.ok().body(RatingService.addRating(categoryDTO));
    }
    @GetMapping(value="/{id}")
    public ResponseEntity getRatingById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok().body(RatingService.getRatingById(id));
    }
    @PutMapping(value="/{id}")
    public ResponseEntity updateRating(@PathVariable(name="id") long id,@RequestBody RatingDTO categoryDTO)
    {
        return ResponseEntity.ok().body(RatingService.updateRating(id,categoryDTO));
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deleteRating(@PathVariable(name="id") long id,@RequestBody RatingDTO categoryDTO)
    {
        return ResponseEntity.ok().body(RatingService.updateRating(id,categoryDTO));
    }
}
