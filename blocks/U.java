package game.blocks;

public class U extends Block{

	private static final int[][][] uMatrixs = new int[][][] {
		{
			{0,0,0,0},
			{1,0,1,0},
			{1,1,1,0},
			{0,0,0,0}
		},{
			{0,0,0,0},
			{0,1,1,0},
			{0,1,0,0},
			{0,1,1,0}
		},{
			{0,0,0,0},
			{0,0,0,0},
			{1,1,1,0},
			{1,0,1,0}
		},{
			{0,0,0,0},
			{1,1,0,0},
			{0,1,0,0},
			{1,1,0,0}
		}
	};
	
	public U() {
		super(uMatrixs[0]);
		this.state = 0;
		this.matrixs = uMatrixs;
		this.type = "U";
	}
	
	public U(int state) {
		super(uMatrixs[state]);
		this.state = state;
		this.matrixs = uMatrixs;
	}

}
