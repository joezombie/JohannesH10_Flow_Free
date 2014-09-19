package is.ru.ANDR;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.*;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johannes Gunnar Heidarsson on 13.9.2014.
 */
public class BoardView extends View {
    private final int NUMBER_OF_CELLS = 5;
    private int cellWidth;
    private int cellHeight;

    private Rect rect = new Rect();
    private Paint paintGrid = new Paint();
    private Paint paintPath = new Paint();
    private Paint paintCircle = new Paint();
    private ArrayList<CellPath> cellPaths = new ArrayList<CellPath>();
    private CellPath currentCellPath;
    private Path path = new Path();

    private boolean audioOn;
    private Context context;

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        this.paintGrid.setStyle(Paint.Style.STROKE);
        this.paintGrid.setColor(Color.GRAY);
        this.paintGrid.setStrokeWidth(4);

        this.paintPath.setStyle(Paint.Style.STROKE);
        this.paintPath.setColor(Color.BLUE);
        this.paintPath.setStrokeWidth(32);
        this.paintPath.setStrokeCap(Paint.Cap.ROUND);
        this.paintPath.setStrokeJoin(Paint.Join.ROUND);
        this.paintPath.setAntiAlias(true);

        this.paintCircle.setStyle(Paint.Style.FILL_AND_STROKE);
        //this.paintCircle.setStrokeCap(Paint.Cap.ROUND);
        this.paintCircle.setStrokeWidth(4);


        cellPaths.add( new CellPath(0,3,3,4, Color.GREEN) );
        cellPaths.add( new CellPath(3,1,4,2, Color.BLUE) );

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        // Test
        this.audioOn = pref.getBoolean("board_audio", true);
    }

    private int xToCol( int x ) {
        return (x - getPaddingLeft()) / this.cellWidth;
    }

    private int yToRow( int y ) {
        return (y - getPaddingTop()) / this.cellHeight;
    }

    private int colToX( int col ) {
        return col * this.cellWidth + getPaddingLeft() ;
    }

    private int rowToY( int row ) {
        return row * this.cellHeight + getPaddingTop() ;
    }

    private boolean areNeighbours( int c1, int r1, int c2, int r2 ) {
        return ( Math.abs(c1-c2) + Math.abs(r1-r2) ) == 1;
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        int size = Math.min(width, height);
        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        this.cellWidth = (xNew - getPaddingLeft() - getPaddingRight()) / NUMBER_OF_CELLS;
        this.cellHeight = (yNew - getPaddingTop() - getPaddingBottom()) / NUMBER_OF_CELLS;

        this.paintGrid.setStrokeWidth(Math.min(this.cellWidth / 10, this.cellHeight / 10));
        this.paintPath.setStrokeWidth(Math.min(this.cellWidth / 4, this.cellHeight / 4));
    }

    @Override
    protected void onDraw (Canvas canvas){
        for( int row=0 ; row<NUMBER_OF_CELLS; ++row){
            for (int col=0; col<NUMBER_OF_CELLS; ++col){
                int x = colToX( col );
                int y = rowToY( row );
                this.rect.set(x, y, x + this.cellWidth, y + this.cellHeight);
                canvas.drawRect(this.rect, this.paintGrid);
            }
        }
        for(CellPath cellPath : this.cellPaths){
            this.paintCircle.setColor(cellPath.getColor());
            for (Circle c : cellPath.getCircleList()){
                float radius = this.cellWidth / 4;
                canvas.drawCircle(colToX(c.getColumn()) + this.cellWidth/2, rowToY(c.getRow()) + this.cellHeight/2, radius, this.paintCircle);
            }
        }



        for(CellPath cellPath : this.cellPaths) {
            this.path.reset();
            if (!cellPath.isEmpty()) {
                List<Coordinate> coordinateList = cellPath.getCoordinates();
                Coordinate coordinate = coordinateList.get(0);
                this.path.moveTo(colToX(coordinate.getCol()) + this.cellWidth / 2, rowToY(coordinate.getRow()) + this.cellHeight / 2);
                for (int i = 1; i < coordinateList.size(); ++i) {
                    coordinate = coordinateList.get(i);
                    this.path.lineTo(colToX(coordinate.getCol()) + this.cellWidth / 2, rowToY(coordinate.getRow()) + this.cellHeight / 2);
                }
                this.paintPath.setColor(cellPath.getColor());
                canvas.drawPath(this.path, this.paintPath);
            }

        }
    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {
        int x = (int) event.getX();         // NOTE: event.getHistorical... might be needed.
        int y = (int) event.getY();
        int c = xToCol( x );
        int r = yToRow( y );
        Coordinate newCoordinate = new Coordinate(c, r);

        if (c >= NUMBER_OF_CELLS || r >= NUMBER_OF_CELLS){
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            for(CellPath cellPath : this.cellPaths) {
                for (Circle circle : cellPath.getCircleList()) {
                    if (circle.isLocatedAt(r, c)) {
                        cellPath.reset();
                        cellPath.addCoordinate(newCoordinate);
                        this.currentCellPath = cellPath;
                    }
                }
            }

        } else if (event.getAction() == MotionEvent.ACTION_MOVE){

            boolean coordinateIsOccupied = false;
            for(CellPath cellPath: this.cellPaths){
                if(cellPath != this.currentCellPath){
                    coordinateIsOccupied = coordinateIsOccupied || cellPath.occupiesCoordinate(newCoordinate);

                }
            }
            if (!currentCellPath.isEmpty() && !currentCellPath.isConnected() && !coordinateIsOccupied) {
                Coordinate lastCoordinate = currentCellPath.getLastCoordinate();
                if (areNeighbours(lastCoordinate.getCol(), lastCoordinate.getRow(), c, r)) {
                    currentCellPath.addCoordinate(newCoordinate);
                    if(currentCellPath.isConnected()){
                        if(audioOn){

                            CharSequence text = "Connected :)";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                    invalidate();
                }
            }


        }

        return true;
    }

}

















