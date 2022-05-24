package prev.phase.livean;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Liveness analysis.
 */
public class LiveAn extends Phase {

    public LiveAn() {
        super("livean");
    }

    public void compLifetimes() {
        HashMap<String, AsmLABEL> succ = new HashMap<>();
        for (Code code : AsmGen.codes) {
            for (AsmInstr instr : code.instrs)
                if (instr instanceof AsmOPER oper) {
                    oper.removeAllFromIn();
                    oper.removeAllFromOut();
                }

            // put labels in successors map, so I can access them later
            for (AsmInstr instr : code.instrs)
                if (instr instanceof AsmLABEL asmLABEL)
                    succ.put(asmLABEL.toString(), asmLABEL); // toString returns asmLABEL.label.name
        }

        for (Code code : AsmGen.codes)
            compLifetimes(code, succ);
    }

    private void compLifetimes(Code code, HashMap<String, AsmLABEL> succ) {
        boolean change = true;

        while (change) {
            change = false;
            int nextInstr = 1;
            for (AsmInstr asmInstr : code.instrs) {
                if (asmInstr instanceof AsmOPER instr) {
                    HashSet<MemTemp> oldIn = instr.in();
                    HashSet<MemTemp> oldOut = instr.out();

                    // calculate new in
                    HashSet<MemTemp> in = new HashSet<>(instr.out()); // add outs
                    instr.defs().forEach(in::remove); // remove defs
                    in.addAll(instr.uses()); // add uses

                    // calculate new out
                    HashSet<MemTemp> out = new HashSet<>();

                    if (!instr.jumps().isEmpty() && !instr.instr().contains("PUSHJ")) {
                        // there are jumps, so we take the instructions
                        // to which we jump to
                        for (MemLabel jLabel : instr.jumps()) {
                            AsmInstr labelInstr = succ.get(jLabel.name);
                            if (labelInstr != null)
                                out.addAll(labelInstr.in());
                        }
                    } else if (nextInstr < code.instrs.size()) {
                        // there are no jumps, so we take only the next instruction
                        // add all ins from the next instruction
                        out.addAll(code.instrs.get(nextInstr).in());
                    }

                    // remove all previous temps
                    instr.removeAllFromIn();
                    instr.removeAllFromOut();
                    // add new temps
                    instr.addInTemps(in);
                    instr.addOutTemp(out);

                    // check if previous and new ins and outs are changed
                    if (!in.equals(oldIn) || !out.equals(oldOut))
                        change = true;

                    // the last instruction is always jump, so we don't have to check
                    // if nextInstr will access index out of range in the else
                    // statement above
                    ++nextInstr;
                }
            }
        }
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
                logger.addAttribute("code", instr.toString());
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
