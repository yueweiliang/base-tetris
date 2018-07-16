package game.blocks;

public class T extends Block{
	private static final int[][][] tMatrixs = new int[][][]{
		{
			{0,0,0,0},
			{0,1,0,0},
			{1,1,1,0},
			{0,0,0,0}
		},{
			{0,0,0,0},
			{0,1,0,0},
			{0,1,1,0},
			{0,1,0,0}
		},{
			{0,0,0,0},
			{0,0,0,0},
			{1,1,1,0},
			{0,1,0,0}
		},{
			{0,0,0,0},
			{0,1,0,0},
			{1,1,0,0},
			{0,1,0,0}
		}
	};

	public T(){
		super(tMatrixs[0]);
		this.matrixs = tMatrixs;
		this.state = 0;
		this.type = "T";
	}

	public T(int state){
		super(tMatrixs[state]);
		this.matrixs = tMatrixs;
		this.state = state;
	}
	
}