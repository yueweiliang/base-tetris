package game.blocks;

/**
 * 条块容器，主要维护一个方格填充矩阵 在某个位置有方块填充，则该处对应数组值为1，否则为0
 * 将某个条块加入加入容器中，即将对应条块的矩阵与容器矩阵对应位置值相加
 * 游戏地图的显示都是基于该类的填充矩阵进行相应的图形绘制
 * @author yeweiliang
 *
 */
public class GameMap {
	public static final int READY = 0; // 就绪态, 地图矩阵全0，无正在下落的条块(需添加条块), 分数为0,
	public static final int RUNNING = 1;// 运行态，有下落的条块
	public static final int OVER = 3;// 游戏结束，在调用init()矩阵重新初始化前，无法进行任何操作

	private int[][] map;
	private Block nowBlock; // 当前加入的条块
	private Block nextBlock; //下一个待加入条块
	private int[] position;// 用于描述条块block[0][0] 在map中的位置(行，列)
	private int count; // 数组所有值之和，即填充的方格数目
	private int runState;

	private int score = 0;

	public GameMap() {
		map = new int[20][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 0;
			}
		}
		count = 0;
		position = new int[2];
		this.runState = READY;
	}

	public void init() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 0;
			}
		}
		count = 0;
		score = 0;
		this.runState = READY;
	}

	public void start() {
		if(runState != READY) {
			return;
		}
		addBlock();
	}
	
	/**添加随机条块以及生成下一个条块*/
	private  synchronized void addBlock() {
		if(runState == READY) {
			addBlock(BlockFactory.createBlock());
		}else {
			addBlock(nextBlock);
		}
		if(runState != OVER) {
		 do{
			 nextBlock = BlockFactory.createBlock();
		 }while(nextBlock.equals(nowBlock));
		}
	}

	/**添加指定条块*/
	public void addBlock(Block block) {
		if (runState == OVER) {
			return;
		} else if (runState == READY) {
			this.runState = RUNNING;
		}
		this.nowBlock = block;
		position[0] = -1;
		position[1] = 3;
		int[][] matrix = block.getMatrix();
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
				if(matrix[i][j] == 1) {
					count ++;
				}
			}
		}
		
		if (!arrayAdd(matrix)) {
			count = calculateCount();
			runState = OVER;
			 System.out.println("Game Over!");
			System.out.println(this.toString());
		}
	}

	/**
	 * 条块旋转
	 * 
	 * @param direction
	 *            是否顺时针旋转
	 * @return
	 */
	public boolean rotate(boolean direction) {
		if (runState != RUNNING) {
			return false;
		}
		arraySubtract(nowBlock.getMatrix());
		nowBlock.rotate(direction);
		if (arrayAdd(nowBlock.getMatrix())) {
			return true;
		} else {
			nowBlock.rotate(!direction);
			arrayAdd(nowBlock.getMatrix());
			return false;
		}
	}

	/**
	 * 条块整体向左平移一个单位
	 * 
	 * @return 移动是否成功
	 */
	public boolean moveLeft() {
		if (runState != RUNNING) {
			return false;
		}
		int[][] matrix = nowBlock.getMatrix();
		arraySubtract(matrix);
		position[1] -= 1;
		if (!arrayAdd(matrix)) {
			position[1] += 1;
			arrayAdd(matrix);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 条块整体右移一个单位
	 * 
	 * @return
	 */
	public boolean moveRight() {
		if (runState != RUNNING) {
			return false;
		}
		int[][] matrix = nowBlock.getMatrix();
		arraySubtract(matrix);
		position[1] += 1;
		if (!arrayAdd(matrix)) {
			position[1] -= 1;
			arrayAdd(matrix);
			return false;
		}
		return true;
	}

	/**
	 * 条块整体向下移动一个单位
	 * 
	 * @return 若下降失败，表示该条块已经下降到底部，需要进行消行和添加下一个条块到容器中
	 */
	public boolean down() {
		if (runState != RUNNING) {
			return false;
		}
		int[][] matrix = nowBlock.getMatrix();
		arraySubtract(matrix);
		position[0] += 1;
		if (!arrayAdd(matrix)) {
			position[0] -= 1;
			arrayAdd(matrix);
			clearLine();
			addBlock();
			return false;
		}
		return true;
	}

	/**
	 * 降至底部
	 */
	public void bottom() {
		while (true) {
			if (!down()) {
				break;
			}
		}
	}

	/**
	 * 返回下一个待加入条块
	 */
	public Block nextBlock() {
		return nextBlock;
	}
	
	/**
	 * 返回当前正在操作的条块
	 */
	public Block nowBlock() {
		return nowBlock;
	}

	/**
	 * 获取当前容器的状态
	 */
	public int getRunState() {
		return runState;
	}

	/**
	 * 返回容器矩阵
	 */
	public int[][] getMap() {
		return map;
	}
	
	/**
	 * 获取当前分数
	 */
	public int getScore() {
		return score;
	}

	/** 强制消除一行并加分(作弊) */
	public void forceClearLine() {
		if(runState != RUNNING) {
			return ;
		}
		int cubeSum =0;
		for (int i = 0; i < map[0].length; i++) {
			cubeSum+= map[map.length-1][i];
		}
		if(cubeSum<1) { //无行可消
			return;
		}
		this.arraySubtract(nowBlock.getMatrix());
		for (int i = 0; i < map[0].length; i++) {
			map[map.length - 1][i] = 0 ;
		}
		for (int x = map.length - 1; x > 0; x--) {
			for (int y = 0; y < map[0].length; y++) {
				int temp = map[x][y];
				map[x][y] = map[x - 1][y];
				map[x - 1][y] = temp;
			}
		}
		count -= cubeSum;
		this.arrayAdd(nowBlock.getMatrix());
//		this.down();
		this.addScore();
		this.count = calculateCount();
	}

	/** 扫描矩阵,判断是否消行 */
	private int clearLine() {
		int clearCount = 0;
		for (int i = map.length - 1; i > 0; i--) {
			int cubeSum = 0;
			for (int j = 0; j < map[0].length; j++) {
				cubeSum += map[i][j];
			}
			if (cubeSum == map[0].length) {
				clearCount += 1;
				for (int k = 0; k < map[0].length; k++) { // 清零一行
					map[i][k] = 0;
				}
				this.addScore();
				/* 行交换 */
				for (int x = i; x > 0; x--) {
					for (int y = 0; y < map[0].length; y++) {
						int temp = map[x][y];
						map[x][y] = map[x - 1][y];
						map[x - 1][y] = temp;
					}
				}
				i = map.length; // 清除一行之后，重新从底部向上扫描
			}
		}
		count -= clearCount * map[0].length;
		return clearCount;
	}

	private void addScore() {
		score += 1;
	}

	/**
	 * 条块矩阵在容器中position指定的位置进行投影,即向容器中添加条块
	 * 
	 * @param matrix
	 * @return 是否添加成功
	 */
	private boolean arrayAdd(int[][] matrix) {
		int startCount = count;
		boolean isSuccess = true;
		for (int i = 0; i < matrix.length; i++) {
			if (i + position[0] < 0 || i + position[0] >= map.length) { // 行数越界检测
				continue;
			}
			for (int j = 0; j < matrix[i].length; j++) {
				if (j + position[1] < 0 || j + position[1] >= map[0].length) { // 列数越界检测
					continue;
				}
				map[i + position[0]][j + position[1]] += matrix[i][j];
				if (map[i + position[0]][j + position[1]] > 1) { // 碰撞检测(map数组中某个值大于1)
					isSuccess = false;
				}
			}
		}
		if (startCount != calculateCount()) {
			isSuccess = false;
		}
		/* 如果发生碰撞，回退到之前的状态 */
		if (!isSuccess) {
			arraySubtract(matrix);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 容器矩阵在position指定位置减去条块矩阵
	 * 
	 * @param matrix
	 */
	private void arraySubtract(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			if (i + position[0] < 0 || i + position[0] >= map.length) {
				continue;
			}
			for (int j = 0; j < matrix[i].length; j++) {
				if (j + position[1] < 0 || j + position[1] >= map[0].length) {
					continue;
				}
				map[i + position[0]][j + position[1]] -= matrix[i][j];
			}
		}

	}

	/**
	 * 计算当前填充的小方格数量
	 * 
	 * @return
	 */
	private int calculateCount() {
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				count += map[i][j];
			}
		}
		return count;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < map.length; i++) {
			buffer.append("| ");
			for (int j = 0; j < map[i].length; j++) {
				buffer.append(map[i][j] + " ");
			}
			buffer.append("|\n");
		}
		return buffer.toString();
	}
}