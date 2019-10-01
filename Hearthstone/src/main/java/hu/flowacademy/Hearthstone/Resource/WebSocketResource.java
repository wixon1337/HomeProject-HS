package hu.flowacademy.Hearthstone.Resource;

import com.google.gson.Gson;
import hu.flowacademy.Hearthstone.Model.GameModel;
import hu.flowacademy.Hearthstone.Repository.GameInstanceRepository;
import hu.flowacademy.Hearthstone.Repository.PlayerMatchesRepository;
import hu.flowacademy.Hearthstone.Service.WebSocketCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketResource {

    private final Gson jsonParser;
    private final GameInstanceRepository gameInstanceRepository;
    private final PlayerMatchesRepository playerMatchesRepository;
    private final SimpMessageSendingOperations messageSendingOperations;

    @Autowired
    WebSocketCommunicationService wscService;

    @Autowired
    public WebSocketResource(Gson jsonParser, GameInstanceRepository gameInstanceRepository,
                             PlayerMatchesRepository playerMatchesRepository, SimpMessageSendingOperations messageSendingOperations) {
        this.jsonParser = jsonParser;
        this.gameInstanceRepository = gameInstanceRepository;
        this.playerMatchesRepository = playerMatchesRepository;
        this.messageSendingOperations = messageSendingOperations;
    }

    @MessageMapping("/message/{id}")
    @SendTo("/topic/reply/{id}")
    public String processMessageFromClient(@Payload String message) { // , @DestinationVariable("id") String socketId
        // TODO
        // System.out.println("message recieved: " + message + " " + "socketId: " + socketId);
        // String fromJson = jsonParser.fromJson("szia Todo", String.class);


        System.out.println("message recieved: " + message); // Stringet vár!
        jsonParser.toJson("szia TODO");
        return "szia";
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        messageSendingOperations.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }
}
