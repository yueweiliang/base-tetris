package game.blocks;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Random;
/** 
 * 单例模式的条块工厂
 */
public class BlockFactory{

	private static String[] TYPES= new String[]{"I","J","L","O","S","T","Z"};
	private static HashMap<String, Block> allBlocks= new HashMap<String, Block>();// 动态维护所有类型的条块实例
	
	/**
	 * 限定条块工厂能生产的条块类型
	 */
	public static boolean setBlockTypes(String[] types) {
	    if(types == null || types.length == 0){
	        return false;
	    }else {
	        TYPES = new String[types.length];
	        int i = 0;
	        for(String type: types) {
	            TYPES[i++] = type;
	        }
	        return true;
	    }
	}
	
	/**
	 * 随机构造某种类型的条块
	 */
	public static Block createBlock(){
		Random rand = new Random();
		Block block = createBlock(TYPES[rand.nextInt(TYPES.length*100)%TYPES.length]);
		System.out.println("条块工厂生产了 " + block.getType() +" 类型的条块：\n" + block.toString());
		return block;
	}

	/**
	 * 构造指定类型的条块
	 */
    public static Block createBlock(String typeName) {
        if (!contains(typeName))
            return null;
        Block block = allBlocks.get(typeName);
        if (block != null) {
            block.recover();// 将块复原到初始状态
            return block;
        }
        try {
            Constructor<?> con = Class.forName("game.blocks." + typeName).getConstructor();
            block = (Block) con.newInstance();
            allBlocks.put(typeName, block);
            return block;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean contains(String typeName) {
        for (String name : TYPES) {
            if (typeName == name)
                return true;
        }
        return false;
    }
}