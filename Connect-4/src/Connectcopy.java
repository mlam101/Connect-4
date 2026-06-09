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

    public static JFrame frame = new JFrame("Connect 4");

    public static GamePanel panel;
    
    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setResizable(false);
        panel = new GamePanel();
        frame.setContentPane(panel);
        frame.pack();
        panel.setBackground(Color.WHITE);
        panel.repaint();
        panel.setVisible(true);
        frame.setVisible(true);
    }

    

    public static class GamePanel extends JPanel {
        private BufferedImage boardImg;
        private BufferedImage blueToken;
        private BufferedImage redToken;
        char[][] board = new char[6][7];


        private void loadSprite() {           
            try {
                boardImg = ImageIO.read(new File("board.png"));
                blueToken = ImageIO.read(new File("bluetoken.png"));
                redToken = ImageIO.read(new File("redtoken.png"));

            } catch (IOException e) {
                System.out.println("Error loading board images: " + e.getMessage());
            }
        }

        JPanel boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(boardImg, 200, 50, null);
            }
        };

        private void drawBoard(Graphics g) {
            for (Integer[] row : board) {
                for (Integer cell : row) {
                    
                }
            }
        }
        private void buttonsInit() {
            for (int i = 0; i < 10; i++) {
                JButton button = new JButton();
                button.setName(Integer.toString(i));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        placeToken(button.getName());
                    }
                    
                });
            }
        }

        private void placeToken(String id) {
            switch (id) {
                case "0":
                    
                    break;
                case "1":
                    
                    break;                    
                default:
            }
        }

        GamePanel() {
            loadSprite();
            setPreferredSize(new Dimension(1280, 720));
            repaint();
            setLayout(new BorderLayout());
            add(boardPanel, BorderLayout.CENTER);
        }

    }
}
