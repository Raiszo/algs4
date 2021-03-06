import java.util.TreeSet;
import java.util.ArrayList;
import java.awt.Color;

public class PointSET {
  private TreeSet<Point2D> point_set;
  public PointSET()                               // construct an empty set of points
  {
    point_set = new TreeSet<Point2D>();
  }
  public boolean isEmpty()                        // is the set empty?
  {
    return point_set.isEmpty();
  }
  public int size()                               // number of points in the set
  {
    return point_set.size();
  }
  public void insert(Point2D p)                   // add the point p to the set (if it is not already in the set)
  {
    point_set.add(p);
  }
  public boolean contains(Point2D p)              // does the set contain the point p?
  {
    return point_set.contains(p);
  }
  public void draw()                              // draw all of the points to standard draw
  {
    for (Point2D point : point_set){point.draw();}
  }
  public Iterable<Point2D> range(RectHV rect)     // all points in the set that are inside the rectangle
  {
    ArrayList<Point2D> contained_points = new ArrayList<Point2D>();
    for (Point2D point : point_set)
    {
     if (point.x() > rect.xmin() & point.x() < rect.xmax() & point.y() > rect.ymin() & point.y() < rect.ymax())
     {
       contained_points.add(point);
     }
    }
    return contained_points;
  }
  public Point2D nearest(Point2D p)               // a nearest neighbor in the set to p; null if set is empty
  {
    Point2D nearest_point = point_set.first(); //initialize this with the first point
    if (isEmpty()) return null;
    else
    {
      for (Point2D point : point_set)
      {
        if (p.distanceTo(point) < p.distanceTo(nearest_point)) nearest_point = point;
      }
      return nearest_point;
    }
  }
  public static void main(String[] args)
  {
    PointSET point_set = new PointSET();
    for (int i = 0; i < args.length; i = i + 2)
    {
      point_set.insert(new Point2D(Double.parseDouble(args[i]),Double.parseDouble(args[i+1])));
    }
    RectHV box = new RectHV(0.1300,0.320,0.350,0.40);
    StdDraw.setPenRadius(.004);
    point_set.draw();
    StdDraw.setPenColor(StdDraw.RED);
    Iterable<Point2D> point_contained = point_set.range(box);
    for (Point2D point : point_contained) point.draw();
    box.draw();
  }
}
