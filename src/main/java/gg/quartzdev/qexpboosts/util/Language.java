package gg.quartzdev.qexpboosts.util;

public enum Language {
    CONSOLE_PREFIX("<gray>[<red>q<aqua>ExpBoosts<gray>]"),
    CHAT_PREFIX("<red>q<aqua>ExpBoosts <bold><gray>></bold>"),
    PLUGIN_INFO("<prefix> <green>Running version <gray><version></gray>"),
    RELOAD_COMPLETE("<prefix> <green>Reload complete!"),
    ERROR_NO_PERMISSION("<prefix> <red>Error: You don't have access to that command"),
    ERROR_SPAWN_REASON_NOT_FOUND("<prefix> <red>Error: Exp Spawn reason not found: '<yellow><spawn-reason></yellow>'"),
    ERROR_WORLD_NOT_FOUND("<prefix> <red>Error: World not found: '<yellow><world></yellow>'"),
    ERROR_MOB_NOT_FOUND("<prefix> <red>Error: Mob not found: '<yellow><mob></yellow>'");

    private String message;

    Language(String msg){
        this.message = msg;
    }

    @Override
    public String toString(){
        return this.message;
    }

    public String get(){
        return this.toString();
    }

    public String parse(String placeholder, String value){
        return message.replaceAll("<" + placeholder + ">", value);
    }
}
