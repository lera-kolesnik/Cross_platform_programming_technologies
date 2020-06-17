package lab_2;
public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;

    public Point3d ( double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d () {
       this(0,0,0);
    }

    public double getX(){
        return xCoord;
    }

    public double getY(){
        return yCoord;
    }

    public double getZ(){
        return zCoord;
    }

    public void setX(double x){
        xCoord = x;
    }

    public void setY(double y){
        yCoord = y;
    }

    public void setZ(double z){
        zCoord = z;
    }

    public boolean compare (Point3d other){
        if (this.xCoord == other.xCoord && this.yCoord == other.yCoord && this.zCoord == other.zCoord){
            return true;
        } else {
            return false;
        }
    }

    public double distanceTo(Point3d other){
        return (
            Math.sqrt(
                Math.pow(this.xCoord - other.xCoord, 2) + 
                Math.pow(this.yCoord - other.yCoord, 2) + 
                Math.pow(this.zCoord - other.zCoord, 2)
            )
        );
    }


}