package prev.phase.regall;

import java.util.*;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;
import prev.phase.livean.LiveAn;

/**
 * Register allocation.
 */
public class RegAll extends Phase {
	
	/** Mapping of temporary variables to registers. */
	public static final HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();
	private final int nregs;

	public RegAll(int nregs) {
		super("regall");
		this.nregs = nregs;
	}

	public void allocate() {
		for (Code code : AsmGen.codes) {
			Graph originalGraph;
			while(true) {
				originalGraph = buildInterferenceGraph(code);
				Graph g = buildInterferenceGraph(code);
				Stack<MemTemp> memTempStack = new Stack<>();

				while(true) {
					simplify(g, this.nregs, memTempStack);
					if (g.graph.isEmpty())
						break;
					spill(g, memTempStack);
				}

				MemTemp coloring = select(originalGraph, memTempStack);

				if (coloring == null) {
					//System.out.println("Coloring successful.");
					// coloring is successful
					break;
				} else {
					// can't color a graph, we need to change instructions
					//System.out.println("Cannot color the graph, actual spill at: " + coloring);
					//System.out.println("Altering code");
					alterCode(code, coloring);

					LiveAn liveAn = new LiveAn();
					liveAn.compLifetimes();
				}
			}

			for (Node node : originalGraph.graph.values()) {
				tempToReg.put(node.memTemp, node.color);
			}
			tempToReg.put(code.frame.FP, 253);
		}
	}

	// Build (Interferencni graf)
	private Graph buildInterferenceGraph(Code code) {
		Graph g = new Graph();
		for (AsmInstr instr : code.instrs) {
			for (MemTemp out : instr.out()) {
				if (g.contains(out)) {
					Node node = g.getNode(out);
					node.addAllNeighbors(instr.out());
				} else {
					Node node = new Node(out, instr.out());
					g.addNode(node);
				}
			}
			for (MemTemp def : instr.defs()) {
				if (g.contains(def)) {
					Node node = g.getNode(def);
					node.addAllNeighbors(instr.defs());
				} else {
					Node node = new Node(def, new HashSet<>(instr.defs()));
					g.addNode(node);
				}
			}
		}

		// We do not color Frame Pointer
		for (MemTemp tmp: g.graph.keySet()) {
			g.graph.get(tmp).removeNeighbor(code.frame.FP);
		}
		g.removeNode(code.frame.FP);

		return g;
	}

	// Simplify (Poenostavitev)
	private void simplify(Graph g, int nregs, Stack<MemTemp> deletedFromGraph) {
		boolean nodePullOut = true;
		while(nodePullOut) {
			nodePullOut = false;

			HashSet<MemTemp> temps = new HashSet<>(g.graph.keySet());
			for (MemTemp tmp : temps) {
				Node node = g.graph.get(tmp);
				if (node.neighbors.size() < nregs) {
					g.removeNode(node.memTemp);
					deletedFromGraph.push(node.memTemp);
					nodePullOut = true;
				}
			}
		}
	}

	// Spill (Dolocitev preliva)
	private void spill(Graph g, Stack<MemTemp> stack) {
		MemTemp spillTemp = g.graph.keySet().iterator().next();
		Node spillNode = g.getNode(spillTemp);

		stack.push(spillTemp);

		g.removeNode(spillTemp);
	}

	// Select (Barvanje)
	private MemTemp select(Graph g, Stack<MemTemp> stack) {
		while (!stack.isEmpty()) {
			MemTemp temp = stack.pop();
			Node node = g.getNode(temp);

			boolean[] colors = new boolean[this.nregs];
			for (MemTemp neighbor : node.neighbors) {
				Node neighborNode = g.getNode(neighbor);
				if (neighborNode.color > -1)
					colors[neighborNode.color] = true;
			}

			boolean canBeColored = false;
			for (int i = 0; i < this.nregs; i++) {
				if (!colors[i]) {
					node.color = i;
					canBeColored = true;
					break;
				}
			}
			// actual spill
			// we need to rewrite some instructions
			if (!canBeColored) {
				return temp;
			}
		}
		return null;
	}

