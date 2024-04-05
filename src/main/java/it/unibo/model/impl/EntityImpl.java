package it.unibo.model.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.Movement;

/**
 * EntityImpl
 */
public class EntityImpl implements Entity{

    private Pair<Double, Double> position;

    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }

    @Override
    public Pair<Double, Double> setPosition(Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }

    
}