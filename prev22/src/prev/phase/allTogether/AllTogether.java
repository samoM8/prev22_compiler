package prev.phase.allTogether;

import prev.Compiler;
import prev.data.asm.AsmInstr;
import prev.data.asm.AsmLABEL;
import prev.data.asm.AsmOPER;
import prev.data.asm.Code;
import prev.data.lin.LinDataChunk;
import prev.data.mem.MemTemp;
import prev.phase.Phase;
import prev.phase.asmgen.AsmGen;
import prev.phase.imclin.ImcLin;
import prev.phase.regall.RegAll;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class AllTogether extends Phase {

    private final String outputFile = Compiler.cmdLineArgValue("--dst-file-name");
    private final int nregs = Integer.decode(Compiler.cmdLineArgValue("--nregs"));
    // HP $252
    // FP $253
    // SP $254

    public AllTogether() {
        super("all");
    }

    private List<String> startOfProgram() {
        List<String> startOfProgram = new LinkedList<>();

        // location Data_segment
        startOfProgram.add("\t\tLOC\tData_Segment");
        // allocating global registers
        startOfProgram.add("\t\tGREG 0"); // $254 stack pointer
        startOfProgram.add("\t\tGREG 0"); // $253 frame pointer
        startOfProgram.add("\t\tGREG 0"); // $252 heap pointer
        startOfProgram.add("OutBuff\t\tOCTA\t0,0");
        startOfProgram.add("InSize\t\tIS\t32");
        startOfProgram.add("InBuff\t\tBYTE\t0");
        startOfProgram.add("\t\tLOC\tInBuff+InSize");
        startOfProgram.add("InArgs\t\tOCTA\tInBuff,InSize");

        // generate static data
        for (LinDataChunk chunk : ImcLin.dataChunks()) {
            if (chunk.init != null) {
                // string
                // for escaped characters we have to add ascii in the middle of string
                startOfProgram.add(chunk.label.name + "\t\tOCTA " + chunk.init + ",0");
            } else {
                StringBuilder instr = new StringBuilder(chunk.label.name + "\t\tOCTA ");
                for (int i = 0; i < chunk.size; i += 8) {
                    if (i == 0)
                        instr.append("0");
                    else
                        instr.append(",0");
                }
                startOfProgram.add(instr.toString());
            }
        }

        //reserve global space for every function
//        Every function can only be actively called 8-times
//        for (Code code : AsmGen.codes) {
//            startOfProgram.add(String.format("%s\t\tOCTA\t0", code.frame.label.name + "Count"));
//        }

        startOfProgram.add("\t\tLOC\t#100");
        // set stack pointer to 0x6000_0000_0000_0000
        startOfProgram.add("Main\t\tSETH\t$254,24576");

        // set frame pointer
        startOfProgram.add("\t\tADD\t$253,$254,16");

        // set heap pointer to 0x4000_0000_0000_0000
        startOfProgram.add("\t\tSETH $252,16384");

        // program starts
        startOfProgram.add("\t\tSET\t$0,252");
        startOfProgram.add("\t\tPUT\trG,$0");
        startOfProgram.add("\t\tPUSHJ\t$" + nregs + ",_main");
        startOfProgram.add("\t\tLDO\t$255,$254,0");
        startOfProgram.add("End\t\tTRAP\t 0,Halt,0");

        return startOfProgram;
    }

    private List<AsmInstr> loadConstToReg(int reg, long constant) {
        List<AsmInstr> instrs = new LinkedList<>();
        boolean negativeConstant = false;
        if (constant < 0) {
            negativeConstant = true;
            constant = -constant;
        }
        int low = (int) (constant & 0xFFFF);
        int mediumLow = (int) ((constant >> 16) & 0xFFFF);
        int mediumHigh = (int) ((constant >> 32) & 0xFFFF);
        int high = (int) ((constant >> 48) & 0xFFFF);

        instrs.add(new AsmOPER(String.format("SETL $%d,%d", reg, low), null, null, null));
        if (mediumLow > 0)
            instrs.add(new AsmOPER(String.format("INCML $%d,%d", reg, mediumLow), null, null, null));
        if (mediumHigh > 0)
            instrs.add(new AsmOPER(String.format("INCMH $%d,%d", reg, mediumHigh), null, null, null));
        if (high > 0)
            instrs.add(new AsmOPER(String.format("INCH $%d,%d", reg, high), null, null, null));

        if (negativeConstant)
            instrs.add(new AsmOPER(String.format("NEG $%d,$%d", reg, reg), null, null, null));

        return instrs;
    }

    private void prologue(Code code) {
        List<AsmInstr> prologue = new LinkedList<>();

        prologue.add(new AsmLABEL(code.frame.label));

        //Every function can only be actively called 8-times
        // add 1 to function called
//        prologue.add(new AsmOPER(String.format("\t\tLDA\t$0,%s", code.frame.label.name + "Count"), null, null, null));
//        prologue.add(new AsmOPER("\t\tLDO\t$1,$0,0", null, null, null));
//        prologue.add(new AsmOPER("\t\tADD\t$1,$1,1", null, null, null));
//        // check if more than 8
//        prologue.add(new AsmOPER("\t\tCMP\t$2,$1,8", null, null, null));
//        prologue.add(new AsmOPER("\t\tZSP\t$2,$2,1", null, null, null));
//        prologue.add(new AsmOPER("\t\tBP\t$2,over", null, null, null));
//        prologue.add(new AsmOPER("\t\tSTO\t$1,$0,0", null, null, null));
//        prologue.add(new AsmOPER("\t\tJMP\tgood", null, null, null)); // set next instr to good
//        prologue.add(new AsmOPER("over\t\tTRAP\t 0,Halt,0", null, null, null));

        // store FP
        prologue.addAll(loadConstToReg(0, -(code.frame.locsSize + 8)));
        prologue.add(new AsmOPER("\t\tSTO $253,$254,$0", null, null, null));
        // set FP to SP / move FP down
        prologue.add(new AsmOPER("ADD $253,$254,0", null, null, null));
        // load call frame size
        prologue.addAll(loadConstToReg(0, -code.frame.size));
        // move SP down
        prologue.add(new AsmOPER("ADD $254,$254,$0", null, null, null));
        // get return address
        prologue.add(new AsmOPER("GET $0,rJ", null, null, null));
        // store return address
        prologue.addAll(loadConstToReg(1, -(code.frame.locsSize + 16)));
        prologue.add(new AsmOPER("STO $0,$253,$1", null, null, null));
        // jump into function
        prologue.add(new AsmOPER("JMP " + code.entryLabel.name, null, null, null));

        code.instrs.addAll(0, prologue);
    }

    private void epilogue(Code code) {
        List<AsmInstr> epilogue = new LinkedList<>();

        epilogue.add(new AsmLABEL(code.exitLabel));

        // extra work
//        Every function can only be actively called 8-times
//        epilogue.add(new AsmOPER(String.format("\t\tLDA\t$0,%s", code.frame.label.name + "Count"), null, null, null));
//        epilogue.add(new AsmOPER("\t\tLDO\t$1,$0,0", null, null, null));
//        epilogue.add(new AsmOPER("\t\tSUB\t$1,$1,1", null, null, null));

        // store return value on location that FP points to
        Vector<MemTemp> uses = new Vector<>();
        uses.add(code.frame.RV);
        epilogue.add(new AsmOPER("STO `s0,$253,0", uses, null, null));
        // restore return address
        epilogue.addAll(loadConstToReg(1, -(code.frame.locsSize + 16)));
        epilogue.add(new AsmOPER("LDO $0,$253,$1", null, null, null));
        epilogue.add(new AsmOPER("PUT rJ,$0", null, null, null));
        // set stack pointer to frame pointer
        epilogue.add(new AsmOPER("ADD $254,$253,0", null, null, null));
        // set old frame pointer
        epilogue.addAll(loadConstToReg(0, -(code.frame.locsSize + 8)));
        // it doesn't matter if I use SP($254) or FP($253) because they are the same at this moment
        epilogue.add(new AsmOPER("LDO $253,$254,$0", null, null, null));

        epilogue.add(new AsmOPER(String.format("POP %d,0", nregs), null, null, null));

        code.instrs.addAll(epilogue);
    }

    // adds epilogue and prologue to every code fragment
    private void addEpilogueAndPrologue() {
        for (Code code : AsmGen.codes) {
            if (code.frame.label.name.equals("_getChar") || code.frame.label.name.equals("_putChar") ||
                    code.frame.label.name.equals("_new") || code.frame.label.name.equals("_del"))
                continue;

            prologue(code);
            epilogue(code);
        }
    }

    public void generateOutputFile() {
        try {
            File file = new File(outputFile);
            PrintWriter printWriter = new PrintWriter(file);
            System.out.println("Generating output program: " + file.getName());

            for (String s : startOfProgram()) {
                printWriter.println(s);
            }

            this.addEpilogueAndPrologue();

            // write code to output file
            for (Code code : AsmGen.codes) {
                if (code.frame.label.name.equals("_getChar") || code.frame.label.name.equals("_putChar") ||
                        code.frame.label.name.equals("_new") || code.frame.label.name.equals("_del"))
                    continue;

                AsmInstr prevInstr = null;
                for (int i = 0; i < code.instrs.size(); i++) {
                    AsmInstr currInst = code.instrs.get(i);
                    if (i > 0 && currInst instanceof AsmLABEL && prevInstr instanceof AsmLABEL)
                        printWriter.println("\t\tSWYM\n" + currInst);
                    else if (currInst instanceof AsmLABEL label)
                        printWriter.print(label);
                    else
                        printWriter.println("\t\t" + currInst.toString(RegAll.tempToReg));

                    prevInstr = currInst;
                }
            }

            StandardLibrary stdLib = new StandardLibrary();
            for (String s : stdLib.generateStdLib()) {
                printWriter.println(s);
            }

            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
