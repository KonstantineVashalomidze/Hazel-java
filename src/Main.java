import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main
{
    public static void main(String[] args)
    {
        new FontFrame().setVisible(true);

    }
}


class FontFrame
    extends JFrame
{
    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 10;

    private JComboBox<String> face;
    private JComboBox<Integer> size;
    private JCheckBox bold;
    private JCheckBox italic;
    private JTextArea sample;

    public FontFrame()
    {
        var layout = new GridBagLayout();
        this.setLayout(layout);

        ActionListener listener = event -> this.updateSample();

        // Construct components

        var faceLabel = new JLabel("Face: ");

        this.face = new JComboBox<>(new String[] { "Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput" });

        this.face.addActionListener(listener);

        var sizeLabel = new JLabel("Size: ");

        this.size = new JComboBox<>(new Integer[] { 8, 10, 12, 15, 18, 24, 36, 48 });

        this.size.addActionListener(listener);

        this.bold = new JCheckBox("Bold");
        this.bold.addActionListener(listener);

        this.italic = new JCheckBox("Italic");
        this.italic.addActionListener(listener);

        this.sample = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        this.sample.setText("The quick brown fox jumps over the lazy dog");
        this.sample.setLineWrap(true);
        this.sample.setBorder(BorderFactory.createEtchedBorder());

        // Add components to grid, using GBC convenience class

        this.add(faceLabel, new GBC(0, 0).setAnchor(GBC.EAST));
        this.add(this.face, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
        this.add(sizeLabel, new GBC(0, 1).setAnchor(GBC.EAST));
        this.add(this.size, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
        this.add(this.bold, new GBC(0, 2, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        this.add(this.italic, new GBC(0, 3, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        this.add(this.sample, new GBC(2, 0, 1, 4).setFill(GBC.BOTH).setWeight(100, 100));
        this.pack();
        this.updateSample();

    }

    public void updateSample() {
        var fontFace = (String) this.face.getSelectedItem();
        int fontStyle = (this.bold.isSelected() ? Font.BOLD : 0)
                + (this.italic.isSelected() ? Font.ITALIC : 0);
        int fontSize = this.size.getItemAt(this.size.getSelectedIndex());
        var font = new Font(fontFace, fontStyle, fontSize);
        this.sample.setFont(font);
        this.sample.repaint();

    }

}



class GBC
    extends GridBagConstraints
{
    public GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    public GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    public GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }


    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }


    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }


}



