package game.blocks;

/**
 * 额外添加的条块类型B
 */
public class B extends Block{

	private static final int[][][] bMatrixs = new int[][][] {
		{
			{0,0,0,0},
			{1,1,0,0},
			{1,1,1,0},
			{0,0,0,0}
		},{
			{0,0,0,0},
			{1,1,0,0},
			{1,1,0,0},
			{1,0,0,0}
		},{
			{0,0,0,0},
			{1,1,1,0},
			{0,1,1,0},
			{0,0,0,0}
		},{
			{0,0,0,0},
			{0,0,1,0},
			{0,1,1,0},
			{0,1,1,0}
		}
	};
	
	public B() {
		this(0);
	}
	
	public B(int state) {
		super(bMatrixs[0]);
		this.state = state;
		this.matrixs = bMatrixs;
	}
	
	@Override
	public String getType() {
		return "B";
	}
}
