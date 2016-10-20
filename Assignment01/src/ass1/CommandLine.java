package ass1;

import java.util.ArrayList;

/**
 * Created by Alex on 20.10.2016.
 */
public class CommandLine{

    private ArrayList<Sequence> allSequences;
    private int maxLength = 0;

    public CommandLine( ArrayList<Sequence> seqs){
        this.allSequences = seqs;
        for (Sequence allSequence : this.allSequences) {
            if (this.maxLength < allSequence.getLength()) {
                this.maxLength = allSequence.getLength();
            }
        }
    }

    /**
     * Print formated sequence and sequence statistics
     */
    public void printFormated(){
        for (int i = 0; i < maxLength;){
            String partOfSequence;
            System.out.println(String.format("%1$33d %2$57d",i+1,i+60));
            for (Sequence allSequence : this.allSequences) {
                partOfSequence = "";
                if (i + 60 < allSequence.getSequenceAsString().length()) {
                    partOfSequence += allSequence.getSequenceAsString().substring(i, i + 60);
                } else {
                    partOfSequence += allSequence.getSequenceAsString().substring(i);
                }
                System.out.println(String.format("%1$-30s %2$-20s", allSequence.getHeader(), partOfSequence));
            }
            System.out.println();
            i+= 60;
        }
    }

    public void printStatistics(){
        int[] stats = new int[5];
        for (Sequence allSequence : this.allSequences) {
            int[] tmp = allSequence.getSequenceStatistiscs();
            for (int j = 0; j < tmp.length; j++) {
                stats[j] += tmp[j];
            }
        }
        System.out.println("Numnber of sequences: " + this.allSequences.size());
        System.out.println("Shortest length: " + getMinLength() + " (excluding \'-\'s: " + getMinBaseNr());
        System.out.println("Average length: " + getAvgLength() + " (excluding \'-\'s: " + getAvgBaseNr());
        System.out.println("Longest length: " + getMaxLength() + " (excluding \'-\'s: " +getMaxBaseNr());
        System.out.println("Counts: A: " + stats[0] + ", C: " + stats[1] + ", G: " + stats[2] + ", T: " + stats[3] + ", -: " + stats[4]);

    }

    private int getMinLength(){
        int min = Integer.MAX_VALUE;
        for (Sequence allSequence : this.allSequences) {
            if (allSequence.getLength() < min) {
                min = allSequence.getLength();
            }
        }
        return min;
    }

    private int getMinBaseNr(){
        int min = Integer.MAX_VALUE;
        for (Sequence allSequence : this.allSequences) {
            int gaps = allSequence.getSequenceStatistiscs()[4];
            if (allSequence.getLength() - gaps < min) {
                min = allSequence.getLength() - gaps;
            }
        }
        return min;
    }

    private int getMaxLength(){
        int max = Integer.MIN_VALUE;
        for (Sequence allSequence : this.allSequences)
            if (allSequence.getLength() > max) {
                max = allSequence.getLength();
            }
        return max;
    }
    private int getMaxBaseNr(){
        int max = Integer.MIN_VALUE;
        for (Sequence allSequence : this.allSequences) {
            int gaps = allSequence.getSequenceStatistiscs()[4];
            if (allSequence.getLength() - gaps > max) {
                max = allSequence.getLength() - gaps;
            }
        }
        return max;
    }

    private int getAvgLength(){
        int avg = 0;
        for (Sequence allSequence : this.allSequences) {
            avg += allSequence.getLength();
        }
        return avg/this.allSequences.size();
    }

    private int getAvgBaseNr(){
        int avg = 0;
        for (Sequence allSequence : this.allSequences) {
            int gaps = allSequence.getSequenceStatistiscs()[4];
            avg += allSequence.getLength() - gaps;
        }
        return avg/this.allSequences.size();
    }

}

