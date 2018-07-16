package game.blocks;

public class J extends Block{

	protected static final int[][][] jMatrixs= new int[][][]{
		{
			{0,0,0,0},
			{0,1,0,0},
			{0,1,0,0},
			{1,1,0,0}
		},{
			{0,0,0,0},
			{0,0,0,0},
			{1,0,0,0},
			{1,1,1,0}
		},{
			{0,0,0,0},
			{1,1,0,0},
			{1,0,0,0},
			{1,0,0,0},
		},{
			{0,0,0,0},
			{0,0,0,0},
			{1,1,1,0},
			{0,0,1,0}
		}
	};

    public J() {
        super(jMatrixs[0]);
        this.matrixs = jMatrixs;
        this.state = 0;
        this.type = "J";
    }

    public J(int state) {
        super(jMatrixs[state]);
        this.matrixs = jMatrixs;
        this.state = state;
    }

}