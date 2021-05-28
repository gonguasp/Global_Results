package ctr.bot.globalresults.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rankschemas")
public class RankSchemas {
    
    public int ffa;
    public int ffaPlayed;
    public int ffaWon;
    public int duos;
    public int duosPlayed;
    public int duosWon;
    public int war3vs3;
    public int war3vs3Played;
    public int war3vs3Won;
    public int war4vs4;
    public int war4vs4Played;
    public int war4vs4Won;
    public int itemless;
    public int itemlessPlayed;
    public int itemlessWon;
    public int captain;
    public int captainPlayed;
    public int captainWon;
    public String discordId;
    public String playerName;

    public RankSchemas() {}
}