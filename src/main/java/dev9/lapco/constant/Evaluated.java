package dev9.lapco.constant;

public enum Evaluated {
    PASS("PASS"),
    FAIL("FAIL");

    public final String result;

    private Evaluated(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result;
    }

}

