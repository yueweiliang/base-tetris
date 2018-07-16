package game.blocks;

public class L extends Block{
	private static final int[][][] lMatrixs= new int[][][]{
		{
			{0,0,0,0},
			{1,0,0,0},
			{1,0,0,0},
			{1,1,0,0}
		},{
			{0,0,0,0},
			{0,0,0,0},
			{1,1,1,0},
			{1,0,0,0}
		},{
			{0,0,0,0},
			{1,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
		},{
			{0,0,0,0},
			{0,0,0,0},
			{0,0,1,0},
			{1,1,1,0}
		}
	};

	public L(){
		super(lMatrixs[0]);
		this.matrixs = lMatrixs;
		this.state = 0;
		this.type = "L";
	}

	public L(int state){
		super(lMatrixs[state]);
		this.matrixs = lMatrixs;
		this.state = state;
	}
	
}