package de.kfs.db.controller.filter;

import javafx.scene.control.TextFormatter;
/**
 * Class filters all characters and only allows input of positive integers
 */
public class IntegerFieldFilter extends TextFormatter<String> {


    /**
     * it literally does what its supposed to
     */
    public IntegerFieldFilter() {
        super(c -> {
            if(c.getControlNewText().isEmpty()) {
                return c;
            }
            //how am I this fucking smart???
            if(c.getControlNewText().matches("\\d+")) {
                return c;
            } else {
                return null;
            }
            //literal miracle, I'm suprised it works this well :o
        });

    }


}
