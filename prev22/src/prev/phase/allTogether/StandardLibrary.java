package prev.phase.allTogether;

import java.util.LinkedList;
import java.util.List;

public class StandardLibrary {
    public List<String> getChar() {
        List<String> instrs = new LinkedList<>();
        instrs.add("_getChar\t\tLDA\t$255,InArgs");
        instrs.add("\t\tTRAP\t0,Fgets,StdIn");
        instrs.add("\t\tLDB\t$0,InBuff");
        instrs.add("\t\tSTO\t$0,$254,0");
        instrs.add("\t\tPOP");

        return instrs;
    }

    public List<String> putChar() {
        List<String> instrs = new LinkedList<>();
        instrs.add("_putChar\t\tLDO\t$0,$254,8"); // first parameter
        instrs.add("\t\tLDA\t$255,OutBuff");
        instrs.add("\t\tSTB\t$0,$255,0");
        instrs.add("\t\tTRAP\t0,Fputs,StdOut");
        instrs.add("\t\tPOP");

        return instrs;
    }

    /*
    public List<String> putString() {
        return null;
    }
    */

    public List<String> newMemory() {
        List<String> instrs = new LinkedList<>();
        // size of memory which we want to allocate
        instrs.add("_new\t\tLDO\t$0,$254,0");
        instrs.add("\t\tSTO\t$252,$254,0");
        instrs.add("\t\tADD\t$252,$252,$0");
        instrs.add("\t\tPOP\t0,0");

        return instrs;
    }

    public List<String> delMemory() {
        List<String> instrs = new LinkedList<>();
        instrs.add("_del\t\tPOP\t0,0");

        return instrs;
    }

    public List<String> generateStdLib() {
        List<String> stdLib = new LinkedList<>();
        stdLib.addAll(getChar());
        stdLib.addAll(putChar());
        //stdLib.addAll(putString());
        stdLib.addAll(newMemory());
        stdLib.addAll(delMemory());

        return stdLib;
    }
}
