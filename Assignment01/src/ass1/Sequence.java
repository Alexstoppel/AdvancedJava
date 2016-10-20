package ass1;

import java.util.ArrayList;

/**
 * Created by Alex on 20.10.2016.
 */
class Sequence {

    private String header;
    private ArrayList<Nucleotide> seq = new ArrayList<>();
    private int[] stats;

    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Store givven sequence as nucleotide array
     * @param sequence Sequence as String
     */
    public void setSequence(String sequence) {
        if(sequence.replace("\n","").toUpperCase().matches("^[ACGU-]+")) {
            for (int i = 0; i < sequence.length(); i++) {
                char tmp = sequence.charAt(i);
                this.seq.add(i, new Nucleotide(sequence.charAt(i)));
            }
            calcSequenceStatistics();
        } else {
            System.err.println("Invalid sequence: " + sequence);
        }
    }

    public String getHeader() {
        return this.header;
    }

    public ArrayList<Nucleotide> getSeq() {
        return this.seq;
    }


    /**
     * Returns string of nucleotide sequence
     * @return Nucleotide sequence as sting
     */
    public String getSequenceAsString() {
        char[] s = new char[this.seq.size()];
        for (int i = 0; i < this.seq.size(); i++) s[i] = this.seq.get(i).getType();
        return new String(s);
    }

    /**
     * Not yet required but might be nice to have for the next assignment.
     * @param start position
     * @param end position
     * @return Array of nucleotides from start to end position
     */
    public Nucleotide[] getSequence(int start, int end) {
        if (start < getLength()){
            if (end+1 > getLength()){
                end = getLength();
            }
            Nucleotide[] n = new Nucleotide[end-start+1];
            for (int j = start; j < end+1; j++) {
                n[j] = this.seq.get(j);
            }
         return n;
        }
        return null;
    }

    /**
     * @return Length of sequence
     */
    public int getLength(){
        return this.seq.size();
    }

    public int[] getSequenceStatistiscs() {
        return this.stats;
    }

    private void calcSequenceStatistics(){
        int[] baseCounter = new int[5];
        for (int i = 0; i < baseCounter.length; i++){
            baseCounter[i] = 0;
        }
        for (Nucleotide n : this.seq)
            switch (n.getType()) {
                case 'A':
                    baseCounter[0]++;
                    break;
                case 'C':
                    baseCounter[1]++;
                    break;
                case 'G':
                    baseCounter[2]++;
                    break;
                case 'U':
                    baseCounter[3]++;
                    break;
                case '-':
                    baseCounter[4]++;
                    break;
                default:
                    break;
            }
        this.stats = baseCounter;
    }
}
