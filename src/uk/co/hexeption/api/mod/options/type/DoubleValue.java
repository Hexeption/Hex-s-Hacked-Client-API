package uk.co.hexeption.api.mod.options.type;

import uk.co.hexeption.api.mod.options.Value;

/**
 * Created by Hexeption on 13/05/2017.
 */
public class DoubleValue extends Value {

    public double[] limit;

    public double step;

    public DoubleValue(double value, double[] limit, double step) {

        super(value);
        this.limit = limit;
        this.step = step;

    }
}
