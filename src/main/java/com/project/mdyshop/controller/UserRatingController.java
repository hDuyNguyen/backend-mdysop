package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateRatingRequest;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Rating;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.RatingReviewService;
import com.project.mdyshop.service.ShopService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/rating")
public class UserRatingController {

    @Autowired
    UserService userService;
    @Autowired
    RatingReviewService ratingReviewService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Rating> createRating(@RequestHeader("Authorization")String jwt,
                                               @RequestBody CreateRatingRequest request)
            throws UserException {

        User user = userService.findUserByToken(jwt);
        Rating rating = ratingReviewService.creatRatingAndReview(request, user);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Rating>> getAllRatingProduct(@PathVariable Long productId) {
        List<Rating> ratings = ratingReviewService.getAllRating(productId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
