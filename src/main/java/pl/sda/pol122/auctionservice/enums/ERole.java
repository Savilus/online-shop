package pl.sda.pol122.auctionservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {
    USER("role.user"),

    ADMIN("role.admin");

    private String roleName;

    public static ERole getByKey(String key) {
        if (key != null) {
            for (ERole role : ERole.values()) {
                if (role.getRoleName().equalsIgnoreCase(key)) {
                    return role;
                }
            }
        }
        return null;
    }
}
