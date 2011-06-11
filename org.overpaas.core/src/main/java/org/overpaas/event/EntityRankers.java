package org.overpaas.event;

import java.util.Comparator;

import org.overpaas.entities.Entity;

public class EntityRankers {

    private EntityRankers() {}
    
    public static Comparator<Entity> sensorComparator(final String sensorName) {
        return new Comparator<Entity>() {
            public int compare(Entity a, Entity b) {
                Object aMetric = a.getProperties().get(sensorName);
                Object bMetric = b.getProperties().get(sensorName);
                
                if (aMetric == null) {
                    return (bMetric != null) ? 1 : 0;
                } else if (bMetric == null) {
                    return -1;
                } else if (aMetric instanceof Comparable) {
                    return ((Comparable) aMetric).compareTo(bMetric);
                } else {
                    throw new IllegalArgumentException("Metric "+sensorName+" not comparable; value is "+aMetric);
                }
            }
        };
    }
}
