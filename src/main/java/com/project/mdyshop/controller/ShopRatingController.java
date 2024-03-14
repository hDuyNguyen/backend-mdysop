package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.ReplyRatingRequest;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/rating")
public class ShopRatingController {

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    RatingReviewService ratingReviewService;

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getShopRatings(@RequestHeader("Authorization")String jwt) throws UserException {

        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        List<Rating> ratings = ratingReviewService.getAllShopRating(shop.getId());

        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> replyRating(@PathVariable Long ratingId,
                                              @RequestBody ReplyRatingRequest request) {
        Rating rating = ratingReviewService.relyReview(ratingId, request);

        return new ResponseEntity<>(rating, HttpStatus.OK);
    }
}
