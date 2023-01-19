package pl.sda.pol122.auctionservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluation_of_transaction")
public class TransactionEvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "orderEntity", referencedColumnName = "id")
    private OrderEntity orderEntity;

    @OneToOne
    @JoinColumn(name = "sellerUserEntity", referencedColumnName = "sellerUserEntity")
    private OrderEntity sellerOrderEntity;

    @OneToOne
    @JoinColumn(name = "buyerUserEntity", referencedColumnName = "buyerUserEntity")
    private OrderEntity buyerOrderEntity;

    @Enumerated(EnumType.STRING)
    private RatingEntity rating;

    @Column(name = "comment", nullable = false, length = 250)
    private String comment;



}
