import java.util.*;

enum Color {
    WHITE, BLACK
}

abstract class Piece {
    Color color;
    char symbol;

    Piece(Color color, char symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return color == Color.WHITE ?
                Character.toUpperCase(symbol) :
                Character.toLowerCase(symbol);
    }

    public abstract boolean isValidMove(Board board,
                                        int sr, int sc,
                                        int er, int ec);
}

class Pawn extends Piece {

    Pawn(Color color) {
        super(color, 'p');
    }

    public boolean isValidMove(Board board,
                               int sr, int sc,
                               int er, int ec) {

        int dir = (color == Color.WHITE) ? -1 : 1;

        if (sc == ec && board.getPiece(er, ec) == null) {

            if (er == sr + dir)
                return true;

            if ((color == Color.WHITE && sr == 6) ||
                (color == Color.BLACK && sr == 1)) {

                if (er == sr + 2 * dir &&
                    board.getPiece(sr + dir, sc) == null)
                    return true;
            }
        }

        if (Math.abs(ec - sc) == 1 &&
            er == sr + dir &&
            board.getPiece(er, ec) != null &&
            board.getPiece(er, ec).color != color)
            return true;

        return false;
    }
}

class Rook extends Piece {

    Rook(Color color) {
        super(color, 'r');
    }

    public boolean isValidMove(Board board,
                               int sr, int sc,
                               int er, int ec) {

        if (sr != er && sc != ec)
            return false;

        return board.clearPath(sr, sc, er, ec);
    }
}

class Bishop extends Piece {

    Bishop(Color color) {
        super(color, 'b');
    }

    public boolean isValidMove(Board board,
                               int sr, int sc,
                               int er, int ec) {

        if (Math.abs(sr - er) != Math.abs(sc - ec))
            return false;

        return board.clearPath(sr, sc, er, ec);
    }
}

class Queen extends Piece {

    Queen(Color color) {
        super(color, 'q');
    }

    public boolean isValidMove(Board board,
                               int sr, int sc,
                               int er, int ec) {

        if (sr == er || sc == ec ||
            Math.abs(sr - er) == Math.abs(sc - ec))
            return board.clearPath(sr, sc, er, ec);

        return false;
    }
}

class Knight extends Piece {

    Knight(Color color) {
        super(color, 'n');
    }

    public boolean isValidMove(Board board,
                               int sr, int sc,
                               int er, int ec) {

        int dr = Math.abs(sr - er);
        int dc = Math.abs(sc - ec);

        return (dr == 2 && dc == 1) ||
               (dr == 1 && dc == 2);
    }
}

class King extends Piece {

    King(Color color) {
        super(color, 'k');
    }

    public boolean isValidMove(Board board,
                               int sr, int sc,
                               int er, int ec) {

        return Math.abs(sr - er) <= 1 &&
               Math.abs(sc - ec) <= 1;
    }
}

class Board {

    Piece[][] board = new Piece[8][8];

    Board() {
        setup();
    }

    Piece getPiece(int r, int c) {
        return board[r][c];
    }

    void setPiece(int r, int c, Piece p) {
        board[r][c] = p;
    }

    void setup() {

        for (int i = 0; i < 8; i++) {

            board[1][i] = new Pawn(Color.BLACK);
            board[6][i] = new Pawn(Color.WHITE);
        }

        board[0][0] = new Rook(Color.BLACK);
        board[0][7] = new Rook(Color.BLACK);

        board[7][0] = new Rook(Color.WHITE);
        board[7][7] = new Rook(Color.WHITE);

        board[0][1] = new Knight(Color.BLACK);
        board[0][6] = new Knight(Color.BLACK);

        board[7][1] = new Knight(Color.WHITE);
        board[7][6] = new Knight(Color.WHITE);

        board[0][2] = new Bishop(Color.BLACK);
        board[0][5] = new Bishop(Color.BLACK);

        board[7][2] = new Bishop(Color.WHITE);
        board[7][5] = new Bishop(Color.WHITE);

        board[0][3] = new Queen(Color.BLACK);
        board[7][3] = new Queen(Color.WHITE);

        board[0][4] = new King(Color.BLACK);
        board[7][4] = new King(Color.WHITE);
    }

    boolean clearPath(int sr,
                      int sc,
                      int er,
                      int ec) {

        int dr = Integer.compare(er, sr);
        int dc = Integer.compare(ec, sc);

        sr += dr;
        sc += dc;

        while (sr != er || sc != ec) {

            if (board[sr][sc] != null)
                return false;

            sr += dr;
            sc += dc;
        }

        return true;
    }
        void display() {

        System.out.println();

        for (int i = 0; i < 8; i++) {

            System.out.print((8 - i) + " ");

            for (int j = 0; j < 8; j++) {

                if (board[i][j] == null)
                    System.out.print(". ");
                else
                    System.out.print(board[i][j].getSymbol() + " ");
            }

            System.out.println();
        }

        System.out.println("  a b c d e f g h");
    }
}

class Player {

    String name;
    Color color;

    Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }
}

class Move {

    int sr;
    int sc;
    int er;
    int ec;

    Move(String from, String to) {

        sc = from.charAt(0) - 'a';
        sr = 8 - (from.charAt(1) - '0');

        ec = to.charAt(0) - 'a';
        er = 8 - (to.charAt(1) - '0');
    }
}

class Game {

    Board board = new Board();

    Player white = new Player("White", Color.WHITE);
    Player black = new Player("Black", Color.BLACK);

    Color turn = Color.WHITE;

    Scanner sc = new Scanner(System.in);

    void start() {

        System.out.println("=== Chess Game (Console Version) ===");

        while (true) {

            board.display();

            System.out.println();

            if (turn == Color.WHITE)
                System.out.println("White's turn.");
            else
                System.out.println("Black's turn.");

            System.out.print("Enter move (Example: e2 e4) or exit : ");

            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit"))
                break;

            String[] move = input.split(" ");

            if (move.length != 2) {

                System.out.println("Invalid Input.");
                continue;
            }

            Move m = new Move(move[0], move[1]);

            if (playMove(m))
                turn = (turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
    }

    boolean playMove(Move m) {

        if (m.sr < 0 || m.sr > 7 ||
            m.er < 0 || m.er > 7 ||
            m.sc < 0 || m.sc > 7 ||
            m.ec < 0 || m.ec > 7) {

            System.out.println("Invalid Position.");
            return false;
        }

        Piece piece = board.getPiece(m.sr, m.sc);

        if (piece == null) {

            System.out.println("No piece found.");
            return false;
        }

        if (piece.color != turn) {

            System.out.println("Wrong player's turn.");
            return false;
        }

        Piece destination = board.getPiece(m.er, m.ec);

        if (destination != null &&
            destination.color == piece.color) {

            System.out.println("Cannot capture your own piece.");
            return false;
        }

        if (!piece.isValidMove(board,
                m.sr, m.sc,
                m.er, m.ec)) {

            System.out.println("Invalid move.");
            return false;
        }

        board.setPiece(m.er, m.ec, piece);
        board.setPiece(m.sr, m.sc, null);

        System.out.println(piece.getClass().getSimpleName()
                + " moved from "
                + (char) ('a' + m.sc)
                + (8 - m.sr)
                + " to "
                + (char) ('a' + m.ec)
                + (8 - m.er));

        return true;
    }
}
public class ChessGame {

    public static void main(String[] args) {

        Game game = new Game();

        game.start();

        System.out.println("\nGame Over.");
    }
}