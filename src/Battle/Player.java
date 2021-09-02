package Battle;

public class Player extends Pokemon{


    private int selected_spell;


    public Player(String name, int level, Spell first_spell,Spell second_spell)
    {
        super(name, level, first_spell, second_spell);
        selected_spell = 1;
    }


    public void setSelected_spell(int i)
    {
        if (i == 1 || i == 2)
            this.selected_spell = i;
        else
            return;
    }

    public void castSpell(Pokemon enemy)
    {
        if(selected_spell == 1)
        {
            enemy.dealDamage(getFirst_spell().getStrength());
            setLastSpell(getFirst_spell());
        }
        else
        {
            enemy.dealDamage(getSecond_spell().getStrength());
            setLastSpell(getSecond_spell());
        }
    }

    public String toString()
    {
        return "[pokemon] " +super.toString();
    }

}
