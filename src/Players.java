import java.util.Random;

public enum Players {
    X,
    O;

    public Players changePlayer() {
        return this == X ? O : X;
    }

    public Players getOppositePlayer() {
        return this == X ? O : X;
    }

    public char getPlayer() {
        return this.toString().charAt(0);
    }

    public static Players getInitialPlayer() {
        return X;
    }

    public static Players getRandomPlayer() {
        Random random = new Random();
        return random.nextInt(2) == 0 ? X : O;
    }
}
