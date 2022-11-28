package com.rkulig.shop.admin.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdminReviewService {

    private final AdminReviewRepository adminReviewRepository;
    public List<AdminReview> getReviews() {
        return adminReviewRepository.findAll();
    }

    public void deleteReview(Long id) {
        adminReviewRepository.deleteById(id);
    }

    @Transactional
    public void moderate(Long id) {
        adminReviewRepository.moderate(id);
    }
}
