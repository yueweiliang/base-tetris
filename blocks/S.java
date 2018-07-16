package game.blocks;

public class S extends Block{
	
	private static final int[][][] sMatrixs = new int[][][]{
		{
			{0,0,0,0},
			{0,1,1,0},
			{1,1,0,0},
			{0,0,0,0}
		},{
			{0,0,0,0},
			{1,0,0,0},
			{1,1,0,0},
			{0,1,0,0}
		}
	};

    public S() {
        super(sMatrixs[0]);
        this.matrixs = sMatrixs;
        this.state = 0;
        this.type = "S";
    }

    /** 构造指定状态的S条块 */
    public S(int state) {
        super(sMatrixs[state]);
        this.matrixs = sMatrixs;
        this.state = state;
    }
	
}