package hu.pe.routengo.entity;

import io.requery.Entity;


@Entity
public abstract class AbstractObjective {
    protected String type;
    protected String name;
    protected int imageId;

    public AbstractObjective() {
    }

    public AbstractObjective(String type, String name, int imageId) {
        this.name = name;
        this.type = type;
        this.imageId = imageId;
    }
}
