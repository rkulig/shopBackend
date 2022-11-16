package com.rkulig.shop.admin.controller.dto;

import com.rkulig.shop.admin.model.AdminProductCurrency;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Getter
public class AdminProductDto {
    @NotEmpty // cannot be empty, with @Length isn't necessary
    @NotBlank // cannot contain only white spaces
    @Length(min=4)
    private String name;
    @NotBlank
    @Length(min=4)
    private String category;
    @NotBlank
    @Length(min=4)
    private String description;
    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;
    private AdminProductCurrency currency;
    private String image;
    @NotBlank
    @Length(min=4)
    private String slug;
}
