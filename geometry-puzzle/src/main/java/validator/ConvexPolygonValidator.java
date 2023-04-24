package validator;

import shape.Polygon;
import shape.Point;


public class ConvexPolygonValidator implements ShapeValidator {
	@Override
	public boolean validate(Polygon polygon) {
		if (polygon.size() < 3) return false;

		  Point p;
		  Point v;
		  Point u;
		  int res = 0;
		  for (int i = 0; i < polygon.size(); i++)
		  {
		    p = polygon.get(i);
		    Point tmp = polygon.get((i+1) % polygon.size());
		    v = new Point(tmp.getX() - p.getX(), tmp.getY() - p.getY());		  
		    u = polygon.get((i+2) % polygon.size());

		    if (i == 0) // in first loop direction is unknown, so save it in res
		      res = u.getX() * v.getY() - u.getY() * v.getX() + v.getX() * p.getY() - v.getY() * p.getX();
		    else
		    {
		      int newres = u.getX() * v.getY() - u.getY() * v.getX() + v.getX() * p.getY() - v.getY() * p.getX();
		      if ( (newres > 0 && res < 0) || (newres < 0 && res > 0) )
		        return false;
		    }
		  }
		  return true;
	}
}
