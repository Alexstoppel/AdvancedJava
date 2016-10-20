package ass1;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class FastaTools {

    public static void main(String[] args) {
        ArrayList<Sequence> allSequences = new ArrayList<>();
        try {
            allSequences = readFasta(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        CommandLine cl = new CommandLine(allSequences);
        cl.printFormated();
        cl.printStatistics();
    }

    /**
     * Reads multi fasta file and return sequence objects with header and nucleotide sequence.
     * @param file Fasta FIle
     * @return ArrayList with segments (header + sequence each)
     * @throws IOException if something's wrong with the fasta file
     */
    private static ArrayList<Sequence> readFasta(String file) throws IOException {
        ArrayList<Sequence> seqs = new ArrayList<>();
        FileReader r = new FileReader(file);
        BufferedReader br = new BufferedReader(r);
        String line = br.readLine();
        Sequence s = new Sequence();
        String tmpline = "";
        while (true) {
            //System.out.println(line);
            if (line == null){
                if (!Objects.equals(tmpline, "")) { s.setSequence(tmpline); }
                seqs.add(s);
                break;
            }
            if (line.startsWith(">")){
                if (s.getHeader() != null){
                    seqs.add(s);
                }
                if (!Objects.equals(tmpline, "")) { s.setSequence(tmpline); }
                tmpline = "";
                s = new Sequence();
                s.setHeader(line);
            } else {
                tmpline+=line;
            }
            line = br.readLine();
        }
        return seqs;
    }
}
