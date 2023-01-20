package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;


    @OneToMany(mappedBy = "categoryEntity")
    @Column(name = "products")
    private List<ProductEntity> listOfProducts;

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<ProductEntity> getListOfProducts() {
        return listOfProducts;
    }
}
