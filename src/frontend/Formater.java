package frontend;

import backend.model.Figure;
import backend.model.FigureFormat;

public class Formater {
    private FigureFormat lastFormat = null;//Ultimo formato que se le copio a una figura.

    public void copy(Figure figure) {
        if (figure != null) {
            lastFormat = figure.getFormat();
        }
    }

    public void paste(Figure figure) {
        if (figure != null && lastFormat != null) {
            lastFormat.setFormat(figure.getFormat());
        }
    }
    public boolean hasFormat() {
        return lastFormat != null;
    }
}
