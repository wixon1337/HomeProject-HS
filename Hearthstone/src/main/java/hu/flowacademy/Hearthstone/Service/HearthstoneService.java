package hu.flowacademy.Hearthstone.Service;

import hu.flowacademy.Hearthstone.Model.Board;
import hu.flowacademy.Hearthstone.Model.Cards.Card;
import hu.flowacademy.Hearthstone.Model.Cards.Specific.ShroomBrewer;
import hu.flowacademy.Hearthstone.Model.Cards.Specific.SimpleMinion;
import hu.flowacademy.Hearthstone.Model.GameInstance;
import hu.flowacademy.Hearthstone.Model.PlayerMatches;
import hu.flowacademy.Hearthstone.Repository.GameInstanceRepository;
import hu.flowacademy.Hearthstone.Repository.PlayerMatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HearthstoneService {

    @Autowired
    private WebSocketCommunicationService wscService;

    @Autowired
    private GameInstanceRepository gameInstanceRepository;

    @Autowired
    private PlayerMatchesRepository playerMatchesRepository;

    public GameInstance initPlayer1(String username) {
        // GameModel gameModel = new GameModel(UUID.randomUUID().toString(), username); gameInstance was gameModel.getUserId() so it doesnt generate 2 different
        GameInstance gameInstance = new GameInstance(UUID.randomUUID().toString(), username); // gameModel.getUserId()
        gameInstanceRepository.save(gameInstance);
        PlayerMatches playerMatches = new PlayerMatches(gameInstance.getUserId());
        playerMatchesRepository.save(playerMatches);
        Long socketUrl = playerMatchesRepository.findOneByPlayer1(gameInstance.getUserId()).getWebSocketAddress();
        // gameModel.setSocketUrl(socketUrl);
        gameInstance.setSocketUrl(socketUrl);




/*        gameInstance.getBoard().getPlayer1Deck().add(new ShroomBrewer());
        gameInstance.getBoard().getPlayer1Deck().add(new ShroomBrewer());
        gameInstance.getBoard().getPlayer1Hand().add(new ShroomBrewer());
        gameInstance.getBoard().getPlayer1Hand().add(new ShroomBrewer());
        gameInstance.getBoard().getPlayer1Hand().add(new ShroomBrewer());
        gameInstance.getBoard().summonMinion(new ShroomBrewer(), 1);*/

        start(gameInstance, username);

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

    public void start(GameInstance gameInstance, String username) {
        Board board = gameInstance.getBoard();
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cardList.add(new SimpleMinion(2,3,3, false, false ,"test1"));
            cardList.add(new SimpleMinion(1,1,1,true,false,"test2"));
            cardList.add(new SimpleMinion(3,3,5,false,true, "test3"));
            cardList.add(new SimpleMinion(4,4,2,true,false,"test3"));
            cardList.add(new SimpleMinion(5,5,5,false,false, "test4"));
            cardList.add(new SimpleMinion(6,5,6,false,false, "test5"));
            cardList.add(new SimpleMinion(1,0,2,false,true, "test6"));
            cardList.add(new SimpleMinion(2,1,3,false,true,"test7"));
            cardList.add(new SimpleMinion(3, 2,3,false,true, "test8"));
            cardList.add(new ShroomBrewer());
        }

        for (Card c : cardList) {
            if ((new Random().nextInt(1) == 0 && cardList.size()/2 > board.getPlayer1Deck().size())) {
                board.getPlayer1Deck().add(c);
            } else {
                board.getPlayer2Deck().add(c);
            }
        }

        startingDraw(board);
        board.drawCardByPlayer1();
        this.wscService.boards.put(username, board);
    }
    
    public void startingDraw(Board board) {
        for (int i = 0; i < 3; i++) {
            board.drawCardByPlayer1();
            board.drawCardByPlayer2();
        }
        board.drawCardByPlayer2();
    }


}
