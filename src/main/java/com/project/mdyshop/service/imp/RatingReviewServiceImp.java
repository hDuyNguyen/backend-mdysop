package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateRatingRequest;
import com.project.mdyshop.dto.request.ReplyRatingRequest;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.Rating;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ProductRepository;
import com.project.mdyshop.repository.RatingRepository;
import com.project.mdyshop.service.ProductService;
import com.project.mdyshop.service.RatingReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingReviewServiceImp implements RatingReviewService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating creatRatingAndReview(CreateRatingRequest request, User user) {
        Optional<Product> opt = productRepository.findById(request.getProductId());

        if (opt.isPresent()) {
            Rating rating = new Rating();

            rating.setProduct(opt.get());
            rating.setUser(user);
            rating.setRating(request.getRating());
            rating.setReply(rating.getReply());
            rating.setCreateAt(LocalDateTime.now());

            return ratingRepository.save(rating);
        }

        return null;
    }

    @Override
    public List<Rating> getAllRating(Long productId) {
        return ratingRepository.findRatingByProductId(productId);
    }

    @Override
    public List<Rating> getAllShopRating(Long shopId) {
        return ratingRepository.findRatingByShopId(shopId);
    }

    @Override
    public Rating relyReview(Long ratingId, ReplyRatingRequest request) {
        Optional<Rating> opt = ratingRepository.findById(ratingId);

        if (opt.isPresent()) {
            Rating rating = opt.get();

            rating.setReply(request.getReply());

            return ratingRepository.save(rating);
        }
        return null;
    }
}
