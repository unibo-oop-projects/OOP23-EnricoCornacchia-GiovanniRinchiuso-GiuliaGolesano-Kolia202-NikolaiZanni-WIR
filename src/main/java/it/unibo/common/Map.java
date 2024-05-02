package it.unibo.common;

/**
 * Class for a Map.
 */
public class Map<E,P> {
    
    private final E entity;
    private final P pos;
    /**
     * Constructor.
     * @param entity
     * @param pos
     */
    public Map(final E entity, final P pos){
        this.entity=entity;
        this.pos=pos;
    }
    /**
     * Getter of the entity.
     * 
     * @return the entity
     */
    public final E getEntity(){
        return this.entity;
    }
    /**
     * Getter of the position.
     * 
     * @return the position
     */
    public final P getPos(){
        return this.pos;
    }
}
