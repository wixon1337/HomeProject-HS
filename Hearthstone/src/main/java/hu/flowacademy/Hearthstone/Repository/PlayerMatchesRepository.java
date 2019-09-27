package hu.flowacademy.Hearthstone.Repository;

import hu.flowacademy.Hearthstone.Model.PlayerMatches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMatchesRepository extends JpaRepository<PlayerMatches, Long> {

    PlayerMatches findOneByPlayer1(String player1);

    PlayerMatches findOneByWebSocketAddress(Long WebSocketAddress);

}
