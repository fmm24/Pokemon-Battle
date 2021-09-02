package Battle;

import sun.plugin2.gluegen.runtime.CPU;

public abstract class Pokemon implements Comparable<Pokemon>{


    private String name;
    private int level;
    private Spell first_spell;
    private Spell second_spell;
    int life_points = 100;
    private Spell last_cast_spell = null;


    public void setLastSpell(Spell last_cast_spell)
    {
        this.last_cast_spell = last_cast_spell;
    }

    public Spell getLast_cast_spell()
    {
        return last_cast_spell;
    }

    public Pokemon(String name,int level,Spell first_spell,Spell second_spell)
    {
        this.name = name;
        this.level = level;
        this.first_spell = first_spell;
        this.second_spell = second_spell;
    }

    public void dealDamage(int damage)
    {
        this.life_points = this.life_points - damage;
    }


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Spell getFirst_spell() {
        return first_spell;
    }

    public Spell getSecond_spell() {
        return second_spell;
    }

    public int getLife_points() {
        return life_points;
    }


    public String toString()
    {
        return "lvl" +this.level + " " + this.name;
    }


    public abstract void castSpell(Pokemon enemy);

    public int compareTo(Pokemon o)
    {

        if(this instanceof Player && o instanceof CpuPokemon)
        {
            return -1;
        }else if (this instanceof CpuPokemon && o instanceof Player)
        {
            return 1;
        }else if (this instanceof Player && o instanceof Player)
        {
            return Integer.compare(o.getLevel(),this.getLevel());
        }else if (this instanceof CpuPokemon && o instanceof CpuPokemon)
        {
            return Integer.compare(o.getLevel(),this.getLevel());
        }else
        {
            return Integer.compare(this.getLevel(),o.getLevel());
        }



    }

}
