package gg.quartzdev.qxpboosts;

public enum qPermission {

    PLAYER("qxpboosts.player"),
    BOOST("qxpboosts.boost.");

    private String permission;

    qPermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return this.permission;
    }

    public String boost(String boostName){
        return this.permission + boostName;
    }
}