package game.blocks;

public class O extends Block{

	private static final int[][][] oMatrixs = new int[][][]{
		{
			{0,0,0,0},
			{0,1,1,0},
			{0,1,1,0},
			{0,0,0,0}
		}
	};
	
	public O(){
		super(oMatrixs[0]);
		this.matrixs = oMatrixs;
		this.state = 0;
		this.type = "L";
	}

	/**
	 * O形条块只有一种状态，覆盖掉基类的选择方法
	 */
	@Override
	public void rotate(boolean direction){
		return;
	}

}