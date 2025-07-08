package frontend;

import backend.CanvasState;
import backend.Drawers;
import backend.model.*;
import backend.model.FigureBuilders.FigureBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class PaintPane extends BorderPane {
	// BackEnd
	private final CanvasState canvasState;
	private final Formater formater= new Formater();
	// Canvas y relacionados
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton deleteButton = new ToggleButton("Borrar");
	private final CheckBox lighteningCheckBox = new CheckBox("Aclaramiento");
	private final CheckBox darkeningCheckBox = new CheckBox("Oscurecimiento");
	private final CheckBox hMirroringCheckBox = new CheckBox("Espejo Horizontal");
	private final CheckBox vMirroringCheckBox = new CheckBox("Espejo Vertical");



	// Selector de color de relleno
	private final ColorPicker fillColorPicker = new ColorPicker(Color.YELLOW);

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private Figure selectedFigure;

	//Clase para dibujar figuras
	private final Drawers drawer = new JFXDrawer(gc, fillColorPicker);

	// StatusBar
	private final StatusPane statusPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] ActionArr = {selectionButton,deleteButton};
		FigureBottoms figuresBtm = new FigureBottoms();
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : ActionArr) {
			tool.setPrefWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		this.setLeft(buttonsBox);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setAlignment(Pos.TOP_CENTER);
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		buttonsBox.getChildren().add(selectionButton);
		buttonsBox.getChildren().addAll(figuresBtm.getBottomGroup());
		buttonsBox.getChildren().add(deleteButton);
		buttonsBox.getChildren().add(fillColorPicker);

		ComboBox<BorderType> borderSelector = new ComboBox<>();
		borderSelector.getItems().addAll(BorderType.values());
		//borderSelector.setPrefWidth(90);

		borderSelector.setValue(BorderType.NORMAL); // valor inicial
		buttonsBox.getChildren().add(borderSelector);

		fillColorPicker.setOnAction(event -> {
			if (selectedFigure != null) {
				Color color = fillColorPicker.getValue();
				selectedFigure.getFormat().setFillColor(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
				redrawCanvas();
			}
		});
		// ---------Panel Derecho---------
		VBox rightPanel = new VBox(10);
		rightPanel.setStyle("-fx-background-color: #999");
		rightPanel.setPadding(new Insets(10));
		this.setRight(rightPanel);
		Label operations = new Label("Operaciones:");
		operations.setStyle(" -fx-font-size: 14;");
		rightPanel.getChildren().add(operations);

		Button divideButtonH = new Button("Dividir An.");
		divideButtonH.setPrefWidth(90);
		divideButtonH.setOnAction(e -> {
			if (selectedFigure == null) {
				statusPane.updateStatus("No hay figura seleccionada");
				return;
			}
			DivideFigure.show(false,selectedFigure, dividesH ->{
				for(Figure figure : dividesH){
					canvasState.addFigure(figure);
				}
				canvasState.deleteFigure(selectedFigure);
				redrawCanvas();
			});
		});
		rightPanel.getChildren().add(divideButtonH);

		Button divideButtonV = new Button("Dividir Al.");
		divideButtonV.setPrefWidth(90);
		divideButtonV.setOnAction(e -> {
			if (selectedFigure == null) {
				statusPane.updateStatus("No hay figura seleccionada");
				return;
			}
			DivideFigure.show(true,selectedFigure, dividesV ->{
				for(Figure figure : dividesV){
					canvasState.addFigure(figure);
				}
				canvasState.deleteFigure(selectedFigure);
				redrawCanvas();
			});
		});
		rightPanel.getChildren().add(divideButtonV);

		Button multiplyButton = new Button("Multiplicar");
		multiplyButton.setPrefWidth(90);
		multiplyButton.setOnAction(event-> {
			if (selectedFigure == null) {
				statusPane.updateStatus("No hay figura seleccionada");
				return;
			}
			MultiplyFigure.show(selectedFigure, clones -> {
				for (Figure f : clones) {
					canvasState.addFigure(f);
				}
				redrawCanvas();
			});
		});
		rightPanel.getChildren().add(multiplyButton);

		Button moveButton = new Button("Trasladar");
		moveButton.setPrefWidth(90);
		moveButton.setOnAction(event -> MoveFigure.show(selectedFigure, this::redrawCanvas));
		rightPanel.getChildren().add(moveButton);

		// ---------Fin panel derecho---------

		Button cpyFormat = new Button("Copiar fmt.");
		Button pasteFormat = new Button("Pegar fmt.");
		cpyFormat.setPrefWidth(90);
		cpyFormat.setOnAction(action -> {
			if (selectedFigure == null) {
				statusPane.updateStatus("No hay figura seleccionada para copiar formato.");
			} else {
				formater.copy(selectedFigure);
				pasteFormat.setDisable(false);
				statusPane.updateStatus("Formato copiado.");
			}
		});
		buttonsBox.getChildren().add(cpyFormat);

		pasteFormat.setDisable(true);
		pasteFormat.setPrefWidth(90);
		pasteFormat.setOnAction(action -> {
			if (selectedFigure == null || formater.hasFormat()) {
				statusPane.updateStatus("No es posible pegar formato.");
			} else {
				formater.paste(selectedFigure);
				statusPane.updateStatus("Formato pegado.");
				redrawCanvas();
			}
		});
		buttonsBox.getChildren().add(pasteFormat);


		HBox effectBar = new HBox(10);
		effectBar.getChildren().addAll(new Label("Efectos:"), lighteningCheckBox, darkeningCheckBox, hMirroringCheckBox, vMirroringCheckBox);
		effectBar.setPadding(new Insets(5));
		effectBar.setAlignment(Pos.CENTER);
		effectBar.setStyle("-fx-background-color: #999;");
		setTop(effectBar);

		// Cuando cambia el valor del combo, se lo asigna a la figura seleccionada
		borderSelector.setOnAction(e -> {
			if (selectedFigure != null) {
				selectedFigure.getFormat().setBorderType(borderSelector.getValue());
				redrawCanvas();
			}
		});

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if (startPoint == null) {
				return;
			}
			if (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return;
			}
			//-------------------------------------------------------------------------------------
			Color color = fillColorPicker.getValue();
			RGBColor figureColor = new RGBColor(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
			FigureBuilder builder = figuresBtm.getBuilder();

			if(builder == null){
				return;
			}

			Figure newFigure = builder.builder(startPoint, endPoint,figureColor,drawer);

			// --------------------------------------------------------------------------------------

			newFigure.getFormat().setLightening(lighteningCheckBox.isSelected());
			newFigure.getFormat().setDarkening(darkeningCheckBox.isSelected());
			newFigure.getFormat().setHMirroring(hMirroringCheckBox.isSelected());
			newFigure.getFormat().setVMirroring(vMirroringCheckBox.isSelected());
			newFigure.getFormat().setBorderType(borderSelector.getValue());
			canvasState.addFigure(newFigure);

			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for (Figure figure : canvasState.figures()) {
				if (figure.Belongs(eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if (found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if (selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.figures()) {
					if (figure.Belongs(eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}

				if (found) {
					lighteningCheckBox.setSelected(selectedFigure.getFormat().hasLightening());
					darkeningCheckBox.setSelected(selectedFigure.getFormat().hasDarkening());
					hMirroringCheckBox.setSelected(selectedFigure.getFormat().hasHMirroring());
					vMirroringCheckBox.setSelected(selectedFigure.getFormat().hasVMirroring());
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					lighteningCheckBox.setSelected(false);
					darkeningCheckBox.setSelected(false);
					hMirroringCheckBox.setSelected(false);
					vMirroringCheckBox.setSelected(false);
					statusPane.updateStatus("Ninguna figura encontrada");
				}

				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if (selectionButton.isSelected() && selectedFigure != null) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 70;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 70;
				selectedFigure.move(diffX,diffY);
				redrawCanvas();
			}
		});

		deleteButton.setOnAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		lighteningCheckBox.setOnAction(event -> {
			if (selectedFigure != null) {
				selectedFigure.getFormat().setLightening(lighteningCheckBox.isSelected());
				redrawCanvas();
			}
		});

		darkeningCheckBox.setOnAction(event -> {
			if (selectedFigure != null) {
				selectedFigure.getFormat().setDarkening(darkeningCheckBox.isSelected());
				redrawCanvas();
			}
		});

		hMirroringCheckBox.setOnAction(event ->{
			if(selectedFigure != null){
				selectedFigure.getFormat().setHMirroring(hMirroringCheckBox.isSelected());
			}
			redrawCanvas();
		});

		vMirroringCheckBox.setOnAction(event ->{
			if(selectedFigure != null){
				selectedFigure.getFormat().setVMirroring(vMirroringCheckBox.isSelected());
			}
			redrawCanvas();
		});
		setLeft(buttonsBox);
		setCenter(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setLineWidth(10);


		for (Figure figure : canvasState.figures()) {
			if(figure==selectedFigure){
				gc.setStroke(Color.RED);
			}else{
				gc.setStroke(Color.BLACK);
			}

			//gc.setFill(fillColorPicker.getValue());
			figure.drawSelf();
		}
	}

}


