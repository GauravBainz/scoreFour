package scoreFour.Board;

public class Peg {
    private BeadColour[] Peg;
    private int height;

    //constructor
    public Peg(){
        Peg = new BeadColour[4];
        height = 0;
    }
    public int height(){//returns height of beads
        return height;
    }
    public int numberOfBlack(){ //returns number of black beads
        //enhanced for-loop, and returning the count
        int count = 0;
        for(BeadColour bead : Peg){
            if(bead == BeadColour.BLACK){
                count++;
            }
        }
        return count;
    }
    public int numberOfWhite(){ //returns number of white beads
        //enhanced for loop that returns the count
        int count = 0;
        for(BeadColour bead : Peg){
            if(bead == BeadColour.WHITE){
                count++;
            }
        }
        return count;
    }
    public boolean isEmpty(){ //will check if there are any beads on the spike
        if(height==0){
            return true;
        }
        else
            return false;

    }
    public boolean isFull(){ //will check if there are 4 beads on the spike
        if(height == 4){
            return true;
        }
        else
            return false;
    }
    public BeadColour getColourAt(int k){ //checks colour of bead at given location
        if(k>= 0 && k< height){
            return Peg[k];
        }
        else{
            return null;
        }
    }
    public void clear(){ //clears the entire spike
        Peg = new BeadColour[4];
        height = 0;
    }
    public void addBead(BeadColour bead){ // adds a bead to the spike if it is not full
        if(!isFull()){
            Peg[height] = bead;
            height++;
        }
    }
    public boolean checkVerticalWin(){
        if(isFull()){
            if(numberOfBlack() ==4 || numberOfWhite() ==4){
                return true;
            }
        }
        return false;
    }
    public BeadColour getBeadAtLevel(int level) {
        if (level >= 0 && level < height) {
            return Peg[level];
        } else {
            return null;
        }
    }
}
