package lessons.comparableAndComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Homework02 {
    public static void main(String[] args) {
        List<Torus> torsos = Arrays.asList(
                new Torus(50, 10),
                new Torus(60, 20),
                new Torus(80, 5)
        );

        Collections.sort(torsos);
        for (Torus torus: torsos) {
            System.out.println(torus.getVolume());
        }
    }
}

class Torus implements Comparable<Torus> {
    private int majorR;
    private int minorR;

    Torus(int majorR, int minorR) {
        this.majorR = majorR;
        this.minorR = minorR;
    }

    public int getMajorR() {
        return majorR;
    }

    public int getMinorR() {
        return minorR;
    }

    public double getVolume() {
        return 2 * Math.PI * majorR * minorR * minorR;
    }

    public int compareTo(Torus t) {
        return (int) (getVolume() - t.getVolume());
//        return 0;
    }
}
