package hu.flowacademy.Hearthstone.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import hu.flowacademy.Hearthstone.Model.Board;
import hu.flowacademy.Hearthstone.Model.Cards.Minion;
import hu.flowacademy.Hearthstone.Model.GameInstance;
import hu.flowacademy.Hearthstone.Model.GameModel;
import hu.flowacademy.Hearthstone.Model.Heroes.Hero;
import hu.flowacademy.Hearthstone.Model.Heroes.Paladin;
import hu.flowacademy.Hearthstone.Model.Heroes.Warlock;
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
            } else if (messageMap.get("type").equals("attack")) {
                System.out.println("van benne type és az attack");
                type = "attack";
            }
        }
        if (messageMap.containsKey("username")) {
            System.out.println("van benen username: " + messageMap.get("username"));
            username = messageMap.get("username");
        }

        System.out.println("Local variable username: " + username);

        Board board = this.boards.get(username);
        GameInstance gameInstance = this.gameInstanceRepository.findByUsername(username);
        if (type.equals("endTurn")) {
            if (board.getPlayer2Hand().size() >= 6) {
                board.burnCardByPlayer2();
            } else {
                board.drawCardByPlayer2();
            }
            gameInstance.setP1Turn(false);
            board.setP1Turn(false);
            board.setPlayer2Mana(board.getMaxMana());
            board.setMaxMana(board.getMaxMana() + 1);
            for (int i = 0; i < board.getPlayer1Boardside().length; i++) {
                if (board.getPlayer1Boardside()[i] != null) {
                    board.getPlayer1Boardside()[i].setSummoned(true);
                }
            }
        } else if (type.equals("endTurnp2")) {
            if (board.getPlayer1Hand().size() >= 6) {
                board.burnCardByPlayer1();
            } else {
                board.drawCardByPlayer1();
            }
            gameInstance.setP1Turn(true);
            board.setP1Turn(true);
            board.setPlayer1Mana(board.getMaxMana());
            for (int i = 0; i < board.getPlayer2Boardside().length; i++) {
                if (board.getPlayer2Boardside()[i] != null) {
                    board.getPlayer2Boardside()[i].setSummoned(true);
                }
            }
        } else if (type.equals("summon")) {
            if (board.isP1Turn()) {
                Minion minion = board.findMinionInPlayer1HandById(Integer.parseInt(messageMap.get("cardId")));
                if (minion.getCost() <= board.getPlayer1Mana()) {
                    board.summonMinionByPlayer1(minion, generateValidPlaceToSummon(board.getPlayer1Boardside()));
                    board.getPlayer1Hand().remove(minion);
                    board.setPlayer1Mana(board.getPlayer1Mana() - minion.getCost());
                }
            }
        } else if (type.equals("summonp2")) {
            if (!board.isP1Turn()) {
                Minion minion = board.findMinionInPlayer2HandById(Integer.parseInt(messageMap.get("cardId")));
                if (minion.getCost() <= board.getPlayer2Mana()) {
                    board.summonMinionByPlayer2(minion, generateValidPlaceToSummon(board.getPlayer2Boardside()));
                    board.getPlayer2Hand().remove(minion);
                    board.setPlayer2Mana(board.getPlayer2Mana() - minion.getCost());
                }
            }
        } else if (type.equals("attack")) {
            if (board.isP1Turn()) {
                Minion selectedMinion = board.findMinionInPlayer1BoardsideById(Integer.parseInt(messageMap.get("selected")));
                Minion targetMinion = board.findMinionInPlayer2BoardsideById(Integer.parseInt(messageMap.get("target")));
                boolean possibleAttack = true;
                if (!targetMinion.isTaunt()){
                    for (int i = 0; i < board.getPlayer2Boardside().length; i++) {
                        // if (!board.getPlayer2Boardside()[i].getId().equals(targetMinion.getId()) && )
                        if (board.getPlayer2Boardside()[i] != null && board.getPlayer2Boardside()[i].isTaunt()) {
                            possibleAttack = false;
                        }
                    }
                }
                if (possibleAttack) {
                    targetMinion.setHealth(targetMinion.getHealth() - selectedMinion.getAttack());
                    selectedMinion.setHealth(selectedMinion.getHealth() - targetMinion.getAttack());
                } else {
                    answer = "You must attack taunt minion!";
                }
                if (selectedMinion.getHealth() <= 0) {
                    board.getPlayer1Boardside()[board.getMinionIndexOnPlayer1Boardside(selectedMinion.getId())] = null;
                }
                if (targetMinion.getHealth() <= 0) {
                    board.getPlayer2Boardside()[board.getMinionIndexOnPlayer2Boardside(targetMinion.getId())] = null;
                }
            } else {
                Minion selectedMinion = board.findMinionInPlayer2BoardsideById(Integer.parseInt(messageMap.get("selected")));
                Minion targetMinion = board.findMinionInPlayer1BoardsideById(Integer.parseInt(messageMap.get("target")));
                boolean possibleAttack = true;
                if (!targetMinion.isTaunt()){
                    for (int i = 0; i < board.getPlayer1Boardside().length; i++) {
                        // if (!board.getPlayer2Boardside()[i].getId().equals(targetMinion.getId()) && )
                        if (board.getPlayer2Boardside()[i] != null && board.getPlayer1Boardside()[i].isTaunt()) {
                            possibleAttack = false;
                        }
                    }
                }
                if (possibleAttack) {
                    targetMinion.setHealth(targetMinion.getHealth() - selectedMinion.getAttack());
                    selectedMinion.setHealth(selectedMinion.getHealth() - targetMinion.getAttack());
                } else {
                    answer = "You must attack taunt minion!";
                }
                if (selectedMinion.getHealth() <= 0) {
                    board.getPlayer2Boardside()[board.getMinionIndexOnPlayer2Boardside(selectedMinion.getId())] = null;
                }
                if (targetMinion.getHealth() <= 0) {
                    board.getPlayer1Boardside()[board.getMinionIndexOnPlayer1Boardside(targetMinion.getId())] = null;
                }
            }
        }

        return answer;
    }

    public Integer generateValidPlaceToSummon(Minion[] boardside) {
        Integer random = new Random().nextInt(5);
        while (boardside[random] != null) {
            random = new Random().nextInt(5);
        }
        return random;
    }

    public Warlock getHero(String username) {
        Board board = this.boards.get(username);
/*        switch (heroSelected) {
            case "warlock": board.setPlayer1Hero(new Warlock());
            break;
            case "paladin": board.setPlayer1Hero(new Paladin());
            break;
        }*/
        board.setPlayer1Hero(new Warlock());
        return (Warlock) board.getPlayer1Hero();
    }

    public Warlock getHeroP2(String username) {
        Board board = this.boards.get(username);
/*        switch (heroSelected) {
            case "warlock": board.setPlayer2Hero(new Warlock());
                break;
            case "paladin": board.setPlayer2Hero(new Paladin());
                break;
        }*/
        board.setPlayer2Hero(new Warlock());
        return (Warlock) board.getPlayer2Hero();
    }


/*    private String p1Endturn() {

    }*/
}
