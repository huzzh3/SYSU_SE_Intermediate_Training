package solution;

import java.util.*;
import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	// Necessary variables
    	final int DIRECTION_NUM = 4;
    	
    	// Two queues are used to implement BFS
    	Queue<JigsawNode> open  = new LinkedList<JigsawNode>();
    	Queue<JigsawNode> close = new LinkedList<JigsawNode>();
    	
    	// Initialize the Jigsaw
    	this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        
        // Add the initial node to the 'open' queue
        open.offer(bNode);
        
        // BFS
        while (!open.isEmpty()) {
        	// Update current JNode and close queue
        	this.currentJNode = open.poll();
        	close.offer(this.currentJNode);
        	
        	// Determine whether currentJNode is endJNode
        	if (this.currentJNode.equals(this.endJNode)) {
        		this.getPath();
        		break;
        	}
        	
        	// Calculate how many directions can be moved
        	int[] movable = this.currentJNode.canMove();
        	
        	// Determine whether these states have been reached, 
        	// and add the unreached states to the open queue
        	for (int i = 0; i < DIRECTION_NUM; i++) {
        		if (movable[i] == 1) {
        			// We can move in this direction
        			// Determine whether the state haven't been reached
        			JigsawNode targetNode = new JigsawNode(this.currentJNode);
        			targetNode.move(i);
        			
        			if (close.contains(targetNode) == false) {
        				// Add the unreached state to the open queue
        				open.offer(targetNode);
        			}
        		}
        	}
        }
    	
        return this.isCompleted();
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
    	
    	// f = h + g
    	// h: Summation of the Manhattan distance between misplaced nodes
    	// g: s
    	int hScore    = 0;
    	for (int index = 1; index < dimension * dimension; index++) {
    		if (jNode.getNodesState()[index] != index && jNode.getNodesState()[index] != 0) {
    			// The tile is misplaced, get the row number and column number (1~5)
    			int tileRow = index / dimension + 1;
    			int tileCol = index % dimension;
    			if (tileCol == 0) {
    				// Remember to update column number (col in 1~5)
    				tileCol = dimension;
    			}
    			
    			// Get the correct row number and column number
    			int correctRow = jNode.getNodesState()[index] / dimension + 1;
    			int correctCol = jNode.getNodesState()[index] % dimension;
    			if (correctCol == 0) {
    				// Remember to update column number (col in 1~5)
    				correctCol = dimension;
    			}
    			
    			// Calculate the Manhattan distance
    			int manhattanDistance = Math.abs(tileRow - correctRow) + Math.abs(tileCol - correctCol);
    			hScore += manhattanDistance;
    		}
    	}
    	
    	int gScore = 0;
    	gScore = s;
    	
    	int fScore = 0;
    	fScore = hScore + gScore;
    	
    	jNode.setEstimatedValue(fScore);
    }
}
