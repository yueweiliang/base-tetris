package game.blocks;

public class Z extends Block{
	private static final int[][][] zMatrixs =new int[][][]{
		{
			{0,0,0,0},
			{1,1,0,0},
			{0,1,1,0},
			{0,0,0,0}
		},{
			{0,0,0,0},
			{0,1,0,0},
			{1,1,0,0},
			{1,0,0,0}
		}
	};

	public Z(){
		super(zMatrixs[0]);
		this.matrixs = zMatrixs;
		this.state = 0;
		this.type = "Z";
	}

	public Z(int state){
		super(zMatrixs[state]);
		this.matrixs = zMatrixs;
		this.state = state;
	}

}