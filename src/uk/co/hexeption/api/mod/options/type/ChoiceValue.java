package uk.co.hexeption.api.mod.options.type;

import uk.co.hexeption.api.mod.options.Value;

/**
 * Created by Hexeption on 13/05/2017.
 */
public class ChoiceValue extends Value {

    public String[] list;

    public ChoiceValue(int value, String[] list) {

        super(list[value]);
        this.list = list;
    }
}
