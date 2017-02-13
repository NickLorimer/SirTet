//testing for I-0

public class Piece {
    
    //Array of coordinates for each of the pieces
    public int[][] pieces = {
    //I0
    {0,0,1,0,2,0,3,0},
    //I1
    {0,0,0,1,0,2,0,3},
    //J0
    {0,0,1,0,2,0,2,1},
    //J1
    {0,0,1,0,1,-1,1,-2},
    //J2
    {0,0,0,1,1,1,2,1},
    //J3
    {0,0,1,0,0,1,0,2},
    //O0
    {0,0,1,0,0,1,1,1},
    //L0
    {0,0,1,0,2,0,0,1},
    //L1
    {0,0,1,0,1,1,1,2},
    //L2
    {0,0,1,0,2,0,2,-1},
    //L3
    {0,0,0,1,0,2,1,2},
    //S0
    {0,0,1,0,1,-1,2,-1},
    //S1
    {0,0,0,1,1,1,1,2},
    //Z0
    {0,0,1,0,1,1,2,1},
    //Z1
    {0,0,0,1,1,0,1,-1},
    //T0
    {0,0,1,0,1,-1,2,0},
    //T1
    {0,0,0,1,0,2,1,1},
    //T2
    {0,0,1,0,1,1,2,0},
    //T3
    {0,0,1,0,1,-1,1,1}
    };
    
    //Just a constructor
    public Piece() {
        
    }
    
    /**
     * Method takes in a State, the starting point and the array that represents
     * the shape that is being placed into the state, updates the state
     * (removes completed columns and shifts the other pieces down)
     * Returns a new State
     */
    public State place (State state, int[] start, int[] coord) {
        State newState = new State(state);
        boolean valid = true;
        for (int i = 0; i < 7; i = i + 2) {
            int x = start[0] + coord[i];
            int y = start[1] + coord[i+1];
            //System.out.println(x + ", " + y);
            //Checks if the coordinates are out of bounds
            if(x < 0 || y < 0 || x > newState.xlen()-1 || y > newState.ylen()-1) {
                //System.out.println("Out of bounds!");
                valid = false;
                return null;
            }
            boolean canPut = newState.get(x,y);//checks if that square is open
            if(canPut) {
                newState.set(x,y,false); //"Places" the piece
            }
            else {
                valid = false;
                //System.out.println("No open pieces");
                return null;
            }
        }
        newState.update();//updates the state
        if (valid) {
            //returns the new State if it is a valid placement
            return newState;
        }
        return null;
    }
}