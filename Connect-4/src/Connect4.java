import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Connect4 {
    
    public static void main(String[] args) {
        new Connect4();
    }

    public Connect4() {
        JFrame frame = new JFrame("Connect 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
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
        Integer[][] board = new Integer[7][6];


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

        JButton[] buttons = new JButton[] {button1, button2, button3, button4, button5, button6, button7};


        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(boardImg, 200, 50, null);
                drawBoard(g);
            }
        };

        private void drawBoard(Graphics g) {
            for (int idx = 0; idx < board.length; idx++) {
                Integer[] row = board[idx];
                for (int cell = 0; cell < row.length; cell++) {
                    if (row[cell] != null) {
                        switch (row[cell]) {
                            case 1:
                                g.drawImage(redToken, ((idx*100)+200), ((cell*100)+50), null);
                                break;
                            case 2:
                                g.drawImage(blueToken, ((idx*100)+200), ((cell*100)+50), null);
                                break;
                            default:
                        }
                    }
                }   
            }
        }
        private void buttonsInit() {
            for (int idx = 0; idx < buttons.length; idx++) {
                JButton button = buttons[idx];
                button.setName(Integer.toString(idx));
                button.setPreferredSize(new Dimension(100, 50));
                button.setBackground(Color.BLUE);
                boardPanel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        placeToken(Integer.parseInt(button.getName()), colourTurn);
                        boardPanel.repaint();
                        debugger();
                    }
                    
                });
            }
        }
        private void debugger() {
            for (Integer[] row : board) {
                for (Integer cell : row) {
                    System.out.println(cell);
                }
            }
        }
        private void placeToken(Integer num, Integer colour) {
            int i = 5;
            while ((board[num][i] != null) && (i > 0)) {
                i--;
            }
            board[num][i] = colour;               
            System.err.println("TOKEN UPDATED");
        }

        GamePanel() {
            loadSprite();
            setPreferredSize(new Dimension(1280, 720));
            repaint();
            setLayout(new BorderLayout());
            buttonsInit();
            add(boardPanel, BorderLayout.CENTER);
        }

    }
}
