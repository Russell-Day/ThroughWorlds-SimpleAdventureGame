package Objects;

public class Player extends Objects {
    private Rooms room; // the Room where the Person is at present
    private int health;
    private final int Strength;
    private final int Dexterity;
    private final int Intelligence;
    private int gold;

    public Player(String Name, String Description, Rooms Room) {
        super(Name, Description); // init super class
        this.room = Room;
        health = 100;
        Strength = ((int)(Math.random()*(6)))+((int)(Math.random()*(6)))+((int)(Math.random()*(6)));
        Dexterity = ((int)(Math.random()*(6)))+((int)(Math.random()*(6)))+((int)(Math.random()*(6)));
        Intelligence = ((int)(Math.random()*(6)))+((int)(Math.random()*(6)))+((int)(Math.random()*(6)));
        gold = 0;
    }

    //Stats
    public int getStrength() {
        return Strength;
    }
    public int getDexterity() {
        return Dexterity;
    }
    public int getIntelligence() {
        return Intelligence;
    }

    //Health
    public int getHealth() {
        return health;
    }
    public void healHealth() {
        this.health = 100;
    }
    public void takeDamage(double damage){
        health = health-((int) damage);
    }

    //Gold
    public int getGold() {
        return gold;
    }
    public void setTotal(int gold) {
        this.gold += gold;
    }

    //Room
    public void setRoom(Rooms Room) {
        this.room = Room;
    }
    public Rooms getRoom() {
        return this.room;
    }
}


