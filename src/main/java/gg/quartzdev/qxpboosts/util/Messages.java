package gg.quartzdev.qxpboosts.util;

public enum Messages {

//    Plugin info
    CONSOLE_PREFIX("<gray>[<red>q<aqua>XP-Boosts<gray>]"),
    CHAT_PREFIX("<red>q<aqua>XP-Boosts <bold><gray>></bold>"),
    PLUGIN_INFO("<prefix> <green>Running version <gray><version>"),
    RELOAD_COMPLETE("<prefix> <green>Config reloaded"),

//    ERRORS
    ERROR_NO_PERMISSION("<prefix> <red>Error: You don't have access to that command"),
    ERROR_XP_SOURCE_NOT_FOUND("<prefix> <red>Error: XP source not found: <yellow><xp-source>"),
    ERROR_MOB_SOURCE_NOT_FOUND("<prefix> <red>Error: Mob source not found: <yellow><mob-source>"),
    ERROR_WORLD_NOT_FOUND("<prefix> <red>Error: World not found: <yellow><world>"),
    ERROR_SOUND_NOT_FOUND("<prefix> <red>Error: Sound not found: <yellow><sound>"),
    ERROR_BOOST_NOT_FOUND("<prefix> <red>Error: Boost not found: <yellow><boost>"),
    ERROR_CMD_NOT_FOUND("<prefix> <red>Error: Command not found: <yellow><cmd>"),
    ERROR_CORRUPT_FILE("<prefix> <red>Error: Corrupt file: <yellow><file></yellow><newline>Please reset the file."),

//    File
    ERROR_CREATE_FILE("<prefix> Error creating file: <yellow><file>"),
    ERROR_SAVE_FILE("<prefix> Error saving file: <yellow><file>"),
    FILE_CREATED("<prefix> <green>Created file: <yellow><file>"),
    ERROR_BOOST_LOAD_EXCEPTION("<prefix> <red>Error: Loading boosts from file failed"),
    ERROR_PLAYER_ONLY("<prefix> <red>Error: You can't run this command from the console"),

//    Syntax
    SYNTAX_ENABLE("<prefix> <red>Syntax: /<label> enable <boost>"),
    SYNTAX_DISABLE("<prefix> <red>Syntax: /<label> disable <boost>"),
    SYNTAX_LIST("<prefix> <red>Syntax: /<label> list"),
    SYNTAX_INFO("<prefix> <red>Syntax: /<label> info <boost>"),
    SYNTAX_RELOAD("<prefix> <red>Syntax: /<label> reload"),
    SYNTAX_CREATE("<prefix> <red>Syntax: /<label> create <boost> <multiplier>"),
    SYNTAX_SET("<prefix> <red>Syntax: /<label> set <boost> <setting> <value>"),
    SYNTAX_SET_SETTING("<prefix> <red>Syntax: /<label> set <boost> <setting> <value>"),

//    Messages
    XP_ACTIONBAR_GAIN("<bold><green>Gained <blue><multiplier>x <green>XP"),
    XP_CHAT_GAIN("<prefix> <green>Gained <blue><multiplier>x <green>XP"),

//    Boost
    BOOST_ENABLED("<prefix> <green>Enabled boost: <light_purple><boost>"),
    BOOST_DISABLED("<prefix> <green>Disabled boost: <light_purple><boost>"),
    BOOST_STATUS_ERROR("<red>Error"),
    BOOST_STATUS_ACTIVE("<green>Active"),
    BOOST_STATUS_DISABLED("<red>Disabled"),
    BOOST_CREATE("<prefix> <green>Boost created: <yellow><boost>"),
    BOOST_DELETE("<prefix> <green>Boost deleted: <yellow><boost>"),
    ERROR_BOOST_ALREADY_ENABLED("<prefix> <red>Error: <light_purple><boost> <red>already enabled"),
    ERROR_BOOST_ALREADY_DISABLED("<prefix> <red>Error: <light_purple><boost> <red>already disabled"),
    ERROR_DELETE_DEFAULT("<prefix> <red>Error: You can't delete the default boost"),
    BOOST_SET_SETTING("<prefix> <yellow><setting><green> for <light_purple><boost></light_purple> has been set to <yellow><value>"),
    ERROR_READ_FILE("<prefix> <red>Error: Incorrect syntax in file: <yellow><file>"),
    INVENTORY_TITLE_SOURCES("<bold><boost></bold> <source-type>"),
    BOOST_LIST("<prefix> Available boosts (<boost-count>): <boost-list>"),
    ERROR_BOOST_ALREADY_EXISTS("<prefix> <red>Error: Boost already exists: <yellow><boost>"),
    BOOST_INFO("<prefix> <light_purple><boost><gray>: <status><newline>" +
            "<prefix> <blue>Multiplier<gray>: <gold><multiplier><newline>" +
            "<prefix> <blue>Chance<gray>: <gold><chance><newline>" +
            "<prefix> <blue>Notifications<gray>:<newline>" +
            "<prefix>  <gray>- <blue>Chat<gray>: <gold><chat><newline>" +
            "<prefix>  <gray>- <blue>Action Bar<gray>: <gold><action-bar><newline>" +
            "<prefix>  <gray>- <blue>Sound<gray>: <gold><sound><newline>" +
            "<prefix> <blue>XP-Sources<gray>: <green>" +
            "<hover:show_text:'<dark_purple>Runs: <red>/xpboosts set <boost> xpsources'><click:run_command:/xpboosts set <boost> xpsources>Click to view</click><newline>" +
            "<prefix> <blue>Mob-Sources<gray>: <green>" +
            "<hover:show_text:'<dark_purple>Runs: <red>/xpboosts set <boost> mobsources'><click:run_command:/xpboosts set <boost> mobsources>Click to view</click>");

    private final String message;
    private String parsedMessage;

    Messages(String msg){
        this.message = msg;
        this.parsedMessage = msg;
    }

    @Override
    public String toString(){
        return this.message;
    }

    public String get(){
        String result = this.parsedMessage;
        this.parsedMessage = this.message;
        return result;
    }

    public Messages parse(String placeholder, String value){
        this.parsedMessage = this.parsedMessage.replaceAll("<" + placeholder + ">", value);
        return this;
    }
}
