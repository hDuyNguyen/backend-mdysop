package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateRatingRequest;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Rating;
import com.project.mdyshop.model.User;

import java.util.List;

public interface RatingService {

    Rating creatRating(CreateRatingRequest request, User user) throws ProductException;

    List<Rating> getProductRating(Long productId) throws ProductException;

}
