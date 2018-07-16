package game.blocks;

public class I extends Block {
	
	/**I条块所有状态结构*/
	private static final int[][][] iMatrixs = new int[][][]{
		{
			{0,0,0,0},
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0}
		},{
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0}
		}
	};

    public I() {
        super(iMatrixs[0]);
        this.matrixs = iMatrixs;
        this.state = 0;
        this.type = "I";
    }

    public I(int state) {
        super(iMatrixs[state]);
        this.matrixs = iMatrixs;
        this.state = state;
    }

}
