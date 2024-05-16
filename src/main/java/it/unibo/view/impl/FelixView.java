package it.unibo.view.impl;

import it.unibo.model.api.Entity;
import it.unibo.view.api.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class responsible for the view of Felix.
 */
public class FelixView implements View{

    private Image image;
    private Entity felix;

    public FelixView(final Entity felix) {
        this.felix = felix;
        this.image = getSource("Felix.png");
    }

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(image, felix.getPosition().getX(), felix.getPosition().getY());
    }

    @Override
    public Image getSource(String name) {
        return new Image(name);
    }
}
