package com.rkulig.shop.admin.repository;

import com.rkulig.shop.admin.model.AdminProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
