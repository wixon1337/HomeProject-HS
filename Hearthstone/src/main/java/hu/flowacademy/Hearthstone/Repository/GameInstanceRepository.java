package hu.flowacademy.Hearthstone.Repository;

import hu.flowacademy.Hearthstone.Model.GameInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameInstanceRepository extends JpaRepository<GameInstance, String> {

    GameInstance findOneByUserId(String userId);

    GameInstance findByUsername(String username);
}
