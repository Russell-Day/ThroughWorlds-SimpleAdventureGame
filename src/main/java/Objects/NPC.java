package Objects;

import Global.A;
import com.example.adventuregame.HelloController;

import java.util.jar.Attributes;

public class NPC extends Objects{
    private int hitPoints;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int damage_reduction;
    private int damage;
    private int gold;

    public NPC (String Name, String Description, A area){
        super (Name, Description);
        switch (area) {
            case Water -> {
                hitPoints = ((int) (Math.random() * (4)+1));
                damage_reduction = 2;
                gold = 50;
            }
            case Cave -> {
                hitPoints = ((int) (Math.random() * (6)+3));
                damage_reduction = 3;
                gold = 300;
            }
            case Underground -> {
                hitPoints = ((int) (Math.random() * (14)+5));
                damage_reduction = 4;
                gold = 750;
            }
            case Forest -> {
                hitPoints = ((int) (Math.random() * (4)+1));
                damage_reduction = 3;
                gold = 150;
            }
        }
        strength = hitPoints * 2;
        dexterity = hitPoints;
        intelligence = hitPoints * 2;
    }

    public int getHitPoints() {
        return hitPoints;
    }
    public int setHitPoints(Player player) {
        this.hitPoints -= (player.getStrength()/damage_reduction);
        damage = player.getStrength()/damage_reduction;
        return damage;
    }
    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public int getGold(){return gold;}
}
