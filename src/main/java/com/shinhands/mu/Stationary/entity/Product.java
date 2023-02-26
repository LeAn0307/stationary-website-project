package com.shinhands.mu.Stationary.entity;

import com.shinhands.mu.Stationary.dto.HistoryOrderDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
@NamedNativeQuery(
        name = "find_all_product",
        query = "SELECT p.*, c.categories_name " +
                "FROM product p " +
                "JOIN categories c ON p.categoryid = c.categoriesid " +
                "WHERE p.deleted = 0 ",
        resultSetMapping = "product_dto"
)
@NamedNativeQuery(
        name = "find_one_product",
        query = "SELECT p.*, c.categories_name " +
                "FROM product p " +
                "JOIN categories c ON p.categoryid = c.categoriesid " +
                "WHERE p.deleted = 0 AND p.id = ?1",
        resultSetMapping = "product_dto"
)
@SqlResultSetMapping(
        name = "product_dto",
        classes = @ConstructorResult(
                targetClass = ProductDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "type", type = String.class),
                        @ColumnResult(name = "discount", type = Long.class),
                        @ColumnResult(name = "avgrating", type = Long.class),
                        @ColumnResult(name = "material", type = String.class),
                        @ColumnResult(name = "categoryid", type = Long.class),
                        @ColumnResult(name = "height", type = Long.class),
                        @ColumnResult(name = "width", type = Long.class),
                        @ColumnResult(name = "weight", type = Long.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "image", type = String.class),
                        @ColumnResult(name = "brand", type = String.class),
                        @ColumnResult(name = "madein", type = String.class),
                        @ColumnResult(name = "amount", type = Long.class),
                        @ColumnResult(name = "color", type = String.class),
                        @ColumnResult(name = "price", type = BigDecimal.class),
                        @ColumnResult(name = "categories_name", type = String.class)
                }
        )
)
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name = "name",length = 255)
    private String name;
    @Column(name = "type",length = 255)
    private String type;
    @Column(name = "discount")
    private Long discount;
    @Column(name = "avgrating")
    private Long avgrating = 0L;
    @Column(name = "material",length = 255)
    private String material;
    @Column(name = "category_id")
    private Long categoryid;
    @Column(name = "height")
    private Long height;
    @Column(name = "width")
    private Long width;
    @Column(name = "weight")
    private Long weight;
    @Column(name = "description",length = 255)
    private String description;
    @Column(name = "image",length = 255)
    private String image;
    @Column(name = "brand",length = 255)
    private String brand;
    @Column(name = "made_in",length = 255)
    private String madein;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "color",length = 255)
    private String color;
    @Column(name="price")
    private BigDecimal price;
    @Column(name = "deleted")
    private Long deleted = 0L;


//    @ManyToOne
//    @JoinColumn(name = "cart_product", nullable = false, referencedColumnName = "categoriesid")
//    private Categories categories;
}
