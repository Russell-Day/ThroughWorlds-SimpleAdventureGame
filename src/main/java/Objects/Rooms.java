package Objects;

import Global.A;

public class Rooms extends Objects{
    private int n, s, w, e, gold;
    private double npc;
    private A area;

    public Rooms(int room_id, String Name, String Description, int N, int E, int S, int W, int gold, double npc, A area) {
        super(Name, Description); // init superclass
        this.n = N;
        this.s = S;
        this.w = W;
        this.e = E;
        this.gold = gold;
        this.npc = npc;
        this.area = area;
    }

    // North
    public int getN() {
        return n;
    }

    // South
    public int getS() {
        return s;
    }

    // East
    public int getE() {
        return e;
    }

    // West
    public int getW() {
        return w;
    }

    // area
    public A getArea(){
        return area;
    }

    /* Gold
    Forest 0-300
    Cave 100 - 500
    Underground 250 - 1000
    Water 0 - 100
    */
    public int getGold () {
        int available_gold=0;
        switch (area){
            case Forest:
            case Water:
                available_gold = ((int)(Math.random()*(gold)));
                break;
            case Cave:
                available_gold = ((int)(Math.random()*(gold-99)+100));
                break;
            case Underground:
                available_gold = ((int)(Math.random()*(gold-249)+250));
                break;
        }
        return available_gold;
    }

    // Npc Generator
    public boolean getNpc () {
        if (npc == 1){
            return true;
        } else if (npc == 0.5) {
            if (Math.random()>0.5){
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}












