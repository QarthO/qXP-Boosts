package gg.quartzdev.qxpboosts;

public enum qPermission {

    PLAYER("qexpboosts.player"),
    BOOST_DOUBLE("qexpboosts.boost.double"),
    BOOST_TRIPLE("qexpboosts.boost.double");

    private String permission;

    qPermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return this.permission;
    }

}
