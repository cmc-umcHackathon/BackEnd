package com.example.hackathon.domain.point.entity;

public enum ProductType {

    ORGANIC_HANDKERCHIEF("오가닉 코튼 자수 손수건", 260),
    PENCIL_CASE("가죽 필통", 331),
    MINI_BACKPACK("미니 백팩", 582),
    KEYRING("키링", 221),
    POUCH("파우치", 356);

    private final String displayName;
    private final int point;

    ProductType(String displayName, int point) {
        this.displayName = displayName;
        this.point = point;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPoint() {
        return point;
    }
}
