package gg.quartzdev.qxpboosts.storage;

import gg.quartzdev.qxpboosts.boost.Boost;
import gg.quartzdev.qxpboosts.util.Language;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class YMLboosts extends qYML{
    public YMLboosts(String fileName) {
        super(fileName);
    }

    public @NotNull Set<Boost> loadAll(){
        Set<Boost> boosts = new HashSet<>();
        Set<String> boostNames = this.getBoostsSection().getKeys(false);
        if(boostNames.isEmpty()){
//            should never get here
//            TODO: reset boosts.yml (maybe rename broken)
            return boosts;
        }

        for(String boostName : boostNames){
            Boost boost = load(boostName);
            if(boost != null){
                boosts.add(boost);
            }
        }

        return boosts;
    }

    public Boost load(String boostName){
        Object boostData = this.getBoostsSection().get(boostName);
        if(!(boostData instanceof Boost)){
            this.logger.error(Language.ERROR_BOOST_LOAD_EXCEPTION);
            return null;
        }
        Boost boost = (Boost) boostData;
        boost.initName(boostName);
        return boost;
    }

    public void save(Boost boost){
        String name = boost.getName();
        this.config.set("boosts." + name, boost);
        this.saveFile();
    }

    private @NotNull ConfigurationSection getBoostsSection(){
        ConfigurationSection configBoostSection = this.config.getConfigurationSection("boosts");
        if(configBoostSection == null) {
            ConfigurationSection boostsSection = config.createSection("boosts");
            this.saveFile();
            return boostsSection;
        }
        return configBoostSection;
    }


}
