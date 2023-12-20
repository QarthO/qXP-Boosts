package gg.quartzdev.qxpboosts.util;

public enum Language {

//    Plugin info
    CONSOLE_PREFIX("<gray>[<red>q<aqua>XP-Boosts<gray>]"),
    CHAT_PREFIX("<red>q<aqua>XP-Boosts <bold><gray>></bold>"),
    PLUGIN_INFO("<prefix> <green>Running version <gray><version></gray>"),
    RELOAD_COMPLETE("<prefix> <green>Config reloaded"),

//    ERRORS
    ERROR_NO_PERMISSION("<prefix> <red>Error: You don't have access to that command"),
    ERROR_XP_SOURCE_NOT_FOUND("<prefix> <red>Error: xp source not found: '<yellow><xp-source></yellow>'"),
    ERROR_WORLD_NOT_FOUND("<prefix> <red>Error: World not found: '<yellow><world></yellow>'"),
    ERROR_BOOST_NOT_FOUND("<prefix> <red>Error: Boost not found: '<yellow><boost></yellow>'"),
    ERROR_CMD_NOT_FOUND("<prefix> <red>Error: Command not found: '<yellow><cmd></yellow>'"),

//    File
    ERROR_CREATE_FILE(""),
    ERROR_SAVE_FILE(""),
    FILE_CREATED("<green>Created file: '<yellow><file></yellow>'"),
    ERROR_BOOST_LOAD_EXCEPTION("<red>Error: Boost loading failed. Contact @ www.quartzdev.gg/discord"),

//    Syntax
    SYNTAX_ENABLE("<prefix> <red>Syntax: /<label> enable <boost>"),
    SYNTAX_DISABLE("<prefix> <red>Syntax: /<label> disable <boost>"),
    SYNTAX_LIST("<prefix> <red>Syntax: /<label> list"),
    SYNTAX_INFO("<prefix> <red>Syntax: /<label> info <boost>"),
    SYNTAX_RELOAD("<prefix> <red>Syntax: /<label> reload"),

//    Messages
    XP_ACTIONBAR_GAIN("<bold><green>Gained <blue><boost-multiplier>x <green>XP"),
    XP_CHAT_GAIN("<prefix> <green>Gained <blue><boost-multiplier>x <green>XP"),

//    Boost
    BOOST_ENABLED("<prefix> <green>Enabled boost: '<yellow><boost></yellow>'"),
    BOOST_DISABLED("<prefix> <green>Disabled boost: '<yellow><boost></yellow>'"),
    BOOST_INFO("<dark_purple>  <boost-name> <gray>-</gray> <green><boost-multiplier></green> <gray>-</gray> <boost-status>"),
    BOOST_STATUS_ERROR("<red>Error"),
    BOOST_STATUS_ACTIVE("<green>Active"),
    BOOST_STATUS_DISABLED("<red>Disabled");



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
