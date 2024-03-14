package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateRatingRequest;
import com.project.mdyshop.dto.request.ReplyRatingRequest;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Rating;
import com.project.mdyshop.model.User;

import java.util.List;

public interface RatingReviewService {

    Rating creatRatingAndReview(CreateRatingRequest request, User user);

    List<Rating> getAllRating(Long productId);

    List<Rating> getAllShopRating(Long shopId);

    Rating relyReview(Long ratingId, ReplyRatingRequest request);

}
