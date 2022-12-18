import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;


public class FractalExplorer{
    private int displaySize;
    private JImageDisplay display;

    private FractalGenerator fractal;

    private Rectangle2D.Double range;
    public FractalExplorer(int size) {
        displaySize = size;
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);

    }

    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal Explorer");

        frame.add(display, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        ButtonHandler resetHandler = new ButtonHandler();
        resetButton.addActionListener(resetHandler);

        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(resetButton);
        frame.add(myBottomPanel, BorderLayout.SOUTH);


        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void drawFractal()
    {
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){

                double xCoord = FractalGenerator.getCoord(range.x,
                        range.x + range.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(range.y,
                        range.y + range.height, displaySize, y);


                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();
            if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
        }
    }
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = FractalGenerator.getCoord(range.x,
                    range.x + range.width, displaySize, x);

            int y = e.getY();
            double yCoord = FractalGenerator.getCoord(range.y,
                    range.y + range.height, displaySize, y);

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }
    
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}