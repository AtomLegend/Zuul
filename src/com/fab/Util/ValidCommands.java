package com.fab.Util;

public enum ValidCommands {
    QUIT("quit"),
    HELP("help"),
    GO("go"),
    CHECK("check"),
    ATTACK("attack"),
    SPEAK("speak"),
    STATS("stats"); // TODO


    private final String value;

    ValidCommands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
