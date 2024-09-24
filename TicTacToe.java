import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardwidth = 600;
    int boardheight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel controlPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    JButton restartButton = new JButton("Restart");

    boolean gameOver = false;
    int turn = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.BLACK);
        frame.add(boardPanel);

        
        for (int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);
                
                currentPlayer = currentPlayer == playerX ? playerO : playerX;

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        turn++;
                        if (gameOver) return;
                        if (turn == 9) tie();
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }

        // Add restart button to control panel
        controlPanel.setLayout(new FlowLayout());
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        controlPanel.add(restartButton);
        frame.add(controlPanel, BorderLayout.SOUTH);
    }

    void checkWinner() {
        //hang ngang
        for(int r = 0; r < 3; r++){
            if(board[r][0].getText() == "") continue;
            if(board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                setWinRow(r);
                gameOver = true;
                return;
            }
        }

        //hang doc
        for(int r = 0; r < 3; r++) {
            if(board[0][r].getText() == "") continue;
            if(board[0][r].getText() == board[1][r].getText() && board[1][r].getText() == board[2][r].getText()) {
                gameOver = true;
                setWinCol(r);
                return;
            }
        }

        //hang cheo trai
        for(int r = 0; r < 3;r++) {
            if(board[r][r].getText() == "" ) continue;
            if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()) {
                board[0][0].setBackground(Color.GREEN);
                board[1][1].setBackground(Color.GREEN);
                board[2][2].setBackground(Color.GREEN);      
                textLabel.setText(currentPlayer + " wins");
                
                gameOver = true;
                return;
            }
            
        }

        //hang cheo phai
        for(int r = 0; r < 3;r++) {
            if(board[2-r][r].getText() == "" ) continue;
            if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()) {
                board[2][0].setBackground(Color.GREEN);
                board[1][1].setBackground(Color.GREEN);
                board[0][2].setBackground(Color.GREEN);     
                textLabel.setText(currentPlayer + " wins");

                gameOver = true;
                return;
            }    
        }
    }

    void setWinRow(int r) {
        board[r][0].setBackground(Color.GREEN);
        board[r][1].setBackground(Color.GREEN);
        board[r][2].setBackground(Color.GREEN);

        textLabel.setText(currentPlayer + " wins");
    }

    void setWinCol(int r) {
        board[0][r].setBackground(Color.GREEN);
        board[1][r].setBackground(Color.GREEN);
        board[2][r].setBackground(Color.GREEN);

        textLabel.setText(currentPlayer + " wins");
    }

    void tie() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j].setBackground(Color.YELLOW);
                gameOver = false;
                turn = 0;
            }
        }
    }

    void resetGame() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setBackground(Color.darkGray);
                gameOver = false;
                turn = 0;
            }
        }
    }
}
