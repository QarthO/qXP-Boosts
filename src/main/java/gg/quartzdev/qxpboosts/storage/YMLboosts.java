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
        Set<String> boostNames = this.config.getKeys(false);
        if(boostNames.isEmpty()){
//            should never get here
//            TODO: reset boosts.yml (maybe rename broken)
            return boosts;
        }

        for(String boostName : boostNames){
            Object boostData = this.config.get()

        }



        return boosts;
    }

    public void save(Boost boost){

    }

    public Boost load(String boostName){
        Boost boost = new Boost("", 1.0);
        return boost;
    }

    private @NotNull ConfigurationSection getBoostsSection(){
        ConfigurationSection configArenaSection = this.config.getConfigurationSection("boosts");
        if(configArenaSection == null) {
//            logger.error(Language.ERROR_READ_FILE.setFile(file.getName()));
            ConfigurationSection boostsSection = config.createSection("boosts");
            this.saveFile();
            return boostsSection;
        }
        Set<String> boostNames = (Set<String>) configArenaSection.getKeys(false);
        return configArenaSection;
    }


}
