package hu.flowacademy.Hearthstone.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import hu.flowacademy.Hearthstone.Model.Board;
import hu.flowacademy.Hearthstone.Model.Cards.Minion;
import hu.flowacademy.Hearthstone.Model.GameInstance;
import hu.flowacademy.Hearthstone.Model.GameModel;
import hu.flowacademy.Hearthstone.Repository.GameInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class WebSocketCommunicationService {

    // public Map<String, GameModel> gameModelMap = new HashMap<>();

    public Map<String, Board> boards = new HashMap<>();

    @Autowired
    Gson gson = new Gson();

    @Autowired
    ObjectMapper objmapper = new ObjectMapper();

    @Autowired
    GameInstanceRepository gameInstanceRepository;

    public String handleMessage(String message) {
        String answer = "update";
        String type = "";
        String username = "";
        Map<String, String> messageMap = new HashMap<>();
        // GameInstance gameInstance = new GameInstance();

        String convertedMessage = message.replaceAll("\\{", "").replaceAll("}", "").replaceAll("\"", "");
        System.out.println(convertedMessage);
        String[] messageArray = convertedMessage.split(",");
        System.out.println(Arrays.toString(messageArray));
        for (int i = 0; i < messageArray.length; i++) {
            String pair = messageArray[i];
            String[] keyAndValue = pair.split(":");
            messageMap.put(keyAndValue[0], keyAndValue[1]);
        }
        System.out.println("map: " + messageMap);
/*        Map<String, String> randommap = new HashMap<>();
        randommap.put("kecske", "naonkecske");
        System.out.println(randommap);*/

/*        System.out.println(message);
        String convertedMessage = message.replaceAll("\"", "").replaceAll("\\{", "").replaceAll("}", "");
        System.out.println(convertedMessage);
        String[] messageArray = convertedMessage.split(":");
        System.out.println(Arrays.toString(messageArray));*/

        if (messageMap.containsKey("type")) {
            if (messageMap.get("type").equals("endTurn")) {
                System.out.println("van benne type és az endTurn");
                type = "endTurn";
            } else if (messageMap.get("type").equals("endTurnp2")) {
                System.out.println("van benne type és az endTurn2");
                type = "endTurnp2";
            } else if (messageMap.get("type").equals("summon")) {
                System.out.println("van benne type és ez summon");
                type = "summon";
            } else if (messageMap.get("type").equals("summonp2")) {
                System.out.println("van benne type és ez a summonp2");
                type = "summonp2";
            }
        }
        if (messageMap.containsKey("username")) {
            System.out.println("van benen username: " + messageMap.get("username"));
            username = messageMap.get("username");
        }

/*        for (int i = 0; i < messageArray.length; i++) {
            if (messageArray[i].equals("type")) {
                System.out.println("van benne type");
                if (messageArray[i+1].equals("endTurn")) { ;
                    type = "endTurn";
                }
            }
            if (messageArray[i].equals("username")) {
                System.out.println("van benne username");
                username = messageArray[i+1];
            }
        }*/

        System.out.println("Local variable username: " + username);

        Board board = this.boards.get(username);
        GameInstance gameInstance = this.gameInstanceRepository.findByUsername(username);
        if (type.equals("endTurn")) {
/*            if (gameInstance.isP1Turn()) {
                gameInstance.setP1Turn(false);
                board.drawCardByPlayer2();
            } else {
                gameInstance.setP1Turn(true);
                board.drawCardByPlayer1();
            }*/
            board.drawCardByPlayer2();
            gameInstance.setP1Turn(false);
            board.setP1Turn(false);
        } else if (type.equals("endTurnp2")) {
            board.drawCardByPlayer1();
            gameInstance.setP1Turn(true);
            board.setP1Turn(true);
        } else if (type.equals("summon")) {
            if (board.isP1Turn()) {
                Minion minion = board.findMinionInPlayer1HandById(Integer.parseInt(messageMap.get("cardId")));
                board.summonMinionByPlayer1(minion, generateValidPlaceToSummon(board.getPlayer1Boardside()));
                board.getPlayer1Hand().remove(minion);
            }
        } else if (type.equals("summonp2")) {
            if (!board.isP1Turn()) {
                Minion minion = board.findMinionInPlayer2HandById(Integer.parseInt(messageMap.get("cardId")));
                board.summonMinionByPlayer2(minion, generateValidPlaceToSummon(board.getPlayer2Boardside()));
                board.getPlayer2Hand().remove(minion);
            }
        }
/*        GameInstance gameInstance = this.gameInstanceRepository.findByUsername(username);
        System.out.println(gameInstance == null);

        if (type.equals("endTurn")) {
            if (gameInstance.isP1Turn()) {
                gameInstance.setP1Turn(false);
                // gameInstance.getBoard().drawCardByPlayer2();
            } else {
                gameInstance.setP1Turn(true);
                // gameInstance.getBoard().drawCardByPlayer1();
            }
        }*/

        // System.out.println(gameInstance.getBoard() == null);
        // System.out.println(gameInstance.getBoard().getPlayer1Hand().size() + " és p2hand: " + gameInstance.getBoard().getPlayer2Hand().size());
        return answer;
    }

    public Integer generateValidPlaceToSummon(Minion[] boardside) {
        Integer random = new Random().nextInt(5);
        while (boardside[random] != null) {
            random = new Random().nextInt(5);
        }
        return random;
    }

/*    private String p1Endturn() {

    }*/
}
