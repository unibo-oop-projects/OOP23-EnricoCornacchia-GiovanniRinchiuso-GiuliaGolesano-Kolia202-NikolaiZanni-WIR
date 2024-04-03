package it.unibo.model.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.Entity;
import it.unibo.utilities.EntityType;

/**
 * EntityImpl
 */
public class EntityImpl implements Entity{

    private final EntityType type;
    private final Set<Component> components;
    private Pair<Double, Double> position;

    /**
     * EntityImpl constructor.
     *
     * @param type     the type of the entity
     * @param position the position of the entity
     */
    public EntityImpl(final EntityType type, final Pair<Double, Double> position){
        this.type = type;
        this.components = new HashSet<>();
        this.position = position;
    }

    @Override
    public void addComponent(Component component) {
        this.components.add(component);
    }

    @Override
    public Set<Component> getComponents() {
        return Collections.unmodifiableSet(this.components);
    }

    @Override
    public Optional<Component> getComponent(Class<? extends Component> componentType) {
        return this.components.stream().filter(componentType::isInstance).findFirst();
    }

    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {
        this.position = position;
    }

    @Override
    public EntityType getEntityType() {
        return this.type;
    }

    
}