import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void shouldRegisterPlayer() {
        Game game = new Game();
        Player player = new Player(1, "Иван", 100);
        game.register(player);
        Assertions.assertEquals(player, game.findByName("Иван"));
    }

    @Test
    public void shouldReturn1WhenFirstPlayerWins() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 100));
        game.register(new Player(2, "Петр", 50));

        int result = game.round("Иван", "Петр");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void shouldReturn2WhenSecondPlayerWins() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 50));
        game.register(new Player(2, "Петр", 100));

        int result = game.round("Иван", "Петр");
        Assertions.assertEquals(2, result);
    }

    @Test
    public void shouldReturn0WhenDraw() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 100));
        game.register(new Player(2, "Петр", 100));

        int result = game.round("Иван", "Петр");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldThrowExceptionWhenFirstPlayerNotFound() {
        Game game = new Game();
        game.register(new Player(2, "Петр", 100));

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Иван", "Петр");
        });
    }

    @Test
    public void shouldThrowExceptionWhenSecondPlayerNotFound() {
        Game game = new Game();
        game.register(new Player(1, "Иван", 100));

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Иван", "Петр");
        });
    }
}