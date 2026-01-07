//Chess by Avan Gupta
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class ChessPanel extends JPanel
{
    private static int HSIZE = 52; //must be even
    private static int VSIZE = 52;
    private static int HFRAME = HSIZE * 8;
    private static int VFRAME = VSIZE * 8;
    private static int HRADIUS = HSIZE/4 - 5;
    private static int VRADIUS = VSIZE/4 - 5;
    
    private BufferedImage myImage;
    private static Graphics myBuffer;
    private Timer t;
    private static Scanner scanner;
    
    private ImageIcon wKing;
    private ImageIcon bKing;
    private ImageIcon wQueen;
    private ImageIcon bQueen;
    private ImageIcon wBishop;
    private ImageIcon bBishop;
    private ImageIcon wKnight;
    private ImageIcon bKnight;
    private ImageIcon wRook;
    private ImageIcon bRook;
    private ImageIcon wPawn;
    private ImageIcon bPawn;
    
    private static boolean pieceClicked = false;
    private static boolean done = false;
    private static boolean done2 = false;
    private static String turn = "w";
    
    private static String testPiece = "";
    
    private static String piece;
    private static String color;
    private static String oldPiece;
    private static String oldColor;
    private static int index = -1;
    private static int oldindex = -1;
    
    private static List<String> board;
    private static List<String> colors;
    private static List<Integer> possibleMoves;
    private static List<Integer> possibleMoves2;
    private static List<Integer> possibleMoves3;
    private static List<Boolean> rooks;
    
    
    public ChessPanel()
    {
        myImage =  new BufferedImage(HFRAME, VFRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        
        myBuffer.setColor(Color.WHITE);
        myBuffer.fillRect(0, 0, HFRAME, VFRAME);
        
        board = new ArrayList<>();
        colors = new ArrayList<>();
        possibleMoves = new ArrayList<>();
        possibleMoves2 = new ArrayList<>();
        possibleMoves3 = new ArrayList<>();
        rooks = new ArrayList<>();
        
        scanner = new Scanner(System.in);
        
        addMouseListener(new Mouse());
        
        for (int i = 1; i <= 4; i++)
        {
            rooks.add(true);
        }
        
        board.add("r");
        board.add("kn");
        board.add("b");
        board.add("q");
        board.add("k");
        board.add("b");
        board.add("kn");
        board.add("r");
        for (int x = 1; x <= 8; x++)
        {
            colors.add("b");
        }
        for (int x = 1; x <= 8; x++)
        {
            board.add("p");
            colors.add("b");
        }
        //testpiece
        for (int x = 1; x <= 32; x++)
        {
            if (x == 16)
            {
                board.add(testPiece);
                if (testPiece.equals(""))
                {
                    colors.add("");
                }
                else
                {
                    colors.add("w");
                }
            }
            else if (x == 17)
            {
                board.add(testPiece);
                if (testPiece.equals(""))
                {
                    colors.add("");
                }
                else
                {
                    colors.add("b");
                }
            }
            else
            {
                board.add("");
                colors.add("");
            }
        }
        for (int x = 1; x <= 8; x++)
        {
            board.add("p");
            colors.add("w");
        }
        board.add("r");
        board.add("kn");
        board.add("b");
        board.add("q");
        board.add("k");
        board.add("b");
        board.add("kn");
        board.add("r");
        for (int x = 1; x <= 8; x++)
        {
            colors.add("w");
        }
        
        wKing = new ImageIcon(getClass().getResource("/whiteking.png"));
        bKing = new ImageIcon(getClass().getResource("/blackking.png"));
        
        wQueen = new ImageIcon(getClass().getResource("/whitequeen.png"));
        bQueen = new ImageIcon(getClass().getResource("/blackqueen.png"));
        
        wBishop = new ImageIcon(getClass().getResource("/whitebishop.png"));
        bBishop = new ImageIcon(getClass().getResource("/blackbishop.png"));
        
        wPawn = new ImageIcon(getClass().getResource("/whitepawn.png"));
        bPawn = new ImageIcon(getClass().getResource("/blackpawn.png"));
        
        wKnight = new ImageIcon(getClass().getResource("/whiteknight.png"));
        bKnight = new ImageIcon(getClass().getResource("/blackknight.png"));
        
        wRook = new ImageIcon(getClass().getResource("/whiterook.png"));
        bRook = new ImageIcon(getClass().getResource("/blackrook.png"));
        
        
        t = new Timer(30, new Listener());
        t.start();
    }
    
    
    
    
    
    private class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //resetting board and grid
            
            //HSIZE = getWidth() / 8;
            //VSIZE = getHeight() / 8;
            //HFRAME = HSIZE * 8;
            //VFRAME = VSIZE * 8;
            //HRADIUS = HSIZE/4 - 5;
            //VRADIUS = VSIZE/4 - 5;
            
            if (!done)
            {
                myBuffer.setColor(Color.WHITE);
                myBuffer.fillRect(0, 0, HFRAME, VFRAME);
               
                myBuffer.setColor(Color.BLACK);
            
                for (int x = 0; x <= 8; x++)
                {
                myBuffer.drawLine(x * HSIZE, 0, x * HSIZE, VFRAME);
                myBuffer.drawLine(0, x * VSIZE, HFRAME, x * VSIZE);
                }
            }
            
            
            //drawing pieces
            for (int x = 0; x <= 63; x++)
            {
                String y = board.get(x);
                String z = colors.get(x);
                
                if (y == "k")
                {
                    if (z == "w")
                    {
                        drawPiece(wKing, x);
                    }
                    else if (z == "b")
                    {
                        drawPiece(bKing, x);
                    }
                }
                else if (y == "q")
                {
                    if (z == "w")
                    {
                        drawPiece(wQueen, x);
                    }
                    else if (z == "b")
                    {
                        drawPiece(bQueen, x);
                    }
                }
                else if (y == "b")
                {
                    if (z == "w")
                    {
                        drawPiece(wBishop, x);
                    }
                    else if (z == "b")
                    {
                        drawPiece(bBishop, x);
                    }
                }
                else if (y == "kn")
                {
                    if (z == "w")
                    {
                        drawPiece(wKnight, x);
                    }
                    else if (z == "b")
                    {
                        drawPiece(bKnight, x);
                    }
                }
                else if (y == "r")
                {
                    if (z == "w")
                    {
                        drawPiece(wRook, x);
                    }
                    else if (z == "b")
                    {
                        drawPiece(bRook, x);
                    }
                }
                else if (y == "p")
                {
                    if (z == "w")
                    {
                        drawPiece(wPawn, x);
                    }
                    else if (z == "b")
                    {
                        drawPiece(bPawn, x);
                    }
                }
            }
            
            
            
            if (pieceClicked)
            {
                getActualMoves(index);
                
                //draws circles
                for (int a = 0; a < possibleMoves.size(); a++)
                {
                    drawCircle(possibleMoves.get(a));
                }
            
                
            }
            
            repaint();
        }
    }
    
    //unnecessary for viewing(paintComponent, drawPiece, drawCircle, getFrame)
    
    public void paintComponent(Graphics g)
    {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }
    
    public static void drawPiece(ImageIcon i, int x)
    {
        if (!done)
        {
            myBuffer.drawImage(i.getImage(), x % 8 * HSIZE + 2, x / 8 * VSIZE + 2, HSIZE - 4, VSIZE - 4, null);
        }
    }
    
    public static void drawCircle(int ind)
    {
        if (!done)
        {
            myBuffer.setColor(Color.LIGHT_GRAY);
            int x = ind % 8 * HSIZE;
            int y = ind / 8 * VSIZE;
            
            myBuffer.fillOval(x + HSIZE/2 - HRADIUS, y + VSIZE/2 - VRADIUS, HRADIUS * 2, VRADIUS * 2);
        }
    }
    
    public static int getHFrame()
    {
        return HFRAME;
    }
    
    public static int getVFrame()
    {
        return VFRAME;
    }
    
    public static void movePiece()
    {
        if (done2)
        {
            done = true;
        }
        
        //en passant
        for (int i = 0; i <= 63; i++)
        {
            if ((board.get(i)).equals("x"))
            {
                board.set(i, "");
                colors.set(i, "");
            }
        }
        //promotion
        if (oldPiece.equals("p"))
        {
            if (oldColor.equals("w"))
            {
                if ((board.get(index)).equals("") && (oldindex - index == 7 || oldindex - index == 9))
                {
                    board.set(index + 8, "");
                    colors.set(index + 8, "");
                }
                if (index <= 7)
                {
                    System.out.println("What do you want to promote to? (q, r, b, kn) (if invalid input, automatic queen)");
                    
                    String scanPiece = scanner.nextLine();
                    if (scanPiece.equals("r"))
                    {
                        board.set(index, "r");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                    else if (scanPiece.equals("b"))
                    {
                        board.set(index, "b");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                    else if (scanPiece.equals("kn"))
                    {
                        board.set(index, "kn");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                    else
                    {
                        board.set(index, "q");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                }
                else if (index == oldindex - 16)
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(oldindex - 8, "x");
                    colors.set(oldindex - 8, oldColor);
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
                else
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
            }
            else if (oldColor.equals("b"))
            {
                if (((board.get(index)).equals("") && (index - oldindex == 7 || index - oldindex == 9)))
                {
                    board.set(index - 8, "");
                    colors.set(index - 8, "");
                }
                if (index >= 56)
                {
                    System.out.println("What do you want to promote to? (q, r, b, kn) (if invalid input, automatic queen)");
                    
                    String scanPiece = scanner.nextLine();
                    if (scanPiece.equals("r"))
                    {
                        board.set(index, "r");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                    else if (scanPiece.equals("b"))
                    {
                        board.set(index, "b");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                    else if (scanPiece.equals("kn"))
                    {
                        board.set(index, "kn");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                    else
                    {
                        board.set(index, "q");
                        colors.set(index, oldColor);
                        board.set(oldindex, "");
                        colors.set(oldindex, "");
                    }
                }
                else if (index == oldindex + 16)
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(oldindex + 8, "x");
                    colors.set(oldindex + 8, oldColor);
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
                else
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
            }
        }
        else if (oldPiece.equals("k"))
        {
            if (oldColor.equals("w"))
            {
                if (index == oldindex + 2)
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(index - 1, "r");
                    colors.set(index - 1, "w");
                    board.set(index + 1, "");
                    colors.set(index + 1, "");
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
                else if (index == oldindex - 2)
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(index + 1, "r");
                    colors.set(index + 1, "w");
                    board.set(index - 2, "");
                    colors.set(index - 2, "");
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
                else
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
            }
            else if (oldColor.equals("b"))
            {
                if (index == oldindex + 2)
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(index - 1, "r");
                    colors.set(index - 1, "b");
                    board.set(index + 1, "");
                    colors.set(index + 1, "");
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
                else if (index == oldindex - 2)
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(index + 1, "r");
                    colors.set(index + 1, "b");
                    board.set(index - 2, "");
                    colors.set(index - 2, "");
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
                else
                {
                    board.set(index, oldPiece);
                    colors.set(index, oldColor);
                    board.set(oldindex, "");
                    colors.set(oldindex, "");
                }
            }
        }
        else
        {
            board.set(index, oldPiece);
            colors.set(index, oldColor);
            board.set(oldindex, "");
            colors.set(oldindex, "");
        }
        
        //castling checks
        if (rooks.size() >= 0)
        {
            if ((board.get(0)).equals(""))
            {
                rooks.set(0, false);
            }
            if ((board.get(7)).equals(""))
            {
                rooks.set(1, false);
            }
            if ((board.get(56)).equals(""))
            {
                rooks.set(2, false);
            }
            if ((board.get(63)).equals(""))
            {
                rooks.set(3, false);
            }
            if ((board.get(4)).equals(""))
            {
                rooks.set(0, false);
                rooks.set(1, false);
            }
            if ((board.get(60)).equals(""))
            {
                rooks.set(2, false);
                rooks.set(3, false);
            }
        }
        
        //checks for check
        boolean check = false;
        boolean mate = false;
        String colorOpp = "";
        if (turn.equals("w"))
        {
            colorOpp = "b";
        }
        else if (turn.equals("b"))
        {
            colorOpp = "w";
        }
        
        for (int a = 0; a <= 63; a++)
        {
            checkMoves(a);
            possibleMoves.addAll(possibleMoves2);
            for (int y = 0; y < possibleMoves.size(); y++)
            {
                int m = possibleMoves.get(y);
                if ((board.get(m)).equals("k") && (colors.get(m)).equals(colorOpp) && (colors.get(a)).equals(turn))
                {
                    check = true;
                }
            }
        }
        
        if (turn.equals("w"))
        {
            colorOpp = "b";
        }
        else if (turn.equals("b"))
        {
            colorOpp = "w";
        }
        
        mate = true;
        
        
        
        
        for (int i = 0; i <= 63; i++)
        {
            if ((colors.get(i)).equals(colorOpp))
            {
                if (turn.equals("w"))
                {
                    turn = "b";
                }
                else if (turn.equals("b"))
                {
                    turn = "w";
                }
                
                getActualMoves(i);
                if (possibleMoves.size() > 0)
                {
                    mate = false;
                }
                
                if (turn.equals("w"))
                {
                    turn = "b";
                }
                else if (turn.equals("b"))
                {
                    turn = "w";
                }
            }
        }
        
        
        
        
        if (check && mate)
        {
            if (turn.equals("w"))
            {
                System.out.println("Checkmate! White wins!");
            }
            else if (turn.equals("b"))
            {
                System.out.println("Checkmate! Black wins!");
            }
            done2 = true;
        }
        else if (mate)
        {
            System.out.println("Draw. Both sides win!");
            done2 = true;
        }
        else if (check)
        {
            
            System.out.println("Check!");
        }
        
        //switches turns
        {
            if (turn.equals("w"))
            {
                turn = "b";
            }
            else if (turn.equals("b"))
            {
                turn = "w";
            }
        }
        
    }
    
    public static void checkMoves(int india)
    {
        myBuffer.setColor(Color.LIGHT_GRAY);
        boolean stop = false;
        String elcolor = colors.get(india);
        String lapiece = board.get(india);
        possibleMoves2.clear();
        if (lapiece.equals("r") || lapiece.equals("q"))
        {
                    for (int a = india - 8; a >= 0; a -= 8)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                    stop = false;
                    for (int a = india + 8; a <= 63; a += 8)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                    stop = false;
                    for (int a = india - 1; a >= india - india % 8; a -= 1)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                    stop = false;
                    for (int a = india + 1; a <= india + 7 - india % 8; a += 1)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                }
        if (lapiece.equals("b") || lapiece.equals("q"))
        {
                    stop = false;
                    for (int a = india - 9; a >= 0 && a % 8 != 7; a -= 9)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                    stop = false;
                    for (int a = india - 7; a >= 0 && a % 8 != 0; a -= 7)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                    stop = false;
                    for (int a = india + 7; a <= 63 && a % 8 != 7; a += 7)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                    stop = false;
                    for (int a = india + 9; a <= 63 && a % 8 != 0; a += 9)
                    {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
                }
        if (lapiece.equals("p"))
        {
                    if (elcolor.equals("w"))
                    {
                        if (india / 8 == 6)
                        {
                            for (int a = india - 8; a >= india - 16 && a >= 0; a -= 8)
                            {
                                if (!(colors.get(a)).equals(""))
                                {
                                    stop = true;
                                }
                                if (!stop)
                                {
                                    possibleMoves2.add(a);
                                }
                            }
                        }
                        else
                        {
                            for (int a = india - 8; a >= india - 8 && a >= 0; a -= 8)
                            {
                                if (!(colors.get(a)).equals(""))
                                {
                                    stop = true;
                                }
                                if (!stop)
                                {
                                    possibleMoves2.add(a);
                                }
                            }
                        }
                        stop = false;
                        for (int a = india - 9; a >= india - 9 && a % 8 != 7 && a >= 0; a -= 9)
                        {
                            if ((colors.get(a)).equals(elcolor))
                            {
                                stop = true;
                            }
                            if ((colors.get(a)).equals(""))
                            {
                                stop = true;
                            }
                            if (!stop)
                            {
                                possibleMoves2.add(a);
                            }
                            String colorOpp = "";
                            if ((colors.get(a)).equals("w"))
                            {
                                colorOpp = "b";
                            }
                            else if ((colors.get(a)).equals("b"))
                            {
                                colorOpp = "w";
                            }
                            if (colorOpp.equals(elcolor))
                            {
                                stop = true;
                            }
                        }
                        stop = false;
                        for (int a = india - 7; a >= india - 7 && a % 8 != 0 && a >= 0; a -= 7)
                        {
                            if ((colors.get(a)).equals(elcolor))
                            {
                                stop = true;
                            }
                            if ((colors.get(a)).equals(""))
                            {
                                stop = true;
                            }
                            if (!stop)
                            {
                                possibleMoves2.add(a);
                            }
                            String colorOpp = "";
                            if ((colors.get(a)).equals("w"))
                            {
                                colorOpp = "b";
                            }
                            else if ((colors.get(a)).equals("b"))
                            {
                                colorOpp = "w";
                            }
                            if (colorOpp.equals(elcolor))
                            {
                                stop = true;
                            }
                        }
                    }
                    else if (elcolor.equals("b"))
                    {
                        if (india / 8 == 1)
                        {
                            for (int a = india + 8; a <= india + 16 && a <= 63; a += 8)
                            {
                                if (!(colors.get(a)).equals(""))
                                {
                                    stop = true;
                                }
                                if (!stop)
                                {
                                    possibleMoves2.add(a);
                                }
                            }
                        }
                        else
                        {
                            for (int a = india + 8; a <= india + 8 && a <= 63; a += 8)
                            {
                                if (!(colors.get(a)).equals(""))
                                {
                                    stop = true;
                                }
                                if (!stop)
                                {
                                    possibleMoves2.add(a);
                                }
                            }
                        }
                        stop = false;
                        for (int a = india + 9; a <= india + 9 && a % 8 != 0 && a <= 63; a += 9)
                        {
                            if ((colors.get(a)).equals(elcolor))
                            {
                                stop = true;
                            }
                            if ((colors.get(a)).equals(""))
                            {
                                stop = true;
                            }
                            if (!stop)
                            {
                                possibleMoves2.add(a);
                            }
                            String colorOpp = "";
                            if ((colors.get(a)).equals("w"))
                            {
                                colorOpp = "b";
                            }
                            else if ((colors.get(a)).equals("b"))
                            {
                                colorOpp = "w";
                            }
                            if (colorOpp.equals(elcolor))
                            {
                                stop = true;
                            }
                        }
                        stop = false;
                        for (int a = india + 7; a <= india + 7 && a % 8 != 7 && a <= 63; a += 7)
                        {
                            if ((colors.get(a)).equals(elcolor))
                            {
                                stop = true;
                            }
                            if ((colors.get(a)).equals(""))
                            {
                                stop = true;
                            }
                            if (!stop)
                            {
                                possibleMoves2.add(a);
                            }
                            String colorOpp = "";
                            if ((colors.get(a)).equals("w"))
                            {
                                colorOpp = "b";
                            }
                            else if ((colors.get(a)).equals("b"))
                            {
                                colorOpp = "w";
                            }
                            if (colorOpp.equals(elcolor))
                            {
                                stop = true;
                            }
                        }
                    }
                }
        if (lapiece.equals("k"))
        {
            for (int a = india - 1; a % 8 != 7 && a >= india - 1; a -= 1)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india - 8; a >= 0 && a >= india - 8; a -= 8)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india - 9; a % 8 != 7 && a >= 0 && a >= india - 9; a -= 9)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india - 7; a >= 0 && a % 8 != 0 && a >= india - 7; a -= 7)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 1; a % 8 != 0 && a <= india + 1; a += 1)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 8; a <= 63 && a <= india + 8; a += 8)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 7; a % 8 != 7 && a <= 63 && a <= india + 7; a += 7)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 9; a % 8 != 0 && a <= 63 && a <= india + 9; a += 9)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            if (elcolor.equals("w"))
            {
                if (rooks.get(3))
                {
                    if ((board.get(61)).equals("") && (board.get(62)).equals(""))
                    {
                        if (!possibleMoves2.contains(62))
                        {
                            possibleMoves2.add(62);
                        }
                    }
                }
                if (rooks.get(2))
                {
                    if ((board.get(59)).equals("") && (board.get(58)).equals("") && (board.get(57)).equals(""))
                    {
                        if (!possibleMoves2.contains(58))
                        {
                            possibleMoves2.add(58);
                        }
                    }
                }
            }
            else if (elcolor.equals("b"))
            {
                        if (rooks.get(1))
                        {
                            if ((board.get(5)).equals("") && (board.get(6)).equals(""))
                            {
                                if (!possibleMoves2.contains(6))
                                {
                                    possibleMoves2.add(6);
                                }
                            }
                        }
                        if (rooks.get(0))
                        {
                            if ((board.get(3)).equals("") && (board.get(2)).equals("") && (board.get(1)).equals(""))
                            {
                                if (!possibleMoves2.contains(2))
                                {
                                    possibleMoves2.add(2);
                                }
                            }
                        }
                    }
        }
        if (lapiece.equals("kn"))
        {
            for (int a = india - 10; a >= 0 && a % 8 != 7 && a % 8 != 6 && a >= india - 10; a -= 10)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india - 17; a >= 0 && a % 8 != 7 && a >= india - 17; a -= 17)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india - 15; a % 8 != 0 && a >= 0 && a >= india - 15; a -= 15)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india - 6; a >= 0 && a % 8 != 0 && a % 8 != 1 && a >= india - 6; a -= 6)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 10; a % 8 != 0 && a % 8 != 1 && a <= 63 && a <= india + 10; a += 10)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 17; a <= 63 && a % 8 != 0 && a <= india + 17; a += 17)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 15; a % 8 != 7 && a <= 63 && a <= india + 15; a += 15)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
            stop = false;
            for (int a = india + 6; a % 8 != 7 && a % 8 != 6 && a <= 63 && a <= india + 6; a += 6)
            {
                        if ((colors.get(a)).equals(elcolor))
                        {
                            stop = true;
                        }
                        if (!stop)
                        {
                            possibleMoves2.add(a);
                        }
                        String colorOpp = "";
                        if ((colors.get(a)).equals("w"))
                        {
                            colorOpp = "b";
                        }
                        else if ((colors.get(a)).equals("b"))
                        {
                            colorOpp = "w";
                        }
                        if (colorOpp.equals(elcolor))
                        {
                            stop = true;
                        }
                    }
        }
    }
    
    public static void getActualMoves(int ind)
    {
        possibleMoves.clear();
                
                checkMoves(ind);
                String pece = board.get(ind);
                String colo = colors.get(ind);
                possibleMoves.addAll(possibleMoves2);
                possibleMoves3.clear();
                possibleMoves3.addAll(possibleMoves);
                
                for (int z = 0; z < possibleMoves.size(); z++)
                {
                    int b = possibleMoves.get(z);
                    
                    String oldP = board.get(b);
                    String oldC = colors.get(b);
                    
                    board.set(b, pece);
                    colors.set(b, colo);
                    board.set(ind, "");
                    colors.set(ind, "");
                    
                    for (int a = 0; a <= 63; a++)
                    {
                        checkMoves(a);
                        for (int y = 0; y < possibleMoves2.size(); y++)
                        {
                            int m = possibleMoves2.get(y);
                            if ((board.get(m)).equals("k") && !(colors.get(a)).equals(turn))
                            {
                                if ((colors.get(m)).equals(turn))
                                {
                                    if (possibleMoves.contains(b))
                                    {
                                        possibleMoves3.set(z, -1);
                                    }
                                    if (pece.equals("k"))
                                    {
                                        if (colo.equals("w"))
                                        {
                                            if (!possibleMoves3.contains(61))
                                            {
                                                if (possibleMoves3.contains(62))
                                                {
                                                    possibleMoves3.set(possibleMoves3.indexOf(62), -1);
                                                }
                                            }
                                            if (!possibleMoves3.contains(59))
                                            {
                                                if (possibleMoves3.contains(58))
                                                {
                                                    possibleMoves3.set(possibleMoves3.indexOf(58), -1);
                                                }
                                            }
                                        }
                                        else if (colo.equals("b"))
                                        {
                                            if (!possibleMoves3.contains(5))
                                            {
                                                if (possibleMoves3.contains(6))
                                                {
                                                    possibleMoves3.set(possibleMoves3.indexOf(6), -1);
                                                }
                                            }
                                            if (!possibleMoves3.contains(4))
                                            {
                                                if (possibleMoves3.contains(3))
                                                {
                                                    possibleMoves3.set(possibleMoves3.indexOf(3), -1);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    
                    //reset
                    board.set(ind, pece);
                    colors.set(ind, colo);
                    board.set(b, oldP);
                    colors.set(b, oldC);
                }
                
                String colorOpp = "";
                if (turn.equals("w"))
                {
                    colorOpp = "b";
                }
                else if (turn.equals("b"))
                {
                    colorOpp = "w";
                }
                
                for (int i = 0; i <= 63; i++)
                {
                    checkMoves(i);
                    if (colors.get(i).equals(colorOpp))
                    {
                        for (int k = 0; k < possibleMoves2.size(); k++)
                        {
                            int j = possibleMoves2.get(k);
                            if ((board.get(j)).equals("k") && colors.get(j).equals(turn))
                            {
                                if (possibleMoves3.contains(62))
                                {
                                    possibleMoves3.set(possibleMoves3.indexOf(62), -1);
                                }
                                if (possibleMoves3.contains(58))
                                {
                                    possibleMoves3.set(possibleMoves3.indexOf(58), -1);
                                }
                                if (possibleMoves3.contains(6))
                                {
                                    possibleMoves3.set(possibleMoves3.indexOf(6), -1);
                                }
                                if (possibleMoves3.contains(2))
                                {
                                    possibleMoves3.set(possibleMoves3.indexOf(2), -1);
                                }
                            }
                        }
                    }
                }
                
                possibleMoves3.removeAll(Collections.singletonList(-1));
                possibleMoves.clear();
                possibleMoves.addAll(possibleMoves3);
    }
    
    
    
    
    private class Mouse extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            int square_x = (e.getX() - e.getX() % HSIZE)/HSIZE;
            int square_y = (e.getY() - e.getY() % VSIZE)/VSIZE;
            int dummyindex = square_x + square_y * 8;
            String dummyColor = "";
            if (dummyindex <= 63)
            {
                dummyColor = colors.get(dummyindex);
            }
            
            if ((dummyColor.equals(turn) || pieceClicked) && dummyindex <= 63)
            {
            
            if (index == -1)
            {
                index = square_x + square_y * 8;
                
                piece = board.get(index);
                color = colors.get(index);
                //System.out.println(piece);
                //System.out.println(color);
                
                pieceClicked = true;
            }
            else
            {
                oldindex = index;
                index = square_x + square_y * 8;
                
                oldPiece = board.get(oldindex);
                oldColor = colors.get(oldindex);
                //System.out.println(oldPiece);
                //System.out.println(oldColor);
                
                piece = board.get(index);
                color = colors.get(index);
                //System.out.println(piece);
                //System.out.println(color);
                
                if (pieceClicked)
                {
                    if (possibleMoves.contains(index))
                    {
                        movePiece();
                        pieceClicked = false;
                        //
                        
                        
                    }
                    else
                    {
                        pieceClicked = false;
                    }
                }
                else
                {
                    pieceClicked = true;
                }
            }
            
            }
            
            
            
            
            //System.out.println("" + square_x + ", " + square_y + ", " + index);
            //System.out.println(board);
            //System.out.println(colors);
        }
    }
}
