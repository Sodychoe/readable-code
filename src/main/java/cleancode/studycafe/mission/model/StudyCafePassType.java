package cleancode.studycafe.mission.model;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석");

    StudyCafePassType(String description) {
        this.description = description;
    }

    private final String description;

    public boolean isSamePassType(StudyCafePassType passType) {
        return this == passType;
    }

}
