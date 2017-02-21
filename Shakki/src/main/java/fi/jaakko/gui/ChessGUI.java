package fi.jaakko.gui;

import fi.jaakko.game.Board;
import fi.jaakko.game.Game;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class ChessGUI implements Runnable{
    
    private JFrame frame;
    private Game game;
    private Board board;
    private ChessListener listener;
    private List<int[]> regularMoves;
    private List<int[]> capture;
    
    public ChessGUI(Board board){
        this.board=board;
        this.game=new Game(board);
    }
    
    @Override
    public void run() {
        frame = new JFrame("Chess");
        frame.setPreferredSize(new Dimension(500, 500));
        this.listener=new ChessListener(this.game,this.frame.getContentPane());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        GridLayout chessLayout = new GridLayout(8, 8);
        container.setLayout(chessLayout);
        
        for(int y=0;y<8;y++){
            for(int x=0;x<8;x++){
                try{
                    JLabel label = new JLabel(this.board.board()[x][y].toString());
                    label.setFont(new Font("Arial Unicode MS", Font.PLAIN, 43));
                    label.setBorder(BorderFactory.createLineBorder(Color.black));
                    label.setOpaque(true);
                    if(this.board.board()[x][y].getColour()==this.game.getCurrentColour()){
                        if(y%2==x%2){
                            label.setBackground(new Color(200,200,150));
                        }else{
                            label.setBackground(new Color(230,230,180));
                        }
                    }else{
                        if(y%2==x%2){
                            label.setBackground(Color.lightGray);
                        }else{
                            label.setBackground(Color.white);
                        }
                    }
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setVerticalAlignment(SwingConstants.CENTER);
                    label.setName(x+" "+y);
                    label.addMouseListener(listener);
                    container.add(label);
                }catch(Exception e){
                    JLabel label = new JLabel("");
                    label.setFont(new Font("Tahoma", Font.PLAIN, 24));
                    label.setBorder(BorderFactory.createLineBorder(Color.black));
                    label.setOpaque(true);
                    if(y%2==x%2){
                        label.setBackground(Color.lightGray);
                    }else{
                        label.setBackground(Color.white);
                    }
                    label.setName(x+" "+y);
                    label.addMouseListener(listener);
                    container.add(label);
                }
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }
    
}