	private void alterCode(Code code, MemTemp t) {
		// we need to change instructions of temp variable to color the graph
		// 16 for return value and old frame pointer
		// 8 for new temp variable
		code.tempSize += 8;
		// positive offset because we negate it later
		long offset = code.frame.locsSize + 16 + code.tempSize;
		Vector<AsmInstr> newInstructions = new Vector<>();

		for (AsmInstr instr : code.instrs) {
			// temp variable is in defs of instruction
			/*
			if (t.toString().equals("T516")) {
				System.out.println(t);
				System.out.println("defs");
				for (MemTemp tem: instr.defs()) {
					System.out.print(" " + tem + " ");
					System.out.println(instr.defs().contains(t));
				}
				System.out.println("uses");
				for (MemTemp tem: instr.uses()) {
					System.out.print(" " + tem + " ");
					System.out.println(instr.uses().contains(t));
				}
			}
			*/
			if (instr.defs().contains(t)) {
				MemTemp newMemTemp1 = new MemTemp();
				MemTemp newMemTemp2 = new MemTemp();

				// new instruction
				Vector<MemTemp> defs = new Vector<>();
				defs.add(newMemTemp1);
				AsmInstr newInstr = new AsmOPER(((AsmOPER)instr).instr(), instr.uses(), defs, instr.jumps());
				newInstructions.add(newInstr);

				// set offset
				Vector<MemTemp> defs2 = new Vector<>();
				defs2.add(newMemTemp2);
				AsmInstr setOffset = new AsmOPER(String.format("SET `d0,%d", offset), null, defs2, null);
				AsmInstr negateOffset = new AsmOPER("NEG `d0,`s0", defs2, defs2, null);
				newInstructions.add(setOffset);
				newInstructions.add(negateOffset);

				// store variable in memory
				Vector<MemTemp> uses = new Vector<>();
				uses.add(newMemTemp1);
				uses.add(code.frame.FP);
				uses.add(newMemTemp2);
				AsmInstr storeInMemory = new AsmOPER("STO `s0,`s1,`s2", uses, null, null);
				newInstructions.add(storeInMemory);

				/*
				System.out.println("Old instruction");
				System.out.println(instr);
				System.out.println("New instruction");
				System.out.println(newInstr);
				System.out.println("Offset: " + setOffset);
				System.out.println("Store: " + storeInMemory);
				*/
			}
			// temp variable is in uses of instruction
			if (instr.uses().contains(t)) {
				MemTemp newMemTemp1 = new MemTemp();
				MemTemp newMemTemp2 = new MemTemp();

				// set offset
				Vector<MemTemp> defs = new Vector<>();
				defs.add(newMemTemp1);
				AsmInstr setOffset = new AsmOPER(String.format("SET `d0,%d", offset), null, defs, null);
				AsmInstr negateOffset = new AsmOPER("NEG `d0,`s0", defs, defs, null);
				newInstructions.add(setOffset);
				newInstructions.add(negateOffset);

				// read from memory
				Vector<MemTemp> uses = new Vector<>();
				Vector<MemTemp> defs2 = new Vector<>();
				uses.add(code.frame.FP);
				uses.add(newMemTemp1);
				defs2.add(newMemTemp2);
				AsmInstr readFromMemory = new AsmOPER("LDO `d0,`s0,`s1", uses, defs2, null);
				newInstructions.add(readFromMemory);

				// new instruction
				Vector<MemTemp> uses2 = new Vector<>();
				for (MemTemp tmp : instr.uses()) {
					if (tmp.equals(t))
						uses2.add(newMemTemp2);
					else
						uses2.add(tmp);
				}
				AsmInstr newInstr = new AsmOPER(((AsmOPER)instr).instr(), uses2, instr.defs(), instr.jumps());
				newInstructions.add(newInstr);
			}
			// temp variable is not in this instruction
			if (!instr.uses().contains(t) && !instr.defs().contains(t)) {
				newInstructions.add(instr);
			}
		}
		code.instrs.clear();
		code.instrs.addAll(newInstructions);
	}
	
	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString(tempToReg));
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
