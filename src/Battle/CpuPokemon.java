package Battle;

import java.util.Random;

public class CpuPokemon extends Pokemon{

    private static Random random = new Random();

    public CpuPokemon(String name,int level,Spell first_spell,Spell second_spell)
    {
        super(name, level, first_spell, second_spell);
    }


    @Override
    public void castSpell(Pokemon enemy) {

        if(random.nextFloat() <= 0.5f)
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
        return "[CPU]" + super.toString();
    }
}
