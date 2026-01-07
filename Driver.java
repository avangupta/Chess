//name: Avan Gupta date: 01/03/2026    period: 

import javax.swing.JFrame;
public class Driver  {
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Chess");
        frame.setSize(ChessPanel.getHFrame() + 1, ChessPanel.getVFrame() + 1);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ChessPanel());
        frame.setVisible(true);
    }
}
