package enums;

public enum DatasetType {
    CIRCLE("circle"),
    EXCLUSIVE_OR("xor"),
    GAUSSIAN("gauss"),
    SPIRAL("spiral");

    private final String type;

    DatasetType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
