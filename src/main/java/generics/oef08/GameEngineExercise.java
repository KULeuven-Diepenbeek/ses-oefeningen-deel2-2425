package generics.oef08;

import java.util.List;

public class GameEngineExercise {

    interface Character {}
    interface CanFight extends Character {}
    record Warrior() implements CanFight {}
    record Knight() implements CanFight {}
    record Wizard() implements Character {}
    interface Action<C extends Character> {
        void execute(C character);
    }
    static class FightAction implements Action<CanFight> {
        @Override
        public void execute(CanFight character) {
            System.out.println(character + " fights!");
        }
    }
    static class GameEngine {
        public <T extends Character> void doAction(
                List<T> characters,
                Action<? super T> action) {
            for (var character : characters) {
                action.execute(character);
            }
        }
    }
    public static void main(String[] args) {
        var engine = new GameEngine();
        Action<CanFight> fight = new FightAction();

        List<Warrior> warriors = List.of(new Warrior(), new Warrior());
        engine.doAction(warriors, fight);

        List<Wizard> wizards = List.of(new Wizard());
        // engine.doAction(wizards, fight); // deze regel mag NIET compileren
    }
}
