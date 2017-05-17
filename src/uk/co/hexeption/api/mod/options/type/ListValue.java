package uk.co.hexeption.api.mod.options.type;

import uk.co.hexeption.api.mod.options.Value;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hexeption on 13/05/2017.
 */
public class ListValue extends Value {

    public ListValue(List<String> list) {

        super(list);
    }

    public ListValue(String[] list) {

        this(Arrays.asList(list));
    }
}
