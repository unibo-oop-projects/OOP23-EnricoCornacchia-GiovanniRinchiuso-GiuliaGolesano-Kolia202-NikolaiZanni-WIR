package it.unibo.model.impl;

import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Brick;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;

/**
 * EntityFactoryImpl
 */
public class EntityFactoryImpl implements EntityFactory{

    @Override
    public Entity createFelix(Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFelix'");
    }

    @Override
    public Entity createRalph(Pair<Double, Double> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRalph'");
    }

    @Override
    public Entity createWindows(Set<Pair<Double, Double>> pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWindows'");
    }

    @Override
    public Entity createBrick(Pair<Double, Double> pos) {
        Entity brick = new Brick
        brick.setPosition(pos);
        return brick;
    }

    

}