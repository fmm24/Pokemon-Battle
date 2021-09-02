package Battle;

public class Spell {

    private int strength;
    private String name;

    public Spell(int strength,String name)
    {
        this.strength = strength;
        this.name = name;
    }


    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public String toString()
    {
        return name + " " + strength;
    }
}
