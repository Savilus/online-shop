package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "category")
@Builder
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "categoryEntity")
    @Column(name = "products")
    private List<ProductEntity> listOfProducts;

    public CategoryEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getImage() {
        return image;
    }

    public List<ProductEntity> getListOfProducts() {
        return listOfProducts;
    }
}
