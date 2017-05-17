package uk.co.hexeption.api.mod.options;

import joptsimple.internal.Strings;
import uk.co.hexeption.api.exception.ArgumentException;
import uk.co.hexeption.api.mod.options.type.BooleanValue;
import uk.co.hexeption.api.mod.options.type.ChoiceValue;
import uk.co.hexeption.api.mod.options.type.DoubleValue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hexeption on 16/01/2017.
 */
public class Option {

    public String name;

    public String desc;

    public Value value;

    public Type type;

    public Map<String, Option> options = new LinkedHashMap<>();

    public Option(String name, String desc, Value value, Type type, List<Option> options) {

        this.name = name;
        this.desc = desc;
        this.value = value;
        this.type = type;
        if (options != null) {
            for (Option option : options) {
                this.options.put(option.name.toLowerCase().replaceAll(" ", ""), option);
            }
        }
    }

    public static Option get(Map<String, Option> options, String valueName) {

        return options.get(valueName);
    }

    public static Option get(Map<String, Option> options, String valueName, String valueName2) {

        return options.get(valueName).options.get(valueName2);
    }

    public static void setOptionValue(Option option, String arg) throws Exception {

        Object value = null;

        switch (option.type) {
            case BOOLEAN:
                try {
                    if (arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("false")) {
                        value = Boolean.parseBoolean(arg);
                    } else {
                        throw new ArgumentException();
                    }
                } catch (Exception e) {
                    throw new ArgumentException();
                }
                break;
            case NUMBER:
                try {
                    value = Double.parseDouble(arg);
                } catch (Exception e) {
                    throw new ArgumentException();
                }
                break;
            case STRING:
                value = arg;
                break;
            case KEYBIND:
                value = arg.toUpperCase();
                break;
            case LIST:
                List<String> list = new ArrayList<>();
                list.addAll((List<String>) option.value.value);

                if (list.contains(arg)) {
                    list.remove(arg);
                } else {
                    list.add(arg);
                }

                value = 1;
                break;
            case CHOICE:
                int in = -1;
                String[] liste = ((ChoiceValue) option.value).list;
                for (int i = 0; i < liste.length; i++) {
                    if (liste[i].equalsIgnoreCase(arg)) {
                        in = i;
                    }
                }
                if (in != -1) {
                    value = liste[in];
                } else {
                    throw new ArgumentException();
                }
                break;
            case OTHER:
                value = arg;
                break;
        }

        if (value != null) {
            option.value.value = value;
        }
    }

    public boolean BOOLEAN() {

        if (value instanceof BooleanValue) {
            return (boolean) value.value;
        }

        return false;
    }

    public double DOUBLE() {

        if (value instanceof DoubleValue) {
            return (double) value.value;
        }

        return 0;
    }

    public int INTEGER() {

        return (int) DOUBLE();
    }

    public String STRING() {

        if (type == Type.LIST) {
            return Strings.join(LIST(), ",");
        }

        return value.value.toString();
    }

    public List<String> LIST() {

        return (List<String>) value.value;
    }

    public String CHOICE() {

        return STRING();
    }

    public enum Type {
        BOOLEAN("<true|false>"),
        NUMBER("<number>"),
        STRING("<text>"),
        KEYBIND("<key>"),
        LIST("<value>"),
        CHOICE("<%s>"),
        OTHER("<value>");

        public String usage;

        Type(String usage) {

            this.usage = usage;
        }
    }
}