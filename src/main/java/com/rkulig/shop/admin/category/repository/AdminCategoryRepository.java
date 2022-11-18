package com.rkulig.shop.admin.category.repository;

import com.rkulig.shop.admin.category.model.AdminCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {
}
