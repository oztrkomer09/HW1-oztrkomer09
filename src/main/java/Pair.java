

import java.util.Objects;

public class Pair implements Comparable<Pair> {
    private String term1;
    private String term2;
    private double factor;

    public Pair(String t1, String t2, double factor) {
        this.term1 = t1;
        this.term2 = t2;
        this.factor = factor;
    }

    public String getTerm1() {
        return term1;
    }

    public void setTerm1(String t1) {
        this.term1 = t1;
    }

    public String getTerm2() {
        return term2;
    }

    public void setTerm2(String t2) {
        this.term2 = t2;
    }



    @Override
    public String toString() {
        return "HWork1.Pair{" +
                "term1='" + term1 + '\'' +
                ", term2='" + term2 + '\'' +
                ", factor=" + factor +
                '}';
    }

    @Override
    public int compareTo(Pair o) {
        if (o.factor > factor)
            return 1;
        else if (o.factor < factor)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Double.compare(pair.factor, factor) == 0 &&
                Objects.equals(term1, pair.term1) &&
                Objects.equals(term2, pair.term2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term1, term2, factor);
    }
}
