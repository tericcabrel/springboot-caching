package com.tericcabrel.springbootcaching.bootstrap;

import com.tericcabrel.springbootcaching.models.Category;
import com.tericcabrel.springbootcaching.models.Product;
import com.tericcabrel.springbootcaching.models.dtos.CreateCategoryDto;
import com.tericcabrel.springbootcaching.models.dtos.CreateProductDto;
import com.tericcabrel.springbootcaching.services.CategoryService;
import com.tericcabrel.springbootcaching.services.ProductService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private static final String CATEGORY_PHONE = "Phone";
    private static final String CATEGORY_COMPUTER = "Computer";
    private static final String CATEGORY_TV = "TV";
    private static final String CATEGORY_GAME = "Game";
    private static final String CATEGORY_TABLET = "Tablet";

    private final CategoryService categoryService;

    private final ProductService productService;

    public DataSeeder(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadCategories();

        this.loadProducts();
    }

    private void loadCategories() {
        String[] categories = new String[]{ CATEGORY_COMPUTER, CATEGORY_GAME, CATEGORY_PHONE, CATEGORY_TABLET, CATEGORY_TV };

        Arrays.stream(categories).forEach((value) -> {
            Optional<Category> optionalCategory = categoryService.findByName(value);

            optionalCategory.ifPresentOrElse(System.out::println, () -> {
                CreateCategoryDto categoryDto = new CreateCategoryDto();

                categoryDto.setName(value);

                categoryService.create(categoryDto);
            });
        });
    }

    private void loadProducts() {
        List<CreateProductDto> productDtoList = new ArrayList<>() {{
            add(new CreateProductDto(1L, "Macbook Pro 2022 14 Inch", "Product Description 1", 2279, true, CATEGORY_COMPUTER));
            add(new CreateProductDto(1L, "Macbook Air 2022 13 Inch", "Product Description  2", 1849, true, CATEGORY_COMPUTER));
            add(new CreateProductDto(1L, "Zenbook 14 Flip OLED (UP5401, 11th Gen Intel)", "Product Description 3", 1499, false, CATEGORY_COMPUTER));
            add(new CreateProductDto(1L, "MSI Titan GT77 12UGS-043FR", "Product Description 4", 3899, true, CATEGORY_COMPUTER));
            add(new CreateProductDto(1L, "Alienware x15 R2", "Product Description 5", 2189, false, CATEGORY_COMPUTER));
            add(new CreateProductDto(1L, "Macbook Pro 2021 16 inch", "Product Description 6", 2179, true, CATEGORY_COMPUTER));


            add(new CreateProductDto(1L, "Xbox Series X", "Product Description 7", 682, true, CATEGORY_GAME));
            add(new CreateProductDto(1L, "Xbox Series S", "Product Description 8", 559, true, CATEGORY_GAME));
            add(new CreateProductDto(1L, "PlayStation 5", "Product Description 9", 589, false, CATEGORY_GAME));
            add(new CreateProductDto(1L, "PlayStation 4 Pro", "Product Description 10", 499, false, CATEGORY_GAME));
            add(new CreateProductDto(1L, "Nintendo Switch", "Product Description 11", 310, true, CATEGORY_GAME));
            add(new CreateProductDto(1L, "Xbox Series One", "Product Description 12", 376, true, CATEGORY_GAME));

            add(new CreateProductDto(1L, "Apple Iphone 13", "Product Description 13", 849, true, CATEGORY_PHONE));
            add(new CreateProductDto(1L, "Apple Iphone XS Max", "Product Description 14", 699, true, CATEGORY_PHONE));
            add(new CreateProductDto(1L, "Xiaomi Redmi Note 11 Pro 5G", "Product Description 15", 599, true, CATEGORY_PHONE));
            add(new CreateProductDto(1L, "Samsung Galaxy S22", "Product Description 16", 799, false, CATEGORY_PHONE));
            add(new CreateProductDto(1L, "Google - Pixel 4a 5G", "Product Description 17", 451, true, CATEGORY_PHONE));
            add(new CreateProductDto(1L, "HUAWEI P30 Pro 256 Silver Frost", "Product Description 18", 750, true, CATEGORY_PHONE));
            add(new CreateProductDto(1L, "OPPO Find X3 Neo", "Product Description 19", 599, false, CATEGORY_PHONE));
            add(new CreateProductDto(1L, "Apple Iphone SE 2020", "Product Description 20", 429, true, CATEGORY_PHONE));


            add(new CreateProductDto(1L, "Samsung Galaxy Tab A8 10.5 Inch", "Product Description 21", 179, true, CATEGORY_TABLET));
            add(new CreateProductDto(1L, "2021 Apple iPad Pro 12.9 Inch", "Product Description 22", 1179, true, CATEGORY_TABLET));
            add(new CreateProductDto(1L, "Lenovo Tab P11 Plus", "Product Description 23", 269, false, CATEGORY_TABLET));
            add(new CreateProductDto(1L, "Xiaomi Pad 5 6+256 Black", "Product Description 24", 369, true, CATEGORY_TABLET));
            add(new CreateProductDto(1L, "2022 Apple iPad Air", "Product Description 25", 1024, true, CATEGORY_TABLET));
            add(new CreateProductDto(1L, "2021 Apple iPad mini", "Product Description 26", 786, false, CATEGORY_TABLET));

            add(new CreateProductDto(1L, "Sony BRAVIA KD55X80J 55 Inch", "Product Description 27", 586, true, CATEGORY_TV));
            add(new CreateProductDto(1L, "Xiaomi Smart TV P1E 55 Inch", "Product Description 28", 399, false, CATEGORY_TV));
            add(new CreateProductDto(1L, "SAMSUNG UE43TU7095 TV LED UHD 4K 43 Inch", "Product Description 29", 399, true, CATEGORY_TV));
            add(new CreateProductDto(1L, "HYUNDAI TV LED 32 Inch", "Product Description 30", 150, false, CATEGORY_TV));
            add(new CreateProductDto(1L, "Samsung UE55AU7172UXXH Smart TV 55 Inch", "Product Description 31", 1, true, CATEGORY_TV));
            add(new CreateProductDto(1L, "Hisense 43AE7000F TV LED 43 Inch", "Product Description 32", 349, true, CATEGORY_TV));
        }};

        productDtoList.forEach((createProductDto) -> {
            Optional<Product> optionalProduct = productService.findByName(createProductDto.getName());

            optionalProduct.ifPresentOrElse(System.out::println, () -> {
                Optional<Category> optionalCategory = categoryService.findByName(createProductDto.getCategoryName());

                optionalCategory.ifPresent((category) -> {
                    createProductDto.setCategoryId(category.getId());

                    productService.create(createProductDto);
                });
            });
        });
    }
}
