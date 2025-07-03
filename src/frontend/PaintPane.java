package frontend;

import backend.CanvasState;
import backend.Drawers;
import backend.model.*;
import javafx.geometry.Insets;
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


public class PaintPane extends BorderPane {
	// BackEnd
	private final CanvasState canvasState;

	// Canvas y relacionados
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
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
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);

		Button divideButtonH = new Button("Divide H");
		divideButtonH.setMinWidth(90);
		divideButtonH.setOnAction(e -> {
			if (selectedFigure == null) {
				statusPane.updateStatus("No hay figura seleccionada");
				return;
			}
			DivideFigureHorizontal.show(selectedFigure, divides ->{
				for(Figure figure : divides){
					canvasState.addFigure(figure);
				}
				canvasState.deleteFigure(selectedFigure);
				redrawCanvas();
			});
		});
		buttonsBox.getChildren().add(divideButtonH);

		Button multiplyButton = new Button("Multiply.");
		multiplyButton.setMinWidth(90);
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
		buttonsBox.getChildren().add(multiplyButton);

		Button moveButton = new Button("Trasladar");
		moveButton.setMinWidth(90);
		moveButton.setOnAction(event -> MoveFigure.show(selectedFigure, this::redrawCanvas));
		buttonsBox.getChildren().add(moveButton);

		ComboBox<BorderType> borderSelector = new ComboBox<>();
		borderSelector.getItems().addAll(BorderType.values());
		borderSelector.setValue(BorderType.NORMAL); // valor inicial
		buttonsBox.getChildren().add(borderSelector);


		buttonsBox.getChildren().add(fillColorPicker);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);

		HBox effectBar = new HBox(10);
		effectBar.getChildren().addAll(new Label("Efectos:"), lighteningCheckBox, darkeningCheckBox, hMirroringCheckBox, vMirroringCheckBox);
		effectBar.setPadding(new Insets(5));
		effectBar.setStyle("-fx-background-color: #999;");
		setTop(effectBar);

		// Cuando cambia el valor del combo, se lo asigna a la figura seleccionada
		borderSelector.setOnAction(e -> {
			if (selectedFigure != null) {
				selectedFigure.setBorderType(borderSelector.getValue());
				redrawCanvas();
			}
		});

		fillColorPicker.setOnAction(event -> {
			if (selectedFigure != null) {
				Color color = fillColorPicker.getValue();
				selectedFigure.setFillColor(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
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
			Figure newFigure = null;

			//ESTO HAY QUE CAMBIARLO ES MUY IMPERATIVO.
			// --------------------------------------------------------------------------------------
			Color color = fillColorPicker.getValue();
			RGBColor figureColor = new RGBColor(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());

			if (rectangleButton.isSelected()) {
				newFigure = new Rectangle(startPoint, endPoint, figureColor);
				newFigure.setDrawer(drawer); //CAMBIAR!!!
			} else if (circleButton.isSelected()) {
				double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
				newFigure = new Circle(startPoint, circleRadius, figureColor);
				newFigure.setDrawer(drawer); //CAMBIAR!!!
			} else if (squareButton.isSelected()) {
				double size = Math.abs(endPoint.getX() - startPoint.getY());
				newFigure = new Square(startPoint, size, figureColor);
				newFigure.setDrawer(drawer); //CAMBIAR!!!
			} else if (ellipseButton.isSelected()) {
				Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
				double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
				double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
				newFigure = new Ellipse(centerPoint, sMayorAxis, sMinorAxis, figureColor);
				newFigure.setDrawer(drawer); //CAMBIAR!!!
			} else {
				return;
			}
			// --------------------------------------------------------------------------------------

			newFigure.setLightening(lighteningCheckBox.isSelected());
			newFigure.setDarkening(darkeningCheckBox.isSelected());
			newFigure.sethMirroring(hMirroringCheckBox.isSelected());
			newFigure.setvMirroring(vMirroringCheckBox.isSelected());
			newFigure.setBorderType(borderSelector.getValue());
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
					lighteningCheckBox.setSelected(selectedFigure.hasLightening());
					darkeningCheckBox.setSelected(selectedFigure.hasDarkening());
					hMirroringCheckBox.setSelected(selectedFigure.hasHMirroring());
					vMirroringCheckBox.setSelected(selectedFigure.hasVMirroring());
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
				selectedFigure.setLightening(lighteningCheckBox.isSelected());
				redrawCanvas();
			}
		});

		darkeningCheckBox.setOnAction(event -> {
			if (selectedFigure != null) {
				selectedFigure.setDarkening(darkeningCheckBox.isSelected());
				redrawCanvas();
			}
		});

		hMirroringCheckBox.setOnAction(event ->{
			if(selectedFigure != null){
				selectedFigure.sethMirroring(hMirroringCheckBox.isSelected());
			}
			redrawCanvas();
		});

		vMirroringCheckBox.setOnAction(event ->{
			if(selectedFigure != null){
				selectedFigure.setvMirroring(vMirroringCheckBox.isSelected());
			}
			redrawCanvas();
		});

		setLeft(buttonsBox);
		setRight(canvas);
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


