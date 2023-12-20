package gg.quartzdev.qxpboosts.boost;

import gg.quartzdev.qxpboosts.util.Language;

public class BoostLoadException extends Exception{

    private static final long serialVersionUID = 1L;

    public BoostLoadException(String boostName) {
        super(Language.ERROR_BOOST_LOAD_EXCEPTION.toString());
    }

    public BoostLoadException(final Throwable throwable) {
        super(Language.ERROR_BOOST_LOAD_EXCEPTION.toString(), throwable);
    }

}
