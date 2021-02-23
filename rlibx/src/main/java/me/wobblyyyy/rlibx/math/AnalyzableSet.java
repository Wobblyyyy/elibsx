/*
 *
 * Copyright (c) 2020, Colin Robertson (wobblyyyy@gmail.com)
 *
 * This file is part of the elibsx project. The elibsx project is licensed
 * under the GNU General Public License V3.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * license for the specific language governing permissions and limitations
 * under the License.
 *
 * Along with this file, you should have received a license file, containing
 * a copy of the GNU General Public License V3. If you did not receive a copy
 * of that license, you may find one online.
 *
 * elibsx GitHub repository:
 * https://github.com/Wobblyyyy/elibsx
 *
 * GNU General Public License V3:
 * http://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *
 */

package me.wobblyyyy.rlibx.math;

import me.wobblyyyy.intra.ftc2.utils.math.Math;

import java.util.ArrayList;

/**
 * A set of numbers that can be expanded and analyzed.
 *
 * <p>
 * The goal of this class is to provide several different data attributes that
 * can be gleaned based on looking at the stream of inputted data.
 * </p>
 *
 * @author Colin Robertson
 * @since 0.2.0
 */
public class AnalyzableSet {
    /**
     * An {@code ArrayList} of all of the numbers added to the set.
     */
    private final ArrayList<Double> data = new ArrayList<>();

    /**
     * An {@code ArrayList} of all of the deviations that have been added
     * to the set.
     */
    private final ArrayList<Double> deviations = new ArrayList<>();

    /**
     * The last-recorded number.
     */
    private double lastNumber;

    /**
     * The current sum of the set.
     */
    private double currentSum;

    /**
     * How many items are currently in the set.
     */
    private double currentCount;

    /**
     * The set's current average.
     */
    private double currentAverage;

    /**
     * The set's minimum.
     */
    private double minimum;

    /**
     * The set's maximum.
     */
    private double maximum;

    /**
     * The last-recorded deviation.
     */
    private double lastDeviation;

    /**
     * The set's average deviation.
     */
    private double averageDeviation;

    /**
     * The set's minimum deviation.
     */
    private double minimumDeviation;

    /**
     * The set's maximum deviation.
     */
    private double maximumDeviation;

    /**
     * Create a new {@code AnalyzableSet} without any values.
     */
    public AnalyzableSet() {

    }

    /**
     * Create a new {@code AnalyzableSet} with a set of values.
     *
     * @param doubles the values that should be analyzed upon construction.
     */
    public AnalyzableSet(double... doubles) {
        for (double d : doubles) {
            analyze(d);
        }
    }

    /**
     * Unbox an {@code ArrayList} of {@code Double}s into an array of normal
     * and non-funky doubles.
     *
     * @param doubles the doubles to be unboxed.
     * @return the subsequently-unboxed doubles.
     */
    private double[] unboxDoubles(ArrayList<Double> doubles) {
        double[] array = new double[doubles.size()];

        for (Double d : doubles) {
            array[doubles.indexOf(d)] = d;
        }

        return array;
    }

    /**
     * Analyze a given number and update the recorded values to match.
     *
     * @param number the number to analyze.
     */
    private void analyze(double number) {
        data.add(number);

        currentSum += number;
        currentCount += 1;
        currentAverage = Math.average(unboxDoubles(data));

        minimum = Math.min(number, minimum);
        maximum = Math.max(number, maximum);

        lastDeviation = number - lastNumber;
        deviations.add(lastDeviation);
        averageDeviation = Math.average(unboxDoubles(deviations));

        minimumDeviation = Math.min(lastDeviation, minimumDeviation);
        maximumDeviation = Math.max(lastDeviation, maximumDeviation);
    }

    /**
     * Add a given number to the {@code AnalyzableSet}, thus updating all of
     * the available values about the set.
     *
     * @param number the number to add to the set.
     */
    public void add(double number) {
        analyze(number);
    }

    /**
     * Get the last number.
     *
     * @return the last number.
     */
    public double getLastNumber() {
        return lastNumber;
    }

    /**
     * Get the current sum.
     *
     * @return the current sum.
     */
    public double getSum() {
        return currentSum;
    }

    /**
     * Get the current average.
     *
     * @return the current average.
     */
    public double getAverage() {
        return currentAverage;
    }

    /**
     * Get the current minimum.
     *
     * @return the current minimum.
     */
    public double getMinimum() {
        return minimum;
    }

    /**
     * Get the current maximum.
     *
     * @return the current maximum.
     */
    public double getMaximum() {
        return maximum;
    }

    /**
     * Get the last deviation.
     *
     * @return the last deviation.
     */
    public double getLastDeviation() {
        return lastDeviation;
    }

    /**
     * Get the average deviation.
     *
     * @return the average deviation.
     */
    public double getAverageDeviation() {
        return averageDeviation;
    }

    /**
     * Get the minimum deviation.
     *
     * @return the minimum deviation.
     */
    public double getMinimumDeviation() {
        return minimumDeviation;
    }

    /**
     * Get the maximum deviation.
     *
     * @return the maximum deviation.
     */
    public double getMaximumDeviation() {
        return maximumDeviation;
    }
}
