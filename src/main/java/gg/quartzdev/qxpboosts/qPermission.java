package gg.quartzdev.qxpboosts;

public enum qPermission {

    PLAYER("qxpboosts.player"),
    BOOST_DOUBLE("qxpboosts.boost.double"),
    BOOST_TRIPLE("qxpboosts.boost.triple");

    private String permission;

    qPermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return this.permission;
    }

}
