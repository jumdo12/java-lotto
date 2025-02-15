package domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    NONE("", 0L, 0, false),
    FIFTH("3개 일치 (5000원)- ", 5000L, 3, false),
    FOURTH("4개 일치 (50000원)- ", 50000L, 4, false),
    THIRD("5개 일치 (1500000원)- ", 1500000L, 5, false),
    SECOND("5개 일치, 보너스 볼 일치(30000000원)- ", 30000000L, 5, true),
    FIRST("6개 일치 (2000000000원)- ", 2000000000L, 6, false);

    private final String message;
    private final long prize;
    private final int count;
    private final boolean bonusMatch;

    Rank(String message, long prize, int count, boolean bonusMatch) {
        this.message = message;
        this.prize = prize;
        this.count = count;
        this.bonusMatch = bonusMatch;
    }

    public static Rank fromResult(int matchCount, boolean contains) {
        List<Rank> ranks = Arrays.stream(Rank.values()).toList().reversed();
        return ranks.stream()
                .filter(rank -> rank.getCount() == matchCount && (!rank.isBonusMatch() || contains))
                .findFirst()
                .orElse(NONE);
    }

    public String getMessage() {
        return message;
    }

    public Long getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }
}
