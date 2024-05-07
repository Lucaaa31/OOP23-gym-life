package gymlife.view.bankgame;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import gymlife.controller.BankGameController;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serial;

/**
 * This class groups all the panels and shows them on screen.
 */
public final class BankGameView extends JLayeredPane {
    @Serial
    private static final long serialVersionUID = -3972452455820596601L;

    //BankGameController controller;
    //float num;

    /**
     * This method sets the dimensions of the plane image and the sky image,
     * moreover it sets the images' layering.
     */
    public BankGameView(BankGameController controller) {
        final TextLabelView numberLabel = new MultiplierGameView();
        final ImageLabelView planeLayer = new AirplaneGameView();
        final ImageLabelView skyLayer = new SkyGameView();
        final JButton button = new JButton();

        this.add(skyLayer, JLayeredPane.DEFAULT_LAYER);
        this.add(planeLayer, JLayeredPane.PALETTE_LAYER);
        this.add(numberLabel, JLayeredPane.MODAL_LAYER);
        this.add(button, JLayeredPane.MODAL_LAYER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                setLayersNewSize(skyLayer, planeLayer, numberLabel, button);
            }
        }); 
        

        /*
         *  //Test
        if (numberLabel instanceof MultiplierGameView mpgv){
            mpgv.updateText(0.2534f);
        }
         */
       

        this.setVisible(true);
    }
    

    private void setLayersNewSize(final ImageLabelView skyLabel, final ImageLabelView planeLabel,
            final TextLabelView numberLabel, final JButton button) {
        final Dimension newSize = this.getSize();
        skyLabel.setBounds(0, 0, newSize.width, newSize.height);
        skyLabel.reload();
        button.setBounds(newSize.width / 35,
        newSize.height / 2, newSize.height / 4,
        newSize.height / 11);
        planeLabel.setBounds(newSize.width / 4,
                newSize.height / 4, newSize.height / 2,
                newSize.height / 2);
        planeLabel.reload();
        numberLabel.setBounds(newSize.width / 3,
                newSize.height / 3, newSize.height / 1,
                newSize.height / 1);
        numberLabel.reload();
    }
}

