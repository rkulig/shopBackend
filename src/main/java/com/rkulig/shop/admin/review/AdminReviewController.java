package com.rkulig.shop.admin.review;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    @GetMapping
    public List<AdminReview> getReviews(){
        return adminReviewService.getReviews();
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        adminReviewService.deleteReview(id);
    }

    @PutMapping("/{id}/moderate")
    public void moderate(@PathVariable Long id){
        adminReviewService.moderate(id);
    }
}
