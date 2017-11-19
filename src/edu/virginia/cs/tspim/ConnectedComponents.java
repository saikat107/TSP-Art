package edu.virginia.cs.tspim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.opencv.core.Core;

import edu.virginia.cs.tspim.util.Config;
import edu.virginia.cs.tspim.util.Util;

public class ConnectedComponents {
	
	
	public int countComponents(int n, int[][] edges) {
		//Util.logln(edges.length);
		//Util.logln(edges[0].length);
		
		if (n <= 0 || edges == null) {
			return 0;
		}

		if (n == 1 && edges.length == 0) {
			return 1;
		}

		int result = 0;
		boolean[] visited = new boolean[n];

		List[] adjList = new List[n];
		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];

			adjList[from].add(to);
			adjList[to].add(from);
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				result++;
				countCCHelper(i, adjList, visited, queue);
			}
		}

		return result;
	}

	private void countCCHelper(int node, List[] adjList, boolean[] visited, Queue<Integer> queue) {
		fill(node, adjList, visited, queue);

		while (!queue.isEmpty()) {
			int currNode = queue.poll();
			List<Integer> neighbors = adjList[currNode];

			for (Integer neighbor : neighbors) {
				fill(neighbor, adjList, visited, queue);
			}
		}
	}

	private void fill(int node, List[] adjList, boolean[] visited, Queue<Integer> queue) {
		if (visited[node]) {
			return;
		}

		visited[node] = true;

		queue.offer(node);
	}
	
}
