import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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

        JComboBox ComBox = new JComboBox();
        FractalGenerator mandelbrot = new Mandelbrot();
        ComBox.addItem(mandelbrot);
        FractalGenerator tricorn = new Tricorn();
        ComBox.addItem(tricorn);
        FractalGenerator burningShip = new BurningShip();
        ComBox.addItem(burningShip);

        ButtonHandler fractalChooser = new ButtonHandler();
        ComBox.addActionListener(fractalChooser);

        JPanel myPanel = new JPanel();
        JLabel label = new JLabel("Fractal:");
        myPanel.add(label);
        myPanel.add(ComBox);
        frame.add(myPanel, BorderLayout.NORTH);


        JPanel myBottomPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        myBottomPanel.add(resetButton);
        myBottomPanel.add(saveButton);
        frame.add(myBottomPanel, BorderLayout.SOUTH);

        ButtonHandler saveHandler = new ButtonHandler();
        saveButton.addActionListener(saveHandler);

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
            if(e.getSource() instanceof JComboBox){
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if(command.equals("Save")){
                JFileChooser myFile = new JFileChooser();
                FileFilter extensionFilter = new FileNameExtensionFilter("png", "png");
                myFile.setFileFilter(extensionFilter);
                myFile.setAcceptAllFileFilterUsed(false);
                int selection = myFile.showSaveDialog(display);
                if(selection == JFileChooser.APPROVE_OPTION){
                    java.io.File file = myFile.getSelectedFile();
                    String fileName = file.toString();
                    try{
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }
                    catch (Exception exception){
                        JOptionPane.showMessageDialog(display, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else return;
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