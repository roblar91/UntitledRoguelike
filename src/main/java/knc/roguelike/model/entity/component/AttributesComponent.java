/*
 * Copyright (c) 2020 Robin Larsson
 * https://github.com/roblar91
 *
 */

package knc.roguelike.model.entity.component;

public class AttributesComponent extends Component {
    private int strength;
    private int agility;
    private int constitution;
    private int knowledge;
    private int devotion;

    public AttributesComponent(int strength, int agility, int constitution, int knowledge, int devotion) {
        this.strength = strength;
        this.agility = agility;
        this.constitution = constitution;
        this.knowledge = knowledge;
        this.devotion = devotion;
    }

    public AttributesComponent() {
        this(10, 10, 10, 10, 10);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public int getDevotion() {
        return devotion;
    }

    public void setDevotion(int devotion) {
        this.devotion = devotion;
    }

    @Override
    public Type getType() {
        return Type.ATTRIBUTES;
    }
}
