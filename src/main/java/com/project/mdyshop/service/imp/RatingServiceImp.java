package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateRatingRequest;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Rating;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ProductRepository;
import com.project.mdyshop.repository.RatingRepository;
import com.project.mdyshop.service.ProductService;
import com.project.mdyshop.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImp implements RatingService {

    @Autowired
    ProductService productService;
    @Autowired
    RatingRepository ratingRepository;
    @Override
    public Rating creatRating(CreateRatingRequest request, User user) throws ProductException {

        Product product = productService.findProductById(request.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(request.getRating());
        rating.setReview(request.getReview());
        rating.setCreateAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) throws ProductException {
        return ratingRepository.findRatingByProductId(productId);
    }
}
