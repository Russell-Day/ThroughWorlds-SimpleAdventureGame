package com.example.adventuregame;

import Global.A;
import Global.D;
import Objects.NPC;
import Objects.Player;
import Objects.Rooms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HelloController {
    // Buttons
    @FXML
    private Button Sleep;
    @FXML
    private Button Help;
    @FXML
    private Button Search;
    @FXML
    private Button RunAway;
    @FXML
    private Button Fight;
    @FXML
    private Button End;

    // Text Boxes
    @FXML
    private Text ContentText;
    @FXML
    private Text RoomNumber;
    @FXML
    private Text HealthTracker;
    @FXML
    private Text strengthTag;
    @FXML
    private Text dexterityTag;
    @FXML
    private Text intelligenceTag;
    @FXML
    private Text contentText1;
    @FXML
    private Text goldCollected;
    @FXML
    private Text killedTag;
    // Split Panes
    @FXML
    private SplitPane helpOtherButtons;



    private ArrayList<Rooms> map; // the map
    private Player player;  // the player
    private NPC npc; // the npc
    private boolean active_fight = false;
    private boolean taken_loot = false;
    private D previous_direction;
    private int kills;
    private int total_actions;



    public HelloController() {
        this.map = new ArrayList<Rooms>(); // TODO: Make map a Generic list of Room
        // --- construct a new adventure ---
        // Add Rooms to the map
        //                 Room( room_id, name,   description,                                                                             N,        E,      S,      W,      Gold,       Npc,        Area)
        map.add(new Rooms(0, "Start Room", "Open large clearing in the forest with a path leading to the <East>.", D.NOEXIT, 1, D.NOEXIT, D.NOEXIT, 50, 0, A.Forest));
        map.add(new Rooms(1, "Forest Pathways 1", "A small clearing with three paths leading to the <East>, <South>, <West>.", D.NOEXIT, 2, 8 ,0, 0, 0, A.Forest));
        map.add(new Rooms(2, "Forest Cubby 1", "A little hidden dead-end with a cave attached, only one route leading back <West>.", D.NOEXIT, D.NOEXIT, D.NOEXIT, 1, 200,0.5, A.Forest));
        map.add(new Rooms(3, "Forest Cubby 2", "A dead-end with nothing special, but there is a small lake, only one route leading back <South>.", D.NOEXIT, D.NOEXIT, 9, D.NOEXIT, 50, 0.5, A.Forest));
        map.add(new Rooms(4, "Forest Entrance", "Just a turn in the road, two routes, one leading <East> and <South>.", D.NOEXIT, 5, 11, D.NOEXIT, 0, 0.5, A.Forest));
        map.add(new Rooms(5, "Forest Pathway 1", "To the <West> you see a normal pathway, but into the distance in the <East> you see a ginormous structure standing over the trees.", D.NOEXIT, 6, D.NOEXIT, 4, 200, 0, A.Forest));
        map.add(new Rooms(6, "Forest Grand Entrance", "You are standing right under a massive entrance to what seems to be a castle to the <East> and there is a pathway to the <West>.", D.NOEXIT, 7, D.NOEXIT, 5, 250, 0.5, A.Forest));
        map.add(new Rooms(7, "Forest Castle", "There is a massive seemingly abandoned castle like structure right in front as it intertwines with the surrounding trees almost as if the castle itself was living. There is a path to the <West>.", D.NOEXIT, D.NOEXIT, D.NOEXIT, 6, 300, 1, A.Forest));
        map.add(new Rooms(8, "Forest Pathway 2", "Just a pathway decide to go either <North> or <South>.", 1, D.NOEXIT, 12, D.NOEXIT, 0, 0, A.Forest));
        map.add(new Rooms(9, "Forest Pathways 2", "A fork in the road, there are paths going <North>, <East>, or <South>.", 3, 10, 15, D.NOEXIT, 100, 0, A.Forest));
        map.add(new Rooms(10, "Forest Pathway 3", "Just a pathway decide to go either <East> or <West>.", D.NOEXIT, 11, D.NOEXIT, 9, 0, 0.5, A.Forest));
        map.add(new Rooms(11, "Forest Pathways 3", "There is a fork in the road, decide to go either <North>, <South>, or <West>.", 4, D.NOEXIT, 16, 10, 0, 0, A.Forest));
        map.add(new Rooms(12, "Forest Pathway 4", "Just a pathway decide to go either <North> or <East>.", 8, 13, D.NOEXIT, D.NOEXIT, 0, 0, A.Forest));
        map.add(new Rooms(13, "Forest Pathway 5", "Another pathway in your journey. Decide to go either <East> or <West>.", D.NOEXIT, 14, D.NOEXIT, 12, 0, 0, A.Forest));
        map.add(new Rooms(14, "Forest Pathways 4", "A fork in the road, you can decide to go <East>, <South>, or <West>.", D.NOEXIT, 15, 20, 13, 100, 0, A.Forest));
        map.add(new Rooms(15, "Forest Pathway 6", "Just another pathway on your journey. You can go either <North> or <West>.", 9, D.NOEXIT, D.NOEXIT, 14, 50, 0, A.Forest));
        map.add(new Rooms(16, "Forest Entrance to Cave 1", "Fun! There is an enticing cave structure to the <South>, or you can choose to go <North> or <East> to stay in the Forest", 11, 17, 21, D.NOEXIT, 0, 0, A.Forest));
        map.add(new Rooms(17, "Forest Pathway 7", "Just another pathway. Decide to go either <East> or <West>.", D.NOEXIT, 18, D.NOEXIT, 16, 0, 0.5, A.Forest));
        map.add(new Rooms(18, "Forest Pathway 8", "Just another pathway. To the south there is a massive stone, but the only paths are to the <East> or <West>.", D.NOEXIT, 19, D.NOEXIT, 17, 100, 0, A.Forest));
        map.add(new Rooms(19, "Forest Entrance to Cave 2", "There is an entrance to the cave to the <South> and you can go <West> to stay in the forest.", D.NOEXIT, D.NOEXIT, 23, 18, 0, 0, A.Forest));
        map.add(new Rooms(20, "Forest Pathway 9", "Just another pathway leading either to the <North> or the <South>.", 14, D.NOEXIT, 27, D.NOEXIT, 0, 0.5, A.Forest));
        map.add(new Rooms(21, "Cave Cubby", "As your eyes slowly adjust to the darkness of the cave, you can see that the cave is massive with small structures littered throughout the cave and jewels lighting up the entirety from the ceiling. There is only a path leading to the forest <North>.", 16, D.NOEXIT, D.NOEXIT, D.NOEXIT, 500, 0.5, A.Cave));
        map.add(new Rooms(22, "Cave Pathway 1", "Just a pathway under the dimly lit cave! You can move <East> or <South>.", D.NOEXIT, 23, 29, D.NOEXIT, 100, 0, A.Cave));
        map.add(new Rooms(23, "Cave Forest Entrance", "There are two paths <North> leading to the forest and one to the <West> leading deeper into the Cave.", 19, D.NOEXIT, D.NOEXIT, 22, 100, 0.5, A.Cave));
        map.add(new Rooms(24, "Forest Entrance to Sea 1", "There are two paths one to the <South> leading into the ocean and one to the <East> to stay in the forest", D.NOEXIT, 25, 30, D.NOEXIT, 0, 0, A.Forest));
        map.add(new Rooms(25, "Forest Seafront 1", "You can still hear the water and smell the salt from the South, but theres still a giant rock just blocking your view. A path through the forest leading to the <East> and <West>.", D.NOEXIT, 26, D.NOEXIT, 24, 0, 0, A.Forest));
        map.add(new Rooms(26, "Forest Seafront 2", "Still hearing the water and smelling the salt from the South, still a giant rock. Forest path to the <East> and <West>.", D.NOEXIT, 27, D.NOEXIT, 25, 0, 0.5, A.Forest));
        map.add(new Rooms(27, "Forest Seafront 3", "To the South you can hear the sounds of water beating the boulder, smell the salt, and feel the slight breeze coming overhead. However only paths are to the <East> and <West>.", 20, 28, D.NOEXIT, 26, 200, 0, A.Forest));
        map.add(new Rooms(28, "Forest Entrance to Sea 2", "There are two paths! One leading to the <South> into the water and one to the <West> to stay in the forest.", D.NOEXIT, D.NOEXIT, 32, 27, 0, 0, A.Forest));
        map.add(new Rooms(29, "Cave Pathway 2", "Just another pathway although albeit very narrow, one to the <North> and to the <South>.", 22, D.NOEXIT, 34, D.NOEXIT, 100, 0, A.Cave));
        map.add(new Rooms(30, "Sea Entrance to Forest 1", "There are two paths, one leading <North> to the Forest and another leading deeper into the ocean to the <East>. You also notice you can now breath underwater?", 24, 31, D.NOEXIT, D.NOEXIT, 0, 0.5, A.Water));
        map.add(new Rooms(31, "Sea Cave", "Welcome to the sea cave! Not sure what your looking at, but there seems to be some sort of unnatural mechanism that seems to allow creature to breath underwater. That's probably why your fine right now. Only path is back to the <West>.", D.NOEXIT, D.NOEXIT, D.NOEXIT, 30, 100, 0.5, A.Water));
        map.add(new Rooms(32, "Sea Entrance to Forest 2", "Two paths, one leading to the forest in the <North> and one deeper into the ocean to the <South>. Also seems like you can breath underwater fine!! Interesting.", 28, D.NOEXIT, 35, D.NOEXIT, 25, 0.5, A.Water));
        map.add(new Rooms(33, "Cave Checkpoint", "You enter a clearing in the cave structure that looks almost 5 cubed miles big. Right in the middle you see a structure that looks like a stand with concentric squares leading out to the edges. Eerie. Only a path <South>", D.NOEXIT, D.NOEXIT, 37, D.NOEXIT, 200, 0.5, A.Cave));
        map.add(new Rooms(34, "Cave Pathway 3", "Just another pathway through the cave this time its human size so do not worry. Path leads to the <North> and <South>.", 29, D.NOEXIT, 39, D.NOEXIT, 100, 0, A.Cave));
        map.add(new Rooms(35, "Sea Entrance to Cave", "There is a entrance to the cave to the <East> and paths leading to the <North> and <South> if you want to stay in the Ocean.", 32, 36, 45, D.NOEXIT, 0, 0, A.Water));
        map.add(new Rooms(36, "Cave Entrance to Sea", "There is a entrance to the ocean to the <West> or you can journey deeper into the cave <East>.", D.NOEXIT, 37, D.NOEXIT, 35, 100, 0.5, A.Cave));
        map.add(new Rooms(37, "Cave Pathways 1", "A fork in the road. You can choose to go either <North>, <East>, and <West>.", 33, 38, D.NOEXIT, 36, 100, 0, A.Cave));
        map.add(new Rooms(38, "Cave Entrance to Deep Cave 1", "You can choose to go deeper into the deep cave to the <South> or stay in the cave by going to the <East> or <West>.", D.NOEXIT, 39, 46, 37, 100, 0.5, A.Cave));
        map.add(new Rooms(39, "Cave Pathways 2", "Another split in the path, you can decide to go to the <North>, <East>, and <West>.", 34, 40, D.NOEXIT, 38, 100, 0.5, A.Cave));
        map.add(new Rooms(40, "Cave Entrance to Deep Cave 2", "You can choose to go to the deep cave to the <South> or stay in the cave by going back to the <West>.", D.NOEXIT,D.NOEXIT, 47, 39, 100, 0.5, A.Cave));
        map.add(new Rooms(41, "Sea Pathway 1", "Another path way, choose to either go <East> or <South>.", D.NOEXIT, 42, 48, D.NOEXIT, 50, 0.5, A.Water));
        map.add(new Rooms(42, "Sea Pathway 2", "Just a pathway, choose to go either <East> or <West>.", D.NOEXIT, 43, D.NOEXIT, 41, 0, 0, A.Water));
        map.add(new Rooms(43, "Sea Pathway 3", "Oooo, this is a tight squeeze, choose to go either <East> or <West>.", D.NOEXIT, 44, D.NOEXIT, 43, 50, 0, A.Water));
        map.add(new Rooms(44, "Sea Pathways", "A fork in the road, or I guess in the sea! Do you want to go to the <East>, <South>, or <West>.", D.NOEXIT, 45, 49, 43, 0, 0, A.Water));
        map.add(new Rooms(45, "Sea Pathway 4", "Just another pathway, choose to go to the <North> or the <West>.", 35, D.NOEXIT, D.NOEXIT, 44, 0, 0.5, A.Water));
        map.add(new Rooms(46, "Deep Cave Entrance 1", "To the <North> you see a steep hill that leads to cave and to the <South> you can enter deeper into the underground cave.", 38, D.NOEXIT, 52, D.NOEXIT, 250, 0.5, A.Underground));
        map.add(new Rooms(47, "Deep Cave Entrance 2", "To the <North> you see a steep hill that leads to cave and to the <South> you can enter deeper into the underground cave.", 40, D.NOEXIT, 53, D.NOEXIT, 250, 0.5, A.Underground));
        map.add(new Rooms(48, "Sea Grand Entrance", "As you enter, all you see around you is a giant entrance to your <South> you see a nicely lit open path and to the <North> there is just open water.", 41, D.NOEXIT, 54, D.NOEXIT, 0,0,A.Water));
        map.add(new Rooms(49, "Sea Pathway 5", "Just another pathway, choose to go either <North> or <South>.", 44, D.NOEXIT, 56, D.NOEXIT, 0,0.5, A.Water));
        map.add(new Rooms(50, "Deep Cave Sea", "There is a monotonous dripping in the corner of the room that has formed a pool of water and you can hear the sea from above, just a small cubby in this system. You can only go to the <East>.", D.NOEXIT, 51, D.NOEXIT, D.NOEXIT, 450, 0.5, A.Underground));
        map.add(new Rooms(51, "Deep Cave Pathways", "Just another fork in the road, or I guess in the deep cave. You can go <East>, <South>, or <West>.", D.NOEXIT, 52, 57, 50, 250, 0.5, A.Underground));
        map.add(new Rooms(52, "Deep Cave Pathway 1", "Another pathway, choose to either go <North> or <West>.", 46, D.NOEXIT, D.NOEXIT, 51, 250, 0.5, A.Underground));
        map.add(new Rooms(53, "Deep Cave Pathway 2", "Another pathway, choose to either go <North> or <South>.", 47, D.NOEXIT, 59, D.NOEXIT, 500, 0.5, A.Underground));
        map.add(new Rooms(54, "Sea Pathway 6", "Just a path to the <North> you can see the massive gate and to the <East> you can see a large castle looming in the distance.", 48, 5, D.NOEXIT, D.NOEXIT, 0, 0.5, A.Water));
        map.add(new Rooms(55, "Sea Grand Castle", "You see a massive castle structure in front of you and at first glance it looks uninhabited by anything other than fish, but there is something off about the place you can feel it. You can only go back to the <West>.", D.NOEXIT, D.NOEXIT, D.NOEXIT, 54, 200, 0.5, A.Water));
        map.add(new Rooms(56, "Sea Castle", "There is a castle in front of you, seems broken down as if there was a massive fight here and it looks pretty empty. Can only go <North>.", 49, D.NOEXIT, D.NOEXIT, D.NOEXIT, 200, 0.5, A.Water));
        map.add(new Rooms(57, "Deep Cave Castle", "It seems there is a castle in front of you and it looks as if it was carved into the rock like the castle is integrated with its surroundings. You can only go <North>.", 51, D.NOEXIT, D.NOEXIT, D.NOEXIT, 500, 0.5, A.Underground));
        map.add(new Rooms(58, "Deep Cave Grand Castle", "The castle in front of you sprawls the span of many miles and is fully lit through the integrated use of these bioluminescent rock forms. You can only go <East>.", D.NOEXIT, 59, D.NOEXIT, D.NOEXIT, 1000, 0.5, A.Underground));
        map.add(new Rooms(59, "Deep Cave Grand Entrance", "The cave opens up to a massive perfectly carved gatelike entrance overhead. The ceiling looks like those inside a grand cathedral. To the <West> you can see some light and to the <North> just another pathway.", 53, D.NOEXIT, D.NOEXIT, 58, 750, 0.5, A.Underground));

        // End Room
        map.add(new Rooms(60, "END", "It is quite unfortunate that your journey came to an end so soon, but you have died. It was fun while it lasted!", D.NOEXIT, D.NOEXIT, D.NOEXIT, D.NOEXIT, 0, 0, A.Forest));

        // create player and place in Room 0
        player = new Player("player", "a loveable game-player", map.get(0));
    }

    public void initialize(){
        strengthTag.setText(player.getStrength()+ "   ");
        intelligenceTag.setText(player.getIntelligence()+ "   ");
        dexterityTag.setText(player.getDexterity()+ "   ");
        help();
    }

    // access methods
    // map
    ArrayList getMap() {
        return map;
    }
    void setMap(ArrayList aMap) {
        map = aMap;
    }

    // player
    public Player getPlayer() {
        return player;
    }

    // MOVEMENT
    void moveActorTo(Player p, Rooms aRoom) {
        p.setRoom(aRoom);
    }
    int moveTo(Player player, D dir) {
        Rooms r = player.getRoom();
        int exit;

        switch (dir) {
            case NORTH:
                exit = r.getN();
                break;
            case SOUTH:
                exit = r.getS();
                break;
            case EAST:
                exit = r.getE();
                break;
            case WEST:
                exit = r.getW();
                break;
            default:
                exit = D.NOEXIT; // noexit - stay in same room
                break;
        }
        if (exit != D.NOEXIT) {
            moveActorTo(player, map.get(exit));
        }
        return exit;
    }
    public int movePlayerTo(D dir) {
        return moveTo(player, dir);
    }
    private void goN() {
        updateOutput(movePlayerTo(D.NORTH));
    }
    private void goS() {
        updateOutput(movePlayerTo(D.SOUTH));
    }
    private void goW() {
        updateOutput(movePlayerTo(D.WEST));
    }
    private void goE() {
        updateOutput(movePlayerTo(D.EAST));
    }
    void updateOutput(int roomNumber) {
        // if roomNumber = NOEXIT, display a special message, otherwise
        // display text (e.g. name and description of room)
        String s;
        Rooms r = getPlayer().getRoom();
        if (roomNumber == D.NOEXIT) {
            s = "No Exit! You are in " + r.getName() + ". " + r.getDescription();
        } else {
            total_actions+=1;
            taken_loot = false;
            s = "You are in "
                    + r.getName() + ". " + r.getDescription();
            RoomNumber.setText(r.getName());
            active_fight = false;
            if (r.getNpc()){
                startFight(r,0);
                active_fight = true;
            }
        }
        ContentText.setText(s);
    } //Initializes Fight


    // Fight
    private void startFight(Rooms r, int reason){
        String Name="";
        String Description="";
        String s="";
        switch (r.getArea()){
            case Water -> {
                Name = "Mermaid";
                Description = "They seem to be humanoid creatures with a scaly tail that glistens under the sun. Very agile with Spears.";
            }
            case Cave -> {
                Name = "Dwarves";
                Description = "Humanoid creatures that are quite small, but very well built and seem smell like ashes and grime.";
            }
            case Underground -> {
                Name = "Dragon";
                Description = "A massive dragon, I have no clue how we are gonna defeat this thing. The wings are glistening under the crystals.";
            }
            case Forest -> {
                Name = "Dark Elves";
                Description = "Our pointy eared humanoid friends, but hmm they seem to have a dark complexion. Their main weapon seems to be a Bow and Arrow.";
            }
        }
        switch (reason){
            case 0: break;
            case 1: s = "Unlucky! Right as you close your eyes, you slowly see a shadow creeping up on you. Up quickly! Better respond to the threat before sleep!\n";
        }
        npc = new NPC(Name, Description,r.getArea());
        s+="An enemy has appeared! You are facing a " + npc.getName() +". " + npc.getDescription() +" You must now choose to <Fight> of <Run Away>!";
        contentText1.setText(s);
    }
    private void attack(){
        String s;
        int damage;
        int random = (int)(Math.random()*20+1);
        if (random >= npc.getDexterity()){
            damage = npc.setHitPoints(player);
            if (npc.getHitPoints()<=0){
                s = "You hit your enemy for "+damage+" and killed him! You also collected "+npc.getGold()+" gold from the kill. Great job you are free to continue to explore.";
                player.setTotal(npc.getGold());
                goldCollected.setText(player.getGold()+"");
                kills += 1;
                killedTag.setText(kills+"");
                active_fight = false;
            } else {
                s = "You successfully hit the enemy for " + damage + " hit points! It still has " + npc.getHitPoints() + " hit points left.";
                s += enemy_attack();
            }
        } else {
            s = "You did not hit the enemy! You only rolled a "+random+" which is lower than the monsters dexterity of "+npc.getDexterity()+" points. It still has "+npc.getHitPoints()+ " hit points left.";
            s += enemy_attack();
        }
        contentText1.setText(s);
    }
    private String enemy_attack(){
        String s = "";
        int damage;
        int random = (int)(Math.random()*20+1);
        if (random >= player.getDexterity()){
            player.takeDamage(npc.getStrength()/3);
            damage = npc.getStrength()/3;
            s = "\nYou have been hit by the "+npc.getName()+" and have taken " +damage+ " damage!";
            HealthTracker.setText(player.getHealth()+"");
            if (player.getHealth()<=0){
                end();
            }
        } else {
            s = "\nThe "+npc.getName()+" did not roll high enough to attack you, so you were not hit.";
        }
        return s;
    }
    public String getRunAwayAttack(Player player){
        String s="";
        if (((int)Math.random()*(20)+1)<npc.getIntelligence()){
            player.takeDamage((npc.getStrength()/3));
            int damage = npc.getStrength()/3;
            s = "\nWhile you were running "+npc.getName()+" saw you retreating! You have been hit and have taken " +damage+ " damage!";
            HealthTracker.setText(player.getHealth()+"");
            if (player.getHealth()<=0){
                end();
            }
        }else {
            s = "\nYou escaped successfully! The "+npc.getName()+" was too dumb and did not see you running away.";
        }
        return s;
    }

    //Gold
    private void searchGold(){
        String s;
        Rooms r = getPlayer().getRoom();
        int random = (int)(Math.random()*20+1);
        int gold;
        if (random<player.getIntelligence()){
            gold = r.getGold();
            player.setTotal(gold);
            goldCollected.setText(player.getGold()+"");
            s = "You rolled " + random + " which is less than your intelligence of "+player.getIntelligence()+" and found " +gold+ " gold!";
        } else {
            s = "You rolled " + random + " which is more than your intelligence of "+player.getIntelligence()+" so you were unable to find any gold.";
        }
        contentText1.setText(s);
    }

    // Run Away
    private void run_away(){
        String s ="";
        switch(previous_direction){
            case NORTH:
                goS();
                s = "You ran away to the South back to where you just were.";
                break;
            case EAST:
                goW();
                s = "You ran away to the East back to where you just were.";
                break;
            case SOUTH:
                goN();
                s = "You ran away to the North back to where you just were.";
                break;
            case WEST:
                goE();
                s = "You ran away to the East back to where you just were.";
                break;
        }
        s+=getRunAwayAttack(player);
        contentText1.setText(s);
    }

    // Sleep
    private void sleep(){
        Rooms r = getPlayer().getRoom();
        if (((int)Math.random()*5+1)==6){
            startFight(r,1);
        } else {
            contentText1.setText("You took a nice rest and healed back your lost health!");
            player.healHealth();
            HealthTracker.setText(player.getHealth()+"");
        }
    }

    // Help
    private void help(){
        contentText1.setText("""
                Goal: Collect as much gold as you can without dying
                Fight Sequences: Based on room, there is a chance of an Monster attacking you. Choose to either <Fight> or <Run Away> (Brings you to Last Room), you can also use directional buttons if you want.
                Stats: Your character stats determine how much damage you do, if you can hit an enemy, or if you can find gold in a room.
                Movement: Use the buttons in the bottom right corner <North>, <East>, <South>, and <West> to move through the different rooms.
                """);
        helpOtherButtons.setOpacity(1);
    }
    private void hideHelp(){
        helpOtherButtons.setOpacity(0);
    }

    // End
    private void end(){
        player.setRoom(map.get(60));
        Rooms r = getPlayer().getRoom();
        RoomNumber.setText(r.getName());
        ContentText.setText("You are at the " + r.getName() + ". " + r.getDescription());
        contentText1.setText("It has been quite the wild ride with a lot of ups and downs. You have made a total of "+total_actions+" actions, accrued over "+player.getGold()+" gold, and killed "+kills+" different enemies.");
    }

    //BUTTONS
    //movement
    @FXML
    public void onEastButtonClick(ActionEvent actionEvent) {
        contentText1.setText("");
        previous_direction = D.EAST;
        hideHelp();
        goE();
    }
    @FXML
    public void onWestButtonClick(ActionEvent actionEvent) {
        contentText1.setText("");
        previous_direction = D.WEST;
        hideHelp();
        goW();
    }
    @FXML
    public void onSouthButtonClick(ActionEvent actionEvent) {
        contentText1.setText("");
        previous_direction = D.SOUTH;
        hideHelp();
        goS();
    }
    @FXML
    public void onNorthButtonClick(ActionEvent actionEvent) {
        contentText1.setText("");
        previous_direction = D.NORTH;
        hideHelp();
        goN();
    }


    //Other
    @FXML
    public void onHelpButtonClick(ActionEvent actionEvent) {
        if (active_fight){
            ContentText.setText("You are currently in a fight! Please choose either a direction to run, <Fight>, or <Run Away>!");
        }
        help();
    }
    @FXML
    public void onSearchButtonClick(ActionEvent actionEvent) {
        hideHelp();
        if (active_fight) {
            ContentText.setText("You are currently in a fight! Please choose either a direction to run, <Fight>, or <Run Away>!");
        } else if (taken_loot){
            contentText1.setText("You have already taken the loot from this place during this current stay, please choose another action!");
        }else{
            searchGold();
            total_actions += 1;
            taken_loot = true;
        }
    }
    @FXML
    public void onFightButtonClick(ActionEvent actionEvent) {
        hideHelp();
        if (active_fight){
            attack();
            total_actions+=1;
        } else {
            contentText1.setText("There is no active fight right now, please choose another action!");
        }
    }
    @FXML
    public void onRunAwayButtonClick(ActionEvent actionEvent) {
        hideHelp();
        if (active_fight){
            run_away();
            total_actions+=1;
        } else {
            contentText1.setText("There is no active fight right now, please choose another action!");
        }
    }
    @FXML
    public void onSleepButtonClick(ActionEvent actionEvent) {
        hideHelp();
        if (active_fight){
            ContentText.setText("You are currently in a fight! Please choose either a direction to run, <Fight>, or <Run Away>!");
        } else if (player.getHealth()!=100){
            sleep();
            total_actions+=1;
            HealthTracker.setText(player.getHealth()+"");
        } else {
            contentText1.setText("You have full health, please choose another action!");
        }
    }
    @FXML
    public void onEndButtonClick(ActionEvent actionEvent) {
        end();
    }
}