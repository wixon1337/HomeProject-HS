package hu.flowacademy.Hearthstone.Resource;

import com.google.gson.Gson;
import hu.flowacademy.Hearthstone.Model.GameInstance;
import hu.flowacademy.Hearthstone.Model.GameModel;
import hu.flowacademy.Hearthstone.Repository.GameInstanceRepository;
import hu.flowacademy.Hearthstone.Repository.PlayerMatchesRepository;
import hu.flowacademy.Hearthstone.Service.HearthstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HearthstoneResource {

    private final Gson jsonParser = new Gson();
/*    private GameInstanceRepository gameInstanceRepository;
    private PlayerMatchesRepository playerMatchesRepository;

    @Autowired
    public HearthstoneResource(Gson jsonParser, GameInstanceRepository gameInstanceRepository, PlayerMatchesRepository playerMatchesRepository) {
        this.jsonParser = jsonParser;
        this.gameInstanceRepository = gameInstanceRepository;
        this.playerMatchesRepository = playerMatchesRepository;
    }*/

    @Autowired
    private HearthstoneService hearthstoneService;

    @RequestMapping("/getAll")
    public String getAllGameInstances() { // for later implementation of Score board and for testing purposes TODO
        return jsonParser.toJson(hearthstoneService.getAllGameInstances());
    }

    @RequestMapping("/newgame/{username}")
    public GameInstance initPlayer1 (@PathVariable("username") String username) {
        return hearthstoneService.initPlayer1(username);
    }

    @RequestMapping("/getUsername/{userId}")
    public String getUserName(@PathVariable String userId) { // sneaky throws
        return hearthstoneService.getUserName(userId);
    }

    @RequestMapping("/getPlayer2Id/{socketId}")
    public String getPlayer2Name(@PathVariable("socketId") Long socketId) {
        return hearthstoneService.getPlayer2Name(socketId);
    }
}
