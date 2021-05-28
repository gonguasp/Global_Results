package ctr.bot.globalresults.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ctr.bot.globalresults.dto.Rank3vs3Dto;
import ctr.bot.globalresults.dto.Rank4vs4Dto;
import ctr.bot.globalresults.dto.RankDuosDto;
import ctr.bot.globalresults.dto.RankFFADto;
import ctr.bot.globalresults.dto.RankItemless;
import ctr.bot.globalresults.entity.RankSchemas;

public interface RankRespository extends MongoRepository<RankSchemas, String> {

    @Query(value = "{ 'ffaPlayed' : { $ne: 0 }}", 
           fields = "{ ffa: 1, ffaPlayed: 1, ffaWon: 1, playerName: 1}")
    List<RankFFADto> findFFA();

    @Query(value = "{ 'duosPlayed' : { $ne: 0 }}", 
           fields = "{ duos: 1, duosPlayed: 1, duosWon: 1, playerName: 1}")
    List<RankDuosDto> findDuos();

    @Query(value = "{ '3vs3Played' : { $ne: 0 }}", 
           fields = "{ war3vs3: 1, war3vs3Played: 1, war3vs3Won: 1, playerName: 1}")
    List<Rank3vs3Dto> find3vs3();

    @Query(value = "{ '4vs4Played' : { $ne: 0 }}", 
           fields = "{ war4vs4: 1, war4vs4Played: 1, war4vs4Won: 1, playerName: 1}")
    List<Rank4vs4Dto> find4vs4();

    @Query(value = "{ 'itemlessPlayed' : { $ne: 0 }}", 
           fields = "{ itemless: 1, itemlessPlayed: 1, itemlessWon: 1, playerName: 1}")
    List<RankItemless> findItemless();

}
    
