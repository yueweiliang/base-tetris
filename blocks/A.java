package game.blocks;

/**
 * 额外添加的条块类型A
 */
public class A extends Block{

	private final static int[][][] aMatrixs = new int[][][] {
		{
			{0,0,0,0},
			{1,0,0,0},
			{1,1,0,0},
			{1,1,1,0}
		},{
			{0,0,0,0},
			{1,1,1,0},
			{1,1,0,0},
			{1,0,0,0}
		},{
			{0,0,0,0},
			{1,1,1,0},
			{0,1,1,0},
			{0,0,1,0},
		},{
			{0,0,0,0},
			{0,0,1,0},
			{0,1,1,0},
			{1,1,1,0}
		}
	};
	
	public A() {
		this(0);
	}
	
	public A(int state) {
		super(aMatrixs[state]);
		this.matrixs = aMatrixs;
		this.state = state;
	}
	
	@Override
	public String getType() {
		return "A";
	}
}
