package hu.flowacademy.Hearthstone.Service;

import hu.flowacademy.Hearthstone.Model.Cards.Specific.ShroomBrewer;
import hu.flowacademy.Hearthstone.Model.GameInstance;
import hu.flowacademy.Hearthstone.Model.GameModel;
import hu.flowacademy.Hearthstone.Model.PlayerMatches;
import hu.flowacademy.Hearthstone.Repository.GameInstanceRepository;
import hu.flowacademy.Hearthstone.Repository.PlayerMatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HearthstoneService {

    @Autowired
    private GameInstanceRepository gameInstanceRepository;

    @Autowired
    private PlayerMatchesRepository playerMatchesRepository;

    public GameInstance initPlayer1(String username) {
        GameModel gameModel = new GameModel(UUID.randomUUID().toString(), username);
        GameInstance gameInstance = new GameInstance(gameModel.getUserId(), username);
        gameInstanceRepository.save(gameInstance);
        PlayerMatches playerMatches = new PlayerMatches(gameInstance.getUserId());
        playerMatchesRepository.save(playerMatches);
        Long socketUrl = playerMatchesRepository.findOneByPlayer1(gameInstance.getUserId()).getWebSocketAddress();
        gameModel.setSocketUrl(socketUrl);

        gameInstance.getBoard().getPlayer1Deck().add(new ShroomBrewer());
        gameInstance.getBoard().getPlayer1Deck().add(new ShroomBrewer());

        return gameInstance;
    }

    public String getUserName(String userId) {
        return gameInstanceRepository.findOneByUserId(userId).getUsername();
    }

    public List<GameInstance> getAllGameInstances() {
        return gameInstanceRepository.findAll();
    }

    public String getPlayer2Name(Long socketId) {
        return playerMatchesRepository.findOneByWebSocketAddress(socketId).getPlayer2();
    }

    public void start() {
        // TODO
    }
}
