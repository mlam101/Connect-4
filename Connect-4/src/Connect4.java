import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Connect4 {
    
    public static void main(String[] args) {
        new Connect4();
    }

    public Connect4() {
        JFrame frame = new JFrame("Connect 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1300, 800));
        frame.setResizable(false);

        GamePanel panel = new GamePanel();
        panel.setBackground(Color.WHITE);
        panel.repaint();
        panel.setVisible(true);

        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static class GamePanel extends JPanel {
        private BufferedImage boardImg;
        private BufferedImage blueToken;
        private BufferedImage redToken;
        private Integer colourTurn = 1;
        private JPanel buttonPanel;
        private FlowLayout myLayout;
        Integer colourWin = 0;

        int[][] board = new int[6][7];


        private void loadSprite() {           
            try {
                boardImg = ImageIO.read(new File("board.png"));
                blueToken = ImageIO.read(new File("bluetoken.png"));
                redToken = ImageIO.read(new File("redtoken.png"));

            } catch (IOException e) {
                System.out.println("Error loading board images: " + e.getMessage());
            }
        }

        private JButton button1 = new JButton();
        private JButton button2 = new JButton();
        private JButton button3 = new JButton();
        private JButton button4 = new JButton();
        private JButton button5 = new JButton();
        private JButton button6 = new JButton();
        private JButton button7 = new JButton();

        JButton[] buttons = {button1, button2, button3, button4, button5, button6, button7};


        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(boardImg, 300, 50, null);
                drawBoard(g);
                if (winChecker() != -1) {
                   drawWin(g);
                }
            }
        };

        private void drawBoard(Graphics g) {
            for (int idx = 0; idx < board.length; idx++) {
                int[] row = board[idx];
                for (int cell = 0; cell < row.length; cell++) {
                        switch (row[cell]) {
                            case 1:
                                g.drawImage(redToken, ((cell*100)+300), ((idx*100)+50), null);
                                break;
                            case 2:
                                g.drawImage(blueToken, ((cell*100)+300), ((idx*100)+50), null);
                                break;
                            default:
                        }
                    }  
            }
        }

        private void drawWin(Graphics g) {
            String message;
            if (winChecker() == 2) {
                message = "BLUE WINS";
            } 
            else {
                message = "RED WINS";
            }
            g.drawString(message, 300, 50);
        }
        private void buttonsInit() {
            buttonPanel = new JPanel();
            myLayout = new FlowLayout(FlowLayout.LEADING);
            myLayout.setHgap(0);
            buttonPanel.setLayout(myLayout);
            buttonPanel.setOpaque(true);
            buttonPanel.setVisible(true);
            JLabel spacerLabel = new JLabel();
            spacerLabel.setPreferredSize(new Dimension(300, 50));
            buttonPanel.add(spacerLabel);
            for (int idx = 0; idx < buttons.length; idx++) {
                JButton button = buttons[idx];
                button.setName(Integer.toString(idx));
                button.setPreferredSize(new Dimension(100, 50));
                button.setBackground(Color.BLUE);
                buttonPanel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        placeToken(Integer.valueOf(button.getName()), colourTurn);
                        boardPanel.repaint();
                        colourTurn %= 2;
                        colourTurn++;
                        debugger();
                    }
                    
                });
            }
        }
        private void placeToken(Integer num, Integer colour) {
            int i = 5;
            while ((board[i][num] != 0) && (i > 0)) {
                i--;
            }
            board[i][num] = colour;
        }

        private void debugger() {
            for (int[] row : board) {
                for (int cell : row) {
                    System.out.print(cell);
                }
                System.err.println("    ");
            }
        }

        private Integer winChecker() {
            int checkerNum = 1;
            while (checkerNum < 3) { //For horizontal 4s
                for (int idx = 0; idx < board.length; idx++) {
                    int[] row = board[idx];
                    for (int i = 0; i < (row.length - 3); i++) {
                        if (row[i] == checkerNum) {
                            if (row[i+1] == checkerNum) {
                                if (row[i+2] == checkerNum) {
                                    if (row[i+3] == checkerNum) {
                                        return checkerNum;
                                    }
                                }
                            }
                        }
                    }
                }
            checkerNum++;
            }
            checkerNum = 1;
            while (checkerNum < 3) { //For verticals 4s
                for (int idx = 0; idx < board.length; idx++) {
                    for (int i = 0; i < 3; i++) {
                        if (board[i][idx] == checkerNum) {
                            if (board[i+1][idx] == checkerNum) {
                                if (board[i+2][idx] == checkerNum) {
                                    if (board[i+3][idx] == checkerNum) {
                                        return checkerNum;
                                    }
                                }
                            }
                        }
                    }
                }
            checkerNum++;
            }
            checkerNum = 1;
            while (checkerNum < 3) { //For diagonal 4s
                for (int idx = 0; idx < board.length; idx++) {
                    for (int i = 0; i < 3; i++) {
                        if (board[i][idx] == checkerNum) {
                            if (board[i+1][idx+1] == checkerNum) {
                                if (board[i+2][idx+2] == checkerNum) {
                                    if (board[i+3][idx+3] == checkerNum) {
                                        return checkerNum;
                                    }
                                }
                            }
                        }
                    }
                }
            checkerNum++;
            }
            checkerNum = 1;
            while (checkerNum < 3) { //For diagonal 4s
                for (int idx = 6; idx > 2; idx--) {
                    for (int i = 0; i < 3; i++) {
                        if (board[i][idx] == checkerNum) {
                            if (board[i+1][idx-1] == checkerNum) {
                                if (board[i+2][idx-2] == checkerNum) {
                                    if (board[i+3][idx-3] == checkerNum) {
                                        return checkerNum;
                                    }
                                }
                            }
                        }
                    }
                }
            checkerNum++;
            }

            return -1;
            
        }

        GamePanel() {
            loadSprite();
            setPreferredSize(new Dimension(1300, 800));
            repaint();
            setLayout(new BorderLayout());
            buttonsInit();
            add(boardPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.PAGE_START);
        }

    }
}
