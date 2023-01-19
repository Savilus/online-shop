package pl.sda.pol122.auctionservice.entities;

public enum RatingEntity {

    ONE_STAR("one"),
    TWO_STARS("two"),
    THREE_STARS("three"),
    FOUR_STARS("four"),
    FIVE_STARS("five");

    private final String rating;

    RatingEntity(String rating) {
        this.rating = rating;
    }
}
