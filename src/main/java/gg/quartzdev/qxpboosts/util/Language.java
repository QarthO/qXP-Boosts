package gg.quartzdev.qxpboosts.util;

public enum Language {

//    Plugin info
    CONSOLE_PREFIX("<gray>[<red>q<aqua>XP-Boosts<gray>]"),
    CHAT_PREFIX("<red>q<aqua>XP-Boosts <bold><gray>></bold>"),
    PLUGIN_INFO("<prefix> <green>Running version <gray><version></gray>"),
    RELOAD_COMPLETE("<prefix> <green>Config reloaded"),

//    ERRORS
    ERROR_NO_PERMISSION("<prefix> <red>Error: You don't have access to that command"),
    ERROR_XP_SOURCE_NOT_FOUND("<prefix> <red>Error: XP source not found: <yellow><xp-source></yellow>"),
    ERROR_MOB_SOURCE_NOT_FOUND("<prefix> <red>Error: Mob source not found: <yellow><mob-source></yellow>"),
    ERROR_WORLD_NOT_FOUND("<prefix> <red>Error: World not found: <yellow><world></yellow>"),
    ERROR_SOUND_NOT_FOUND("<predix> <red>Error: Sound not found: <yellow>sound"),
    ERROR_BOOST_NOT_FOUND("<prefix> <red>Error: Boost not found: <yellow><boost></yellow>"),
    ERROR_CMD_NOT_FOUND("<prefix> <red>Error: Command not found: <yellow><cmd></yellow>"),
    ERROR_CORRUPT_FILE("<prefix> <red>Error: Corrupt file: <yellow><file></yellow><newline>Please reset the file."),

//    File
    ERROR_CREATE_FILE("<prefix> Error creating file: <yellow><file>"),
    ERROR_SAVE_FILE("<prefix> Error saving file: <yellow><file>"),
    FILE_CREATED("<prefix> <green>Created file: <yellow><file>"),
    ERROR_BOOST_LOAD_EXCEPTION("<prefix> <red>Error: Loading boosts from file failed"),

//    Syntax
    SYNTAX_ENABLE("<prefix> <red>Syntax: /<label> enable <boost>"),
    SYNTAX_DISABLE("<prefix> <red>Syntax: /<label> disable <boost>"),
    SYNTAX_LIST("<prefix> <red>Syntax: /<label> list"),
    SYNTAX_INFO("<prefix> <red>Syntax: /<label> info <boost>"),
    SYNTAX_RELOAD("<prefix> <red>Syntax: /<label> reload"),
    SYNTAX_CREATE("<prefix> <red>Syntax: /<label> create <boost> <multiplier>"),
    SYNTAX_SET("<prefix> <red>Syntax: /<label> set <boost> <setting> <value>"),
    SYNTAX_SET_MULTIPLIER("<prefix> <red>Syntax: /<label> set <boost> multiplier <multiplier>"),
    SYNTAX_SET_CHANCE("<prefix> <red>Syntax: /<label> set <boost> chance <chance>"),
    SYNTAX_SET_CHAT("<prefix> <red>Syntax: /<label> set <boost> chat <true/false>"),
    SYNTAX_SET_ACTIONBAR("<prefix> <red>Syntax: /<label> set <boost> actionbar <true/false>"),
    SYNTAX_SET_SOUND("<prefix> <red>Syntax: /<label> set <boost> sound <sound>"),

//    Messages
    XP_ACTIONBAR_GAIN("<bold><green>Gained <blue><multiplier>x <green>XP"),
    XP_CHAT_GAIN("<prefix> <green>Gained <blue><multiplier>x <green>XP"),

//    Boost
    BOOST_ENABLED("<prefix> <green>Enabled boost: <yellow><boost></yellow>"),
    BOOST_DISABLED("<prefix> <green>Disabled boost: <yellow><boost></yellow>"),
    BOOST_INFO_HEADER("<prefix> <blue>List of all loaded boosts"),
    BOOST_INFO_LINE("<dark_purple>    <name> <gray>- <gold><multiplier></gold> -</gray> <status>"),
    BOOST_STATUS_ERROR("<red>Error"),
    BOOST_STATUS_ACTIVE("<green>Active"),
    BOOST_STATUS_DISABLED("<red>Disabled"),
    BOOST_CREATE("<prefix> <green>Boost created: <yellow><name>"),
    BOOST_DELETE("<prefix> <green>Boost deleted: <yellow><name>"),
    ERROR_DELETE_DEFAULT("<prefix> <red>Error: You can't delete the default boost"),
    BOOST_SET_MULTIPLIER("<prefix> <green>Multiplier for <yellow><boost></yellow> has been set to <yellow><multiplier></yellow>"),
    ERROR_READ_FILE("<prefix> <red>Error: Incorrect syntax in file: <yellow><file>"),
    INVENTORY_TITLE_SOURCES("<bold><boost></bold> <source-type>");

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

    public Language parse(String placeholder, String value){
        this.message = this.message.replaceAll("<" + placeholder + ">", value);
        return this;
    }
}
